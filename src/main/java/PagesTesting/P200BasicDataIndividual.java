package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P200BasicDataIndividual {
    private final WebDriver driver;
    private final By indName = By.xpath("//div[@class='item']/div/button");
    private final By searchInd = By.cssSelector("[class=form-control]");
    private final By selectTheTopOfTheSearch = By.xpath("//div[@class='list']/button[1]");
    private final By attachNo = By.id("attachmentsCount");
    private final By indDetails = By.id("entityDetails");

    public P200BasicDataIndividual(WebDriver driver) {
        this.driver = driver;
    }

    public P200BasicDataIndividual selectIndividual(String ind) {
        Utility.Clicking_OnElement(driver, indName);
        Utility.SendData(driver, searchInd, ind);
        Utility.Clicking_OnElement(driver, selectTheTopOfTheSearch);
        return this;
    }

    public P200BasicDataIndividual enterAttachNo(String noOfAttachment) {
        Utility.SendData(driver, attachNo, noOfAttachment);
        return this;
    }

    public P20BasicData enterIndDetails(String details) {
        Utility.ScrollingUsingJS(driver,indDetails);
        Utility.SendData(driver, indDetails, details);
        return new P20BasicData(driver);
    }


}