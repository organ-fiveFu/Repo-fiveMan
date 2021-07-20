package com.nurse.healthy.model.entity.business;

import lombok.Data;
import sun.awt.SunHints;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "bus_donation_record")
public class BusDonationRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ApiModelProperty(value = "", required = false)
    @Id
    private Long id;

    /**
     * 捐款日期
     */
    @ApiModelProperty(value = "捐款日期", required = false)
    @Column(name = "donation_date")
    private Date donationDate;

    /**
     * 捐款人（单位）
     */
    @ApiModelProperty(value = "捐款人（单位）", required = false)
    @Column(name = "donor")
    private String donor;

    /**
     * 捐赠人意愿
     */
    @ApiModelProperty(value = "捐赠人意愿", required = false)
    @Column(name = "donor_willingness")
    private String donorWillingness;

    /**
     * 捐款金额
     */
    @ApiModelProperty(value = "捐款金额", required = false)
    @Column(name = "contributions")
    private BigDecimal contributions;

    /**
     * 捐赠物品
     */
    @ApiModelProperty(value = "捐赠物品", required = false)
    @Column(name = "donation_item")
    private String donationItem;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量", required = false)
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 估价
     */
    @ApiModelProperty(value = "估价", required = false)
    @Column(name = "valuation")
    private BigDecimal valuation;

    /**
     * 捐赠物处置
     */
    @ApiModelProperty(value = "捐赠物处置", required = false)
    @Column(name = "donation_items_disposal")
    private String donationItemsDisposal;

    /**
     * 责任人
     */
    @ApiModelProperty(value = "责任人", required = false)
    @Column(name = "person_in_charge")
    private String personInCharge;

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

