package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.form.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.udacity.jwdnd.course1.cloudstorage.controller.ResultPageAttributes.*;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private static final Logger LOG = LoggerFactory.getLogger(CredentialsController.class.getSimpleName());

    private static final String CREDENTIALS_UPDATE = "Credentials were updated.";
    private static final String CREDENTIALS_UPDATE_ERROR = "Error while updating credentials.";
    private static final String NEW_CREDENTIALS_ADDED = "Created new credentials";
    private static final String CREDENTIALS_ADD_ERROR = "Error while updating credentials.";
    private static final String COULD_NOT_CREATE_CREDENTIALS = "Could not create credentials. Please try again.";
    private static final String NO_URL_PROVIDED = "Credentials url was not provided.";
    private static final String NO_USERNAME_PROVIDED = "Credentials username was not provided.";
    private static final String NO_PASSWORD_PROVIDED = "Credentials password was not provided.";
    private static final String CREDENTIALS_DELETED = "Credentials were deleted.";
    private static final String CREDENTIALS_DELETE_ERROR = "Credentials could not be deleted.";
    private static final String CREDENTIALS_COULD_NOT_BE_DELETED = "Credentials could not be deleted. Please try again.";

    private final CredentialsService credentialsService;
    private final UserService userService;

    public CredentialsController(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @PostMapping
    public String addCredentials(Authentication auth, CredentialsForm newCredentials, Model model) {
        List<String> errors = new ArrayList<>();
        validateFields(newCredentials, errors);

        Integer userId = this.userService.getUser(auth.getName()).getId();
        Credentials credentials = this.credentialsService.getDecryptedCredentials(newCredentials.getId(), userId);
        boolean credentialsAlreadyExist = credentials != null;

        if (credentialsAlreadyExist) {
            updateCredentials(newCredentials, model, userId, credentials);
        } else {
            if (errors.isEmpty()) {
                addCredentials(newCredentials, model, userId);
            } else {
                model.addAttribute(ERROR_FLAG, true);
                model.addAttribute(ERRORS_LIST, errors);
            }
        }

        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(Authentication auth, @PathVariable("id") Integer id, Model model){
        Integer userId = userService.getUser(auth.getName()).getId();

        boolean deletionWasSuccessful = this.credentialsService.deleteCredentials(id, userId);

        if(deletionWasSuccessful){
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(CREDENTIALS_DELETED);
        } else {
            model.addAttribute(ERROR_FLAG, true);
            model.addAttribute(ERRORS_LIST, Arrays.asList(CREDENTIALS_COULD_NOT_BE_DELETED));

            LOG.error(CREDENTIALS_DELETE_ERROR);
        }

        return "result";
    }

    private void addCredentials(CredentialsForm newCredentials, Model model, Integer userId) {
        Credentials credentialsToCreate = new Credentials(newCredentials.getUrl(), newCredentials.getUsername(), newCredentials.getPassword(), userId);
        boolean wasAddingSuccessful = this.credentialsService.addCredentials(credentialsToCreate);

        if (wasAddingSuccessful) {
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(NEW_CREDENTIALS_ADDED);
        } else {
            model.addAttribute(ERROR_FLAG, true);
            model.addAttribute(ERRORS_LIST, Arrays.asList(COULD_NOT_CREATE_CREDENTIALS));

            LOG.error(CREDENTIALS_ADD_ERROR);
        }
    }

    private void validateFields(CredentialsForm newCredentials, List<String> errors) {
        boolean urlWasNotProvided = newCredentials.getUrl() == null || newCredentials.getUrl().isEmpty();
        boolean usernameWasNotProvided = newCredentials.getUsername() == null || newCredentials.getUsername().isEmpty();
        boolean passwordWasNotProvided = newCredentials.getPassword() == null || newCredentials.getPassword().isEmpty();

        if (urlWasNotProvided) {
            errors.add(NO_URL_PROVIDED);

            LOG.warn(NO_URL_PROVIDED);
        }

        if (usernameWasNotProvided) {
            errors.add(NO_USERNAME_PROVIDED);

            LOG.warn(NO_USERNAME_PROVIDED);
        }

        if (passwordWasNotProvided) {
            errors.add(NO_PASSWORD_PROVIDED);

            LOG.warn(NO_PASSWORD_PROVIDED);
        }
    }

    private void updateCredentials(CredentialsForm newCredentials, Model model, Integer userId, Credentials credentials) {
        Credentials credentialsToUpdate = new Credentials(newCredentials.getId(), newCredentials.getUrl(), newCredentials.getUsername(), credentials.getKey(), newCredentials.getPassword(), userId);
        boolean updateWasSuccessful = this.credentialsService.updateCredentials(credentialsToUpdate);

        if (updateWasSuccessful) {
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(CREDENTIALS_UPDATE);
        } else {
            model.addAttribute(ERROR_FLAG, true);

            LOG.error(CREDENTIALS_UPDATE_ERROR);
        }
    }
}
