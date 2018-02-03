package com.abhinav.hackernewsclient.model_test;

import com.abhinav.hackernewsclient.RxSchedulerRule;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.comments.CommentsModel;
import com.abhinav.hackernewsclient.ui.comments.CommentsModelListener;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.verify;

/**
 * Created by appinventiv on 3/2/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentsModelTest {
    @ClassRule
    public static final RxSchedulerRule schedulers = new RxSchedulerRule();

    @Mock
    private CommentsModelListener listener;

    private CommentsModel model;

    @Before
    public void setUp() throws Exception {
        model = new CommentsModel(listener);
    }

    @Test
    public void testOnNext() throws Exception {
        Comment comment = new Comment();
        model.onNext(comment);
        verify(listener).onCommentsLoaded(comment);
        comment.setDepthLevel(1);
        model.onNext(comment);
        verify(listener).onCommentReplyLoaded(comment);
    }

    @Test
    public void testOnFetchComments() throws Exception {
        model.commentHashMap = new HashMap<>();
        Comment comment = new Comment();
        comment.setId(123L);
        comment.setText("Sample Text");
        model.commentHashMap.put(comment.getId(), comment);
        ArrayList<Long> list = new ArrayList<>();
        list.add(comment.getId());
        model.fetchStoryComments(list);
        verify(listener).onPreLoadedComments(new ArrayList<>(model.commentHashMap.values()));
    }
}
