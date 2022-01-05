package com.test.cases;

import com.test.commans.*;
import com.test.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.*;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.fail;

public class DataSourceModuleTest {
    private WebDriver driver;
    private final String URL = SetUp.getBaseUrl();
    private final StringBuffer verificationErrors = new StringBuffer();
    List<List<String>> infoLists = DataSourceImportUtil.getInfo("src/test/java/com/test/datas",
            "DataSourceImport.xlsx","ShapeFile",3);
    public DataSourceModuleTest() throws IOException, InterruptedException {
    }

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
        Thread.sleep(3000);
        driver.findElement(By.xpath("//i[@class='el-icon-caret-bottom']")).click();
        driver.findElement(By.xpath("//div[@class='list']/span[3]")).click();
    }

    //确定数据格式为jeo json
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseGeoJson() throws InterruptedException, IOException {
        List<List<String>> lists;
        List<List<String>> infoLists = DataSourceImportUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceImport.xlsx","jeoJson",1);
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",1);
        DataSourceUploadUtil.dataUploadGis(lists,driver,0,infoLists);
    }

    //确定数据格式为shape file
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseShapeFile() throws InterruptedException, IOException {
        List<List<String>> lists;
        List<List<String>> infoLists = DataSourceImportUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceImport.xlsx","ShapeFile",2);
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",8);
        DataSourceUploadUtil.dataUploadGis(lists,driver,1,infoLists);
    }
    //确定数据类型为csv file
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseCsvFile() throws InterruptedException, IOException {
        List<List<String>> lists;
        List<List<String>> infoLists = DataSourceImportUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceImport.xlsx","Sheet2",3);
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",15);
        DataSourceUploadUtil.dataUploadGis(lists,driver,2,infoLists);
    }
    //确定数据类型为arc json
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseArcJson() throws InterruptedException, IOException {
        List<List<String>> lists;
        List<List<String>> infoLists = DataSourceImportUtil.getInfo("src/test/java/com/test/datas",
                "DataSourceImport.xlsx","ShapeFile",4);
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",22);
        DataSourceUploadUtil.dataUploadGis(lists,driver,3,infoLists);

    }
    //确定数据类型为tiff
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseTiff() throws InterruptedException, IOException {
        List<List<String>> lists;
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",29);
        DataSourceUploadUtil.dataUploadGis(lists,driver,4,infoLists);
    }
    //确定数据类型为3d tiles
    @Test(dependsOnMethods = "LoginTest")
    public void ChooseTiles() throws InterruptedException, IOException {
        List<List<String>> lists;
        lists=DataSourceUploadUtil.getInfoByExcel("src/test/java/com/test/datas",
                "LoginTestData.xlsx","Sheet2",36);
        DataSourceUploadUtil.dataUploadModel(lists,driver);
    }
    //数据源导入
    @Test(dependsOnMethods = "LoginTest")
    public void ImportTest(){
        /*List<String> info = new ArrayList<>();
        DataSourceImportUtil.Import(driver,info);*/
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


