package ru.autotest.pageobjects.pages;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.autotest.annotations.UrlPage;
import ru.autotest.support.GuiceScoped;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractPage<T> extends PageObject<T> {

    /**
     * Добавляем конструктор
     * получаем стенд (хост) webdriver.base.url
     * если на страница добавим url то получить url
     * <p>
     * открыть браузер и стр
     * получить стр
     */

    @FindBy(tagName = "h1")
    public WebElement header;

    @Inject
    public AbstractPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    protected String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url", "https://otus.ru"), "/");
    }

    private String getUrlPage() {
        UrlPage urlAnnotation = getClass().getAnnotation(UrlPage.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }
        return "";
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPage());
        return (T) page(getClass());
    }

    public <T> T page(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructor(WebDriver.class);
            return convertInstanceOfObject(constructor.newInstance(driver), clazz);
        } catch (NoSuchMethodException
                 | IllegalAccessException
                 | InstantiationException
                 | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }

    public void scrollPageTo() {
    }

    public T pageHeaderShouldBeSameAs(String expectedHeader) {
        Assertions.assertEquals(expectedHeader, header.getText(), "");
        return (T) this;
    }
}
