package com.qworldr.mmorpg.logic.skill.tree;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.common.behaviortree.node.BActionNode;
import com.qworldr.mmorpg.common.behaviortree.node.NodeContext;
import com.qworldr.mmorpg.logic.skill.resource.EffectResource;

/**
 * 技能效果节点
 * @author wujiazhen
 */
public abstract class EffectNode extends BActionNode {

    private EffectResource effectResource;

    public EffectNode(EffectResource resource) {
        this.effectResource = resource;
    }
    private int effectId;
    @Override
    protected final NodeStatus update(NodeContext params) {
        //效果释放逻辑
        return execute((SkillContext)params);
    }

    @Override
    public void init(NodeContext params) {
        start((SkillContext)params);
    }

    @Override
    public void destory(NodeContext params) {
        end((SkillContext)params);
    }

    /**
     *  效果开始执行
     * @param skillContext
     */
    public void start(SkillContext skillContext) {
    }

    ;

    /**
     * 效果执行结束
     * @param skillContext
     */
    public void end(SkillContext skillContext) {
    }

    ;

    /**
     * 执行效果逻辑
     * @param skillContext
     * @return
     */
    public abstract NodeStatus execute(SkillContext skillContext);

    public int getEffectId() {
        return effectId;
    }

    public void setEffectId(int effectId) {
        this.effectId = effectId;
    }

    public EffectResource getEffectResource() {
        return effectResource;
    }
}
