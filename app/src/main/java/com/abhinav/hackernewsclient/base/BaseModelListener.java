package com.abhinav.hackernewsclient.base;

import com.abhinav.hackernewsclient.data.network.FailureResponse;

/**
 * Created by appinventiv on 23/1/18.
 */

interface BaseModelListener {
    void noNetworkError();
    void onErrorOccurred(FailureResponse failureResponse);
}
