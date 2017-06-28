package com.android.sdk.pfo.pfosdk;

import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

/**
 * Created by lala on 2017/6/12.
 */
public class SimpleDefaultPay implements IPay {

    @Override
    public boolean isSupportMethod(String method) {
        return true;
    }

    @Override
    public void pay(PayParams payParams, IPayListener listener) {
        SDKUtils.toast("invoke method [exit] success,and it needs to re-compiler for the real sdk by packageTool.");
        listener.onPayResult(new PayResult());
    }
}
