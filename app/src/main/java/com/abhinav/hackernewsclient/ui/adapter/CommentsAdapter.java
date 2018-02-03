package com.abhinav.hackernewsclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.viewholder.CommentsViewHolder;

import java.util.ArrayList;

/**
 * Created by appinventiv on 27/1/18.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsViewHolder> {

    private ArrayList<Comment> comments;

    public CommentsAdapter(ArrayList<Comment> comments) {
        if (comments == null)
            comments = new ArrayList<>();
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

    public Comment getItem(int position) {
        return comments.get(position);
    }

    private int getItemPosition(Comment comment) {
        return comments.indexOf(comment);
    }

    public void addCommentItem(Comment comment) {
        comments.add(comment);
        notifyItemInserted(getItemPosition(comment));
    }

    public void updateCommentReplies(Comment updatedComment) {
        int pos = getItemPosition(updatedComment);
        comments.remove(pos);
        comments.add(pos, updatedComment);
        notifyItemChanged(pos);
    }

    public void removeAllComments() {
        comments.clear();
        notifyDataSetChanged();
    }

    public void addPreLoadedComments(ArrayList<Comment> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
        notifyDataSetChanged();
    }
}
