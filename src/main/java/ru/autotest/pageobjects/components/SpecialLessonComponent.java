package ru.autotest.pageobjects.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.autotest.annotations.Component;
import ru.autotest.data.EnumStartedCourse;
import ru.autotest.pageobjects.pages.CoursePage;
import ru.autotest.support.GuiceScoped;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.fail;

@Component(".container-padding-bottom")
public class SpecialLessonComponent extends AbsBaseComponent<SpecialLessonComponent> {
    @FindBy(css = ".lessons__new-item-title")
    private WebElement listNameLessons;

    @FindBy(css = ".lessons__new-item-start")
    private List<WebElement> listStartLessons;

    @FindBy(css = ".lessons a")
    private List<WebElement> listLessons;

    @FindBy(css = ".container-padding-bottom .lessons a")
    private List<WebElement> onlySpecListLessons;

    @Inject
    public SpecialLessonComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    public CoursePage clickLessonName(String nameLesson) {
        findLessonByName(nameLesson).click();
        return new CoursePage(guiceScoped);
    }

    private WebElement findLessonByName(String nameLesson) {
        for (WebElement itemLesson : listLessons) {
            if (itemLesson.getText().contains(nameLesson)) {
                return itemLesson;
            }
        }
        return fail("course not found with name  [" + nameLesson + "]");
    }

    public CoursePage clickLessonFirstStarted(EnumStartedCourse status) {

        /*создаем мапу в которой элемент и дата
         * получаем список курсов со страницы
         * проходимся по списку и надодим для каждого курса дату
         * удаляем из даты ненужный текст
         * если дата, не мапится формату даты присваиваем нал
         * записываем курс и дату в мапу
         */

        String sdate;
        Date date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM", new Locale("ru"));
        Map<WebElement, Date> newListLessonsMap = new HashMap<>();
        ;


        for (WebElement el : onlySpecListLessons) {

            sdate = el.findElement(By.cssSelector(".lessons__new-item-time")).getText();
            int endI = sdate.indexOf(' ', 3);
            String s = sdate.substring(0, endI);
            try {
                date = formatter.parse(s);
                newListLessonsMap.put(el, date);
            } catch (ParseException e) {
                //newListLessonsMap.put(el, date.);
            }
        }

        Map.Entry<WebElement, Date> elementDate;

        if (status.equals(EnumStartedCourse.EARLY)) {
            elementDate = newListLessonsMap.entrySet().stream()
                    .min(Comparator.comparing(e -> e.getValue()))
                    .get();
        } else {
            elementDate = newListLessonsMap.entrySet().stream()
                    .max(Comparator.comparing(e -> e.getValue()))
                    .get();
        }

        elementDate.getKey().click();

        return new CoursePage(guiceScoped);
    }

    public CoursePage clickLessonByNameFromStream(String nameLesson) {

        List<WebElement> itemLesson = listLessons.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector(".lessons__new-item-title")).getText().equals(nameLesson))
                .distinct().toList();

        if (itemLesson.isEmpty()) {
            return fail("course not found with name  [" + nameLesson + "]");
        } else {
            itemLesson.get(0).click();
            return new CoursePage(guiceScoped);
        }
    }


}
