package com.revanwang.wms.service;


import com.revanwang.wms.domain.StockInBill;
import com.revanwang.wms.query.StockInBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IStockInBillService {

    /**
     * 保存StockInBill对象
     * @param stockInBill
     */
    void save(StockInBill stockInBill);

    /**
     * 删除StockInBill对象
     * @param id    StockInBill的id
     */
    void delete(Long id);

    /**
     * 更新StockInBill对象
     * @param stockInBill
     */
    void update(StockInBill stockInBill);

    /**
     * 查询StockInBill对象
     * @param id    StockInBill的id
     * @return  id
     */
    StockInBill get(Long id);

    /**
     * @return 返回所有的StockInBill对象
     */
    List<StockInBill> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(StockInBillQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<StockInBill> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<StockInBill> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    StockInBill queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 审核
     * 审核通过把入库表单 入库到库存表单中
     */
    void audit(Long id);
}
