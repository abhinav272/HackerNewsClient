package com.abhinav.hackernewsclient.base;

import com.abhinav.hackernewsclient.network.FailureResponse;

import java.lang.ref.SoftReference;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by appinventiv on 23/1/18.
 */

abstract class BaseModel<T extends BaseModelListener> implements Observer<T> {

    private SoftReference<T> listener;
    private CompositeDisposable disposable;

    public BaseModel(T listener) {
        attachListener(listener);
    }

    public void attachListener(T listener) {
        this.listener = new SoftReference<>(listener);
        disposable = new CompositeDisposable();
    }

    public void detachListener() {
        this.listener = null;
        flushDisposable();
    }

    public T getListener() {
        return (listener != null) ? listener.get() : null;
    }

    @Override
    public void onError(Throwable e) {
        if (e != null && (e instanceof SocketTimeoutException || e instanceof UnknownHostException)) {
            noNetworkAvailableError();
        } else {
            onSpecificErrorOccurred(e);
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        disposable.add(d);
    }

    @Override
    public void onComplete() {
        disposable.clear();
    }

    private void flushDisposable() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void onSpecificErrorOccurred(Throwable e) {
        FailureResponse failureResponse = null;
        if (e != null && e instanceof HttpException) {
            failureResponse = new FailureResponse();
            failureResponse.setErrorCode(((HttpException) e).code());
            failureResponse.setMsg(((HttpException) e).message());
        }
        getListener().onErrorOccurred(failureResponse);
    }

    private void noNetworkAvailableError() {
        getListener().noNetworkError();
    }
}
