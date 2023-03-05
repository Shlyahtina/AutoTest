package ru.autotest.pageobjects.pages;

import com.google.inject.Inject;
import ru.autotest.annotations.MyNamePageObject;
import ru.autotest.annotations.UrlPage;
import ru.autotest.support.GuiceScoped;

@UrlPage("/")
@MyNamePageObject("Главная страница OTUS")
public class OtusMainPage extends AbstractPage<OtusMainPage> {

    @Inject
    public OtusMainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

}
