package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.enums.SexEnum;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.mapper.BusVitalSignRecordMapper;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.entity.business.BusVitalSignRecord;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordQueryVO;
import com.vblessings.nhs.model.vo.nurse.PatientInfo;
import com.vblessings.nhs.model.vo.nurse.PointTime;
import com.vblessings.nhs.model.vo.nurse.VitalSignRecord;
import com.vblessings.nhs.model.vo.nurse.VitalSignRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusVitalSignRecordService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.util.StringUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusVitalSignRecordServiceImpl implements BusVitalSignRecordService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusVitalSignRecordMapper busVitalSignRecordMapper;

    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;

    @Override
    public void addVitalSignRecord(BusVitalSignRecordPO busVitalSignRecordPO, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        BusVitalSignRecord busVitalSignRecord = new BusVitalSignRecord();
        BeanUtil.copyProperties(busVitalSignRecordPO, busVitalSignRecord);
        try {
            busVitalSignRecord.setRecordTime(sdf.parse(busVitalSignRecordPO.getRecordTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        busVitalSignRecord.setCreatorId(null);
        busVitalSignRecord.setCreateTime(null);
        OperateUtil.onSaveNew(busVitalSignRecord, userInfo, id);
        try {
            busVitalSignRecordMapper.insert(busVitalSignRecord);
        } catch (DuplicateKeyException e) {
            throw ResponseEnum.DATA_ALREADY_EXISTS.newException("数据已存在");
        }
    }

    @Override
    public void batchUpdateVitalSignRecord(List<BusVitalSignRecordPO> busVitalSignRecordPOS, UserInfoToken userInfo) {
        Date now = new Date();
        busVitalSignRecordPOS.forEach(busVitalSignRecordPO -> {
            if (busVitalSignRecordPO.getId() != null) {
                BusVitalSignRecord busVitalSignRecord = new BusVitalSignRecord();
                BeanUtil.copyProperties(busVitalSignRecordPO, busVitalSignRecord);
                try {
                    busVitalSignRecord.setRecordTime(sdf.parse(busVitalSignRecordPO.getRecordTime()));
                } catch (ParseException e) {
                    throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
                }
                busVitalSignRecord.setUpdaterId(userInfo.getUserId());
                busVitalSignRecord.setUpdateTime(now);
                busVitalSignRecordMapper.updateByPrimaryKey(busVitalSignRecord);
            } else {
                addVitalSignRecord(busVitalSignRecordPO, userInfo);
            }
        });

    }

//    @Override
//    public List<BusVitalSignRecordPO> queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO) {
//        Example example = new Example(BusVitalSignRecord.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
//        criteria.andEqualTo("isDel", 0);
//        if (Strings.isNotBlank(queryVitalSignPO.getStartTime()) && Strings.isNotBlank(queryVitalSignPO.getEndTime())) {
//            try {
//                Date start = sdf.parse(queryVitalSignPO.getStartTime());
//                Date end = sdf.parse(queryVitalSignPO.getEndTime());
//                criteria.andBetween("recordTime", start, end);
//            } catch (ParseException e) {
//                throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
//            }
//        } else {
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTime(new Date());
//            Date end = calendar.getTime();
//            calendar.add(Calendar.DAY_OF_MONTH, -6);
//            calendar.set(Calendar.HOUR_OF_DAY, 0);
//            calendar.set(Calendar.MINUTE, 0);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            Date start = calendar.getTime();
//            criteria.andBetween("recordTime", start, end);
//        }
//        List<BusVitalSignRecord> busVitalSignRecords = busVitalSignRecordMapper.selectByExample(example);
//        List<BusVitalSignRecordPO> busVitalSignRecordPOS = new ArrayList<>();
//        busVitalSignRecords.forEach(busVitalSignRecord -> {
//            BusVitalSignRecordPO busVitalSignRecordPO = new BusVitalSignRecordPO();
//            BeanUtil.copyProperties(busVitalSignRecord, busVitalSignRecordPO);
//            busVitalSignRecordPO.setRecordTime(sdf.format(busVitalSignRecord.getRecordTime()));
//            busVitalSignRecordPOS.add(busVitalSignRecordPO);
//        });
//        return busVitalSignRecordPOS;
//    }

    @Override
    public List<BusVitalSignRecordQueryVO> batchQueryVitalSignRecord(QueryBatchVitalSignPO queryBatchVitalSignPO) {
        return busVitalSignRecordMapper.batchQueryVitalSignRecord(queryBatchVitalSignPO.getRecordTime(), queryBatchVitalSignPO.getTimePoint());
    }

    @Override
    public void delVitalSignRecord(String ids) {
        String[] id = ids.split(",");
        busVitalSignRecordMapper.batchDel(id);
    }

    @Override
    public List<BusVitalSignRecordPO> queryPatientVitalSignRecord(QueryVitalSignPagePO queryVitalSignPagePO) {
        List<BusVitalSignRecordPO> busVitalSignRecordPOS = new ArrayList<>();
        if (queryVitalSignPagePO.getId() != null) {
            BusVitalSignRecord busVitalSignRecord = busVitalSignRecordMapper.selectByPrimaryKey(queryVitalSignPagePO.getId());
            BusVitalSignRecordPO busVitalSignRecordPO = new BusVitalSignRecordPO();
            BeanUtil.copyProperties(busVitalSignRecord, busVitalSignRecordPO);
            busVitalSignRecordPO.setRecordTime(sdf.format(busVitalSignRecord.getRecordTime()));
            busVitalSignRecordPOS.add(busVitalSignRecordPO);
        } else {
            Example example = new Example(BusVitalSignRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel", 0);
            if (Strings.isNotBlank(queryVitalSignPagePO.getName())) {
                criteria.andLike("name", "%" + queryVitalSignPagePO.getName() + "%");
            }
            if (Strings.isNotBlank(queryVitalSignPagePO.getBusinessNo())) {
                criteria.andEqualTo("businessNo", queryVitalSignPagePO.getBusinessNo());
            }
            if (Strings.isNotBlank(queryVitalSignPagePO.getStartTime()) && Strings.isNotBlank(queryVitalSignPagePO.getEndTime())) {
                try {
                    Date start = sdf.parse(queryVitalSignPagePO.getStartTime());
                    Date end = sdf.parse(queryVitalSignPagePO.getEndTime());
                    criteria.andBetween("recordTime", start, end);
                } catch (ParseException e) {
                    throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
                }
            }
            List<BusVitalSignRecord> busVitalSignRecords = busVitalSignRecordMapper.selectByExample(example);
            busVitalSignRecords.forEach(busVitalSignRecord -> {
                BusVitalSignRecordPO busVitalSignRecordPO = new BusVitalSignRecordPO();
                BeanUtil.copyProperties(busVitalSignRecord, busVitalSignRecordPO);
                busVitalSignRecordPO.setRecordTime(sdf.format(busVitalSignRecord.getRecordTime()));
                busVitalSignRecordPOS.add(busVitalSignRecordPO);
            });
        }
        return busVitalSignRecordPOS;
    }

    @Override
    public VitalSignRecordVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        VitalSignRecordVO vitalSignRecordVO = new VitalSignRecordVO();
        Date startTime;
        Date endTime;
        if (Strings.isNotBlank(queryVitalSignPO.getStartTime()) && Strings.isNotBlank(queryVitalSignPO.getEndTime())) {
            try {
                startTime = sdf.parse(queryVitalSignPO.getStartTime());
                endTime = sdf.parse(queryVitalSignPO.getEndTime());
            } catch (ParseException e) {
                throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            endTime = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, -6);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            startTime = calendar.getTime();
        }

        // 查询住院信息
        Example exampleHosp = new Example(BusHospitalRecord.class);
        Example.Criteria criteriaHosp = exampleHosp.createCriteria();
        criteriaHosp.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteriaHosp.andEqualTo("isDel", 0);
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(exampleHosp);
        if (busHospitalRecords.size() == 0) {
            throw ResponseEnum.DATA_NOT_FOUND.newException("未找到患者住院信息");
        }
        PatientInfo patientInfo = new PatientInfo();
        BeanUtil.copyProperties(busHospitalRecords.get(0), patientInfo);
        patientInfo.setSex(SexEnum.getValueFromCode(patientInfo.getSex()));
        vitalSignRecordVO.setPatientInfoList(patientInfo);

        // 判断开始时间是否在入院时间之前
        Calendar admissionCalendar = Calendar.getInstance();
        admissionCalendar.setTime(busHospitalRecords.get(0).getAdmissionTime());
        admissionCalendar.set(Calendar.HOUR_OF_DAY, 0);
        admissionCalendar.set(Calendar.MINUTE, 0);
        admissionCalendar.set(Calendar.SECOND, 0);
        admissionCalendar.set(Calendar.MILLISECOND, 0);
        Date admissionDay = admissionCalendar.getTime();
        if (admissionDay.after(endTime)) {
            throw ResponseEnum.DATA_NOT_FOUND.newException("患者在指定时间段内并未入院，请核查起止时间或者联系管理员");
        }
        if (admissionDay.after(startTime)) {
            startTime = admissionDay;
        }

        // 查询三测单记录
        Example example = new Example(BusVitalSignRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andBetween("recordTime", startTime, endTime);
        List<BusVitalSignRecord> busVitalSignRecords = busVitalSignRecordMapper.selectByExample(example);
        long days = (startTime.getTime() - admissionDay.getTime()) / (24*3600*1000) + 1;
        vitalSignRecordVO.setBeginDate(sdf.format(startTime));

        List<String> intakeList = new ArrayList<>();
        List<String> outputList = new ArrayList<>();
        List<String> urineList = new ArrayList<>();
        List<String> defecateList = new ArrayList<>();
        List<String> defecatePatternList = new ArrayList<>();
        List<String> weightList = new ArrayList<>();
        List<String> bloodOxygenList = new ArrayList<>();

        List<VitalSignRecord> mb = new ArrayList<>();
        List<VitalSignRecord> wd = new ArrayList<>();

        // 7天一个循环
        for (int i = 0; i < 7; i++) {
            // 第i天
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startTime);
            startCalendar.add(Calendar.DAY_OF_MONTH, i);
            // 第i天超过截止日期
            if (startCalendar.getTime().compareTo(endTime) > 0) {
                vitalSignRecordVO.getDayList().add("");
                addRecord(vitalSignRecordVO, null, intakeList, outputList, urineList, defecateList, defecatePatternList, weightList, bloodOxygenList, mb, wd);
                continue;
            }
            // 获取第i天记录列表
            List<BusVitalSignRecord> dayRecordList = busVitalSignRecords.stream().filter(s -> s.getRecordTime().compareTo(startCalendar.getTime()) == 0).collect(Collectors.toList());
            vitalSignRecordVO.getDayList().add(String.valueOf(days + i));
            addRecord(vitalSignRecordVO, dayRecordList, intakeList, outputList, urineList, defecateList, defecatePatternList, weightList, bloodOxygenList, mb, wd);
        }
        vitalSignRecordVO.getDayMap().put("{name:'入量',units:'ml'}", intakeList);
        vitalSignRecordVO.getDayMap().put("{name:'出量',units:'ml'}", outputList);
        vitalSignRecordVO.getDayMap().put("{name:'小便',units:'ml'}", urineList);
        vitalSignRecordVO.getDayMap().put("{name:'大便',units:'次/日'}", defecateList);
        vitalSignRecordVO.getDayMap().put("{name:'大便方式',units:''}", defecatePatternList);
        vitalSignRecordVO.getDayMap().put("{name:'体重',units:'kg'}", weightList);
        vitalSignRecordVO.getDayMap().put("{name:'血氧饱和度',units:''}", bloodOxygenList);

        PointTime pointTime = new PointTime();
        pointTime.setMb(mb);
        pointTime.setWd(wd);
        vitalSignRecordVO.setPointTime(pointTime);

        return vitalSignRecordVO;
    }

    private void addRecord(VitalSignRecordVO vitalSignRecordVO,
                           List<BusVitalSignRecord> dayRecordList,
                           List<String> intakeList,
                           List<String> outputList,
                           List<String> urineList,
                           List<String> defecateList,
                           List<String> defecatePatternList,
                           List<String> weightList,
                           List<String> bloodOxygenList,
                           List<VitalSignRecord> mbList,
                           List<VitalSignRecord> wdList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        vitalSignRecordVO.getDayOps().add("");
        if (dayRecordList == null || dayRecordList.size() == 0) {
            for (int j = 0; j < 6; j++) {
                vitalSignRecordVO.getBreathingList().add("");
            }
            vitalSignRecordVO.getXyList().add("");
            vitalSignRecordVO.getXyList().add("");
            intakeList.add("");
            outputList.add("");
            urineList.add("");
            defecateList.add("");
            defecatePatternList.add("");
            weightList.add("");
            bloodOxygenList.add("");
        } else {
            int breathCount = 0;
            boolean xyFlag = false;
            boolean intakeFlag = false;
            boolean outputFlag = false;
            boolean urineFlag = false;
            boolean defecateFlag = false;
            boolean defecatePatternFlag = false;
            boolean weightFlag = false;
            boolean bloodOxygenFlag = false;
            for (BusVitalSignRecord busVitalSignRecord : dayRecordList) {
                VitalSignRecord mb = new VitalSignRecord();
                VitalSignRecord wd = new VitalSignRecord();
                if (busVitalSignRecord.getRecordTime() != null && Strings.isNotBlank(busVitalSignRecord.getTimePoint())) {
                    String date = sdf.format(busVitalSignRecord.getRecordTime());
                    String dateTime = date + " " + busVitalSignRecord.getTimePoint();
                    Integer hour = Integer.valueOf(busVitalSignRecord.getTimePoint().split(":")[0]);
                    mb.setDataTime(dateTime);
                    mb.setDate(date);
                    mb.setHour(hour);
                    mb.setType("");
                    mb.setValue(StringUtil.intToString(busVitalSignRecord.getPulse()));
                    wd.setDataTime(dateTime);
                    wd.setDate(date);
                    wd.setHour(hour);
                    wd.setType("ew");
                    wd.setValue(StringUtil.bigDecimalToString(busVitalSignRecord.getTemperature()));
                }
                mbList.add(mb);
                wdList.add(wd);
                // 呼吸
                if (breathCount < 6 && busVitalSignRecord.getBreathing() != null) {
                    vitalSignRecordVO.getBreathingList().add(StringUtil.intToString(busVitalSignRecord.getBreathing()));
                    breathCount ++;
                }
                // 血压
                if (!xyFlag && busVitalSignRecord.getLowBloodPressure() != null && busVitalSignRecord.getHighBloodPressure() != null) {
                    vitalSignRecordVO.getXyList().add(StringUtil.intToString(busVitalSignRecord.getLowBloodPressure()));
                    vitalSignRecordVO.getXyList().add(StringUtil.intToString(busVitalSignRecord.getHighBloodPressure()));
                    xyFlag = true;
                }
                // 入量
                if (!intakeFlag && busVitalSignRecord.getIntake() != null) {
                    intakeList.add(StringUtil.bigDecimalToString(busVitalSignRecord.getIntake()));
                    intakeFlag = true;
                }
                // 出量
                if (!outputFlag && busVitalSignRecord.getOutput() != null) {
                    outputList.add(StringUtil.bigDecimalToString(busVitalSignRecord.getOutput()));
                    outputFlag = true;
                }
                // 小便
                if (!urineFlag && busVitalSignRecord.getUrine() != null) {
                    urineList.add(StringUtil.bigDecimalToString(busVitalSignRecord.getUrine()));
                    urineFlag = true;
                }
                // 大便
                if (!defecateFlag && busVitalSignRecord.getDefecate() != null) {
                    defecateList.add(StringUtil.intToString(busVitalSignRecord.getDefecate()));
                    defecateFlag = true;
                }
                // 大便方式
                if (!defecatePatternFlag && busVitalSignRecord.getDefecatePattern() != null) {
                    defecatePatternList.add(busVitalSignRecord.getDefecatePattern());
                    defecatePatternFlag = true;
                }
                // 体重
                if (!weightFlag && busVitalSignRecord.getWeight() != null) {
                    weightList.add(StringUtil.bigDecimalToString(busVitalSignRecord.getWeight()));
                    weightFlag = true;
                }
                // 血氧
                if (!bloodOxygenFlag && busVitalSignRecord.getBloodOxygen() != null) {
                    bloodOxygenList.add(StringUtil.intToString(busVitalSignRecord.getBloodOxygen()));
                    bloodOxygenFlag = true;
                }
            }
            while (breathCount < 6) {
                vitalSignRecordVO.getBreathingList().add("");
                breathCount ++;
            }
            if (!xyFlag) {
                vitalSignRecordVO.getXyList().add("");
                vitalSignRecordVO.getXyList().add("");
            }
            if (!intakeFlag) {
                intakeList.add("");
            }
            if (!outputFlag) {
                outputList.add("");
            }
            if (!urineFlag) {
                urineList.add("");
            }
            if (!defecateFlag) {
                defecateList.add("");
            }
            if (!weightFlag) {
                weightList.add("");
            }
            if (!bloodOxygenFlag) {
                bloodOxygenList.add("");
            }
        }
    }
}
