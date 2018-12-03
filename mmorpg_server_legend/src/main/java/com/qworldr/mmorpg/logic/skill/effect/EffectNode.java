package com.qworldr.mmorpg.logic.skill.effect;

import com.qworldr.mmorpg.common.behaviortree.enu.NodeStatus;
import com.qworldr.mmorpg.common.behaviortree.node.BActionNode;
import com.qworldr.mmorpg.common.behaviortree.node.NodeContext;

/**
 * @Author wujiazhen
 */
public abstract class EffectNode extends BActionNode {

    private int effectId;
    @Override
    protected NodeStatus update(NodeContext params) {
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
    public abstract void start(SkillContext skillContext);

    /**
     * 效果执行结束
     * @param skillContext
     */
    public abstract void end(SkillContext skillContext);

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
}
