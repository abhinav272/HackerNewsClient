package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BaseModel;
import com.abhinav.hackernewsclient.data.DataManager;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

/**
 * Created by appinventiv on 24/1/18.
 */

class TopStoriesModel extends BaseModel<Story> {


    TopStoriesModel(TopStoriesModelListener listener) {
        super(listener);
    }

    void fetchTopStories() {
        DataManager.getInstance().fetchTopStories()
                .subscribe(this);
    }

    @Override
    public void onNext(Story story) {
        ((TopStoriesModelListener) getListener()).onTopStoryFetched(story);
    }
}
