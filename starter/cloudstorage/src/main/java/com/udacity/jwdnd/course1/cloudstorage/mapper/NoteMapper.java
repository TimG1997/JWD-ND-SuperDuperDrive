package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE user_id = #{userId}")
    Note[] getNotes(Integer userId);

    @Insert("INSERT INTO NOTES (id, user_id, title, description) VALUES(#{id}, #{userId}, #{title}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertNote(Note note);

    @Update("UPDATE NOTES SET title = #{title}, description = #{description} WHERE id = #{id} AND user_id = #{userId}")
    int update(Note note);

    @Delete("DELETE FROM NOTES WHERE id = #{id} AND user_id = #{userId}")
    int delete(Integer id, Integer userId);
}
