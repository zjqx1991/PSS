package com.revanwang.wms.service.impl;

import com.revanwang.utils.RevanPermissionUtils;
import com.revanwang.wms.annotation.RequiredPermission;
import com.revanwang.wms.dao.IPermissionDAO;
import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPermissionService;
import com.revanwang.wms.web.action.BaseAction;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;
import java.util.*;

public class PermissServiceImpl implements IPermissionService, ApplicationContextAware {

    private ApplicationContext ctx;

    @Setter
    private IPermissionDAO permissionDAO;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }


    @Override
    public void reload() {
        //获取数据库中存在的权限对象
        List<Permission> list = this.permissionDAO.getList();
        Set<String> expressionSet = new HashSet<>();
        for (Permission p:list) {
            expressionSet.add(p.getExpression());
        }

        //1、扫描所有的BaseAction的子类
        Map<String, BaseAction> actionMap = this.ctx.getBeansOfType(BaseAction.class);
        //2、遍历Action
        Collection<BaseAction> actionCollection = actionMap.values();
        for (BaseAction action : actionCollection) {
            //3、迭代每一个Action类中的方法
            Method[] methods = action.getClass().getDeclaredMethods();
            //4、迭代方法
            for (Method m : methods) {
                String expression = RevanPermissionUtils.buildExpression(m);

                if (!expressionSet.contains(expression)) {
                    //5、判断当前方法上是否存在RequiredPermission标签
                    RequiredPermission rp = m.getAnnotation(RequiredPermission.class);
                    if (null != rp) {
                        Permission permission = new Permission();
                        permission.setName(rp.value());
                        permission.setExpression(RevanPermissionUtils.buildExpression(m));
                        this.permissionDAO.save(permission);
                    }
                }

            }
        }

    }

    @Override
    public void delete(Long id) {
        this.permissionDAO.delete(get(id));
    }

    @Override
    public Permission get(Long id) {
        return this.permissionDAO.get(id);
    }

    @Override
    public List<Permission> getList() {
        return this.permissionDAO.getList();
    }

    @Override
    public QueryResultObject query(CommonQueryObject qo) {
        return this.permissionDAO.query(qo);
    }

    @Override
    public List<Permission> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.permissionDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Permission> query(String condition, Object... args) {
        return this.permissionDAO.query(condition, args);
    }

    @Override
    public Permission queryObject(String condition, Object... args) {
        return this.permissionDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.permissionDAO.deleteBatch(ids);
    }


}
