package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IDepotDAO;
import com.revanwang.wms.domain.Depot;
import com.revanwang.wms.query.DepotQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IDepotService;
import lombok.Setter;

import java.util.List;

public class DepotServiceImpl implements IDepotService {


    @Setter
    private IDepotDAO depotDAO;

    @Override
    public void save(Depot depot) {
        this.depotDAO.save(depot);
    }

    @Override
    public void delete(Long id) {
        this.depotDAO.delete(get(id));
    }

    @Override
    public void update(Depot depot) {
        this.depotDAO.update(depot);
    }

    @Override
    public Depot get(Long id) {
        return this.depotDAO.get(id);
    }

    @Override
    public List<Depot> getList() {
        return this.depotDAO.getList();
    }

    @Override
    public QueryResultObject query(DepotQueryObject qo) {
        return this.depotDAO.query(qo);
    }

    @Override
    public List<Depot> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.depotDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Depot> query(String condition, Object... args) {
        return this.depotDAO.query(condition, args);
    }

    @Override
    public Depot queryObject(String condition, Object... args) {
        return this.depotDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.depotDAO.deleteBatch(ids);
    }

}
