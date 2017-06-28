package com.android.sdk.pfo.pfosdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

public class MainActivity extends AppCompatActivity implements IPfoUserListener{

    private Button btnLogin,btnCancel,btnSwitch,btnPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PfoSDK.getInstance().onCreate();
        setContentView(R.layout.activity_main);
        initSDK();
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSwitch = (Button) findViewById(R.id.btnSwitch);
        btnPay = (Button) findViewById(R.id.btnPay);
        initEvent();
    }

    private void initSDK() {
        PfoSDK.getInstance().init(this);
        PfoSDK.getInstance().setUserListener(this);

    }

    //以下是SDK统一接口回调

    @Override
    public void onLoginSuccess(PfoToken token) {
        SDKUtils.toast("验证成功...userId="+token.getSdkUserID());
        //进入游戏
        //如果CP有自己的userid/username请在这里设置
        token.setAppUserId("111");
        token.setAppUserName("xiaoxiao");
    }

    @Override
    public void onLoginFailed(String reason) {
        SDKUtils.toast("登录失败...[msg]="+reason);
    }

    @Override
    public void onLoginCancel() {

    }

//    @Override
//    public void onLogout() {
//        Toast.makeText(this,"退出登录成功",Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onExit(int code) {

    }


    private void initEvent() {

        //登录
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PfoSDK.getInstance().login();
            }
        });


        //切换帐号
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PfoSDK.getInstance().switchLogin();
            }
        });

        //支付
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PfoSDK.getInstance().pay(initPayParams(), new IPayListener() {
                    @Override
                    public void onPayResult(PayResult result) {
                        if(result.getCode()==0){
                            SDKUtils.toast("支付成功");
                        }else {
                            SDKUtils.toast("支付失败");
                        }
                    }
                });
            }
        });
    }

    private PayParams initPayParams() {
        PayParams payParams = new PayParams();
        payParams.setProductId("1000001");
        payParams.setProductName("twinkle twinkle little star");
        payParams.setProductDesc("overvalued package");
        payParams.setProductNum(1);
        payParams.setPrice(100);
        payParams.setDistrictId("1");
        payParams.setDistrictName("大区一");
        payParams.setServerId("2");
        payParams.setServerName("暗影之服");
        payParams.setAppOrderId("10000000000102002003000001");
        payParams.setExtension("ext1....");
        payParams.setRoleId("2000001");
        payParams.setRoleName("无敌星战");
        payParams.setPayNotifyUrl("http://www.baidu.com");

        return payParams;
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        PfoSDK.getInstance().onRestart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PfoSDK.getInstance().onRequestPermissionResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        PfoSDK.getInstance().onNewIntent(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        PfoSDK.getInstance().onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PfoSDK.getInstance().onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        PfoSDK.getInstance().onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PfoSDK.getInstance().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        PfoSDK.getInstance().onStop();
    }

    @Override
    protected void onDestroy() {
        PfoSDK.getInstance().onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PfoSDK.getInstance().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        PfoSDK.getInstance().onStart();
    }
}
