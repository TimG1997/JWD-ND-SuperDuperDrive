package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage extends AbstractPage{

    @FindBy(id = "result-success-container")
    private WebElement resultSuccessContainer;

    @FindBy(id = "continue-link")
    private WebElement continueLink;

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getResultSuccessContainer() {
        return resultSuccessContainer;
    }

    public HomePage clickContinueLink(){
        this.continueLink.click();
        return new HomePage(getDriver());
    }
}
