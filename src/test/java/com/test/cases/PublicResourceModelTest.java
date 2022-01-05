package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.SetUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
