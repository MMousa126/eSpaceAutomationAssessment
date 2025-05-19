package PagesTesting;

import Utilities.Utility;
import com.aventstack.extentreports.templating.FreemarkerTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P23ArchiveFile {
    private final WebDriver driver;
    private final By archiveBtn = By.xpath("//footer[@class='form-footer']/button[1]");
    private final By addArchive = By.cssSelector("[class = upload-btn]");
    private final By addressTitle = By.xpath("//div[@class = 'item']/textarea");
    private final By saveArchive = By.xpath("//div[@class = 'modal-footer']/button");


    public P23ArchiveFile(WebDriver driver) {
        this.driver =driver;
    }


    public P23ArchiveFile clickOnAddArchive(){
        Utility.Clicking_OnElement(driver,archiveBtn);
        return this;
    }

    public P23ArchiveFile clickOnAddArchiveFromPopup(String filePath){
        Utility.uploadingFileUsingRobot(driver,addArchive,filePath);
        return this;
    }

    public P23ArchiveFile enterBrowsingName(String browseName){

        Utility.SendData(driver,addressTitle,browseName);
        return this;
    }

    public P12IncomingTransaction clickOnSaveArchive(){
        Utility.Clicking_OnElement(driver,saveArchive);
        return  new P12IncomingTransaction(driver);
    }





}
