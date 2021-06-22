package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.model.dto.DecryptedCredentialsDTO;
import com.udacity.jwdnd.course1.cloudstorage.model.form.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class.getSimpleName());
    private static final String HOME_PAGE = "home";
    private static final String LOGOUT = "logout-success";
    private static final String REDIRECT_TO = "redirect:/";

    private static final String EMPTY = "";

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialsService credentialsService;
    private final UserService userService;

    private int userId;

    public HomeController(FileService fileService, NoteService noteService, CredentialsService credentialsService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomeView(Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());
        boolean invalidAuth = user == null;

        if (invalidAuth) {
            return REDIRECT_TO + LOGOUT;
        }

        userId = user.getId();

        File[] files = this.fileService.getFiles(userId);
        Note[] notes = this.noteService.getNotes(userId);
        List<DecryptedCredentialsDTO> credentials = this.credentialsService.getAllCredentials(userId);

        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("credentials", credentials);
        model.addAttribute("newNote", new NoteForm());
        model.addAttribute("newCredentials", new CredentialsForm());

        return HOME_PAGE;
    }

}
