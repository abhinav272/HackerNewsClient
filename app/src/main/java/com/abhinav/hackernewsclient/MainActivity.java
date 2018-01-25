package com.abhinav.hackernewsclient;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.abhinav.hackernewsclient.base.BaseActivity;
import com.abhinav.hackernewsclient.ui.news.TopStoriesFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.frame_container, new TopStoriesFragment(), TopStoriesFragment.class.getSimpleName());
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    /*@OnClick(R.id.btn)
    public void onViewClicked() {
        DataManager.getInstance().fetchTopStories()
                .subscribe(new Observer<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("onSubscribe: ", "yo");
                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        Log.e("onNext: ", longs.size() + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError: ", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("onComplete: ", "yo complete");
                    }
                });
    }*/
}
