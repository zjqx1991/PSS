package com.revanwang.wms.service.impl;

import com.revanwang.utils.RevanContext;
import com.revanwang.wms.dao.IProductStockDAO;
import com.revanwang.wms.dao.IStockOutBillDAO;
import com.revanwang.wms.domain.*;
import com.revanwang.wms.query.StockOutBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IStockOutBillService;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class StockOutBillServiceImpl implements IStockOutBillService {


    @Setter
    private IStockOutBillDAO stockOutBillDAO;
    @Setter
    private IProductStockDAO productStockDAO;

    @Override
    public void save(StockOutBill stockOutBill) {
        //1、制单人和制单时间
        stockOutBill.setInputTime(new Date());
        stockOutBill.setInputUser((Employee) RevanContext.revan_getCurrentSession());

        setupStockOutBill(stockOutBill);

        this.stockOutBillDAO.save(stockOutBill);
    }

    private void setupStockOutBill(StockOutBill stockOutBill) {
        //2、设置为审核状态
        stockOutBill.setStatus(StockOutBill.NORMAL);
        //3、情况入库数量和入库金额
        stockOutBill.setTotalNumber(BigDecimal.ZERO);
        stockOutBill.setTotalAmount(BigDecimal.ZERO);

        for (StockOutBillItem item : stockOutBill.getItems()) {
            //4、设置 StockOutBillItem 与 StockOutBill的联系
            item.setStockOutBill(stockOutBill);
            //5、计算明细小计
            item.setAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
            //设置总数量和总金额
            stockOutBill.setTotalNumber(stockOutBill.getTotalNumber().add(item.getNumber()));
            stockOutBill.setTotalAmount(stockOutBill.getTotalAmount().add(item.getSalePrice().multiply(item.getNumber())));
        }
    }

    @Override
    public void delete(Long id) {
        this.stockOutBillDAO.delete(get(id));
    }

    @Override
    public void update(StockOutBill stockOutBill) {
        if (stockOutBill.getStatus() == StockOutBill.NORMAL) {
            setupStockOutBill(stockOutBill);
            this.stockOutBillDAO.update(stockOutBill);
        }
    }

    @Override
    public StockOutBill get(Long id) {
        return this.stockOutBillDAO.get(id);
    }

    @Override
    public List<StockOutBill> getList() {
        return this.stockOutBillDAO.getList();
    }

    @Override
    public QueryResultObject query(StockOutBillQueryObject qo) {
        return this.stockOutBillDAO.query(qo);
    }

    @Override
    public List<StockOutBill> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.stockOutBillDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<StockOutBill> query(String condition, Object... args) {
        return this.stockOutBillDAO.query(condition, args);
    }

    @Override
    public StockOutBill queryObject(String condition, Object... args) {
        return this.stockOutBillDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.stockOutBillDAO.deleteBatch(ids);
    }

    @Override
    public void audit(Long id) {
        //1、获取销售订单
        StockOutBill outBill = this.stockOutBillDAO.get(id);
        //2、判断是否可以审核
        if (outBill.getStatus() == StockOutBill.NORMAL) {
            //3、设置审核人、审核时间、状态
            outBill.setAuditor((Employee) RevanContext.revan_getCurrentSession());
            outBill.setAuditTime(new Date());
            outBill.setStatus(StockOutBill.AUDIT);

            //4、遍历销售清单
            for (StockOutBillItem item : outBill.getItems()) {
                //5、查询库存对象
                ProductStock ps = this.productStockDAO.queryByProductAndDepot(item.getProduct().getId(), outBill.getDepot().getId());
                //6、判断库存中是否有该商品
                if (ps == null || item.getNumber().compareTo(ps.getStoreNumber()) > 0) {
                    throw new RuntimeException("商品 [" + item.getProduct().getName() +"] 在 "+ outBill.getDepot().getName() + " 库存不足!!!");
                }

                //7、剩余库存
                ps.setStoreNumber(ps.getStoreNumber().subtract(item.getNumber()).setScale(2, RoundingMode.HALF_UP));
                //8、商品总价值
                ps.setAmount(ps.getStoreNumber().multiply(ps.getPrice()).setScale(2, RoundingMode.HALF_UP));
                //9、出库时间
                ps.setOutcomeDate(new Date());

                //10、更新库存
                this.productStockDAO.update(ps);
            }

            //更新审核
            this.stockOutBillDAO.update(outBill);
        }
    }

}
