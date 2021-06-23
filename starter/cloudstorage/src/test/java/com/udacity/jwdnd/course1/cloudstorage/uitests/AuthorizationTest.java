package com.udacity.jwdnd.course1.cloudstorage.uitests;

import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.NavigationHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationTest extends AbstractUiTest {

    private NavigationHelper navigationHelper;

    @BeforeEach
    public void beforeEach(){
        this.navigationHelper = new NavigationHelper(getDriver(), getBaseUrl(), getPort());
    }

    @Test
    public void given_unauthorized_user_should_only_be_able_to_access_login_and_signup(){
        SignUpPage signUpPage = this.navigationHelper.goToSignUpPage();
        assertTrue(signUpPage.getUsernameInput().isDisplayed());

        LoginPage loginPage = this.navigationHelper.goToLoginPage();
        assertTrue(loginPage.getUsernameInput().isDisplayed());

        this.navigationHelper.goToHomePage();
        assertTrue(loginPage.getUsernameInput().isDisplayed());
    }
}
