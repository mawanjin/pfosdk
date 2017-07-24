package com.android.sdk.pfo.pfosdk.sdk;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;

import com.android.sdk.pfo.pfosdk.IActivityCallback;
import com.android.sdk.pfo.pfosdk.IUploadUserInfoListener;
import com.android.sdk.pfo.pfosdk.InitResult;
import com.android.sdk.pfo.pfosdk.PfoSDK;
import com.android.sdk.pfo.pfosdk.PfoToken;
import com.android.sdk.pfo.pfosdk.bo.PfoMetaUserInfo;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

import java.util.Arrays;

/**
 * Created by lala on 2017/6/13.
 */

public class QihooUser extends PfoUserAdapter implements IActivityCallback {
    private String tag = "QihooUser";

    private Activity context;
    private String[] supportMethods = {"login","switchLogin","logout","submitExtraData","exit"};

    public QihooUser(Activity activity){
        this.context = activity;
        //qihoosdk.initSdk(this.context,pfosdk.getinstance().getSdkparams());
        PfoSDK.getInstance().setActivityCallback(this);
        InitResult initResult = new InitResult();
        initResult.setSDKInit(true);
        PfoSDK.getInstance().onInitResult(initResult);
        SDKUtils.log(null,"new instance class-QihooUser");
    }

    public void login(){
        //qihoosdk.getInstance().login(this.activity);
        SDKUtils.log(null,"method QihooUser::login()called.");

        //send login result to server,and server verify token by third sdk server.
        //PfoSDK.verifyToken(appid,channelid,thirdchannelid,token,new callback(){ PfoSDK.getInstance().onAuthResult(PfoToken) });
        //simulate process
        PfoToken token = new PfoToken();
        token.setCode(PfoToken.PFO_LOGIN_RESULT_CODE_FAILED);
        token.setSdkUserID("002");
        token.setSdkUserName("qihoouser002");
        token.setToken("sfsafsdafsdafsda");
        token.setMsg("abc...");
        PfoSDK.getInstance().onLoginResult(token);
    }

//    public void logout(){
//        SDKUtils.log(null,"method QihooUser::logout()called.");
//
//        PfoSDK.getInstance().onLogout();
//    }


    public void exit(){
        SDKUtils.log(null,"method QihooUser::exit()called.");
        PfoSDK.getInstance().onExit(1);
    }

    @Override
    public void switchLogin() {
        SDKUtils.log(null,"method QihooUser::switchLogin()called.");
        //TODO qihoosdk.switch();

        PfoToken token = new PfoToken();
        token.setCode(PfoToken.PFO_LOGIN_RESULT_CODE_SUCCESS);
        token.setSdkUserID("002");
        token.setSdkUserName("qihoouser002");
        token.setToken("sfsafsdafsdafsda");
        token.setMsg("abc...");
        PfoSDK.getInstance().onLoginResult(token);
    }

    @Override
    public void uploadUserInfo(PfoMetaUserInfo userInfo, IUploadUserInfoListener listener) {
        //todo 上传至服务器
        PfoSDK.getInstance().uploadUserInfo(userInfo,listener);
    }

    @Override
    public boolean isSupportMethod(String method) {
        return SDKUtils.arrayContain(supportMethods,method);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onNewIntent(Intent paramIntent) {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onRequestPermissionResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt) {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
