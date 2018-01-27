package com.abhinav.hackernewsclient.ui.comments;

import android.util.Log;

import com.abhinav.hackernewsclient.base.BaseModel;
import com.abhinav.hackernewsclient.base.BaseModelListener;
import com.abhinav.hackernewsclient.data.DataManager;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

import java.util.List;

/**
 * Created by appinventiv on 26/1/18.
 */

public class CommentsModel extends BaseModel<Comment> {

    public CommentsModel(BaseModelListener listener) {
        super(listener);
    }

    public void fetchStoryComments(List<Long> commentIds) {
        DataManager.getInstance().fetchStoryComments(commentIds)
                .subscribe(this);
    }

    @Override
    public void onComplete() {
        super.onComplete();
        ((CommentsModelListener) getListener()).onCommentsLoadComplete();
    }

    public void fetchCommentsReply(List<Long> commentKidIds) {
        if (commentKidIds == null) {
            return;
        }
        DataManager.getInstance().fetchCommentsReply(commentKidIds)
                .subscribe(this);
    }

    @Override
    public void onNext(Comment comment) {
        if (comment.getDepthLevel() == 0)
            ((CommentsModelListener) getListener()).onCommentsLoaded(comment);
        else
            ((CommentsModelListener) getListener()).onCommentReplyLoaded(comment);
    }
}
