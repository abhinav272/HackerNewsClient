package com.abhinav.hackernewsclient.network;

import com.abhinav.hackernewsclient.network.adapter.EpochDateTypeAdapter;
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

public class RestController {

    private static final RestController ourInstance = new RestController();
    private APIHackerNews apiHackerNews;

    public static RestController getInstance() {
        return ourInstance;
    }

    private RestController() {
        apiHackerNews = getRetrofitService();
    }

    private static APIHackerNews getRetrofitService(){
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

    public Observable<List<Long>> getTopStories(){
         return apiHackerNews.getTopStories()
                .observeOn(AndroidSchedulers.mainThread());
    }

}
