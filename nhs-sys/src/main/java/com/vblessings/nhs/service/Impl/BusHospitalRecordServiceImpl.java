package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.mapper.BusTakeMedicineRecordMapper;
import com.vblessings.nhs.mapper.SysBedInfoMapper;
import com.vblessings.nhs.model.entity.bed.SysBedInfo;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.QueryFigurePO;
import com.vblessings.nhs.model.po.QuerySourcePO;
import com.vblessings.nhs.model.po.QuerySummaryPO;
import com.vblessings.nhs.model.po.business.BusHospitalRecordPO;
import com.vblessings.nhs.model.vo.PeopleSourceVO;
import com.vblessings.nhs.model.vo.QuerySummaryVO;
import com.vblessings.nhs.model.vo.TempData;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusHospitalRecordService;
import com.vblessings.nhs.util.BusinessNoUtil;
import com.vblessings.nhs.util.OperateUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Service
public class BusHospitalRecordServiceImpl implements BusHospitalRecordService {
    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysBedInfoMapper sysBedInfoMapper;

    @Resource
    private BusTakeMedicineRecordMapper busTakeMedicineRecordMapper;

    @Override
    public void add(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        //查看是否已经在院了
        Example e= new Example(BusHospitalRecord.class);
        Example.Criteria c = e.createCriteria();
        c.andEqualTo("status",0).andEqualTo("archiveId",busHospitalRecord.getArchiveId()).
                andEqualTo("isDel",0);
        List<BusHospitalRecord> list = busHospitalRecordMapper.selectByExample(e);
        if(list!=null && list.size()>0){
            throw ResponseEnum.DATA_NOT_FOUND.newException("该老人已入院，无法再次入院");
        }
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busHospitalRecord, userInfo, id);
        //入院时间
        busHospitalRecord.setAdmissionTime(new Date());
        //住院号
        busHospitalRecord.setBusinessNo(BusinessNoUtil.generateBusinessNo());
        //设置费用到期状态 默认是0
        busHospitalRecord.setFeesDueStatue(0);
        //更新床位
        Example example = new Example(SysBedInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("buildingCode", busHospitalRecord.getBuildingCode());
        criteria.andEqualTo("floorCode", busHospitalRecord.getFloorCode());
        criteria.andEqualTo("roomCode", busHospitalRecord.getRoomCode());
        if( busHospitalRecord.getBedCode()!=null){
        criteria.andEqualTo("bedCode", busHospitalRecord.getBedCode());}
        criteria.andEqualTo("isDel", 0);
        criteria.andEqualTo("useFlag", "1");
        SysBedInfo sysBedInfo = sysBedInfoMapper.selectOneByExample(example);
        if (StringUtils.isEmpty(sysBedInfo)) {
            throw ResponseEnum.DATA_NOT_FOUND.newException("该床位不存在，无法新增");
        }
        sysBedInfo.setStatus("1"); //已入住
        sysBedInfoMapper.updateByExampleSelective(sysBedInfo, example);
        busHospitalRecordMapper.insert(busHospitalRecord);

    }

    /**
     * 分页查询入院登记档案
     *
     * @param userInfo
     * @return
     */
    @Override
    public PageInfo<BusHospitalRecord> select(BusHospitalRecordPO busHospitalRecordPO, UserInfoToken userInfo) {
        PageHelper.startPage(busHospitalRecordPO.getPageNum(), busHospitalRecordPO.getPageSize());
        Example example = new Example(BusHospitalRecord.class);
        Example.Criteria C = example.createCriteria();
        if (Strings.isNotBlank(busHospitalRecordPO.getName())) {
            C.andLike("name", "%" + busHospitalRecordPO.getName() + "%");
        }
        if (Strings.isNotBlank(busHospitalRecordPO.getBusinessNo())) {
            C.andEqualTo("businessNo", busHospitalRecordPO.getBusinessNo());
        }
        if (Strings.isNotBlank(busHospitalRecordPO.getBuildingCode())) {
            C.andEqualTo("buildingCode", busHospitalRecordPO.getBuildingCode());
        }
        if (Strings.isNotBlank(busHospitalRecordPO.getFloorCode())) {
            C.andEqualTo("floorCode", busHospitalRecordPO.getFloorCode());
        }
        if (Strings.isNotBlank(busHospitalRecordPO.getRoomCode())) {
            C.andEqualTo("roomCode", busHospitalRecordPO.getRoomCode());
        }
        if (Strings.isNotBlank(busHospitalRecordPO.getBedCode())) {
            C.andEqualTo("bedCode", busHospitalRecordPO.getBedCode());
        }
        C.andEqualTo("isDel", 0).andEqualTo("status", busHospitalRecordPO.getStatus());
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(busHospitalRecords);
        return pageInfo;
    }

