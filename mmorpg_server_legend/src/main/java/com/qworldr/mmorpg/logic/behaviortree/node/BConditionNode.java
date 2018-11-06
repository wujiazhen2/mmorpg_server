package com.qworldr.mmorpg.logic.behaviortree.node;

import com.qworldr.mmorpg.logic.behaviortree.enu.NodeStatus;

/**
 * @Author wujiazhen
 * 行为树条件节点基类
 */
public abstract class BConditionNode extends BLeafNode {


    @Override
    protected NodeStatus update(NodeContext params) {
          //条件节点只有SUCCESS和FAIL状态，将逻辑放到condition方法中判断。
           return condition(params)?NodeStatus.SUCCESS:NodeStatus.FAIL;
    }

    protected abstract boolean condition(NodeContext params);
}
