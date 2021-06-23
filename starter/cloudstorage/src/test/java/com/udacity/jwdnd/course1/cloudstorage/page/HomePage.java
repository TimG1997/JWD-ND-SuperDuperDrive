package com.udacity.jwdnd.course1.cloudstorage.page;

import com.udacity.jwdnd.course1.cloudstorage.page.tab.NotesTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(id = "buttonLogout")
    private WebElement logoutButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

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

    public WebElement getLogoutButton() {
        return logoutButton;
    }
}
