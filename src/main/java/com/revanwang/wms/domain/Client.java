package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client extends BaseDomain {
    private String  name;       //客户名称
    private String  sn;         //客户编号
    private String  phone;      //客户电话
}
