package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class P11IncomingInbox {


    private final WebDriver driver;
    private final By dep1 = By.xpath("(//a[@routerlinkactive = 'active'])[2]");
    private final By dep2 = By.xpath("(//a[@routerlinkactive = 'active'])[3]");
    private final By regTrans = By.id("dropdownCreateTransaction");
    private final By incomingTrans = By.xpath("(//button[@class = 'dropdown-item'])[2]");
    private final By internalIncomingTrans = By.xpath("(//button[@class = 'dropdown-item'])[3]");
    private final By searchBox = By.xpath("//div[@class='transactions-list']/header/div/input");
    private final By searchBtn = By.xpath("(//div[@class='transactions-list']/header/div/button)[1]");
    private final By stateDDL = By.id("dropdownTransactionStatus");
    private final By sortingBtn = By.xpath("//div[@class='transactions-list']/header/button");



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

    public P11IncomingInbox enterSearchableDataIntoBox(String transNo){
        Utility.SendData(driver,searchBox,transNo);
        return this;
    }

    public P11IncomingInbox clickOnSearchBtn(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver -> driver.findElement(searchBtn).isEnabled());
        Utility.Clicking_OnElement(driver,searchBtn);
        return this;
    }


}
