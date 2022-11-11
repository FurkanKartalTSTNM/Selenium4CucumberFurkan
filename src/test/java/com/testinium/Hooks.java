package com.testinium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public String URL = "http://127.0.0.1:4444/wd/hub";
    public static String browserName = "chrome";

    protected static WebDriver driver;
    ChromeOptions chromeOptions;
    DesiredCapabilities capabilities;
    private static final String hubCloudDev = "https://hubclouddev.testinium.com/wd/hub";
    private static final String hubLocal = "http://localhost:4444/wd/hub";
    private static final String hubCloudProd = "https://hubclouddev.testinium.com/wd/hub";

    @Before
    public void beforeTest() throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();

        final String key = System.getenv("key");
        final String testID = System.getenv("testID");

        if (StringUtils.isNotEmpty(key)) {
            // testinium tarafından başlatıldığında
                System.out.println("Testiniumda ayaga kalkti");
                dc = DesiredCapabilities.chrome();
                dc.setCapability("testinium:testID", testID);
                dc.setCapability("testinium:takesScreenshot", true);
                dc.setCapability("testinium:recordsVideo", true);
                // Cloud USERNAME:ACCESS_KEY
                dc.setCapability("key", System.getProperty("key"));
                driver = new RemoteWebDriver(new URL(hubCloudProd),dc);

        } else {
            // test local'den testinium'suz başlatıldığında
            /*
            System.setProperty("webdriver.chrome.webdriver", "/Users/ebubekir/Dev/data/chromedriver");
            webdriver = new ChromeDriver();

             */

            System.out.println("Localde ayaga kalkti");
            driver = new ChromeDriver(chromeOptions());
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //System.setProperty("webdriver.gecko.driver", "/Users/ebubekir/Dev/data/geckodriver");
/*
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), dc
            );

 */


        }
    }

    @After
    public void afterTest() {
        if(driver!=null) {
            driver.quit();
        }
    }

    public ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--start-fullscreen");
        System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver.exe");
        chromeOptions.merge(capabilities);
        return chromeOptions;
    }

    public static WebDriver getWebDriver() {
        return driver;
    }
}
