package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class MaterialModelTest {
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
        driver.findElement(By.xpath("//div[@class='list']/span[4]")).click();
    }

    @Test(dependsOnMethods = "LoginTest")
    public void addMaterial() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("路明非");
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[2]")).click();
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[2]")).click();
        driver.findElement(By.xpath("//div[@class='el-upload el-upload--text']/input")).sendKeys("D:\\WebDriver Resources\\测试数据\\ShapeFile\\states.zip");
        driver.findElement(By.xpath("//textarea")).sendKeys("描述");
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(2).click();
        driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[1]")).click();
        driver.findElements(By.xpath("//input[@class='el-upload__input']")).get(1).sendKeys("C:\\Users\\24022\\Desktop\\图片信息\\download.jpg");
        driver.findElement(By.xpath("//button[@class='el-button el-button--primary']/span")).click();
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElements(By.xpath("//div[@class='list_top']")).get(0)).perform();
        actions.click(driver.findElements(By.xpath("//div[@class='bg']/span")).get(0));
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
