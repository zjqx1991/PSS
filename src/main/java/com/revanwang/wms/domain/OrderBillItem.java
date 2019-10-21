package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class OrderBillItem extends BaseDomain {
    private BigDecimal  costPrice;                  //采购成本价
    private BigDecimal  number;                     //采购数量
    private BigDecimal  amount;                     //明细小计
    private String      remark;                     //备注

    private Product     product;                    //货品
    private OrderBill   orderBill;                  //单据


}
