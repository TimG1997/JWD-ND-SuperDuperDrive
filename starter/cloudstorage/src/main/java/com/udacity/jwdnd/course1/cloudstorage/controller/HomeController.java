package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
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
    private final UserService userService;

    private int userId;

    public HomeController(FileService fileService, NoteService noteService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
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

        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("notes", noteService.getNotes(userId));
        model.addAttribute("newNote", new NoteForm());

        return HOME_PAGE;
    }

}
