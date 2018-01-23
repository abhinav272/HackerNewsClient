package com.abhinav.hackernewsclient;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.abhinav.hackernewsclient.base.BaseActivity;
import com.abhinav.hackernewsclient.base.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(R.id.frame_container, new BaseFragment(), BaseFragment.class.getSimpleName());
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }
}
