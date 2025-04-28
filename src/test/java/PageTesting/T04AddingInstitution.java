package PageTesting;

import Assertion.AssertUtility;
import PagesTesting.P01Login;
import PagesTesting.P04institution;
import Utilities.DataFaker;
import Utilities.DataUtility;
import Utilities.LogsUtility;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static Factory.DriverFactory.*;

//@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})


public class T04AddingInstitution {
    private final String browser = "Browser";
    private final String browser_filename = "environment";
    private final String portal = "Portal";
    private final String landing = "landingPage";
    private final String Browser = DataUtility.GetPropertiesDataFromFile(browser_filename,browser);
    private final String nazahaPortal = DataUtility.GetPropertiesDataFromFile(browser_filename,portal);
    private final String landingPage = DataUtility.GetPropertiesDataFromFile(browser_filename,landing);

    @BeforeClass
    void setUp(){

        try {

            SetupThreadDriver(Browser);

            LogsUtility.LoggerInfo(Browser + " is Opened Correctly");
            GetThreadDriver().get(nazahaPortal);
            LogsUtility.LoggerInfo("Page is Redirected to the URL");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    void addingInstitutionLevel1(){

        String entityName = "Automated Level 1" + " "+ DataFaker.fakerApp()
                +DataFaker.generateRandomListOfNumbers(10,20,1);
        String entityCode = DataFaker.generateRandomListOfNumbers(0,10,5);
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .sysSettings()
                .clickOnInstitution()
                .clickOnAddingInstitution()
                .selectEntityType(6)
                .selectEntityLevel(1)
                .enterEntityName(entityName)
                .enterEntityCode(entityCode)
                .selectEntityRegion(1)
                .availableIncoming("No")
                .clickOnSave();

        DataUtility.writeDataIntoJsonFile("EntityData","Entity_Name",entityName);
        DataUtility.writeDataIntoJsonFile("EntityData","Entity_Code",entityCode);

        new AssertUtility(new SoftAssert())
                .assertTrue(new P04institution(GetThreadDriver()).btnExists());
    }

    @Test(priority = 2)
    void addingInstitutionLevel2(){
        String entityNameLvl2 = "Automated Level 2" + " "+ DataFaker.fakerApp();
        String entityLvl1Name = DataUtility.GetJsonDataFromFile("EntityData","Entity_Name");
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .sysSettings()
                .clickOnInstitution()
                .clickOnAddingInstitution()
                .selectEntityType(6)
                .selectEntityLevel(2)
                .selectEntityParent(entityLvl1Name)
                .enterEntityName(entityNameLvl2)
                .selectEntityRegion(1)
                .availableIncoming("No")
                .clickOnSave();

        DataUtility.writeDataIntoJsonFile("EntityData","Entity_NameLevel2",entityNameLvl2);

        new AssertUtility(new SoftAssert())
                .assertTrue(new P04institution(GetThreadDriver()).btnExists());
    }
    @Test(priority = 3)
    void addingInstitutionLevel3(){
        String entityNameLvl3 = "Automated Level 3" + " "+ DataFaker.fakerApp();
        String entityLvl2Name = DataUtility.GetJsonDataFromFile("EntityData","Entity_NameLevel2");
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .sysSettings()
                .clickOnInstitution()
                .clickOnAddingInstitution()
                .selectEntityType(6)
                .selectEntityLevel(3)
                .selectEntityParent(entityLvl2Name)
                .enterEntityName(entityNameLvl3)
                .selectEntityRegion(1)
                .availableIncoming("No")
                .clickOnSave();

        DataUtility.writeDataIntoJsonFile("EntityData","Entity_NameLevel2",entityNameLvl3);

        new AssertUtility(new SoftAssert())
                .assertTrue(new P04institution(GetThreadDriver()).btnExists());
    }
    @Test(priority = 4)
    void addingInstitutionInboxRedirect(){

        String entityName = "Automated Level 1" + " "+ DataFaker.fakerApp();
        String entityCode = DataFaker.generateRandomListOfNumbers(0,10,5);
        new P01Login(GetThreadDriver())
                .enterUsername("MohamedTest")
                .enterPassword("Moh@Test2025")
                .clickOnLogin()
                .sysSettings()
                .clickOnInstitution()
                .clickOnAddingInstitution()
                .selectEntityType(6)
                .selectEntityLevel(1)
                .enterEntityName(entityName)
                .enterEntityCode(entityCode)
                .selectEntityRegion(1)
                .availableIncoming("No")
                .clickOnSave()
                .clickOnMyInbox();

        DataUtility.writeDataIntoJsonFile("EntityData","Entity_Name",entityName);
        DataUtility.writeDataIntoJsonFile("EntityData","Entity_Code",entityCode);

        new AssertUtility(new SoftAssert())
                .assertTrue(new P04institution(GetThreadDriver()).btnExists());
    }

    @AfterMethod
    void quit()
    {
        QuitThreadDriver();
    }
}
