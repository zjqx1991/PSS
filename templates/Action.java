package ${basePKG}.web.action;

import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import ${basePKG}.annotation.RequiredPermission;
import ${basePKG}.domain.${className};
import ${basePKG}.query.${className}QueryObject;
import ${basePKG}.query.QueryResultObject;
import ${basePKG}.service.I${className}Service;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ${className}Action extends BaseAction {


    @Setter
    private I${className}Service ${lowerClassName}Service;           


    @Getter
    private ${className} ${lowerClassName} = new ${className}();
    /**
     * 高级查询 + 分页查询
     */
    @Getter
    private ${className}QueryObject qo = new ${className}QueryObject();

    @Setter
    private List<Long> ids;

    @Override
    @RequiredPermission("${className}列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            QueryResultObject resultObject = this.${lowerClassName}Service.query(this.qo);
            ActionContextPut("pageResult", resultObject);
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return LIST;
    }

    @Override
    @RequiredPermission("${className}编辑")
    public String input() throws Exception {

        Long ${lowerClassName}Id = this.${lowerClassName}.getId();
        if (${lowerClassName}Id != null) {
            this.${lowerClassName} = this.${lowerClassName}Service.get(${lowerClassName}Id);
            System.out.println("${className}Action.input: " + this.${lowerClassName});
        }
        return INPUT;
    }


    @RequiredPermission("${className}保存或更新")
    public String saveOrUpdate() {
        try {
            Long id = this.${lowerClassName}.getId();
            if (id == null) {
                //新增
                this.${lowerClassName}Service.save(this.${lowerClassName});
                addActionMessage("保存成功");
            } else {
                //编辑
                this.${lowerClassName}Service.update(this.${lowerClassName});
                addActionMessage("更新成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return SUCCESS;
    }


    @RequiredPermission("${className}删除")
    public String delete() {
        try {
            Long ${lowerClassName}Id = this.${lowerClassName}.getId();
            if (${lowerClassName}Id != null) {
                this.${lowerClassName}Service.delete(${lowerClassName}Id);
                addActionMessage("删除成功");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return NONE;
    }

    @RequiredPermission("${className}批量删除")
    public String deleteBatch() {
        try {
            if (this.ids.size() > 0) {
                this.${lowerClassName}Service.deleteBatch(this.ids);
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
        Long empId = this.${lowerClassName}.getId();
        if (empId != null) {
            //获取数据库数据
            this.${lowerClassName} = this.${lowerClassName}Service.get(empId);
        }
        /**
         * 修改用户的部门时，报错
         * 清除一级缓存
         */
    }

}
