package com.qworldr.mmorpg.provider;

import com.qworldr.mmorpg.anno.Generator;
import com.qworldr.mmorpg.entity.IEntity;
import com.qworldr.mmorpg.utils.ReflectUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public class HibernateEntityProvider<T extends IEntity, ID extends Serializable> extends HibernateDaoSupport implements EntityProvider<T, ID> {
    private Class<T> entityClass;
    private String keyGenerator;

    @Autowired
    public void setSessionFactoryValue(SessionFactory sessionFactory){
        this.setSessionFactory(sessionFactory);
    }
    public HibernateEntityProvider() {
        this.entityClass = (Class<T>) ReflectUtils.getSuperGenericType(getClass());
        Field[] declaredFields = entityClass.getDeclaredFields();
        Field declaredField = null;
        for (int i = 0; i < declaredFields.length; i++) {
            declaredField = declaredFields[i];
            if (declaredField.getAnnotation(Id.class) != null) {
                Generator annotation = declaredField.getAnnotation(Generator.class);
                if (annotation != null) {
                    keyGenerator = annotation.value();
                }
            }
        }
    }

    protected String getKeyGenerator() {
        return keyGenerator;
    }

    public T get(ID id) {
        return this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            T t = session.get(entityClass, id);
            transaction.commit();
            return t;
        });
    }

    public List<T> load() {
        return this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from " + entityClass.getSimpleName());
            List<T> list = query.list();
            transaction.commit();
            return list;
        });
    }

    @Override
    public T loadAndCreate(ID id,ICreator<T,ID> creator) {
        T t = get(id);
        if(t!=null){
            return t;
        }
        t = creator.create(id);
        save(t);
        return t;
    }

    @Override
    public void saveOrUpdate(T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void save(final T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void update(T entity) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            session.flush();
            transaction.commit();
            return entity;
        });
    }

    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().execute(session -> {
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
            Transaction transaction = session.beginTransaction();
            Query namedQuery = buildNameQuery(nameQuery, session, params);
            List list = namedQuery.list();
            transaction.commit();
            return list;
        });
    }

    @Override
    public T queryForSingle(String nameQuery, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<T>) session -> {
            Transaction transaction = session.beginTransaction();
            Query<T> namedQuery = buildNameQuery(nameQuery, session, params);
            T t = namedQuery.uniqueResult();
            transaction.commit();
            return t;
        });
    }

    @Override
    public <E> List<E> query(String nameQuery, Class<E> clazz, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<List<E>>) session -> {
            Transaction transaction = session.beginTransaction();
            Query namedQuery = buildNameQuery(nameQuery, session, params);
            List list = namedQuery.list();
            transaction.commit();
            return list;
        });
    }

    @Override
    public <E> E queryForSingle(String nameQuery, Class<E> clazz, Object... params) {
        return getHibernateTemplate().execute((HibernateCallback<E>) session -> {
            Transaction transaction = session.beginTransaction();
            Query<E> namedQuery = buildNameQuery(nameQuery, session, params);
            E e = namedQuery.uniqueResult();
            transaction.commit();
            return e;
        });
    }

    private Query buildNameQuery(String nameQuery, Session session, Object[] params) {
        Query namedQuery = session.getNamedQuery(nameQuery);
        int i = 0;
        for (Object param : params) {
            namedQuery.setParameter(i++, param);
        }
        return namedQuery;
    }

    @Override
    public void delete(ID id) {
        this.getHibernateTemplate().execute(session -> {
            Transaction transaction = session.beginTransaction();
            session.delete(id);
            session.flush();
            transaction.commit();
            return null;
        });
    }


}
