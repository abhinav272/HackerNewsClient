package com.abhinav.hackernewsclient.ui.comments;

import com.abhinav.hackernewsclient.base.BasePresenter;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by appinventiv on 26/1/18.
 */

public class CommentsPresenter extends BasePresenter<CommentsView> implements CommentsModelListener {

    private CommentsModel model;
    private Queue<List<Long>> commentReplies = new ArrayDeque<>();

    public CommentsPresenter(CommentsView view) {
        super(view);
    }

    @Override
    public void onCommentsLoaded(Comment comment) {
        getView().showComment(comment);
        if (comment.getKids() != null && comment.getKids().size() > 0)
            commentReplies.add(comment.getKids());
    }

    @Override
    public void onCommentsLoadComplete() {
        model.fetchCommentsReply(commentReplies.poll());
    }

    @Override
    public void onCommentReplyLoaded(Comment comment) {
        getView().showCommentsReply(comment);
    }

    @Override
    public void onPreLoadedComments(ArrayList<Comment> comments) {
        getView().showPreLoadedComments(comments);
    }

    @Override
    protected void setModel() {
        model = new CommentsModel(this);
    }

    @Override
    protected void destroy() {
        model.detachListener();
        commentReplies = null;
        model = null;
    }

    protected void initFetching(List<Long> commentIds) {
        model.fetchStoryComments(commentIds);
    }

    @Override
    protected void initView() {

    }
}
