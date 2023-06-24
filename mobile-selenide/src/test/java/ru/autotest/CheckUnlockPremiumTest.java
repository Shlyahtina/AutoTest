package ru.autotest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.autotest.component.popups.AlertComponent;
import ru.autotest.data.StartPageHeaderData;
import ru.autotest.extensions.AppiumExtension;
import ru.autotest.page.ChatPage;
import ru.autotest.page.ExercisePage;
import ru.autotest.page.StartPage;

import static com.codeborne.selenide.Selenide.page;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(AppiumExtension.class)
@DisplayName("ТЕСТ: Проверям отображение блока <UnlockPremium> на страницах")
public class CheckUnlockPremiumTest {

    @Test
    @Order(1)
    @DisplayName("Шаг 1: Проверяем первую старницу")
    public void visibleStartPage() {
        StartPage startPage = new StartPage()
                .open();

        if (startPage.isOpenStartPage(StartPageHeaderData.FIRST_PAGE_HEADER)) {

            startPage.startPageShouldOpened()
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
    }

    @Test
    @Order(2)
    @DisplayName("Шаг 2: Закрываем алерт")
    public void closeAlert() {
        AlertComponent alertComponent = new AlertComponent();
        if (alertComponent.isPopupVisible()) {
            alertComponent.clickButtonOK()
                    .chatPageShouldOpened();
        }
    }

    @Test
    @Order(3)
    @DisplayName("Шаг 3: Проверяем отображение блока UnlockPremium на странице ChatPage")
    public void verifyVisibleUnlockPremiumByChatPage() {
        new ChatPage()
                .unlockPremiumVisible();
    }

    @Test
    @Order(4)
    @DisplayName("Шаг 4: Проверяем отображение блока UnlockPremium на странице ExercisePage")
    public void verifyVisibleUnlockPremiumByExercisePage() {
        page(new ChatPage()).headerComponent.clickTabExercise()
                .exercisePageTextSameAs("Learn 5 new words today")
                .unlockPremiumVisible();
    }

    @Test
    @Order(5)
    @DisplayName("Шаг 5: Проверяем отображение блока UnlockPremium на странице GrammarPage")
    public void verifyVisibleUnlockPremiumByGrammarPage() {
        page(new ExercisePage()).headerComponent.clickTabGrammar()
                .exercisePageTextSameAs("BASIC")
                .unlockPremiumVisible();
    }

    @Test
    @Order(6)
    @DisplayName("Шаг 6: Проверяем отображение блока UnlockPremium на странице StatsPage")
    public void verifyVisibleUnlockPremiumByStatsPage() {
        page(new ExercisePage()).headerComponent.clickTabStats()
                .exercisePageTextSameAs("WORDS WRITTEN")
                .unlockPremiumVisible();
    }

}
