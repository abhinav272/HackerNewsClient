package com.abhinav.hackernewsclient.ui.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhinav.hackernewsclient.R;
import com.abhinav.hackernewsclient.base.BaseFragment;
import com.abhinav.hackernewsclient.data.network.pojo.Comment;
import com.abhinav.hackernewsclient.data.network.pojo.Story;
import com.abhinav.hackernewsclient.ui.adapter.CommentsAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by appinventiv on 26/1/18.
 */

public class CommentsFragment extends BaseFragment implements CommentsView {

    public static final String STORY = "story";
    @BindView(R.id.rv_comments)
    RecyclerView rvComments;
    Unbinder unbinder;
    @BindView(R.id.tv_story_title)
    TextView tvStoryTitle;
    private CommentsPresenter presenter;
    private CommentsAdapter commentsAdapter;
    private Story story;

    public static CommentsFragment getInstance(Story story) {
        CommentsFragment fragment = new CommentsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STORY, story);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CommentsPresenter(this);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        initArgs();
        unbinder = ButterKnife.bind(this, view);
        presenter.attachView(this);
        return view;
    }

    private void initArgs() {
        story = getArguments().getParcelable(STORY);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvStoryTitle.setText(story.getTitle());
        setupRv();
        presenter.initFetching(story.getKids());
    }

    private void setupRv() {
        commentsAdapter = new CommentsAdapter(null);
        rvComments.setLayoutManager(new LinearLayoutManager(getContext()));
        rvComments.setAdapter(commentsAdapter);
    }

    @Override
    public void showComment(Comment commentsLevel1) {
//        Log.e("showComment: ", commentsLevel1.getId() + " " + commentsLevel1.getText());
        commentsAdapter.addCommentItem(commentsLevel1);
    }

    @Override
    public void showPreLoadedComments(ArrayList<Comment> comments) {
        commentsAdapter.addPreLoadedComments(comments);
    }

    @Override
    public void showCommentsReply(Comment commentsLevel2) {
//        Log.e("showCommentsReply: ", commentsLevel2.getParent() + " " + commentsLevel2.getText());
        commentsAdapter.updateCommentReplies(commentsLevel2);
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
        presenter.destroy();
    }
}
