package com.test.cases;

import com.test.commans.Login;
import com.test.utils.LoginUtil;
import com.test.utils.MyServiceUtil;
import com.test.utils.SetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.fail;

//我的服务模块
public class ServiceModuleTest {
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

    @Test
    public void LoginTest() throws InterruptedException, IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        Map<String,String> loginData = new HashMap<>();
        LoginUtil.getInfo("src/test/java/com/test/datas",
                "Login.xlsx","Sheet1",loginData);
        Login.login(driver,loginData);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[2]")).click();

    }

    @Test(dependsOnMethods = "LoginTest")
    public void addDataSourceServer() throws IOException, InterruptedException {
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        List<List<String>> lists = MyServiceUtil.getInfo("src/test/java/com/test/datas","MyServiceData.xlsx","DataSource",6);
        for (int i=0;i<5;i++){
            if(lists.get(i).get(0).equals("null")){
                continue;
            }
            if(lists.get(i).get(1).equals("WMTS")){
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[1]")).click();
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[4]")).click();
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(2).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[6]")).click();
                Thread.sleep(1000);
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(3).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[1]")).click();
                driver.findElements(By.xpath("//input[@class='el-input__inner']")).get(0).sendKeys("张三版");

                driver.findElement(By.xpath("//i[@class='el-input__icon el-icon-arrow-down']")).click();
                Thread.sleep(3000);
                //切片格式
                List<WebElement> elements = driver.findElements(By.xpath("//div[@class='el-cascader-panel']/div[1]/div[1]/ul/li/span"));
                for (WebElement element:elements) {
                    if (element.getText().equals(lists.get(i).get(2))){
                        element.click();
                        break;
                    }
                }
                List<WebElement> elements1 = driver.findElements(By.xpath("//div[@class='el-cascader-panel']/div[2]/div[1]/ul/li/span"));
                for (WebElement element:elements1) {
                    if (element.getText().equals(lists.get(i).get(3))){
                        element.click();
                    }
                }
                Actions actions = new Actions(driver);
                actions.clickAndHold(driver.findElements(By.xpath("//div[@class='el-tooltip el-slider__button']")).get(1))
                        .moveToElement(driver.findElement(By.xpath("//div[@class='el-form-item cacheslider is-required']/div/ul/li[7]")));
                driver.findElement(By.xpath("//textarea")).sendKeys("描述");
                driver.findElement(By.xpath("//span[@class='span']")).click();
            }
        }
    }

    @Test(dependsOnMethods = "LoginTest")
    //暂时存在问题
    public void addAgencyService() throws IOException, InterruptedException {
        driver.findElement(By.xpath("//div[@class='top_btn']")).click();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        List<List<String>> lists = MyServiceUtil.getInfo("src/test/java/com/test/datas","MyServiceData.xlsx","AgencyService",11);
        for (int i=0;i<5;i++){
            if(lists.get(i).get(0).equals("null")){
                continue;
            }
            if(lists.get(i).get(1).equals("WMTS")){
                driver.findElements(By.xpath("//div[@class='el-input']/input")).get(0).sendKeys("张三");
                Thread.sleep(1000);
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(0).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[2]")).click();
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(1).click();
                driver.findElement(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li[4]")).click();
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(3).click();
                driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li")).get(0).click();

                Actions actions = new Actions(driver);

                driver.findElement(By.xpath("//i[@class='el-input__icon el-icon-arrow-down']")).click();
                Thread.sleep(3000);
                //切片格式
                List<WebElement> elements2 = driver.findElements(By.xpath("//div[@class='el-cascader-panel']/div[1]/div[1]/ul/li/span"));
                for (WebElement element:elements2) {
                    if (element.getText().equals(lists.get(i).get(5))){
                       actions.moveToElement(element).click().perform();
                        break;
                    }
                }
                Thread.sleep(2000);
                List<WebElement> elements1 = driver.findElements(By.xpath("//div[@class='el-cascader-panel']/div[2]/div[1]/ul/li/span"));
                for (WebElement element:elements1) {
                    if (element.getText().equals(lists.get(i).get(6))){
                        actions.moveToElement(element).click().perform();
                        break;
                    }
                }

                actions.clickAndHold(driver.findElements(By.xpath("//div[@class='el-tooltip el-slider__button']")).get(1))
                        .moveToElement(driver.findElement(By.xpath("//div[@class='el-form-item cacheslider is-required']/div/ul/li[7]")));
                driver.findElements(By.xpath("//i[@class='el-select__caret el-input__icon el-icon-arrow-up']")).get(4).click();
                List<WebElement> elements3 = driver.findElements(By.xpath("//div[@class='el-select-dropdown el-popper' and contains(@x-placement,'start')]/div/div/ul/li"));
                for (WebElement element: elements3) {
                    if (element.getText().equals(lists.get(i).get(4))){
                        element.click();
                        break;
                    }
                }
                driver.findElements(By.xpath("//label[@class='el-form-item__label']")).get(7).click();
                driver.findElements(By.xpath("//div[@class='el-input']/input")).get(1).sendKeys(lists.get(i).get(7));
                driver.findElement(By.xpath("//div[@class='authentication_count']/li/input[1]")).sendKeys(lists.get(i).get(8));
                driver.findElement(By.xpath("//div[@class='authentication_count']/li/input[2]")).sendKeys(lists.get(i).get(9));
                driver.findElement(By.xpath("//textarea")).sendKeys("张三二");
                driver.findElements(By.xpath("//span[@class='span']")).get(3).click();
                Thread.sleep(2000);
                driver.findElements(By.xpath("//span[@class='span']")).get(4).click();
                Thread.sleep(5000);
            }
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
