package com.vblessings.nhs.util;

import java.math.BigDecimal;

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

    /**
     * int转字符，判断是否为空，为空返回''
     * @param integer   目标数字
     * @return      str
     */
    public static String intToString(Integer integer) {
        return integer != null ? String.valueOf(integer) : "";
    }

    /**
     * bigDecimal转字符，判断是否为空，为空返回''
     * @param bigDecimal   目标数字
     * @return      str
     */
    public static String bigDecimalToString(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.toPlainString() : "";
    }
}
