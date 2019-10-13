package com.revanwang.wms.service.impl;

import com.revanwang.wms.dao.IPersionDAO;
import com.revanwang.wms.domain.Persion;
import com.revanwang.wms.query.PersionQueryObject;
import com.revanwang.wms.query.QueryResultObject;
import com.revanwang.wms.service.IPersionService;
import lombok.Setter;

import java.util.List;

public class PersionServiceImpl implements IPersionService {


    @Setter
    private IPersionDAO persionDAO;

    @Override
    public void save(Persion persion) {
        this.persionDAO.save(persion);
    }

    @Override
    public void delete(Long id) {
        this.persionDAO.delete(get(id));
    }

    @Override
    public void update(Persion persion) {
        this.persionDAO.update(persion);
    }

    @Override
    public Persion get(Long id) {
        return this.persionDAO.get(id);
    }

    @Override
    public List<Persion> getList() {
        return this.persionDAO.getList();
    }

    @Override
    public QueryResultObject query(PersionQueryObject qo) {
        return this.persionDAO.query(qo);
    }

    @Override
    public List<Persion> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.persionDAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<Persion> query(String condition, Object... args) {
        return this.persionDAO.query(condition, args);
    }

    @Override
    public Persion queryObject(String condition, Object... args) {
        return this.persionDAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.persionDAO.deleteBatch(ids);
    }

}
