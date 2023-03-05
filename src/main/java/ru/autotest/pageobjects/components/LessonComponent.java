package ru.autotest.pageobjects.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.autotest.annotations.Component;
import ru.autotest.data.EnumStartedCourse;
import ru.autotest.data.MyEnumType;
import ru.autotest.pageobjects.pages.CoursePage;
import ru.autotest.support.GuiceScoped;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@Component(".container-padding-bottom")
public class LessonComponent extends AbsBaseComponent<LessonComponent> {
    @FindBy(css = ".lessons__new-item-title")
    private WebElement listNameLessons;

    @FindBy(css = ".lessons__new-item-start")
    private List<WebElement> listStartLessons;

    @FindBy(css = ".lessons a")
    private static List<WebElement> listLessons;

    @FindBy(css = ".container-padding-bottom .lessons a")
    private List<WebElement> onlySpecListLessons;

    @FindBy(css = "[class='lessons'] .lessons__new-item-start, [class='lessons'] .lessons__new-item-bottom > .lessons__new-item-time")
    private List<WebElement> dateLessons;

    @Inject
    public LessonComponent(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private WebElement findElementByName(String nameLesson) {
        List<WebElement> itemLesson = listLessons.stream()
                .filter(webElement -> webElement.findElement(By.cssSelector(".lessons__new-item-title")).getText().equals(nameLesson))
                .distinct().toList();

        if (itemLesson.isEmpty()) {
            return fail("course not found with name  [" + nameLesson + "]");
        } else {

            return itemLesson.get(0);
        }
    }

    public CoursePage clickLessonByNameFromStream(String nameLesson) {
        findElementByName(nameLesson).click();
        return new CoursePage(guiceScoped);
    }

    public CoursePage clickLessonFirstStarted(EnumStartedCourse status) {
        Map<WebElement, Date> newListLessonsMap = getMapDateLessons();

        WebElement elementByDate;

        if (status.equals(EnumStartedCourse.EARLY)) {
            elementByDate = getItemEARLY(newListLessonsMap);
        } else {
            elementByDate = getItemLATE(newListLessonsMap);
        }

        elementByDate.click();

        return new CoursePage(guiceScoped);
    }


    public void clickLessonByName(String nameLesson) {

        Map<WebElement, Date> map = getMapDateLessons();

        Map<WebElement, Date> result = new HashMap<>();

        for (Map.Entry<WebElement, Date> entry : map.entrySet()) {
            if (entry.getKey().getText().contains(nameLesson)) {
                Date value = entry.getValue();
                WebElement key = entry.getKey();
                result.put(key, value);
            }
        }

        System.out.println("Название курса: \n" + result.keySet().stream().findFirst().get().getText() + "\nДата курса: \n" + result.values().stream().findFirst().get().toString());

        result.keySet().stream().findFirst().get().click();

    }

    public void clickLesson(MyEnumType flag, Date date) {
        Map<WebElement, Date> map = getMapDateLessons();

        Map<WebElement, Date> result = new HashMap<>();
        Date value;
        WebElement key;

        for (Map.Entry<WebElement, Date> entry : map.entrySet()) {

            switch (flag.getTitle()) {
                case ("больше"):
                    if (entry.getValue().compareTo(date) > 0) {
                        value = entry.getValue();
                        key = entry.getKey();
                        result.put(key, value);
                    }
                    break;
                case ("равно"):
                    if (entry.getValue().compareTo(date) == 0) {
                        value = entry.getValue();
                        key = entry.getKey();
                        result.put(key, value);
                    }
                    break;
                case ("меньше"):
                    if (entry.getValue().compareTo(date) < 0) {
                        value = entry.getValue();
                        key = entry.getKey();
                        result.put(key, value);
                    }
                    break;
            }
        }


        System.out.println("Название курса: \n" + result.keySet().stream().findFirst().get().getText() + "\nДата курса: \n" + result.values().stream().findFirst().get().toString());

        result.keySet().stream().findFirst().get().click();
    }

    private Map<WebElement, Date> getMapDateLessons() {
        /*создаем мапу в которой элемент и дата
         * получаем список курсов со страницы
         * проходимся по списку и находим для каждого курса дату
         * удаляем из даты ненужный текст
         * если дата, не мапится формату даты не добавляем в мапу
         * записываем курс и дату в мапу
         */
        String strDate;
        Date dateEl;
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM", new Locale("ru"));
        Map<WebElement, Date> newListLessonsMap = new HashMap<>();

        for (WebElement el : listLessons) {

            strDate = el.findElement(By.cssSelector("[class='lessons'] .lessons__new-item-start, [class='lessons'] .lessons__new-item-bottom > .lessons__new-item-time")).getText();

            byte[] barr = strDate.getBytes(Charset.forName("UTF-8"));
            if (barr[0] == -47 && barr[1] == 32) {
                byte[] barr2 = new byte[barr.length - 2];
                System.arraycopy(barr, 2, barr2, 0, barr.length - 2);
                strDate = new String(barr2, StandardCharsets.UTF_8);

            }

            int endI = strDate.indexOf(' ', 3);
            if (endI > 0) {
                strDate = strDate.substring(0, endI);
            }
            try {
                dateEl = formatter.parse(strDate);
                dateEl.setYear(123);
                newListLessonsMap.put(el, dateEl);
            } catch (ParseException e) {
                System.out.println("");
            }
        }

        return newListLessonsMap;
    }


    public LessonComponent moveMouseByNameLessons(String nameLesson) {
        WebElement el = moveToElement(findElementByName(nameLesson));
        assertEquals("js-stats lessons__new-item lessons__new-item_hovered", el.getAttribute("class"));
        return this;
    }

    private WebElement getItemLATE(Map<WebElement, Date> lessMap) {

        Optional<Date> date = lessMap.values().stream()
                .reduce(
                        (f, s) ->
                                s.compareTo(f) < 0
                                        ? (Date) f
                                        : s);

        WebElement element = lessMap.entrySet().stream().filter(e -> e.getValue().equals(date.get())).findFirst().get().getKey();

        return element;
    }

    private WebElement getItemEARLY(Map<WebElement, Date> lessMap) {

        Optional<Date> date = lessMap.values().stream()
                .reduce(
                        (f, s) ->
                                s.compareTo(f) >= 0
                                        ? (Date) f
                                        : s);

        WebElement element = lessMap.entrySet().stream().filter(e -> e.getValue().equals(date.get())).findFirst().get().getKey();

        return element;
    }


}
