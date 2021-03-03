package com.anonym.parsedome.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

public class DataImport implements Serializable {

    private static final long serialVersionUID = 4314217103996695230L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;
    private DataEnum title;
    private String createTime;
    private String updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataEnum getTitle() {
        return title;
    }

    public void setTitle(DataEnum title) {
        this.title = title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
