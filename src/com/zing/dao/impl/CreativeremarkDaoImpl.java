package com.zing.dao.impl;

import com.zing.dao.CreativeremarkDao;
import com.zing.pojo.Creativeremark;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Repository("creativeremarkDao")
public class CreativeremarkDaoImpl extends HibernateDaoSupport implements CreativeremarkDao {


    @Override
    /**
     * id查询
     */
    public Creativeremark getById(final Integer id) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Creativeremark.class);
        dc.add(Restrictions.eq("id",id));
        List<Creativeremark> list = (List<Creativeremark>) this.getHibernateTemplate().findByCriteria(dc);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    /**
     * 根据用户表主键和创意项目表主键查询
     */
    public Creativeremark getByUserAndCreProject(final Integer UserId,final Integer CreProjectId) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Creativeremark.class);
        dc.add(Restrictions.eq("userId",UserId));
        dc.add(Restrictions.eq("creprojectId",CreProjectId));
        List<Creativeremark> list = (List<Creativeremark>) this.getHibernateTemplate().findByCriteria(dc);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    /**
     * 修改
     * @return 1-正常修改 0-待修改对象不存在
     */
    public Integer update(Creativeremark creativeremark) throws Exception {
        if(creativeremark == null){
            return 0;
        }
        this.getHibernateTemplate().update(creativeremark);
        return 1;
    }

    @Override
    /**
     * 添加数据
     * @return 该记录在表中主键id
     */
    public Serializable save(Creativeremark creativeremark) throws Exception {
        return this.getHibernateTemplate().save(creativeremark);
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

}
