package com.abhinav.hackernewsclient.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;

/**
 * Created by appinventiv on 27/1/18.
 */

public class CommentRepliesViewHolder extends RecyclerView.ViewHolder {

    private TextView tvReply, tvAuthor;

    public CommentRepliesViewHolder(View itemView) {
        super(itemView);
        tvAuthor = itemView.findViewById(R.id.tv_comment_reply_author);
        tvReply = itemView.findViewById(R.id.tv_comment_reply);
    }

    public void bind(Comment kidComment) {
        tvReply.setText(kidComment.getText());
        tvAuthor.setText(kidComment.getBy());
    }
}
