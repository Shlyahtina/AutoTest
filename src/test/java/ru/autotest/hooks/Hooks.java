package ru.autotest.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import ru.autotest.support.GuiceScoped;

public class Hooks {

    @Inject
    private GuiceScoped guiceScoped;

    @After
    public void close(){
        if(guiceScoped.driver != null){
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
