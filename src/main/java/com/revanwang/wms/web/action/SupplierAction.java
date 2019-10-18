package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Supplier;
import com.revanwang.wms.query.SupplierQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.ISupplierService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class SupplierAction extends BaseAction {


    @Setter
    private ISupplierService supplierService;           


    @Getter
    private Supplier supplier = new Supplier();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private SupplierQueryObject qo = new SupplierQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("供应商列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.supplierService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("供应商编辑")
    public String input() throws Exception {

        Long supplierId = this.supplier.getId();
        if (supplierId != null) {
            this.supplier = this.supplierService.get(supplierId);
            System.out.println("SupplierAction.input: " + this.supplier);
        }
        return INPUT;
    }


    @RequiredPermission("供应商保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.supplier.getId();
            if (id == null) {
                //新增
                this.supplierService.save(this.supplier);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.supplierService.update(this.supplier);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("供应商删除")
    public String delete() {
        try {
            Long supplierId = this.supplier.getId();
            if (supplierId != null) {
                this.supplierService.delete(supplierId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("供应商批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.supplierService.deleteBatch(this.ids);
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
        Long empId = this.supplier.getId();
        if (empId != null) {
            //获取数据库数据
            this.supplier = this.supplierService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
