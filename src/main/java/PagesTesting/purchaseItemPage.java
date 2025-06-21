package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.*;

public class purchaseItemPage {

    private final WebDriver driver;
    private final By sendEmail = By.xpath("(//input[@id= 'customer-email'])[1]");
    private final By firstNameLoc = By.cssSelector("[name = firstname]");
    private final By lastNameLoc = By.cssSelector("[name = lastname]");
    private final By companyLoc = By.cssSelector("[name = company]");

    private final By streetAddressLoc = By.cssSelector("[name=\"street[0]\"]");
    private final By cityLoc = By.cssSelector("[name = city]");

    private final By stateRigonLoc = By.cssSelector("[name= region_id]");
    private final By postcodeLoc = By.cssSelector("[name= postcode ]");
    private final By telephoneLoc = By.cssSelector("[name= telephone]");

    private final By nextBtnLoc = By.cssSelector(".primary [data-role= opc-continue]");
    private final By redioBtn = By.xpath("//input[@class = 'radio']");
    public purchaseItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public purchaseItemPage enterEmail(String email){
        Utility.SendData(driver,sendEmail,email);
        return this;
    }

    public purchaseItemPage enterName(String firstName, String lastName){
        Utility.SendData(driver,firstNameLoc,firstName);
        Utility.SendData(driver,lastNameLoc,lastName);
        return this;
    }

    public purchaseItemPage enterCompany(String company){
        Utility.SendData(driver,companyLoc,company);
        return this;
    }

    public purchaseItemPage enterStreetAddress(String address){
        Utility.SendData(driver,streetAddressLoc,address);
        return this;
    }

    public purchaseItemPage enterCity(String city){
        Utility.SendData(driver,cityLoc,city);
        return this;
    }

    public purchaseItemPage selectRigion(String rigion){
        Utility.SelectingFromDropDownByText(driver,stateRigonLoc,rigion);
        return this;
    }

    public purchaseItemPage enterZipCode(String zipcode){
        Utility.SendData(driver,postcodeLoc,zipcode);
        return this;
    }

    public purchaseItemPage enterPhoneNo(String phoneNo){
        Utility.SendData(driver,telephoneLoc,phoneNo);
        return this;
    }

    public purchaseItemPage selectShoppingMethod(){
        Utility.Clicking_OnElementVisibility(driver,redioBtn);
        return this;
    }

    public paymentMethod clickOnNext(){
        Utility.Clicking_OnElement(driver,nextBtnLoc);
        return new paymentMethod(driver);
    }
}
