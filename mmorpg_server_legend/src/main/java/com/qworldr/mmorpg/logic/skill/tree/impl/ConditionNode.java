package com.qworldr.mmorpg.logic.skill.tree.impl;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.common.behaviortree.node.BTreeNode;
import com.qworldr.mmorpg.logic.skill.bean.EffectNodeResource;
import com.qworldr.mmorpg.logic.skill.tree.ControllerNode;
import com.qworldr.mmorpg.logic.skill.tree.SkillContext;

/**
 * 条件节点，只有满足条件才执行子节点
 *
 * @author wujiazhen
 * @Date 2019/1/2
 */
public class ConditionNode extends ControllerNode {
    private EffectNodeResource resource;

    public ConditionNode(EffectNodeResource resource) {
        this.resource = resource;
    }

    @Override
    public NodeStatus execute(SkillContext skillContext) {
        //TODO 判断resource中的条件是否满足
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
