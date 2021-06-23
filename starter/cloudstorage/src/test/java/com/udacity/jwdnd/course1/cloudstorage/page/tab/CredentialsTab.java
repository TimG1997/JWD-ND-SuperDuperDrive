package com.udacity.jwdnd.course1.cloudstorage.page.tab;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.modal.AddCredentialsModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CredentialsTab extends HomePage {

    @FindBy(id = "add-a-new-credential-button")
    private WebElement addANewCredentialButton;

    @FindBy(id = "credentialTable")
    private WebElement credentialsTable;

    @FindBy(id = "edit-credentials-button")
    private WebElement editCredentialsButton;

    @FindBy(id = "delete-credentials-button")
    private WebElement deleteCredentialsButton;

    public CredentialsTab(WebDriver driver) {
        super(driver);
    }

    public AddCredentialsModal clickEditCredentialsButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.editCredentialsButton)).click();
        return new AddCredentialsModal(getDriver());
    }

    public AddCredentialsModal clickAddNewCredentialButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.addANewCredentialButton)).click();
        return new AddCredentialsModal(getDriver());
    }

    public WebElement getCredentialsTable() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.credentialsTable));
        return this.credentialsTable;
    }

    public ResultPage clickDeleteCredentialsButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.deleteCredentialsButton)).click();
        return new ResultPage(getDriver());
    }
}
