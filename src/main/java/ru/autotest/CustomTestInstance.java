package ru.autotest;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.autotest.annotations.Driver;
import ru.autotest.driverfactory.WebDriverFactory;
import ru.autotest.listeners.MouseListener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

public class CustomTestInstance implements BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {

    EventFiringWebDriver driver;

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        //TODO: добавить инфу если тест упал, скин приложить. перезапустить тест, который упал

    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {

        driver = new WebDriverFactory().getWebDriver();
        driver.register(new MouseListener());
        Set<Field> fields = getAnnotatedFields(Driver.class, context);

        for (Field field : fields) {
            if (field.getType().getName().equals(WebDriver.class.getName())) {
                AccessController.doPrivileged((PrivilegedAction<Void>)
                        () -> {
                            try {
                                field.setAccessible(true);
                                field.set(context.getTestInstance().get(), driver);
                            } catch (IllegalAccessException e) {
                                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                            }
                            return null;
                        }
                );
            }
        }
    }

    private Set<Field> getAnnotatedFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
        Set<Field> set = new HashSet<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    set.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }
        return set;
    }
}
