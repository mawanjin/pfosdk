package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/9.
 */

public class InitResult {
    private String extension;
    private boolean isSDKExit;

    public InitResult() {}

    public InitResult(boolean paramBoolean)
    {
        this.isSDKExit = paramBoolean;
    }

    public InitResult(boolean paramBoolean, String paramString)
    {
        this.isSDKExit = paramBoolean;
        this.extension = paramString;
    }

    public String getExtension()
    {
        return this.extension;
    }

    public boolean isSDKExit()
    {
        return this.isSDKExit;
    }

    public void setExtension(String paramString)
    {
        this.extension = paramString;
    }

    public void setSDKExit(boolean paramBoolean)
    {
        this.isSDKExit = paramBoolean;
    }
}
