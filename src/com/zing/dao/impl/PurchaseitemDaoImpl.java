package com.zing.dao.impl;

import com.zing.dao.PurchaseItemDao;
import com.zing.pojo.Purchaseitem;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

@Repository("purchaseitemDao")
public class PurchaseitemDaoImpl extends HibernateDaoSupport implements PurchaseItemDao {

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchaseitem purchaseitem) throws Exception {
        return this.getHibernateTemplate().save(purchaseitem);
    }

    /**
     * 修改
     * 成功返回1
     * 失败(为空)返回0
     */
    @Override
    public Integer update(Purchaseitem purchaseitem) throws Exception {
        if(purchaseitem == null){
            return 0;
        }
        this.getHibernateTemplate().update(purchaseitem);
        return 1;
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
