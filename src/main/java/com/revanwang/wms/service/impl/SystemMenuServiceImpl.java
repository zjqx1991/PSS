package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.ISystemMenuDAO;
import com.revanwang.wms.domain.SystemMenu;
import com.revanwang.wms.query.SystemMenuQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.ISystemMenuService;
import com.revanwang.wms.vo.SystemMenuVO;
import lombok.Setter;

import java.util.*;

public class SystemMenuServiceImpl implements ISystemMenuService {


    @Setter
    private ISystemMenuDAO systemMenuDAO;

    @Override
    public void save(SystemMenu systemMenu) {
        this.systemMenuDAO.save(systemMenu);
    }

    @Override
    public void delete(Long id) {
        SystemMenuQueryObject qo = new SystemMenuQueryObject();
        qo.setParentId(id);
        QueryResultObject resultObject = this.query(qo);
        if (resultObject.getTotalCount() > 0) {
            throw new RuntimeException("该菜单正在被使用");
        }
        this.systemMenuDAO.delete(get(id));
    }

    @Override
    public void update(SystemMenu systemMenu) {
        this.systemMenuDAO.update(systemMenu);
    }

    @Override
    public SystemMenu get(Long id) {
        return this.systemMenuDAO.get(id);
    }

    @Override
    public List<SystemMenu> getList() {
        return this.systemMenuDAO.getList();
    }

    @Override
    public QueryResultObject query(SystemMenuQueryObject qo) {
        return this.systemMenuDAO.query(qo);
    }

    @Override
    public List<SystemMenu> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.systemMenuDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<SystemMenu> query(String condition, Object... args) {
        return this.systemMenuDAO.query(condition, args);
    }

    @Override
    public SystemMenu queryObject(String condition, Object... args) {
        return this.systemMenuDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.systemMenuDAO.deleteBatch(ids);
    }

    @Override
    public List<SystemMenuVO> queryMenus(SystemMenuQueryObject qo) {
        List<SystemMenuVO> menuVOList = new ArrayList<>();
        SystemMenu parentMenu = get(qo.getParentId());
        systemMenuIteration(menuVOList, parentMenu);

        Collections.reverse(menuVOList);
        return menuVOList;
    }

    /**
     * 迭代Menu
     * @param menuVOList
     * @param parentMenu
     */
    private void systemMenuIteration(List<SystemMenuVO> menuVOList, SystemMenu parentMenu) {
        if (parentMenu != null) {
            SystemMenuVO menuVO = new SystemMenuVO();
            menuVO.setId(parentMenu.getId());
            menuVO.setName(parentMenu.getName());
            menuVOList.add(menuVO);
            //继续迭代
            systemMenuIteration(menuVOList, parentMenu.getParent());
        }
    }

}
