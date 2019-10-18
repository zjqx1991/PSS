package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.ISupplierDAO;
import com.revanwang.wms.domain.Supplier;
import com.revanwang.wms.query.SupplierQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.ISupplierService;
import lombok.Setter;

import java.util.List;

public class SupplierServiceImpl implements ISupplierService {


    @Setter
    private ISupplierDAO supplierDAO;

    @Override
    public void save(Supplier supplier) {
        this.supplierDAO.save(supplier);
    }

    @Override
    public void delete(Long id) {
        this.supplierDAO.delete(get(id));
    }

    @Override
    public void update(Supplier supplier) {
        this.supplierDAO.update(supplier);
    }

    @Override
    public Supplier get(Long id) {
        return this.supplierDAO.get(id);
    }

    @Override
    public List<Supplier> getList() {
        return this.supplierDAO.getList();
    }

    @Override
    public QueryResultObject query(SupplierQueryObject qo) {
        return this.supplierDAO.query(qo);
    }

    @Override
    public List<Supplier> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.supplierDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Supplier> query(String condition, Object... args) {
        return this.supplierDAO.query(condition, args);
    }

    @Override
    public Supplier queryObject(String condition, Object... args) {
        return this.supplierDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.supplierDAO.deleteBatch(ids);
    }

}
