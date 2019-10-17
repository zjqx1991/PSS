package com.revanwang.wms.dao.impl;


import com.revanwang.wms.dao.ISystemMenuDAO;
import com.revanwang.wms.domain.Role;
import com.revanwang.wms.domain.SystemMenu;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SystemMenuDAOImpl
        extends GenericDAOImpl<SystemMenu>
        implements ISystemMenuDAO {
    @Override
    public List<SystemMenu> queryChildrenSystemMenu() {
        String condition = "SELECT obj FROM SystemMenu obj WHERE obj.parent IS NOT NULL";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(condition);
        return query.list();
    }

    @Override
    public List<SystemMenu> querySystemMenuByParentSn(String parentSn) {
        String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent.sn = :sn";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("sn", parentSn);
        return query.list();
    }

    @Override
    public List<SystemMenu> querySystemMenuByParentSnAndRoles(String parentSn, List<Role> roles) {
        String hql = "SELECT m FROM Role r JOIN r.systemMenuList m WHERE m.parent.sn = :sn AND r IN :role";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("sn", parentSn);
        query.setParameterList("role", roles);
        return query.list();
    }
}
