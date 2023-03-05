package ru.AutoTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OtusPrivateOfficePage {

    private final WebDriver driver;

    @FindBy(css = ".title__text")
    private String title;

    public OtusPrivateOfficePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public String getTitle() {
        return title;
    }
}
