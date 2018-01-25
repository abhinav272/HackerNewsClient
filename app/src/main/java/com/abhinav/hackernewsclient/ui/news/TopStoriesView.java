package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BaseView;

/**
 * Created by appinventiv on 24/1/18.
 */

public interface TopStoriesView extends BaseView {
    void fetchTopStories();
    void populateTopStories();
}
