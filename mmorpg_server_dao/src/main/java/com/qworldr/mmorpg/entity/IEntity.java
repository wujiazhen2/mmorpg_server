package com.qworldr.mmorpg.entity;

import com.qworldr.mmorpg.type.JsonType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@TypeDef(name = "json",typeClass = JsonType.class)
public interface IEntity<ID> {

    ID getId();

    void setId(ID id);
}
