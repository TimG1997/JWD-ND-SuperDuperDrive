package com.udacity.jwdnd.course1.cloudstorage.model.dto;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;

public class DecryptedCredentialsDTO extends Credentials {

    private String decryptedPassword;

    public DecryptedCredentialsDTO(Integer id, String url, String username, String key, String password, Integer userId, String decryptedPassword) {
        super(id, url, username, key, password, userId);
        this.decryptedPassword = decryptedPassword;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }
}
