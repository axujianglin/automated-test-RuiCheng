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

public class SDKModelTest {
    private WebDriver driver;
    private final String URL = SetUp.getBaseUrl();
    private final StringBuffer verificationErrors = new StringBuffer();
    Map<String,String> loginData = new HashMap<>();

    @BeforeClass
    public void LoginFirst(){
        SetUp set = new SetUp();
        set.setProperty();
        ChromeOptions options = SetUp.setChromeOption();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    //登录
    @Test
    public void LoginTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        LoginUtil.getInfo("src/test/java/com/test/datas",
                "Login.xlsx","Sheet1",loginData);
        Login.login(driver,loginData);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[7]")).click();
        Thread.sleep(2000);
        driver.findElements(By.xpath("//li[@class='el-menu-item childClass childStyle']")).get(0).click();
    }

    //新建版本
    @Test
    public void addVersion() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='top_btn']"));
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\idea project\\springMVC\\04_mimi_project\\src\\main\\webapp\\js\\jquery.js");
        driver.findElement(By.xpath("//textarea")).sendKeys("张三的描述");
        driver.findElements(By.xpath("//span[@class='el-radio__inner']")).get(0).click();
        driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
        driver.findElements(By.xpath("//i[@class='el-icon-circle-plus-outline']")).get(0).click();
        driver.findElement(By.xpath("//button[@class='el-button el-button--default el-button--small el-button--primary ']")).click();
        driver.findElements(By.xpath("//i[@class='el-icon-download']")).get(0).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("el-icon-remove-outline")).get(0).click();
        driver.findElement(By.xpath("//button[@class='el-button el-button--default el-button--small el-button--primary ']")).click();
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
