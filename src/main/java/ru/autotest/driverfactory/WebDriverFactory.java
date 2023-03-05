package ru.autotest.driverfactory;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.autotest.errors.WebDriverNotSupport;

import java.util.Locale;

public class WebDriverFactory implements IWebDriverFactory {
    @Override
    public EventFiringWebDriver getWebDriver(String browserType) {

        switch (browserType) {
            case "chrome":
                return new EventFiringWebDriver(new Chrome().createDriver(new DesiredCapabilities()));
            case "firefox":
                return new EventFiringWebDriver(new Firefox().createDriver(new DesiredCapabilities()));
            case "opera":
                try {
                    throw new WebDriverNotSupport(browserType);
                } catch (WebDriverNotSupport e) {
                    e.printStackTrace();
                    return null;
                }
            default:
                return new EventFiringWebDriver(new Chrome().createDriver(new DesiredCapabilities()));
        }
    }
}