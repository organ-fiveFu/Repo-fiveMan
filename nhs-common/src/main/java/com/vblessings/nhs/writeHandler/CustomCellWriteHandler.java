package com.vblessings.nhs.writeHandler;

import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCellWriteHandler implements CellWriteHandler {


    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {

    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
        Workbook workbook =writeSheetHolder.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        if(cell.getRowIndex() == 0){
            font.setFontHeightInPoints((short) 20);
            font.setFontName("宋体");
            font.setBold(true);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
           Row row = cell.getRow();
           row.setHeightInPoints(30);
        }
        if(cell.getRowIndex() == 1){
            font.setFontHeightInPoints((short)8 );
            font.setFontName("宋体");
            font.setBold(true);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setWrapText(true);
            Row row = cell.getRow();
            row.setHeightInPoints(26);

        }
        if(cell.getRowIndex() == 2){
            font.setFontHeightInPoints((short) 6);
            font.setFontName("宋体");
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            Row row = cell.getRow();
            row.setHeightInPoints(31);
        }
        if(cell.getRowIndex() > 2){
            font.setFontHeightInPoints((short) 8);
            font.setFontName("正楷");
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setWrapText(true);
            Row row = cell.getRow();
            row.setHeightInPoints(30);
        }
       cell.setCellStyle(cellStyle);

    }
}
