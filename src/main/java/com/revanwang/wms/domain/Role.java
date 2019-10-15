package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象
 */
@Setter
@Getter
public class Role extends BaseDomain {
    private String      name;       //角色名称
    private String      sn;         //角色编码

    //角色所拥有权限
    private List<Permission> permissions = new ArrayList<>();

    //系统菜单
    private List<SystemMenu> systemMenuList = new ArrayList<>();

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", permissions=" + permissions +
                ", id=" + id +
                '}';
    }
}
