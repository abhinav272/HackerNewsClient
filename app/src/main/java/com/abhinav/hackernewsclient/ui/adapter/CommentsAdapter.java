package com.abhinav.hackernewsclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.viewholder.CommentsViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by appinventiv on 27/1/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private HashMap<Long, Comment> comments;

    public CommentsAdapter(HashMap<Long, Comment> comments) {
        if (comments == null)
            comments = new HashMap<>();
        this.comments = comments;
    }

    @Override
    public CommentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comments_single_item, parent, false);
        return new CommentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private Comment getItem(int position) {
        ArrayList<Comment> allComments = new ArrayList<>(comments.values());
        return allComments.get(position);
    }

    private int getItemPosition(Comment comment) {
        ArrayList<Comment> allComments = new ArrayList<>(comments.values());
        return allComments.indexOf(comment);
    }

    public void addCommentItem(Comment comment) {
        comments.put(comment.getId(), comment);
        notifyItemInserted(getItemPosition(comment));
    }

    public void updateCommentReplies(Comment updatedComment) {
        Comment c = comments.get(updatedComment.getParent());
        c.setKidComments(updatedComment);
        notifyItemChanged(getItemPosition(c));
    }

    public void removeAllComments() {
        comments.clear();
        notifyDataSetChanged();
    }
}
