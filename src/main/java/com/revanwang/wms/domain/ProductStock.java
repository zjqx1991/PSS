package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存
 */
@Setter
@Getter
public class ProductStock extends BaseDomain {
    private BigDecimal  price;              //销售价
    private BigDecimal  storeNumber;        //商品库存
    private BigDecimal  amount;             //商品总价值
    private Date incomeDate;                //入库时间
    private Date  outcomeDate;              //出库时间
    private Product product;                //商品
    private Depot   depot;                  //仓库
}
