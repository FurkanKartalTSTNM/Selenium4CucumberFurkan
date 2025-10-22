package com.testinium;

import com.testinium.driver.TestiniumSeleniumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Hooks {

    public String hubURL = "http://host.docker.internal:4444/wd/hub";
    protected static WebDriver driver;
    protected static Actions actions;

    @Before
    public void beforeTest() throws MalformedURLException {
        String key = System.getProperty("key", "");
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        if (key.isEmpty()) {
            System.out.println("local");
            switch (browser) {
                case "firefox" -> driver = new FirefoxDriver(firefoxOptions());
                default -> driver = new ChromeDriver(chromeOptions());
            }
        } else {
            System.out.println("testinium ortaminda baslatiliyor");
            URL grid = new URL(hubURL);

            switch (browser) {
                case "firefox" -> {
                    FirefoxOptions options = firefoxOptions();
                    options.setCapability("key", key);
                    driver = new TestiniumSeleniumDriver(grid, options);
                }
                default -> {
                    ChromeOptions options = chromeOptions();
                    options.setCapability("key", key);
                    driver = new TestiniumSeleniumDriver(grid, options);
                }
            }
        }

        // (İsteğe bağlı) Actions kullanacaksan hazırla
        actions = new Actions(driver);
    }

    @After
    public void afterTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
            actions = null;
        }
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    // -------- Options Builders --------

    public ChromeOptions chromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Bildirimleri kapatma vb. prefs
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        // Ortak argümanlar
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        // CI/container ortamları için faydalı:
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        // Headless istersen: -Dheadless=true
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }

        options.setAcceptInsecureCerts(true);
        return options;
    }

    public FirefoxOptions firefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        // Bildirimleri kapatma
        options.addPreference("dom.webnotifications.enabled", false);

        // Tam ekran
        options.addArguments("--kiosk");           // mac/linux
        options.addArguments("--start-fullscreen");// cross-platform

        // Headless istersen: -Dheadless=true
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("-headless");
        }

        options.setAcceptInsecureCerts(true);
        return options;
    }
}
