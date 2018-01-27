package com.abhinav.hackernewsclient.ui.adapter.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.ui.adapter.CommentRepliesAdapter;

/**
 * Created by appinventiv on 27/1/18.
 */

public class CommentsViewHolder extends RecyclerView.ViewHolder {

    private RecyclerView rvCommentReplies;
    private TextView tvCommentTitle;

    public CommentsViewHolder(View itemView) {
        super(itemView);
        rvCommentReplies = itemView.findViewById(R.id.rv_comment_reply);
        tvCommentTitle = itemView.findViewById(R.id.tv_comment_title);
        rvCommentReplies.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        rvCommentReplies.setAdapter(getAdapterForReplies());
    }


    public void bind(Comment comment) {
        tvCommentTitle.setText(comment.getText());
        if (comment.getKidComments() != null && comment.getKidComments().size() > 0) {
            ((CommentRepliesAdapter) rvCommentReplies.getAdapter()).updateDataSet(comment.getKidComments());
        }
    }

    private CommentRepliesAdapter getAdapterForReplies() {
        return new CommentRepliesAdapter(null);
    }
}
