package com.revanwang.wms.dao;

import com.revanwang.wms.domain.ProductStock;

public interface IProductStockDAO extends IGenericDAO<ProductStock> {

    /**
     * 获取库存对象
     * @param productId 商品对象id
     * @param depotId   仓库对象id
     * @return
     */
    ProductStock queryByProductAndDepot(Long productId, Long depotId);
}
