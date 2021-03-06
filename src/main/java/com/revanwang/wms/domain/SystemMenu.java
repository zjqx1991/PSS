package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SystemMenu extends BaseDomain implements IJson {
    private String  name;       //菜单名称
    private String  url;        //菜单链接，子菜单拥有，父菜单没有
    private String  sn;         //菜单编号，父菜单有，子菜单没有，用来加载自己的子菜单
    private SystemMenu  parent; //上级菜单

    //下级菜单
    private List<SystemMenu> children = new ArrayList<>();

    @Override
    public String toString() {
        return "SystemMenu{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", sn='" + sn + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public Object jsonObject() {
        Map<String, Object> map = new HashMap<>();
        //"{id: 21, pId: 2, name: '系统模块'}"
        map.put("id", id);
        map.put("pId", this.parent != null ? this.parent.getId() : null);
        map.put("name", name);
        map.put("action", url);
        return map;
    }
}
