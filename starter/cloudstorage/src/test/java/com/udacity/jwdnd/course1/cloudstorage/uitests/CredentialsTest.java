package com.udacity.jwdnd.course1.cloudstorage.uitests;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.modal.AddCredentialsModal;
import com.udacity.jwdnd.course1.cloudstorage.page.tab.CredentialsTab;
import com.udacity.jwdnd.course1.cloudstorage.utils.NavigationHelper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class CredentialsTest extends AbstractUiTest{

    private static final String VALUE_ATTRIBUTE = "value";

    private static final String ORIGINAL_URL = "http://www.example.com";
    private static final String ORIGINAL_USERNAME = "username";
    private static final String ORIGINAL_PASSWORD = "password";

    private static final String EDITED_URL = "http://www.edited.com";
    private static final String EDITED_USERNAME = "edited-username";
    private static final String EDITED_PASSWORD = "edited-password";

    @Test
    public void given_correct_data_user_should_be_able_to_create_edit_and_delete_credentials(){
        testAddCredentials();

        testEditCredentials();

        testDeleteCredentials();
    }

    private void testDeleteCredentials() {
        CredentialsTab credentialsTab = new CredentialsTab(getDriver());

        ResultPage resultPage = credentialsTab.clickDeleteCredentialsButton();
        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        HomePage homePage = resultPage.clickContinueLink();

        // new object, because lambda syntax expects a variable has to be (effectively) final
        CredentialsTab newCredentialsTab = homePage.clickOnCredentialsTab();

        assertFalse(credentialsTab.getCredentialsTable().getText().contains(EDITED_URL));
        assertFalse(credentialsTab.getCredentialsTable().getText().contains(EDITED_USERNAME));
    }

    private void testEditCredentials() {
        CredentialsTab credentialsTab = new CredentialsTab(getDriver());
        AddCredentialsModal addCredentialsModal = credentialsTab.clickEditCredentialsButton();

        // decrypted password should appear
        assertTrue(addCredentialsModal.getPasswordInput().getAttribute(VALUE_ATTRIBUTE).contains(ORIGINAL_PASSWORD));

        ResultPage resultPage = addCredentialsModal.addCredentials(EDITED_URL, EDITED_USERNAME, EDITED_PASSWORD);

        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        HomePage homePage = resultPage.clickContinueLink();
        credentialsTab = homePage.clickOnCredentialsTab();

        assertTrue(credentialsTab.getCredentialsTable().getText().contains(EDITED_URL));
        assertTrue(credentialsTab.getCredentialsTable().getText().contains(EDITED_USERNAME));

        // decrypted password should not appear
        assertFalse(credentialsTab.getCredentialsTable().getText().contains(EDITED_PASSWORD));

        addCredentialsModal = credentialsTab.clickEditCredentialsButton();

        // decrypted password should appear in the edit view
        assertTrue(addCredentialsModal.getPasswordInput().getAttribute(VALUE_ATTRIBUTE).contains(EDITED_PASSWORD));

        addCredentialsModal.clickCloseAddCredentialsModal();
    }

    private void testAddCredentials() {
        NavigationHelper navigationHelper = new NavigationHelper(getDriver(), getBaseUrl(), getPort());
        HomePage homePage = navigationHelper.signUpLoginGoToHomePage(ORIGINAL_USERNAME, ORIGINAL_PASSWORD);

        CredentialsTab credentialsTab = homePage.clickOnCredentialsTab();

        AddCredentialsModal addCredentialsModal = credentialsTab.clickAddNewCredentialButton();
        ResultPage resultPage = addCredentialsModal.addCredentials(ORIGINAL_URL, ORIGINAL_USERNAME, ORIGINAL_PASSWORD);

        assertTrue(resultPage.getResultSuccessContainer().isDisplayed());

        homePage = resultPage.clickContinueLink();
        credentialsTab = homePage.clickOnCredentialsTab();

        assertTrue(credentialsTab.getCredentialsTable().getText().contains(ORIGINAL_URL));
        assertTrue(credentialsTab.getCredentialsTable().getText().contains(ORIGINAL_USERNAME));

        // should not appear in decrypted format -> assert that it's not there in encrypted format
        assertFalse(credentialsTab.getCredentialsTable().getText().contains(ORIGINAL_PASSWORD));
    }

}
