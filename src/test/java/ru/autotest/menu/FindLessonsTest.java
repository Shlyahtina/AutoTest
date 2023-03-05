package ru.autotest.menu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.autotest.CustomTestInstance;
import ru.autotest.annotations.Driver;
import ru.autotest.data.EnumStartedCourse;
import ru.autotest.pageobjects.components.LessonComponent;
import ru.autotest.pageobjects.pages.OtusMainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(CustomTestInstance.class)
public class FindLessonsTest {
    @Driver
    private WebDriver driver;

    @Test
    public void selectLessonByNameWithStreamTest() {
        new OtusMainPage(driver).open();
        new LessonComponent(driver).clickLessonByNameFromStream("Apache Kafka");
        assertEquals("https://otus.ru/lessons/kafka/", driver.getCurrentUrl());
    }

    @Test
    public void selectLessonFirstStartedTest() {
        new OtusMainPage(driver).open();
        new LessonComponent(driver).clickLessonFirstStarted(EnumStartedCourse.EARLY);
        new OtusMainPage(driver).open();
        new LessonComponent(driver).clickLessonFirstStarted(EnumStartedCourse.LATE);
    }

    @Test
    public void moveMouseTest() {
        new OtusMainPage(driver).open();
        new LessonComponent(driver).moveMouseByNameLessons("Apache Kafka");
    }

}
