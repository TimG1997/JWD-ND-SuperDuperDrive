package com.udacity.jwdnd.course1.cloudstorage.page.modal;

import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.NotesTab;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNoteModal extends NotesTab {

    @FindBy(id = "note-title")
    private WebElement noteTitleInput;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id = "save-note-changes-button")
    private WebElement saveNoteChangesButton;

    public AddNoteModal(WebDriver driver) {
        super(driver);
    }

    public ResultPage addNewNote(String title, String description){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);

        wait.until(ExpectedConditions.elementToBeClickable(this.noteTitleInput)).clear();
        this.noteTitleInput.sendKeys(title);

        wait.until(ExpectedConditions.elementToBeClickable(this.noteDescriptionInput)).clear();
        this.noteDescriptionInput.sendKeys(description);

        wait.until(ExpectedConditions.elementToBeClickable(this.saveNoteChangesButton)).click();

        return new ResultPage(getDriver());
    }

}
