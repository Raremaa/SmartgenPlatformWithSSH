package com.zing.dao.impl;

import com.zing.dao.CreativeprojectDao;
import com.zing.pojo.Creativeproject;
import com.zing.queryparam.CreQueryParam;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
     * 基于创意项目表通用查询接口数据
     */
    public List<Creativeproject> getCreativeprojectList(CreQueryParam creQueryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Creativeproject>>() {
            @Override
            public List<Creativeproject> doInHibernate(Session session) throws HibernateException {
                String hql = "SELECT new Creativeproject (id,userId,creprojectTitle, " +
                        "creprojectContent,creprojectLabel,creprojectPicture, " +
                        "creprojectVideo,creprojectPlan,creprojectClassify, " +
                        "creprojectState,creprojectPraise,creprojectModifyTime, " +
                        "creprojectReleaseTime,creprojectEvaluateTime, " +
                        "creprojectEvaluateResult,creprojectEvaluateOpinion) " +
                        "FROM Creativeproject WHERE 1=1";
                if(creQueryParam.getCondition() != null){
                    hql += (" and " + creQueryParam.getCondition());
                }
                if(creQueryParam.getOrderBy() != null){
                    hql += (" order by "+creQueryParam.getOrderBy());
                    if(creQueryParam.getOrderByInTurn() != null){
                        hql += (" "+creQueryParam.getOrderByInTurn());
                    }
                }
                Query query = session.createQuery(hql);
                if(creQueryParam.getPageSize()!= null){
                    if(creQueryParam.getPage() != null){
                        //(当前页数-1)*每页条数
                        query.setFirstResult((creQueryParam.getPage()-1)*creQueryParam.getPageSize());
                        query.setMaxResults(creQueryParam.getPageSize());
                    }else{
                        query.setFirstResult(0);
                        query.setMaxResults(creQueryParam.getPageSize());
                    }
                }
                List<Creativeproject> list = query.list();
                return list;
            }
        });
    }

    @Override
    /**
     * 更新非null字段
     */
    public Integer update(Creativeproject creativeproject) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Creativeproject.class);
        dc.add(Restrictions.eq("id",creativeproject.getId()));
        List<Creativeproject> list = (List<Creativeproject>) this.getHibernateTemplate().findByCriteria(dc);
        if(list.size() == 0){
            return 0;//没有待修改的字段
        }
        Creativeproject temp = list.get(0);
        if(creativeproject.getUserId() != null){
            temp.setUserId(creativeproject.getUserId());
        }
        if(creativeproject.getCreprojectTitle() != null){
            temp.setCreprojectTitle(creativeproject.getCreprojectTitle());
        }
        if(creativeproject.getCreprojectContent() != null){
            temp.setCreprojectContent(creativeproject.getCreprojectContent());
        }
        if(creativeproject.getCreprojectLabel() != null){
            temp.setCreprojectLabel(creativeproject.getCreprojectLabel());
        }
        if(creativeproject.getCreprojectPicture() != null){
            temp.setCreprojectPicture(creativeproject.getCreprojectPicture());
        }
        if(creativeproject.getCreprojectVideo() != null){
            temp.setCreprojectVideo(creativeproject.getCreprojectVideo());
        }
        if(creativeproject.getCreprojectPlan() != null){
            temp.setCreprojectPlan(creativeproject.getCreprojectPlan());
        }
        if(creativeproject.getCreprojectClassify() != null){
            temp.setCreprojectClassify(creativeproject.getCreprojectClassify());
        }
        if(creativeproject.getCreprojectState() != null){
            temp.setCreprojectState(creativeproject.getCreprojectState());
        }
        if(creativeproject.getCreprojectPraise() != null){
            temp.setCreprojectPraise(creativeproject.getCreprojectPraise());
        }
        if(creativeproject.getCreprojectModifyTime() != null){
            temp.setCreprojectModifyTime(creativeproject.getCreprojectModifyTime());
        }
        if(creativeproject.getCreprojectReleaseTime() != null){
            temp.setCreprojectReleaseTime(creativeproject.getCreprojectReleaseTime());
        }
        if(creativeproject.getCreprojectEvaluateTime() != null){
            temp.setCreprojectEvaluateTime(creativeproject.getCreprojectEvaluateTime());
        }
        if(creativeproject.getCreprojectEvaluateOpinion() != null){
            temp.setCreprojectEvaluateOpinion(creativeproject.getCreprojectEvaluateOpinion());
        }
        if(creativeproject.getCreprojectEvaluateResult() != null){
            temp.setCreprojectEvaluateResult(creativeproject.getCreprojectEvaluateResult());
        }
        this.getHibernateTemplate().update(temp);
        return 1;
    }

    @Override
    /**
     * 根据Id查询
     */
    public Creativeproject getById(Integer id) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Creativeproject.class);
        dc.add(Restrictions.eq("id",id));
        List<Creativeproject> list = (List<Creativeproject>) this.getHibernateTemplate().findByCriteria(dc);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }
}
