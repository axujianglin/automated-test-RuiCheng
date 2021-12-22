package com.test.commans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GeoJsonCommon {
    public void dataUpload(List<List<String>> lists,WebDriver driver,int a) throws InterruptedException {
        for(int i=0;i<10;i++){
            //确定数据源为文件
            driver.findElement(By.xpath("//div[@class='top_btn']")).click();
            driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("测试文件");
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
            driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li")).get(1).click();
            //确定数据类型为GIS
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
            Thread.sleep(2000);
            driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li")).get(0).click();
            //确定数据格式
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(2).click();
            Thread.sleep(1000);
            driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li")).get(a).click();
            //对数据对象和文件进行操作
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(3).click();
            List<WebElement> listSelect = driver.findElements(By.xpath("//div[contains(@x-placement,'start')]/div/div/ul/li"));
            for (WebElement list:listSelect) {
                if(lists.get(0).get(i).equals(list.getText())){
                    list.click();
                    break;
                }
            }
            driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(lists.get(1).get(i));
            Thread.sleep(2000);
            driver.findElements(By.xpath("//span[@class='button login_btn']")).get(0).click();
            Thread.sleep(2000);
            String alertInfo = driver.findElement(By.xpath("//div[@class='el-message el-message--success']/p")).getText();
            if("数据源添加成功，文件已经上传成功！".equals(alertInfo)){
                System.out.println("文件上传成功");
            }
            else {
                System.out.println("文件上传失败");
            }
        }
    }
}
