package com.test.utils;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class VersionManageUtil {

    public static List<List<String>> getInfo(String filePath, String fileName, String sheetName) throws IOException {
        List<List<String>> infoList = new ArrayList<>();
        List<String> addVersionList = new ArrayList<>();
        List<String> addTwoList = new ArrayList<>();
        File file = new File(filePath+"\\"+fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);
        Row rowOne = sheet.getRow(1);
        for (int i=0;i<3;i++){
            rowOne.getCell(i).setCellType(CELL_TYPE_STRING);
            addVersionList.add(rowOne.getCell(i).getStringCellValue());
        }
        infoList.add(addVersionList);
        Row rowTwo = sheet.getRow(4);
        for (int i=0;i<3;i++){
            rowTwo.getCell(i).getStringCellValue();
            addTwoList.add(rowTwo.getCell(i).getStringCellValue());
        }
        infoList.add(addTwoList);
        return infoList;
    }

    public static void addVersion(WebDriver driver,List<List<String>> info) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        for(int i =0;i<2;i++){
            //新建版本
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='top_btn']")).click();
            driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
            driver.findElements(By.xpath("//li[@class='el-select-dropdown__item']")).get(0).click();
            driver.findElement(By.xpath("//textarea")).sendKeys("张三测试");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(info.get(i).get(0));
            driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
            Thread.sleep(1000);
            driver.findElements(By.xpath("//i[@class='el-icon-view']")).get(0).click();

            for(int j=0;j<2;j++){
                Thread.sleep(1000);
                driver.findElement(By.xpath("//span[@class='numberadd']")).click();
                driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(info.get(i).get(j+1));
                driver.findElement(By.xpath("//textarea")).sendKeys("第"+(j+1)+"次新增迭代");
                driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
                Thread.sleep(10000);
                driver.navigate().refresh();
                driver.findElements(By.xpath("//span[@class='operation_btn blue']")).get(j+1).click();
                driver.findElement(By.xpath("//span[@class='current']")).click();
                Thread.sleep(1000);
                driver.navigate().refresh();

            }
            driver.findElement(By.xpath("//li[@class='el-menu-item is-active childClass childStyle']")).click();
        }

    }
}
