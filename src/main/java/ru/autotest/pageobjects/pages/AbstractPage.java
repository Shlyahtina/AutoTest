package ru.autotest.pageobjects.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import ru.autotest.annotations.UrlPage;

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

    public AbstractPage(WebDriver driver) {
        super(driver);
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
}
