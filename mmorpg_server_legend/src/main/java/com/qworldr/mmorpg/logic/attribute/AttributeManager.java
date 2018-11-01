package com.qworldr.mmorpg.logic.attribute;

import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;

import java.util.Map;

/**
 * @Author wujiazhen
 * 影响属性的因素有 属性点，装备，称号。。。多个模块。以属性树的形式记录属性的起源。
 *
 */
public class AttributeManager {
    public void setAttr(AttributeSourceType sourceType, Map<AttributeType, Integer> attrs) {
    }
    public int getAttr(AttributeType type){
        return 100;
    }
}
