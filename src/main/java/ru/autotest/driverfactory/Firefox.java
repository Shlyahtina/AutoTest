package ru.autotest.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Firefox implements WebDriverProvider {

    private WebDriver driver = null;

    private static final Logger LOG = Logger.getLogger(Firefox.class.getName());

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriverManager.firefoxdriver().setup();
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, getFirefoxOptions());
        try {
            driver = (WebDriver) new FirefoxOptions(getFirefoxOptions());
            return driver;
        } catch (Exception ex) {
            if (LOG.isLoggable(Level.INFO)) {
                LOG.info(String.valueOf(ex));
            }
        }
        return driver;
    }

    private static FirefoxOptions getFirefoxOptions() {
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--remote-allow-origins=*");
        firefoxOptions.addArguments("--window-size=1580,1280");
        return firefoxOptions;
    }
}
