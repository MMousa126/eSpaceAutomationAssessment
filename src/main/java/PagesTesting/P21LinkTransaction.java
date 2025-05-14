package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class P21LinkTransaction {
    private final WebDriver driver;
    private final By searchableText = By.cssSelector("[formcontrolname = searchTransactionCode]");
    private final By selectType = By.cssSelector("[formcontrolname = searchTransactionType]");
    private final By searchBtn = By.xpath("(//button)[19]");
    private final By selectRelated = By.xpath("(//div/label)[6]");
    private final By selectMain = By.xpath("(//div/label)[7]");
    private final By linkBtn = By.xpath("(//button[@type = 'button'])[2]");
    private final By cancelBtn = By.xpath("//footer/button[2]");
    private final By saveBtn = By.xpath("//footer/button[1]");

    public P21LinkTransaction(WebDriver driver) {
        this.driver =driver;
    }

    public P21LinkTransaction enterTransactionNo(String transNo){
        Utility.clearField(driver,searchableText);
        Utility.SendData(driver,searchableText,transNo);
        return this;
    }

    public P21LinkTransaction selectTransactionType(String transType){
        if (transType.equals("وارد") || transType.equals("incoming"))
            Utility.SelectingFromDropDownByIndex(driver,selectType,1);
        else
            Utility.SelectingFromDropDownByIndex(driver,selectType,2);
        return this;
    }

    public P21LinkTransaction clickOnSearch(){
        Utility.waitUntilThePresenceOfElement(driver,searchBtn);
        Utility.Clicking_OnElementWithoutAnyWait(driver,searchBtn);
        return this;
    }

    public P21LinkTransaction selectRelationType(String linkType, String language){
        language = language.toLowerCase().replaceAll("\\s","");
        if (language.equals("english"))
        {
            linkType = linkType.toLowerCase().replaceAll("\\s","");
        }
        if (linkType.equals("relation") || linkType.equals("تابع")){
            Utility.waitUntilThePresenceOfElement(driver,selectRelated);
            Utility.Clicking_OnElement(driver,selectRelated);
        }else{
            Utility.waitUntilThePresenceOfElement(driver,selectMain);
            Utility.Clicking_OnElement(driver,selectMain);
        }
        return this;
    }

    public P21LinkTransaction clickOnLink(){
        Utility.Clicking_OnElement(driver,linkBtn);
        return this;
    }

    public P21LinkTransaction clickOnCancel(){
        Utility.Clicking_OnElement(driver,cancelBtn);
        return this;
    }

    public P12IncomingTransaction clickOnSaveLinking(){
        Utility.Clicking_OnElement(driver,saveBtn);
        return new P12IncomingTransaction(driver);
    }
}
