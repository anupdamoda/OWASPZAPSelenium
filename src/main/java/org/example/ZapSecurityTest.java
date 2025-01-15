package org.example;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.zaproxy.clientapi.core.ClientApi;


public class ZapSecurityTest {
    static final String ZAP_PROXY_ADDRESS = "localhost";
    static final int ZAP_PROXY_PORT = 8081;
    static final String ZAP_API_KEY = "ibmj02082rr6alt16n4cui5eqf";

    private WebDriver driver;
    private ClientApi api;

    @BeforeMethod
    public void setup(){
     String proxy_Server_URL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxy_Server_URL);
        proxy.setSslProxy(proxy_Server_URL);

        ChromeOptions co = new ChromeOptions();
        co.setProxy(proxy);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(co);
        api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);

    }


}