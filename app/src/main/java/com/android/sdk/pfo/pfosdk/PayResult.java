package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/9.
 */

public class PayResult {


    private int code;//0成功 其他失败
    private String orderID;//应用订单编号
    private String extension;//应用自定义字段-原样返回
    private String productID;//产品编号
    private String productName;//产品名称
    private String msg;//成功或失败信息

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
