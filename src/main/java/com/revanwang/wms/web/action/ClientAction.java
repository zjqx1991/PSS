package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Client;
import com.revanwang.wms.query.ClientQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IClientService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ClientAction extends BaseAction {


    @Setter
    private IClientService clientService;           


    @Getter
    private Client client = new Client();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private ClientQueryObject qo = new ClientQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("客户列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.clientService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("客户编辑")
    public String input() throws Exception {

        Long clientId = this.client.getId();
        if (clientId != null) {
            this.client = this.clientService.get(clientId);
            System.out.println("ClientAction.input: " + this.client);
        }
        return INPUT;
    }


    @RequiredPermission("客户保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.client.getId();
            if (id == null) {
                //新增
                this.clientService.save(this.client);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.clientService.update(this.client);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("客户删除")
    public String delete() {
        try {
            Long clientId = this.client.getId();
            if (clientId != null) {
                this.clientService.delete(clientId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("客户批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.clientService.deleteBatch(this.ids);
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
        Long empId = this.client.getId();
        if (empId != null) {
            //获取数据库数据
            this.client = this.clientService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
