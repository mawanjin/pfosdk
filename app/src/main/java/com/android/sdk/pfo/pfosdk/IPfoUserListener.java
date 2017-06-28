package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/13.
 */

public interface IPfoUserListener {
    void onLoginSuccess(PfoToken token);
    void onLoginFailed(String reason);
    void onLoginCancel();
//    void onLogout();
    void onExit(int code);//0退出1取消退出
}
