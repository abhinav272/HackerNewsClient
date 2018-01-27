package com.abhinav.hackernewsclient.data.network.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by appinventiv on 23/1/18.
 */

public class Story implements Parcelable {

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
    private Date time;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    public ItemType type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("deleted")
    @Expose
    private boolean deleted;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.by);
        dest.writeValue(this.descendants);
        dest.writeValue(this.id);
        dest.writeList(this.kids);
        dest.writeValue(this.score);
        dest.writeLong(this.time != null ? this.time.getTime() : -1);
        dest.writeString(this.title);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.url);
        dest.writeByte(this.deleted ? (byte) 1 : (byte) 0);
    }

    public Story() {
    }

    protected Story(Parcel in) {
        this.by = in.readString();
        this.descendants = (Long) in.readValue(Long.class.getClassLoader());
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.kids = new ArrayList<Long>();
        in.readList(this.kids, Long.class.getClassLoader());
        this.score = (Long) in.readValue(Long.class.getClassLoader());
        long tmpTime = in.readLong();
        this.time = tmpTime == -1 ? null : new Date(tmpTime);
        this.title = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : ItemType.values()[tmpType];
        this.url = in.readString();
        this.deleted = in.readByte() != 0;
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel source) {
            return new Story(source);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };
}
