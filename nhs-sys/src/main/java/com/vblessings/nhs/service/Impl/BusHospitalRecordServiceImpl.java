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
import com.vblessings.nhs.model.vo.TempData;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusHospitalRecordService;
import com.vblessings.nhs.util.BusinessNoUtil;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
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
        example.selectProperties("status","nursingLevel","dischargeTime","admissionTime","bedCode");
        example.createCriteria().andEqualTo("isDel",0);
        //所有记录list
        List<BusHospitalRecord> busHospitalRecordList = busHospitalRecordMapper.selectByExample(example);
        QuerySummaryVO querySummaryVO = new QuerySummaryVO();
        if(busHospitalRecordList !=null && busHospitalRecordList.size()>0){

            List<BusHospitalRecord> OriginalList =  new ArrayList<>();
            List<BusHospitalRecord> OutHospitalList =busHospitalRecordList.stream().filter(e->e.getDischargeTime()!=null).collect(Collectors.toList());
            List<BusHospitalRecord> INHospitalList =  new ArrayList<>();
            //时间条件过滤一遍
        if(querySummaryPO.getStartTime()!=null){
            //原有病人list
            OriginalList = busHospitalRecordList.stream().filter(e->e.getAdmissionTime().compareTo(querySummaryPO.getStartTime())<0).
                    filter(e->e.getStatus().equals("0")).collect(Collectors.toList());
            //入院病人list
            INHospitalList = busHospitalRecordList.stream().filter(e->e.getAdmissionTime().compareTo(querySummaryPO.getStartTime())>=0).collect(Collectors.toList());

            //出院病人list
            OutHospitalList = OutHospitalList.stream().filter(e->e.getDischargeTime().compareTo(querySummaryPO.getStartTime())>=0).collect(Collectors.toList());
        }
        if(querySummaryPO.getEndTime()!=null){
            INHospitalList = busHospitalRecordList.stream().filter(e->e.getAdmissionTime().compareTo(querySummaryPO.getEndTime())<=0).collect(Collectors.toList());

            OutHospitalList = OutHospitalList.stream().filter(e->e.getDischargeTime().compareTo(querySummaryPO.getEndTime())<=0).collect(Collectors.toList());

        }


        //原有人数
        querySummaryVO.setOriginalNum(OriginalList.size());
        //入院人数
            int InHospitalNum = INHospitalList.size();
        querySummaryVO.setInHospitalNum(InHospitalNum);
        //出院人数
            int OutHospitalNum = OutHospitalList.size();
        querySummaryVO.setOutHospitalNum(OutHospitalNum);
        //留院人数
            querySummaryVO.setStayHospitalNum(OriginalList.size()+InHospitalNum-OutHospitalNum);

            List<BusHospitalRecord> withoutTime =  busHospitalRecordList.stream().filter(e->e.getStatus().equals("0")).collect(Collectors.toList());
        //实际占用床位数
        querySummaryVO.setTakeUpBed(withoutTime.stream().filter(e->e.getBedCode()!=null).collect(Collectors.toList()).size());
        //完全失能老人数
        querySummaryVO.setDisabilityNum(withoutTime.stream().filter(e->e.getNursingLevel().equals("0001")).collect(Collectors.toList()).size());
        //部分失能老人数
        querySummaryVO.setPartialDisability(withoutTime.stream().filter(e->e.getNursingLevel().equals("0002")).collect(Collectors.toList()).size());
        //自理老人数
        querySummaryVO.setProvideForOneself(withoutTime.stream().filter(e->e.getNursingLevel().equals("0003")).collect(Collectors.toList()).size());
        return querySummaryVO;
        }
        return querySummaryVO;
    }

    @Override
    public Map<String, List<TempData>> queryBrokenLine(QueryFigurePO queryFigurePO) {
        //按月分组
        Map map = new HashMap();
        if(queryFigurePO.getTimeType()!=null && queryFigurePO.getTimeType().equals("1")){
            //每月入院list
        List<TempData> tempDataList =  busHospitalRecordMapper.queryBrokenLineByMonth(queryFigurePO);

        List<TempData> tempDataList1 = busHospitalRecordMapper.queryBrokenLineByMonth1(queryFigurePO);
            map.put("inHospital",tempDataList);
            map.put("outHospital",tempDataList1);
           return map;
        }
        if(queryFigurePO.getTimeType()!=null && queryFigurePO.getTimeType().equals("2")){
            List<TempData> tempDataList =  busHospitalRecordMapper.queryBrokenLineByYear(queryFigurePO);

            List<TempData> tempDataList1 = busHospitalRecordMapper.queryBrokenLineByYear1(queryFigurePO);
            map.put("inHospital",tempDataList);
            map.put("outHospital",tempDataList1);
            return map;
        }
          return null;
    }



    @Override
    public Map<String, List<TempData>> queryCake(QueryFigurePO queryFigurePO) {
        //按月分组
        Map map = new HashMap();
        QuerySummaryVO querySummaryVO = new QuerySummaryVO();
        if (queryFigurePO.getTimeType() != null && queryFigurePO.getTimeType().equals("1")) {
            //每月入院完全失能老人
            List<TempData> tempDataList = busHospitalRecordMapper.queryCakeByMonth(queryFigurePO);

            //每月入院部分失能老人
            List<TempData> tempDataList1 = busHospitalRecordMapper.queryCakeByMonth1(queryFigurePO);


            //每月入院自理老人
            List<TempData> tempDataList2 = busHospitalRecordMapper.queryCakeByMonth2(queryFigurePO);


            map.put("disability", tempDataList);
            map.put("partialDisability", tempDataList1);
            map.put("provideForOneself", tempDataList2);
            return map;
        }
        if (queryFigurePO.getTimeType() != null && queryFigurePO.getTimeType().equals("2")) {

            //每年入院完全失能老人
            List<TempData> tempDataList = busHospitalRecordMapper.queryCakeByYear(queryFigurePO);

            //每年入院部分失能老人
            List<TempData> tempDataList1 = busHospitalRecordMapper.queryCakeByYear1(queryFigurePO);

            //每年入院自理老人
            List<TempData> tempDataList2 = busHospitalRecordMapper.queryCakeByYear2(queryFigurePO);
            map.put("disability", tempDataList);
            map.put("partialDisability", tempDataList1);
            map.put("provideForOneself", tempDataList2);
            return map;
        }
        return null;
    }
}
