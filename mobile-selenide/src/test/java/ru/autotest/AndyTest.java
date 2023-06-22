package ru.autotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.autotest.component.popups.AlertComponent;
import ru.autotest.data.StartPageHeaderData;
import ru.autotest.extensions.AppiumExtension;
import ru.autotest.page.StartPage;

@ExtendWith(AppiumExtension.class)
public class AndyTest {

    @Test
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
    public void test1(){
        new StartPage()
                .open()
                .clickNextButton()
                .clickNextButton();
    }
}
