package com.qworldr.mmorpg.logic.skill.tree;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.common.behaviortree.node.BCompositeNode;
import com.qworldr.mmorpg.common.behaviortree.node.NodeContext;

/**
 * 控制节点
 *
 * @author wujiazhen
 * @Date 2019/1/2
 */
public abstract class ControllerNode extends BCompositeNode {

    @Override
    protected NodeStatus update(NodeContext params) {
        return execute((SkillContext) params);
    }

    /**
     * 执行效果逻辑
     *
     * @param skillContext
     * @return
     */
    public abstract NodeStatus execute(SkillContext skillContext);
}
