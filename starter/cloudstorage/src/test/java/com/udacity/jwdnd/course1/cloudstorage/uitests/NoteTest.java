package com.udacity.jwdnd.course1.cloudstorage.uitests;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.modal.AddNoteModal;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.NotesTab;
import com.udacity.jwdnd.course1.cloudstorage.utils.NavigationHelper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.*;

public class NoteTest extends AbstractUiTest {

    private static final String ORIGINAL_NOTE_TITLE = "Note-Title";
    private static final String ORIGINAL_NOTE_DESCRIPTION = "Note-Description";
    private static final String EDITED_NOTE_TITLE = "New-Note-Title";
    private static final String EDITED_NOTE_DESCRIPTION = "New-Note-Description";

    @Test
    public void given_correct_data_user_should_be_able_to_create_edit_and_delete_note() {
        testCreateNote();

        testEditNote();

        testDeleteNote();
    }

    private void testDeleteNote() {
        NotesTab notesTab = new NotesTab(getDriver());
        ResultPage resultPage = notesTab.clickDeleteNoteButton();
        resultPage.clickContinueLink();

        // notes table is not present
        assertThrows(TimeoutException.class, () -> notesTab.getNotesTable().getText().contains(EDITED_NOTE_DESCRIPTION));
    }

    private void testEditNote() {
        NotesTab notesTab = new NotesTab(getDriver());
        AddNoteModal addNoteModal = notesTab.clickEditNoteButton();
        ResultPage resultPage = addNoteModal.addNewNote(EDITED_NOTE_TITLE, EDITED_NOTE_DESCRIPTION);

        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        HomePage homePage = resultPage.clickContinueLink();
        notesTab = homePage.clickOnNotesTab();

        assertTrue(notesTab.getNotesTable().getText().contains(EDITED_NOTE_TITLE));
        assertTrue(notesTab.getNotesTable().getText().contains(EDITED_NOTE_DESCRIPTION));
    }

    private void testCreateNote() {
        NavigationHelper navigationHelper = new NavigationHelper(getDriver(), getBaseUrl(), getPort());
        HomePage homePage = navigationHelper.signUpLoginGoToHomePage(USERNAME, PASSWORD);

        NotesTab notesTab = homePage.clickOnNotesTab();

        AddNoteModal addNoteModal = notesTab.clickAddNoteButton();
        ResultPage resultPage = addNoteModal.addNewNote(ORIGINAL_NOTE_TITLE, ORIGINAL_NOTE_DESCRIPTION);

        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        homePage = resultPage.clickContinueLink();
        notesTab = homePage.clickOnNotesTab();

        assertTrue(notesTab.getNotesTable().getText().contains(ORIGINAL_NOTE_TITLE));
        assertTrue(notesTab.getNotesTable().getText().contains(ORIGINAL_NOTE_DESCRIPTION));
    }

}
