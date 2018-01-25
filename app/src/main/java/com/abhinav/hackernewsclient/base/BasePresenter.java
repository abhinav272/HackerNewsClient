package com.abhinav.hackernewsclient.base;

import android.util.Log;
import android.widget.Toast;

import com.abhinav.hackernewsclient.data.network.FailureResponse;

import java.lang.ref.SoftReference;

/**
 * Created by appinventiv on 23/1/18.
 */

public abstract class BasePresenter<T extends BaseView> implements BaseModelListener {

    private SoftReference<T> view;

    public BasePresenter(T view){
        this.view = new SoftReference<>(view);
        setModel();
    }

    public void attachView(T view){
        this.view = new SoftReference<T>(view);
        setModel();
    }

    public T getView() {
        return (view == null) ? null : view.get();
    }

    public void detachView() {
        view = null;
        destroy();
    }

    protected abstract void setModel();

    protected abstract void destroy();

    protected abstract void initView();

    /**
     * Common place to receive noNetwork hook
     * this can be passed on to BaseFragment or BaseActivity to show common screen or error
     */

    @Override
    public void noNetworkError() {

    }

    /**
     * Common place to log errors to Analytics or custom file logging
     * Every Presenter can override this method to provide custom handling if required
     * */

    @Override
    public void onErrorOccurred(FailureResponse failureResponse) {
        if (failureResponse != null) {
            Log.e("OnErrorOccured", failureResponse.getErrorCode() + failureResponse.getMsg());
        }
    }
}
