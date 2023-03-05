package ru.autotest.driverfactory;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.autotest.errors.WebDriverNotSupport;

import java.util.Locale;

public class WebDriverFactory implements IWebDriverFactory {

    private String browserType = System.getProperty("browser", "chrome").trim().toLowerCase(Locale.ROOT);

    @Override
    public EventFiringWebDriver getWebDriver() {

        switch (this.browserType) {
            case "chrome":
                return new EventFiringWebDriver(new Chrome().createDriver(new DesiredCapabilities()));
            case "firefox":
                return new EventFiringWebDriver(new Firefox().createDriver(new DesiredCapabilities()));
            case "opera":
                try {
                    throw new WebDriverNotSupport(this.browserType);
                } catch (WebDriverNotSupport e) {
                    e.printStackTrace();
                    return null;
                }
            default:
                return new EventFiringWebDriver(new Chrome().createDriver(new DesiredCapabilities()));
        }
    }
}
