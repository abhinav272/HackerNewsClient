package com.abhinav.hackernewsclient.data;

import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

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
/*
    public Observable<Comment> fetchStoryComments(List<Long> storyKids, final int depthLevelToAchieve) {
        return Observable.fromIterable(storyKids)
                .concatMap(new Function<Long, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(Long aLong) throws Exception {
                        return restController.getCommentDeatils(aLong);
                    }
                })
                .concatMap(new Function<Comment, ObservableSource<? extends Comment>>() {
                    @Override
                    public ObservableSource<? extends Comment> apply(Comment comment) throws Exception {
                        comment.setDepthLevel(comment.getDepthLevel() + 1);
                        if (comment.getDepthLevel() == depthLevelToAchieve || comment.getKids() == null
                                || comment.getKids().size() == 0 || comment.isDeleted())
                            return Observable.just(comment);
                        else
                            return Observable.just(comment)
                            .mergeWith(fetchStoryComments(comment.getKids(), depthLevelToAchieve - comment.getDepthLevel()));
                    }
                })
                .filter(new Predicate<Comment>() {
                    @Override
                    public boolean test(Comment comment) throws Exception {
                        return comment != null && comment.getKids().size() > 0 &&
                                !comment.isDeleted() && comment.getText() != null;
                    }
                });
    }*/

    public Observable<Comment> fetchStoryComments(List<Long> storyKids) {
        return Observable.fromIterable(storyKids)
                .concatMap(new Function<Long, ObservableSource<? extends Comment>>() {
                    @Override
                    public ObservableSource<? extends Comment> apply(Long aLong) throws Exception {
                        return restController.getCommentDeatils(aLong);
                    }
                })
                .filter(new Predicate<Comment>() {
                    @Override
                    public boolean test(Comment comment) throws Exception {
//                        return !comment.isDeleted() && comment.getKids() != null && comment.getKids().size() >= 0;
                        return !comment.isDeleted();
                    }
                });
    }

    public Observable<Comment> fetchCommentsReply(List<Long> commentKidIds) {
        return Observable.fromIterable(commentKidIds)
                .concatMap(new Function<Long, ObservableSource<? extends Comment>>() {
                    @Override
                    public ObservableSource<? extends Comment> apply(Long aLong) throws Exception {
                        return restController.getCommentDeatils(aLong);
                    }
                })
                .flatMap(new Function<Comment, ObservableSource<Comment>>() {
                    @Override
                    public ObservableSource<Comment> apply(Comment comment) throws Exception {
                        comment.setDepthLevel(1);
                        return Observable.just(comment);
                    }
                })
                .filter(new Predicate<Comment>() {
                    @Override
                    public boolean test(Comment comment) throws Exception {
                        return !comment.isDeleted();
                    }
                });
    }
}
