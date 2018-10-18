package com.qworldr.mmorpg.common.reader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qworldr.mmorpg.convert.DefaultConvert;
import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.logic.map.Grid;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import com.qworldr.mmorpg.reader.Reader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author wujiazhen
 */
@Component
public class MapReader implements Reader {

    public static final String GRIDS = "grids";

    @Override
    public Map read(ResourceMetaData resourceMetaData) throws Exception {
        Resource[] resources = resourceMetaData.getResources();
        Map map = new HashMap();
        Class cls = resourceMetaData.getReourceClass();
        Field keyField = cls.getDeclaredField(resourceMetaData.getKeyCol());
        keyField.setAccessible(true);
        DefaultConvert convert = new DefaultConvert();
        Field field;
        for (Resource resource : resources) {
            InputStream inputStream;
            String text;
            inputStream = resource.getInputStream();
            text = FileCopyUtils.copyToString(new InputStreamReader(inputStream));
            Object o = cls.newInstance();
            String filename = resource.getFilename();
            String s = StringUtils.stripFilenameExtension(filename);
            //文件名是id;
            keyField.set(o, convert.covert(s, keyField.getType()));
            Field[] declaredFields = cls.getDeclaredFields();
            JSONObject jsonObject = JSON.parseObject(text);
            Set<String> keySet = jsonObject.keySet();
            for (String key : keySet) {
                field = cls.getDeclaredField(key);
                field.setAccessible(true);
                if(GRIDS.equals(key)){
                    int[][] grids = jsonObject.getObject(key, int[][].class);
                    Grid[][] g= new Grid[grids.length][];
                    //左下角坐标为(0,0)
                    for (int i=0;i<grids.length;i++) {
                        g[grids.length-i-1]=new Grid[grids[i].length];
                        for(int j=0;j<grids[i].length;j++){
                            g[grids.length-i-1][j]=new Grid(grids[i][j]);
                        }
                    }
                    field.set(o,g);
                }else {
                    field.set(o, jsonObject.getObject(key, field.getType()));
                }
            }

            map.put(keyField.get(o), o);
        }
        return map;
    }

    @Override
    public ReaderType getReaderType() {
        return ReaderType.MAP;
    }
}
