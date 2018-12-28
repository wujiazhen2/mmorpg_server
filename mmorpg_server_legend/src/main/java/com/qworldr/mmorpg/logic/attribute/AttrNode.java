package com.qworldr.mmorpg.logic.attribute;

import com.qworldr.mmorpg.logic.attribute.enu.AttributeSourceType;
import com.qworldr.mmorpg.logic.attribute.enu.AttributeType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wujiazhen
 * 属性树节点
 */
public class AttrNode {
    /**
     *  属性来源,一般是属性来源，该来源下有分类再定义key细分
     */
    private Object key;
    /**
     * 当前节点属性值
     */
    private Map<AttributeType,Integer> attrMap;
    /**
     *  子属性节点
     */
    private Map<Object,AttrNode> childAttrs;

    /**
     * 获取该节点包括其下级节点所有的属性，
     * @return
     */
    public Map<AttributeType,Integer> getAllAttrs(){
        if(childAttrs==null || childAttrs.size()==0){
            return attrMap;
        }else{
            Map<AttributeType,Integer> map = new HashMap(attrMap);
            childAttrs.forEach((k,v)->{
                Map<AttributeType, Integer> allAttrs = v.getAllAttrs();
                for(AttributeType type :attrMap.keySet()){
                    map.put(type,map.getOrDefault(type,0)+allAttrs.get(type));
                }
            });
            return map;
        }
    }

    /**
     * 获取该节点的属性
     * @return
     */
    public Map<AttributeType, Integer> getAttrMap() {
        return attrMap;
    }
     AttrNode(){
        this.attrMap=new HashMap<>();
     }
    public AttrNode(Object type, Map<AttributeType, Integer> attrMap) {
        this.key = type;
        this.attrMap = attrMap;
    }

    public Object getKey() {
        return key;
    }

     Map<Object, AttrNode> getChildAttrs() {
        return childAttrs;
    }

     AttrNode addOrUpdateChildAttr(Object key,AttrNode attrNode) {
        if(this.childAttrs==null){
            this.childAttrs=new HashMap<>();
        }
        //添加新的属性节点或者替换旧的属性。
        AttrNode old = childAttrs.put(key, attrNode);
        return old;
    }
}
