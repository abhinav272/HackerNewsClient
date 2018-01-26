package com.abhinav.hackernewsclient.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.adapter.viewholder.TopStoriesViewHolder;

import java.util.ArrayList;

/**
 * Created by appinventiv on 26/1/18.
 */

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesViewHolder> {

    private DelegateClick listener;
    private ArrayList<Story> stories;

    public TopStoriesAdapter(ArrayList<Story> stories, DelegateClick listener) {
        if (stories == null) {
            stories = new ArrayList<>();
        }
        this.stories = stories;
        this.listener = listener;
    }

    @Override
    public TopStoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_top_story_single_item, parent, false);
        return new TopStoriesViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(TopStoriesViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    private Story getItem(int position) {
        return stories.get(position);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public void addStoryItem(Story newStory) {
        stories.add(newStory);
        notifyItemInserted(stories.size() - 1);
    }

    public void removeAllStories() {
        stories.clear();
        notifyDataSetChanged();
    }

    public interface DelegateClick {
        void onStorySelected(Story story);
    }
}
