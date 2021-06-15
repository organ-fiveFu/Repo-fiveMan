package com.nurse.healthy.Interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.nurse.healthy.annoation.IgnoreUserToken;
import com.nurse.healthy.component.RedisCache;
import com.nurse.healthy.consts.AuthConstants;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.holder.UserInfoContextHolder;
import com.nurse.healthy.result.UserInfoToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private RedisCache redisCache;

    public static void setCurrentUser(HttpServletRequest httpServletRequest, UserInfoToken userInfo) {
        httpServletRequest.setAttribute(AuthConstants.TOKEN_AUTH_USER_INFO, userInfo);
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            IgnoreUserToken ignoreUserToken = method.getAnnotation(IgnoreUserToken.class);
            if (ignoreUserToken != null) {
                return true;
            }
            //无注解需要验证token
            String token_header = httpServletRequest.getHeader(AuthConstants.TOKEN_HEADER);
            if (StrUtil.isAllBlank(token_header)) {
                throw new MyException("未获取到token");
            }
            // 获取请求头里授权参数
            if (StringUtils.isNotEmpty(token_header)) {
                UserInfoToken userInfoToken = redisCache.getCacheObject(token_header);
                    if (userInfoToken != null) {
                        setCurrentUser(httpServletRequest, userInfoToken);
                        UserInfoContextHolder.setCurUserInfo(userInfoToken);
                        return true;
                    }
                else {
                    throw new MyException("token获取信息失败，该用户已登出或过期，请重新登录");
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserInfoContextHolder.clearContext();
    }

}
