package com.qworldr.mmorpg.logic.skill.tree.impl;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.common.behaviortree.node.BTreeNode;
import com.qworldr.mmorpg.logic.skill.tree.ControllerNode;
import com.qworldr.mmorpg.logic.skill.tree.SkillContext;

/**
 * 顺序节点，顺序执行所有子节点。所有子节点都执行成功才返回SUCCESS
 *
 * @author wujiazhen
 * @Date 2019/1/2
 */
public class SequenceNode extends ControllerNode {
    @Override
    public NodeStatus execute(SkillContext skillContext) {
        NodeStatus tick;
        NodeStatus result = NodeStatus.SUCCESS;
        for (BTreeNode child : this.getChilds()) {
            tick = child.tick(skillContext);
            if (tick.equals(NodeStatus.FAIL)) {
                result = NodeStatus.FAIL;
            }
        }
        return result;
    }
}
