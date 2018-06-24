package com.zing.dao.impl;

import com.zing.dao.PurchaseItemDao;
import com.zing.pojo.Purchaseitem;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

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

    /**
     * 根据订单表id查询订单详情
     */
    @Override
    public List<Purchaseitem> getPurchaseitemByPurchaseId(Integer id) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Purchaseitem>>() {
            @Override
            public List<Purchaseitem> doInHibernate(Session session) throws HibernateException {
                String hql = "select new Purchaseitem (id,purchaseitemCount,purchaseitemMsg,purchaseitemSinglePrice," +
                        "purchaseitemPrice,purchaseitemName,purchaseitemPicture) from Purchaseitem p where p.purchase.id="+id;
                Query query = session.createQuery(hql);
                return query.list();
            }
        });
    }

    @Override
    public void delete(Purchaseitem purchaseitem) throws Exception {
        this.getHibernateTemplate().delete(purchaseitem);
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
