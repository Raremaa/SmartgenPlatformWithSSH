package com.zing.dao.impl;

import com.zing.dao.UserDao;
import com.zing.pojo.User;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }


    @Override
    /**
     * 根据id查询
     */
    public User getUserById(Serializable id) throws Exception {
        return this.getHibernateTemplate().get(User.class,id);
    }
}
