package ru.autotest.pageobjects.pages;

import org.openqa.selenium.WebDriver;
import ru.autotest.annotations.UrlPage;

@UrlPage("/")
public class OtusMainPage extends AbstractPage<OtusMainPage> {

    public OtusMainPage(WebDriver driver) {
        super(driver);
    }

}
