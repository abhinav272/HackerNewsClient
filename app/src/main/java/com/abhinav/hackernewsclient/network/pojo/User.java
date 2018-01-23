package com.abhinav.hackernewsclient.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by appinventiv on 23/1/18.
 */

public class User {
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("created")
    @Expose
    private Long created;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("karma")
    @Expose
    private Long karma;
    @SerializedName("submitted")
    @Expose
    private List<Long> submitted = null;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getKarma() {
        return karma;
    }

    public void setKarma(Long karma) {
        this.karma = karma;
    }

    public List<Long> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<Long> submitted) {
        this.submitted = submitted;
    }
}
