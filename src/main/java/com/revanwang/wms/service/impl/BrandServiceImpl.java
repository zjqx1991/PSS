package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IBrandDAO;
import com.revanwang.wms.domain.Brand;
import com.revanwang.wms.query.BrandQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IBrandService;
import lombok.Setter;

import java.util.List;

public class BrandServiceImpl implements IBrandService {


    @Setter
    private IBrandDAO brandDAO;

    @Override
    public void save(Brand brand) {
        this.brandDAO.save(brand);
    }

    @Override
    public void delete(Long id) {
        this.brandDAO.delete(get(id));
    }

    @Override
    public void update(Brand brand) {
        this.brandDAO.update(brand);
    }

    @Override
    public Brand get(Long id) {
        return this.brandDAO.get(id);
    }

    @Override
    public List<Brand> getList() {
        return this.brandDAO.getList();
    }

    @Override
    public QueryResultObject query(BrandQueryObject qo) {
        return this.brandDAO.query(qo);
    }

    @Override
    public List<Brand> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.brandDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Brand> query(String condition, Object... args) {
        return this.brandDAO.query(condition, args);
    }

    @Override
    public Brand queryObject(String condition, Object... args) {
        return this.brandDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.brandDAO.deleteBatch(ids);
    }

}
