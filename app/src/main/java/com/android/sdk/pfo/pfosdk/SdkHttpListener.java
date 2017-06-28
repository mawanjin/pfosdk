package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/28.
 */

public interface SdkHttpListener {
    public void onResponse(String response);

    public void onCancelled();
}
