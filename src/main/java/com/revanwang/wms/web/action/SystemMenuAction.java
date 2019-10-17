package com.revanwang.wms.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.SystemMenu;
import com.revanwang.wms.query.SystemMenuQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.ISystemMenuService;
import com.revanwang.wms.vo.SystemMenuVO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class SystemMenuAction extends BaseAction {


    @Setter
    private ISystemMenuService systemMenuService;


    @Getter
    private SystemMenu systemMenu = new SystemMenu();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private SystemMenuQueryObject qo = new SystemMenuQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("菜单列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {

        try {
            //查询显示链条
            Long parentId = this.qo.getParentId();

            if (parentId.intValue() > 0) {
                List<SystemMenuVO> menuVOList = this.systemMenuService.queryMenus(this.qo);
                ActionContextPut("menus", menuVOList);
            }
            QueryResultObject resultObject = this.systemMenuService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("菜单编辑")
    public String input() throws Exception {
        Long parentId = this.qo.getParentId();
        if (parentId.intValue() < 0) {
            ActionContextPut("parentName", "根菜单");
        } else {
            ActionContextPut("parentName",
                    this.systemMenuService.get(this.qo.getParentId()).getName());
        }
        Long systemMenuId = this.systemMenu.getId();
        if (systemMenuId != null) {
            this.systemMenu = this.systemMenuService.get(systemMenuId);
            System.out.println("SystemMenuAction.input: " + this.systemMenu);
        }
        return INPUT;
    }


    @RequiredPermission("菜单保存或更新")
    public String saveOrUpdate() {
        System.out.println("保存或更新:==" + this.systemMenu + "___:" + this.qo.getParentId());
        try {
            Long parentId = this.qo.getParentId();
            if (parentId.intValue() > 0) {
                //设置父类
                this.systemMenu.setParent(this.systemMenuService.get(parentId));
            }
            Long id = this.systemMenu.getId();
            if (id == null) {
                //新增
                this.systemMenuService.save(this.systemMenu);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.systemMenuService.update(this.systemMenu);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("菜单删除")
    public String delete() throws Exception {
        try {
            Long systemMenuId = this.systemMenu.getId();
            if (systemMenuId != null) {
                this.systemMenuService.delete(systemMenuId);
                addActionMessage("删除成功");
                ActionContextResponseMsg("删除成功");
            }
        } catch (Exception e) {
            ActionContextResponseMsg(e.getMessage());
            addActionError(e.getMessage());
            e.printStackTrace();
        }
        return NONE;
    }

    @RequiredPermission("菜单批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.systemMenuService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }


    public String loadSystemMenuByParentSn() throws Exception {
        List<SystemMenu> list = this.systemMenuService.querySystemMenuByParentSn(this.qo.getParentSn());
        List<Object> jsonList = new ArrayList<>();
        for (SystemMenu menu : list) {
            jsonList.add(menu.jsonObject());
        }
        ActionContextJson(JSON.toJSONString(jsonList));
        return NONE;
    }

    /**
     * 拦截 saveOrUpdate方法
     *
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.systemMenu.getId();
        if (empId != null) {
            //获取数据库数据
            this.systemMenu = this.systemMenuService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
