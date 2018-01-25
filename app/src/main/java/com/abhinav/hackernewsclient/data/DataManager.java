package com.abhinav.hackernewsclient.data;

import com.abhinav.hackernewsclient.data.network.pojo.Story;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * DataManager is the single source of truth
 * RestController is so written to be only accessible by DataManger only
 * If offline support is to be integrated, a similar class for DBController can be written
 * which is only accessible by DataManager.
 * <p>
 * All Components only seek data from DataManager whereas DataManager decides if it needs to
 * fetch it from local or Server.
 * <p>
 * DataManger is so written to facilitate easy custom cache implementation as well as populating
 * old local data to User while fetching fresh data from Server and refresh user screen.
 */

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private RestController restController;

    private DataManager() {
        restController = RestController.getInstance();
    }

    public static DataManager getInstance() {
        return ourInstance;
    }

    public Observable<Story> fetchTopStories() {
        return restController.getTopStories()
                .concatMap(new Function<List<Long>, ObservableSource<? extends Story>>() {
                    @Override
                    public ObservableSource<? extends Story> apply(List<Long> longs) throws Exception {
                        return getStoryByIds(longs);
                    }
                });
    }

    private ObservableSource<Story> getStoryByIds(List<Long> storyIds) {
        return Observable.fromIterable(storyIds)
                .concatMap(new Function<Long, ObservableSource<Story>>() {
                    @Override
                    public ObservableSource<Story> apply(Long aLong) throws Exception {
                        return restController.getStoryDetails(aLong);
                    }
                });
    }
}
