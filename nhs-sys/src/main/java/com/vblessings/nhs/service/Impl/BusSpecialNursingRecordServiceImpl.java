package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.exception.ResponseEnum;
import com.vblessings.nhs.mapper.BusSpecialNursingRecordMapper;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.po.businessVO.QuerySpecialNursingPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.nurse.ExportSpecialNursingVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusSpecialNursingRecordService;
import com.vblessings.nhs.util.OperateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BusSpecialNursingRecordServiceImpl implements BusSpecialNursingRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusSpecialNursingRecordMapper busSpecialNursingRecordMapper;

    @Override
    public void addSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busSpecialNursingRecord,userInfo,id);
        busSpecialNursingRecordMapper.insert(busSpecialNursingRecord);
    }

    @Override
    public PageVO<BusSpecialNursingRecord> pageSpecialNursing(QuerySpecialNursingPO querySpecialNursingPO) {
        Page<BusSpecialNursingRecord> result = PageHelper.startPage(querySpecialNursingPO.getPageNum(), querySpecialNursingPO.getPageSize());
        Example example = new Example(BusSpecialNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isDel",0);
        List<BusSpecialNursingRecord> busSpecialNursingRecordList = new ArrayList<>();

        if(Strings.isNotBlank(querySpecialNursingPO.getName())){
            criteria.andLike("patientName","%"+querySpecialNursingPO.getName()+"%");
           }
        if(Strings.isNotBlank(querySpecialNursingPO.getId())){
            criteria.andEqualTo("id",Long.parseLong(querySpecialNursingPO.getId()));
        }
        busSpecialNursingRecordList = busSpecialNursingRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busSpecialNursingRecordList);

    }

    @Override
    public void updateSpecialNursing(BusSpecialNursingRecord busSpecialNursingRecord, UserInfoToken userInfo) {
        busSpecialNursingRecord.setUpdaterId(userInfo.getUserId());
        busSpecialNursingRecord.setUpdateTime(new DateTime());
        busSpecialNursingRecordMapper.updateByPrimaryKeySelective(busSpecialNursingRecord);
    }

    @Override
    public void delSpecialNursing(String ids) {
     String[] id = ids.split(",");
        busSpecialNursingRecordMapper.del(id);
    }

    /**
     * 导出excel
     * @param ids
     */
    @Override

    public void exportSpecialNursing(String ids, HttpServletResponse response) throws IOException {
        Example example = new Example(BusSpecialNursingRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> id = Arrays.asList(ids.split(","));
        criteria.andEqualTo("isDel",0);
        criteria.andIn("id",id);
        List<BusSpecialNursingRecord> busSpecialNursingRecordList = busSpecialNursingRecordMapper.selectByExample(example);
        List<ExportSpecialNursingVO> exportSpecialNursingVOList = new ArrayList<>();
        for (BusSpecialNursingRecord busSpecialNursingRecord:
        busSpecialNursingRecordList) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            ExportSpecialNursingVO exportSpecialNursingVO = new ExportSpecialNursingVO();
            BeanUtils.copyProperties(busSpecialNursingRecord,exportSpecialNursingVO);
            exportSpecialNursingVO.setNursingTime(sdf.format(busSpecialNursingRecord.getNursingTime()));
            if(busSpecialNursingRecord.getIsEveningCare().equals(0)){
                exportSpecialNursingVO.setIsEveningCare("是");
            } if(busSpecialNursingRecord.getIsEveningCare().equals(1)){
                exportSpecialNursingVO.setIsEveningCare("否");
            }
            if(busSpecialNursingRecord.getIsMorningCare().equals(0)){
                exportSpecialNursingVO.setIsMorningCare("是");
            }
            if(busSpecialNursingRecord.getIsMorningCare().equals(1)){
                exportSpecialNursingVO.setIsMorningCare("否");
            }
            if(busSpecialNursingRecord.getIsPressureUlcersCare().equals(0)){
                exportSpecialNursingVO.setIsPressureUlcersCare("是");
            }
            if(busSpecialNursingRecord.getIsPressureUlcersCare().equals(1)){
                exportSpecialNursingVO.setIsPressureUlcersCare("否");
            }
            exportSpecialNursingVOList.add(exportSpecialNursingVO);
        }
        String fileName = "特级护理.xlsx";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try(OutputStream out = response.getOutputStream()){
            EasyExcel.write(out,ExportSpecialNursingVO.class).excelType(ExcelTypeEnum.XLSX).sheet().doWrite(exportSpecialNursingVOList);
        }catch (Exception e){
            log.error("特级护理excel导出失败",e.getMessage());
            throw ResponseEnum.EXPORT_EXCEL_ERROR.newException();
        }
    }
}
