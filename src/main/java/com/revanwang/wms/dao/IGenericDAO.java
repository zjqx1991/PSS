package com.revanwang.wms.dao;

import com.revanwang.wms.query.AbstractQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IGenericDAO<T> {

    /**
     * 保存对象
     * @param obj   保存对象
     */
    void save(T obj);

    /**
     * 删除员工
     * @param obj   删除对象
     */
    void delete(T obj);

    /**
     * 更新对象信息
     * @param obj    对象
     */
    void update(T obj);

    /**
     * 查询对象
     * @param id    对象id
     * @return  id对象
     */
    T get(Long id);

    /**
     * @return 返回所有的数据
     */
    List<T> getList();

    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(AbstractQueryObject qo);

    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<T> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<T> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    T queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
