package com.zing.dao.impl;

import com.zing.dao.PurchaseDao;
import com.zing.pojo.Purchase;
import com.zing.queryparam.PurchaseQueryParam;
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

@Repository("purchaseDao")
public class PurchaseDaoImpl extends HibernateDaoSupport implements PurchaseDao {

    /**
     * 条件查询
     * 基于订单表通用查询接口数据
     */
    @Override
    public List<Purchase> getList(PurchaseQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Purchase>>() {
            @Override
            public List<Purchase> doInHibernate(Session session) throws HibernateException {
                String hql ="select new Purchase (id,purchaseNo,purchasePaymentTime,purchasePatternOfPayment,purchaseState,purchasePrice)from Purchase where 1=1";
                if(queryParam.getCondition() != null){
                    hql += (" and " + queryParam.getCondition());
                }
                if(queryParam.getOrderBy() != null){
                    hql += (" order by "+queryParam.getOrderBy());
                    if(queryParam.getOrderByInTurn() != null){
                        hql += (" "+queryParam.getOrderByInTurn());
                    }
                }
                Query query = session.createQuery(hql);
                if(queryParam.getPageSize()!= null){
                    if(queryParam.getPage() != null){
                        //(当前页数-1)*每页条数
                        query.setFirstResult((queryParam.getPage()-1)*queryParam.getPageSize());
                        query.setMaxResults(queryParam.getPageSize());
                    }else{
                        query.setFirstResult(0);
                        query.setMaxResults(queryParam.getPageSize());
                    }
                }
                List<Purchase> list = query.list();
                return list;
            }
        });
    }

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchase purchase) throws Exception {
        return this.getHibernateTemplate().save(purchase);
    }


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
