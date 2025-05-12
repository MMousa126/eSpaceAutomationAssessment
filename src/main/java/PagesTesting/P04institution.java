package PagesTesting;

import Utilities.Utility;
import Utilities.LogsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class P04institution {
    private final WebDriver driver;
    private final By entityType = By.id("agencyTypeId");
    private final By entityLevels = By.id("agencyLevelId");
    private final By entityNames = By.id("agencyName");
    private final By entityCoding = By.id("code");
    private final By entityRegions = By.id("agencyRegionId");
    private final By parentId = By.id("parentAgencyId");
    private final By clickOnSave = By.cssSelector("[type= button]:nth-of-type(1)");
    private final By clickOnCancel = By.cssSelector("[type= button]:nth-of-type(2)");
    private final By availableNo = By.cssSelector("[for = 'availableNo']");
    private final By availableYes = By.cssSelector("[for = 'availableYes']");
    private final By myInboxBtn = By.xpath("(//button[@type='button'])[3]");
    private final By clickOnDismiss = By.xpath("(//button[@type='button'])[2]");


    public P04institution(WebDriver driver) {
        this.driver =driver;
    }

    public P04institution selectEntityType(int entityIndex){

        Utility.refreshPage(driver);
        if (entityIndex > 19|| entityIndex == 0){
            LogsUtility.LoggerWarn("Out of The Boundary !!");
            LogsUtility.LoggerInfo("Out of The Boundary !!");
        }else {
            Utility.ScrollingUsingJS(driver,entityType);
            Utility.SelectingFromDropDownByIndex(driver,entityType,entityIndex);
        }
        return this;
    }

    public P04institution selectEntityLevel(int entityLevel){

        if (entityLevel > 3|| entityLevel == 0){
            LogsUtility.LoggerWarn("Out of the Boundary !!");
            LogsUtility.LoggerInfo("Out of The Boundary !!");

        }else {
            Utility.ScrollingUsingJS(driver,entityLevels);
            Utility.SelectingFromDropDownByIndex(driver,entityLevels,entityLevel);
            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this;
    }

    public P04institution selectEntityRegion(int entityRegion){

        if (entityRegion > 1 || entityRegion == 0){
            LogsUtility.LoggerWarn("Out of the Boundary !!");
            LogsUtility.LoggerInfo("Out of The Boundary !!");
        }else{
            Utility.ScrollingUsingJS(driver,entityRegions);
            Utility.SelectingFromDropDownByIndex(driver,entityRegions,entityRegion);
        }
        return this;
    }
    public P04institution selectEntityParent(int entityParent){

        Utility.ScrollingUsingJS(driver,parentId);
        Utility.SelectingFromDropDownByIndex(driver,parentId,entityParent);

        return this;
    }
    public P04institution selectEntityParent(String entityParent){

        Utility.ScrollingUsingJS(driver,parentId);
        Utility.SelectingFromDropDownByText(driver,parentId,entityParent);

        return this;
    }

    public P04institution enterEntityName (String entityName){
        Utility.SendData(driver,entityNames,entityName);
        return this;
    }
    public P04institution enterEntityCode (String entityCode){

        Utility.SendData(driver,entityCoding,entityCode);
        return this;
    }

    public P04institution availableIncoming(String des){
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

    public P04institution clickOnSave(){
        Utility.Clicking_OnElement(driver,clickOnSave);
        return this;
    }

    public P03SystemSettings clickOnDismissBtn(){
        Utility.Clicking_OnElement(driver,clickOnDismiss);
        return new P03SystemSettings(driver);
    }

    public P03SystemSettings clickOnCancel(){
        Utility.Clicking_OnElement(driver,clickOnCancel);
        return new P03SystemSettings(driver);
    }

    public P10MyInbox clickOnMyInbox(){

        Utility.Clicking_OnElement(driver,myInboxBtn);
        try {
            Thread.sleep(Duration.ofSeconds(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new P10MyInbox(driver);
    }

    public boolean btnExists(){
        return Utility.isElementPresent(driver,myInboxBtn);
    }
}
