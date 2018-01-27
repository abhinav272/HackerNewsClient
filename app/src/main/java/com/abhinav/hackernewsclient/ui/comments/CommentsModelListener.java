package com.abhinav.hackernewsclient.ui.comments;

import com.abhinav.hackernewsclient.base.BaseModelListener;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

/**
 * Created by appinventiv on 26/1/18.
 */

interface CommentsModelListener extends BaseModelListener {
    void onCommentsLoaded(Comment comment);
    void onCommentReplyLoaded(Comment comment);

    void onCommentsLoadComplete();
}
