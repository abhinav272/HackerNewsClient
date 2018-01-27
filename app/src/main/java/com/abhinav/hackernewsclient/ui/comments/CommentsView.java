package com.abhinav.hackernewsclient.ui.comments;

import com.abhinav.hackernewsclient.base.BaseView;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

/**
 * Created by appinventiv on 26/1/18.
 */

public interface CommentsView extends BaseView {
    void showComment(Comment commentsLevel1);
    void showCommentsReply(Comment commentsLevel2);
}
