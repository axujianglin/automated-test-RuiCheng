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

//我的应用模块
public class  ApplicationModelTest {
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
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[1]")).click();
    }

    @Test(dependsOnMethods = "LoginTest")
    public void applicationTest() throws InterruptedException {
        //添加应用
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("张三");
        /*driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li")).get(0).click();*/

        driver.findElement(By.xpath("//textarea")).sendKeys("描述");
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys("C:\\Users\\24022\\Desktop\\图片信息\\th.jpg");
        driver.findElement(By.xpath("//div[@class='el-form-item create_btn']/div/span[2]")).click();
        Thread.sleep(2000);
        //进入页面详情
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='list_top'][1]"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='bg']/span")).click();
        //编辑信息
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@class='btnClass cancel']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("1");
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(3).sendKeys("12");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\24022\\Desktop\\图片信息\\download.jpg");
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']/span")).get(3).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='tab']/span")).click();
        driver.findElements(By.xpath("//div[@class='right cancel']")).get(0).click();
        driver.findElement(By.xpath("//i[@class='el-icon-plus']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("子组织结构");
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']")).get(1).click();
        Thread.sleep(2000);
        driver.findElements(By.xpath("//i[@class='el-icon-edit']")).get(1).click();
        driver.findElement(By.xpath("//input[@class='el-input__inner']")).sendKeys("修改后");
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']")).get(1).click();

        driver.findElements(By.xpath("//div[@class='right cancel']")).get(1).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[@class='tab']/span")).click();
        driver.findElement(By.xpath("//div[@class='btnClass']")).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(4).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(5).click();
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']/span")).get(0).click();
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
