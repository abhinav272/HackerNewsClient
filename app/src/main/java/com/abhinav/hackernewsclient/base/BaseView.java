package com.abhinav.hackernewsclient.base;

import com.abhinav.hackernewsclient.data.network.FailureResponse;

/**
 * Created by appinventiv on 23/1/18.
 */

public interface BaseView {

    void showNoNetworkError();
    void showToastLong(String message);
    void showSpecificError(FailureResponse failureResponse);
    void showProgressDialog();
    void hideProgressDialog();
}
