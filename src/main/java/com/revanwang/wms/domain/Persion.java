package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Persion extends BaseDomain {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
