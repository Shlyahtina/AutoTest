package ru.AutoTest.Suite1_OTUS;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.AutoTest.factory.WebDriverFactory;
import ru.AutoTest.pages.OtusPrivateOfficePage;

public class PrivateOffice {

    private WebDriver driver;
    private OtusPrivateOfficePage otusprivateOfficePage;

    @BeforeClass
    public void SetUp() {
        driver = WebDriverFactory.getWebDriver("chrome");
        otusprivateOfficePage = new OtusPrivateOfficePage(driver);
        driver.get("https://otus.ru/learning/");
    }

    @AfterMethod
    public void goOtusMainPage() {
        driver.get("https://otus.ru/learning/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verify_Title() throws InterruptedException {

        Thread.sleep(5000);
        Assert.assertNotEquals(otusprivateOfficePage.getTitle(), "Личный кабинет");
    }

}
