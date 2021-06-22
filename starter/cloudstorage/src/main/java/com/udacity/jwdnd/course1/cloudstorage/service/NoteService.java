package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public boolean addNote(Note note){
        int insertedRows = this.noteMapper.insertNote(note);

        return insertedRows > 0;
    }

    public boolean updateNote(Note note){
        int rowsAffected = this.noteMapper.updateNote(note);
        return rowsAffected > 0;
    }

    public Note[] getNotes(Integer userId){
        return this.noteMapper.getNotes(userId);
    }

    public boolean deleteNote(Integer id, Integer userId) {
        int rowsAffected = this.noteMapper.deleteNote(id, userId);
        return rowsAffected > 0;
    }
}
