package com.vblessings.nhs.Interceptor;

import cn.hutool.core.util.StrUtil;
import com.vblessings.nhs.annoation.IgnoreUserToken;
import com.vblessings.nhs.component.RedisCache;
import com.vblessings.nhs.consts.AuthConstants;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.holder.UserInfoContextHolder;
import com.vblessings.nhs.result.UserInfoToken;
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
                throw ResponseEnum.TOKEN_NOT_FOUND.newException("未获取到token");
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
                    throw ResponseEnum.TOKEN_FAILE_GET_INFO.newException();
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
