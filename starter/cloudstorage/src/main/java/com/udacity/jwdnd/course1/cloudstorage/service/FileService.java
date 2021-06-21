package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean addFile(File file) {
        Integer rowsAffected = fileMapper.insert(file);
        return rowsAffected > 0;
    }

    public boolean deleteFile(Integer id, Integer userId){
        Integer rowsAffected = fileMapper.delete(id, userId);
        return rowsAffected > 0;
    }

    public File[] getFiles(int userId) {
        return fileMapper.getFileNames(userId);
    }

    public File getFile(Integer id) {
        return fileMapper.getFile(id);
    }
}
