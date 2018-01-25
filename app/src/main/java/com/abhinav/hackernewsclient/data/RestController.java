package com.abhinav.hackernewsclient.data;

import com.abhinav.hackernewsclient.data.network.APIHackerNews;
import com.abhinav.hackernewsclient.data.network.adapter.EpochDateTypeAdapter;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.data.network.pojo.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by appinventiv on 23/1/18.
 */

class RestController {

    private static final RestController ourInstance = new RestController();
    private APIHackerNews apiHackerNews;

    private RestController() {
        apiHackerNews = getRetrofitService();
    }

    public static RestController getInstance() {
        return ourInstance;
    }

    private static APIHackerNews getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(getGsonFactory())
                .baseUrl(APIHackerNews.ENDPOINT)
                .build();

        return retrofit.create(APIHackerNews.class);

    }

    private static Converter.Factory getGsonFactory() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,
                EpochDateTypeAdapter.getEpochDateTypeAdapter()).create();
        return GsonConverterFactory.create(gson);
    }

    Observable<List<Long>> getTopStories() {
        return apiHackerNews.getTopStories()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<User> getUserDetails(String userId) {
        return apiHackerNews.getUser(userId)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Comment> getCommentDeatils(Long commentId) {
        return apiHackerNews.getCommentItem(commentId)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Story> getStoryDetails(Long storyId) {
        return apiHackerNews.getStoryItem(storyId)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
