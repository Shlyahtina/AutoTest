package ru.autotest.pageobjects.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.autotest.annotations.Component;
import ru.autotest.pageobjects.pages.PageObject;
import ru.autotest.support.GuiceScoped;

public abstract class AbsBaseComponent<T> extends PageObject<T> {

    {
        this.baseWaiters.waitForCondition(ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
    }

    private String baseLocator;
    private Actions actions;

    protected final String getBaseLocator(){
        return baseLocator;
    }
    protected void setBaseLocator(String value){

        baseLocator=value;
    }

    protected final Actions getActions(){
        return actions;
    }
    protected void setActions(Actions value){

        actions=value;
    }


    @Inject
    public AbsBaseComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
        actions = new Actions(driver);
    }

    protected By getComponentLocator() {
        Component component = getClass().getAnnotation(Component.class);

        if (component != null) {
            String value = component.value();

            baseLocator = value;

            if (value.startsWith("/")) {
                return By.xpath(value);
            }
            return By.cssSelector(value);
        }

        return null;
    }


}
