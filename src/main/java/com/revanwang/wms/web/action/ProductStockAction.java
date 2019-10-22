package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.ProductStock;
import com.revanwang.wms.query.ProductStockQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IProductStockService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ProductStockAction extends BaseAction {


    @Setter
    private IProductStockService productStockService;           


    @Getter
    private ProductStock productStock = new ProductStock();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private ProductStockQueryObject qo = new ProductStockQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("库存列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.productStockService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("库存编辑")
    public String input() throws Exception {

        Long productStockId = this.productStock.getId();
        if (productStockId != null) {
            this.productStock = this.productStockService.get(productStockId);
            System.out.println("ProductStockAction.input: " + this.productStock);
        }
        return INPUT;
    }


    @RequiredPermission("库存保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.productStock.getId();
            if (id == null) {
                //新增
                this.productStockService.save(this.productStock);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.productStockService.update(this.productStock);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("库存删除")
    public String delete() {
        try {
            Long productStockId = this.productStock.getId();
            if (productStockId != null) {
                this.productStockService.delete(productStockId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("库存批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.productStockService.deleteBatch(this.ids);
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
        Long empId = this.productStock.getId();
        if (empId != null) {
            //获取数据库数据
            this.productStock = this.productStockService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
