package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/12.
 */

public class PfoUser {
    private static PfoUser instance;
    private IUser userPlugin;

    public static PfoUser getInstance(){
        if(instance == null){
            instance = new PfoUser();
        }
        return instance;
    }

    public void exit(){
        if(this.userPlugin == null){
            return;
        }
        this.userPlugin.exit();
    }

    public void init(){
        this.userPlugin = (IUser) PluginFactory.getInstance().initPlugin(1);
        if(this.userPlugin == null){
            this.userPlugin = new SimpleDefaultUser();
        }
    }

    public boolean isSupport(String mtd){
        if(this.userPlugin == null)return false;
        return this.userPlugin.isSupportMethod(mtd);
    }

    public void login(){
        if(this.userPlugin == null)return;
        this.userPlugin.login();
    }

//    public void logout(){
//        if(this.userPlugin == null)return;
//        this.userPlugin.logout();
//    }

    public void showAccountCenter(){
        if(this.userPlugin == null)return;
        this.userPlugin.showAccountCenter();
    }

    public void submitExtraData(UserExtraData data){
        if(this.userPlugin == null)return;
        this.userPlugin.submitExtraData(data);
    }

    public void switchLogin(){
        if(this.userPlugin == null)return;
        this.userPlugin .switchLogin();
    }
}
