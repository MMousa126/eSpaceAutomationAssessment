package PagesTesting;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P10MyInbox {
    private final WebDriver driver;
    private final By myInbox = By.xpath("(//a[@routerlinkactive = 'active'])[1]");

    public P10MyInbox(WebDriver driver) {
        this.driver=driver;
    }
    public String getCurrentURL(){
        return Utility.getCurrentURL(driver);
    }
//    public
}
