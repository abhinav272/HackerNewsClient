package com.abhinav.hackernewsclient.model_test;

import com.abhinav.hackernewsclient.RxSchedulerRule;
import com.abhinav.hackernewsclient.data.DataManager;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.news.TopStoriesModel;
import com.abhinav.hackernewsclient.ui.news.TopStoriesModelListener;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Created by appinventiv on 3/2/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class TopStoriesModelTest {
    @ClassRule
    public static final RxSchedulerRule schedulers = new RxSchedulerRule();

    @Mock
    private TopStoriesModelListener listener;

    private TopStoriesModel model;

    @Before
    public void setUp() throws Exception {
        model = new TopStoriesModel(listener);
    }

    @Test
    public void testOnNext() throws Exception {
        Story story = new Story();
        model.onNext(story);
        verify(listener).onTopStoryFetched(story);
    }

    @Test
    public void testFetchStories() throws Exception {
        Story story = new Story();
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(story);
        model.setStories(stories);
        model.fetchTopStories();
        verify(listener).onPreLoadedStories(stories);
    }

    @Test
    public void testSetStories() throws Exception {
        Story story = new Story();
        ArrayList<Story> stories = new ArrayList<>();
        stories.add(story);
        model.setStories(stories);
        assert model.getStories().size() == 1;
    }
}
