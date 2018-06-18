package com.zing.dao.impl;

import com.zing.dao.CollectioninfoDao;
import com.zing.pojo.Collectioninfo;
import com.zing.queryparam.CollectioninfoQueryParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
     */
    public List<Collectioninfo> getList(CollectioninfoQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Collectioninfo>>() {
            @Override
            public List<Collectioninfo> doInHibernate(Session session) throws HibernateException {
                String hql ="from Collectioninfo c left join fetch c.user where User.id =1 ";
//                if(queryParam.getCondition() != null){
//                    hql += (" and " + queryParam.getCondition());
//                }
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
                List<Collectioninfo> list = query.list();
                return list;
            }
        });
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
