package com.abhinav.hackernewsclient.presenter_test;

import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.comments.CommentsModel;
import com.abhinav.hackernewsclient.ui.comments.CommentsPresenter;
import com.abhinav.hackernewsclient.ui.comments.CommentsView;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appinventiv on 3/2/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentsPresenterTest {

    @Mock
    private CommentsView view;

    @Mock
    private CommentsModel mockModel;

    @Mock
    private CommentsPresenter presenter;

    private ArrayList<Comment> comments;

    @Before
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        presenter = new CommentsPresenter(view){
            @Override
            public void setModel() {
                this.model = mockModel;
            }
        };

//        doNothing().when(presenter).commentReplies.poll();
    }

    @Test
    public void testOnPreLoadedComments() throws Exception {
        presenter.onPreLoadedComments(comments);
        verify(view).showPreLoadedComments(comments);
    }

    @Test
    public void testOnCommentLoaded() throws Exception {
        Comment comment = new Comment();
        presenter.onCommentsLoaded(comment);
        verify(view).showComment(comment);
    }

    @Test
    public void testCommentsLoadComplete() throws Exception {
        presenter.onCommentsLoadComplete();
        verify(mockModel).fetchCommentsReply(presenter.commentReplies.poll());
    }

    @Test
    public void testCommentReplyLoaded() throws Exception {
        Comment comment = new Comment();
        presenter.onCommentReplyLoaded(comment);
        verify(view).showCommentsReply(comment);
    }

    @Test
    public void testInitFetching() throws Exception {
        List list = new ArrayList();
        presenter.initFetching(list);
        verify(mockModel).fetchStoryComments(list);
    }

    @Test
    public void testPresenterDestroy() throws Exception {
        presenter.destroy();
        verify(mockModel).detachListener();
    }
}
