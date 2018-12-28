package com.qworldr.mmorpg.common.behaviortree.enu;

import com.qworldr.mmorpg.common.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.common.behaviortree.node.impl.BSelectorNode;
import com.qworldr.mmorpg.common.behaviortree.node.impl.BSequenceNode;

/**
 * @author wujiazhen
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
