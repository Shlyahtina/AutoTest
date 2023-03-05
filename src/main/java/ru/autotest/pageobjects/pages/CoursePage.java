package ru.autotest.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class CoursePage extends AbstractPage<CoursePage> {

    @FindBy(css = ".title__text")
    private String title;

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return title;
    }
}
