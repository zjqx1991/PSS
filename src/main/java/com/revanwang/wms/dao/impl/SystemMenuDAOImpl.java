package com.revanwang.wms.dao.impl;


import com.revanwang.wms.dao.ISystemMenuDAO;
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
}
