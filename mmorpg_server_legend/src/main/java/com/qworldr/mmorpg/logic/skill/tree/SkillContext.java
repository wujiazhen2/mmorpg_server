package com.qworldr.mmorpg.logic.skill.tree;

import com.qworldr.mmorpg.common.behaviortree.node.NodeContext;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;

/**
 * @author wujiazhen
 */
public class SkillContext extends NodeContext {
    /**
     * 释放技能的生物
     */
    private BiologyObject releaser;
    /**
     * 技能的攻击目标
     */
    private BiologyObject target;
    /**
     * 技能的目标
     */
    private Position[]  targetPosition;
}
