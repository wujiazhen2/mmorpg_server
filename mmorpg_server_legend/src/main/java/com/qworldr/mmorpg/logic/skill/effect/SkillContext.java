package com.qworldr.mmorpg.logic.skill.effect;

import com.qworldr.mmorpg.common.behaviortree.node.NodeContext;
import com.qworldr.mmorpg.logic.map.Position;
import com.qworldr.mmorpg.logic.map.object.BiologyObject;

/**
 * @author wujiazhen
 */
public class SkillContext extends NodeContext {
    private BiologyObject target;
    private Position[]  targetPosition;
}