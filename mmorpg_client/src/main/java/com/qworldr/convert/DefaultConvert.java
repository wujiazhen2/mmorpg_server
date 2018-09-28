package com.qworldr.convert;

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
        PropertyEditor defaultEditor = this.getDefaultEditor(clazz);
        if(defaultEditor==null){
            defaultEditor=this.findCustomEditor(clazz,null);
        }
        if(defaultEditor==null){
            throw new RuntimeException(String.format("缺少%d类型ProertyeEditor",clazz));
        }
        defaultEditor.setAsText(value);
        return (T) defaultEditor.getValue();
    }
}
