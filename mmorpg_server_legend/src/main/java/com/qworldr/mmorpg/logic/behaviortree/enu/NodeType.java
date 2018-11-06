package com.qworldr.mmorpg.logic.behaviortree.enu;

import com.qworldr.mmorpg.logic.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.logic.behaviortree.node.impl.BSelectorNode;
import com.qworldr.mmorpg.logic.behaviortree.node.impl.BSequenceNode;

/**
 * @Author wujiazhen
 */
public enum  NodeType {

    SELECTOR(){
        @Override
        public BCompositeNode createNode() {
            return new BSelectorNode();
        }
    },
    SEQUENCE(){
        @Override
        public BCompositeNode createNode() {
            return new BSequenceNode();
        }
    },
    /**
     * 叶子节点
     */
    LEFT,
    ;

    public  BCompositeNode createNode(){return null;};
}
