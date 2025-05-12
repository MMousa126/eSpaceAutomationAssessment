package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Objects;

public class P06Individual {

    private final WebDriver driver;
    private final By selectIdentityXpath = By.xpath("(//label[1])[2]");
    private final By selectResidentXpath = By.xpath("(//label[2])[1]");
    private final By nationalityId = By.id("nationalityId");
    private final By dofCss = By.cssSelector("[formcontrolname = birthDate]");
    private final By genderMaleXpath = By.xpath("(//label[1])[11]");
    private final By genderFemaleXpath = By.xpath("(//label[2])[2]");
    private final By idClass = By.className("selected");
    private final By idNumberId = By.id("identificationNumber");
    private final By firstNameId = By.id("firstName");
    private final By fatherNameId = By.id("fatherName");
    private final By grandfatherNameId = By.id("grandFatherName");
    private final By lastNameId = By.id("lastName");
    private final By mobNumberId = By.id("mobileNumber");
    private final By availableNo = By.xpath("//div[@id= 'isAvailableForIncomingTransaction']/label[2]");
    private final By availableYes = By.xpath("//div[@id= 'isAvailableForIncomingTransaction']/label[1]");
    private final By clickOnSave = By.cssSelector("[type= submit]:nth-of-type(1)");
    private final By myInboxBtn = By.xpath("(//button[@type='button'])[3]");
    private final By clickOnDismiss = By.xpath("(//button[@type='button'])[2]");
    private final By clickOnCancel = By.cssSelector("[type= button]:nth-of-type(2)");
    private final By dateOfBirth = By.cssSelector("[formcontrolname = birthDate]");
    private final By resident = By.xpath("//div[@id = 'citizenIdentifierType']/label[2]");
    private final By citizen = By.xpath("//div[@id = 'citizenIdentifierType']/label[1]");

    private final By[] byLocators = {
            idNumberId,
            firstNameId,
            fatherNameId,
            grandfatherNameId,
            lastNameId,
            mobNumberId,
            dateOfBirth
    };



    public P06Individual(WebDriver driver) {
        this.driver = driver;
    }

    public P06Individual selectIdentityType(String idType){

        idType = idType.toLowerCase().replaceAll("\\s", "");

        if (idType.equals("identity") || idType.equals("هوية")){
//            Utility.ScrollingUsingJS(driver,selectIdentityXpath);
            Utility.Clicking_OnElementWithVisibility(driver,selectIdentityXpath);
        }else{
//            Utility.ScrollingUsingJS(driver,selectResidentXpath);
            Utility.Clicking_OnElementWithVisibility(driver,selectResidentXpath);

        }

        return this;
    }

    public P06Individual enterIndividualData(String[] individualData){

        for (int i = 0; i < individualData.length;i++){
            Utility.ScrollingUsingJS(driver,byLocators[i]);
            Utility.SendData(driver,byLocators[i],individualData[i]);
        }
        return this;
    }

    public P06Individual selectIdentity(String identitytype ){

        if (Objects.equals(identitytype, "هوية") ||
                Objects.equals(identitytype, "identity")){
            Utility.Clicking_OnElement(driver,citizen);
        }else{
            Utility.Clicking_OnElement(driver,resident);
        }
        return this;
    }

//    public P06Individual enterDate(String date){
//
//        Utility.ScrollingUsingJS(driver,dateOfBirth);
//        Utility.SendData(driver,dateOfBirth,date);
//        return this;
//    }

    public P06Individual selectNationality(String nationality){
        Utility.ScrollingUsingJS(driver,nationalityId);
        int option = switch (nationality) {
            case "مصري" -> 2;
            case "اماراتي" -> 3;
            default -> 1;
        };

        Utility.SelectingFromDropDownByIndex(driver,nationalityId, option);
        return this;
    }

    public P06Individual selectGender (String gender) {
        if (gender.equals("Male") || gender.equals("ذكر")) {
            Utility.Clicking_OnElement(driver, genderMaleXpath);
        }
        else
            Utility.Clicking_OnElement(driver,genderFemaleXpath);

        return this;
    }

//    public P06Individual enterDOB(String dob){
//        Utility.ScrollingUsingJS(driver,dofCss);
//        Utility.SendData(driver,dofCss,dob);
//        return this;
//    }
    public P06Individual availableIncoming(String des){
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

    public P06Individual clickOnSave(){
        Utility.Clicking_OnElement(driver,clickOnSave);
        return this;
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
