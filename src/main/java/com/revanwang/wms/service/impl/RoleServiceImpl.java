package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IRoleDAO;
import com.revanwang.wms.domain.Role;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IRoleService;
import lombok.Setter;

import java.util.List;

public class RoleServiceImpl implements IRoleService {

    @Setter
    private IRoleDAO roleDAO;

    @Override
    public void save(Role role) {
        this.roleDAO.save(role);
    }

    @Override
    public void delete(Long id) {
        this.roleDAO.delete(get(id));
    }

    @Override
    public void update(Role role) {
        this.roleDAO.update(role);
    }

    @Override
    public Role get(Long id) {
        return this.roleDAO.get(id);
    }

    @Override
    public List<Role> getList() {
        return this.roleDAO.getList();
    }

    @Override
    public QueryResultObject query(CommonQueryObject qo) {
        return this.roleDAO.query(qo);
    }

    @Override
    public List<Role> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.roleDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Role> query(String condition, Object... args) {
        return this.roleDAO.query(condition, args);
    }

    @Override
    public Role queryObject(String condition, Object... args) {
        return this.roleDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.roleDAO.deleteBatch(ids);
    }
}
