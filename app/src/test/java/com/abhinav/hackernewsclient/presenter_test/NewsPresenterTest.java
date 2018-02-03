package com.abhinav.hackernewsclient.presenter_test;

import com.abhinav.hackernewsclient.RxSchedulerRule;
import com.abhinav.hackernewsclient.data.network.FailureResponse;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.news.TopStoriesModel;
import com.abhinav.hackernewsclient.ui.news.TopStoriesPresenter;
import com.abhinav.hackernewsclient.ui.news.TopStoriesView;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by appinventiv on 3/2/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class NewsPresenterTest {

    @Mock
    private TopStoriesView view;
    @Mock
    private TopStoriesModel mockModel;

    @Mock
    private TopStoriesPresenter presenter;
    private ArrayList<Story> stories;

    @Before
    public void setUp() throws Exception {
        stories = new ArrayList<>();
        presenter = new TopStoriesPresenter(view) {
            @Override
            public void setModel() {
                this.model = mockModel;
            }
        };

    }

    @Test
    public void testOnPreLoadedStories() throws Exception {
        presenter.onPreLoadedStories(stories);
        verify(view).populatePreLoadedStories(stories);
        verify(view).setRefresh(false);
    }

    @Test
    public void testTopStoryFetched() throws Exception {
        Story story = new Story();
        story.setId(123L);
        story.setTitle("Test Story Title");
//        Story story2 = new Story();
//        story2.setId(124L);
//        story2.setTitle("Another Sample Title");
        presenter.onTopStoryFetched(story);
        verify(view).addStoryToView(story);
    }

    @Test
    public void testRefetchTopStories() throws Exception {
        presenter.onRefetchTopStories();
        verify(mockModel).clearDisposable();
        verify(mockModel).setStories(null);
        verify(mockModel).fetchTopStories();
    }

    @Test
    public void testPresenterInit() throws Exception {
        presenter.initView();
        verify(mockModel).fetchTopStories();
    }

    @Test
    public void testPresenterDestroy() throws Exception {
        presenter.destroy();
        verify(mockModel).detachListener();
    }

    @Test
    public void testNoNetworkError() throws Exception {
        presenter.noNetworkError();
        verify(view).showNoNetworkError();
    }

    @Test
    public void testSpeceficError() throws Exception {
        presenter.onErrorOccurred(null);
        verify(view, times(2)).showSpecificError(null);
    }
}
