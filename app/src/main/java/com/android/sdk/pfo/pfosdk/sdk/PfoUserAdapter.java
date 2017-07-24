package com.android.sdk.pfo.pfosdk.sdk;

import com.android.sdk.pfo.pfosdk.IUser;
import com.android.sdk.pfo.pfosdk.UserExtraData;

/**
 * Created by lala on 2017/6/13.
 */
public abstract class PfoUserAdapter implements IUser{
    public void exit(){}
    public abstract boolean isSupportMethod(String method);
    public void login(){}
    public void loginCustom(String param){}
    @Deprecated
    public void logout(){}
    public boolean showAccountCenter(){return false;}
    public void submitExtraData(UserExtraData extraData){}
    public void switchLogin(){}
}
