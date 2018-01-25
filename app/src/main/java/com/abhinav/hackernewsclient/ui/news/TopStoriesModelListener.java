package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BaseModelListener;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

/**
 * Created by appinventiv on 24/1/18.
 */

public interface TopStoriesModelListener extends BaseModelListener {
    void onTopStoryFetched(Story story);
}
