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
import com.vblessings.nhs.mapper.BusTakeMedicineRecordMapper;
import com.vblessings.nhs.model.entity.business.BusSpecialNursingRecord;
import com.vblessings.nhs.model.entity.business.BusTakeMedicineRecord;
import com.vblessings.nhs.model.po.business.QueryTakeMedicineRecord;
import com.vblessings.nhs.model.vo.PageVO;
import com.vblessings.nhs.model.vo.business.ExportDispensingVO;
import com.vblessings.nhs.model.vo.business.ExportTakeMedicineVO;
import com.vblessings.nhs.model.vo.nurse.ExportSpecialNursingVO;
import com.vblessings.nhs.result.UserInfoToken;
import com.vblessings.nhs.service.BusTakeMedicineRecordService;
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
import java.util.List;

@Service
public class BusTakeMedicineRecordServiceImpl implements BusTakeMedicineRecordService {
    @Resource
    private SnowflakeComponent snowflakeComponent;

    @Resource
    private BusTakeMedicineRecordMapper busTakeMedicineRecordMapper;


    @Override
    public void add(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo) {
        Long id = snowflakeComponent.getInstance().nextId();
        OperateUtil.onSaveNew(busTakeMedicineRecord,userInfo,id);
        busTakeMedicineRecordMapper.insert(busTakeMedicineRecord);
    }

    @Override
    public void update(BusTakeMedicineRecord busTakeMedicineRecord, UserInfoToken userInfo) {
        busTakeMedicineRecord.setUpdaterId(userInfo.getUserId());
        busTakeMedicineRecord.setUpdateTime(new DateTime());
        busTakeMedicineRecordMapper.updateByPrimaryKeySelective(busTakeMedicineRecord);
    }

    @Override
    public PageVO<BusTakeMedicineRecord> pageTakeMedicine(QueryTakeMedicineRecord queryTakeMedicineRecord) {
        Page<BusTakeMedicineRecord> result = PageHelper.startPage(queryTakeMedicineRecord.getPageNum(), queryTakeMedicineRecord.getPageSize());
        List<BusTakeMedicineRecord> busTakeMedicineRecordList = busTakeMedicineRecordMapper.selectByTime(queryTakeMedicineRecord);
        return new PageVO<>(result.getPageNum(), result.getPageSize(), result.getTotal(), result.getPages(), busTakeMedicineRecordList);
    }

    @Override
    public void delTakeMedicine(String ids) {
        String[] id = ids.split(",");
        busTakeMedicineRecordMapper.del(id);
    }

