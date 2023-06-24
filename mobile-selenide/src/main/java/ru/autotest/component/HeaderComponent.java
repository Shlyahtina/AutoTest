package ru.autotest.component;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.autotest.page.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HeaderComponent extends ElementsContainer {

    @FindBy(css = "[text='Chat']")
    private SelenideElement tabChat;
    @FindBy(css = "[text='Exercise']")
    private SelenideElement tabExercise;
    @FindBy(css = "[text='Grammar']")
    private SelenideElement tabGrammar;
    @FindBy(css = "[text='Stats']")
    private SelenideElement tabStats;

    public ChatPage clickTabChat() {
        $("[text='Next']").click();
        return page(new ChatPage());
    }

    public ExercisePage clickTabExercise() {
        $("[text='Exercise']").click();
        return page(new ExercisePage());
    }

    public GrammarPage clickTabGrammar() {
        $("[text='Grammar']").click();
        return page(new GrammarPage());
    }

    public StatsPage clickTabStats() {
        $("[text='Stats']").click();
        return page(new StatsPage());
    }

    public void checkHeaderVisible() {
        getSelf().isDisplayed();
    }
}
