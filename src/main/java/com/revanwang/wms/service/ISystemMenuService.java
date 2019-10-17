package com.revanwang.wms.service;


import com.revanwang.wms.domain.SystemMenu;
import com.revanwang.wms.query.SystemMenuQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.vo.SystemMenuVO;

import java.util.List;

public interface ISystemMenuService {

    /**
     * 保存SystemMenu对象
     * @param systemMenu
     */
    void save(SystemMenu systemMenu);

    /**
     * 删除SystemMenu对象
     * @param id    SystemMenu的id
     */
    void delete(Long id);

    /**
     * 更新SystemMenu对象
     * @param systemMenu
     */
    void update(SystemMenu systemMenu);

    /**
     * 查询SystemMenu对象
     * @param id    SystemMenu的id
     * @return  id
     */
    SystemMenu get(Long id);

    /**
     * @return 返回所有的SystemMenu对象
     */
    List<SystemMenu> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(SystemMenuQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<SystemMenu> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<SystemMenu> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    SystemMenu queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 查询显示菜单链
     * @param qo
     * @return
     */
    List<SystemMenuVO> queryMenus(SystemMenuQueryObject qo);

    /**
     * 获取子系统
     * @return
     */
    List<SystemMenu> queryChildrenSystemMenu();

    /**
     * 通过parentSn 来获取用户所拥有的系统菜单
     * @param parentSn
     * @return
     */
    List<SystemMenu> querySystemMenuByParentSn(String parentSn);
}
