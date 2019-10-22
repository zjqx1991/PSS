package com.revanwang.wms.service;


import com.revanwang.wms.domain.StockOutBill;
import com.revanwang.wms.query.StockOutBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IStockOutBillService {

    /**
     * 保存StockOutBill对象
     * @param stockOutBill
     */
    void save(StockOutBill stockOutBill);

    /**
     * 删除StockOutBill对象
     * @param id    StockOutBill的id
     */
    void delete(Long id);

    /**
     * 更新StockOutBill对象
     * @param stockOutBill
     */
    void update(StockOutBill stockOutBill);

    /**
     * 查询StockOutBill对象
     * @param id    StockOutBill的id
     * @return  id
     */
    StockOutBill get(Long id);

    /**
     * @return 返回所有的StockOutBill对象
     */
    List<StockOutBill> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(StockOutBillQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<StockOutBill> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<StockOutBill> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    StockOutBill queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
