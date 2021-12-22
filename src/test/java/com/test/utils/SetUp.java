package com.test.utils;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.Properties;

public class SetUp {

    //chrome路径
    public void setProperty(){
        System.setProperty("webdriver.chrome.driver","D:\\idea project\\WebDriver-projects\\06_framework\\src\\test\\resources\\chromedriver.exe");
    }

    //访问的地址
    public static String getBaseUrl(){
        Properties properties = new Properties();
        String baseUrl = null;
        //读取Url
        try {
            InputStream in = new FileInputStream("src/test/java/com/test/datas/baseUrl.properties");
            properties.load(in);
            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseUrl;
    }

    //设置浏览器的属性
    public static ChromeOptions setChromeOption(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        return options;
    }
}


