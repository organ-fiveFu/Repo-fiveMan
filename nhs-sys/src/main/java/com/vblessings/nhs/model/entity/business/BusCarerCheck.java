package com.vblessings.nhs.model.entity.business;


import lombok.Data;
import sun.awt.SunHints;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "bus_carer_check")
public class BusCarerCheck implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 考核人
     */
    @ApiModelProperty(value = "考核人", required = false)
    @Column(name = "inspection_people")
    private String inspectionPeople;

    /**
     * 考核时间
     */
    @ApiModelProperty(value = "考核时间", required = false)
    @Column(name = "inspection_time")
    private Date inspectionTime;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号", required = false)
    @Column(name = "sort_number")
    private Integer sortNumber;

    /**
     * 科室名
     */
    @ApiModelProperty(value = "科室名", required = false)
    @Column(name = "department")
    private String department;

    /**
     * 被考核护工姓名
     */
    @ApiModelProperty(value = "被考核护工姓名", required = false)
    @Column(name = "carer_name")
    private String carerName;

    /**
     * 考核分数
     */
    @ApiModelProperty(value = "考核分数", required = false)
    @Column(name = "score")
    private BigDecimal score;

    /**
     * 备注（扣分原因）
     */
    @ApiModelProperty(value = "备注（扣分原因）", required = false)
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

}

