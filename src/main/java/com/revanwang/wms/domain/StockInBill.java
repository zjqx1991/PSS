package com.revanwang.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class StockInBill extends BaseDomain {

    public static final int NORMAL = 0;         //未审核
    public static final int AUDIT = 1;          //未审核


    private String  sn;                             //订单单据编号，可以自动生成，也可以手动输入
    private Date    vdate;                          //业务时间
    private int     status = StockInBill.NORMAL;      //单据审核状态，缺省是未审核
    private BigDecimal  totalNumber;                //入库总数量
    private BigDecimal  totalAmount;                //入库总金额
    private Depot    depot;                         //仓库
    private Employee    inputUser;                  //制单人
    private Date        inputTime;                  //制单时间
    private Employee    auditor;                    //审核人
    private Date        auditTime;                  //审核时间

    //单据明细
    private List<StockInBillItem> items = new ArrayList<>();

    @Override
    public String toString() {
        return "StockInBill{" +
                "sn='" + sn + '\'' +
                ", vdate=" + vdate +
                ", status=" + status +
                ", totalNumber=" + totalNumber +
                ", totalAmount=" + totalAmount +
                ", depot=" + depot +
                ", inputUser=" + inputUser +
                ", inputTime=" + inputTime +
                ", auditor=" + auditor +
                ", auditTime=" + auditTime +
                ", id=" + id +
                '}';
    }
}
