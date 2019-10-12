package com.revanwang.wms.service;

import com.revanwang.wms.domain.Role;
import com.revanwang.wms.query.CommonQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IRoleService {

    /**
     * 保存角色
     * @param role    角色
     */
    void save(Role role);

    /**
     * 删除角色
     * @param id    角色id
     */
    void delete(Long id);

    /**
     * 更新角色信息
     * @param role    角色
     */
    void update(Role role);

    /**
     * 查询role
     * @param id    roleid
     * @return  角色
     */
    Role get(Long id);

    /**
     * @return 返回所有的角色
     */
    List<Role> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(CommonQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Role> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Role> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Role queryObject(String condition, Object...args);

    void deleteBatch(List<Long> ids);
}
