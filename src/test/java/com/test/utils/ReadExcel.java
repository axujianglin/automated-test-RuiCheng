package com.test.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class ReadExcel {
    /**从excel表中读取数据的方法
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param sheetName 表名
     * @return 数据的二维数组
     *
     */
    public static Object[][] getData(String filePath,String fileName,String sheetName) throws IOException {
        File file = new File(filePath+"\\"+fileName);
        FileInputStream in = new FileInputStream(file);
        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if(fileExtensionName.equals(".xlsx")){
            workbook = new XSSFWorkbook(in);
        }
        else if (fileExtensionName.equals(".xls")){
            workbook = new HSSFWorkbook(in);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        List<Object[]> records = new ArrayList<>();
        for(int i=1;i<rowCount+1;i++){
            Row row = sheet.getRow(i);
            //声明一个数组，用来存储Excel数据文件每行中的数据，数组的大小用getLastCellNum办法来进行动态声明，实现测试数据个数和数组大小相一致
            String[] fields = new String[row.getLastCellNum()];
            for(int j=0;j<row.getLastCellNum();j++){
                //调用getCell和getStringCellValue方法获取Excel文件中的单元格数据
                row.getCell(j).setCellType(CELL_TYPE_STRING);
                fields[j] = row.getCell(j).getStringCellValue();
            }
            //将fields的数据兑现存储到records的list中
            records.add(fields);
        }
        //定义函数返回值，即Object[][]
        //将存储测试数据的list转换为一个Object的二维数组
        Object[][] results = new Object[records.size()][];
        for (int i =0;i< records.size();i++){
            results[i] = records.get(i);
        }
        return results;

    }

}

