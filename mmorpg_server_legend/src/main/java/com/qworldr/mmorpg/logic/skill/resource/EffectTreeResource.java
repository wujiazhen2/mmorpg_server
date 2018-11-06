package com.qworldr.mmorpg.logic.skill.resource;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.logic.skill.bean.EffectTree;
import com.qworldr.mmorpg.logic.skill.bean.EffectTreeNode;
import com.qworldr.mmorpg.provider.IAfterLoad;

import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @Author wujiazhen
 */
@Resource
public class EffectTreeResource  implements IAfterLoad {
    @Id
    private int id;

    private EffectTreeNode effectTreeNode;

    /**
     * 资源文件加载完全后,在加载函数中处理
     */
    @Transient
    private EffectTree effectTree;

    @Override
    public void afterLoad() {
        //将effectTreeNode转为effectTree;
    }
}
