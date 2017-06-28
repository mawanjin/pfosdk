package com.android.sdk.pfo.pfosdk;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by lala on 2017/6/9.
 */

public class SDKParams {
    private Map<String,String> configs = new HashMap<>();

    public SDKParams(Map<String,String> params){

        Iterator localIterator = null;
        if(params != null){
            localIterator = params.keySet().iterator();
            while (localIterator.hasNext()){
                String str = (String) localIterator.next();
                put(str,params.get(str));
            }
        }
    }

    public boolean contains(String paramString)
    {
        return this.configs.containsKey(paramString);
    }

    public Boolean getBoolean(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Boolean.valueOf(Boolean.parseBoolean(str));
    }

    public Byte getByte(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Byte.valueOf(Byte.parseByte(str));
    }

    public Double getDouble(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Double.valueOf(Double.parseDouble(str));
    }

    public Float getFloat(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Float.valueOf(Float.parseFloat(str));
    }

    public int getInt(String paramString)
    {
        int r = 0;
        String str = configs.get(paramString);
        try{
            r = Integer.parseInt(str);
        }catch (Exception e){
            r = -1;
        }

        return r;
    }

    public Long getLong(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(str));
    }

    public Short getShort(String paramString)
    {
        String str = getString(paramString);
        if (str == null) {
            return null;
        }
        return Short.valueOf(Short.parseShort(str));
    }

    public String getString(String paramString)
    {
        if (this.configs.containsKey(paramString)) {
            return (String)this.configs.get(paramString);
        }
        return null;
    }

    public void put(String paramString1, String paramString2)
    {
        this.configs.put(paramString1, paramString2);
    }

    public String toString()
    {
        StringBuilder localStringBuilder = new StringBuilder();
        Iterator localIterator = this.configs.keySet().iterator();
        for (;;)
        {
            if (!localIterator.hasNext()) {
                return localStringBuilder.toString();
            }
            String str = (String)localIterator.next();
            localStringBuilder.append(str).append("=").append((String)this.configs.get(str)).append("\n");
        }
    }
}

