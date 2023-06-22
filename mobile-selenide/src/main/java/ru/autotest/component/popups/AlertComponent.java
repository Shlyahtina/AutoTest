package ru.autotest.component.popups;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.netty.handler.codec.spdy.SpdyWindowUpdateFrame;
import org.openqa.selenium.By;
import ru.autotest.component.AbsBaseComponent;

import static com.codeborne.selenide.Selenide.$;

public class AlertComponent extends AbsPopupBase<AlertComponent> {

    private SelenideElement alertElement = $(By.id("android:id/parentPanel"));

    @Override
    public AlertComponent popupShouldVisible(){
        alertElement.shouldBe(Condition.visible);
        return this;
    }
    @Override
    public AlertComponent popupShouldNOTVisible() {
        alertElement.shouldNot(Condition.visible);
        return this;
    }
}
