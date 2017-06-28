package com.android.sdk.pfo.pfosdk.verify;

import com.android.sdk.pfo.pfosdk.PfoSDK;
import com.android.sdk.pfo.pfosdk.PfoToken;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

import java.util.HashMap;

/**
 * Created by lala on 2017/6/13.
 */

public class PfoVerify {
    public static PfoToken auth(PfoToken token){
        HashMap localHashMap = new HashMap();
        localHashMap.put("appID", PfoSDK.getInstance().getAppID());
        localHashMap.put("channelID", PfoSDK.getInstance().getCurrChannel());
        localHashMap.put("extension", "xxx");
        localHashMap.put("sdkVersionCode", PfoSDK.getInstance().getSDKVersionCode());
        localHashMap.put("deviceID", SDKUtils.getDeviceId(PfoSDK.getInstance().getContext()));
        //verify from server,
        //onsuccess(String rs){}
        return token;
    }

    public static PfoToken convertToken(String str){
        PfoToken token = new PfoToken();
        token.setCode(PfoToken.PFO_LOGIN_RESULT_CODE_SUCCESS);
        token.setToken("token...");
        token.setSdkUserID("qihoouser001");
        token.setSdkUserName("小小草");
        return token;
    }
}
