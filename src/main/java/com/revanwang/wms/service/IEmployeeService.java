package com.revanwang.wms.service;


import com.revanwang.wms.domain.Employee;

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
}