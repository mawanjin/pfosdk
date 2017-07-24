package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/9.
 */

public class InitResult {
    private String extension;
    private boolean isSDKInit;

    public InitResult() {}

    public InitResult(boolean paramBoolean)
    {
        this.isSDKInit = paramBoolean;
    }

    public InitResult(boolean paramBoolean, String paramString)
    {
        this.isSDKInit = paramBoolean;
        this.extension = paramString;
    }

    public String getExtension()
    {
        return this.extension;
    }
    public void setExtension(String paramString)
    {
        this.extension = paramString;
    }

    public boolean isSDKInit() {
        return isSDKInit;
    }

    public void setSDKInit(boolean SDKInit) {
        isSDKInit = SDKInit;
    }
}
