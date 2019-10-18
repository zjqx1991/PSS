package com.revanwang.wms.query;

import com.revanwang.utils.RevanMapUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductQueryObject extends AbstractQueryObject {
    private String  keyword;
    private Long    brandId = -1L;

    @Override
    void customQueryCondition() {

        if (hasLength(this.keyword)) {
            String key = "%" + this.keyword + "%";

            addQueryCondition("(obj.name LIKE :name OR obj.sn LIKE :sn)",
                    RevanMapUtils.revan_createMapObject("name", key),
                    RevanMapUtils.revan_createMapObject("sn", key));
        }
        if (this.brandId.intValue() > 0) {
            addQueryCondition("obj.brand.id = :brandId",
                    RevanMapUtils.revan_createMapObject("brandId", this.brandId));
        }
    }
}
