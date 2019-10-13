package com.revanwang.wms.service;


import com.revanwang.wms.domain.Persion;
import com.revanwang.wms.query.PersionQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IPersionService {

    /**
     * 保存Persion对象
     * @param persion
     */
    void save(Persion persion);

    /**
     * 删除Persion对象
     * @param id    Persion的id
     */
    void delete(Long id);

    /**
     * 更新Persion对象
     * @param persion
     */
    void update(Persion persion);

    /**
     * 查询Persion对象
     * @param id    Persion的id
     * @return  id
     */
    Persion get(Long id);

    /**
     * @return 返回所有的Persion对象
     */
    List<Persion> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(PersionQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Persion> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Persion> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Persion queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
