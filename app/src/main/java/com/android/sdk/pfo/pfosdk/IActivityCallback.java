package com.android.sdk.pfo.pfosdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

/**
 * Created by lala on 2017/6/12.
 */

public interface IActivityCallback {

    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onBackPressed();
    void onConfigurationChanged(Configuration newConfig);
    void onCreate();
    void onDestroy();
    void onNewIntent(Intent paramIntent);
    void onPause();
    void onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    void onRestart();
    void onResume();
    void onStart();
    void onStop();
}
