package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/9.
 */

public class PfoToken {
    public static final int PFO_LOGIN_RESULT_CODE_SUCCESS = 0;
    public static final int PFO_LOGIN_RESULT_CODE_CANCEL = 1;
    public static final int PFO_LOGIN_RESULT_CODE_FAILED = 2;

    private int code;//PFO_LOGIN_RESULT_CODE_X
    private String token;
    private String extension;//暂未使用
    private String sdkUserID;//统一认证用户标识
    private String sdkUserName;//统一认证用户名
    private String appUserId;//应用帐户系统的用户编号
    private String appUserName;//应用帐户系统的用户名
    private String msg;//成功或失败信息

    public PfoToken(){
    }

    public PfoToken(String paramString0, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    {
        this.appUserId = paramString0;
        this.sdkUserID = paramString1;
        this.appUserName = paramString2;
        this.sdkUserName = paramString3;
        this.token = paramString4;
        this.extension = paramString5;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSdkUserID() {
        return sdkUserID;
    }

    public void setSdkUserID(String sdkUserID) {
        this.sdkUserID = sdkUserID;
    }

    public String getSdkUserName() {
        return sdkUserName;
    }

    public void setSdkUserName(String sdkUserName) {
        this.sdkUserName = sdkUserName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(String appUserId) {
        this.appUserId = appUserId;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public void setAppUserName(String appUserName) {
        this.appUserName = appUserName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
