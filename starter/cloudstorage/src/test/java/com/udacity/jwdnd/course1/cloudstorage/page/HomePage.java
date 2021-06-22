package com.udacity.jwdnd.course1.cloudstorage.page;

import com.udacity.jwdnd.course1.cloudstorage.page.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "buttonUpload")
    private WebElement uploadButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUploadButton() {
        return uploadButton;
    }
}
