package com.abhinav.hackernewsclient.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by appinventiv on 23/1/18.
 */

public class Story {

    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("descendants")
    @Expose
    private Long descendants;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("kids")
    @Expose
    private List<Long> kids = null;
    @SerializedName("score")
    @Expose
    private Long score;
    @SerializedName("time")
    @Expose
    private Long time;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    public ItemType type;
    @SerializedName("url")
    @Expose
    private String url;

    public enum ItemType {
        @SerializedName("story")
        STORY("story"),
        @SerializedName("ask")
        ASK("ask"),
        @SerializedName("job")
        JOB("job");

        private String string;

        ItemType(String string) {
            this.string = string;
        }

        public static ItemType fromString(String string) {
            if (string != null) {
                for (ItemType postType : ItemType.values()) {
                    if (string.equalsIgnoreCase(postType.string)) return postType;
                }
            }
            return null;
        }
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Long getDescendants() {
        return descendants;
    }

    public void setDescendants(Long descendants) {
        this.descendants = descendants;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
