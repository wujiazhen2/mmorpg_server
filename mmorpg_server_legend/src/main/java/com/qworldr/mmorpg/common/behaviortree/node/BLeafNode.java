package com.qworldr.mmorpg.common.behaviortree.node;

import java.util.List;

/**
 * @Author wujiazhen
 */
public abstract class BLeafNode extends BTreeNode {
    @Override
    public List<BTreeNode> getChilds() {
        throw new UnsupportedOperationException("action node is a  leaf node");
    }

    @Override
    public void setChilds(List<BTreeNode> childs) {
        throw new UnsupportedOperationException("action node is a  leaf node");
    }
}
