package com.android.sdk.pfo.pfosdk.bo;

/**
 * Created by lala on 2017/7/24.
 */

public class PfoBalance {
    //货币id
    private int balanceId;
    //货币名称
    private String balanceName;
    //货币数额
    private int balanceNum;

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public String getBalanceName() {
        return balanceName;
    }

    public void setBalanceName(String balanceName) {
        this.balanceName = balanceName;
    }

    public int getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(int balanceNum) {
        this.balanceNum = balanceNum;
    }
}
