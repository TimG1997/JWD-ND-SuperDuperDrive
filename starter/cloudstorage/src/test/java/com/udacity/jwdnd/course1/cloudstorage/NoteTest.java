package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.modal.AddNoteModal;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.NotesTab;
import com.udacity.jwdnd.course1.cloudstorage.utils.NavigationHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTest extends AbstractUiTest{

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String NOTE_TITLE = "Note-Title";
    private static final String NOTE_DESCRIPTION = "Note-Description";

    private NavigationHelper navigationHelper;

    @BeforeEach
    public void beforeEach(){
        this.navigationHelper = new NavigationHelper(getDriver(), getBaseUrl(), getPort());
    }

    @Test
    public void given_correct_data_should_create_note(){
        HomePage homePage = this.navigationHelper.signUpLoginGoToHomePage(USERNAME, PASSWORD);
        NotesTab notesTab = homePage.clickOnNotesTab();

        AddNoteModal addNoteModal = notesTab.clickAddNoteButton();
        ResultPage resultPage = addNoteModal.addNewNote(NOTE_TITLE, NOTE_DESCRIPTION);

        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        homePage = resultPage.clickContinueLink();
        notesTab = homePage.clickOnNotesTab();

        assertTrue(notesTab.getNotesTable().getText().contains(NOTE_TITLE));
        assertTrue(notesTab.getNotesTable().getText().contains(NOTE_DESCRIPTION));
    }

}
