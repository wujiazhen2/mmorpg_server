package com.qworldr.mmorpg.entity;

import com.qworldr.mmorpg.type.JsonType;
import com.qworldr.mmorpg.type.TypeName;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@TypeDef(name = TypeName.JSON, typeClass = JsonType.class)
public interface IEntity<ID> {

    ID getId();

    void setId(ID id);
}
