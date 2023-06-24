package ru.autotest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.autotest.component.popups.AlertComponent;
import ru.autotest.data.StartPageHeaderData;
import ru.autotest.extensions.AppiumExtension;
import ru.autotest.page.ChatPage;
import ru.autotest.page.ExercisePage;
import ru.autotest.page.GrammarPage;
import ru.autotest.page.StartPage;

import static com.codeborne.selenide.Selenide.page;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(AppiumExtension.class)
@DisplayName("ТЕСТ: Проверям отображение блока <Заголовок> на страницах")
public class CheckHeaderTest {

    @Test
    @Order(1)
    @DisplayName("Шаг 1: Проверяем первую старницу")
    public void visibleStartPage() {
        StartPage startPage = new StartPage()
                .open()
                .startPageShouldOpened()
                .startPageTextSameAs(StartPageHeaderData.FIRST_PAGE_HEADER)
                .clickNextButton()
                .startPageTextSameAs(StartPageHeaderData.SECOND_PAGE_HEADER)
                .clickNextButton()
                .skipButtonShouldBeVisible();
        new AlertComponent()
                .popupShouldNOTVisible();
        startPage
                .clickSkipButton()
                .popupShouldVisible();
    }

    @Test
    @Order(2)
    @DisplayName("Шаг 2: Закрываем алерт")
    public void closeAlert(){
        new AlertComponent()
                .clickButtonOK()
                .chatPageShouldOpened();
    }

    @Test
    @Order(3)
    @DisplayName("Шаг 3: Проверяем заголовок на странице ChatPage")
    public void verifyVisibleHeaderByChatPage(){
        page(new ChatPage()).headerComponent.checkHeaderVisible();
    }

    @Test
    @Order(3)
    @DisplayName("Шаг 4: Проверяем заголовок на странице ExercisePage")
    public void verifyVisibleHeaderByExercisePage(){
        page(new ChatPage()).headerComponent.clickTabExercise().headerComponent.checkHeaderVisible();
    }

    @Test
    @Order(3)
    @DisplayName("Шаг 5: Проверяем заголовок на странице GrammarPage")
    public void verifyVisibleHeaderByGrammarPage(){
        page(new ExercisePage()).headerComponent.clickTabExercise().headerComponent.checkHeaderVisible();
    }

    @Test
    @Order(3)
    @DisplayName("Шаг 6: Проверяем заголовок на странице StatsPage")
    public void verifyVisibleHeaderByStatsPage(){
        page(new GrammarPage()).headerComponent.clickTabExercise().headerComponent.checkHeaderVisible();
    }
}
