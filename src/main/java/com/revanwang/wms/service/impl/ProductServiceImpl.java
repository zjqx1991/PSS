package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IProductDAO;
import com.revanwang.wms.domain.Product;
import com.revanwang.wms.query.ProductQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IProductService;
import lombok.Setter;

import java.util.List;

public class ProductServiceImpl implements IProductService {


    @Setter
    private IProductDAO productDAO;

    @Override
    public void save(Product product) {
        this.productDAO.save(product);
    }

    @Override
    public void delete(Long id) {
        this.productDAO.delete(get(id));
    }

    @Override
    public void update(Product product) {
        this.productDAO.update(product);
    }

    @Override
    public Product get(Long id) {
        return this.productDAO.get(id);
    }

    @Override
    public List<Product> getList() {
        return this.productDAO.getList();
    }

    @Override
    public QueryResultObject query(ProductQueryObject qo) {
        return this.productDAO.query(qo);
    }

    @Override
    public List<Product> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.productDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Product> query(String condition, Object... args) {
        return this.productDAO.query(condition, args);
    }

    @Override
    public Product queryObject(String condition, Object... args) {
        return this.productDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.productDAO.deleteBatch(ids);
    }

}
