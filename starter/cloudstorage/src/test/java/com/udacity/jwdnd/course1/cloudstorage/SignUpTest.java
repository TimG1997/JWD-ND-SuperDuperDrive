package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpTest extends AbstractUiTest{

    @Test
    public void given_valid_information_a_user_should_be_able_to_sign_up_and_login(){
        getDriver().get(getBaseUrl() + getPort() + "/signup");
        String username = "username";
        String password = "password";

        SignUpPage signUpPage = new SignUpPage(getDriver());
        signUpPage.signUp("firstname", "lastname", username, password);

        signUpPage.clickOnSignUpSuccessLoginLink();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        HomePage homePage = new HomePage(getDriver());

        String expectedHomePageTitle = "Home";
        String actualHomePageTitle = getDriver().getTitle();

        assertEquals(expectedHomePageTitle, actualHomePageTitle);
        assertTrue(homePage.getUploadButton().isDisplayed());
    }
}
