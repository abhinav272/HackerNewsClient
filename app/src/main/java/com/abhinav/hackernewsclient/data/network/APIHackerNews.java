package com.abhinav.hackernewsclient.data.network;

import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.data.network.pojo.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface APIHackerNews {
    String ENDPOINT = "https://hacker-news.firebaseio.com";

    /**
     * API to fetch top stories
     */
    @GET("/v0/topstories.json")
    Observable<List<Long>> getTopStories();

    /**
     * API to fetch USER profile
     */
    @GET("/v0/user/{user}.json")
    Observable<User> getUser(@Path("user") String user);

    /**
     * API to fetch item details for STORY
     */
    @GET("/v0/item/{itemId}.json")
    Observable<Story> getStoryItem(@Path("itemId") String itemId);

    /**
     * API to fetch item details for COMMENT
     */
    @GET("/v0/item/{itemId}.json")
    Observable<Comment> getCommentItem(@Path("itemId") String itemId);
}
