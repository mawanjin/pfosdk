package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/12.
 */

public interface IPay extends IPlugin{
    public static final int PLUGIN_TYPE = 2;
    void pay(PayParams payParams,IPayListener listener);
}
