package com.vblessings.nhs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Copyright (c) 2017 Choice, Inc.
* All Rights Reserved.
* Choice Proprietary and Confidential.
*
* 驼峰下划线转换工具
*
* @author YQ
* @since  15:05 2020/10/20
*/

public class HumpLineUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,最后转为大写
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰,正常输出
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
