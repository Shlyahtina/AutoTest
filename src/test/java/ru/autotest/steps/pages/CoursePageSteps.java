package ru.autotest.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import ru.autotest.pageobjects.pages.CoursePage;

public class CoursePageSteps {

    @Inject
    public CoursePage coursePage;

    @Тогда("На странице курса отображается заголовок {string}")
    public void openPageCourse(String header) {
        coursePage.pageHeaderShouldBeSameAs(header);
    }

}
