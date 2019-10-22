package com.revanwang.wms.dao.impl;


import com.revanwang.wms.dao.IProductStockDAO;
import com.revanwang.wms.domain.ProductStock;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductStockDAOImpl
        extends GenericDAOImpl<ProductStock>
        implements IProductStockDAO {


    @Override
    public ProductStock queryByProductAndDepot(Long productId, Long depotId) {
        String hql = "SELECT obj FROM ProductStock obj WHERE obj.product.id = :productId AND obj.depot.id = :depotId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql)
                .setParameter("productId", productId)
                .setParameter("depotId", depotId);
        return (ProductStock) query.uniqueResult();
    }
}
