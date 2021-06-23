package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.NavigationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpTest extends AbstractUiTest {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private NavigationHelper navigationHelper;

    @BeforeEach
    public void beforeEach() {
        this.navigationHelper = new NavigationHelper(getDriver(), getBaseUrl(), getPort());
    }

    @Test
    public void given_valid_information_a_user_should_be_able_to_sign_up_and_login() {

        // sign up

        SignUpPage signUpPage = this.navigationHelper.goToSignUpPage();
        signUpPage.signUp(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD);

        // login

        LoginPage loginPage = signUpPage.clickOnSignUpSuccessLoginLink();
        HomePage homePage = loginPage.login(USERNAME, PASSWORD);

        assertTrue(homePage.getLogoutButton().isDisplayed());

        // logout

        loginPage = homePage.logout();

        assertTrue(loginPage.getUsernameInput().isDisplayed());

        // try to access home page

        navigationHelper.goToHomePage();

        assertTrue(loginPage.getUsernameInput().isDisplayed());
    }
}
