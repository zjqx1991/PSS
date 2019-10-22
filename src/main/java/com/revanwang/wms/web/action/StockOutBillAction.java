package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.StockOutBill;
import com.revanwang.wms.query.StockOutBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IClientService;
import com.revanwang.wms.service.IDepotService;
import com.revanwang.wms.service.IStockOutBillService;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.List;

public class StockOutBillAction extends BaseAction {


    @Setter
    private IStockOutBillService stockOutBillService;
    @Setter
    private IDepotService depotService;
    @Setter
    private IClientService clientService;

    @Getter
    private StockOutBill stockOutBill = new StockOutBill();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private StockOutBillQueryObject qo = new StockOutBillQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("销售订单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.stockOutBillService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
            ActionContextPut("depots", this.depotService.getList());
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("销售订单编辑")
    public String input() throws Exception {
        ActionContextPut("clients", this.clientService.getList());
        ActionContextPut("depots", this.depotService.getList());
        Long stockOutBillId = this.stockOutBill.getId();
        if (stockOutBillId != null) {
            this.stockOutBill = this.stockOutBillService.get(stockOutBillId);
            System.out.println("StockOutBillAction.input: " + this.stockOutBill);
        }
        return INPUT;
    }


    @RequiredPermission("销售订单保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.stockOutBill.getId();
            if (id == null) {
                //新增
                this.stockOutBillService.save(this.stockOutBill);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.stockOutBillService.update(this.stockOutBill);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("销售订单删除")
    public String delete() {
        try {
            Long stockOutBillId = this.stockOutBill.getId();
            if (stockOutBillId != null) {
                this.stockOutBillService.delete(stockOutBillId);
                addActionMessage("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("销售订单批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.stockOutBillService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("销售订单批量删除")
    public String audit() {
        try {

            if (this.stockOutBill.getId() != null) {
                this.stockOutBillService.audit(this.stockOutBill.getId());
                addActionMessage("审核通过");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionMessage(e.getMessage());
        }
        return SUCCESS;
    }


    /**
     * 拦截 saveOrUpdate方法
     *
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.stockOutBill.getId();
        if (empId != null) {
            //获取数据库数据
            this.stockOutBill = this.stockOutBillService.get(empId);
            this.stockOutBill.getItems().clear();
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
