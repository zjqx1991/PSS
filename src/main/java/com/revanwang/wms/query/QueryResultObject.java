package com.revanwang.wms.query;

import com.revanwang.wms.domain.Employee;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 查询结果对象
 */
@Setter
@Getter
public class QueryResultObject {
    private Integer currentPage;            //当前页
    private Integer pageSize;               //每页个数
    private Integer totalCount;             //总个数
    private List<Employee> resultList;      //查询结果


    private Integer totalPage;              //总页数
    private Integer prevPage;               //上一页
    private Integer nextPage;               //下一页

    public QueryResultObject(Integer currentPage, Integer pageSize, Integer totalCount, List<Employee> resultList) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.resultList = resultList;


        this.totalPage = (this.totalCount % this.pageSize == 0) ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = this.currentPage - 1 > 1 ? this.currentPage - 1 : 1;
        this.nextPage = this.currentPage + 1 < this.totalPage ? this.currentPage + 1 : this.totalPage;
    }

    public Integer getCurrentPage() {

        return this.currentPage > this.totalPage ? this.totalPage : this.currentPage;
    }
}
