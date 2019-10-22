package com.revanwang.wms.query;

import com.revanwang.utils.RevanContext;
import com.revanwang.utils.RevanMapUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientQueryObject extends AbstractQueryObject {
    private String  keyword;
    private String  tel;

    @Override
    void customQueryCondition() {

        if (hasLength(keyword)) {
            String key = "%" + keyword + "%";
            addQueryCondition("obj.name LIKE :name OR obj.sn LIKE :sn",
                    RevanMapUtils.revan_createMapObject("name", key),
                    RevanMapUtils.revan_createMapObject("sn", key));
        }

        if (hasLength(tel)) {
            String key = "%" + tel + "%";
            addQueryCondition("obj.phone LIKE :phone",
                    RevanMapUtils.revan_createMapObject("phone", key));
        }

    }
}
