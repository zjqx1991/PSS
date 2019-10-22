package com.revanwang.wms.service;


import com.revanwang.wms.domain.ProductStock;
import com.revanwang.wms.query.ProductStockQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IProductStockService {

    /**
     * 保存ProductStock对象
     * @param productStock
     */
    void save(ProductStock productStock);

    /**
     * 删除ProductStock对象
     * @param id    ProductStock的id
     */
    void delete(Long id);

    /**
     * 更新ProductStock对象
     * @param productStock
     */
    void update(ProductStock productStock);

    /**
     * 查询ProductStock对象
     * @param id    ProductStock的id
     * @return  id
     */
    ProductStock get(Long id);

    /**
     * @return 返回所有的ProductStock对象
     */
    List<ProductStock> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(ProductStockQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<ProductStock> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<ProductStock> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    ProductStock queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
