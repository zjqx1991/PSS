package com.revanwang.wms.service;


import com.revanwang.wms.domain.Client;
import com.revanwang.wms.query.ClientQueryObject;
import com.revanwang.wms.query.QueryResultObject;

import java.util.List;

public interface IClientService {

    /**
     * 保存Client对象
     * @param client
     */
    void save(Client client);

    /**
     * 删除Client对象
     * @param id    Client的id
     */
    void delete(Long id);

    /**
     * 更新Client对象
     * @param client
     */
    void update(Client client);

    /**
     * 查询Client对象
     * @param id    Client的id
     * @return  id
     */
    Client get(Long id);

    /**
     * @return 返回所有的Client对象
     */
    List<Client> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(ClientQueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Client> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<Client> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    Client queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
