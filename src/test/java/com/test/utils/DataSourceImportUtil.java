package com.test.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class DataSourceImportUtil {
    //读取excel中的数据
    public static List<List<String>> getInfo(String filePath, String fileName, String sheetName,int x) throws IOException, InterruptedException {
        List<String> importInfo;
        List<List<String>> importInfos = new ArrayList<>();
        File file = new File(filePath + "\\" + fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);
        //设置当数据格式为shape file时，有16个数据对象
        int b = 10;
        if(x==2){
            b = 16;
        }
        for(int i=0;i<b;i++){
            importInfo = new ArrayList<>();
            Row row = sheet.getRow(i*3+1);
            /*if(row.getCell(0).getStringCellValue().equals("null")){
                importInfo.add("null");
            }*/
            int nums = row.getLastCellNum();
            for (int j = 0; j < nums; j++) {
                row.getCell(j).setCellType(CELL_TYPE_STRING);
                importInfo.add(row.getCell(j).getStringCellValue());
            }
            importInfos.add(importInfo);
        }

        return importInfos;
    }
    //导入数据
    public static void Import(WebDriver driver, List<List<String>> infoLists,int x) throws  InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.findElements(By.xpath("//i[@class='el-icon-setting']")).get(0).click();
        //编码
        Thread.sleep(2000);
        String aClass = driver.findElements(By.xpath("//div[@class='left_div']/label")).get(0).getAttribute("class");
        System.out.println(aClass);
        if(aClass.equals("el-checkbox")){
            driver.findElements(By.xpath("//div[@class='left_div']/label/span[1]")).get(0).click();
            List<WebElement> list = getSelectValue(6, driver);
            for (WebElement listExample:list) {
                if (infoLists.get(x).get(0).equals(listExample.getText())) {
                    listExample.click();
                    break;
                }
            }
        }
        String aClass1 = driver.findElements(By.xpath("//div[@class='left_div']/label")).get(2).getAttribute("class");
        if(aClass1.equals("el-checkbox")){
            //目标坐标系
            driver.findElements(By.xpath("//i[@class='iconfont icon-diqiu']")).get(0).click();
            driver.findElement(By.xpath("//div[@class='coordinate_bottom']/span")).click();
        }
        String aClass2 = driver.findElements(By.xpath("//div[@class='left_div']/label")).get(3).getAttribute("class");
        if(aClass2.equals("el-checkbox")){
            //替换数据
            Thread.sleep(1000);
            driver.findElements(By.xpath("//div[@class='left_div']/label/span[1]")).get(3).click();
        }
        String aClass3 = driver.findElements(By.xpath("//div[@class='left_div']/label")).get(4).getAttribute("class");
        if(aClass3.equals("el-checkbox")){
            driver.findElements(By.xpath("//div[@class='left_div']/label/span[1]")).get(4).click();
        }

    }
    //匹配下拉框的值
    public static void find(int index, WebDriver driver,List<List<String>> infoLists,int a) {
        List<WebElement> lists = getSelectValue(index,driver);
        for (WebElement list:lists) {
            System.out.println("从下拉框中获取到的值为："+list.getText());
            System.out.println("excel表格中的值"+infoLists.get(a).get(index-8));
            if (infoLists.get(a).get(index-8).equals(list.getText())) {
                list.click();
                break;
            }
        }
    }
    //匹配特殊下拉框的值
    public static void findTwo(int index, WebDriver driver,List<List<String>> infoLists) {
        List<WebElement> lists = getSelectValue(index,driver);
        for (WebElement list:lists) {
            if (infoLists.get(4).get(index-3).equals(list.getText())) {
                list.click();
                break;
            }
        }
    }
    //获取下拉框的值
    public static List<WebElement> getSelectValue(int index, WebDriver driver) {
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(index).click();
        return driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li"));
    }
}
