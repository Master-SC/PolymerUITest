package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        String browser = ConfigReader.getProperty("browser");
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // Runs Chrome in headless mode
            options.addArguments("--no-sandbox"); // Bypasses OS security model, needed for CI/CD
            options.addArguments("--disable-dev-shm-usage"); // Prevents potential memory issues
            options.addArguments("--remote-allow-origins=*"); // For CI/CD compatibility
            options.addArguments("--disable-gpu"); // Disables GPU hardware acceleration
            driver = new ChromeDriver(options);
        } else if (browser.equals("Edge")) {
            driver = new EdgeDriver();
        }

        String baseUrl = ConfigReader.getProperty("baseUrl");
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
