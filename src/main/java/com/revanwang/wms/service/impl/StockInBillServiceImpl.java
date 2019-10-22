package com.revanwang.wms.service.impl;

import com.revanwang.utils.RevanContext;
import com.revanwang.wms.dao.IProductStockDAO;
import com.revanwang.wms.dao.IStockInBillDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.domain.ProductStock;
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
    @Setter
    private IProductStockDAO productStockDAO;

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

    @Override
    public void audit(Long id) {
        //1、查询入库表单对象
        StockInBill stockInBill = this.stockInBillDAO.get(id);
        //2、判断是否已审核
        if (stockInBill.getStatus() == StockInBill.NORMAL) {
            //3、设置审核人、审核时间、状态
            stockInBill.setAuditor((Employee) RevanContext.revan_getCurrentSession());
            stockInBill.setAuditTime(new Date());
            stockInBill.setStatus(StockInBill.AUDIT);

            //4、入库
            for (StockInBillItem item : stockInBill.getItems()) {
                //5、判断仓库中是否存在（通过产品id和仓库id）
                ProductStock ps = this.productStockDAO.queryByProductAndDepot(item.getProduct().getId(), stockInBill.getDepot().getId());
                if (ps != null) {
                    //6、库存中存在
                    //6.1、设置个数
                    ps.setStoreNumber(ps.getStoreNumber().add(item.getNumber()));
                    //6.2、设置金额
                    ps.setAmount(ps.getAmount().add(item.getAmount()));
                    //6.3、设置价格（使用加权平均来求）
                    ps.setPrice(ps.getAmount().divide(ps.getStoreNumber()).setScale(2, RoundingMode.HALF_UP));
                    //6.4、入库时间
                    ps.setIncomeDate(new Date());

                    //更新库存
                    this.productStockDAO.update(ps);
                }
                else {
                    //6、库存中不存在
                    ps = new ProductStock();
                    //6.1、设置个数
                    ps.setStoreNumber(item.getNumber());
                    //6.2、设置金额
                    ps.setAmount(item.getAmount());
                    //6.3、设置价格（使用加权平均来求）
                    ps.setPrice(item.getCostPrice());
                    //6.4、入库时间
                    ps.setIncomeDate(new Date());
                    //6.5、商品
                    ps.setProduct(item.getProduct());
                    //6.6、仓库
                    ps.setDepot(stockInBill.getDepot());

                    //保存
                    this.productStockDAO.save(ps);
                }
                System.out.println("商品id:= " + item.getProduct().getId());
                System.out.println("仓库id:= " + stockInBill.getDepot().getId());
                System.out.println("入库表单明细:= " + ps);
            }

            //更新入库对象
            this.stockInBillDAO.update(stockInBill);
        }

    }

}
