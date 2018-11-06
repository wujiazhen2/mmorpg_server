package com.qworldr.mmorpg.logic.behaviortree.enu;

import com.qworldr.mmorpg.logic.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.logic.behaviortree.node.impl.BSelectorNode;
import com.qworldr.mmorpg.logic.behaviortree.node.impl.BSequenceNode;

/**
 * @Author wujiazhen
 */
public enum  NodeType {

    SELECTOR(false){
        @Override
        public BCompositeNode createNode() {
            return new BSelectorNode();
        }
    },
    SEQUENCE(false){
        @Override
        public BCompositeNode createNode() {
            return new BSequenceNode();
        }
    },
    /**
     * 叶子节点
     */
    ACTION,
    CONDITION
    ;
    private boolean isLeaf;
     NodeType(){
        this.isLeaf=true;
    }
     NodeType(boolean isLeaf){
        this.isLeaf=isLeaf;
    }
    public  BCompositeNode createNode(){return null;};

    public boolean isLeaf() {
        return isLeaf;
    }

}
