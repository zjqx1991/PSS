package com.revanwang.wms.query;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * 封装了 查询条件
 */
abstract public class AbstractQueryObject {

    @Setter
    @Getter
    private Integer currentPage = 1;
    @Setter @Getter
    private Integer pageSize = 5;

    //查询条件
    private List<String> conditionList = new ArrayList<>();

    //查询条件参数
    private List paramList = new ArrayList<>();

    private boolean isbuild = false;

    /**
     * 子类自定义查询条件
     */
    abstract void customQueryCondition();


    private void init() {
        if (!isbuild) {
            this.customQueryCondition();
            isbuild = true;
        }
    }

    /**
     * 添加查询条件
     * @param condition 查询条件
     * @param args      条件参数
     */
    protected void addQueryCondition(String condition, Object...args) {
        this.conditionList.add(condition);
        this.paramList.addAll(Arrays.asList(args));
    }

    /**
     * 高级查询条件
     */
    public String getQueryCondition() {
        //添加条件（钩子）
        init();
        int conditionCount = this.conditionList.size();
        if (conditionCount == 0) {
            return "";
        }

        //拼接查询条件
        StringBuilder sb = new StringBuilder(80);
        for (int i = 0; i < conditionCount; i++) {
            if (i == 0) {
                sb.append(" WHERE ");
            }
            else {
                sb.append(" AND ");
            }
            sb.append(this.conditionList.get(i));
        }
        return sb.toString();
    }

    /**
     * 高级查询条件参数
     */
    public List getQueryParams() {
        //添加条件（钩子）
        init();
        return this.paramList;
    }

    protected boolean hasLength(String string) {
        return string != null && string.length() > 0 && !"".equals(string);
    }

}
