package com.qworldr.mmorpg.common.constants;

import com.qworldr.mmorpg.common.anno.Desc;

public interface StatusCode {
    @Desc("成功")
    short SUCCESS = 200;
    @Desc("失败")
    short ERROR = 500;

}
