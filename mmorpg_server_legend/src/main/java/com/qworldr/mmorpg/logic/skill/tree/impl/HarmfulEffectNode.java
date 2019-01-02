package com.qworldr.mmorpg.logic.skill.tree.impl;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.logic.skill.resource.EffectResource;
import com.qworldr.mmorpg.logic.skill.tree.EffectNode;
import com.qworldr.mmorpg.logic.skill.tree.SkillContext;

/**
 * 伤害技能节点
 *
 * @author wujiazhen
 * @Date 2019/1/2
 */
public class HarmfulEffectNode extends EffectNode {
    public HarmfulEffectNode(EffectResource resource) {
        super(resource);
    }

    @Override
    public NodeStatus execute(SkillContext skillContext) {
        return null;
    }
}
