package com.vblessings.nhs.exception;


import cn.hutool.core.exceptions.ValidateException;
import com.alibaba.fastjson.JSONException;
import com.vblessings.nhs.request.bean.RequestDetail;
import com.vblessings.nhs.request.holder.RequestDetailThreadLocal;
import com.vblessings.nhs.result.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * 统一异常处理器
 * @author ZJY
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		log.debug("请求有参数才进来:{} ",binder.getObjectName());
	}

	/**
	 * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("author", "ZJY");
	}

	/**
	 * 处理自定义业务异常
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = BusinessException.class)
	public ResultBody handleException(BusinessException ex) {
		// 打印堆栈信息
		printRequestDetail();
		printApiCodeException(ex.getResponseEnum(), ex);
		return ResultBody.newErrorInstance(ex.getResponseEnum().getCode(),
				ex.getMessage(), ex.getArgs());
	}

	/**
	 * @description: 校验异常
	 *
	 * @param ex
	 * @return
	 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ValidateException.class)
    public ResultBody handleException(ValidateException ex) {
        // 打印堆栈信息
        printRequestDetail();
        return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				ex.getMessage());
    }

	/**
	 * 非法参数验证异常
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResultBody handleException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get();
        return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				message);
	}

	/**
	 * 非法参数验证异常
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({BindException.class})
	public ResultBody handleException(BindException ex) {
        String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get();
		return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				message);
	}

	//处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResultBody ConstraintViolationExceptionHandler(ConstraintViolationException e) {
		String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).findFirst().get();
		return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				message);
	}


	/**
	 * 缺少请求参数异常处理
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResultBody handleException(MissingServletRequestParameterException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.BAD_REQUEST, ex);
		return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				ResponseEnum.BAD_REQUEST.getMessage());
	}

	/**
	 * 缺少请求体异常处理器
	 *
	 * @param e 缺少请求体异常
	 * @return ResponseResult
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResultBody parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
		log.error("", e);
		return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				"参数不合法:" + e.getCause().getMessage());
	}


	/**
	 * 404异常处理
	 *
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResultBody handleException(NoHandlerFoundException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.NOT_FOUND, ex);
		return ResultBody.newErrorInstance(ResponseEnum.NOT_FOUND.getCode(),
				ResponseEnum.NOT_FOUND.getMessage());
	}

	/**
	 * 不支持方法异常处理
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResultBody handleException(HttpRequestMethodNotSupportedException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.METHOD_NOT_ALLOWED, ex);
		return ResultBody.newErrorInstance(ResponseEnum.METHOD_NOT_ALLOWED.getCode(),
				ResponseEnum.METHOD_NOT_ALLOWED.getMessage());
	}

	/**
	 * 非法获取异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = IllegalAccessException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResultBody handleException(IllegalAccessException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.ILLEGAL_ACCESS, ex);
		return ResultBody.newErrorInstance(ResponseEnum.ILLEGAL_ACCESS.getCode(),
				ResponseEnum.ILLEGAL_ACCESS.getMessage());
	}

	/**
	 * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResultBody handleException(MaxUploadSizeExceededException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.FILE_TOO_LARGE, ex);
		return ResultBody.newErrorInstance(ResponseEnum.FILE_TOO_LARGE.getCode(),
				ResponseEnum.FILE_TOO_LARGE.getMessage());
	}

	/**
	 * 数据库校验异常
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResultBody handleException(DataIntegrityViolationException ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.DATA_INTEGRITY_VIOLATION, ex);
		return ResultBody.newErrorInstance(ResponseEnum.DATA_INTEGRITY_VIOLATION.getCode(),
				"数据库校验错误：" + ex.getCause().getMessage());
	}

	/**
	 * 默认的异常处理
	 *
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultBody handleException(Exception ex) {
		printRequestDetail();
		printApiCodeException(ResponseEnum.INTERNAL_SERVER_ERROR, ex);
		return ResultBody.newErrorInstance();
	}

	/**
	 * 获取httpStatus格式化字符串
	 *
	 * @param responseEnum
	 * @return
	 */
	private String getApiCodeString(IResponseEnum responseEnum) {
		if (responseEnum != null) {
			return String.format("errorCode: %s, errorMessage: %s", responseEnum.getCode(), responseEnum.getMessage());
		}
		return null;
	}

	/**
	 * 打印请求详情
	 */
	private void printRequestDetail() {
		RequestDetail requestDetail = RequestDetailThreadLocal.getRequestDetail();
		if (requestDetail != null) {
			log.error("异常来源：ip: {}, path: {}", requestDetail.getIp(), requestDetail.getPath());
		}
	}
	/**
	 * 打印错误码及异常
	 *
	 * @param responseEnum
	 * @param exception
	 */
	private void printApiCodeException(IResponseEnum responseEnum, Exception exception) {
		log.error(getApiCodeString(responseEnum), exception);
	}

	/**
	 * 消息转换
	 *
	 * @param 	ex
	 * @return  ResultBody
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(JSONException.class)
	public ResultBody handleJSONException(JSONException ex) {
		log.error("", ex);
		return ResultBody.newErrorInstance(ResponseEnum.BAD_REQUEST.getCode(),
				"数据转换错误:" + ex.getMessage());
	}
}