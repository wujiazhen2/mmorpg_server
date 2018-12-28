package com.qworldr.mmorpg.common.behaviortree.node;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;

import java.util.List;

/**
 * @author wujiazhen
 * 行为树基础节点
 */
public abstract class BTreeNode {

    private BTreeNode parent;
    private List<BTreeNode> childs;
    /**
     * 上次的执行记录节点的状态
     */
    private NodeStatus status;

    public NodeStatus tick(NodeContext params) {
        //当前节点状态为null,说明这个tick
        if (status == null) {
            init(params);
        }
        NodeStatus result = update(params);
        //节点状态不等于RUNNING，说明这次tick结束。
        this.status=result;
        if (!NodeStatus.RUNNING.equals(this.status)) {
            destory(params);
            this.status=null;
        }
        return result;
    }

    public void init(NodeContext params) {
    }

    public void destory(NodeContext params) {
    }

    public BTreeNode getParent() {
        return parent;
    }

    protected abstract NodeStatus update(NodeContext params);

    public NodeStatus getStatus() {
        return status;
    }

    public void setStatus(NodeStatus status) {
        this.status = status;
    }

    public void setParent(BTreeNode parent) {
        this.parent = parent;
    }

    public List<BTreeNode> getChilds() {
        return childs;
    }

    public void setChilds(List<BTreeNode> childs) {
        this.childs = childs;
    }
}
