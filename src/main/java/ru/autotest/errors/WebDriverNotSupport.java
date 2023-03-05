package ru.autotest.errors;

public class WebDriverNotSupport extends Exception {

    public WebDriverNotSupport(String driverType) {
        super(String.format("Browser type %s doesn't support", driverType));
    }
}
