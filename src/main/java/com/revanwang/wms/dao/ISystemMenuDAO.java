package com.revanwang.wms.dao;

import com.revanwang.wms.domain.Role;
import com.revanwang.wms.domain.SystemMenu;

import java.util.List;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu> {

    List<SystemMenu> queryChildrenSystemMenu();

    List<SystemMenu> querySystemMenuByParentSn(String parentSn);

    List<SystemMenu> querySystemMenuByParentSnAndRoles(String parentSn, List<Role> roles);
}
