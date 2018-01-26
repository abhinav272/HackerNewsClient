package com.abhinav.hackernewsclient.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.adapter.TopStoriesAdapter;
import com.abhinav.hackernewsclient.utils.Formatter;

/**
 * Created by appinventiv on 26/1/18.
 */

public class TopStoriesViewHolder extends RecyclerView.ViewHolder {

    private TopStoriesAdapter.DelegateClick listener;
    private TextView tvStoryTitle, tvAuthor, tvStoryScore, tvPublishedAt;

    public TopStoriesViewHolder(View itemView, TopStoriesAdapter.DelegateClick listener) {
        super(itemView);
        this.listener = listener;
        tvAuthor = itemView.findViewById(R.id.tv_author);
        tvPublishedAt = itemView.findViewById(R.id.tv_publish_date);
        tvStoryScore = itemView.findViewById(R.id.tv_story_score);
        tvStoryTitle = itemView.findViewById(R.id.tv_story_title);
    }

    public void bindData(final Story story) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onStorySelected(story);
            }
        });
        tvStoryTitle.setText(story.getTitle());
        tvAuthor.setText(story.getBy());
        tvStoryScore.setText(String.format(itemView.getContext().getString(R.string.score), story.getScore().toString()));
        tvPublishedAt.setText(Formatter.formatPublishDate(story.getTime()));
    }
}
