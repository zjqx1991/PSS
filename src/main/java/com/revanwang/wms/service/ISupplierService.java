package com.revanwang.wms.service;


import com.revanwang.wms.domain.Supplier;
import com.revanwang.wms.query.SupplierQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface ISupplierService {

    /**
     * 保存Supplier对象
     * @param supplier
     */
    void save(Supplier supplier);

    /**
     * 删除Supplier对象
     * @param id    Supplier的id
     */
    void delete(Long id);

    /**
     * 更新Supplier对象
     * @param supplier
     */
    void update(Supplier supplier);

    /**
     * 查询Supplier对象
     * @param id    Supplier的id
     * @return  id
     */
    Supplier get(Long id);

    /**
     * @return 返回所有的Supplier对象
     */
    List<Supplier> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(SupplierQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Supplier> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Supplier> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Supplier queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
