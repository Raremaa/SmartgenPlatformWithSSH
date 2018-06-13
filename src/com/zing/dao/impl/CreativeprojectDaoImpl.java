package com.zing.dao.impl;

import com.zing.dao.CreativeprojectDao;
import com.zing.pojo.Creativeproject;
import com.zing.pojo.User;
import com.zing.queryParam.CreativeprojectQueryParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("creativeprojectDao")
public class CreativeprojectDaoImpl extends HibernateDaoSupport implements CreativeprojectDao{

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    @Override
    /**
     * 条件查询
     */
    public List<Creativeproject> getCreativeprojectList(CreativeprojectQueryParam creativeprojectQueryParam) throws Exception {
          return null;
//        return this.getHibernateTemplate().execute(new HibernateCallback<List<Creativeproject>>() {
//            @Override
//            public List<Creativeproject> doInHibernate(Session session) throws HibernateException {
//                String hql = "SELECT new Creativeproject (userId,creprojectTitle, " +
//                        "creprojectContent,creprojectLabel,creprojectPicture, " +
//                        "creprojectVideo,creprojectPlan,creprojectClassify, " +
//                        "creprojectState,creprojectPraise,creprojectModifyTime, " +
//                        "creprojectReleaseTime,creprojectEvaluateTime, " +
//                        "creprojectEvaluateResult,creprojectEvaluateOpinion) " +
//                        "FROM Creativeproject WHERE 1=1";
//                if(queryParam.getCondition() != null){
//                    hql += (" and " + queryParam.getCondition());
//                }
//                if(queryParam.getOrderBy() != null){
//                    hql += (" order by "+queryParam.getOrderBy());
//                    if(queryParam.getOrderByInTurn() != null){
//                        hql += (" "+queryParam.getOrderByInTurn());
//                    }
//                }
//                Query query = session.createQuery(hql);
//                if(queryParam.getPageSize()!= null){
//                    if(queryParam.getPage() != null){
//                        //(当前页数-1)*每页条数
//                        query.setFirstResult((queryParam.getPage()-1)*queryParam.getPageSize());
//                        query.setMaxResults(queryParam.getPageSize());
//                    }else{
//                        query.setFirstResult(0);
//                        query.setMaxResults(queryParam.getPageSize());
//                    }
//                }
//                List<Creativeproject> list = query.list();
//                return list;
//            }
//        });
    }
}
