package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.service.FileService;
import com.udacity.jwdnd.course1.cloudstorage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.udacity.jwdnd.course1.cloudstorage.controller.ResultPageAttributes.*;


@Controller
@RequestMapping("/files")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class.getSimpleName());

    private static final String FILE_UPLOADED = "New file was uploaded";
    private static final String NO_FILE_PROVIDED = "Provided file is empty.";
    private static final String FILE_UPLOAD_USER_ERROR = "File could not be uploaded. Please try again.";
    private static final String FILE_UPLOAD_ERROR = "File could not be uploaded.";
    private static final String FILE_WAS_DELETED = "File was deleted";
    private static final String FILE_DELETE_ERROR = "Error deleting file appeared.";
    public static final String FILE_ALREADY_EXISTS = "File already exists";

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(Authentication auth, @RequestParam("fileUpload") MultipartFile multipartFile, Model model) {
        List<String> errors = new ArrayList<>();

        if (multipartFile.isEmpty()) {
            errors.add(NO_FILE_PROVIDED);
            model.addAttribute(ERROR_FLAG, true);
            model.addAttribute(ERRORS_LIST, errors);

            LOG.warn(NO_FILE_PROVIDED);
            return "result";
        }

        Integer userId = userService.getUser(auth.getName()).getId();
        try {
            boolean fileAlreadyExists = fileService.getFile(multipartFile.getOriginalFilename(), userId) != null;

            if(fileAlreadyExists){
                errors.add(FILE_ALREADY_EXISTS);
                model.addAttribute(ERROR_FLAG, true);
                model.addAttribute(ERRORS_LIST, errors);

                LOG.warn(FILE_ALREADY_EXISTS);
                return "result";
            }

            File file = new File(multipartFile.getOriginalFilename(), multipartFile.getSize(), multipartFile.getContentType(), multipartFile.getBytes(), userId);
            boolean fileUploadSuccessful = fileService.addFile(file);

            if (fileUploadSuccessful) {
                model.addAttribute(SUCCESS_FLAG, true);

                LOG.info(FILE_UPLOADED);
                return "result";
            } else {
                errors.add(FILE_UPLOAD_USER_ERROR);

                LOG.error(FILE_UPLOAD_ERROR);
            }

        } catch (IOException e) {
            errors.add(FILE_UPLOAD_USER_ERROR);

            LOG.error(FILE_UPLOAD_ERROR, e);
        }

        if(!errors.isEmpty()){
            model.addAttribute(ERRORS_LIST, errors);
            model.addAttribute(ERROR_FLAG, true);
        }

        return "result";
    }

    @GetMapping(path = "/view/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] viewFile(@PathVariable("id") Integer id){
        File file = fileService.getFile(id);
        return file.getData();
    }

    @GetMapping(path = "/delete/{id}")
    public String deleteFile(Authentication auth, @PathVariable("id") Integer fileId, Model model){
        Integer userId = userService.getUser(auth.getName()).getId();

        boolean deletionWasSuccessful = fileService.deleteFile(fileId, userId);
        if(deletionWasSuccessful){
            model.addAttribute(SUCCESS_FLAG, true);

            LOG.info(FILE_WAS_DELETED);
        } else {
            model.addAttribute(ERROR_FLAG, true);

            LOG.error(FILE_DELETE_ERROR);
        }

        return "result";
    }
}
