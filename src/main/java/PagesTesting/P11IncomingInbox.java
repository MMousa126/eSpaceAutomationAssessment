package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P11IncomingInbox {


    private final WebDriver driver;
    private final By dep1 = By.xpath("(//a[@routerlinkactive = 'active'])[2]");
    private final By dep2 = By.xpath("(//a[@routerlinkactive = 'active'])[3]");
    private final By regTrans = By.id("dropdownCreateTransaction");
    private final By incomingTrans = By.xpath("(//button[@class = 'dropdown-item'])[2]");
    private final By internalIncomingTrans = By.xpath("(//button[@class = 'dropdown-item'])[3]");



    public P11IncomingInbox(WebDriver driver) {
        this.driver = driver;
    }

    public P11IncomingInbox selectDep1(){
        Utility.Clicking_OnElement(driver,dep1);
        return this;
    }

    public P11IncomingInbox selectDep2(){
        Utility.Clicking_OnElement(driver,dep2);
        return this;
    }


    public P11IncomingInbox openDDLRegisterIncomingTrans(){
        Utility.Clicking_OnElement(driver,regTrans);
        return this;
    }


    public P12IncomingTransaction clickOnRegIncomingTrans(){
        Utility.Clicking_OnElement(driver,incomingTrans);
        return new P12IncomingTransaction(driver);
    }

    public P12IncomingTransaction clickOnRegInternalIncomingTrans(){
        Utility.Clicking_OnElement(driver,internalIncomingTrans);
        return new P12IncomingTransaction(driver);
    }

}
