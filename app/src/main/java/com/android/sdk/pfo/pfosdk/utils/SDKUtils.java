package com.android.sdk.pfo.pfosdk.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.sdk.pfo.pfosdk.PfoSDK;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lala on 2017/6/12.
 */

public class SDKUtils {
    public static boolean logFlag = true;
    public static boolean isNullOrEmpty(String paramString)
    {
        return (paramString == null) || (paramString.trim().length() == 0);
    }


    public static Map<String, String> getAssetPropConfig(Context paramContext, String paramString)
    {
        try
        {
            Properties localProperties = new Properties();
            localProperties.load(new InputStreamReader(paramContext.getAssets().open(paramString), "UTF-8"));
            HashMap localHashMap = new HashMap();
            Iterator localIterator = localProperties.entrySet().iterator();
            while (localIterator.hasNext()){
                Map.Entry localEntry = (Map.Entry)localIterator.next();
                String str1 = localEntry.getKey().toString().trim();
                String str2 = localEntry.getValue().toString().trim();
                if (!localHashMap.containsKey(str1)) {
                    localHashMap.put(str1, str2);
                }
            }

            return localHashMap;

        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return null;
    }

    public static String getAssetConfigs(Context context, String fileName)
    {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void toast(String msg){
        Toast.makeText(PfoSDK.getInstance().getContext(),msg,Toast.LENGTH_LONG).show();
    }

    public static void log(String tag,String msg){
        if(!logFlag)return;
        if(isNullOrEmpty(tag)){
            Log.d("pfosdk",msg);
        }else {
            Log.d(tag,msg);
        }

    }


    public static boolean arrayContain(String[] array, String str)
    {
        if ((array == null) || (array.length == 0)) {return false;}
        for (String s :array){
            if(s.equals(str))return true;
        }
        return false;
    }

    static String imei = null,mac = null;
    public static String getDeviceId(Context mContext) {
        try{
            if (isNullOrEmpty(imei)) {
                TelephonyManager tm = (TelephonyManager) mContext
                        .getSystemService(Context.TELEPHONY_SERVICE);
                imei = tm.getDeviceId();
                if (isNullOrEmpty(imei)|| imei.equals("0"))
                    imei = getMacAddress(mContext).replaceAll(":", "_");
            }
        }catch (Exception e){
            imei = getMacAddress(mContext).replaceAll(":", "_");
        }
        return imei;
    }

    public static String getMacAddress(Context mContext) {
        String result = "";
        try {
            if (!isNullOrEmpty(mac)) return mac;
            WifiManager wifiManager = (WifiManager) mContext
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            result = wifiInfo.getMacAddress();
            mac = result;

            if (mac == null || mac.equals("")) mac = getIDFinal();
        }catch (Exception e){
            if (mac == null || mac.equals("")) mac = getIDFinal();
        }
        return result;
    }

    public static String getIDFinal(){
        String m_szDevIDShort="35";
        try{
            m_szDevIDShort = "35" + //we make this look like a valid IMEI
                    Build.BOARD.length()%10 +
                    Build.BRAND.length()%10 +
                    Build.CPU_ABI.length()%10 +
                    Build.DEVICE.length()%10 +
                    Build.DISPLAY.length()%10 +
                    Build.HOST.length()%10 +
                    Build.ID.length()%10 +
                    Build.MANUFACTURER.length()%10 +
                    Build.MODEL.length()%10 +
                    Build.PRODUCT.length()%10 +
                    Build.TAGS.length()%10 +
                    Build.TYPE.length()%10 +
                    Build.USER.length()%10 ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return m_szDevIDShort;
    }

}
