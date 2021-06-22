package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.form.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
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
@RequestMapping("/notes")
public class NoteController {

    private static final Logger LOG = LoggerFactory.getLogger(NoteController.class.getSimpleName());

    private static final String NO_DESCRIPTION_PROVIDED = "Note description wasn't provided.";
    private static final String NO_TITLE_PROVIDED = "Note title wasn't provided.";
    private static final String COULD_NOT_ADD_NOTE = "Could not add new note. Please try again";
    private static final String NEW_NOTE_ADDED = "New note was added.";
    private static final String NOTE_DELETED = "Note was deleted";
    private static final String DELETE_NOTE_ERROR = "Error happened deleting note.";
    private static final String NOTE_UPDATE = "Note was updated.";
    private static final String NOTE_UPDATE_ERROR = "Note couldn't be updated.";
    private static final String NOTE_COULD_NOT_BE_DELETED = "Note could not be deleted.";

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String addNote(Authentication auth, NoteForm newNote, Model model) {
        List<String> errors = new ArrayList<>();
        validateFields(newNote, errors);

        Integer userId = userService.getUser(auth.getName()).getId();

        boolean noteAlreadyExists = newNote.getId() != null;

        if (noteAlreadyExists) {
            updateNote(newNote, model, userId);
        } else {
            if (errors.isEmpty()) {
                addNote(newNote, model, userId);
            } else {
                model.addAttribute(ERROR_FLAG, true);
                model.addAttribute(ERRORS_LIST, errors);
            }
        }

        return "result";
    }


    @GetMapping("/delete/{id}")
    public String deleteNote(Authentication auth, @PathVariable("id") Integer id, Model model) {
        Integer userId = userService.getUser(auth.getName()).getId();

        boolean deletionWasSuccessful = noteService.deleteNote(id, userId);

        if (deletionWasSuccessful) {
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(NOTE_DELETED);
        } else {
            model.addAttribute(ERROR_FLAG, true);
            model.addAttribute(ERRORS_LIST, Arrays.asList(NOTE_COULD_NOT_BE_DELETED));

            LOG.error(DELETE_NOTE_ERROR);
        }

        return "result";
    }

    private void validateFields(NoteForm newNote, List<String> errors) {
        boolean descriptionWasNotProvided = newNote.getDescription() == null || newNote.getDescription().isEmpty();
        boolean titleWasNotProvided = newNote.getTitle() == null || newNote.getTitle().isEmpty();

        if (descriptionWasNotProvided) {
            errors.add(NO_DESCRIPTION_PROVIDED);

            LOG.warn(NO_DESCRIPTION_PROVIDED);
        }

        if (titleWasNotProvided) {
            errors.add(NO_TITLE_PROVIDED);

            LOG.warn(NO_TITLE_PROVIDED);
        }
    }

    private void addNote(NoteForm newNote, Model model, Integer userId) {
        Note note = new Note(newNote.getTitle(), newNote.getDescription(), userId);
        boolean wasAddingSuccessful = noteService.addNote(note);

        if (wasAddingSuccessful) {
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(NEW_NOTE_ADDED);
        } else {
            model.addAttribute(ERROR_FLAG, true);
            model.addAttribute(ERRORS_LIST, Arrays.asList(COULD_NOT_ADD_NOTE));

            LOG.error(COULD_NOT_ADD_NOTE);
        }
    }

    private void updateNote(NoteForm newNote, Model model, Integer userId) {
        Note noteToUpdate = new Note(newNote.getId(), newNote.getDescription(), newNote.getTitle(), userId);
        boolean updateWasSuccessful = this.noteService.updateNote(noteToUpdate);

        if (updateWasSuccessful) {
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(NOTE_UPDATE);
        } else {
            model.addAttribute(ERROR_FLAG, true);

            LOG.error(NOTE_UPDATE_ERROR);
        }
    }


}
