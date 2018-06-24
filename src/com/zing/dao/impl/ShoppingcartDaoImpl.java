package com.zing.dao.impl;

import com.zing.dao.ShoppingcartDao;
import com.zing.pojo.Shoppingcart;
import com.zing.queryparam.ShoppingcartQueryParam;
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

@Repository("shoppingcartDao")
public class ShoppingcartDaoImpl extends HibernateDaoSupport implements ShoppingcartDao {

    /**
     * 保存
     */
    @Override
    public Serializable save(Shoppingcart shoppingcart) throws Exception {
        return this.getHibernateTemplate().save(shoppingcart);
    }

    /**
     * 修改
     */
    @Override
    public void update(Shoppingcart shoppingcart) throws Exception {
        this.getHibernateTemplate().update(shoppingcart);
    }

    /**
     * 条件查询
     * 基于购物车表通用查询接口数据
     */
    @Override
    public List<Shoppingcart> getList(ShoppingcartQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Shoppingcart>>() {
            @Override
            public List<Shoppingcart> doInHibernate(Session session) throws HibernateException {
                String hql ="from Shoppingcart where 1=1";
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
                List<Shoppingcart> list = query.list();
                return list;
            }
        });
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
