package com.android.sdk.pfo.pfosdk.bo;

import java.util.List;

/**
 * Created by lala on 2017/7/24.
 */

public class PfoMetaUserInfo {
    //enterServer（登录），levelUp（升级），createRole（创建角色），exitServer（退出）
    private String type;
    //游戏服id
    private String serverId;
    //游戏服名称
    private String serverName;
    //游戏区id
    private String districtId;
    //游戏区名称
    private String districtName;

    private String roleId;
    private String roleName;
    //玩家的职业id
    private String professionId;
    //玩家的职业名称
    private String profession;
    //男女无
    private String gender;

    //职业称号id
    private String professionRoleid;
    //职业称号
    private String professionName;
    //角色等级
    private int roleLevel;
    //战力值
    private int power;
    //vip等级
    private int vip;

    private List<PfoBalance> balance;

    //帮派id
    private int partyId;
    //帮派名称
    private String partyName;
    //帮派称号id
    private String partyRoleId;
    //帮派称号名称
    private String partyRoleName;

    private List<PfoFriend> friendlist;

    private List<PfoRanking> ranking;



}
