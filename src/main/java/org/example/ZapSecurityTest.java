package org.example;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Test;
import org.zaproxy.clientapi.core.*;

import java.util.List;


public class ZapSecurityTest {
    static final String ZAP_PROXY_ADDRESS = "localhost";
    static final int ZAP_PROXY_PORT = 8081;
    static final String ZAP_API_KEY = "ibmj02082rr6alt16n4cui5eqf";

    private WebDriver driver;
    private ClientApi api;

    @BeforeMethod
    public void setup() throws ClientApiException {
     String proxy_Server_URL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;

        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxy_Server_URL);
        proxy.setSslProxy(proxy_Server_URL);

        ChromeOptions co = new ChromeOptions();
        co.setProxy(proxy);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(co);
        api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT, ZAP_API_KEY);

        // Fetch all alerts
        System.out.println("Fetching alerts...");
        ApiResponse alertsResponse = api.alert.alerts(null, null, null, null, null);

        // Parse alerts and check for medium alerts
        boolean mediumAlertFound = analyzeAlerts(alertsResponse);
        if (mediumAlertFound) {
            System.out.println("Test failed: Medium alert(s) found.");
        } else {
            System.out.println("Test passed: No medium alerts.");
        }

    }

    private static boolean analyzeAlerts(ApiResponse alertsResponse) {
        boolean mediumAlertFound = false;
        if (alertsResponse instanceof ApiResponseList) {
            ApiResponseList alertList = (ApiResponseList) alertsResponse;
            List<ApiResponse> alerts = alertList.getItems(); // Retrieve all alerts

            for (ApiResponse alert : alerts) {
                if (alert instanceof ApiResponseList) {
                    ApiResponseList alertDetails = (ApiResponseList) alert;

                    // Retrieve the 'risk' attribute
                    String risk = null;
                    for (ApiResponse detail : alertDetails.getItems()) {
                        if (detail instanceof ApiResponseElement) {
                            ApiResponseElement element = (ApiResponseElement) detail;
                            if ("risk".equalsIgnoreCase(element.getName())) {
                                risk = element.getValue();
                                break;
                            }
                        }
                    }

                    if ("Medium".equalsIgnoreCase(risk)) {
                        mediumAlertFound = true;
                        break;
                    }
                }
            }
        }
        return mediumAlertFound;
    }

    @Test
    public void owaspsecurityTest(){
        driver.get("https://juice-shop.herokuapp.com/#/administrator");
        Assert.assertTrue(driver.getTitle().contains("Juice"));

    }

    @AfterMethod
    public void teardown(){
        if (api != null){
            String title = "Owasp juice portal Security Report - Title";
            String template = "traditional-html";
            String description = "Owasp juice portal Security Report - Description ";
            String reportfilename = "owaspsecurity-report.html";
            String targetFolder = System.getProperty("user.dir");

            try {
                ApiResponse response = api.reports.generate(title,template, null, description, null, null, null, null, null, reportfilename, null, targetFolder, null);

            } catch (ClientApiException e) {
                throw new RuntimeException(e);
            }


        }
    }
}