package com.udacity.jwdnd.course1.cloudstorage.page.tab;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.modal.AddNoteModal;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotesTab extends HomePage {

    @FindBy(id = "add-a-new-note-button")
    private WebElement addANewNoteButton;

    @FindBy(id = "btnEditNote")
    private WebElement editNoteButton;

    @FindBy(id = "buttonNoteDelete")
    private WebElement buttonNoteDelete;

    @FindBy(id = "notesTable")
    private WebElement notesTable;

    public NotesTab(WebDriver driver) {
        super(driver);
    }

    public AddNoteModal clickEditNoteButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.editNoteButton)).click();
        return new AddNoteModal(getDriver());
    }

    public ResultPage clickDeleteNoteButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.buttonNoteDelete)).click();
        return new ResultPage(getDriver());
    }

    public AddNoteModal clickAddNoteButton(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.elementToBeClickable(this.addANewNoteButton)).click();
        return new AddNoteModal(getDriver());
    }

    public WebElement getNotesTable() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.visibilityOf(notesTable));
        return notesTable;
    }
}
