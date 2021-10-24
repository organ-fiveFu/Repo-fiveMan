package com.vblessings.nhs.model.vo.nurse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "三测单查询出参")
public class VitalSignRecordVO {

    private PatientInfo patientInfoList;

    /**
     * 开始时间,按今天倒推7天
     */
    @ApiModelProperty(value = "开始时间,按今天倒推7天")
    private String beginDate;

    /**
     * 住院天数 共七条，没有的那天数据返回''
     */
    @ApiModelProperty(value = "住院天数 共七条，没有的那天数据返回''")
    private List<String> dayList;

    /**
     * 产后/术后天数 共七条，没有的那天数据返回''
     */
    @ApiModelProperty(value = "产后/术后天数 共七条，没有的那天数据返回''")
    private List<String> dayOps;

    /**
     * 呼吸 42条数据 某个时间点空值返回''
     */
    @ApiModelProperty(value = "呼吸 42条数据 某个时间点空值返回''")
    private List<String> breathingList;

    /**
     * 血压 14条，当天空值返回''
     */
    @ApiModelProperty(value = "血压 14条，当天空值返回''")
    private List<String> xyList;

    /**
     * 血压 14条，当天空值返回''
     */
    @ApiModelProperty(value = "血压 14条，当天空值返回''")
    private Map<String, List<String>> dayMap;

    /**
     * 记录信息
     */
    @ApiModelProperty(value = "记录信息")
    private PointTime pointTime;
}

