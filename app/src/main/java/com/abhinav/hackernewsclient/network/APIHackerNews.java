package com.abhinav.hackernewsclient.network;

import com.abhinav.hackernewsclient.network.pojo.Comment;
import com.abhinav.hackernewsclient.network.pojo.Story;
import com.abhinav.hackernewsclient.network.pojo.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface APIHackerNews {
    String ENDPOINT = "https://hacker-news.firebaseio.com/v0/";

    /**
     * API to fetch top stories
     */
    @GET("/topstories.json")
    Observable<List<Long>> getTopStories();

    /**
     * API to fetch user profile
     */
    @GET("/user/{user}.json")
    Observable<User> getUser(@Path("user") String user);

    /**
     * API to fetch item details for STORY
     */
    @GET("/item/{itemId}.json")
    Observable<Story> getStoryItem(@Path("itemId") String itemId);

    /**
     * API to fetch item details for Comment
     */
    @GET("/item/{itemId}.json")
    Observable<Comment> getCommentItem(@Path("itemId") String itemId);
}