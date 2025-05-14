package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P20BasicData {
    private final WebDriver driver;
    private final By asl = By.xpath("(//div[@id = 'transactionType']/label)[1]");
    private final By sora = By.xpath("(//div[@id = 'transactionType']/label)[2]");
    private final By royalDegreeYes = By.xpath("//div[@id='urgentRequest']/label[1]");
    private final By royalDegreeNo = By.xpath("//div[@id='urgentRequest']/label[2]");
    private final By urgentLevel = By.id("urgencyLevel");
    private final By confidentialLevel = By.id("confidentialityLevel");
    private final By importanceLevel = By.id("importanceLevel");
    private final By transTopic = By.id("topic");
    private final By entity = By.xpath("(//div[@id = 'source']/label[1])");
    private final By company = By.xpath("(//div[@id = 'source']/label[2])");
    private final By individual = By.xpath("(//div[@id = 'source']/label[2])");
    private final By save = By.xpath("//footer/button[1]");
    private final By cancel = By.xpath("//footer/button[2]");

    public P20BasicData(WebDriver driver) {
        this.driver = driver;
    }
    public String getCurrentUrl(){
        return Utility.getCurrentURL(driver);
    }

    public P20BasicData selectTransactionType(String type){
        if (type.equals("اصل")){
            Utility.Clicking_OnElement(driver,asl);
        }else
            Utility.Clicking_OnElement(driver,sora);

        return this;
    }


    public P20BasicData selectYesForRoyal(String yesNo){
        yesNo = yesNo.toLowerCase().replaceAll("\\s", "");
        if (yesNo.equals("yes")){
            Utility.Clicking_OnElement(driver,royalDegreeYes);
        }else
            Utility.Clicking_OnElement(driver,royalDegreeNo);
        return this;
    }

    public P20BasicData selectUrgentLevel(int option){
        if (option > 3 || option < 1)
            Utility.SelectingFromDropDownByIndex(driver,urgentLevel,3);
        else
            Utility.SelectingFromDropDownByIndex(driver,urgentLevel,option);
        return this;
    }

    public P20BasicData selectUrgentLevel(String option){

        option =  option.replaceAll("\\s", "");
        switch (option){
            case "عادي":
                Utility.SelectingFromDropDownByIndex(driver,urgentLevel,1);
                break;
            case "مستعجل":
                Utility.SelectingFromDropDownByIndex(driver,urgentLevel,2);
                break;
            default:
                Utility.SelectingFromDropDownByIndex(driver,urgentLevel,3);
        }
        return this;
    }

    public P20BasicData selectConfidentialLevel(String option){
        Utility.SelectingFromDropDownByText(driver,confidentialLevel,option);
        return this;
    }

    public P20BasicData selectImportantLevel(String option){

        option =  option.replaceAll("\\s", "");
        switch (option){
            case "عادي":
                Utility.SelectingFromDropDownByIndex(driver,importanceLevel,1);
                break;
            case "هام":
                Utility.SelectingFromDropDownByIndex(driver,importanceLevel,2);
                break;
            default:
                Utility.SelectingFromDropDownByIndex(driver,importanceLevel,3);
        }
        return this;
    }

    public P20BasicData selectImportantLevel(int option){
        if (option > 3 || option < 1)
            Utility.SelectingFromDropDownByIndex(driver,importanceLevel,3);
        else
            Utility.SelectingFromDropDownByIndex(driver,importanceLevel,option);
        return this;
    }

    public P20BasicData enterTransactionTopic(String topic){
        Utility.SendData(driver,transTopic,topic);
        return this;
    }

    public P200BasicDataEntity selectEntity(){
        Utility.Clicking_OnElement(driver,entity);
        return new P200BasicDataEntity(driver);
    }

    public P200BasicDataCompany selectCompany(){
        Utility.Clicking_OnElement(driver,company);
        return new P200BasicDataCompany(driver);
    }

    public P200BasicDataIndividual selectIndividual(){
        Utility.Clicking_OnElement(driver,individual);
        return new P200BasicDataIndividual(driver);
    }


    public P12IncomingTransaction clickOnSave(){

        Utility.Clicking_OnElement(driver,save);
        return new P12IncomingTransaction(driver);
    }


}
