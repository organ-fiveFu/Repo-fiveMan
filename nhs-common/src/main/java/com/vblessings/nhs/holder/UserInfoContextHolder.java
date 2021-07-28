package com.vblessings.nhs.holder;

import com.vblessings.nhs.result.UserInfoToken;

public class UserInfoContextHolder {
    private static final ThreadLocal<UserInfoToken> contextHolder = new InheritableThreadLocal();

    private static final ThreadLocal<String> tokenStrHolder = new InheritableThreadLocal();

    public static void clearContext() {
        contextHolder.remove();
    }

    public static UserInfoToken getCurUserInfo() {
        UserInfoToken userInfoToken = (UserInfoToken) contextHolder.get();
        return userInfoToken;
    }

    public static void setCurUserInfo(UserInfoToken userInfoToken) {
        contextHolder.set(userInfoToken);
    }


    public static void setTokenStr(String tokenStr){
        tokenStrHolder.set(tokenStr);
    }

    public static String getTokenStr(){
        String tokenStr = tokenStrHolder.get();
        return tokenStr;
    }
}
