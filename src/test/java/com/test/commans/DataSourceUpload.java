package com.test.commans;/*
package com.test.commans;

import com.test.entity.DataSourceUploadEntity;
import com.test.utils.DataSourceUploadUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeUnit;

//用来封装具体的xpath操作
public class DataSourceUpload{

    */
/*数据源文件上传*//*

    public void uploadFiles(WebDriver driver) throws  IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        DataSourceUploadEntity entity = DataSourceUploadUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceUpload.xlsx","Sheet1");
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("测试文件");

        */
/*DataSourceUploadEntity entity = DataSourceUploadUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceUpload.xlsx","Sheet1");
        List<String> dataList = new ArrayList<>();
        dataList.add(entity.getDataSourceFormat());
        dataList.add(entity.getDataType());
        dataList.add(entity.getDataFormat());
        dataList.add(entity.getDataObject());

        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys(entity.getName());

        for(int i =0;i<4;i++){
            driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(i).click();
            List<WebElement> lists = driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li"));
            for (WebElement list:lists) {
                if(dataList.get(i).equals(list.getText())){
                    list.click();
                    break;
                }
            }
        }
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys(entity.getFilePath());
        Thread.sleep(2000);
        driver.findElements(By.xpath("//span[@class='button login_btn']")).get(0).click();
        Thread.sleep(2000);
*//*


    }
    //选取数据源
    public static void getDataSource(WebDriver driver,DataSourceUploadEntity entity){
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
        List<WebElement> lists = driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li"));
        for (WebElement list:lists) {
            if(entity.getDataSourceFormat().equals(list.getText())){
                list.click();
                getDataType(driver);
                break;
            }else if(entity.getDataSourceFormat().equals(list.getText())){
                list.click();
            }

        }
    }
    //选取数据类型
    public static void getDataType(WebDriver driver){
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
        List<WebElement> lists = driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li"));
        for (WebElement list:lists) {
            if("GIS数据".equals(list.getText())){
                list.click();

                break;
            }else if("三维模型数据".equals(list.getText())){
                list.click();
            }

        }
    }

    public static void getDataFormat(WebDriver driver){
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
        List<WebElement> lists = driver.findElements(By.xpath("//div[@x-placement='bottom-start']/div/div/ul/li"));
        for (WebElement list:lists) {
            if("geo json".equals(list.getText())){
                list.click();

                break;
            }else if("shape file".equals(list.getText())){
                list.click();
            }

        }
    }


}
*/
