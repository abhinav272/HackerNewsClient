package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BasePresenter;
import com.abhinav.hackernewsclient.data.network.FailureResponse;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

/**
 * Created by appinventiv on 24/1/18.
 */

public class TopStoriesPresenter extends BasePresenter<TopStoriesView> implements TopStoriesModelListener {

    private TopStoriesModel model;

    public TopStoriesPresenter(TopStoriesView view) {
        super(view);
        model = new TopStoriesModel(this);
    }

    @Override
    public void onErrorOccurred(FailureResponse failureResponse) {
        super.onErrorOccurred(failureResponse);
        getView().showSpecificError(failureResponse);
    }

    @Override
    protected void setModel() {

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
        if (!story.isDeleted())
            getView().addStoryToView(story);
    }
}
