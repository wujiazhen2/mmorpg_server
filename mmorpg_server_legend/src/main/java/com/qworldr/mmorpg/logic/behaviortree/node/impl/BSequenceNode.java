package com.qworldr.mmorpg.logic.behaviortree.node.impl;

import com.qworldr.mmorpg.logic.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.logic.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.logic.behaviortree.node.NodeContext;


/**
 * @Author wujiazhen
 * 组合节点之一 Sequence节点
 * 顺序节点(Sequence),它从左向右依次执行所有节点，只要节点返回Success，就继续执行后续节点，
 * 当一个节点返回Fail或 Running 时，停止执行后续节点。向父节点返回 Fail 或 Running，
 * 只有当所有节点都返回 Success 时，才向父节点返回 Success。与选择节点相似，当节点返回Running 时，
 * 顺序节点除了终止后续节点的执行，还要记录返回 Running的这个节点，下次迭代会直接从该节点开始执行。
 */
public class BSequenceNode extends BCompositeNode {

    @Override
    protected NodeStatus update(NodeContext params) {
        NodeStatus result;
        //从活跃节点开始执行。
        for (int i = this.getActiveChildIndex(); i < getChilds().size(); i++) {
            result = getChilds().get(i).tick(params);
            //如果节点失败，直接返回
            if(NodeStatus.FAIL.equals(result)){
                return result;
            //如果节点是RUNNING状态,则记录节点，下次执行。
            }else if(NodeStatus.RUNNING.equals(result)){
                this.setActiveChildIndex(i);
                return result;
            }
        }
        return NodeStatus.SUCCESS;
    }
}