    /**
     * 自带药信息头
     * @param bigTitle
     * @return
     */
    public  List<List<String>> getTakeMedicine(String bigTitle, BusTakeMedicineRecord busTakeMedicineRecord){
        String sex = "";
        if (busTakeMedicineRecord.getSex()!=null){
            switch(busTakeMedicineRecord.getSex())
            {
                case "1" :
                    sex="男";
                    break;
                case "2" :
                    sex="女";
                case "0" :
                    sex="未知的性别";
                    break;
                default :
                    sex="未说明的性别";
            }
        }
        String secondTitle ="病区:"+busTakeMedicineRecord.getWard()+" 床号:"+busTakeMedicineRecord.getBedCode()+" 姓名:"+busTakeMedicineRecord.getName()+
                " 性别:"+sex+" 年龄:"+busTakeMedicineRecord.getName()+" 诊断:"+busTakeMedicineRecord.getHospitalDiagnosis();
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add(secondTitle);
        head0.add("日期");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add(secondTitle);
        head1.add("药品名称");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add(secondTitle);
        head3.add("数量");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add(secondTitle);
        head4.add("有效期");
        List<String> head5 = new ArrayList<>();
        head5.add(bigTitle);
        head5.add(secondTitle);
        head5.add("家属签名");
        List<String> head6 = new ArrayList<>();
        head6.add(bigTitle);
        head6.add(secondTitle);
        head6.add("护士签名");
        head.add(head0);
        head.add(head1);
        head.add(head3);
        head.add(head4);
        head.add(head5);
        head.add(head6);
        return head;
    }
    @Override
    public void exportTakeMedicine(String ids, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Example example = new Example(BusTakeMedicineRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> id = Arrays.asList(ids.split(","));
        criteria.andEqualTo("isDel",0).andEqualTo("isTaken",0);
        criteria.andIn("id",id);
        List<BusTakeMedicineRecord> busTakeMedicineRecordList = busTakeMedicineRecordMapper.selectByExample(example);
        BusTakeMedicineRecord busTakeMedicineRecord1 = busTakeMedicineRecordList.get(0);
        List<ExportTakeMedicineVO> exportTakeMedicineVOList = new ArrayList<>();
        if(busTakeMedicineRecordList!=null && busTakeMedicineRecordList.size()>0) {
            for (BusTakeMedicineRecord busTakeMedicineRecord :
                    busTakeMedicineRecordList) {
                ExportTakeMedicineVO exportTakeMedicineVO = new ExportTakeMedicineVO();
                BeanUtils.copyProperties(busTakeMedicineRecord, exportTakeMedicineVO);
                if(busTakeMedicineRecord.getTakeMedicineDate()!=null){
                    exportTakeMedicineVO.setTakeMedicineDate(sdf.format(busTakeMedicineRecord.getTakeMedicineDate()));
                }
                if(busTakeMedicineRecord.getExpiryDate()!=null){
                    exportTakeMedicineVO.setExpiryDate(sdf.format(busTakeMedicineRecord.getExpiryDate()));
                }

                    exportTakeMedicineVOList.add(exportTakeMedicineVO);

            }
        }
        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("老人自带药品记录表");

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
        EasyExcel.write(out, ExportSpecialNursingVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .head(getTakeMedicine(bigTitle.toString(),busTakeMedicineRecord1))
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(10))
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("老人自带药品记录表").doWrite(exportTakeMedicineVOList);

    }

    @Override
    public void exportDispensing(String ids, HttpServletResponse response) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Example example = new Example(BusTakeMedicineRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<String> id = Arrays.asList(ids.split(","));
        criteria.andEqualTo("isDel",0).andEqualTo("isTaken",1);
        criteria.andIn("id",id);
        List<BusTakeMedicineRecord> busTakeMedicineRecordList = busTakeMedicineRecordMapper.selectByExample(example);
        BusTakeMedicineRecord busTakeMedicineRecord1 = busTakeMedicineRecordList.get(0);
        List<ExportDispensingVO> exportDispensingVOList = new ArrayList<>();
        if(busTakeMedicineRecordList!=null && busTakeMedicineRecordList.size()>0) {
            for (BusTakeMedicineRecord busTakeMedicineRecord :
                    busTakeMedicineRecordList) {
                ExportDispensingVO exportDispensingVO = new ExportDispensingVO();
                BeanUtils.copyProperties(busTakeMedicineRecord, exportDispensingVO);
                if(busTakeMedicineRecord.getTakeMedicineDate()!=null){
                    exportDispensingVO.setTakeMedicineDate(sdf.format(busTakeMedicineRecord.getTakeMedicineDate()));
                }
                exportDispensingVOList.add(exportDispensingVO);

            }
        }
        //头信息
        StringBuffer bigTitle = new StringBuffer();
        bigTitle.append("老人配药记录表");

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
        EasyExcel.write(out, ExportDispensingVO.class)
                .excelType(ExcelTypeEnum.XLSX)
                .head(getTakeDispensing(bigTitle.toString(),busTakeMedicineRecord1))
                .registerWriteHandler(horizontalCellStyleStrategy)
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(14))
                .registerWriteHandler(new CustomCellWriteHandler())
                .sheet("老人配药记录表").doWrite(exportDispensingVOList);


    }
    public  List<List<String>> getTakeDispensing(String bigTitle, BusTakeMedicineRecord busTakeMedicineRecord){
        String sex = "";
        if (busTakeMedicineRecord.getSex()!=null){
            switch(busTakeMedicineRecord.getSex())
            {
                case "1" :
                    sex="男";
                    break;
                case "2" :
                    sex="女";
                case "0" :
                    sex="未知的性别";
                    break;
                default :
                    sex="未说明的性别";
            }
        }
        String secondTitle ="病区:"+busTakeMedicineRecord.getWard()+" 床号:"+busTakeMedicineRecord.getBedCode()+" 姓名:"+busTakeMedicineRecord.getName()+
                " 性别:"+sex+" 年龄:"+busTakeMedicineRecord.getName()+" 诊断:"+busTakeMedicineRecord.getHospitalDiagnosis();
        List<List<String>> head = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<>();
        head0.add(bigTitle);
        head0.add(secondTitle);
        head0.add("日期");
        List<String> head1 = new ArrayList<>();
        head1.add(bigTitle);
        head1.add(secondTitle);
        head1.add("药品名称及规格");
        List<String> head2 = new ArrayList<>();
        head2.add(bigTitle);
        head2.add(secondTitle);
        head2.add("用法");
        List<String> head3 = new ArrayList<>();
        head3.add(bigTitle);
        head3.add(secondTitle);
        head3.add("用量");
        List<String> head4 = new ArrayList<>();
        head4.add(bigTitle);
        head4.add(secondTitle);
        head4.add("备注");
        head.add(head0);
        head.add(head1);
        head.add(head2);
        head.add(head3);
        head.add(head4);
        return head;
    }


}
