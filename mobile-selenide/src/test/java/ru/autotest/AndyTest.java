package ru.autotest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.autotest.extensions.AppiumExtension;
import ru.autotest.page.MainPage;

@ExtendWith(AppiumExtension.class)
public class AndyTest {

    @Test
    public void visibleStartPage(){
        new MainPage().open();
    }
}
