package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P02LandingPage {

    private final WebDriver driver;

    private final By logout = By.cssSelector("[alt = logout]");
    private final By systemSetBtn = By.xpath("(//img[@alt = 'buliding'])[11]");
    private final By incomingBtn = By.xpath("(//img[@alt = 'buliding'])[1]");


    public P02LandingPage(WebDriver driver) {

        this.driver = driver;
    }

    public P01Login clickOnLogout(){

        Utility.Clicking_OnElement(driver,logout);
        return new P01Login(driver);
    }

    public P03SystemSettings sysSettings(){

        Utility.Clicking_OnElement(driver,systemSetBtn);
        return new P03SystemSettings(driver);
    }

    public P11IncomingInbox clickOnIncomingInbox(){
        Utility.Clicking_OnElement(driver,incomingBtn);
        return new P11IncomingInbox(driver);
    }
}
