package Testing;

import Utilities.DataUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedMethodListeners;
import Listeners.ITestResultListeners;

@Listeners({IInvokedMethodListeners.class, ITestResultListeners.class})
public class newLogin {


    private By button = By.cssSelector("button[class=dz-button]");
    WebDriver driver = new ChromeDriver();
    @BeforeMethod
    public void setup(){


        driver.get("https://www.facebook.com");
    }
    @Test
    public void uploadFile(){

//        driver.findElement(By.id("email")).sendKeys("djfksajl@gmail.com");
//
//        VisualUtility.TakingScreenShotVTExpected(driver,"screen");
//
//
//
//        Assert.assertTrue(VisualUtility.areImageEqual("newScreenshot","screen","diff"));

        String[] newHeader = {
                "ID", "Name","Department"
        };
        String[][] newData = {
                {"1", "John Doe", "HR"},
                {"2", "Jane Smith", "IT"},
                {"3", "Emily Davis", "Finance"}
        };
        DataUtility.createExcelFile("newFile","newSheet",newHeader,newData);



    }

}
