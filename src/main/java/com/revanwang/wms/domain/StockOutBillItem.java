package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class StockOutBillItem extends BaseDomain {
    private BigDecimal salePrice;                  //销售价
    private BigDecimal number;                     //采购数量
    private BigDecimal amount;                     //明细小计
    private String remark;                         //备注

    private Product product;                       //货品
    private StockOutBill stockOutBill;             //入库单

}
