package com.revanwang.wms.dao.impl;

import com.revanwang.utils.RevanMapUtils;
import com.revanwang.wms.dao.IGenericDAO;
import com.revanwang.wms.query.AbstractQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GenericDAOImpl<T> implements IGenericDAO<T> {

    protected SessionFactory sessionFactory;
    private Class<T> targetClass;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 创建子类对象时，首先要创建父类对象
     */
    public GenericDAOImpl() {
        //获取this的 带有泛型信息的父类
        ParameterizedType clazz = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取泛型父类中泛型参数
        Type[] types = clazz.getActualTypeArguments();
        this.targetClass = (Class<T>) types[0];
    }

    @Override
    public void save(T obj) {
        this.sessionFactory.getCurrentSession().persist(obj);
    }

    @Override
    public void delete(T obj) {
        this.sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public void update(T obj) {
        this.sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public T get(Long id) {
        return this.sessionFactory.getCurrentSession().load(this.targetClass, id);
    }

    @Override
    public List<T> getList() {
        return this.sessionFactory.getCurrentSession().createCriteria(this.targetClass).list();
    }

    @Override
    public QueryResultObject query(AbstractQueryObject qo) {

        //每页个数
        Integer pageSize = qo.getPageSize();
        //当前页
        Integer currentPage = qo.getCurrentPage();

        //拼接查询参数
        List paramList = qo.getQueryParams();

        //1、COUNT
        Session session = sessionFactory.getCurrentSession();

        //拼接查询条件
        StringBuilder countSB = new StringBuilder(100);
        countSB.append("SELECT COUNT(obj) FROM ");
        countSB.append(this.targetClass.getSimpleName());
        countSB.append(" obj");
        countSB.append(qo.getQueryCondition());
        //查询总个数
        Query query = session.createQuery(countSB.toString());
        //设置参数
        setConditionParame(paramList, query);
        //查询总个数
        Integer totalCount = ((Long) query.uniqueResult()).intValue();

        //查询数据
        StringBuilder dataSB = new StringBuilder(100);
        dataSB.append("SELECT obj FROM ");
        dataSB.append(this.targetClass.getSimpleName());
        dataSB.append(" obj");
        dataSB.append(qo.getQueryCondition());
        query = session.createQuery(dataSB.toString());
        //设置参数
        setConditionParame(paramList, query);
        //设置返回结果
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);

        return new QueryResultObject(currentPage, pageSize, totalCount, query.list());
    }

    @Override
    public List<T> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        //查询数据
        StringBuilder dataSB = new StringBuilder(100);
        dataSB.append("SELECT obj FROM ");
        dataSB.append(this.targetClass.getSimpleName());
        dataSB.append(" obj");

        if (args != null && args.length != 0) {
            dataSB.append(" WHERE ");
            dataSB.append(condition);
        }

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(dataSB.toString());
        //设置参数
        if (condition.length() > 0 && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                Map<String, Object> map = (Map<String, Object>) args[i];
                query.setParameter(RevanMapUtils.revan_getMapKey(map).toString(), RevanMapUtils.revan_getMapValue(map));
            }
        }

        //设置返回结果
        if (currentPage > 0 && pageSize > 0) {
            query.setFirstResult((currentPage - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.list();
    }

    @Override
    public List<T> query(String condition, Object... args) {
        return query(-1, -1, condition, args);
    }

    @Override
    public T queryObject(String condition, Object... args) {
        List<T> list = query(condition, args);
        return list.size() == 1 ? list.get(0) : null;
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        //查询数据
        StringBuilder dataSB = new StringBuilder(100);
        dataSB.append("DELETE FROM ");
        dataSB.append(this.targetClass.getSimpleName());
        dataSB.append(" obj");
        dataSB.append(" WHERE obj.id IN :ids");
        //创建
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(dataSB.toString());
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }


    /**
     * 给请求参数设置
     */
    private void setConditionParame(List paramList, Query query) {
        for (Object obj : paramList) {
            Map<String, Object> map = (Map<String, Object>) obj;
            query.setParameter(RevanMapUtils.revan_getMapKey(map).toString(),
                    RevanMapUtils.revan_getMapValue(map));
        }
    }
}
