package com.abhinav.hackernewsclient.ui.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by appinventiv on 24/1/18.
 */

public class TopStoriesFragment extends BaseFragment implements TopStoriesView {

    @BindView(R.id.rv_top_stories)
    RecyclerView rvTopStories;
    Unbinder unbinder;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    private TopStoriesPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new TopStoriesPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTopStories();
            }
        });
        fetchTopStories();
    }

    private void setupRV() {
        rvTopStories.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }

    @Override
    public void fetchTopStories() {
        presenter.initView();
    }

    @Override
    public void populateTopStories() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
