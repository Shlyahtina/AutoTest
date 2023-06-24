package ru.autotest.page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import ru.autotest.component.HeaderComponent;

import static com.codeborne.selenide.Selenide.$;

public class GrammarPage extends AbsBasePage<GrammarPage> {

    @FindBy(css = ".android.widget.HorizontalScrollView")
    public HeaderComponent headerComponent;

    public GrammarPage exercisePageTextSameAs(String exText) {
        $(String.format("[text='%s']", exText)).shouldBe(Condition.visible);
        return this;
    }

    public GrammarPage unlockPremiumVisible() {
        $("[text=' Unlock Premium']").isDisplayed();
        return this;
    }
}
