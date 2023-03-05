package ru.autotest.steps.commons;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.autotest.driverfactory.WebDriverFactory;
import ru.autotest.support.GuiceScoped;

public class CommonSteps {

    @Inject
    public GuiceScoped guiceScoped;

    @Пусть("Пользователь открывает браузер {string}")
    public void openBrowser(String browserName) {
        guiceScoped.driver = new WebDriverFactory().getWebDriver(browserName);
    }

}
