package com.vblessings.nhs.jwt;

public interface IJWTInfo {
    /**
     * 获取员工编号
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    Long getId();

    /**
     * 获取员工姓名
     * @return
     */
    String getName();
}
