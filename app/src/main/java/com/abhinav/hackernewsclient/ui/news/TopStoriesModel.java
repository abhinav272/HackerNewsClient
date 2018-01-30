package com.abhinav.hackernewsclient.ui.news;

import com.abhinav.hackernewsclient.base.BaseModel;
import com.abhinav.hackernewsclient.data.DataManager;
import com.abhinav.hackernewsclient.data.network.pojo.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appinventiv on 24/1/18.
 */

class TopStoriesModel extends BaseModel<Story> {

    private List<Story> stories;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    TopStoriesModel(TopStoriesModelListener listener) {
        super(listener);
    }

    void fetchTopStories() {
        if (stories == null || stories.size() == 0)
            DataManager.getInstance().fetchTopStories()
                        .subscribe(this);
        else ((TopStoriesModelListener) getListener()).onPreLoadedStories(stories);
    }

    @Override
    public void onNext(Story story) {
        if (stories == null) {
            stories = new ArrayList<>();
        }
        stories.add(story);
        ((TopStoriesModelListener) getListener()).onTopStoryFetched(story);
    }
}
