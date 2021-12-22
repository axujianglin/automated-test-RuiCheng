package com.test.commans;

import com.test.utils.DataSourceImportUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataSourceImportCommon {

    public static void judgementType(int a,WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        Thread.sleep(2000);
        String methodName="Import"+a;
        Class<DataSourceImportCommon> formatter=DataSourceImportCommon.class;
        DataSourceImportCommon common = new DataSourceImportCommon();
        Method cMethod;
        try {
            cMethod = formatter.getMethod(methodName,WebDriver.class, List.class);
            cMethod.invoke(common, driver,infoLists);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    /*行政区划导入3*/
    public void Import3(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        //共同步骤操作
        DataSourceImportUtil.Import(driver,infoLists,3);
        //下拉框匹配
        for(int i=9;i<=19;i++){
            if(i == 9){
                if(infoLists.get(3).get(1).equals("高程服务")){
                    driver.findElement(By.xpath("//*[@id=\"attribute_top\"]/div[5]/div/div/span/span/i")).click();
                    driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li")).get(1).click();
                    driver.findElement(By.xpath("//*[@id=\"attribute_top\"]/div[6]/span[3]")).click();
                    driver.findElement(By.xpath("//*[@id=\"attribute_top\"]/div[7]/input")).sendKeys(infoLists.get(3).get(12));
                }
                else if(infoLists.get(3).get(1).equals("数据自带高程")){
                    driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(9).click();
                    WebElement element = driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li"));
                    element.click();
                    driver.findElement(By.xpath("//span[@class='deviation']")).click();
                    driver.findElement(By.xpath("//input[@class='constant_input']")).sendKeys(infoLists.get(3).get(12));
                }
            }
            if(i!=15 & i!=18){
                DataSourceImportUtil.find(i,driver,infoLists,3);
            }

        }
        driver.findElement(By.xpath("//input[@class='rule_input']")).sendKeys(infoLists.get(3).get(13));
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(6000);
        String info = driver.findElements(By.xpath("//div[@class='el-table__body-wrapper is-scrolling-none']/table/tbody/tr/td[9]/div")).get(0).getText();
        if("导入中".equals(info)){
            Thread.sleep(6000);
            String info1 = driver.findElements(By.xpath("//div[@class='el-table__body-wrapper is-scrolling-none']/table/tbody/tr/td[9]/div")).get(0).getText();
            if("导入中".equals(info1)){
                return;
            }
        }
        else {

            publicServer(driver);
            Thread.sleep(2000);
        }

    }

    /*房屋面导入4*/
    public  void  Import4(WebDriver driver, List<List<String>> infoLists) throws InterruptedException {
        //共同步骤操作
        DataSourceImportUtil.Import(driver,infoLists,4);
        //下拉框匹配
        for(int i=9;i<19;i++){
            if(i ==17){
                continue;
            }
            DataSourceImportUtil.find(i,driver,infoLists,4);
        }
        driver.findElement(By.xpath("//*[@id=\"attribute_bottom\"]/div[3]/div[1]/span[2]")).click();
        for(int i = 15;i<19;i++){
            DataSourceImportUtil.findTwo(i,driver,infoLists);
        }
        driver.findElements(By.xpath("//input[@class='constant_input']")).get(0).sendKeys(infoLists.get(4).get(16));
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(2000);
        publicServer(driver);
        Thread.sleep(2000);
    }

    /*河流导入5*/
    public  void Import5(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,5);
        for(int i=9;i<14;i++){
            DataSourceImportUtil.find(i,driver,infoLists,5);
        }
        driver.findElement(By.xpath("//*[@id=\"attribute_top\"]/div[6]/input")).sendKeys(infoLists.get(5).get(7));
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(2000);
        publicServer(driver);
        Thread.sleep(2000);
    }

    /*道路导入6*/
    public  void Import6(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,6);
        for(int i=9;i<20;i++){
            if(i ==17){
                continue;
            }
            DataSourceImportUtil.find(i,driver,infoLists,6);
        }
        driver.findElement(By.xpath("//*[@id=\"attribute_top\"]/div[6]/input")).sendKeys(infoLists.get(6).get(12));
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(2000);
        publicServer(driver);
        Thread.sleep(2000);
    }

    /*管线导入7*/
    public  void Import7(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,7);
        for(int i=9;i<19;i++){
            if(i==15 |i ==17){
                continue;
            }
            DataSourceImportUtil.find(i,driver,infoLists,7);
        }
        driver.findElements(By.xpath("//input[@class='constant_input']")).get(0).sendKeys(infoLists.get(7).get(11));
        driver.findElements(By.xpath("//input[@class='rule_input rule_inputs']")).get(0).sendKeys(infoLists.get(7).get(12));
        driver.findElements(By.xpath("//input[@class='rule_input rule_inputs']")).get(1).sendKeys(infoLists.get(7).get(13));
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);


    }
    /*兴趣点导入8*/
    public  void Import8(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,8);
        for(int i=9;i<15;i++){
            DataSourceImportUtil.find(i,driver,infoLists,8);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(2000);
        publicServerTwo(driver);
        Thread.sleep(2000);
    }

    /*站列导入10*/
    public  void Import10(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,10);
        for (int i=9;i<15;i++){
            if (i==12 | i==14){
                continue;
            }
            DataSourceImportUtil.find(i,driver,infoLists,10);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*钢管导入11*/
    public void Import11(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,11);
        for (int i=9;i<29;i++){
            if (i > 11 & i % 2 ==0) {
                continue;
            }
            System.out.println(infoLists.get(11).get(i - 8));
            DataSourceImportUtil.find(i, driver, infoLists, 11);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*弯管导入12*/
    public void Import12(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,12);
        for (int i=9;i<40;i++){
            if (i > 11 & i % 2 ==0) {
                continue;
            }
            DataSourceImportUtil.find(i, driver, infoLists, 12);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*焊缝导入13*/
    public void Import13(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,13);
        for (int i=9;i<29;i++){
            if (i > 11 & i % 2 ==0) {
                continue;
            }
            DataSourceImportUtil.find(i, driver, infoLists, 13);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*套管导入14*/
    public void Import14(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,14);
        for (int i=9;i<19;i++){
            if (i > 11 & i % 2 ==0) {
                continue;
            }
            DataSourceImportUtil.find(i, driver, infoLists, 14);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*场内管线15*/
    public void Import15(WebDriver driver,List<List<String>> infoLists) throws InterruptedException {
        DataSourceImportUtil.Import(driver,infoLists,15);
        for (int i=9;i<19;i++){
            if (i > 11 & i % 2 ==0) {
                continue;
            }
            DataSourceImportUtil.find(i, driver, infoLists, 15);
        }
        driver.findElements(By.xpath("//span[@class='cspan']")).get(0).click();
        Thread.sleep(1000);
        //publicServer(driver);
        Thread.sleep(2000);
    }

    /*发布WMS服务,driver.findElement(By.xpath("")).click();   */
    public static void publicServer(WebDriver driver) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.findElements(By.xpath("//i[@class='el-icon-s-promotion']")).get(0).click();
        Thread.sleep(2000);
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(4).click();
        driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li")).get(0).click();
        driver.findElements(By.xpath("//textarea")).get(1).sendKeys("描述");
        driver.findElement(By.xpath("//div[@class='el-form-item']/div/label/span[1]")).click();
        driver.findElements(By.xpath("//span[@class='button login_btn']")).get(1).click();
        Thread.sleep(3000);
        driver.findElements(By.xpath("//li[@class='el-menu-item']")).get(1).click();
        Thread.sleep(4000);

    }



    /*发布WMTS服务*/
    public static void publicServerTwo(WebDriver driver) throws InterruptedException {
        driver.findElements(By.xpath("//i[@class='el-icon-s-promotion']")).get(0).click();
        driver.findElement(By.xpath("//div[@class='el-input']/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class='el-form-item is-success is-required']/div/div/div/span")).click();
        driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li")).get(3).click();
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(5).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li")).click();

        driver.findElement(By.xpath("//i[@class='el-input__icon el-icon-arrow-down']")).click();
        driver.findElements(By.xpath("//span[@class='el-cascader-node__label']")).get(0).click();
        driver.findElements(By.xpath("//span[@class='el-cascader-node__label']")).get(2).click();
        Actions action = new Actions(driver);
        action.clickAndHold(driver.findElements(By.xpath("//div[@class='el-tooltip el-slider__button']")).get(1))
                .moveToElement(driver.findElements(By.xpath("//div[@class='el-form-item__content']/ul/li")).get(8)).perform();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[3]/div/div[2]/form/div[6]/div/textarea")).sendKeys("描述");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[3]/div/div[2]/form/div[7]/div/label/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[3]/div/div[2]/form/div[8]/div/span[2]")).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("//li[@class='el-menu-item']")).get(1).click();
        Thread.sleep(1000);
    }


}
