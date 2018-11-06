package com.qworldr.mmorpg.logic.skill.bean;

import com.qworldr.mmorpg.logic.behaviortree.enu.NodeType;

import java.util.List;

/**
 * @Author wujiazhen
 * 效果树配置节点
 */
public class EffectTreeNode {
    private NodeType nodeType;
    private int effectId;
    private List<EffectTreeNode> childs;


    public List<EffectTreeNode> getChilds() {
        return childs;
    }


    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public int getEffectId() {
        return effectId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }
}
