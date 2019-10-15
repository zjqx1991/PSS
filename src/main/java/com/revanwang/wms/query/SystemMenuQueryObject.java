package com.revanwang.wms.query;

import com.revanwang.utils.RevanMapUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemMenuQueryObject extends AbstractQueryObject {
    private Long parentId = -1L;

    @Override
    void customQueryCondition() {
        System.out.println("SystemMenuQueryObject.customQueryCondition");
        if (this.parentId > 0) {
            addQueryCondition("obj.parent.id = :parentId",
                    RevanMapUtils.revan_createMapObject("parentId", this.parentId));
        }
        else {
            addQueryCondition("obj.parent IS NULL");
        }
    }
}
