package com.qworldr.mmorpg.provider;

import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.utils.ReflectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

public  class HibernateEntityProvider<T extends IEntity,ID extends Serializable> extends HibernateDaoSupport implements EntityProvider<T,ID> {
    private Class<T> entityClass;
    @Autowired
    public  void setSessionFactoryValue(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    public HibernateEntityProvider(){
        this.entityClass= (Class<T>) ReflectUtils.getSuperGenericType(getClass());
    }
    public T get(ID id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    public List<T> load(){
        return  this.getHibernateTemplate().loadAll(entityClass);
    }
    @Override
    public void saveOrUpdate(T entity){
        this.getHibernateTemplate().execute(session->{
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }
    @Override
    public void save(final T entity){
        this.getHibernateTemplate().execute(session->{
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }
    @Override
    public void update(T entity){
        this.getHibernateTemplate().execute(session->{
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }
    @Override
    public void delete(T entity){
        this.getHibernateTemplate().execute(session->{
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }
    @Override
    public List<T> query(String nameQuery, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<List<T>>) session -> {
            Query namedQuery = buildNameQuery(nameQuery,session, params);
            List list = namedQuery.list();
            return list;
        });
    }
    @Override
    public T queryForSingle(String nameQuery, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<T>) session -> {
            Query<T> namedQuery = buildNameQuery(nameQuery, session, params);
            return namedQuery.uniqueResult();
        });
    }
    @Override
    public <E> List<E> query(String nameQuery, Class<E> clazz, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<List<E>>) session -> {
            Query namedQuery = buildNameQuery(nameQuery,session, params);
            List list = namedQuery.list();
            return list;
        });
    }
    @Override
    public <E> E queryForSingle(String nameQuery, Class<E> clazz, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<E>) session -> {
            Query<E> namedQuery = buildNameQuery(nameQuery, session, params);
            return namedQuery.uniqueResult();
        });
    }

    private  Query buildNameQuery(String nameQuery, Session session, Object[] params) {
        Query namedQuery = session.getNamedQuery(nameQuery);
        int i = 0;
        for (Object param : params) {
            namedQuery.setParameter(i++, param);
        }
        return namedQuery;
    }

    @Override
    public void delete(ID id){
        this.getHibernateTemplate().execute(session->{
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            session.flush();
            transaction.commit();
            return null;
        });
    }


}
