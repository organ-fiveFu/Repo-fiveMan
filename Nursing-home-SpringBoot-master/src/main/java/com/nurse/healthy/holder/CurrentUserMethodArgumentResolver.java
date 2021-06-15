package com.nurse.healthy.holder;

import com.nurse.healthy.annoation.CurrentUser;
import com.nurse.healthy.consts.AuthConstants;
import com.nurse.healthy.exception.MyException;
import com.nurse.healthy.result.UserInfoToken;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.getParameterType().equals(UserInfoToken.class) && methodParameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        UserInfoToken userInfoToken = (UserInfoToken)request.getAttribute(AuthConstants.TOKEN_AUTH_USER_INFO);
        if (userInfoToken == null){
           throw new MyException("token获取信息失败，该用户已登出或过期，请重新登录");
        }
        return userInfoToken;
    }
}
