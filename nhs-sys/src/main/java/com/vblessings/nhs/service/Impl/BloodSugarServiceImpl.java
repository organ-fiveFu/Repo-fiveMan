package com.vblessings.nhs.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.enums.DictTypeEnum;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusBloodSugarRecordMapper;
import com.vblessings.nhs.model.entity.business.BusBloodSugarRecord;
import com.vblessings.nhs.model.po.TimeQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarInsertPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarQueryPO;
import com.vblessings.nhs.model.po.nurse.BloodSugarUpdatePO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarExcelVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarQueryVO;
import com.vblessings.nhs.model.vo.nurse.BloodSugarReportQueryVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BloodSugarService;
import com.vblessings.nhs.service.SysDictDataService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.writeHandler.CustomCellWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("bloodSugarService")
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BloodSugarServiceImpl implements BloodSugarService {

    @Resource
    private BusBloodSugarRecordMapper busBloodSugarRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private SysDictDataService sysDictDataService;

    @Override
    public boolean insertBloodSugar(BloodSugarInsertPO bloodSugarInsertPO, UserInfoToken userInfoToken) {
        String s = DateUtil.formatDate(bloodSugarInsertPO.getBloodSugarRecordDate());
        //判断当个日期的病人是否重复
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("businessNo", bloodSugarInsertPO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andLike("bloodSugarRecordDate", s+"%");
        criteria.andEqualTo("samplingStatus", bloodSugarInsertPO.getSamplingStatus());
        int count = busBloodSugarRecordMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该日期该病人该采样时间段的血糖记录已存在，无法新增");
        }
        //新增
        BusBloodSugarRecord busBloodSugarRecord = new BusBloodSugarRecord();
        BeanUtils.copyProperties(bloodSugarInsertPO, busBloodSugarRecord);
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busBloodSugarRecord, userInfoToken, id);
        log.info("新增血糖记录,入参busBloodSugarRecord:{}", JSON.toJSONString(busBloodSugarRecord));
        try {
            busBloodSugarRecordMapper.insertSelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("新增血糖记录失败");
            throw ResponseEnum.FILE_INSERT_FAIL.newException("新增血糖记录失败");
        }
        return true;
    }

    @Override
    public boolean updateBloodSugar(BloodSugarUpdatePO bloodSugarUpdatePO, UserInfoToken userInfoToken) {
        String s = DateUtil.formatDate(bloodSugarUpdatePO.getBloodSugarRecordDate());
        //判断当个日期的病人是否重复
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id", bloodSugarUpdatePO.getId());
        criteria.andEqualTo("businessNo", bloodSugarUpdatePO.getBusinessNo());
        criteria.andEqualTo("isDel", 0);
        criteria.andLike("bloodSugarRecordDate", s+"%");
        criteria.andEqualTo("samplingStatus", bloodSugarUpdatePO.getSamplingStatus());
        int count = busBloodSugarRecordMapper.selectCountByExample(example);
        if(count > 0){
            throw ResponseEnum.CODE_ALREADY_EXISTS.newException("该日期该病人该采样时间段的血糖记录已存在，无法更新");
        }
        //更新
        BusBloodSugarRecord busBloodSugarRecord = new BusBloodSugarRecord();
        BeanUtils.copyProperties(bloodSugarUpdatePO, busBloodSugarRecord);
        busBloodSugarRecord.setUpdaterId(userInfoToken.getUserId());
        busBloodSugarRecord.setUpdateTime(new Date());
        log.info("更新血糖记录,入参busBloodSugarRecord:{}", JSON.toJSONString(busBloodSugarRecord));
        try {
            busBloodSugarRecordMapper.updateByPrimaryKeySelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("更新血糖记录失败");
            throw ResponseEnum.FILE_UPDATE_FAIL.newException("更新血糖记录失败");
        }
        return true;
    }

    @Override
    public PageVO<BloodSugarQueryVO> queryBloodSugar(BloodSugarQueryPO bloodSugarQueryPO) {
        Page<BloodSugarQueryVO> result = PageHelper.startPage(bloodSugarQueryPO.getPageNum(), bloodSugarQueryPO.getPageSize());
        List<BloodSugarQueryVO> bloodSugarQueryVOList = busBloodSugarRecordMapper.queryBloodSugar(bloodSugarQueryPO);
        if(CollectionUtil.isEmpty(bloodSugarQueryVOList)){
            return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), new ArrayList<>());
        }
        Map<String, String> samplingStatusMap = sysDictDataService.getDictName(DictTypeEnum.BLOOD_SAMPLING_STATUS.getCode(), null);
        bloodSugarQueryVOList.forEach(bloodSugarQueryVO -> {
            bloodSugarQueryVO.setSamplingStatusName(samplingStatusMap.get(bloodSugarQueryVO.getSamplingStatus()));
            bloodSugarQueryVO.setBedName(bloodSugarQueryVO.getBuildingName() + "-" + bloodSugarQueryVO.getFloorName() +
                    "-" + bloodSugarQueryVO.getRoomName() + "-" + bloodSugarQueryVO.getBedName());
        });
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), bloodSugarQueryVOList);
    }

    @Override
    public boolean deleteBloodSugar(Long id, UserInfoToken userInfoToken) {
        BusBloodSugarRecord busBloodSugarRecord = busBloodSugarRecordMapper.selectByPrimaryKey(id);
        busBloodSugarRecord.setIsDel(1);
        busBloodSugarRecord.setUpdateTime(new Date());
        busBloodSugarRecord.setUpdaterId(userInfoToken.getUserId());
        log.info("删除血糖记录,入参busBloodSugarRecord:" + busBloodSugarRecord);
        try {
            busBloodSugarRecordMapper.updateByPrimaryKeySelective(busBloodSugarRecord);
        }catch (Exception e){
            log.error("删除血糖记录失败");
            throw ResponseEnum.FILE_DELETE_FAIL.newException("删除血糖记录失败");
        }
        return true;
    }

    @Override
    public void exportBloodSugar(String ids, HttpServletResponse response) throws IOException {
        List<String> id = Arrays.asList(ids.split(","));
        Example example = new Example(BusBloodSugarRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        criteria.andIn("id",id);
        List<BusBloodSugarRecord> busBloodSugarRecords = busBloodSugarRecordMapper.selectByExample(example);
        BusBloodSugarRecord busBloodSugarRecord1 = busBloodSugarRecords.get(0);
        List<String> samplingStatusList = busBloodSugarRecords.stream().map(BusBloodSugarRecord::getSamplingStatus).collect(Collectors.toList());
        Map<String, String> samplingStatusMap = sysDictDataService.getDictName(DictTypeEnum.BLOOD_SAMPLING_STATUS.getCode(), samplingStatusList);
        List<BloodSugarExcelVO> bloodSugarExcelVOList = new ArrayList<>();
        busBloodSugarRecords.forEach(busBloodSugarRecord -> {
            BloodSugarExcelVO bloodSugarExcelVO = new BloodSugarExcelVO();
            //采样时间
            String samplingTime = null == busBloodSugarRecord.getSamplingTime() ? "" :
                    DateFormatUtils.format(busBloodSugarRecord.getSamplingTime(), "yyyy-MM-dd HH:mm:ss");
            bloodSugarExcelVO.setSamplingTime(samplingTime);
            bloodSugarExcelVO.setSamplingStatusName(samplingStatusMap.get(busBloodSugarRecord.getSamplingStatus()));
            bloodSugarExcelVO.setBloodGlucoseValue(busBloodSugarRecord.getBloodGlucoseValue());
            bloodSugarExcelVOList.add(bloodSugarExcelVO);
        });
        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("血糖记录表");
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy
                =new HorizontalCellStyleStrategy(headWriteCellStyle,contentWriteCellStyle);
        String fileName = URLEncoder.encode(bigTitle.toString(), "UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        OutputStream out = response.getOutputStream();
        EasyExcel.write(out, BloodSugarExcelVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .head(getBloodSugar(bigTitle.toString(), busBloodSugarRecord1))
                .registerWriteHandler(horizontalCellStyleStrategy)
                //设置列宽
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(22))
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("血糖记录表").doWrite(bloodSugarExcelVOList);
    }

    /**
     * 血糖记录报表-notoken
     * @author linxiazhu
     * @date 10:17 2022/2/15
     * @param timeQueryPO   入参
     * @return  java.util.List<com.vblessings.nhs.model.vo.nurse.BloodSugarReportQueryVO>
     */
    @Override
    public List<BloodSugarReportQueryVO> queryBloodSugarNoToken(TimeQueryPO timeQueryPO) {
        List<BloodSugarReportQueryVO> bloodSugarReportQueryVOS = busBloodSugarRecordMapper.queryBloodSugarNoToken(timeQueryPO);
        if(CollectionUtil.isEmpty(bloodSugarReportQueryVOS)){
            return new ArrayList<>();
        }
        Map<String, String> samplingStatusMap = sysDictDataService.getDictName(DictTypeEnum.BLOOD_SAMPLING_STATUS.getCode(), null);
        bloodSugarReportQueryVOS.forEach(bloodSugarReportQueryVO -> {
            bloodSugarReportQueryVO.setSamplingStatusName(samplingStatusMap.get(bloodSugarReportQueryVO.getSamplingStatus()));
            bloodSugarReportQueryVO.setSpliceBedName(bloodSugarReportQueryVO.getBuildingName() + "-" + bloodSugarReportQueryVO.getFloorName() +
                    "-" + bloodSugarReportQueryVO.getRoomName() + "-" + bloodSugarReportQueryVO.getBedName());
        });
        return bloodSugarReportQueryVOS;
    }

    /**
     * 血糖记录信息头
     * @param bigTitle
     * @return
     */
    public   List<List<String>> getBloodSugar(String bigTitle, BusBloodSugarRecord busBloodSugarRecord){
        String secondTitle = "床号:"+busBloodSugarRecord.getBedName()+ "   姓名:"+busBloodSugarRecord.getPatientName()+"    医院诊断:"+busBloodSugarRecord.getHospitalDiagnosis();
        List<List<String>> head = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add(secondTitle);
        head0.add("采样日期");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add(secondTitle);
        head1.add("采样状态");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add(secondTitle);
        head2.add("血糖值");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add(secondTitle);
        head3.add("单位");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        return head;
    }
}
