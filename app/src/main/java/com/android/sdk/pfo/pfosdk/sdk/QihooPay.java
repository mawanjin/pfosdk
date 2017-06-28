package com.android.sdk.pfo.pfosdk.sdk;

import android.app.Activity;

import com.android.sdk.pfo.pfosdk.IPay;
import com.android.sdk.pfo.pfosdk.IPayListener;
import com.android.sdk.pfo.pfosdk.PayParams;
import com.android.sdk.pfo.pfosdk.PayResult;
import com.android.sdk.pfo.pfosdk.PfoSDK;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

/**
 * Created by lala on 2017/6/13.
 */

public class QihooPay implements IPay{

    private Activity context;

    public QihooPay(Activity activity){
        this.context = activity;
        SDKUtils.log(null,"new instance class-QihooPay");
    }

    @Override
    public boolean isSupportMethod(String method) {
        return true;
    }

    @Override
    public void pay(PayParams payParams, IPayListener listener) {
        SDKUtils.log(null,"method QihooUser::pay()called.");
        PayResult result = new PayResult();
        listener.onPayResult(result);
    }
}
