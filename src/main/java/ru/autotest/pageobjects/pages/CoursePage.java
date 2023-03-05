package ru.autotest.pageobjects.pages;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.autotest.support.GuiceScoped;


public class CoursePage extends AbstractPage<CoursePage> {
    @Inject
    public CoursePage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    @Override
    public String getBaseUrl() {
        return super.getBaseUrl();
    }


}
