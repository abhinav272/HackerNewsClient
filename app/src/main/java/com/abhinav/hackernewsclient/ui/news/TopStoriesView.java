package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BaseView;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

import java.util.List;

/**
 * Created by appinventiv on 24/1/18.
 */

public interface TopStoriesView extends BaseView {
    void fetchTopStories();
    void addStoryToView(Story story);
    void setRefresh(boolean b);
    void populatePreLoadedStories(List<Story> stories);
}
