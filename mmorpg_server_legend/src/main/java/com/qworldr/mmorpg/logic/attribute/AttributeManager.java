package com.qworldr.mmorpg.logic.attribute;

import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import com.qworldr.mmorpg.utils.MapUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author wujiazhen
 * 影响属性的因素装备，称号。。。多个模块。以属性树的形式记录属性的起源。
 * 属性可以分为一级属性和二级属性，一级属性不直接做战斗计算，用于影响二级属性。
 * 二级属性是直接的战斗属性。只有在根节点再将一级属性转化为二级属性 合并在combatAttrs上。其他地方存的都是两种混合的属性。
 * 属性操作的唯一入口
 */
public class AttributeManager extends AttrNode {
    public AttributeManager() {
    }

    /**
     * 完整的战斗属性，包含一级属性转化的部分。
     */
    private Map<AttributeType, Integer> combatAttrs = new HashMap<>();
    /**
     * 一级属性
     */
    private Map<AttributeType, Integer> primaryAttrs = new HashMap<>();
    /**
     * 一级属性转化的属性
     */
    private Map<AttributeType, Integer> primaryCombatAttrs;

    /**
     * 更新玩家属性，属性添加到根节点下
     *
     * @param key   属性节点key，一般是AttributeSourceType，标识属性来源
     * @param attrs
     */
    public void updateAttr(Object key, Map<AttributeType, Integer> attrs) {
        AttrNode newNode = new AttrNode(key, attrs);
        AttrNode oldNode = addOrUpdateChildAttr(key, newNode);
        updateRootAttrs(newNode, oldNode);
    }

    /**
     * 更新玩家属性，属性节点添加到指定节点下
     *
     * @param attr  指定节点
     * @param key   属性节点key，一般是AttributeSourceType，标识属性来源
     * @param attrs
     */
    public void updateAttr(AttrNode attr, Object key, Map<AttributeType, Integer> attrs) {
        AttrNode newNode = new AttrNode(key, attrs);
        AttrNode oldNode = attr.addOrUpdateChildAttr(key, newNode);
        updateRootAttrs(newNode, oldNode);
    }

    /**
     * 获取根节点下的属性节点
     *
     * @param key
     * @return
     */
    public AttrNode getAttrNode(Object key) {
        return this.getChildAttrs().get(key);
    }

    /**
     * 获取指定节点下的属性节点。
     *
     * @param parent
     * @param key
     * @return
     */
    public AttrNode getAttrNode(AttrNode parent, Object key) {
        return parent.getChildAttrs().get(key);
    }

    /**
     * 更新玩家真实属性，新的属性节点替换旧节点
     *
     * @param newNode
     * @param oldNode
     */
    private void updateRootAttrs(AttrNode newNode, AttrNode oldNode) {
        //如果原来也有节点,就需要减掉原来的属性
        Map<AttributeType, Integer> changeAttrs;
        if (oldNode != null) {
            changeAttrs = MapUtils.sub(newNode.getAllAttrs(), oldNode.getAllAttrs());
        } else {
            changeAttrs = newNode.getAllAttrs();
        }
        Set<AttributeType> set = new HashSet<>();
        changeAttrs.forEach((k, v) -> {
            if (k.isCombat()) {
                this.combatAttrs.put(k, this.combatAttrs.getOrDefault(k, 0) + v);
            } else {
                //一级属性更改还要修改影响的二级属性。
                this.primaryAttrs.put(k, this.primaryAttrs.getOrDefault(k, 0) + v);
                //记录下修改了的一级属性，待会重新计算改变的二级属性。
                set.add(k);
            }
        });
        if (set.size() > 0) {
            updateAttrsWithPrimaryAttrs(set);
        }
        long l1 = System.currentTimeMillis();
    }

    private void updateAttrsWithPrimaryAttrs(Set<AttributeType> set) {

        //转化公式，暂时1比1把。。。。后面应该改成读配置
        //受影响的一级属性重新计算转化二级属性
        Map<AttributeType, Integer> newAttrs = new HashMap<>();
        for (AttributeType type : set) {
            primaryTransferCombat(type).forEach((k, v) -> {
                newAttrs.put(k, newAttrs.getOrDefault(k, 0) + v);
            });
        }
        //一级属性转化的属性和战斗属性
        //第一次设置，不用考虑替换，全部覆盖即可。
        if(primaryCombatAttrs==null){
            primaryCombatAttrs=newAttrs;
            newAttrs.forEach((k,v)->{
                combatAttrs.put(k, combatAttrs.getOrDefault(k, 0)+v);
            });
            return;
        }
        newAttrs.forEach((k, v) -> {
            Integer oldValue = primaryCombatAttrs.getOrDefault(k, 0);
            //如果和上次一样就不用执行了。
            if (v == oldValue) {
                return;
            }
            primaryCombatAttrs.put(k, v);
            //战士属性加上改变的就是最后的属性
            int changeValue = v - oldValue;
            combatAttrs.put(k, combatAttrs.getOrDefault(k, 0) + changeValue);
        });


    }

    /**
     * 一级属性转为二级属性的转化逻辑，目前是1:1
     *
     * @param type
     * @return
     */
    public Map<AttributeType, Integer> primaryTransferCombat(AttributeType type) {

        Map<AttributeType, Integer> change = new HashMap<>();
        for (AttributeType attributeType : type.getAttributeTypes()) {
            change.put(attributeType, this.primaryAttrs.get(type));
        }

        return change;
    }

    public Map<AttributeType, Integer> getCombatAttrs() {
        return combatAttrs;
    }

    public Map<AttributeType, Integer> getPrimaryAttrs() {
        return primaryAttrs;
    }


    public int getAttr(AttributeType type) {
        return this.getAttrMap().get(type);
    }
}
