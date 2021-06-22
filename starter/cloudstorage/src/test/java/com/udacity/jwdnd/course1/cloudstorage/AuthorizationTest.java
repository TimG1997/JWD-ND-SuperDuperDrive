package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorizationTest extends AbstractUiTest {

    @Test
    public void given_unauthorized_user_should_only_be_able_to_access_login_and_signup(){
        testThatUserCanAccessSignUp();
        testThatUserCanAccessLogin();
        testThatUserCannotAccessHome();
    }

    private void testThatUserCanAccessSignUp() {
        getDriver().get(getBaseUrl() + getPort() + "/signup");

        String expectedSignUpPageTitle = "Sign Up";
        String actualSignUpPageTitle = getDriver().getTitle();

        assertEquals(expectedSignUpPageTitle, actualSignUpPageTitle);
    }

    private void testThatUserCanAccessLogin(){
        getDriver().get(getBaseUrl() + getPort() + "/login");

        String expectedLoginPageTitle = "Login";
        String actualLoginPageTitle = getDriver().getTitle();

        assertEquals(expectedLoginPageTitle, actualLoginPageTitle);
    }

    private void testThatUserCannotAccessHome() {
        getDriver().get(getBaseUrl() + getPort() + "/home");

        String expectedLoginPageTitle = "Login";
        String actualLoginPageTitle = getDriver().getTitle();

        // should redirect to login page
        assertEquals(expectedLoginPageTitle, actualLoginPageTitle);
    }
}
