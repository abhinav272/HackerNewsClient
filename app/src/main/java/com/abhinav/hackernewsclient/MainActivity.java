package com.abhinav.hackernewsclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.abhinav.hackernewsclient.base.BaseActivity;
import com.abhinav.hackernewsclient.base.BaseFragment;
import com.abhinav.hackernewsclient.network.RestController;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addFragment(R.id.frame_container, new BaseFragment(), BaseFragment.class.getSimpleName());
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onViewClicked();
            }
        });
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }


    public void onViewClicked() {
        RestController.getInstance().getTopStories()
                .subscribe(new Observer<List<Long>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e( "onSubscribe: ", "yo");
                    }

                    @Override
                    public void onNext(List<Long> longs) {
                        Log.e("onNext: ", longs.size()+"");
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