    @Override
    public void out(String businessNo, String to) {
        Example example2 = new Example(BusHospitalRecord.class);
        example2.createCriteria().andEqualTo("businessNo", businessNo).andEqualTo("isDel", 0);
        BusHospitalRecord busHospitalRecord = busHospitalRecordMapper.selectOneByExample(example2);
        busHospitalRecord.setBusinessNo(businessNo);
        busHospitalRecord.setDischargeTime(new Date());
        busHospitalRecord.setUpdateTime(new Date());
        busHospitalRecord.setPeopleTo(to);
        busHospitalRecord.setStatus("1");

        Example example = new Example(BusHospitalRecord.class);
        example.createCriteria().andEqualTo("businessNo", businessNo);
        //更新床位
        Example example1 = new Example(SysBedInfo.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("buildingCode", busHospitalRecord.getBuildingCode());
        criteria1.andEqualTo("floorCode", busHospitalRecord.getFloorCode());
        criteria1.andEqualTo("roomCode", busHospitalRecord.getRoomCode());
        if( busHospitalRecord.getBedCode()!=null){
            criteria1.andEqualTo("bedCode", busHospitalRecord.getBedCode());}
        criteria1.andEqualTo("isDel", 0);
        criteria1.andEqualTo("useFlag", "1");
        SysBedInfo sysBedInfo = sysBedInfoMapper.selectOneByExample(example1);
        sysBedInfo.setStatus("0");
        sysBedInfoMapper.updateByExampleSelective(sysBedInfo, example1);
        busHospitalRecordMapper.updateByExampleSelective(busHospitalRecord, example);
    }

    @Override
    public void update(BusHospitalRecord busHospitalRecord, UserInfoToken userInfo) {
        //查询更新之前的入院登记
        BusHospitalRecord busHospitalRecord1 = busHospitalRecordMapper.selectByPrimaryKey(busHospitalRecord.getId());
        busHospitalRecord.setUpdaterId(userInfo.getUserId());
        busHospitalRecord.setUpdateTime(new DateTime());
        if(!Objects.equals(busHospitalRecord.getBuildingCode(), busHospitalRecord1.getBuildingCode()) ||
        !Objects.equals(busHospitalRecord.getFloorCode(), busHospitalRecord1.getFloorCode()) || !Objects.equals(busHospitalRecord.getRoomCode(), busHospitalRecord1.getRoomCode())
        || !Objects.equals(busHospitalRecord.getBedCode(), busHospitalRecord1.getBedCode())){
            //更新床位
            Example example = new Example(SysBedInfo.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("buildingCode", busHospitalRecord.getBuildingCode());
            criteria.andEqualTo("floorCode", busHospitalRecord.getFloorCode());
            criteria.andEqualTo("roomCode", busHospitalRecord.getRoomCode());
            if(busHospitalRecord1.getBedCode()!=null){
                criteria.andEqualTo("bedCode", busHospitalRecord1.getBedCode());
            }
            criteria.andEqualTo("isDel", 0);
            criteria.andEqualTo("useFlag", "1");
            SysBedInfo sysBedInfo = sysBedInfoMapper.selectOneByExample(example);
            if (StringUtils.isEmpty(sysBedInfo)) {
                throw ResponseEnum.DATA_NOT_FOUND.newException("该床位不存在，无法更新");
            }
            sysBedInfo.setStatus("1"); //已入住
            sysBedInfoMapper.updateByExampleSelective(sysBedInfo, example);
            //更新之前的床位
            Example example1 = new Example(SysBedInfo.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("buildingCode", busHospitalRecord1.getBuildingCode());
            criteria1.andEqualTo("floorCode", busHospitalRecord1.getFloorCode());
            criteria1.andEqualTo("roomCode", busHospitalRecord1.getRoomCode());
            if(busHospitalRecord1.getBedCode()!=null){
            criteria1.andEqualTo("bedCode", busHospitalRecord1.getBedCode());
            }
            criteria1.andEqualTo("isDel", 0);
            criteria1.andEqualTo("useFlag", "1");
            SysBedInfo sysBedInfo1 = sysBedInfoMapper.selectOneByExample(example1);
            sysBedInfo1.setStatus("0"); //未入住
            sysBedInfoMapper.updateByExampleSelective(sysBedInfo1, example1);
        }
        busHospitalRecordMapper.updateByPrimaryKeySelective(busHospitalRecord);
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Override
    public QuerySummaryVO selectTotalByTime(QuerySummaryPO querySummaryPO) {
        Example example = new Example(BusHospitalRecord.class);
        example.selectProperties("status", "nursingLevel", "dischargeTime", "admissionTime", "bedCode");
        example.createCriteria().andEqualTo("isDel", 0);
        //查询出所有的没有被删除的在院和不在院的信息
        List<BusHospitalRecord> busHospitalRecordList = busHospitalRecordMapper.selectByExample(example);
        Example example1 = new Example(BusTakeMedicineRecord.class);
        example1.selectProperties("businessNo","createTime");
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("isDel",0).andEqualTo("isTaken",0);
        //查询出所有带药信息
        List<BusTakeMedicineRecord> busTakeMedicineRecordList = busTakeMedicineRecordMapper.selectByExample(example1);


        //初始化值
        QuerySummaryVO querySummaryVO = new QuerySummaryVO();
        querySummaryVO.setStayHospitalNum(0);
        querySummaryVO.setOriginalNum(0);
        querySummaryVO.setProvideForOneself(0);
        querySummaryVO.setPartialDisability(0);
        querySummaryVO.setDisabilityNum(0);
        querySummaryVO.setTakeUpBed(0);
        querySummaryVO.setOutHospitalNum(0);
        querySummaryVO.setInHospitalNum(0);
        querySummaryVO.setTakeMedicalNum(0);
        if (busHospitalRecordList != null && busHospitalRecordList.size() > 0) {
            List<BusHospitalRecord> OriginalList = new ArrayList<>();
            List<BusHospitalRecord> OutHospitalList = busHospitalRecordList.stream().filter(e -> e.getDischargeTime() != null).collect(Collectors.toList());
            List<BusHospitalRecord> INHospitalList = new ArrayList<>();
            //时间条件过滤一遍,开始时间和结束时间都有
            if (querySummaryPO.getStartTime() != null && querySummaryPO.getEndTime() != null) {
                //原有病人list（比开始时间小，且在院的人）
                OriginalList = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getStartTime()) < 0).
                        filter(e -> e.getStatus().equals("0")).collect(Collectors.toList());
                //入院病人list
                INHospitalList = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getStartTime()) >= 0).collect(Collectors.toList())
                        .stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getEndTime()) <= 0).collect(Collectors.toList());
                ;

                //出院病人list
                OutHospitalList = OutHospitalList.stream().filter(e -> e.getDischargeTime().compareTo(querySummaryPO.getStartTime()) >= 0).collect(Collectors.toList())
                        .stream().filter(e -> e.getDischargeTime().compareTo(querySummaryPO.getEndTime()) <= 0).collect(Collectors.toList());
            }

