package com.vblessings.nhs.util;

import cn.hutool.core.util.ReflectUtil;
import com.vblessings.nhs.result.UserInfoToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class OperateUtil {
    public static void onSaveNew(Object origin, UserInfoToken userInfoToken, Long id) {
        Date now = new Date();
        //主键id
        ReflectUtil.setFieldValue(origin, "id", id);
        //创建时间
        ReflectUtil.setFieldValue(origin, "createTime", now);
        //创建人ID
        ReflectUtil.setFieldValue(origin, "creatorId", userInfoToken.getUserId());
        //删除标志
        ReflectUtil.setFieldValue(origin, "isDel", 0);
        //修改时间
        ReflectUtil.setFieldValue(origin, "updateTime", now);
        //修改人ID
        ReflectUtil.setFieldValue(origin, "updaterId", userInfoToken.getUserId());
    }
}
