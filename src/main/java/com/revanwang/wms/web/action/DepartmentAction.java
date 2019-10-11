package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Department;
import com.revanwang.wms.query.DepartmentQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepartmentService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DepartmentAction extends BaseAction {

    @Setter
    private IDepartmentService departmentService;
    @Getter
    private Department department = new Department();
    @Setter
    private List<Long> ids;
    @Getter
    private DepartmentQueryObject qo = new DepartmentQueryObject();

    @Override
    @RequiredPermission("部门列表")
    @InputConfig(methodName = "input")
    public String execute() {
        try {
            QueryResultObject resultObject = this.departmentService.query(qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }


    @Override
    @RequiredPermission("部门编辑")
    public String input() throws Exception {
        Long departId = this.department.getId();
        if (departId != null) {
            //Edit
            this.department = this.departmentService.get(departId);
            System.out.println("DepartmentAction.Edit:==" + this.department);
        } else {
            System.out.println("DepartmentAction.input:==" + this.department);
        }

        return INPUT;
    }


    /**
     * 保存和更新
     */
    @RequiredPermission("部门保存或更新")
    public String saveOrUpdate() {
        try {
            Long departId = this.department.getId();
            if (departId == null) {
                //保存
                this.departmentService.save(this.department);
                System.out.println("DepartmentAction.save：" + this.department);
                addActionMessage("部门保存成功");
            } else {  //更新
                this.departmentService.update(this.department);
                System.out.println("DepartmentAction.Update：" + this.department);
                addActionMessage("部门更改成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("部门删除")
    public String delete() {
        try {
            Long departId = this.department.getId();
            if (departId != null) {
                this.departmentService.delete(departId);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("部门批量删除")
    public String deleteBatch() {
        System.out.println("DepartmentAction.deleteBatch:==" + this.ids);
        try {
            if (this.ids.size() > 0) {
                this.departmentService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

}
