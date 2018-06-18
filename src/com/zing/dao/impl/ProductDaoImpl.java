package com.zing.dao.impl;

import com.zing.dao.ProductDao;
import com.zing.pojo.Product;
import com.zing.queryparam.ProductQueryParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {
    @Override
    /**
     * 条件查询
     * 基于产品表通用查询接口数据
     */
    public List<Product> getProductList(ProductQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Product>>() {
            @Override
            public List<Product> doInHibernate(Session session) throws HibernateException {
                String hql = "select new Product (id,productNo,productName,productPrice,productClassify,productLabel," +
                        "productPicture,productStatus,productMsg,productOriginPrice,productFreight,productCount," +
                        "productSell,productBestCount,productMiddleCount,productBadCount,productRequireMoney," +
                        "productCurrentMoney,productCountPrice,productOneMsg) " +
                        "from Product where 1=1";
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
                List<Product> list = query.list();
                return list;
            }
        });
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
