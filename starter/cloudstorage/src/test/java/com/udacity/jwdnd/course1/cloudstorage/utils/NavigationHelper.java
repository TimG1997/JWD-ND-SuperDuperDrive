package com.udacity.jwdnd.course1.cloudstorage.utils;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    // URIs
    private static final String SIGN_UP_URI = "/signup";
    private static final String LOGIN_URI = "/login";
    private static final String HOME_URI = "/home";

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";

    private WebDriver driver;
    private String baseUrl;
    private Integer port;

    public NavigationHelper(WebDriver driver, String baseUrl, Integer port) {
        this.driver = driver;
        this.baseUrl = baseUrl;
        this.port = port;
    }

    public HomePage signUpLoginGoToHomePage(String username, String password){
        SignUpPage signUpPage = goToSignUpPage();
        signUpPage.signUp(FIRST_NAME, LAST_NAME, username, password);

        LoginPage loginPage = signUpPage.clickOnSignUpSuccessLoginLink();
        HomePage homePage = loginPage.login(username, password);

        return homePage;
    }

    public SignUpPage goToSignUpPage(){
        this.driver.get(this.baseUrl + this.port + SIGN_UP_URI);
        return new SignUpPage(this.driver);
    }

    public LoginPage goToLoginPage(){
        this.driver.get(this.baseUrl + this.port + LOGIN_URI);
        return new LoginPage(this.driver);
    }

    public HomePage goToHomePage(){
        this.driver.get(this.baseUrl + this.port + HOME_URI);
        return new HomePage(this.driver);
    }
}
