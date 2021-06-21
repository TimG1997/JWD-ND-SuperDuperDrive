package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private Integer id;
    private String name;
    private String contentType;
    private long size;
    private Integer userId;
    private byte[] data;

    public File(String name, long size, String contentType, byte[] data, Integer userId) {
        this.name = name;
        this.data = data;
        this.size = size;
        this.userId = userId;
        this.contentType = contentType;
    }

    public File(Integer id, String name, String contentType, long size, Integer userId, byte[] data) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.userId = userId;
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

