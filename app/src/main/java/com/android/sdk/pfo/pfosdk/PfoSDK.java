package com.android.sdk.pfo.pfosdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.android.sdk.pfo.pfosdk.bo.PfoMetaUserInfo;
import com.android.sdk.pfo.pfosdk.bo.PfoUploadUserInfoResult;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;
import com.android.sdk.pfo.pfosdk.verify.PfoVerify;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lala on 2017/6/9.
 */

public class PfoSDK {
    private static final String App_GAME_NAME = "Pfo_Game_Application";
    private static final String APP_PROXY_NAME = "PFO_APPLICATION_PROXY_NAME";
    private static final String DEFAULT_PKG_NAME = "com.android.sdk.pfo.pfosdk";
    private static final String LOGIC_CHANNEL_PREFIX = "pfochannel_";
    private boolean INIT_SDK = false;

    private static PfoSDK instance;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());
    private Application application;
    private List<IActivityCallback> activityCallbacks = new ArrayList(1);
    private List<IPfoApplicationListener> applicationListeners = new ArrayList<>(2);

    private int channel;
    private Activity context;
    private SDKParams developInfo;
    private Bundle metaData;

    private IPfoUserListener userListener;
    private List<IPfoSDKListener> listeners = new ArrayList<>();
    private String sdkUserID = null;
    private PfoToken token;

    public static PfoSDK getInstance(){
        if(instance == null){
            instance = new PfoSDK();
        }
        return instance;
    }

    private IPfoApplicationListener newApplicationInstance(Application paramApplication,String className){
        if(className == null || SDKUtils.isNullOrEmpty(className)){
            return null;
        }
        if(className.startsWith(".")){
            className = "com.android.sdk.pfo.pfosdk"+className;
        }
        try {
            IPfoApplicationListener applicationListener = (IPfoApplicationListener) Class.forName(className).newInstance();
            return applicationListener;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Application getApplication(){
        return this.application;
    }

    public Activity getContext(){
        return this.context;
    }

    public String getSdkUserID(){
        return this.sdkUserID;
    }

    public PfoToken getToken(){
        return this.token;
    }


    public void init(Activity activity){
        this.context = activity;
        PfoUser.getInstance().init();
        PfoPay.getInstance().init();


        //add other plugins here to suit your needs.
    }

    public void onAppAttachBaseContext(Application application, Context context){
        this.application = application;
        this.applicationListeners.clear();
        PluginFactory.getInstance().loadPluginInfo(context);
        this.developInfo = PluginFactory.getInstance().getSDKParams(context);
        this.metaData = PluginFactory.getInstance().getMetaData(context);

        if(this.metaData.containsKey("pfo_game_proxy_application")){
            String gameApplication = this.metaData.getString("pfo_game_proxy_application");
            IPfoApplicationListener listener = newApplicationInstance(application,gameApplication);
            if(listener!=null){
                this.applicationListeners.add(listener);
            }
        }

        if(this.metaData.containsKey("pfo_game_application")){
            String gameApplication = this.metaData.getString("pfo_game_application");
            IPfoApplicationListener listener = newApplicationInstance(application,gameApplication);
            if(listener!=null){
                this.applicationListeners.add(listener);
            }
        }

        for (IPfoApplicationListener listener : applicationListeners){
            listener.onProxyAttachBaseContext(context);
        }

        if(this.metaData.containsKey("pfosdk_appkey") && this.metaData.containsKey("pfosdk_privatekey")) {
            INIT_SDK = true;
        }else{
            SDKUtils.toast("初始化失败,appkey、privatekey未正确配置");
            INIT_SDK = false;
        }

    }


    public void onAppConfigurationChanged(Application application, Configuration configuration){
        for (IPfoApplicationListener listener : applicationListeners){
            listener.onProxyConfigurationChanged(configuration);
        }
    }

    public void onAppCreate(Application paramApplication){

        for (IPfoApplicationListener listener : applicationListeners){
            listener.onProxyCreate(paramApplication);
        }
    }

    public void onTerminate(){
        for (IPfoApplicationListener listener : applicationListeners){
            listener.onTerminate();
        }
    }

    //user interface
    public void uploadUserInfo(final PfoMetaUserInfo pfoMetaUserInfo, final IUploadUserInfoListener listener){
        if(!INIT_SDK){
            SDKUtils.toast("初始化失败,未正确配置");
            return;
        }
        if(PfoUser.getInstance().isSupport("login")){
            runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    PfoUser.getInstance().uploadUserInfo(pfoMetaUserInfo,listener);
                }
            });
        }
        else{
            SDKUtils.toast("暂不支持该方法");
        }
    }

    public void login(){
        if(!INIT_SDK){
            SDKUtils.toast("初始化失败,未正确配置");
            return;
        }
        if(PfoUser.getInstance().isSupport("login")){
            runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    PfoUser.getInstance().login();
                }
            });
        }
        else{
            SDKUtils.toast("暂不支持该方法");
        }
    }

    public void switchLogin(){
        if(!INIT_SDK){
            SDKUtils.toast("初始化失败,未正确配置");
            return;
        }
        if(PfoUser.getInstance().isSupport("switchLogin"))
            PfoUser.getInstance().switchLogin();
        else
            SDKUtils.toast("暂不支持该方法");
    }

