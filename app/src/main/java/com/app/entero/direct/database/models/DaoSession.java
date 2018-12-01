package com.app.entero.direct.database.models;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.app.entero.direct.database.models.OrderDetailTable;
import com.app.entero.direct.database.models.OrderTableMaster;
import com.app.entero.direct.database.models.ProductListModelDao;

import com.app.entero.direct.database.models.OrderDetailTableDao;
import com.app.entero.direct.database.models.OrderTableMasterDao;
import com.app.entero.direct.database.models.ProductListModelDaoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig orderDetailTableDaoConfig;
    private final DaoConfig orderTableMasterDaoConfig;
    private final DaoConfig productListModelDaoDaoConfig;

    private final OrderDetailTableDao orderDetailTableDao;
    private final OrderTableMasterDao orderTableMasterDao;
    private final ProductListModelDaoDao productListModelDaoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        orderDetailTableDaoConfig = daoConfigMap.get(OrderDetailTableDao.class).clone();
        orderDetailTableDaoConfig.initIdentityScope(type);

        orderTableMasterDaoConfig = daoConfigMap.get(OrderTableMasterDao.class).clone();
        orderTableMasterDaoConfig.initIdentityScope(type);

        productListModelDaoDaoConfig = daoConfigMap.get(ProductListModelDaoDao.class).clone();
        productListModelDaoDaoConfig.initIdentityScope(type);

        orderDetailTableDao = new OrderDetailTableDao(orderDetailTableDaoConfig, this);
        orderTableMasterDao = new OrderTableMasterDao(orderTableMasterDaoConfig, this);
        productListModelDaoDao = new ProductListModelDaoDao(productListModelDaoDaoConfig, this);

        registerDao(OrderDetailTable.class, orderDetailTableDao);
        registerDao(OrderTableMaster.class, orderTableMasterDao);
        registerDao(ProductListModelDao.class, productListModelDaoDao);
    }
    
    public void clear() {
        orderDetailTableDaoConfig.clearIdentityScope();
        orderTableMasterDaoConfig.clearIdentityScope();
        productListModelDaoDaoConfig.clearIdentityScope();
    }

    public OrderDetailTableDao getOrderDetailTableDao() {
        return orderDetailTableDao;
    }

    public OrderTableMasterDao getOrderTableMasterDao() {
        return orderTableMasterDao;
    }

    public ProductListModelDaoDao getProductListModelDaoDao() {
        return productListModelDaoDao;
    }

}
