package com.vblessings.nhs.model.entity.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_dict_type")
public class SysDictType implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 字典类别编码
     */
    @ApiModelProperty(value = "字典类别编码", required = false)
    @Column(name = "dict_type_code")
    private String dictTypeCode;

    /**
     * 字典类别名称
     */
    @ApiModelProperty(value = "字典类别名称", required = false)
    @Column(name = "dict_type_name")
    private String dictTypeName;

    /**
     * 五笔码
     */
    @ApiModelProperty(value = "五笔码", required = false)
    @Column(name = "wubi_code")
    private String wubiCode;

    /**
     * 拼音码
     */
    @ApiModelProperty(value = "拼音码", required = false)
    @Column(name = "pinyin_code")
    private String pinyinCode;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号", required = false)
    @Column(name = "sort_no")
    private Integer sortNo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    @Column(name = "remark")
    private String remark;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = false)
    @Column(name = "creator_id")
    private Long creatorId;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人", required = false)
    @Column(name = "updater_id")
    private Long updaterId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = false)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = false)
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用", required = false)
    @Column(name = "is_del")
    private Integer isDel;

    /**
     * 查询时添加是否显示停用(0为使用 ，1为停用)
     */
    @ApiModelProperty(value = "查询时添加是否显示停用(0为使用 ，1为停用)", required = false)
    @Column(name = "use_flag")
    private Integer useFlag;

}
