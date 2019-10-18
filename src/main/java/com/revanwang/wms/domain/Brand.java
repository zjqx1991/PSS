package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Brand extends BaseDomain {
    private String  name;       //品牌名称
    private String  sn;         //品牌编号
}
