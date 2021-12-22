package com.proxyTest;

import com.test.utils.SetUp;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;

import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.*;
import net.lightbody.bmp.filters.RequestFilter;

import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.List;



public class BrowerProxyUtils{
    /**
     * 获取当前页面部分请求的状态码
     */
    @Test
    public static void getStatus() throws InterruptedException {
        //初始化
        SetUp set = new SetUp();
        set.setProperty();
        BrowserMobProxy proxy = new BrowserMobProxyServer();
        proxy.start();
        proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        proxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
        proxy.newHar("baidu.com");
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        ChromeOptions options = new ChromeOptions();
        options.setProxy(seleniumProxy);
        options.setAcceptInsecureCerts(true);
        WebDriver driver = new ChromeDriver(options);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        // 监听网络请求
        proxy.addRequestFilter(new RequestFilter() {
            @Override
            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
                //System.out.println(request.getUri() + "-->" + request.headers().get("cookie"));
                return null;
            }
        });
        //进入页面
        driver.get("https://www.baidu.com/");
        Har har = proxy.getHar();
        List<HarEntry> entries = har.getLog().getEntries();
        for (HarEntry entry : entries) {
            HarResponse response = entry.getResponse();
            HarRequest request = entry.getRequest();
            String url1 = request.getUrl();
            int statusTwo = response.getStatus();
            System.out.println("路由地址为：" + url1);
            System.out.println("得到的状态码为：" + statusTwo);
        /*String serviceAdress = driver.findElement(By.xpath("//div[@class='right_div service']/span[2]")).getText();
        String fileName = serviceAdress.substring(serviceAdress.indexOf("/") + 1);
        String url = "http://10.10.1.137:8081"+serviceAdress;
        int status = 0;
        if (!fileName.contains(".json")) {
            Thread.sleep(5000);
            Har har = proxy.getHar();
            List<HarEntry> entries = har.getLog().getEntries();
            for (HarEntry entry : entries) {
                HarResponse response = entry.getResponse();
                HarRequest request = entry.getRequest();
                String url1 = request.getUrl();
                int statusTwo = response.getStatus();
                if (url1.equals(url)) {
                    if(statusTwo == 200){
                        status = statusTwo;
                        System.out.println("状态码为"+status+",3dTiles文件发布成功");
                    }else {
                        System.out.println("状态码为"+status+",3dTiles文件发布失败");
                    }
                }
            }
        }*/


        }
    }
}
