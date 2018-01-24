package com.abhinav.hackernewsclient.data;

import java.util.List;

import io.reactivex.Observable;

/**
 * DataManager is the single source of truth
 * RestController is so written to be only accessible by DataManger only
 * If offline support is to be integrated, a similar class for DBController can be written
 * which is only accessible by DataManager.
 *
 * All Components only seek data from DataManager whereas DataManager decides if it needs to
 * fetch it from local or Server.
 *
 * DataManger is so written to facilitate easy custom cache implementation as well as populating
 * old local data to User while fetching fresh data from Server and refresh user screen.
 */

public class DataManager {
    private static final DataManager ourInstance = new DataManager();
    private RestController restController;

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
        restController = RestController.getInstance();
    }

    public Observable<List<Long>> fetchTopStories() {
        return restController.getTopStories();
    }
}
