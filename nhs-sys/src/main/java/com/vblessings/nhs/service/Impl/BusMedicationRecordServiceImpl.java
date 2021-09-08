package com.vblessings.nhs.service.Impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vblessings.nhs.component.SnowflakeComponent;
import com.vblessings.nhs.mapper.BusMedicationRecordMapper;
import com.vblessings.nhs.model.entity.business.BusMedicationRecord;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryMedicineRecordPO;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.ExportDispensingVO;
import com.vblessings.nhs.model.vo.business.ExportMedicationRecordVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusMedicationRecordService;
import com.vblessings.nhs.util.OperateUtil;
import com.vblessings.nhs.writeHandler.CustomCellWriteHandler;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BusMedicationRecordServiceImpl implements BusMedicationRecordService {
    @Resource
    private BusMedicationRecordMapper busMedicationRecordMapper;

    @Resource
    private SnowflakeComponent snowflakeComponent;
    @Override
    public void add(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busMedicationRecord,userInfo,id);
        busMedicationRecordMapper.insert(busMedicationRecord);
    }

    @Override
    public void update(BusMedicationRecord busMedicationRecord, UserInfoToken userInfo) {
        busMedicationRecord.setUpdaterId(userInfo.getUserId());
        busMedicationRecord.setUpdateTime(new DateTime());
        busMedicationRecordMapper.updateByPrimaryKeySelective(busMedicationRecord);
    }

    @Override
    public PageVO<BusMedicationRecord> pageMedicationRecord(QueryMedicineRecordPO queryMedicineRecordPO) {
        Page<BusMedicationRecord> result = PageHelper.startPage(queryMedicineRecordPO.getPageNum(), queryMedicineRecordPO.getPageSize());
        Example example = new Example(BusMedicationRecord.class);
        Example.Criteria C = example.createCriteria();
        if(queryMedicineRecordPO.getName()!=null){
        C.andLike("name","%"+queryMedicineRecordPO.getName()+"%");}
        if(queryMedicineRecordPO.getBusinessNo()!=null){
            C.andEqualTo("businessNo",queryMedicineRecordPO.getBusinessNo());
        }
        C.andEqualTo("isDel",0);
        List<BusMedicationRecord> busMedicationRecordList = busMedicationRecordMapper.selectByExample(example);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busMedicationRecordList);
    }

    @Override
    public void delMedicationRecord(String ids) {
        String[] id = ids.split(",");
        busMedicationRecordMapper.del(id);
    }

    @Override
    public void exportMedicationRecord(String ids, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Example example = new Example(BusMedicationRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> id = Arrays.asList(ids.split(","));
        criteria.andEqualTo("isDel",0);
        criteria.andIn("id",id);
        List<BusMedicationRecord> busMedicationRecordList = busMedicationRecordMapper.selectByExample(example);
        BusMedicationRecord busMedicationRecord = busMedicationRecordList.get(0);
        List<ExportMedicationRecordVO> exportMedicationRecordVOList = new ArrayList<>();
        for (BusMedicationRecord busMedicationRecord1:
        busMedicationRecordList) {
            ExportMedicationRecordVO exportMedicationRecordVO = new ExportMedicationRecordVO();
            BeanUtils.copyProperties(busMedicationRecord1,exportMedicationRecordVO);
            exportMedicationRecordVO.setMedicationDate(sdf.format(busMedicationRecord1.getMedicationDate()));
            exportMedicationRecordVOList.add(exportMedicationRecordVO);
        }

        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("老人服药记录表");

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
        EasyExcel.write(out, ExportMedicationRecordVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .head(getMedicationRecord(bigTitle.toString(),busMedicationRecord))
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(14))
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("老人服药记录表").doWrite(exportMedicationRecordVOList);


    }
    public  List<List<String>> getMedicationRecord(String bigTitle, BusMedicationRecord busMedicationRecord){
        String secondTitle =" 床号:"+busMedicationRecord.getBedCode()+" 姓名:"+busMedicationRecord.getName()+
                " 病区:"+busMedicationRecord.getWard();
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add(secondTitle);
        head0.add("服药日期");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add(secondTitle);
        head1.add("药品规格");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add(secondTitle);
        head2.add("计量");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add(secondTitle);
        head3.add("频次");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add(secondTitle);
        head4.add("用药时间");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        return head;
    }

}
