package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class searchResultPage {

    private final WebDriver driver;
    private final By validationMsg = By.cssSelector("div.message.notice > div");
    // getting the first title for the searched item
    private final By productListTitle = By.cssSelector(".product-item-info div a.product-item-link");


    public searchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    // Returning the title of the first searched Element
    // used for asserting
    public String getTitleOfSearchedItem(){
        Utility.WaitUntilTheElementIsVisible(driver,productListTitle);
        Utility.ScrollingUsingJS(driver,productListTitle);
        return Utility.GetText(driver,productListTitle);
    }

    // This Method for Clicking on the first Element and Direct user to the item page to process the checkout
    public itemPage clickOnSearchedItem(){
        Utility.WaitUntilTheElementIsVisible(driver,productListTitle);
        Utility.ScrollingUsingJS(driver,productListTitle);
        Utility.Clicking_OnElement(driver,productListTitle);
        return new itemPage(driver);
    }

    public String getValidationMSG(){
        Utility.WaitUntilTheElementIsVisible(driver,validationMsg);
        return Utility.GetText(driver,validationMsg);
    }

}
