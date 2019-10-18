package com.revanwang.wms.service;


import com.revanwang.wms.domain.Brand;
import com.revanwang.wms.query.BrandQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IBrandService {

    /**
     * 保存Brand对象
     * @param brand
     */
    void save(Brand brand);

    /**
     * 删除Brand对象
     * @param id    Brand的id
     */
    void delete(Long id);

    /**
     * 更新Brand对象
     * @param brand
     */
    void update(Brand brand);

    /**
     * 查询Brand对象
     * @param id    Brand的id
     * @return  id
     */
    Brand get(Long id);

    /**
     * @return 返回所有的Brand对象
     */
    List<Brand> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(BrandQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Brand> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Brand> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Brand queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
