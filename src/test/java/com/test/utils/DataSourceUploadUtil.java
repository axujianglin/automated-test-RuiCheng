package com.test.utils;


import com.proxyTest.BrowerProxyUtils;
import com.test.commans.DataSourceImportCommon;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;

import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING;

public class DataSourceUploadUtil {
    static String firstName;
    public static List<List<String>> getInfoByExcel(String filePath, String fileName, String sheetName, int i) throws IOException, InterruptedException {
        List<String> dataObjectList = new ArrayList<>();
        List<String> chooseFileList = new ArrayList<>();
        List<String> organizitionList = new ArrayList<>();
        List<List<String>> lists = new ArrayList<>();
        File file = new File(filePath+"\\"+fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);
        //对数据对象进行赋值
        Row rowDataObject = sheet.getRow(i);
        //对文件进行赋值
        Row rowChooseFile = sheet.getRow(i+2);
        //对关联组织进行赋值
        Row rowOrganization = sheet.getRow(i+4);
        rowDataObject.getCell(0).setCellType(CELL_TYPE_STRING);
        if(rowDataObject.getCell(0).getStringCellValue().equals("模型")){
            for(int j = 0;j<4;j++){
                rowDataObject.getCell(j).setCellType(CELL_TYPE_STRING);
                dataObjectList.add(rowDataObject.getCell(j).getStringCellValue());
                chooseFileList.add(rowChooseFile.getCell(j).getStringCellValue());
            }
        }
        else {
            int b = 10;
            if (i == 8){
                b = 16;
            }
            for(int j = 0;j<b;j++){
                    rowDataObject.getCell(j).setCellType(CELL_TYPE_STRING);
                    rowChooseFile.getCell(j).setCellType(CELL_TYPE_STRING);
                    rowOrganization.getCell(j).setCellType(CELL_TYPE_STRING);
                    dataObjectList.add(rowDataObject.getCell(j).getStringCellValue());
                    chooseFileList.add(rowChooseFile.getCell(j).getStringCellValue());
                    organizitionList.add(rowOrganization.getCell(j).getStringCellValue());
            }
        }
        lists.add(dataObjectList);
        lists.add(chooseFileList);
        lists.add(organizitionList);
        return lists;
    }
    //GIS数据的文件上传
    public static void dataUploadGis(List<List<String>> lists, WebDriver driver, int a,List<List<String>> infoLists) throws InterruptedException{
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //设置当数据格式为shape file时，有16个数据对象
        int b = 10;
        if(a==1){
            b = 16;
        }
        for(int i=0;i<b;i++){
            if(lists.get(1).get(i).equals("")){
                continue;
            }
            switch (a){
                case 0:
                    firstName = "jeoJson";
                    break;
                case 1:
                    firstName = "shapeFile";
                    break;
                case 2:
                    firstName = "csvFile";
                    break;
                case 3:
                    firstName = "arcJson";
                    break;
                case 4:
                    firstName = "tiff";
                    break;
            }
            //确定数据源为文件
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[@class='top_btn']")).click();
            /*临时代码*/
            driver.findElements(By.xpath("//i[@class='iconfont icon-guanbi']")).get(0).click();
            driver.findElement(By.xpath("//div[@class='top_btn']")).click();


            Thread.sleep(1000);
            /*driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys("好的");
            driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.UP);
            driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.ENTER);*/
            driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys(firstName+lists.get(0).get(i)+"测试文件an");
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
            driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li")).get(1).click();
            //确定数据类型为GIS
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
            Thread.sleep(1000);
            driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li")).get(0).click();
            //确定数据格式
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(2).click();
            Thread.sleep(1000);
            driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li")).get(a).click();
            //对数据对象和文件进行操作
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(3).click();
            List<WebElement> listSelect = driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li"));
            for (WebElement list:listSelect){
                if(lists.get(0).get(i).equals(list.getText())){
                    list.click();
                    break;
                }
            }
            driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(lists.get(1).get(i));
            Thread.sleep(2000);
            driver.findElements(By.xpath("//span[@class='button login_btn']")).get(0).click();
            Thread.sleep(1000);
            if(infoLists.get(i).get(2).equals(lists.get(0).get(i))){
                if(firstName.equals("tiff")){
                    return;
                }
                Thread.sleep(1000);
                //chooseOrganization(driver,lists,i);
                DataSourceImportCommon.judgementType(i,driver,infoLists);
            }
        }
    }

    //选择组织机构
    public static void chooseOrganization(WebDriver driver,List<List<String>> lists,int x){
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(1).click();
        driver.findElement(By.xpath("//div[@class='organization']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//label[@class='vue-treeselect__label']"));
        List<WebElement> elements1 = driver.findElements(By.xpath("//span[@class='vue-treeselect__checkbox vue-treeselect__checkbox--unchecked']"));
        for(int i=0;i<48;i++){
            if(elements.get(i).getText().equals(lists.get(2).get(x))){
                elements1.get(i).click();
                driver.findElement(By.xpath("//button[@class='el-button el-button--primary']/span")).click();
                return;
            }
        }
    }
    //三维模型数据的上传
    public static void dataUploadModel(List<List<String>> lists, WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        for(int i=0;i<10;i++){
            if(lists.get(1).get(i).equals("")){
                continue;
            }
            //确定数据源为文件
            driver.findElement(By.xpath("//div[@class='top_btn']")).click();
            driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys(lists.get(0).get(i)+"测试文件1");
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
            driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li")).get(1).click();
            //确定数据类型为三维模型数据
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
            Thread.sleep(1000);
            driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li")).get(0).click();
            //对数据对象和文件进行操作
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(3).click();
            List<WebElement> listSelect = driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li"));
            for (WebElement list:listSelect) {
                if(lists.get(0).get(i).equals(list.getText())){
                    list.click();
                    break;
                }
            }
            Thread.sleep(1000);
            WebElement element = driver.findElement(By.xpath("//input[@class='el-upload__input']"));
            Thread.sleep(2000);
            element.sendKeys(lists.get(1).get(i));
            driver.findElements(By.xpath("//span[@class='button login_btn']")).get(0).click();
            //发布3dtiles文件
            driver.findElements(By.xpath("//i[@class='el-icon-s-promotion']")).get(0).click();
            driver.findElements(By.xpath("//textarea")).get(1).sendKeys("张三描述");
            driver.findElement(By.xpath("//div[@class='el-form-item']/div/label/span/span")).click();
            driver.findElements(By.xpath("//span[@class='button login_btn']")).get(1).click();


        }
    }
}
