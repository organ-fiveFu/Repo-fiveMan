package com.vblessings.nhs.model.vo;

import lombok.Data;

@Data
public class ReportData<T> {

    //总页数
    private Integer total;

    //总条数
    private Long count;

    private T data;

    public static <T> ReportData<T> data(T c) {
        ReportData<T> res = new ReportData<>();
        res.setData(c);
        return res;
    }

    public static <T> ReportData<T> data(Integer total, Long count, T c) {
        ReportData<T> res = new ReportData<>();
        res.setTotal(total);
        res.setCount(count);
        res.setData(c);
        return res;
    }
}
