package com.udacity.jwdnd.course1.cloudstorage.page.modal;

import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.CredentialsTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddCredentialsModal extends CredentialsTab {

    @FindBy(id = "credential-url")
    private WebElement urlInput;

    @FindBy(id = "credential-username")
    private WebElement usernameInput;

    @FindBy(id = "credential-password")
    private WebElement passwordInput;

    @FindBy(id = "save")
    private WebElement saveChangesButton;

    @FindBy(id = "close-credential-modal-button")
    private WebElement closeCredentialModalButton;

    public AddCredentialsModal(WebDriver driver) {
        super(driver);
    }

    public ResultPage addCredentials(String url, String username, String password){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.urlInput)).clear();
        this.urlInput.sendKeys(url);

        wait.until(ExpectedConditions.elementToBeClickable(this.usernameInput)).clear();
        this.usernameInput.sendKeys(username);

        wait.until(ExpectedConditions.elementToBeClickable(this.passwordInput)).clear();
        this.passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(this.saveChangesButton)).click();

        return new ResultPage(getDriver());
    }

    public WebElement getPasswordInput() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.passwordInput));
        return passwordInput;
    }

    public CredentialsTab clickCloseAddCredentialsModal(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.closeCredentialModalButton)).click();
        return new CredentialsTab(getDriver());
    }
}
