package com.android.sdk.pfo.pfosdk;

import android.content.Context;
import android.util.Log;

/**
 * Created by lala on 2017/6/28.
 */
class InquiryOrderInfoTask {

    private SdkHttpTask sSdkHttpTask;
    public static InquiryOrderInfoTask newInstance(){
        return new InquiryOrderInfoTask();
    }

    public void doRequest(Context context, final PayParams payParams,
                          final InquiryOrderListener listener) {

        //todo 将订单上传到PFOSDK服务器，并替换notifyUrl
        String url = "http://www.baidu.com";

        // 如果存在，取消上一次请求
        if (sSdkHttpTask != null) {
            sSdkHttpTask.cancel(true);
        }

        // 新请求
        sSdkHttpTask = new SdkHttpTask(context);
        sSdkHttpTask.doGet(new SdkHttpListener() {

            @Override
            public void onResponse(String response) {
                // QihooUserInfo.parseJson这个解析的是demo测试服务器返回的数据格式。
                // 游戏更换url后需要按新的格式解析response字符串。不能直接用这个。
                listener.onGotOrderInfo(payParams);
                sSdkHttpTask = null;
            }

            @Override
            public void onCancelled() {
                listener.onGotOrderInfo(payParams);
                sSdkHttpTask = null;
            }

        }, url);

    }

    public boolean doCancel() {
        return (sSdkHttpTask != null) ? sSdkHttpTask.cancel(true) : false;
    }

}


