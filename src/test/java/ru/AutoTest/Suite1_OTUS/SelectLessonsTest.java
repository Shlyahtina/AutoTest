package ru.AutoTest.Suite1_OTUS;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.AutoTest.factory.WebDriverFactory;
import ru.AutoTest.pages.OtusMainPage;

public class SelectLessonsTest {

    private WebDriver driver;
    private OtusMainPage otusMainPage;

    @BeforeClass
    public void SetUp() {
        driver = WebDriverFactory.getWebDriver("chrome");
        otusMainPage = new OtusMainPage(driver);
        driver.get("https://otus.ru");
    }

    @AfterMethod
    public void goOtusMainPage() {
        driver.get("https://otus.ru");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void selectLesson_ApacheKafka() throws InterruptedException {
        otusMainPage
                .clickLessonName("Apache Kafka");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://otus.ru/lessons/kafka/");
    }

    @Test
    public void selectLesson_PHPDeveloper() throws InterruptedException {
        otusMainPage
                .clickLessonName("Специализация PHP Developer");
        Thread.sleep(5000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://otus.ru/lessons/php-specialization/");
    }

}