            //开始时间为空，有结束时间
            if (querySummaryPO.getStartTime() == null && querySummaryPO.getEndTime() != null) {
                //那么原有人数则为0
                INHospitalList = INHospitalList.stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getEndTime()) <= 0).collect(Collectors.toList());

                OutHospitalList = OutHospitalList.stream().filter(e -> e.getDischargeTime().compareTo(querySummaryPO.getEndTime()) <= 0).collect(Collectors.toList());

            }
            //有开始时间
            if (querySummaryPO.getStartTime() != null && querySummaryPO.getEndTime() == null) {

                OriginalList = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getStartTime()) < 0).
                        filter(e -> e.getStatus().equals("0")).collect(Collectors.toList());

                INHospitalList = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySummaryPO.getStartTime()) >= 0).collect(Collectors.toList());

                OutHospitalList = OutHospitalList.stream().filter(e -> e.getDischargeTime().compareTo(querySummaryPO.getStartTime()) >= 0).collect(Collectors.toList());
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
            querySummaryVO.setStayHospitalNum(OriginalList.size() + InHospitalNum - OutHospitalNum);

            List<BusHospitalRecord> withoutTime = busHospitalRecordList.stream().filter(e -> e.getStatus().equals("0")).collect(Collectors.toList());
            //实际占用床位数
            querySummaryVO.setTakeUpBed(withoutTime.stream().filter(e -> e.getBedCode() != null).collect(Collectors.toList()).size());
            //完全失能老人数
            querySummaryVO.setDisabilityNum(withoutTime.stream().filter(e -> e.getNursingLevel().equals("0001")).collect(Collectors.toList()).size());
            //部分失能老人数
            querySummaryVO.setPartialDisability(withoutTime.stream().filter(e -> e.getNursingLevel().equals("0002")).collect(Collectors.toList()).size());
            //自理老人数
            querySummaryVO.setProvideForOneself(withoutTime.stream().filter(e -> e.getNursingLevel().equals("0003")).collect(Collectors.toList()).size());

        }

        if(busTakeMedicineRecordList!=null && busTakeMedicineRecordList.size()>0){
            //结果集去重
            busTakeMedicineRecordList= busTakeMedicineRecordList.stream().filter(distinctByKey(BusTakeMedicineRecord::getBusinessNo)).collect(Collectors.toList());
            //时间条件过滤一遍
            if (querySummaryPO.getStartTime() != null && querySummaryPO.getEndTime() != null) {
                List<BusTakeMedicineRecord> busTakeMedicineRecordListCopy = busTakeMedicineRecordList.stream().filter((e -> e.getCreateTime().compareTo(querySummaryPO.getStartTime()) >= 0)).
                        filter((e -> e.getCreateTime().compareTo(querySummaryPO.getEndTime())<0)).collect(Collectors.toList());
                querySummaryVO.setTakeMedicalNum(busTakeMedicineRecordListCopy.size());
            }
            if (querySummaryPO.getStartTime() == null && querySummaryPO.getEndTime() != null) {
                List<BusTakeMedicineRecord> busTakeMedicineRecordListCopy =  busTakeMedicineRecordList.stream().filter(e->e.getCreateTime().compareTo(querySummaryPO.getEndTime())<0).collect(Collectors.toList());
                querySummaryVO.setTakeMedicalNum(busTakeMedicineRecordListCopy.size());
            }
            if (querySummaryPO.getStartTime() != null && querySummaryPO.getEndTime() == null) {
                List<BusTakeMedicineRecord> busTakeMedicineRecordListCopy =  busTakeMedicineRecordList.stream().filter(e->e.getCreateTime().compareTo(querySummaryPO.getStartTime())>0).collect(Collectors.toList());
                querySummaryVO.setTakeMedicalNum(busTakeMedicineRecordListCopy.size());
            }
            return querySummaryVO;
        }
        return querySummaryVO;
    }

    @Override
    public Map<String, List<TempData>> queryBrokenLine(QueryFigurePO queryFigurePO) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(queryFigurePO.getEndTime());//设置起时间
        cal.add(Calendar.MONTH, 1);//增加一个月

        String s1 = new SimpleDateFormat("yyyy-MM").format(queryFigurePO.getStartTime());
        String s2 = new SimpleDateFormat("yyyy-MM").format(cal.getTime());


        Date d1 = new SimpleDateFormat("yyyy-MM").parse(s1);//定义起始日期
        Date d2 = new SimpleDateFormat("yyyy-MM").parse(s2);//定义结束日期

        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        List<String> timeList = new LinkedList<>();
        while (dd.getTime().before(d2)) {//判断是否到结束日期

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            String str = sdf.format(dd.getTime());

            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            timeList.add(str);
        }


        //按月分组
        Map map = new HashMap();
        if (queryFigurePO.getTimeType() != null && queryFigurePO.getTimeType().equals("1")) {
            //每月入院list
            List<TempData> tempDataList = busHospitalRecordMapper.queryBrokenLineByMonth(queryFigurePO);
            List<String> tempTime = tempDataList.stream().map(TempData::getTempData).collect(Collectors.toList());
            for (String s :
                    timeList) {
                if (!tempTime.contains(s)) {
                    TempData tempData = new TempData();
                    tempData.setTargetNum(0);
                    tempData.setTempData(s);
                    tempDataList.add(tempData);
                }
            }
            tempDataList = tempDataList.stream().sorted(Comparator.comparing(TempData::getTempData)).collect(Collectors.toList());
            List<TempData> tempDataList1 = busHospitalRecordMapper.queryBrokenLineByMonth1(queryFigurePO);
            List<String> tempTime1 = tempDataList1.stream().map(TempData::getTempData).collect(Collectors.toList());
            for (String s :
                    timeList) {
                if (!tempTime1.contains(s)) {
                    TempData tempData = new TempData();
                    tempData.setTargetNum(0);
                    tempData.setTempData(s);
                    tempDataList1.add(tempData);
                }
            }
            tempDataList1 = tempDataList1.stream().sorted(Comparator.comparing(TempData::getTempData)).collect(Collectors.toList());

            map.put("inHospital", tempDataList);
            map.put("outHospital", tempDataList1);
            return map;
        }
        if (queryFigurePO.getTimeType() != null && queryFigurePO.getTimeType().equals("2")) {
            List<TempData> tempDataList = busHospitalRecordMapper.queryBrokenLineByYear(queryFigurePO);

            List<TempData> tempDataList1 = busHospitalRecordMapper.queryBrokenLineByYear1(queryFigurePO);
            map.put("inHospital", tempDataList);
            map.put("outHospital", tempDataList1);
            return map;
        }
        return null;
    }


    @Override
    public Map<String, List<TempData>> queryCake(QueryFigurePO queryFigurePO) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.setTime(queryFigurePO.getEndTime());//设置起时间
        cal.add(Calendar.MONTH, 1);//增加一个月

        String s1 = new SimpleDateFormat("yyyy-MM").format(queryFigurePO.getStartTime());
        String s2 = new SimpleDateFormat("yyyy-MM").format(cal.getTime());


        Date d1 = new SimpleDateFormat("yyyy-MM").parse(s1);//定义起始日期
        Date d2 = new SimpleDateFormat("yyyy-MM").parse(s2);//定义结束日期

        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        List<String> timeList = new LinkedList<>();
        while (dd.getTime().before(d2)) {//判断是否到结束日期

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            String str = sdf.format(dd.getTime());

            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
            timeList.add(str);
        }

        //按月分组
        Map map = new HashMap();
        QuerySummaryVO querySummaryVO = new QuerySummaryVO();
        if (queryFigurePO.getTimeType() != null && queryFigurePO.getTimeType().equals("1")) {
            //每月入院完全失能老人
            List<TempData> tempDataList = busHospitalRecordMapper.queryCakeByMonth(queryFigurePO);
            List<String> tempTime = tempDataList.stream().map(TempData::getTempData).collect(Collectors.toList());
            for (String s :
                    timeList) {
                if (!tempTime.contains(s)) {
                    TempData tempData = new TempData();
                    tempData.setTargetNum(0);
                    tempData.setTempData(s);
                    tempDataList.add(tempData);
                }
            }

            //每月入院部分失能老人
            List<TempData> tempDataList1 = busHospitalRecordMapper.queryCakeByMonth1(queryFigurePO);
            List<String> tempTime1 = tempDataList1.stream().map(TempData::getTempData).collect(Collectors.toList());
            for (String s :
                    timeList) {
                if (!tempTime1.contains(s)) {
                    TempData tempData = new TempData();
                    tempData.setTargetNum(0);
                    tempData.setTempData(s);
                    tempDataList1.add(tempData);
                }
            }


            //每月入院自理老人
            List<TempData> tempDataList2 = busHospitalRecordMapper.queryCakeByMonth2(queryFigurePO);
            List<String> tempTime2 = tempDataList2.stream().map(TempData::getTempData).collect(Collectors.toList());
            for (String s :
                    timeList) {
                if (!tempTime2.contains(s)) {
                    TempData tempData = new TempData();
                    tempData.setTargetNum(0);
                    tempData.setTempData(s);
                    tempDataList2.add(tempData);
                }
            }
            tempDataList = tempDataList.stream().sorted(Comparator.comparing(TempData::getTempData)).collect(Collectors.toList());
            tempDataList1 = tempDataList1.stream().sorted(Comparator.comparing(TempData::getTempData)).collect(Collectors.toList());
            tempDataList2 = tempDataList2.stream().sorted(Comparator.comparing(TempData::getTempData)).collect(Collectors.toList());


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

    @Override
    public PeopleSourceVO querySource(QuerySourcePO querySourcePO) {
        Example example = new Example(BusHospitalRecord.class);
        example.selectProperties("status", "nursingLevel", "dischargeTime", "admissionTime", "peopleFrom", "peopleTo");
        example.createCriteria().andEqualTo("isDel", 0);
        //所有记录list
        List<BusHospitalRecord> busHospitalRecordList = busHospitalRecordMapper.selectByExample(example);
        PeopleSourceVO peopleSourceVO = new PeopleSourceVO();
        peopleSourceVO.setToHsp(0);
        peopleSourceVO.setToSociety(0);
        peopleSourceVO.setFromHsp(0);
        peopleSourceVO.setFromSociety(0);
        if (busHospitalRecordList != null && busHospitalRecordList.size() > 0) {
            //入院列表
            List<BusHospitalRecord> inHsp = new ArrayList<>();
            //出院列表
            List<BusHospitalRecord> outHsp = busHospitalRecordList.stream().filter(e -> e.getDischargeTime() != null).collect(Collectors.toList());
            if (querySourcePO.getStartTime() != null && querySourcePO.getEndTime() != null) {
                inHsp = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySourcePO.getStartTime()) >= 0).collect(Collectors.toList())
                        .stream().filter(e -> e.getAdmissionTime().compareTo(querySourcePO.getEndTime()) <= 0).collect(Collectors.toList());

                outHsp = outHsp.stream().filter(e -> e.getDischargeTime().compareTo(querySourcePO.getStartTime()) >= 0).collect(Collectors.toList())
                        .stream().filter(e -> e.getDischargeTime().compareTo(querySourcePO.getEndTime()) <= 0).collect(Collectors.toList());
            }

            if (querySourcePO.getStartTime() == null && querySourcePO.getEndTime() != null) {
                inHsp = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySourcePO.getEndTime()) <= 0).collect(Collectors.toList());

                outHsp = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySourcePO.getEndTime()) <= 0).collect(Collectors.toList());

            }

            if (querySourcePO.getStartTime() != null && querySourcePO.getEndTime() == null) {
                inHsp = busHospitalRecordList.stream().filter(e -> e.getAdmissionTime().compareTo(querySourcePO.getStartTime()) >= 0).collect(Collectors.toList());

                outHsp = outHsp.stream().filter(e -> e.getDischargeTime().compareTo(querySourcePO.getStartTime()) >= 0).collect(Collectors.toList());

            }
            if (querySourcePO.getStartTime() == null && querySourcePO.getEndTime() == null) {
                inHsp = busHospitalRecordList;
            }
            peopleSourceVO.setFromHsp(inHsp.stream().filter(e -> e.getPeopleFrom().equals("2")).collect(Collectors.toList()).size());
            peopleSourceVO.setFromSociety(inHsp.stream().filter(e -> e.getPeopleFrom().equals("1")).collect(Collectors.toList()).size());
            peopleSourceVO.setToSociety(outHsp.stream().filter(e -> e.getPeopleTo().equals("1")).collect(Collectors.toList()).size());
            peopleSourceVO.setToHsp(outHsp.stream().filter(e -> e.getPeopleTo().equals("2")).collect(Collectors.toList()).size());
            return peopleSourceVO;

        }
        return peopleSourceVO;
    }
}