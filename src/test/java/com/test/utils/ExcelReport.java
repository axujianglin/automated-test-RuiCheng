package com.test.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class ExcelReport{
    public static void main(String[] args) {

    }


    /**
     * @param packageName 包名
     * @param className 类名
     * @param methodName 方法名
     * @param remark 备注
     * @param result 结果
     * @param reason 原因
     */
    public static void writeExcel(String packageName,String className,String methodName,String remark,String result,String reason){
        String path = "D:\\idea project\\WebDriver-projects\\06_framework\\src\\test\\java\\com\\test\\datas\\TestResult.xlsx";
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path));
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNums = sheet.getLastRowNum();
            int rowCurrentNums = rowNums + 1;
            sheet.createRow(rowCurrentNums);
            XSSFRow row = sheet.getRow(rowCurrentNums);
            CellStyle cellStyle2 =workbook.createCellStyle();
            cellStyle2.setFillForegroundColor(IndexedColors.RED.getIndex()); // 前景色
            cellStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle2.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框

            CellStyle cellStyle3 =workbook.createCellStyle();
            cellStyle3.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex()); // 前景色
            cellStyle3.setFillPattern(CellStyle.SOLID_FOREGROUND);
            cellStyle3.setBorderBottom(CellStyle.BORDER_THIN); // 底部边框
            if(row != null){
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String currentTime = dateFormat.format(now);
                //创建单元格并赋值
                row.createCell(0).setCellValue(currentTime);
                row.createCell(1).setCellValue(packageName);
                row.createCell(2).setCellValue(className);
                row.createCell(3).setCellValue(methodName);
                row.createCell(4).setCellValue(remark);
                row.createCell(5).setCellValue(result);
                if("fail".equals(result)){
                    row.getCell(5).setCellStyle(cellStyle2);
                }
                else if("pass".equals(result)){
                    row.getCell(5).setCellStyle(cellStyle3);
                }
                row.createCell(6).setCellValue(reason);
            }else {
                System.out.println("行为空");
            }
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



