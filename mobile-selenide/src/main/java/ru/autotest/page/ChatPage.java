package ru.autotest.page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import ru.autotest.component.HeaderComponent;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ChatPage extends AbsBasePage<ChatPage> {

    @FindBy(css = ".android.widget.HorizontalScrollView")
    public HeaderComponent headerComponent;

    public ChatPage chatPageShouldOpened() {
        $("[text='Type a message...']").shouldBe(Condition.visible);
        return page(this);
    }

    public ChatPage unlockPremiumVisible() {
        $("[text=' Unlock Premium']").isDisplayed();
        return page(this);
    }



}
