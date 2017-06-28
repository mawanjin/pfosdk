package com.android.sdk.pfo.pfosdk;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import com.android.sdk.pfo.pfosdk.utils.SDKUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lala on 2017/6/12.
 */

public class PluginFactory {
    private static PluginFactory instance;
    private Map<Integer,String> supportedPlugins = new HashMap<>();

    public static PluginFactory getInstance(){
        if(instance == null){
            instance = new PluginFactory();
        }
        return instance;
    }

    private String getPluginName(int id){
        if(this.supportedPlugins.containsKey(Integer.valueOf(id))){
            return this.supportedPlugins.get(Integer.valueOf(id));
        }
        return null;
    }

    private boolean isSupportPlugin(int id){
        return this.supportedPlugins.containsKey(id);
    }

    public Bundle getMetaData(Context context){
        try
        {
            ApplicationInfo localApplicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
            {
                Bundle localBundle = localApplicationInfo.metaData;
                return localBundle;
            }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
        return new Bundle();
    }

    public SDKParams getSDKParams(Context context){
        return new SDKParams(SDKUtils.getAssetPropConfig(context,"pfo_developer_config.properties"));
    }

    public Object initPlugin(int id){
        if(!isSupportPlugin(id)){
            Log.e("PFoSDK","The config of the PFoSDK is not support plugin type:"+id);
            return null;
        }

        try {
            Class clazz = Class.forName(getPluginName(id));
            Constructor constructor = clazz.getDeclaredConstructor(new Class[]{Activity.class});
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = PfoSDK.getInstance().getContext();
            Object o = constructor.newInstance(arrayOfObject);
            return o;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void loadPluginInfo(Context context){
        String str = SDKUtils.getAssetConfigs(context,"pfo_plugin_config.xml");
        XmlPullParser parser = Xml.newPullParser();

        int eventType = 0;
        try {
            parser.setInput(new StringReader(str));
            eventType = parser.getEventType();
            while (eventType!=XmlPullParser.END_DOCUMENT){
                if(eventType == XmlPullParser.START_TAG){
                    String name = parser.getName();
                    if("plugin".equals(name)){
                        this.supportedPlugins.put(Integer.parseInt(parser.getAttributeValue(1)),parser.getAttributeValue(0));
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
