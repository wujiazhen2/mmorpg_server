package com.qworldr.mmorpg.logic.skill.enu;

import com.qworldr.mmorpg.common.behaviortree.node.BTreeNode;
import com.qworldr.mmorpg.common.behaviortree.node.impl.BSelectorNode;
import com.qworldr.mmorpg.common.behaviortree.node.impl.BSequenceNode;
import com.qworldr.mmorpg.logic.skill.bean.EffectNodeResource;
import com.qworldr.mmorpg.logic.skill.manager.EffectManager;
import com.qworldr.mmorpg.logic.skill.resource.EffectResource;
import com.qworldr.mmorpg.logic.skill.tree.impl.ConditionNode;
import com.qworldr.mmorpg.logic.skill.tree.impl.HarmfulEffectNode;
import com.qworldr.mmorpg.logic.skill.tree.impl.SequenceNode;

/**
 * 技能树节点类型
 *
 * @author wujiazhen
 * @Date 2019/1/2
 */
public enum EffectTreeNodeType {
    /**
     * 顺序执行节点
     */
    SEQUENCE() {
        public BTreeNode createTreeNode(EffectNodeResource resource) {
            return new SequenceNode();
        }
    },
    /**
     * 条件节点
     */
    CONDITION() {
        @Override
        public BTreeNode createTreeNode(EffectNodeResource resource) {
            return new ConditionNode(resource);
        }
    },
    HARMFULEFFECT() {
        @Override
        public BTreeNode createTreeNode(EffectNodeResource resource) {
            EffectResource effectResource = EffectManager.getInstance().getEffectResource(resource.getEffectId());
            return new HarmfulEffectNode(effectResource);
        }
    };

    public abstract BTreeNode createTreeNode(EffectNodeResource resource);
}
