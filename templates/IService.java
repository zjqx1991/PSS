package ${basePKG}.service;


import ${basePKG}.domain.${className};
import ${basePKG}.query.${className}QueryObject;
import ${basePKG}.query.QueryResultObject;

import java.util.List;

public interface I${className}Service {

    /**
     * 保存${className}对象
     * @param ${lowerClassName}
     */
    void save(${className} ${lowerClassName});

    /**
     * 删除${className}对象
     * @param id    ${className}的id
     */
    void delete(Long id);

    /**
     * 更新${className}对象
     * @param ${lowerClassName}
     */
    void update(${className} ${lowerClassName});

    /**
     * 查询${className}对象
     * @param id    ${className}的id
     * @return  id
     */
    ${className} get(Long id);

    /**
     * @return 返回所有的${className}对象
     */
    List<${className}> getList();


    /**
     * 高级查询 + 分页查询
     * @param qo    查询对象
     * @return
     */
    QueryResultObject query(${className}QueryObject qo);



    /**
     * 查询条件
     * @param currentPage   当前页
     * @param pageSize      每页个数
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<${className}> query(Integer currentPage, Integer pageSize, String condition, Object...args);

    /**
     * 查询条件
     * @param condition     查询条件
     * @param args          参数值
     * @return
     */
    List<${className}> query(String condition, Object...args);

    /**
     * 查询对象
     * @param condition
     * @param args
     * @return
     */
    ${className} queryObject(String condition, Object...args);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);
}
