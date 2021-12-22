package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.SetUp;

import org.openqa.selenium.By;
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

public class PublicResourceModelTest {
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
        Map<String,String> loginData = new HashMap<>();
        LoginUtil.getInfo("src/test/java/com/test/datas",
                "Login.xlsx","Sheet1",loginData);
        Login.login(driver,loginData);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[7]")).click();
    }

    //新增版本
    @Test
    public void addVersion() throws InterruptedException {
        //第一个版本
        driver.findElements(By.xpath("//li[@class='el-menu-item childClass childStyle']")).get(0).click();
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElements(By.xpath("//textarea")).get(1).sendKeys("张三的测试");
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys("D:\\WebDriver Resources\\测试数据\\ShapeFile\\states.zip");
        Thread.sleep(2000);
        driver.findElements(By.xpath("//span[@class='button login_btn']")).get(1).click();
        driver.findElements(By.xpath("//i[@class='el-icon-view']")).get(0).click();
        driver.findElements(By.xpath("//span[@class='name']")).get(1).click();
        //获取文件个数
        List<WebElement> elements = driver.findElements(By.xpath("//tbody/tr"));
        //和文件实际个数进行对比
        if(elements.size()==5){
            System.out.println("文件上传个数正确");
        }else {
            System.out.println("文件上传个数不正确");
        }
        driver.findElements(By.xpath("//span[@class='name']")).get(1).click();
        driver.findElement(By.xpath("//span[@class='edit']")).click();

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\WebDriver Resources\\TestDemo_allcorrect_20170410\\TestDemo\\.svn.zip");
        driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
        driver.findElements(By.xpath("//span[@class='name']")).get(1).click();
        List<WebElement> elements1 = driver.findElements(By.xpath("//tbody/tr"));
        //和文件实际个数进行对比
        if(elements1.size()==817){
            System.out.println("文件上传个数正确");
        }else {
            System.out.println("文件上传个数不正确");
        }


        /*//第二个版本
        driver.findElement(By.xpath("//li[@class='el-menu-item is-active childClass childStyle']")).click();
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.findElement(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).click();
        driver.findElements(By.xpath("//li[@class='el-select-dropdown__item']")).get(0).click();
        driver.findElements(By.xpath("//textarea")).get(1).sendKeys("张三的测试");
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys("D:\\WebDriver Resources\\孪生可视化平台\\测试数据.rar");
        driver.findElements(By.xpath("//span[@class='button login_btn']")).get(1).click();
        driver.findElements(By.xpath("//i[@class='el-icon-view']")).get(0).click();
        driver.findElements(By.xpath("//span[@class='name']")).get(1).click();
        *//*文件数进行对比*//*
        driver.findElement(By.xpath("//span[@class='edit']")).click();
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("D:\\WebDriver Resources\\TestDemo_allcorrect_20170410\\TestDemo\\.svn.zip");
        driver.findElement(By.xpath("//span[@class='button login_btn']")).click();



        driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(1).sendKeys("2.4.1");
        driver.findElement(By.xpath("//textarea")).sendKeys("张三2");
        driver.findElement(By.xpath("//input[@class='el-upload__input']")).sendKeys("D:\\WebDriver Resources\\TestDemo_allcorrect_20170410\\TestDemo\\doc.zip");
        driver.findElement(By.xpath("//span[@class='button login_btn']")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/div[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[2]/div[1]/span[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[3]/div/div[2]/form/div[1]/div/div/div/input")).sendKeys("D:\\WebDriver Resources\\TestDemo_allcorrect_20170410\\TestDemo\\intergrationtest.zip");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[3]/div/div[2]/form/div[2]/div/span[2]")).click();*/
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
