package com.nurse.healthy.util;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成业务号no类
 */
public class BusinessNoUtil {
    public static String generateBusinessNo()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        Date date = new Date();
        String dateStr = sdf.format(date);
        if(dateStr.indexOf("-")!=-1){
            String[] keyArray = dateStr.split("-");
            StringBuffer sb = new StringBuffer();
            boolean flag = false;
            for(String key:keyArray){
                if(flag){
                    //下划线后的首字母大写
                    sb.append(StringUtils.capitalize(key));
                }else{
                    flag=true;
                    sb.append(key);
                }
            }
            dateStr = sb.toString();
        }
        return dateStr;

    }
}
