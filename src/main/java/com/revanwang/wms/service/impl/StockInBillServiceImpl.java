package com.revanwang.wms.service.impl;

import com.revanwang.utils.RevanContext;
import com.revanwang.wms.dao.IStockInBillDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.domain.StockInBill;
import com.revanwang.wms.domain.StockInBillItem;
import com.revanwang.wms.query.StockInBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IStockInBillService;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockInBillServiceImpl implements IStockInBillService {


    @Setter
    private IStockInBillDAO stockInBillDAO;

    @Override
    public void save(StockInBill stockInBill) {
        //1、制单人和制单时间
        stockInBill.setInputTime(new Date());
        stockInBill.setInputUser((Employee) RevanContext.revan_getCurrentSession());

        setupStockInBill(stockInBill);

        this.stockInBillDAO.save(stockInBill);
    }

    private void setupStockInBill(StockInBill stockInBill) {
        //2、设置为审核状态
        stockInBill.setStatus(StockInBill.NORMAL);
        //3、情况入库数量和入库金额
        stockInBill.setTotalNumber(BigDecimal.ZERO);
        stockInBill.setTotalAmount(BigDecimal.ZERO);

        for (StockInBillItem item : stockInBill.getItems()) {
            //4、设置 StockInBillItem 与 StockInBill的联系
            item.setStockInBill(stockInBill);
            //5、计算明细小计
            item.setAmount(item.getNumber().multiply(item.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
            //设置总数量和总金额
            stockInBill.setTotalNumber(stockInBill.getTotalNumber().add(item.getNumber()));
            stockInBill.setTotalAmount(stockInBill.getTotalAmount().add(item.getCostPrice().multiply(item.getNumber())));
        }
    }

    @Override
    public void delete(Long id) {
        this.stockInBillDAO.delete(get(id));
    }

    @Override
    public void update(StockInBill stockInBill) {
        if (stockInBill.getStatus() == StockInBill.NORMAL) {
            setupStockInBill(stockInBill);
            this.stockInBillDAO.update(stockInBill);
        }
    }

    @Override
    public StockInBill get(Long id) {
        return this.stockInBillDAO.get(id);
    }

    @Override
    public List<StockInBill> getList() {
        return this.stockInBillDAO.getList();
    }

    @Override
    public QueryResultObject query(StockInBillQueryObject qo) {
        return this.stockInBillDAO.query(qo);
    }

    @Override
    public List<StockInBill> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.stockInBillDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<StockInBill> query(String condition, Object... args) {
        return this.stockInBillDAO.query(condition, args);
    }

    @Override
    public StockInBill queryObject(String condition, Object... args) {
        return this.stockInBillDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.stockInBillDAO.deleteBatch(ids);
    }

}
