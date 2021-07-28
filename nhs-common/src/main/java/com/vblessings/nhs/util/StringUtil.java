package com.vblessings.nhs.util;

public class StringUtil {
    /**
     * @description: 校验字符串是不是全中文
     */
    public static boolean isChinese(String str){
        return str.matches("^[\u4e00-\u9fa5]+$");
    }

    /**
     * @description: 校验字符串是不是全英文
     */
    public static boolean isEnglish(String str) {
        return str.matches("[a-zA-Z]+");
    }


    /**
     * 判断是否是数字
     * @param itemDetailResult
     * @return
     */
    public static boolean isNumeric(String itemDetailResult) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return itemDetailResult.matches(reg);
    }
}
