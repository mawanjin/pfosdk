package com.android.sdk.pfo.pfosdk.test;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

import com.android.sdk.pfo.pfosdk.IPfoApplicationListener;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

/**
 * Created by lala on 2017/6/13.
 */

public class MApplication  implements IPfoApplicationListener {
    private String tag = "MApplication";


    @Override
    public void onProxyCreate(Context context) {

    }

    @Override
    public void onProxyAttachBaseContext(Context context) {

    }

    @Override
    public void onProxyConfigurationChanged(Configuration configuration) {

    }

    @Override
    public void onTerminate() {

    }
}
