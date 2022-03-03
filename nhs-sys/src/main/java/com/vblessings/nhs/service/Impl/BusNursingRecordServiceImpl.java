package com.vblessings.nhs.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusHospitalRecordMapper;
import com.vblessings.nhs.mapper.BusNursingRecordMapper;
import com.vblessings.nhs.model.entity.business.BusHospitalRecord;
import com.vblessings.nhs.model.entity.business.BusNursingRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.business.*;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.BusNursingRecordQueryVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignRecordVO;
import com.vblessings.nhs.model.vo.business.BusVitalSignVO;
import com.vblessings.nhs.model.vo.business.ExportNursingRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusNursingRecordService;
import com.vblessings.nhs.util.DateUtils;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.writeHandler.CustomCellWriteHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BusNursingRecordServiceImpl implements BusNursingRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusNursingRecordMapper busNursingRecordMapper;

    @Resource
    private BusHospitalRecordMapper busHospitalRecordMapper;

    @Override
    public void addNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        BusNursingRecord busNursingRecord = new BusNursingRecord();
        BeanUtil.copyProperties(busNursingRecordPO, busNursingRecord);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            busNursingRecord.setRecordTime(sdf.parse(busNursingRecordPO.getRecordTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        OperateUtil.onSaveNew(busNursingRecord, userInfo, id);
        try {
            busNursingRecordMapper.insert(busNursingRecord);
        } catch (DuplicateKeyException e) {
            throw ResponseEnum.DATA_ALREADY_EXISTS.newException("数据已存在");
        }
    }

    @Override
    public PageVO<BusNursingRecordPO> pageNursingRecord(QueryNursingRecordPO queryNursingRecordPO) {
        Page<BusNursingRecord> result = PageHelper.startPage(queryNursingRecordPO.getPageNum(), queryNursingRecordPO.getPageSize());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<BusNursingRecordPO> busNursingRecordPOS = new ArrayList<>();

        if (queryNursingRecordPO.getId() != null) {
            BusNursingRecord busNursingRecord = busNursingRecordMapper.selectByPrimaryKey(queryNursingRecordPO.getId());
            BusNursingRecordPO busNursingRecordPO = new BusNursingRecordPO();
            BeanUtil.copyProperties(busNursingRecord, busNursingRecordPO);
            busNursingRecordPO.setRecordTime(sdf1.format(busNursingRecord.getRecordTime()));
            busNursingRecordPO.setCreateTime(sdf2.format(busNursingRecord.getCreateTime()));
            busNursingRecordPOS.add(busNursingRecordPO);
        } else {
            Example example = new Example(BusNursingRecord.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("isDel", 0);
            if (!Strings.isNullOrEmpty(queryNursingRecordPO.getName())) {
                criteria.andLike("name", "%" + queryNursingRecordPO.getName() + "%");
            }
            if (!Strings.isNullOrEmpty(queryNursingRecordPO.getBusinessNo())) {
                criteria.andEqualTo("businessNo", queryNursingRecordPO.getBusinessNo());
            }
            if (queryNursingRecordPO.getStartTime() != null && queryNursingRecordPO.getEndTime() != null) {
                criteria.andBetween("recordTime", queryNursingRecordPO.getStartTime(), queryNursingRecordPO.getEndTime());
            }
            List<BusNursingRecord> busNursingRecords = busNursingRecordMapper.selectByExample(example);
            busNursingRecords.forEach(busNursingRecord -> {
                BusNursingRecordPO busNursingRecordPO = new BusNursingRecordPO();
                BeanUtil.copyProperties(busNursingRecord, busNursingRecordPO);
                busNursingRecordPO.setRecordTime(sdf1.format(busNursingRecord.getRecordTime()));
                busNursingRecordPO.setCreateTime(sdf2.format(busNursingRecord.getCreateTime()));
                busNursingRecordPOS.add(busNursingRecordPO);
            });
        }
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busNursingRecordPOS);
    }

    @Override
    public void updateNursingRecord(BusNursingRecordPO busNursingRecordPO, UserInfoToken userInfo) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BusNursingRecord busNursingRecord = new BusNursingRecord();
        BeanUtil.copyProperties(busNursingRecordPO, busNursingRecord);
        try {
            busNursingRecord.setRecordTime(sdf1.parse(busNursingRecordPO.getRecordTime()));
            busNursingRecord.setCreateTime(sdf2.parse(busNursingRecordPO.getCreateTime()));
        } catch (ParseException e) {
            throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
        }
        busNursingRecord.setUpdaterId(userInfo.getUserId());
        busNursingRecord.setUpdateTime(new DateTime());
        busNursingRecordMapper.updateByPrimaryKey(busNursingRecord);
    }

    @Override
    public void batchUpdateNursingRecord(List<BusNursingRecordPO> busNursingRecordPOS, UserInfoToken userInfo) {
        busNursingRecordPOS.forEach(busNursingRecordPO -> {
            if (busNursingRecordPO.getId() != null) {
                updateNursingRecord(busNursingRecordPO, userInfo);
            } else {
                addNursingRecord(busNursingRecordPO, userInfo);
            }
        });
    }

    @Override
    public void delNursingRecord(String ids) {
        String[] id = ids.split(",");
        busNursingRecordMapper.batchDel(id);
    }

    @Override
    public BusVitalSignVO queryVitalSignRecord(QueryVitalSignPO queryVitalSignPO) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BusVitalSignVO busVitalSignVO = new BusVitalSignVO();

        // 查询住院信息
        Example exampleHosp = new Example(BusHospitalRecord.class);
        Example.Criteria criteriaHosp = exampleHosp.createCriteria();
        criteriaHosp.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteriaHosp.andEqualTo("isDel", 0);
        List<BusHospitalRecord> busHospitalRecords = busHospitalRecordMapper.selectByExample(exampleHosp);
        if (busHospitalRecords.size() == 0) {
            throw ResponseEnum.DATA_NOT_FOUND.newException("未找到患者住院信息");
        }
        BeanUtil.copyProperties(busHospitalRecords.get(0), busVitalSignVO);

        Example example = new Example(BusNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", queryVitalSignPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        if (!Strings.isNullOrEmpty(queryVitalSignPO.getStartTime()) && !Strings.isNullOrEmpty(queryVitalSignPO.getEndTime())) {
            try {
                Date start = sdf.parse(queryVitalSignPO.getStartTime());
                Date end = sdf.parse(queryVitalSignPO.getEndTime());
                criteria.andBetween("recordTime", start, end);
            } catch (ParseException e) {
                throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            Date end = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, -6);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date start = calendar.getTime();
            criteria.andBetween("recordTime", start, end);
        }
        List<BusNursingRecord> busNursingRecords = busNursingRecordMapper.selectByExample(example);
        List<BusVitalSignRecordVO> busVitalSignRecordVOS = new ArrayList<>();
        busNursingRecords.forEach(busNursingRecord -> {
            BusVitalSignRecordVO busVitalSignRecordVO = new BusVitalSignRecordVO();
            BeanUtil.copyProperties(busNursingRecord, busVitalSignRecordVO);
            busVitalSignRecordVO.setRecordTime(sdf.format(busNursingRecord.getRecordTime()));
            busVitalSignRecordVO.setLengthOfStay(DateUtils.differentDaysByMillisecond(
                    busHospitalRecords.get(0).getAdmissionTime(), busNursingRecord.getRecordTime()));
            busVitalSignRecordVOS.add(busVitalSignRecordVO);
        });
        busVitalSignVO.setBusVitalSignRecordVOS(busVitalSignRecordVOS);
        return busVitalSignVO;
    }

    @Override
    public List<BusNursingRecordQueryVO> batchQueryNursingRecord(QueryBatchVitalSignPO queryBatchVitalSignPO) {
        return busNursingRecordMapper.batchQueryNursingRecord(queryBatchVitalSignPO.getRecordTime());
    }

    @Override
    public List<BusNursingRecordPO> nursingRecordByTimePoint(QueryNursingRecordByTimePO queryNursingRecordByTimePO) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Example example = new Example(BusNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", queryNursingRecordByTimePO.getBusinessNo());
        if (!Strings.isNullOrEmpty(queryNursingRecordByTimePO.getRecordTime()) && !Strings.isNullOrEmpty(queryNursingRecordByTimePO.getTimePoint())) {
            try {
                criteria.andEqualTo("recordTime", sdf1.parse(queryNursingRecordByTimePO.getRecordTime()));
            } catch (ParseException e) {
                throw ResponseEnum.DATA_TRANSFER_ERROR.newException("日期格式转换错误");
            }
            criteria.andEqualTo("timePoint", queryNursingRecordByTimePO.getTimePoint());
        }
        criteria.andEqualTo("isDel", 0);
        List<BusNursingRecord> busNursingRecords = busNursingRecordMapper.selectByExample(example);
        if (busNursingRecords.size() > 0) {
            List<BusNursingRecordPO> busNursingRecordPOS = new ArrayList<>();
            busNursingRecords.forEach(busNursingRecord -> {
                BusNursingRecordPO busNursingRecordPO = new BusNursingRecordPO();
                BeanUtil.copyProperties(busNursingRecord, busNursingRecordPO);
                busNursingRecordPO.setRecordTime(sdf1.format(busNursingRecord.getRecordTime()));
                busNursingRecordPO.setCreateTime(sdf2.format(busNursingRecord.getCreateTime()));
                busNursingRecordPOS.add(busNursingRecordPO);
            });
            return busNursingRecordPOS;
        } else {
            throw ResponseEnum.DATA_NOT_FOUND.newException("未查询到数据");
        }
    }

    @Override
    public void exportNursingRecord(String ids, HttpServletResponse response) throws IOException {
        Example example = new Example(BusNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> id = Arrays.asList(ids.split(","));
        criteria.andEqualTo("isDel", 0);
        criteria.andIn("id", id);
        List<BusNursingRecord> busNursingRecordList = busNursingRecordMapper.selectByExample(example);
        BusNursingRecord busNursingRecord = busNursingRecordList.get(0);
        List<ExportNursingRecordVO> exportNursingRecordVOList = new ArrayList<>();
        if (busNursingRecordList != null && busNursingRecordList.size() > 0) {
            for (BusNursingRecord busNursingRecord1 :
                    busNursingRecordList) {
                ExportNursingRecordVO exportNursingRecordVO = new ExportNursingRecordVO();
                BeanUtil.copyProperties(busNursingRecord1, exportNursingRecordVO);
                if (busNursingRecord1.getIsHaircut()!=null){
                    if(busNursingRecord1.getIsHaircut()){
                        exportNursingRecordVO.setIsHaircut("是");
                    }else {
                        exportNursingRecordVO.setIsHaircut("否");
                    }
                }
                if (busNursingRecord1.getIsManicure()!=null) {
                    if (busNursingRecord1.getIsHaircut()) {
                        exportNursingRecordVO.setIsManicure("是");
                    } else {
                        exportNursingRecordVO.setIsManicure("否");
                    }
                }
                if (busNursingRecord1.getIsCleanToilet()!=null) {
                    if (busNursingRecord1.getIsCleanToilet()) {
                        exportNursingRecordVO.setIsCleanToilet("是");
                    } else {
                        exportNursingRecordVO.setIsCleanToilet("否");
                    }
                }
                if (busNursingRecord1.getIsHangClothes()!=null) {
                    if (busNursingRecord1.getIsHangClothes()) {
                        exportNursingRecordVO.setIsHangClothes("是");
                    } else {
                        exportNursingRecordVO.setIsHangClothes("否");
                    }
                }
                if (busNursingRecord1.getIsCleanRoom()!=null) {
                    if (busNursingRecord1.getIsCleanRoom()) {
                        exportNursingRecordVO.setIsCleanRoom("是");
                    } else {
                        exportNursingRecordVO.setIsCleanRoom("否");
                    }
                }

                if (busNursingRecord1.getIsMeals()!=null) {
                    if (busNursingRecord1.getIsMeals()) {
                        exportNursingRecordVO.setIsMeals("是");
                    } else {
                        exportNursingRecordVO.setIsMeals("否");
                    }
                }
                if (busNursingRecord1.getIsWashGargle()!=null) {
                    if (busNursingRecord1.getIsWashGargle()) {
                        exportNursingRecordVO.setIsWashGargle("是");
                    } else {
                        exportNursingRecordVO.setIsWashGargle("否");
                    }
                }
                exportNursingRecordVOList.add(exportNursingRecordVO);

            }
        }

        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("护理记录");

        String fileName = URLEncoder.encode(bigTitle.toString(), "UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        // 头的策略

        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy
                =new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);
        OutputStream out = response.getOutputStream();
        EasyExcel.write(out, ExportNursingRecordVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .head(getNursingRecord(bigTitle.toString(),busNursingRecord))
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(12))
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("老人自带药品记录表").doWrite(exportNursingRecordVOList);

    }

    @Override
    public List<BusNursingRecordQueryVO> batchQueryNursingRecordNoToken(TimeQueryPO timeQueryPO) {
        List<BusNursingRecordQueryVO> busNursingRecordQueryVOS = busNursingRecordMapper.batchQueryNursingRecordNoToken(timeQueryPO);
        if(CollectionUtil.isNotEmpty(busNursingRecordQueryVOS)){
            busNursingRecordQueryVOS.forEach(busNursingRecordQueryVO -> {
                if(StringUtils.isNotBlank(busNursingRecordQueryVO.getRecordTime()) && StringUtils.isNotBlank(busNursingRecordQueryVO.getTimePoint())){
                    busNursingRecordQueryVO.setRecordPointTime(busNursingRecordQueryVO.getRecordTime() + " " + busNursingRecordQueryVO.getTimePoint());
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsHaircut(), true)){
                    busNursingRecordQueryVO.setIsHaircutName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsManicure(), true)){
                    busNursingRecordQueryVO.setIsManicureName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsCleanToilet(), true)){
                    busNursingRecordQueryVO.setIsCleanToiletName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsHangClothes(), true)){
                    busNursingRecordQueryVO.setIsHangClothesName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsCleanRoom(), true)){
                    busNursingRecordQueryVO.setIsCleanRoomName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsMeals(), true)){
                    busNursingRecordQueryVO.setIsMealsName("√");
                }
                if(Objects.equals(busNursingRecordQueryVO.getIsWashGargle(), true)){
                    busNursingRecordQueryVO.setIsWashGargleName("√");
                }
            });
        }
        return busNursingRecordQueryVOS;
    }

    public  List<List<String>> getNursingRecord(String bigTitle, BusNursingRecord busNursingRecord){
        String secondTitle ="住院号:"+busNursingRecord.getBusinessNo()+" 姓名:"+busNursingRecord.getName();
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add(secondTitle);
        head0.add("洗头理发");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add(secondTitle);
        head1.add("修剪指甲");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add(secondTitle);
        head2.add("清洗便器");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add(secondTitle);
        head3.add("更换清洗晾晒衣服");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add(secondTitle);
        head4.add("清扫房间");
        List<String> head5 = new ArrayList<>();
        head5.add(bigTitle);
        head5.add(secondTitle);
        head5.add("订餐送餐");
        List<String> head6 = new ArrayList<>();
        head6.add(bigTitle);
        head6.add(secondTitle);
        head6.add("送开水打洗漱水");
        List<String> head7 = new ArrayList<>();
        head7.add(bigTitle);
        head7.add(secondTitle);
        head7.add("老年人身心观察处理");
        List<String> head8 = new ArrayList<>();
        head8.add(bigTitle);
        head8.add(secondTitle);
        head8.add("其他");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        head.add(head5);
        head.add(head6);
        head.add(head7);
        head.add(head8);
        return head;
    }
}
