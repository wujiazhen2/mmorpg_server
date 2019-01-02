package com.qworldr.mmorpg.common.behaviortree.node;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * @author wujiazhen
 * 行为树组合节点基类
 */
@NotThreadSafe
public abstract class BCompositeNode extends BTreeNode {
    /**
     * 用于记录当前正在RUNNING状态的子节点，默认是0。
     */
    private int activeChildIndex;
    public boolean addChild(BTreeNode child){
        return this.getChilds().add(child);
    }

    @Override
    public void destory(NodeContext params) {
        //重置RUNNING状态的子节点脚标
        resetChildIndex();
    }

    public int getActiveChildIndex() {
        return activeChildIndex;
    }
    public void setActiveChildIndex(int activeChildIndex) {
        this.activeChildIndex = activeChildIndex;
    }
    public void resetChildIndex(){
        this.activeChildIndex=0;
    }
}
