package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IClientDAO;
import com.revanwang.wms.domain.Client;
import com.revanwang.wms.query.ClientQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IClientService;
import lombok.Setter;

import java.util.List;

public class ClientServiceImpl implements IClientService {


    @Setter
    private IClientDAO clientDAO;

    @Override
    public void save(Client client) {
        this.clientDAO.save(client);
    }

    @Override
    public void delete(Long id) {
        this.clientDAO.delete(get(id));
    }

    @Override
    public void update(Client client) {
        this.clientDAO.update(client);
    }

    @Override
    public Client get(Long id) {
        return this.clientDAO.get(id);
    }

    @Override
    public List<Client> getList() {
        return this.clientDAO.getList();
    }

    @Override
    public QueryResultObject query(ClientQueryObject qo) {
        return this.clientDAO.query(qo);
    }

    @Override
    public List<Client> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.clientDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Client> query(String condition, Object... args) {
        return this.clientDAO.query(condition, args);
    }

    @Override
    public Client queryObject(String condition, Object... args) {
        return this.clientDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.clientDAO.deleteBatch(ids);
    }

}
