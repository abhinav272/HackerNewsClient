package com.abhinav.hackernewsclient.ui.comments;

import com.abhinav.hackernewsclient.base.BaseView;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

import java.util.ArrayList;

/**
 * Created by appinventiv on 26/1/18.
 */

public interface CommentsView extends BaseView {
    void showComment(Comment commentsLevel1);
    void showCommentsReply(Comment commentsLevel2);

    void showPreLoadedComments(ArrayList<Comment> comments);
}
