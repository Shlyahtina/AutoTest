package ru.AutoTest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OtusMainPage {
    private final WebDriver driver;

    @FindBy(css = ".lessons a")
    private List<WebElement> elLessons;

    @FindBy(css = ".nav__items a")
    private List<WebElement> elCategory;

    public OtusMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public OtusMainPage clickLessonName(String nameLesson) {

       for(WebElement itemLesson: elLessons){
           if(itemLesson.getText().contains(nameLesson)){
              itemLesson.click();
              return this;
          }
       }
       return this;
    }

    public OtusMainPage  verifyCategory(){
            return null;
    }

}
