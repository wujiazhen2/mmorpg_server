package com.qworldr.mmorpg;

import com.qworldr.mmorpg.logic.account.entity.AccountEntity;
import com.qworldr.mmorpg.provide.HibernateDataProvider;
import org.apache.commons.jexl3.*;
import org.apache.commons.jexl3.internal.Engine;
import org.junit.Test;

public class Test01 {

    static class AccountDao extends HibernateDataProvider<AccountEntity, Long> {

    }

    @Test
    public void test() {
//        new AccountDao();
//        new HibernateDataProvider<AccountEntity,Long>(){};
        Class<?> accountEntityClass = AccountEntity.class;
        Class<?> longClass = Long.class;
       register(accountEntityClass,longClass);



    }

    public <T, ID> HibernateDataProvider<T, ID> register(Class<T> entity, Class<ID> id) {
//        return new HibernateDataProvider<T, ID>() {
//        };
        JexlEngine jexlEngine = new Engine();
        JexlExpression expression = jexlEngine.createExpression(String.format("new com.qworldr.mmorpg.provide.HibernateDataProvider<%s, %s>(){}", entity.getName(), id.getName()));
        JexlContext jc = new MapContext();
        Object evaluate = expression.evaluate(jc);
        return (HibernateDataProvider<T, ID>) evaluate;
    }
}
