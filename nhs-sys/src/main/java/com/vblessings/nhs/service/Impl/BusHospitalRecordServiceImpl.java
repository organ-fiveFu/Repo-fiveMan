package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.po.QuerySummaryPO;
import com.vblessings.nhs.model.po.business.BusHospitalRecordPO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusHospitalRecordService;
import com.vblessings.nhs.util.BusinessNoUtil;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BusHospitalRecordServiceImpl implements BusHospitalRecordService {
    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;



    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Override
    public void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busHospitalRecord,userInfo,id);
        //入院时间
        busHospitalRecord.setAdmissionTime(new Date());
        //住院号
        busHospitalRecord.setBusinessNo(BusinessNoUtil.generateBusinessNo());
        //设置费用到期状态 默认是0
        busHospitalRecord.setFeesDueStatue(0);
        busHospitalRecordMapper.insert(busHospitalRecord);
    }

    /**
     * 分页查询入院登记档案
     * @param userInfo
     * @return
     */
    @Override
    public PageInfo<BusHospitalRecord> select(BusHospitalRecordPO busHospitalRecordPO, UserInfoToken userInfo) {
        PageHelper.startPage(busHospitalRecordPO.getPageNum(), busHospitalRecordPO.getPageSize());
        Example example = new Example(BusHospitalRecord.class);
        Example.Criteria C = example.createCriteria();
        if(Strings.isNotBlank(busHospitalRecordPO.getName())){
           C.andLike("name","%"+busHospitalRecordPO.getName()+"%");
        }
        if(Strings.isNotBlank(busHospitalRecordPO.getBusinessNo())){
            C.andEqualTo("businessNo",busHospitalRecordPO.getBusinessNo());
        }
        C.andEqualTo("isDel",0).andEqualTo("status",busHospitalRecordPO.getStatue());
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(busHospitalRecords);
        return pageInfo;
    }

    @Override
    public void out(String businessNo) {
        BusHospitalRecord busHospitalRecord = new BusHospitalRecord();
        busHospitalRecord.setBusinessNo(businessNo);
        busHospitalRecord.setDischargeTime(new Date());
        busHospitalRecord.setStatus("1");

        Example example = new Example(BusHospitalRecord.class);
        example.createCriteria().andEqualTo("businessNo",businessNo);
        busHospitalRecordMapper.updateByExampleSelective(busHospitalRecord,example);
    }

    @Override
    public void update(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        busHospitalRecord.setUpdaterId(userInfo.getUserId());
        busHospitalRecord.setUpdateTime(new DateTime());
        busHospitalRecordMapper.updateByPrimaryKeySelective(busHospitalRecord);
    }

    @Override
    public QuerySummaryVO selectTotalByTime(QuerySummaryPO querySummaryPO) {
        Example example = new Example(BusHospitalRecord.class);
        example.selectProperties("status","nursingLevel","bedCode","createTime");
        example.createCriteria().andEqualTo("isDel",0);
        List<BusHospitalRecord> busHospitalRecordList = busHospitalRecordMapper.selectByExample(example);
        QuerySummaryVO querySummaryVO = new QuerySummaryVO();
        if(busHospitalRecordList !=null && busHospitalRecordList.size()>0){

        //时间条件过滤一遍
        if(querySummaryPO.getStartTime()!=null){
            busHospitalRecordList = busHospitalRecordList.stream().filter(e->e.getCreateTime().compareTo(querySummaryPO.getStartTime())>=0).collect(Collectors.toList());
        }
        if(querySummaryPO.getEndTime()!=null){
            busHospitalRecordList = busHospitalRecordList.stream().filter(e->e.getCreateTime().compareTo(querySummaryPO.getEndTime())<=0).collect(Collectors.toList());

        }
        //原有人数
        querySummaryVO.setOriginalNum(busHospitalRecordList.size());
        //入院人数
        querySummaryVO.setInHospitalNum(busHospitalRecordList.stream().filter(e->e.getStatus().equals("0")).collect(Collectors.toList()).size());
        //出院人数
        querySummaryVO.setOutHospitalNum(busHospitalRecordList.stream().filter(e->e.getStatus().equals("1")).collect(Collectors.toList()).size());
        //在院人数列表
        busHospitalRecordList = busHospitalRecordList.stream().filter(e->e.getStatus().equals("0")).collect(Collectors.toList());
        //实际占用床位数
        querySummaryVO.setTakeUpBed(busHospitalRecordList.stream().filter(e->e.getBedCode()!=null).collect(Collectors.toList()).size());
        //完全失能老人数
        querySummaryVO.setDisabilityNum(busHospitalRecordList.stream().filter(e->e.getNursingLevel().equals("1")).collect(Collectors.toList()).size());
        //部分失能老人数
        querySummaryVO.setPartialDisability(busHospitalRecordList.stream().filter(e->e.getNursingLevel().equals("2")).collect(Collectors.toList()).size());
        //自理老人数
        querySummaryVO.setProvideForOneself(busHospitalRecordList.stream().filter(e->e.getNursingLevel().equals("3")).collect(Collectors.toList()).size());
        return querySummaryVO;}
        return querySummaryVO;
    }

    @Override
    public Map<String,QuerySummaryVO> queryBrokenLine(QueryFigurePO queryFigurePO) {
        //按月分组
        if(queryFigurePO.getTimeType()!=null && queryFigurePO.getTimeType().equals("1")){
            return busHospitalRecordMapper.queryBrokenLineByMonth(queryFigurePO);
        }
        if(queryFigurePO.getTimeType()!=null && queryFigurePO.getTimeType().equals("2")){
            return null;
          /*  return busHospitalRecordMapper.queryBrokenLineByYear(queryFigurePO);*/
        }
          return null;
    }

    @Override
    public Map<String, QuerySummaryVO> queryColumnar(QueryFigurePO queryFigurePO) {
        return null;
    }

    @Override
    public Map<String, QuerySummaryVO> queryCake(QueryFigurePO queryFigurePO) {
        return null;
    }
}
