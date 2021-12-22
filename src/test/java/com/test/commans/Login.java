package com.test.commans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class Login {
    /*登录*/
    public static void login(WebDriver driver, Map<String,String> map) throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='login']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys(map.get("userName"));
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys(map.get("password"));
        driver.findElement(By.xpath("//span[@class='btn']")).click();

        /*String loginInfo = driver.findElement(By.xpath("//div[@id='app']/div")).getAttribute("class");
        if("home".equals(loginInfo)){
            System.out.println("登录成功");

        }else if("login".equals(loginInfo)){
            System.out.println("登录失败");
        }*/
    }
}
