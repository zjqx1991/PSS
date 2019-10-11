package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IDepartmentDAO;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.query.DepartmentQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepartmentService;
import lombok.Setter;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {

    @Setter
    private IDepartmentDAO  departmentDAO;

    @Override
    public void save(Department department) {
        this.departmentDAO.save(department);
    }

    @Override
    public void delete(Long id) {
        this.departmentDAO.delete(get(id));
    }

    @Override
    public void update(Department department) {
        this.departmentDAO.update(department);
    }

    @Override
    public Department get(Long id) {
        return this.departmentDAO.get(id);
    }

    @Override
    public List<Department> getList() {
        return this.departmentDAO.getList();
    }

    @Override
    public QueryResultObject query(DepartmentQueryObject qo) {
        return this.departmentDAO.query(qo);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.departmentDAO.deleteBatch(ids);
    }
}
