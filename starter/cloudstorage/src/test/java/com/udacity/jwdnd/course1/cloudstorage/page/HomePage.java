package com.udacity.jwdnd.course1.cloudstorage.page;

import com.udacity.jwdnd.course1.cloudstorage.page.tab.CredentialsTab;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.NotesTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "buttonLogout")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage logout(){
        this.logoutButton.click();
        return new LoginPage(getDriver());
    }

    public NotesTab clickOnNotesTab(){
        this.navNotesTab.click();
        return new NotesTab(getDriver());
    }

    public CredentialsTab clickOnCredentialsTab(){
        this.navCredentialsTab.click();
        return new CredentialsTab(getDriver());
    }

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}
