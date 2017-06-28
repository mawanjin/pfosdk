package com.android.sdk.pfo.pfosdk;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import com.android.sdk.pfo.pfosdk.dialog.ProgressUtil;
import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

/**
 * Created by lala on 2017/6/12.
 */

public class PfoPay {
    private static PfoPay instance;
    private IPay payPlugin;

    public static PfoPay getInstance(){
        if(instance == null)instance = new PfoPay();
        return instance;
    }

    public void init(){
        this.payPlugin = (IPay) PluginFactory.getInstance().initPlugin(2);
        if(this.payPlugin == null){
            this.payPlugin = new SimpleDefaultPay();
        }
    }

    public boolean isSupport(String mtd){
        if(this.payPlugin == null)return false;
        return this.payPlugin.isSupportMethod(mtd);
    }

    public void pay(final PayParams payParams, final IPayListener listener){
        if(this.payPlugin == null)return;
        PayResult result = checkParams(payParams);
        if(result.getCode()==0){
            final InquiryOrderInfoTask mUserInfoTask = InquiryOrderInfoTask.newInstance();
            //todo 将订单信息上传至PFOSDK服务器，并替换notifyUrl
          final ProgressDialog progress = ProgressUtil.show(PfoSDK.getInstance().getContext(), "支付", "正在生成支付信息",
            new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    if (mUserInfoTask != null) {
                        mUserInfoTask.doCancel();
                    }
                }
            });

            progress.show();

            mUserInfoTask.doRequest(PfoSDK.getInstance().getContext(), payParams, new InquiryOrderListener() {
                @Override
                public void onGotOrderInfo(PayParams payParams) {
                    progress.dismiss();
                    PfoPay.this.payPlugin.pay(payParams,listener);
                }
            });


        }else{
            listener.onPayResult(result);
        }
    }

    private PayResult checkParams(PayParams payParams) {
        PayResult result = new PayResult();
        result.setCode(0);
        if(SDKUtils.isNullOrEmpty(payParams.getAppOrderId())){
            result.setCode(1001);
        }else if(SDKUtils.isNullOrEmpty(payParams.getPayNotifyUrl())){
            result.setCode(1002);
        }else if(payParams.getPrice()<1){
            result.setCode(1003);
        }else if(SDKUtils.isNullOrEmpty(payParams.getProductId())){
            result.setCode(1004);
        }else if(SDKUtils.isNullOrEmpty(payParams.getProductName())){
            result.setCode(1005);
        }else if(SDKUtils.isNullOrEmpty(payParams.getAppName())){
            result.setCode(1006);
        }else if(SDKUtils.isNullOrEmpty(payParams.getRoleId())){
            result.setCode(1007);
        }else if(SDKUtils.isNullOrEmpty(payParams.getRoleName())){
            result.setCode(1008);
        }else if(SDKUtils.isNullOrEmpty(payParams.getAppUserId())){
            result.setCode(1009);
        }else if(SDKUtils.isNullOrEmpty(payParams.getAppUserName())){
            result.setCode(1010);
        }else if(SDKUtils.isNullOrEmpty(payParams.getSdkUserId())){
            result.setCode(1011);
        }else if(SDKUtils.isNullOrEmpty(payParams.getServerId())){
            result.setCode(1012);
        }else if(SDKUtils.isNullOrEmpty(payParams.getDistrictId())){
            result.setCode(1013);
        }else if(SDKUtils.isNullOrEmpty(payParams.getServerName())){
            result.setCode(1014);
        }else if(SDKUtils.isNullOrEmpty(payParams.getDistrictName())){
            result.setCode(1015);
        }else if(payParams.getProductNum()<1){
            result.setCode(1016);
        }

        return result;
    }



}
