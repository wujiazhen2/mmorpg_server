package com.qworldr.mmorpg.type;

import com.alibaba.druid.support.json.JSONUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class JsonType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.CLOB};
    }

    @Override
    public Class returnedClass() {
        return Object.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return JSONUtils.toJSONString(o).equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return JSONUtils.toJSONString(o).hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String json = resultSet.getString(strings[0]);
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return JSONUtils.parse(json);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o == null) {
            preparedStatement.setString(i, "");
        } else {
            preparedStatement.setString(i, JSONUtils.toJSONString(o));
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        String s = JSONUtils.toJSONString(o);
        return JSONUtils.parse(s);
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable) deepCopy(o);
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return deepCopy(serializable);
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return deepCopy(o);
    }
}
