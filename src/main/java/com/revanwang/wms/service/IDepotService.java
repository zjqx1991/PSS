package com.revanwang.wms.service;


import com.revanwang.wms.domain.Depot;
import com.revanwang.wms.query.DepotQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IDepotService {

    /**
     * 保存Depot对象
     * @param depot
     */
    void save(Depot depot);

    /**
     * 删除Depot对象
     * @param id    Depot的id
     */
    void delete(Long id);

    /**
     * 更新Depot对象
     * @param depot
     */
    void update(Depot depot);

    /**
     * 查询Depot对象
     * @param id    Depot的id
     * @return  id
     */
    Depot get(Long id);

    /**
     * @return 返回所有的Depot对象
     */
    List<Depot> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(DepotQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Depot> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Depot> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Depot queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
