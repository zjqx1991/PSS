package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.StockInBill;
import com.revanwang.wms.query.StockInBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepotService;
import com.revanwang.wms.service.IStockInBillService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StockInBillAction extends BaseAction {


    @Setter
    private IStockInBillService stockInBillService;           
    @Setter
    private IDepotService depotService;

    @Getter
    private StockInBill stockInBill = new StockInBill();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private StockInBillQueryObject qo = new StockInBillQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("入库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            ActionContextPut("depots", depotService.getList());
            QueryResultObject resultObject = this.stockInBillService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("入库编辑")
    public String input() throws Exception {
        ActionContextPut("depots", depotService.getList());
        Long stockInBillId = this.stockInBill.getId();
        if (stockInBillId != null) {
            this.stockInBill = this.stockInBillService.get(stockInBillId);
            System.out.println("StockInBillAction.input: " + this.stockInBill);
        }
        return INPUT;
    }


    @RequiredPermission("入库保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.stockInBill.getId();
            if (id == null) {
                //新增
                this.stockInBillService.save(this.stockInBill);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.stockInBillService.update(this.stockInBill);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("入库删除")
    public String delete() {
        try {
            Long stockInBillId = this.stockInBill.getId();
            if (stockInBillId != null) {
                this.stockInBillService.delete(stockInBillId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("入库批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.stockInBillService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }


    @RequiredPermission("入库审核")
    public String audit() {
        this.stockInBillService.audit(this.stockInBill.getId());
        return SUCCESS;
    }

    /**
     * 拦截 saveOrUpdate方法
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.stockInBill.getId();
        if (empId != null) {
            //获取数据库数据
            this.stockInBill = this.stockInBillService.get(empId);
            this.stockInBill.getItems().clear();
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
