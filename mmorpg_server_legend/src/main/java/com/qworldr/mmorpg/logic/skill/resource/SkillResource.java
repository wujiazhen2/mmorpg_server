package com.qworldr.mmorpg.logic.skill.resource;

import com.qworldr.mmorpg.anno.Resource;

import javax.persistence.Id;
import java.util.List;

/**
 * @Author wujiazhen
 */
@Resource
public class SkillResource {
    @Id
    private int id;

    /**
     *  效果id, 应该类似行为树，技能释放通过树逻辑执行各个效果
     */
    private int effectId;

}
