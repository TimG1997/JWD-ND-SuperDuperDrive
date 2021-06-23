package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends AbstractPage{

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "buttonSignUp")
    private WebElement signUpButton;

    @FindBy(id = "signup-success-login-link")
    private WebElement signupSuccessLoginLink;

    public SignUpPage(WebDriver driver){
        super(driver);
    }

    public void signUp(String firstName, String lastName, String username, String password){
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);

        this.signUpButton.click();
    }

    public LoginPage clickOnSignUpSuccessLoginLink(){
        this.signupSuccessLoginLink.click();
        return new LoginPage(getDriver());
    }

    public WebElement getUsernameInput() {
        return usernameInput;
    }
}
