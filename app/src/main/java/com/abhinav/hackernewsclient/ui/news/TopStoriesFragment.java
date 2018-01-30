package com.abhinav.hackernewsclient.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.base.BaseActivity;
import com.abhinav.hackernewsclient.base.BaseFragment;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.adapter.TopStoriesAdapter;
import com.abhinav.hackernewsclient.ui.comments.CommentsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by appinventiv on 24/1/18.
 */

public class TopStoriesFragment extends BaseFragment implements TopStoriesView, TopStoriesAdapter.DelegateClick {

    @BindView(R.id.rv_top_stories)
    RecyclerView rvTopStories;
    Unbinder unbinder;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private TopStoriesPresenter presenter;
    private TopStoriesAdapter topStoriesAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("onCreate: ", "called");
        setRetainInstance(true);
        presenter = new TopStoriesPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.attachView(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                topStoriesAdapter.removeAllStories();
                presenter.onRefetchTopStories();
            }
        });
        fetchTopStories();
    }

    private void setupRV() {
        topStoriesAdapter = new TopStoriesAdapter(null, this);
        rvTopStories.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTopStories.setAdapter(topStoriesAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy: ", "called");
        presenter.destroy();
    }

    @Override
    public void fetchTopStories() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.initView();
    }

    @Override
    public void addStoryToView(Story story) {
        swipeRefreshLayout.setRefreshing(false);
        topStoriesAdapter.addStoryItem(story);
    }

    @Override
    public void setRefresh(boolean b) {
        swipeRefreshLayout.setRefreshing(b);
    }

    @Override
    public void onStorySelected(Story story) {
        Log.e("onStorySelected: ", story.getTitle()+" " + story.getId());
        if (story.getKids() == null || story.getKids().size() == 0){
            showToastLong(getString(R.string.no_comments));
            return;
        }
        ((BaseActivity) getActivity()).addFragmentWithBackstack(R.id.frame_container,
                CommentsFragment.getInstance(story), CommentsFragment.class.getSimpleName());
    }

    @Override
    public void populatePreLoadedStories(List<Story> stories) {
        topStoriesAdapter.addPreLoadedStories(stories);
    }
}
