package com.test.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class LoginUtil {
    public static void getInfo(String filePath, String fileName, String sheetName, Map<String,String> loginData) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(1);
        loginData.put("userName",row.getCell(0).getStringCellValue());
        loginData.put("password",row.getCell(1).getStringCellValue());
    }
}
