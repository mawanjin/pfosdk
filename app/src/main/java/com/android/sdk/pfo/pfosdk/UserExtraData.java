package com.android.sdk.pfo.pfosdk;

/**
 * Created by lala on 2017/6/12.
 */

public class UserExtraData {
    public static final int TYPE_CREATE_ROLE = 2;
    public static final int TYPE_ENTER_GAME = 3;
    public static final int TYPE_LEVEL_UP = 4;
    public static final int TYPE_EXIT_GAME = 5;
    public static final int TYPE_SELECT_SERVER = 1;

    private int dataType;
    private int moneyNum;
    private long roleCreateTime;
    private String roleID;
    private String roleLevel;
    private long roleLevelUpTime;
    private String roleName;
    private String serverID;
    private String serverName;

    public static int getTypeCreateRole() {
        return TYPE_CREATE_ROLE;
    }

    public static int getTypeEnterGame() {
        return TYPE_ENTER_GAME;
    }

    public static int getTypeLevelUp() {
        return TYPE_LEVEL_UP;
    }

    public static int getTypeExitGame() {
        return TYPE_EXIT_GAME;
    }

    public static int getTypeSelectServer() {
        return TYPE_SELECT_SERVER;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getMoneyNum() {
        return moneyNum;
    }

    public void setMoneyNum(int moneyNum) {
        this.moneyNum = moneyNum;
    }

    public long getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(long roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

    public long getRoleLevelUpTime() {
        return roleLevelUpTime;
    }

    public void setRoleLevelUpTime(long roleLevelUpTime) {
        this.roleLevelUpTime = roleLevelUpTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
