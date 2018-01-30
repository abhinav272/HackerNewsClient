package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BasePresenter;
import com.abhinav.hackernewsclient.data.network.FailureResponse;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

import java.util.List;

/**
 * Created by appinventiv on 24/1/18.
 */

public class TopStoriesPresenter extends BasePresenter<TopStoriesView> implements TopStoriesModelListener {

    private TopStoriesModel model;

    public TopStoriesPresenter(TopStoriesView view) {
        super(view);
    }

    @Override
    public void onErrorOccurred(FailureResponse failureResponse) {
        super.onErrorOccurred(failureResponse);
        getView().showSpecificError(failureResponse);
    }

    @Override
    public void noNetworkError() {
        super.noNetworkError();
        getView().setRefresh(false);
    }

    @Override
    protected void setModel() {
        model = new TopStoriesModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        model = null;
    }

    @Override
    protected void initView() {
        model.fetchTopStories();
    }

    @Override
    public void onTopStoryFetched(Story story) {
        if (!story.isDeleted() && getView() != null)
            getView().addStoryToView(story);
    }

    @Override
    public void onPreLoadedStories(List<Story> stories) {
        if (getView() != null) {
            getView().setRefresh(false);
            getView().populatePreLoadedStories(stories);
        }
    }

    public void onRefetchTopStories() {
        model.clearDisposable();
        model.setStories(null);
        model.fetchTopStories();
    }
}
