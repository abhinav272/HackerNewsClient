package com.abhinav.hackernewsclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.viewholder.CommentRepliesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by appinventiv on 27/1/18.
 */

public class CommentRepliesAdapter extends RecyclerView.Adapter<CommentRepliesViewHolder> {

    private List<Comment> kidComments;

    public CommentRepliesAdapter(List<Comment> kidComments) {
        if (kidComments == null)
            kidComments = new ArrayList<>();
        this.kidComments = kidComments;
    }

    @Override
    public CommentRepliesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_comment_reply_single_item, parent, false);
        return new CommentRepliesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentRepliesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public int getItemCount() {
        return kidComments.size();
    }

    private Comment getItem(int position) {
        return kidComments.get(position);
    }

    public void addReplyComment(Comment comment) {
        kidComments.add(comment);
        notifyItemChanged(kidComments.size() - 1);
    }

    public void updateDataSet(List<Comment> kidComments) {
        this.kidComments.clear();
        this.kidComments.addAll(kidComments);
        notifyDataSetChanged();
    }
}
