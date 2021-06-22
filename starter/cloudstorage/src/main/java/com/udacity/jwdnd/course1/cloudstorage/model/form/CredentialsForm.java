package com.udacity.jwdnd.course1.cloudstorage.model.form;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialsService;

public class CredentialsForm {

    private Integer id;
    private String url;
    private String username;
    private String password;

    public CredentialsForm(){
        this.url = "";
        this.username = "";
        this.password = "";
    }
    public CredentialsForm(Integer id, String url, String username, String password) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
