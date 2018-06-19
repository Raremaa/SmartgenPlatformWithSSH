package com.zing.dao.impl;

import com.zing.dao.CollectioninfoDao;
import com.zing.pojo.Collectioninfo;
import com.zing.pojo.Product;
import com.zing.pojo.User;
import com.zing.queryparam.CollectioninfoQueryParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("collectioninfoDao")
public class CollectioninfoDaoImpl extends HibernateDaoSupport implements CollectioninfoDao {
    @Override
    /**
     * 添加收藏信息
     * @return 1-成功 0-失败(收藏信息为空)
     */
    public Integer save(Collectioninfo collectioninfo) throws Exception {
        if (collectioninfo == null){
            return 0;
        }
        this.getHibernateTemplate().save(collectioninfo);
        return 1;
    }

    @Override
    /**
     * 查询
     * 这里是默认查询所有内容 后续需优化！ 效率较低
     * 基于收藏表通用查询接口数据
     * 这里报错 后续检查问题
     */
    public List<Object[]> getList(CollectioninfoQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Object[]>>() {
            @Override
            public List<Object[]> doInHibernate(Session session) throws HibernateException {
                String hql ="from Collectioninfo where 1=1";
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
                List<Object[]> list = query.list();
                return list;
            }
        });
    }

    @Override
    /**
     * 根据条件查找符合条件的数据条数
     */
    public Long getCount(CollectioninfoQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                String hql = "select count (*) from Collectioninfo where 1=1";
                System.err.println(hql);
                if (queryParam.getCondition() != null){
                    hql += (" and " + queryParam.getCondition());
                }
                Query query = session.createQuery(hql);
                return (Long) query.uniqueResult();
            }
        });
    }

    /**
     * 根据用户id获取用户收藏商品列表
     */
    @Override
    public List<Product> getProductByUserId(final Integer userId) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Product>>() {
            @Override
            public List<Product> doInHibernate(Session session) throws HibernateException {
                String hql = "select c.product from Collectioninfo c where 1=1 and c.user.id ="+userId;
                Query query = session.createQuery(hql);
                List<Product> list = query.list();
                List<Product> result = new ArrayList<Product>(0);
                for(Product p:list){
                    Product pt = new Product();
                    pt.setId(p.getId());
                    pt.setProductName(p.getProductName());
                    pt.setProductOneMsg(p.getProductOneMsg());
                    pt.setProductPrice(p.getProductPrice());
                    pt.setProductPicture(p.getProductPicture());
                    result.add(pt);
                }
                return result;
            }
        });
    }


    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
