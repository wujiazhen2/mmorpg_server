package com.qworldr.mmorpg.logic.attribute.enu;

import com.qworldr.mmorpg.logic.player.Player;

import java.util.Arrays;

/**
 * @author wujiazhen
 * 属性类型
 *
 */
public enum AttributeType {

    /** 二级属性 */
    /**
     * 血量
     */
    HP(true),
    /**
     * 法力
     */
    MP(true),
    /**
     *  物理攻击
     */
    ATK(true),
    /**
     *  魔法攻击
     */
    MAG(true),

    /**
     * 攻击速度
     */
    WSP(true),
    /**
     * 移动速度  100 时 一步/s
     */
    MOV(true),


    /** 一级属性 会影响二级属性 */
    /**
     * 力量
     */
    STR(false,ATK),
    /**
     * 智力
     */
    INT(false,MP,MAG),
    /**
     *  敏捷
     */
    DEX(false,WSP,MOV),
    /**
     *  体质
     */
    CON(false,HP),
    ;
    /**
     * 是否战斗属性，即二级属性
     */
    private boolean combat;
    /**
     *  一级属性影响的战斗属性
     */
    private AttributeType[] attributeTypes;
    AttributeType(boolean combat ,AttributeType...effectAttr){
            this.combat=combat;
            this.attributeTypes=effectAttr;
    }

    public boolean isCombat() {
        return combat;
    }

    public AttributeType[] getAttributeTypes() {
        return attributeTypes;
    }

    public int cal(Player player){
        int value=0;
        return value;
    }
}
