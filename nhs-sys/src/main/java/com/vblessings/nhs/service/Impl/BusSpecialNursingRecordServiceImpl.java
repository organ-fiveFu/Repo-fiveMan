package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
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
import com.vblessings.nhs.writeHandler.CustomCellWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
     * 特级护理信息头
     * @param bigTitle
     * @return
     */
    public   List<List<String>> getSpecialNursing(String bigTitle){
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add("姓名");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add("过敏史");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add("床号");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add("房间号");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add("时间");
        List<String> head5 = new ArrayList<>();
        head5.add(bigTitle);
        head5.add("晨间护理");
        List<String> head6 = new ArrayList<>();
        head6.add(bigTitle);
        head6.add("预防压疮护理");
        List<String> head7 = new ArrayList<>();
        head7.add(bigTitle);
        head7.add("晚间护理");
        List<String> head8 = new ArrayList<>();
        head8.add(bigTitle);
        head8.add("出量记录");
        List<String> head9 = new ArrayList<>();
        head9.add(bigTitle);
        head9.add("入量记录");
        List<String> head10 = new ArrayList<>();
        head10.add(bigTitle);
        head10.add("精神状态及其他");
        List<String> head11 = new ArrayList<>();
        head11.add(bigTitle);
        head11.add("责任人");
        List<String> head12 = new ArrayList<>();
        head12.add(bigTitle);
        head12.add("医院诊断");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        head.add(head5);head.add(head6);
        head.add(head7);
        head.add(head8);
        head.add(head9);head.add(head10);
        head.add(head11);head.add(head12);
        return head;
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
        //获取特级护理信息
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

        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("特级护理");

        String fileName = URLEncoder.encode(bigTitle.toString(), "UTF-8");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        // 头的策略

        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        headWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();

        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        contentWriteCellStyle.setBorderBottom(BorderStyle.THIN);
        contentWriteCellStyle.setBorderLeft(BorderStyle.THIN);
        contentWriteCellStyle.setBorderRight(BorderStyle.THIN);
        contentWriteCellStyle.setBorderTop(BorderStyle.THIN);
         OutputStream out = response.getOutputStream();
            EasyExcel.write(out,ExportSpecialNursingVO.class).excelType(ExcelTypeEnum.XLSX).head(getSpecialNursing(bigTitle.toString())).registerWriteHandler(new CustomCellWriteHandler()).sheet("特级护理").doWrite(exportSpecialNursingVOList);

    }
}
