package ${basePKG}.service.impl;

import ${basePKG}.dao.I${className}DAO;
import ${basePKG}.domain.${className};
import ${basePKG}.query.${className}QueryObject;
import ${basePKG}.query.QueryResultObject;
import ${basePKG}.service.I${className}Service;
import lombok.Setter;

import java.util.List;

public class ${className}ServiceImpl implements I${className}Service {


    @Setter
    private I${className}DAO ${lowerClassName}DAO;

    @Override
    public void save(${className} ${lowerClassName}) {
        this.${lowerClassName}DAO.save(${lowerClassName});
    }

    @Override
    public void delete(Long id) {
        this.${lowerClassName}DAO.delete(get(id));
    }

    @Override
    public void update(${className} ${lowerClassName}) {
        this.${lowerClassName}DAO.update(${lowerClassName});
    }

    @Override
    public ${className} get(Long id) {
        return this.${lowerClassName}DAO.get(id);
    }

    @Override
    public List<${className}> getList() {
        return this.${lowerClassName}DAO.getList();
    }

    @Override
    public QueryResultObject query(${className}QueryObject qo) {
        return this.${lowerClassName}DAO.query(qo);
    }

    @Override
    public List<${className}> query(Integer currentPage, Integer pageSize, String condition, Object... args) {
        return this.${lowerClassName}DAO.query(currentPage, pageSize, condition, args);
    }

    @Override
    public List<${className}> query(String condition, Object... args) {
        return this.${lowerClassName}DAO.query(condition, args);
    }

    @Override
    public ${className} queryObject(String condition, Object... args) {
        return this.${lowerClassName}DAO.queryObject(condition, args);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.${lowerClassName}DAO.deleteBatch(ids);
    }

}
