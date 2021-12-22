package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class EffectModelTest {
    private WebDriver driver;
    private final String URL = SetUp.getBaseUrl();
    private final StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public void LoginFirst(){
        SetUp set = new SetUp();
        set.setProperty();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //登录
    @Test
    public void LoginTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        Map<String, String> loginData = new HashMap<>();
        LoginUtil.getInfo("src/test/java/com/test/datas",
                "Login.xlsx", "Sheet1", loginData);
        Login.login(driver, loginData);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[5]")).click();
    }

    @Test(dependsOnMethods = "LoginTest")
    public void addEffect() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("楚子航");
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[2]")).click();
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[1]")).click();
        driver.findElements(By.xpath("//div[@class='el-upload el-upload--picture-card']/input")).get(0).sendKeys("C:\\Users\\24022\\Desktop\\图片信息\\download.jpg");
        driver.findElement(By.xpath("//button[@class='el-button el-button--primary']")).click();
        Thread.sleep(2000);
    }

    //测试结束关闭driver,收尾
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if(!"".equals(verificationErrorString)){
            fail(verificationErrorString);
        }
    }
}
