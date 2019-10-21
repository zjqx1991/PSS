package com.revanwang.wms.service.impl;

import com.revanwang.utils.RevanContext;
import com.revanwang.wms.dao.IOrderBillDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.domain.OrderBill;
import com.revanwang.wms.domain.OrderBillItem;
import com.revanwang.wms.query.OrderBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IOrderBillService;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class OrderBillServiceImpl implements IOrderBillService {


    @Setter
    private IOrderBillDAO orderBillDAO;

    @Override
    public void save(OrderBill orderBill) {
        //1、设置制单人和制单时间
        orderBill.setInputUser((Employee) RevanContext.revan_getCurrentSession());
        orderBill.setInputTime(new Date());
        setupOrderBillItem(orderBill);
        this.orderBillDAO.save(orderBill);
    }

    private void setupOrderBillItem(OrderBill orderBill) {
        //2、手动设置审核状态
        orderBill.setStatus(OrderBill.NORMAL);
        //3、采购数量和采购金额
        orderBill.setTotalNumber(BigDecimal.ZERO);
        orderBill.setTotalAmount(BigDecimal.ZERO);
        for (OrderBillItem item : orderBill.getItems()) {
            //4、处理订单与明细关系
            item.setOrderBill(orderBill);
            //5、计算明细中小计
            item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
            //6、采购数量和采购金额
            orderBill.setTotalNumber(orderBill.getTotalNumber().add(item.getNumber()));
            orderBill.setTotalAmount(orderBill.getTotalAmount().add(item.getAmount()));
        }
    }

    @Override
    public void delete(Long id) {
        this.orderBillDAO.delete(get(id));
    }

    @Override
    public void update(OrderBill orderBill) {
        if (orderBill.getStatus() == OrderBill.NORMAL) {
            setupOrderBillItem(orderBill);
            this.orderBillDAO.update(orderBill);
        }
    }

    @Override
    public OrderBill get(Long id) {
        return this.orderBillDAO.get(id);
    }

    @Override
    public List<OrderBill> getList() {
        return this.orderBillDAO.getList();
    }

    @Override
    public QueryResultObject query(OrderBillQueryObject qo) {
        return this.orderBillDAO.query(qo);
    }

    @Override
    public List<OrderBill> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.orderBillDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<OrderBill> query(String condition, Object... args) {
        return this.orderBillDAO.query(condition, args);
    }

    @Override
    public OrderBill queryObject(String condition, Object... args) {
        return this.orderBillDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.orderBillDAO.deleteBatch(ids);
    }

}
