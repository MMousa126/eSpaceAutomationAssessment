package Pages;

import Utilities.Utility;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class P03UserManagment {
    private final WebDriver driver;

    private final By searchBoxAdmin = By.cssSelector("div .oxd-form-row .oxd-input.oxd-input--active");
    private final By searchButton = By.cssSelector("[type = submit]");
    private final By usernameLoc = By.xpath("//div[@class = 'oxd-table-card']/div/div[2]");
    private final By userRoleLoc = By.xpath("//div[@class = 'oxd-table-card']/div/div[3]");
    private final By statusLoc = By.xpath("//div[@class = 'oxd-table-card']/div/div[5]");
    private final By deleteBtn = By.cssSelector(".oxd-table-cell-actions button:nth-of-type(1)");
    private final By toastDelete = By.id("oxd-toaster_1");

    private final By noOfResultLoc = By.cssSelector("div.orangehrm-horizontal-padding.orangehrm-vertical-padding span");

    public P03UserManagment(WebDriver driver) {
        this.driver = driver;
    }

    public P03UserManagment enterSearchUser(String users){
        Utility.SendData(driver,searchBoxAdmin,users);
        return this;
    }



    public P03UserManagment clickOnSearchBtn(){
        Utility.Clicking_OnElement(driver,searchButton);
        return this;
    }

    public P03UserManagment clickOnDelete(){
        Utility.Clicking_OnElement(driver,deleteBtn);
        return this;
    }

    public String getAlertText(){
        return Utility.GetText(driver,toastDelete);
    }



    public String getUsername(){

        // Changing Screen sizes to find Locator
        driver.manage().window().setSize(new Dimension(1366, 768)); // desktop size

        return Utility.GetText(driver,usernameLoc);
    }

    public String getUserRole(){
        return Utility.GetText(driver,userRoleLoc);
    }

    public String getStatus(){
        return Utility.GetText(driver,statusLoc);
    }

    public String getNoOfResults(){

        String number = Utility.GetText(driver,noOfResultLoc);
        number = Utility.extractNumberAsString(number);
        return number;
    }
}
