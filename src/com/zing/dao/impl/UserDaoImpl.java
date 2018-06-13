package com.zing.dao.impl;

import com.zing.dao.UserDao;
import com.zing.pojo.User;
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
import java.util.Map;


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
    public User getUserById(final Serializable id) throws Exception {
//        return this.getHibernateTemplate().get(User.class,id);
        return this.getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "SELECT new map " +
                        "(userName as userName,userPhone as userPhone,userRealName as userRealName," +
                        "id as id,userPassword as userPassword,userSex as userSex,userHeadPortrait as userHeadPortrait," +
                        "userIdNumber as userIdNumber,userIdentity as userIdentity,userLocation as userLocation) " +
                        "FROM User WHERE id = ?";
                Query query = session.createQuery(hql);
                query.setParameter(0, id);
                Map map = (Map) query.uniqueResult();
                User user = null;
                if (map != null) {
                    user = new User();
                    user.setUserName((String) map.get("userName"));
                    user.setUserPhone((String) map.get("userPhone"));
                    user.setUserRealName((String) map.get("userName"));
                    user.setId((Integer) map.get("id"));
                    user.setUserPassword((String) map.get("userPassword"));
                    user.setUserSex((Integer) map.get("userSex"));
                    user.setUserHeadPortrait((String) map.get("userHeadPortrait"));
                    user.setUserIdNumber((String) map.get("userIdNumber"));
                    user.setUserIdentity((Integer) map.get("userIdentity"));
                    user.setUserLocation((String) map.get("userLocation"));
                }
                return user;
            }
        });

    }

    @Override
    /**
     * 条件查询
     * 这里未使用通用查询接口数据 后续需优化
     */
    public List<User> getUserList(final String condition) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<User>>() {
            @Override
            public List<User> doInHibernate(Session session) throws HibernateException {
                String hql = "SELECT new User(userRealName,userName,userPhone,userPassword,userSex,userHeadPortrait," +
                        "userIdNumber,userLocation,userIdentity) FROM User WHERE 1=1";
                if(condition != null){
                    hql += " and " +condition;
                }
                Query query = session.createQuery(hql);
                List<User> list = query.list();
                return list;
            }
        });
    }

    @Override
    /**
     * 保存用户信息
     * 返回序列化编号(即此条记录的主键)
     */
    public Integer save(User user) throws Exception {
        Integer id = (Integer) this.getHibernateTemplate().save(user);
        return id;
    }

    @Override
    public User login(final String userPhone,final String userPassword) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "SELECT new User(userRealName,userName,userPhone,userPassword,userSex,userHeadPortrait," +
                        "userIdNumber,userLocation,userIdentity) FROM User where userPhone = ? and userPassword = ?";
                Query query = session.createQuery(hql);
                query.setParameter(0,userPhone);
                query.setParameter(1,userPassword);
                return (User) query.uniqueResult();
            }
        });
    }

    //    @Override
//    通过Criteria进行id查询 应设置过于麻烦且可读性较差这里不适用
//    public User getUserById(Serializable id) throws Exception {
//        DetachedCriteria dc = DetachedCriteria.forClass(User.class);
//        ProjectionList plist = Projections.projectionList();
//        plist.add(Property.forName("userName"));
//        plist.add(Property.forName("userPhone"));
//        dc.setProjection(plist);
//        dc.add(Restrictions.eq("id",id));
//        List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(dc);
//        return list.get(0);
//    }
}
