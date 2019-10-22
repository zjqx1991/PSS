package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Depot;
import com.revanwang.wms.query.DepotQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepotService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.internal.ExceptionMapperStandardImpl;

import java.util.List;

public class DepotAction extends BaseAction {


    @Setter
    private IDepotService depotService;           


    @Getter
    private Depot depot = new Depot();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private DepotQueryObject qo = new DepotQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("仓库列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.depotService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("仓库编辑")
    public String input() throws Exception {

        Long depotId = this.depot.getId();
        if (depotId != null) {
            this.depot = this.depotService.get(depotId);
            System.out.println("DepotAction.input: " + this.depot);
        }
        return INPUT;
    }


    @RequiredPermission("仓库保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.depot.getId();
            if (id == null) {
                //新增
                this.depotService.save(this.depot);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.depotService.update(this.depot);
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("仓库删除")
    public String delete() {
        try {
            Long depotId = this.depot.getId();
            if (depotId != null) {
                this.depotService.delete(depotId);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            System.out.println("DepotAction.delete:==" + e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("仓库批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.depotService.deleteBatch(this.ids);
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
        Long empId = this.depot.getId();
        if (empId != null) {
            //获取数据库数据
            this.depot = this.depotService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