//    public void logout(){
//        if(PfoUser.getInstance().isSupport("logout"))
//            PfoUser.getInstance().logout();
//        else
//            SDKUtils.toast("暂不支持该方法");
//    }

    public void exit(){
        if(!INIT_SDK){
            SDKUtils.toast("初始化失败,未正确配置");
            return;
        }
        if(PfoUser.getInstance().isSupport("exit"))
            PfoUser.getInstance().exit();
        else
            SDKUtils.toast("暂不支持该方法");
    }

    public void pay(PayParams payParams,IPayListener listener){
        if(!INIT_SDK){
            SDKUtils.toast("初始化失败,未正确配置");
            return;
        }
        if(PfoPay.getInstance().isSupport("pay"))
            PfoPay.getInstance().pay(payParams,listener);
        else
            SDKUtils.toast("暂不支持该方法");
    }


    private void onAuthResult(PfoToken token){
        if(token!=null)
        SDKUtils.log(null,"method PfoSDK::onAuthResult()called.");
        if(token.getCode()==PfoToken.PFO_LOGIN_RESULT_CODE_SUCCESS){
            this.sdkUserID = token.getSdkUserID();
            this.token = token;
        }

        if(userListener!=null){
            if(token.getCode()==PfoToken.PFO_LOGIN_RESULT_CODE_SUCCESS){
                userListener.onLoginSuccess(token);
            }else {
                userListener.onLoginFailed(token.getMsg());
            }
        }

    }


    public void onConfigurationChanged(Configuration configuration){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onConfigurationChanged(configuration);
        }
    }

    public void onBackPressed(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onBackPressed();
        }
    }
    public void onNewIntent(Intent intent){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onNewIntent(intent);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onActivityResult(requestCode,resultCode,data);
        }
    }

    public void onPause(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onPause();
        }
    }

    public void onResume(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onResume();
        }
    }

    public void onCreate(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onCreate();
        }
    }

    public void onStart(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onStart();
        }
    }

    public void onStop(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onStop();
        }
    }

    public void onDestroy(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onDestroy();
        }
    }

    public void  onRequestPermissionResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onRequestPermissionResult(requestCode,permissions,grantResults);
        }
    }

    public void onRestart(){
        for(IActivityCallback activityCallback:activityCallbacks){
            activityCallback.onRestart();
        }
    }

    public void onInitResult(InitResult result){

    }

    public void onLoginResult(PfoToken token){

        SDKUtils.log(null,"method PfoSDK::onLoginResult()called.");
        if(token.getCode()==PfoToken.PFO_LOGIN_RESULT_CODE_CANCEL){
            if(userListener!=null){
                userListener.onLoginCancel();
            }
            return;
        }

        if(isAuth()){
            SDKUtils.log(null,"need verify token...");
            new AuthTask().execute(new PfoToken[] { token });
        }else{
            SDKUtils.log(null,"no necessary to verify token...");
            onAuthResult(token);
        }
    }

    public void onExit(int code) {
        if(userListener!=null)userListener.onExit(code);
    }

//    public void onLogout(){
//        if(userListener!=null){
//            userListener.onLogout();
//        }
//    }


    public void setActivityCallback(IActivityCallback callback){
        if(!this.activityCallbacks.contains(callback)&&callback!=null){
            this.activityCallbacks.add(callback);
        }
    }

    public void setSDKListener(IPfoSDKListener sdkListener){
        if ((!this.listeners.contains(sdkListener)) && (sdkListener != null)) {
            this.listeners.add(sdkListener);
        }
    }

    public void runOnMainThread(Runnable runnable)
    {
        if (this.mainThreadHandler != null) {
            this.mainThreadHandler.post(runnable);
        }else{
            while (this.context == null) {
                return;
            }
            this.context.runOnUiThread(runnable);
        }
    }

    private boolean isAuth(){
        if(getAuthURL()==null)return false;
        return true;
    }
    private String getAuthURL()
    {
        if ((this.developInfo == null) || (!this.developInfo.contains("PFO_AUTH_URL"))) {
            return null;
        }
        return this.developInfo.getString("PFO_AUTH_URL");
    }

    public String getAppID() {
        if ((this.developInfo == null) || (!this.developInfo.contains("PFO_APPID"))) {
            return "-1";
        }
        return this.developInfo.getString("PFO_APPID");
    }

    public Object getCurrChannel() {
        if ((this.developInfo == null) || (!this.developInfo.contains("U8_Channel"))) {
            return 0;
        }
        return this.developInfo.getString("PFO_Channel");
    }

    public Object getSDKVersionCode() {
        if ((this.developInfo == null) || (!this.developInfo.contains("PFO_SDK_VERSION_CODE"))) {
            return "";
        }
        return this.developInfo.getString("PFO_SDK_VERSION_CODE");
    }

    public String getDevInfoByKey(String key){
        if ((this.developInfo == null) || (!this.developInfo.contains(key))) {
            return "";
        }
        return this.developInfo.getString(key);
    }




    class AuthTask
            extends AsyncTask<PfoToken, Void, PfoToken>
    {
        AuthTask() {}

        protected PfoToken doInBackground(PfoToken... paramVarArgs)
        {
            PfoToken token = paramVarArgs[0];

            return PfoVerify.auth(token);
        }

        protected void onPostExecute(PfoToken paramUToken)
        {
            PfoSDK.this.onAuthResult(paramUToken);
        }
    }

    public void setUserListener(IPfoUserListener userListener) {
        this.userListener = userListener;
    }



}
