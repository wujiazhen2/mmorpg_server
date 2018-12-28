package com.qworldr.mmorpg;

import com.qworldr.mmorpg.logic.attribute.AttributeManager;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujiazhen
 */
public class AttrTest {
    AttributeManager attributeManager;
    @Before
    public void before(){
        attributeManager= new AttributeManager();
        long l = System.currentTimeMillis();
        Map<AttributeType,Integer> map =new HashMap<>();
        map.put(AttributeType.CON,5);
        map.put(AttributeType.STR,10);
        attributeManager.updateAttr(AttributeSourceType.STATPOINT,map);
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
    @Test
    public void test01(){
        Map<AttributeType,Integer> map =new HashMap<>();
        long l = System.currentTimeMillis();
        map =new HashMap<>();
        map.put(AttributeType.ATK,10);
        map.put(AttributeType.INT,5);
        attributeManager.updateAttr(AttributeSourceType.INIT,map);
        map =new HashMap<>();
        map.put(AttributeType.CON,2);
        map.put(AttributeType.STR,8);
        map.put(AttributeType.DEX,10);
        attributeManager.updateAttr(AttributeSourceType.STATPOINT,map);
        Map<AttributeType, Integer> combatAttrs = attributeManager.getCombatAttrs();
        long l1 = System.currentTimeMillis();
        System.out.println(l1-l);
    }
}
