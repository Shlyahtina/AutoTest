package ru.autotest.waiter;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class BaseWaiters {
    private WebDriverWait webDriverWait;

    public BaseWaiters(WebDriver driver) {
        this.webDriverWait = new WebDriverWait(driver,
                Duration.ofSeconds(
                        Integer.parseInt(
                                System.getProperty("waiter_timeout_seconds", "5").trim().toLowerCase(Locale.ROOT)
                        )
                )
        );
    }

    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            webDriverWait.until(condition);
        } catch (TimeoutException ignore) {
            return false;
        }
        return true;
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitForElementNotVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.invisibilityOf(element));
    }
}
