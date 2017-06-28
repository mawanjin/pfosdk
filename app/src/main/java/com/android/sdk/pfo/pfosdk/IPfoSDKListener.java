package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/9.
 */

public interface IPfoSDKListener {
//    void onAuthResult(PfoToken paramUToken);

//    void onInitResult(InitResult paramInitResult);

//    void onLoginResult(String sucess);

    void onLogout();

    void onSwitchAccount();

    void onSwitchAccount(String paramString);

    void onExit();
}
