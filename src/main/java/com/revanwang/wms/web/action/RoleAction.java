package com.revanwang.wms.web.action;

import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.domain.Role;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPermissionService;
import com.revanwang.wms.service.IRoleService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RoleAction extends BaseAction {

    @Setter
    private IRoleService roleService;

    @Setter
    private IPermissionService permissionService;

    @Getter
    private CommonQueryObject qo = new CommonQueryObject();
    @Getter
    private Role role = new Role();

    @Override
    @RequiredPermission("角色列表")
    public String execute() throws Exception {

        QueryResultObject resultObject = this.roleService.query(qo);
        ActionContextPut("pageResult", resultObject);
        return LIST;
    }


    @Override
    @RequiredPermission("角色编辑")
    public String input() throws Exception {
        System.out.println("RoleAction.input:==" + this.role);
        Long roleId = this.role.getId();
        List<Permission> permissions = this.permissionService.getList();
        ActionContextPut("permissions", permissions);
        if (roleId != null) {
            this.role = this.roleService.get(roleId);
        }
        return INPUT;
    }

    @RequiredPermission("角色删除")
    public String delete() {
        System.out.println("RoleAction.delete:==" + this.role);
        Long roleId = this.role.getId();
        if (roleId != null) {
            this.roleService.delete(roleId);
        }
        return SUCCESS;
    }

    @RequiredPermission("角色保存或更新")
    public String saveOrUpdate() {
        System.out.println("RoleAction.saveOrUpdate:==" + this.role);
        Long roleId = this.role.getId();
        if (roleId == null) {
            this.roleService.save(this.role);
        } else {
            this.roleService.update(this.role);
        }
        return SUCCESS;
    }

    /**
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("RoleAction.prepareSaveOrUpdate");
        /**
         * 因为使用了 paramsPrepareParamsStack 所以会
         * 获取2次参数，导致角色中的Permission重复
         */
        this.role.setPermissions(null);
    }
}
