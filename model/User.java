package com.example.sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class User {

    @SerializedName("userId")
    private Integer userId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @SerializedName("userId")
    public Integer getUserId() {
        return userId;
    }

    @SerializedName("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @SerializedName("id")
    public Integer getId() {
        return id;
    }

    @SerializedName("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("title")
    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("body")
    public String getBody() {
        return body;
    }

    @SerializedName("body")
    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
