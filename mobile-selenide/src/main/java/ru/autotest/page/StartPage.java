package ru.autotest.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.autotest.component.popups.AlertComponent;
import ru.autotest.data.StartPageHeaderData;

import static com.codeborne.selenide.Selenide.$;

public class StartPage extends AbsBasePage<StartPage> {

    private SelenideElement skipButton = $("[text='Skip >']");

    public StartPage clickNextButton() {
        $("[text='Next']").click();
        return this;
    }

    public StartPage startPageShouldOpened() {
        $(By.id("android:id/content")).shouldBe(Condition.visible);
        return this;
    }

    public boolean isOpenStartPage(StartPageHeaderData exText) {
        return $(String.format("[text='%s']", exText.getHeader())).isDisplayed();
    }


    public StartPage startPageTextSameAs(StartPageHeaderData exText) {
        $(String.format("[text='%s']", exText.getHeader())).shouldBe(Condition.visible);
        return this;
    }

    public StartPage skipButtonShouldBeVisible() {
        skipButton.shouldBe(Condition.visible);
        return this;
    }

    public AlertComponent clickSkipButton() {
        skipButton.click();
        return new AlertComponent();
    }
}
