package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class itemSearch {

    private final WebDriver driver;
    private final By searhBox = By.id("search");
    private final By searchBtn = By.cssSelector("[title = Search]");

    public itemSearch(WebDriver driver) {
        this.driver = driver;
    }


    public itemSearch enterSearchableItem(String searchableIteam){
        Utility.SendData(driver,searhBox,searchableIteam);
        return this;
    }

    public searchResultPage clickOnSearchBtn(){
        Utility.Clicking_OnElement(driver,searchBtn);
        return new searchResultPage(driver);
    }



}
