package com.revanwang.wms.service.impl;

import com.revanwang.utils.MD5;
import com.revanwang.utils.RevanContext;
import com.revanwang.wms.dao.IEmployeeDAO;
import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.domain.Permission;
import com.revanwang.wms.domain.Role;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IEmployeeService;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeServiceImpl implements IEmployeeService {


    @Setter
    private IEmployeeDAO employeeDAO;

    @Override
    public void save(Employee employee) {
        //用户密码加密
        employee.setPassword(MD5.encode(employee.getPassword()));
        this.employeeDAO.save(employee);
    }

    @Override
    public void delete(Long id) {
        this.employeeDAO.delete(get(id));
    }

    @Override
    public void update(Employee employee) {
        this.employeeDAO.update(employee);
    }

    @Override
    public Employee get(Long id) {
        return this.employeeDAO.get(id);
    }

    @Override
    public List<Employee> getList() {
        return this.employeeDAO.getList();
    }

    @Override
    public QueryResultObject query(EmployeeQueryObject qo) {
        return this.employeeDAO.query(qo);
    }

    @Override
    public List<Employee> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.employeeDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Employee> query(String condition, Object... args) {
        return this.employeeDAO.query(condition, args);
    }

    @Override
    public Employee queryObject(String condition, Object... args) {
        return this.employeeDAO.queryObject(condition, args);
    }

    @Override
    public void login(String username, String password) {
        //1、查询数据库
        Employee employee = this.employeeDAO.checkLogin(username, MD5.encode(password));

        System.out.println("username_password:==" + employee);
        if (employee == null) {
            throw new RuntimeException("亲，用户名或密码名错误");
        }

        //2、把用户存储到session
        RevanContext.revan_setCurrentSessionObject(employee);

        //3、把用户的权限存储到session
        if (!employee.isAdmin()) {
            //用户所拥有的权限
            Set<String> permissionSet = new HashSet<>();
            //获取角色
            List<Role> roleList = employee.getRoles();
            for (Role role : roleList) {
                List<Permission> permissionList = role.getPermissions();
                for (Permission p : permissionList) {
                    permissionSet.add(p.getExpression());
                }
            }
            RevanContext.revan_setCurrentPermission(permissionSet);
        }
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.employeeDAO.deleteBatch(ids);
    }

}
