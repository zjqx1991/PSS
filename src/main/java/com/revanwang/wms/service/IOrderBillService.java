package com.revanwang.wms.service;


import com.revanwang.wms.domain.OrderBill;
import com.revanwang.wms.query.OrderBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IOrderBillService {

    /**
     * 保存OrderBill对象
     * @param orderBill
     */
    void save(OrderBill orderBill);

    /**
     * 删除OrderBill对象
     * @param id    OrderBill的id
     */
    void delete(Long id);

    /**
     * 更新OrderBill对象
     * @param orderBill
     */
    void update(OrderBill orderBill);

    /**
     * 查询OrderBill对象
     * @param id    OrderBill的id
     * @return  id
     */
    OrderBill get(Long id);

    /**
     * @return 返回所有的OrderBill对象
     */
    List<OrderBill> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(OrderBillQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<OrderBill> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<OrderBill> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    OrderBill queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 审核订单
     * @param id 订单id
     */
    void audit(Long id);
}
