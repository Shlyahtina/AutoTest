package ru.autotest.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Chrome implements WebDriverProvider {

    private WebDriver driver = null;

    private static final Logger LOG = Logger.getLogger(Chrome.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {

        WebDriverManager.chromedriver().setup();
        capabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());
        try {
            driver = new ChromeDriver(getChromeOptions());
            return driver;
        } catch (Exception ex) {
            if (LOG.isLoggable(Level.INFO)) {
                LOG.info(String.valueOf(ex));
            }
        }
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--window-size=1580,1280");

        return chromeOptions;
    }
}
