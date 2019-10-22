package com.revanwang.wms.query;

import com.revanwang.utils.RevanMapUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStockQueryObject extends AbstractQueryObject {
    private String  keyword;

    @Override
    void customQueryCondition() {
        if (hasLength(this.keyword)) {
            String key = "%" + this.keyword + "%";
            System.out.println("ProductStockQueryObject.customQueryCondition:==" + this.keyword);
            addQueryCondition("(obj.product.name LIKE :name OR obj.depot.name LIKE :sn)",
                    RevanMapUtils.revan_createMapObject("name", key),
                    RevanMapUtils.revan_createMapObject("sn", key));
        }
    }
}
