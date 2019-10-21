package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.OrderBill;
import com.revanwang.wms.domain.Supplier;
import com.revanwang.wms.query.OrderBillQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IOrderBillService;
import com.revanwang.wms.service.ISupplierService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderBillAction extends BaseAction {


    @Setter
    private IOrderBillService orderBillService;           
    @Setter
    private ISupplierService supplierService;

    @Getter
    private OrderBill orderBill = new OrderBill();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private OrderBillQueryObject qo = new OrderBillQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("订单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.orderBillService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("订单编辑")
    public String input() throws Exception {
        //供应商
        List<Supplier> suppliers = this.supplierService.getList();
        ActionContextPut("suppliers", suppliers);

        Long orderBillId = this.orderBill.getId();
        if (orderBillId != null) {
            this.orderBill = this.orderBillService.get(orderBillId);
            System.out.println("OrderBillAction.input: " + this.orderBill.getItems().size());
        }
        return INPUT;
    }


    @RequiredPermission("订单保存或更新")
    public String saveOrUpdate() {
        System.out.println("订单保存或更新" + this.orderBill);
        try {
            Long id = this.orderBill.getId();
            if (id == null) {
                //新增
                this.orderBillService.save(this.orderBill);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.orderBillService.update(this.orderBill);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("订单删除")
    public String delete() {
        try {
            Long orderBillId = this.orderBill.getId();
            if (orderBillId != null) {
                this.orderBillService.delete(orderBillId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("订单批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.orderBillService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }


    /**
     * 拦截 saveOrUpdate方法
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.orderBill.getId();
        if (empId != null) {
            //获取数据库数据
            this.orderBill = this.orderBillService.get(empId);
            //清除级联
            this.orderBill.getItems().clear();
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
