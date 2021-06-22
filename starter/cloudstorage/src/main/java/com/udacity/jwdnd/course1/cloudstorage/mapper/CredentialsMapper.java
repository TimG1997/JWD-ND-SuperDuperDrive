package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE user_id = #{userId}")
    Credentials[] getAllCredentials(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE id = #{id} AND user_id = #{userId}")
    Credentials getCredentials(Integer id, Integer userId);

    @Insert("INSERT INTO CREDENTIALS (id, url, username, key, password, user_id) " +
            "VALUES(#{id}, #{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCredentials(Credentials credentials);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} " +
            "WHERE id = #{id} AND user_id = #{userId}")
    int updateCredentials(Credentials credentials);

    @Delete("DELETE FROM CREDENTIALS WHERE id = #{id} and user_id = #{userId}")
    int deleteCredentials(Integer id, Integer userId);
}
