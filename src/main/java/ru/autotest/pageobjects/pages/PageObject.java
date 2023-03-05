package ru.autotest.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.autotest.waiter.BaseWaiters;

public abstract class PageObject<T> {

    /**
     * добавляем: драйвер
     * ожидания
     * действия
     */
    protected WebDriver driver;
    protected Actions actions;
    protected BaseWaiters baseWaiters;


    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        baseWaiters = new BaseWaiters(driver);
        actions = new Actions(driver);
    }

    public WebElement moveToElement(WebElement element) {
        actions.moveToElement(element)
                .perform();

        return element;
    }
}
