package ru.autotest.extensions;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.autotest.drivers.AppiumSelenideDriver;

public class AppiumExtension implements BeforeAllCallback{
    @Override
    public void beforeAll(ExtensionContext context){
        Configuration.browserSize = null;
        Configuration.browser = AppiumSelenideDriver.class.getName();
    }
}
