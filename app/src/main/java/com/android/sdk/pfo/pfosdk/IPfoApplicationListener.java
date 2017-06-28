package com.android.sdk.pfo.pfosdk;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by lala on 2017/6/9.
 */

public interface IPfoApplicationListener {
    void onProxyCreate(Context context);
    void onProxyAttachBaseContext(Context context);
    void onProxyConfigurationChanged(Configuration configuration);
    void onTerminate();
}
