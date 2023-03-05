package ru.autotest.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import ru.autotest.data.MyEnumType;
import ru.autotest.pageobjects.components.LessonComponent;
import ru.autotest.pageobjects.pages.OtusMainPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainPageSteps {

    @Inject
    public OtusMainPage otusMainPage;

    @Inject
    public LessonComponent lessonComponent;

    @Тогда("На главной странице отображается заголовок {string}")
    public void verifyOtusMainPage(String header) {
        otusMainPage.pageHeaderShouldBeSameAs(header);
    }

    @Если("Пользователь открывает страницу {string}")
    public void openOtusMainPage(String pageName) {
        otusMainPage.open();
    }

    @Если("Пользователь нажимает на курс {string}")
    public void clickLessonByName(String lessonName) {
        lessonComponent.clickLessonByName(lessonName);
    }

    @ParameterType(value = "больше|равно|меньше", name = "enumType")
    public MyEnumType enumType(String titleType) {
        MyEnumType result = null;
        for (MyEnumType typeEnum : MyEnumType.values()) {
            if (typeEnum.getTitle().equalsIgnoreCase(titleType)) {
                result = typeEnum;
                break;
            }
        }
        return result;

    }

    @Если("Пользователь нажимает на курс с датой {enumType} {string}")
    public void clickLesson(MyEnumType flag, String dateInString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
        Date date = formatter.parse(dateInString);

        lessonComponent.clickLesson(flag, date);
    }

    @Если("Пользователь нажимает на курс с датой {enumType}")
    public void clickLesson2(MyEnumType flag) {
        lessonComponent.clickLesson(flag, new Date());
    }

}
