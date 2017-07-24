package com.android.sdk.pfo.pfosdk;

import android.widget.Toast;

import com.android.sdk.pfo.pfosdk.bo.PfoMetaUserInfo;
import com.android.sdk.pfo.pfosdk.bo.PfoUploadUserInfoResult;

/**
 * Created by lala on 2017/6/12.
 */

public class SimpleDefaultUser implements IUser {

    private void log(String msg){
        Toast.makeText(PfoSDK.getInstance().getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void exit() {
        log("invoke method [exit] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
        PfoSDK.getInstance().onExit(1);
    }

    @Override
    public void login() {
        log("invoke method [login] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
        PfoSDK.getInstance().onLoginResult(new PfoToken());
    }

    @Override
    public void loginCustom(String param) {

    }

//    @Override
//    public void logout() {
//        log("invoke method [logout] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
//        PfoSDK.getInstance().onLogout();
//    }

    @Override
    public boolean showAccountCenter() {
        log("invoke method [showAccountCenter] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
        return true;
    }

    @Override
    public void submitExtraData(UserExtraData extraData) {
        log("invoke method [submitExtraData] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
    }

    @Override
    public void switchLogin() {
        log("invoke method [switchLogin] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
        PfoSDK.getInstance().onLoginResult(new PfoToken());
    }

    @Override
    public void uploadUserInfo(PfoMetaUserInfo userInfo,IUploadUserInfoListener listener) {
        log("invoke method [uploadUserInfo] success,and it needs to be disposed to generate the final package which integrates with a real channel sdk by Packager_System.");
        PfoUploadUserInfoResult result = new PfoUploadUserInfoResult();
        if(listener!=null){
            listener.onUploadUserInfoResult(result);
        }
    }

    @Override
    public boolean isSupportMethod(String method) {
        return true;
    }
}
