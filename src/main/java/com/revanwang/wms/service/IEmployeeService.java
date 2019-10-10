package com.revanwang.wms.service;


import com.revanwang.wms.domain.Employee;
import com.revanwang.wms.query.EmployeeQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IEmployeeService {

    /**
     * 保存员工
     * @param employee    员工
     */
    void save(Employee employee);

    /**
     * 删除员工
     * @param id    员工id
     */
    void delete(Long id);

    /**
     * 更新员工信息
     * @param employee    员工
     */
    void update(Employee employee);

    /**
     * 查询员工
     * @param id    员工id
     * @return  id员工
     */
    Employee get(Long id);

    /**
     * @return 返回所有的员工
     */
    List<Employee> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(EmployeeQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Employee> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Employee> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Employee queryObject(String condition, Object...args);

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     */
    void login(String username, String password);


    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
