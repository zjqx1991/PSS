package com.revanwang.wms.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.domain.Persion;
import com.revanwang.wms.query.PersionQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPersionService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PersionAction extends BaseAction {


    @Setter
    private IPersionService persionService;


    @Getter
    private Persion persion = new Persion();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private PersionQueryObject qo = new PersionQueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("Persion列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.persionService.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("Persion编辑")
    public String input() throws Exception {

        Long persionId = this.persion.getId();
        if (persionId != null) {
            this.persion = this.persionService.get(persionId);
            System.out.println("PersionAction.input: " + this.persion);
        }
        return INPUT;
    }


    @RequiredPermission("Persion保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.persion.getId();
            if (id == null) {
                //新增
                this.persionService.save(this.persion);
                addActionMessage("保存成功");
            } else {
                //编辑
                this.persionService.update(this.persion);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("Persion删除")
    public String delete() {
        try {
            Long persionId = this.persion.getId();
            if (persionId != null) {
                this.persionService.delete(persionId);
                addActionMessage("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }

    @RequiredPermission("Persion批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.persionService.deleteBatch(this.ids);
                addActionMessage("批量删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }


    /**
     * 拦截 saveOrUpdate方法
     *
     * @throws Exception
     */
    public void prepareSaveOrUpdate() throws Exception {
        System.out.println("prepareSaveOrUpdate:====" + hasActionErrors());
        Long empId = this.persion.getId();
        if (empId != null) {
            //获取数据库数据
            this.persion = this.persionService.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
