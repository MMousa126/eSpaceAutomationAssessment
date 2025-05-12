package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class P05Company {
    private final WebDriver driver;
    private final By companyNameId = By.id("nonGovernmentalAgencyName");
    private final By commercialNoId = By.id("commercialRegistrationNumber");
    private final By availableNo = By.xpath("//label[@for = 'availableNo']");
    private final By availableYes = By.xpath("//label[@for = 'availableYes']");
    private final By clickOnSave = By.cssSelector("[type= button]:nth-of-type(1)");
    private final By myInboxBtn = By.xpath("(//button[@type='button'])[3]");    private final By clickOnDismiss = By.xpath("(//button[@type='button'])[2]");
    private final By clickOnCancel = By.cssSelector("[type= button]:nth-of-type(2)");



    public P05Company(WebDriver driver) {
        this.driver = driver;
    }

    public P05Company enterCompanyData(String companyName, String commercialRegistrationNo){

        Utility.SendData(driver,companyNameId,companyName);
        Utility.SendData(driver,commercialNoId,commercialRegistrationNo);

        return this;
    }

    public P05Company availableIncoming(String des){
        des = des.toLowerCase().replaceAll("\\s", "");

        if (des.equals("yes") || des.equals("نعم")){
            Utility.ScrollingUsingJS(driver,availableYes);
            Utility.Clicking_OnElementWithVisibility(driver,availableYes);
        }else{
            Utility.ScrollingUsingJS(driver,availableNo);
            Utility.Clicking_OnElementWithVisibility(driver,availableNo);

        }
        return this;
    }

    public P05Company clickOnSave(){
        Utility.Clicking_OnElement(driver,clickOnSave);
        return this;
    }

    public P00MyInbox clickOnMyInbox(){

        Utility.Clicking_OnElement(driver,myInboxBtn);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new P00MyInbox(driver);
    }

    public String getCurrentURL(){
        return Utility.getCurrentURL(driver);
    }

    public P03SystemSettings clickOnDismissBtn(){
        Utility.Clicking_OnElement(driver,clickOnDismiss);
        return new P03SystemSettings(driver);
    }

    public P03SystemSettings clickOnCancel(){
        Utility.Clicking_OnElement(driver,clickOnCancel);
        return new P03SystemSettings(driver);
    }
}
