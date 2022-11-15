package com.testinium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public String URL = "http://hub.testinium.io/wd/hub";
    protected static WebDriver driver;
    protected static Actions actions;

    DesiredCapabilities capabilities;
    ChromeOptions chromeOptions;

    FirefoxOptions firefoxOptions;

    private static final String hubCloudDev = "https://hubclouddev.testinium.com/wd/hub";
    private static final String hubLocal = "http://localhost:4444/wd/hub";
    private static final String hubCloudProd = "https://hubclouddev.testinium.com/wd/hub";

    String browserName = "chrome";
    String selectPlatform = "win";

    @Before
    public void beforeTest() throws MalformedURLException {
        String key = System.getProperty("key", "");
        if (key.isEmpty()) {
            System.out.println("local");
            System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
            driver = new ChromeDriver(chromeOptions());
        } else {
            System.out.println("testinium");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability("key", System.getProperty("key"));
            try {
                driver = new RemoteWebDriver(new URL(URL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }


    }

    public FirefoxOptions firefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        firefoxOptions.addArguments("--kiosk");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--start-fullscreen");
        FirefoxProfile profile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        capabilities.setCapability("marionette", true);
        firefoxOptions.merge(capabilities);
        System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver");
        return firefoxOptions;

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
