package PagesTesting;

import Utilities.Utility;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class viewItemsCard {
    private final WebDriver driver;
    private final By itemName = By.xpath("(//strong/a)[2]");
    private final By proceedToCheckout = By.cssSelector("[data-role = proceed-to-checkout]");

    public viewItemsCard(WebDriver driver) {
        this.driver = driver;
    }

    public String getitemName(){
        Utility.WaitUntilTheElementIsVisible(driver,itemName);
        return Utility.GetText(driver,itemName);
    }

    public purchaseItemPage clickOnProceed(){
        Utility.Clicking_OnElement(driver,proceedToCheckout);
        return new purchaseItemPage(driver);
    }


}
