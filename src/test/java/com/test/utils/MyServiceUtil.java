package com.test.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class MyServiceUtil {
    public static List<List<String>> getInfo(String filePath, String fileName, String sheetName,int a) throws IOException {

        List<List<String>> lists = new ArrayList<>();
        int x = 1;
        File file = new File(filePath+"\\"+fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);

        for (int i =0;i<5;i++){
            List<String> list = new ArrayList<>();
            for (int j=0;j<a;j++){
                Row row = sheet.getRow(x);
                row.getCell(j).setCellType(CELL_TYPE_STRING);
                list.add(row.getCell(j).getStringCellValue());
            }
            lists.add(list);
            x+=3;
        }

        return lists;
    }


}
