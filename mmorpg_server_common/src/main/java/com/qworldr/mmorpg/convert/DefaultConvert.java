package com.qworldr.mmorpg.convert;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditor;
import java.util.Date;
@Component
public class DefaultConvert extends PropertyEditorRegistrySupport {

    public DefaultConvert(){
        registerDefaultEditors();
        registerCustomEditor(Date.class, new DateEditor());
    }

    public <T>  T  covert(String value,Class<T> clazz)  {
        if(clazz.equals(String.class)){
            return (T) value;
        }
        PropertyEditor defaultEditor = this.getDefaultEditor(clazz);
        if(defaultEditor==null){
            defaultEditor=this.findCustomEditor(clazz,null);
        }
        //不是注册类型，就是枚举或者json
        if(defaultEditor==null){
            if(clazz.isEnum()){
                return (T) Enum.valueOf((Class<Enum>)clazz,value);
            }else {
                T parse = (T) JSON.parseObject(value,clazz);
                return parse;
            }
        }
        defaultEditor.setAsText(value);
        return (T) defaultEditor.getValue();
    }
}
