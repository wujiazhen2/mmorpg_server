package com.qworldr.mmorpg.logic.behaviortree.node.impl;

import com.qworldr.mmorpg.logic.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.logic.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.logic.behaviortree.node.NodeContext;


/**
 * @Author wujiazhen
 * 组合节点之一 Selector节点
 * 选择节点(Select)，遍历方式为从左到右依次执行所有子节点，只要节点返回 Fail，
 * 就继续执行后续节点，直到一个节点返回Success或Running为止，停止执行后续节点。如果有一
 * 个节点返回Success或Running则向父节点返回Success或Running。否则向父节点返回 Fail。
 * 当子节点返回 Running时，除了停止实行后续节点、向父节点返回 Running 外，还要保存返回Running 的这个节点，
 * 下次迭代则直接从该节点开始执行。
 */
public class BSelectorNode extends BCompositeNode {

    @Override
    protected NodeStatus update(NodeContext params) {
        NodeStatus result;
        //从活跃节点开始执行。
        for (int i = this.getActiveChildIndex(); i < getChilds().size(); i++) {
            result = getChilds().get(i).tick(params);
            //如果节点成功，直接返回
            if (NodeStatus.SUCCESS.equals(result)) {
                return result;
            //如果节点是RUNNING状态,则记录节点，下次执行。
            } else if (NodeStatus.RUNNING.equals(result)) {
                this.setActiveChildIndex(i);
                return result;
            }
        }
        return NodeStatus.FAIL;
    }
}
