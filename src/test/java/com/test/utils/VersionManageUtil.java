package com.test.utils;

import org.apache.poi.ss.formula.functions.T;
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

public class VersionManageUtil {

    public static List<String> getInfo(String filePath, String fileName, String sheetName) throws IOException {
        List<String> addVersionList = new ArrayList<>();
        File file = new File(filePath+"\\"+fileName);
        FileInputStream in = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheet(sheetName);
        Row rowOne = sheet.getRow(1);
        for (int i=0;i<3;i++){
            rowOne.getCell(i).setCellType(CELL_TYPE_STRING);
            addVersionList.add(rowOne.getCell(i).getStringCellValue());
        }

        return addVersionList;
    }

    public static void addVersion(WebDriver driver,List<String> info) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//li[@class='el-select-dropdown__item']")).get(0).click();
        driver.findElement(By.xpath("//textarea")).sendKeys("张三文件测试");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(info.get(0));
        driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("//i[@class='el-icon-view']")).get(0).click();
        for(int j=0;j<2;j++){
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@class='numberadd']")).click();
            driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(info.get(j+1));
            driver.findElement(By.xpath("//textarea")).sendKeys("第"+(j+1)+"次新增迭代");
            driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
            Thread.sleep(2000);
        }
        driver.findElement(By.xpath("//i[@class='el-icon-sort']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='uploadfile_count']/div"));
        if(elements.size() == 3){
            System.out.println("文件上传列表个数正确");
            for(int i = 0;i<10;i++){
                Thread.sleep(120000);
                List<WebElement> elements1 = driver.findElements(By.xpath("//div[@class='uploadfile_count']/div"));
                if(elements1 == null){
                    System.out.println("文件上传列表清空，所有文件上传成功");
                    driver.findElement(By.xpath("//i[@class='iconfont icon-guanbi']")).click();
                    driver.findElements(By.xpath("//span[@class='operation_btn blue']")).get(0).click();
                }
            }
        }else {
            System.out.println("文件上传列表个数不正确");
        }
    }
}
