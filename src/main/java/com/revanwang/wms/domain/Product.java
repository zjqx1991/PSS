package com.revanwang.wms.domain;


import com.revanwang.utils.FileUploadUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品
 */
@Setter
@Getter
public class Product extends BaseDomain {
    private String  name;           //商品名称
    private String  sn;             //商品编号
    private BigDecimal  costPrice;  //成本价
    private BigDecimal  salePrice;  //销售价
    private String  intro;          //描述
    private String  imagePath;      //图片地址

    private Brand   brand;          //所属品牌

    public String getSmallImagePath() {
        if (this.imagePath == null) {
            //默认图片
            return "";
        }
        int index = this.imagePath.lastIndexOf(".");
        String name = this.imagePath.substring(0, index);
        String type = this.imagePath.substring(index);
        return name + FileUploadUtil.suffix + type;
    }
}
