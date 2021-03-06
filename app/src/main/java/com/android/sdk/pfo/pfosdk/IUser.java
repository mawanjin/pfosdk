package com.android.sdk.pfo.pfosdk;

import com.android.sdk.pfo.pfosdk.bo.PfoMetaUserInfo;

/**
 * Created by lala on 2017/6/12.
 */

public interface IUser extends IPlugin{
    public static final int PLUGIN_TYPE = 1;


    void exit();
    void login();
    void loginCustom(String param);
//    void logout();
    boolean showAccountCenter();
    void submitExtraData(UserExtraData extraData);
    void switchLogin();
    void uploadUserInfo(PfoMetaUserInfo userInfo,IUploadUserInfoListener listener);


}
