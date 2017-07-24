package com.android.sdk.pfo.pfosdk.bo;

/**
 * Created by lala on 2017/7/24.
 */

public class PfoUploadUserInfoResult {
    int code;//0成功1失败
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
