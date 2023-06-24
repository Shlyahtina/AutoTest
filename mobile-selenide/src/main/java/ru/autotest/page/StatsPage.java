package ru.autotest.page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import ru.autotest.component.HeaderComponent;

import static com.codeborne.selenide.Selenide.$;

public class StatsPage extends AbsBasePage<StatsPage>{

    @FindBy(css = ".android.widget.HorizontalScrollView")
    HeaderComponent headerComponent;

    public StatsPage exercisePageTextSameAs(String exText){
        $(String.format("[text='%s']",exText)).shouldBe(Condition.visible);
        return this;
    }

    public StatsPage unlockPremiumVisible() {
        $("[text=' Unlock Premium']").isDisplayed();
        return this;
    }
}
