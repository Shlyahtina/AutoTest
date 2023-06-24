package ru.autotest.component.popups;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.autotest.page.ChatPage;

import static com.codeborne.selenide.Selenide.$;

public class AlertComponent extends AbsPopupBase<AlertComponent> {

    private SelenideElement alertElement = $(By.id("android:id/parentPanel"));
    private SelenideElement buttonOK = $(By.id("android:id/button1"));

    @Override
    public AlertComponent popupShouldVisible() {
        alertElement.shouldBe(Condition.visible);
        return this;
    }

    @Override
    public AlertComponent popupShouldNOTVisible() {
        alertElement.shouldNot(Condition.visible);
        return this;
    }

    public ChatPage clickButtonOK() {
        buttonOK.shouldBe(Condition.visible).click();
        return new ChatPage();
    }


    public boolean isPopupVisible() {
        return alertElement.isDisplayed();
    }

}
