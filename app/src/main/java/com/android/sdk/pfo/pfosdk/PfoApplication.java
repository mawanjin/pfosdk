package com.android.sdk.pfo.pfosdk;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

/**
 * Created by lala on 2017/6/9.
 */

public class PfoApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        SDKUtils.log(null,"method attachBaseContext() called.");
        PfoSDK.getInstance().onAppAttachBaseContext(this,base);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SDKUtils.log(null,"method onConfigurationChanged() called.");
        PfoSDK.getInstance().onAppConfigurationChanged(this,newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SDKUtils.log(null,"method onCreate() called.");
        PfoSDK.getInstance().onAppCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SDKUtils.log(null,"method onTerminate() called.");
        PfoSDK.getInstance().onTerminate();
    }
}
