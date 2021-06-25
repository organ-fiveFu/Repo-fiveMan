package com.nurse.healthy.model.vo;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 分页对象实体
 * Copyright (c) 2018 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author gouhuajiao
 * @since 2020年7月29日
 */
@Data
@Builder(toBuilder=true)
@NoArgsConstructor
public class PageVO<T> {
    /**
     * 页码
     */
    @ApiModelProperty(name = "pageNum",value = "页码")
    private Integer pageNum;
    /**
     * 分页数量
     */
    @ApiModelProperty(name = "pageSize",value = "分页数量")
    private Integer pageSize;
    /**
     * 查询总数
     */
    @ApiModelProperty(name = "total",value = "查询总数")
    private Long total;
    /**
     *总页数
     */
    @ApiModelProperty(name = "pages",value = "总页数")
    private Integer pages;
    /**
     * 结果集
     */
    @ApiModelProperty(name = "list",value = "结果集")
    private List<T> list;

    /**
     * 当前页、每页数、总数、总页数、结果
     * @author YQ
     * @date 16:28 2020/10/22
     * @param pageNum
     * @param pageSize
     * @param total
     * @param pages
     * @param list
     * @return  null
     */
    public PageVO(Integer pageNum, Integer pageSize, Long total, Integer pages, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }


    public PageVO(Page page, List<T> list){
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.list = list;
    }
}
