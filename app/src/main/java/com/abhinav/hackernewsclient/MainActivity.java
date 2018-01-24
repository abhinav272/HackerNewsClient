package com.abhinav.hackernewsclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.abhinav.hackernewsclient.base.BaseActivity;
import com.abhinav.hackernewsclient.data.DataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {


    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addFragment(R.id.frame_container, new BaseFragment(), BaseFragment.class.getSimpleName());
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn)
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
    }
}
