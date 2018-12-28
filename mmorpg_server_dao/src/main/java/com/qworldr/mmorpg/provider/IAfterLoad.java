package com.qworldr.mmorpg.provider;

/**
 * @author wujiazhen
 * 资源类继承IAfterLoad方法后，在资源文件加载完成后，会执行afterLoad方法。
 */
public interface IAfterLoad {

    void afterLoad();
}
