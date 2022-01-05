package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

public class AccountManageModelTest {
    private WebDriver driver;
    private final String URL = SetUp.getBaseUrl();
    private final StringBuffer verificationErrors = new StringBuffer();
    Map<String,String> loginData = new HashMap<>();

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
        LoginUtil.getInfo("src/test/java/com/test/datas",
                "Login.xlsx","Sheet1",loginData);
        Login.login(driver,loginData);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[7]")).click();
    }
    //账户添加和修改
    @Test
    public void addAccountTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElements(By.xpath("//li[@class='el-menu-item childClass childStyle']")).get(3).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='btn']")).click();
        List<WebElement> elements = driver.findElements(By.xpath("//input[@class='el-input__inner']"));
        elements.get(1).sendKeys("zhangsan");
        elements.get(2).sendKeys("4569815@x");
        elements.get(3).sendKeys("zhangsan");

        Thread.sleep(2000);
        //归属部门
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys("左右");
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.UP);
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.ENTER);
        elements.get(4).sendKeys("15895764598");
        elements.get(5).sendKeys("2402208586@qq.com");
        driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li")).get(1).click();
        driver.findElement(By.xpath("//textarea[@class='el-textarea__inner']")).sendKeys("描述");
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']")).get(1).click();
        Thread.sleep(2000);

        //修改用户
        driver.findElements(By.xpath("//span[@class='textBtn']")).get(0).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys("烽火");
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.UP);
        driver.findElement(By.xpath("//input[@class='vue-treeselect__input']")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//ul[@class='el-scrollbar__view el-select-dropdown__list']/li")).get(1).click();
        driver.findElements(By.xpath("//button[@class='el-button el-button--primary']")).get(1).click();
        Thread.sleep(2000);
    }
    //账户的用户查询
    @Test
    public void queryUser() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("zhangsan");
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
    }

    //角色新增
    @Test
    public void addCharacter() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElements(By.xpath("//li[@class='el-menu-item childClass childStyle']")).get(4).click();
        driver.findElement(By.xpath("//span[@class='btnClass']")).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("张三1");
        driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//li[@class='el-select-dropdown__item']")).get(3).click();
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(3).sendKeys("没有描述");
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(2).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(4).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(16).click();
        driver.findElement(By.xpath("//button[@class='el-button el-button--primary']")).click();
        Thread.sleep(2000);
        //角色查询
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("张三1");
        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElements(By.xpath("//div[@class='cell']/div")).get(0).click();
        driver.findElement(By.xpath("//span[@class='btnClass cancel']")).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//ul[2]/li[2]/ul/li[2]")).click();
            driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(2).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(6).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(20).click();
        driver.findElements(By.xpath("//span[@class='el-checkbox__inner']")).get(21).click();
        driver.findElements(By.xpath("//span[@class='btnClass']")).get(1).click();



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
