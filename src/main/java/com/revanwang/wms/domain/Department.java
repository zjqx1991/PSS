package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 部门对象
 */
@Setter
@Getter
public class Department extends BaseDomain {
    private String      name;           //部门名称
    private String      sn;             //部门编码

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", id=" + id +
                '}';
    }
}
