package com.abhinav.hackernewsclient.ui.comments;

import com.abhinav.hackernewsclient.base.BaseModel;
import com.abhinav.hackernewsclient.base.BaseModelListener;
import com.abhinav.hackernewsclient.data.DataManager;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by appinventiv on 26/1/18.
 */

public class CommentsModel extends BaseModel<Comment> {

    private HashMap<Long, Comment> commentHashMap;

    public CommentsModel(BaseModelListener listener) {
        super(listener);
    }

    public void fetchStoryComments(List<Long> commentIds) {
        if (commentHashMap == null || commentHashMap.size() == 0)
            DataManager.getInstance().fetchStoryComments(commentIds)
                    .subscribe(this);
        else
            ((CommentsModelListener) getListener()).onPreLoadedComments(new ArrayList<>(commentHashMap.values()));
    }

    @Override
    public void onComplete() {
        super.onComplete();
        ((CommentsModelListener) getListener()).onCommentsLoadComplete();
    }

    public void fetchCommentsReply(List<Long> commentKidIds) {
        if (commentKidIds != null) {
            DataManager.getInstance().fetchCommentsReply(commentKidIds)
                    .subscribe(this);
        }
    }

    @Override
    public void onNext(Comment comment) {
        if (commentHashMap == null)
            commentHashMap = new HashMap<>();
        if (comment.getDepthLevel() == 0) {
            commentHashMap.put(comment.getId(), comment);
            ((CommentsModelListener) getListener()).onCommentsLoaded(comment);
        } else {
            Comment c = commentHashMap.get(comment.getParent());
            c.setKidComments(comment);
            ((CommentsModelListener) getListener()).onCommentReplyLoaded(c);
        }
    }
}
