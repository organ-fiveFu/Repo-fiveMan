package com.vblessings.nhs.writeHandler;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

public class BloodSugarCellWriteHandler implements CellWriteHandler {
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
            font.setFontHeightInPoints((short)30);
            font.setFontName("宋体");
            font.setBold(true);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cellStyle.setWrapText(true);
            Row row = cell.getRow();
            row.setHeightInPoints(30);
        }
        if(cell.getRowIndex() == 1){
            font.setFontHeightInPoints((short)16);
            font.setFontName("宋体");
            font.setBold(true);
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cellStyle.setWrapText(true);
            Row row = cell.getRow();
            row.setHeightInPoints(26);

        }
        if(cell.getRowIndex() == 2){
            font.setFontHeightInPoints((short)12);
            font.setFontName("宋体");
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            Row row = cell.getRow();
            row.setHeightInPoints(26);
        }
        if(cell.getRowIndex() > 2){
            font.setFontHeightInPoints((short)10);
            font.setFontName("正楷");
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            Row row = cell.getRow();
            row.setHeightInPoints(26);
        }
        cell.setCellStyle(cellStyle);

    }
}
