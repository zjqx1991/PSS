package com.revanwang.wms.service;


import com.revanwang.wms.domain.Product;
import com.revanwang.wms.query.ProductQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IProductService {

    /**
     * 保存Product对象
     * @param product
     */
    void save(Product product);

    /**
     * 删除Product对象
     * @param id    Product的id
     */
    void delete(Long id);

    /**
     * 更新Product对象
     * @param product
     */
    void update(Product product);

    /**
     * 查询Product对象
     * @param id    Product的id
     * @return  id
     */
    Product get(Long id);

    /**
     * @return 返回所有的Product对象
     */
    List<Product> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(ProductQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Product> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Product> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Product queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
