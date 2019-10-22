package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IProductStockDAO;
import com.revanwang.wms.domain.ProductStock;
import com.revanwang.wms.query.ProductStockQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IProductStockService;
import lombok.Setter;

import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {


    @Setter
    private IProductStockDAO productStockDAO;

    @Override
    public void save(ProductStock productStock) {
        this.productStockDAO.save(productStock);
    }

    @Override
    public void delete(Long id) {
        this.productStockDAO.delete(get(id));
    }

    @Override
    public void update(ProductStock productStock) {
        this.productStockDAO.update(productStock);
    }

    @Override
    public ProductStock get(Long id) {
        return this.productStockDAO.get(id);
    }

    @Override
    public List<ProductStock> getList() {
        return this.productStockDAO.getList();
    }

    @Override
    public QueryResultObject query(ProductStockQueryObject qo) {
        return this.productStockDAO.query(qo);
    }

    @Override
    public List<ProductStock> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.productStockDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<ProductStock> query(String condition, Object... args) {
        return this.productStockDAO.query(condition, args);
    }

    @Override
    public ProductStock queryObject(String condition, Object... args) {
        return this.productStockDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.productStockDAO.deleteBatch(ids);
    }

}
