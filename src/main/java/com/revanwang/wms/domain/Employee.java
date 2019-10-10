package com.revanwang.wms.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工对象
 */
@Setter
@Getter
public class Employee extends BaseDomain {
    private String      name;               //员工名称
    private String      password;           //员工密码
    private String      email;              //员工邮箱
    private Integer     age;                //员工年龄
    private boolean     admin;              //是否是超级管理员

    //员工的所有角色
    private List<Role> roles = new ArrayList<>();
    private Department  department;         //员工所属部门


    /**
     * 定义一个属性获取用户的角色名称
     * @return
     */
    public String getRoleName() {
        if (this.admin) {
            return "[超级管理员]";
        }

        if (this.roles.size() == 0) {
            return "暂未配置角色";
        }

        StringBuilder sb = new StringBuilder(50);
        sb.append("[ ");
        for (Role role : roles) {
            sb.append(role.getName());
            sb.append(" ,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" ]");
        System.out.println("Employee.getRoleName:===" + sb.toString());
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", admin=" + admin +
                ", roles=" + roles +
                ", department=" + department +
                '}';
    }
}
