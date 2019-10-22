package com.revanwang.wms.query;

import com.revanwang.utils.RevanDateUtil;
import com.revanwang.utils.RevanMapUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class StockOutBillQueryObject extends AbstractQueryObject {
    private Date beginDate;                 //业务开始时间
    private Date  endDate;                  //业务结束时间
    private Long    depotId = -1L;          //ID，默认为全部供应商
    private int     status = -1;            //订单状态，默认为所有状态

    @Override
    void customQueryCondition() {

        if (beginDate != null) {
            addQueryCondition("obj.vdate >= :beginDate",
                    RevanMapUtils.revan_createMapObject("beginDate", RevanDateUtil.revan_beginDate(beginDate)));
        }

        if (endDate != null) {
            addQueryCondition("obj.vdate <= :endDate",
                    RevanMapUtils.revan_createMapObject("endDate", RevanDateUtil.revan_endDate(endDate)));
        }

        if (depotId.intValue() > 0) {
            addQueryCondition("obj.depot.id = :depotId",
                    RevanMapUtils.revan_createMapObject("depotId", depotId));
        }

        if (status >= 0) {
            addQueryCondition("obj.status = :status",
                    RevanMapUtils.revan_createMapObject("status", status));
        }
    }
}
