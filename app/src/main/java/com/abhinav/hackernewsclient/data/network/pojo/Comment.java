package com.abhinav.hackernewsclient.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by appinventiv on 23/1/18.
 */

public class Comment {
    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("kids")
    @Expose
    private List<Long> kids = null;
    @SerializedName("parent")
    @Expose
    private Long parent;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private Date time;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;

    private int depthLevel = 0;
    private ArrayList<Comment> kidComments;

    public int getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(int depthLevel) {
        this.depthLevel = depthLevel;
    }

    public ArrayList<Comment> getKidComments() {
        return kidComments;
    }

    public void setKidComments(Comment comment) {
        if (kidComments == null) {
            kidComments = new ArrayList<>();
        }
        kidComments.add(comment);
        setKidComments(kidComments);
    }

    public void setKidComments(ArrayList<Comment> kidComments) {
        this.kidComments = kidComments;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getKids() {
        return kids;
    }

    public void setKids(List<Long> kids) {
        this.kids = kids;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
