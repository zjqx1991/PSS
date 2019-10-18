package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Brand;
import com.revanwang.wms.query.BrandQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IBrandService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BrandAction extends BaseAction {


    @Setter
    private IBrandService brandService;           


    @Getter
    private Brand brand = new Brand();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private BrandQueryObject qo = new BrandQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("Brand列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.brandService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("Brand编辑")
    public String input() throws Exception {

        Long brandId = this.brand.getId();
        if (brandId != null) {
            this.brand = this.brandService.get(brandId);
            System.out.println("BrandAction.input: " + this.brand);
        }
        return INPUT;
    }


    @RequiredPermission("Brand保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.brand.getId();
            if (id == null) {
                //新增
                this.brandService.save(this.brand);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.brandService.update(this.brand);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("Brand删除")
    public String delete() {
        try {
            Long brandId = this.brand.getId();
            if (brandId != null) {
                this.brandService.delete(brandId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("Brand批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.brandService.deleteBatch(this.ids);
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
        Long empId = this.brand.getId();
        if (empId != null) {
            //获取数据库数据
            this.brand = this.brandService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
