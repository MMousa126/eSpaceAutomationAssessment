package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.WebDriver;

public class P00MyInbox {
    private final WebDriver driver;

    public P00MyInbox(WebDriver driver) {
        this.driver=driver;
    }
    public String getCurrentURL(){
        return Utility.getCurrentURL(driver);
    }
//    public
}
