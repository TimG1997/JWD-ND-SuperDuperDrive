package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class.getSimpleName());

    private static final String USERNAME_ALREADY_EXISTS_ERROR_MSG = "The username already exists.";
    private static final String USERNAME_ALREADY_EXISTS_LOG_MSG = "A user tried to register with username {}, which already exists";
    private static final String SIGNUP_ERROR_MSG = "There was an error signing you up. Please try again.";

    private static final String SIGNUP_PAGE = "signup";

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpView() {
        return SIGNUP_PAGE;
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model) {
        String signupError = null;

        String username = user.getUsername();
        if (!userService.isUsernameAvailable(username)) {
            signupError = USERNAME_ALREADY_EXISTS_ERROR_MSG;
            LOG.info(USERNAME_ALREADY_EXISTS_LOG_MSG, username);
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);

            if (rowsAdded < 0) {
                signupError = SIGNUP_ERROR_MSG;
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return SIGNUP_PAGE;
    }

}
