package com.zing.dao.impl;

import com.zing.dao.PurchaseaddressDao;
import com.zing.pojo.Purchaseaddress;
import com.zing.queryparam.PurchaseaddressQueryParam;
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
import java.io.Serializable;
import java.util.List;

@Repository("purchaseaddressDao")
public class PurchaseaddressDaoImpl extends HibernateDaoSupport implements PurchaseaddressDao {

    /**
     * 条件查询
     * 基于收货地址表通用查询接口数据
     */
    @Override
    public List<Purchaseaddress> getList(PurchaseaddressQueryParam queryParam) throws Exception {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Purchaseaddress>>() {
            @Override
            public List<Purchaseaddress> doInHibernate(Session session) throws HibernateException {
                String hql = "select new Purchaseaddress (id,puraddressIsChoice,puraddressUserName," +
                        "puraddressAddress,puraddressUserPhone,puraddressZipcode,puraddressProvince," +
                        "puraddressCity)from Purchaseaddress where 1=1";
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
                List<Purchaseaddress> list = query.list();
                return list;
            }
        });
    }

    /**
     * 保存
     */
    @Override
    public Serializable save(Purchaseaddress purchaseaddress) throws Exception {
        return this.getHibernateTemplate().save(purchaseaddress);
    }

    /**
     * 修改
     * 成功返回1 失败返回0(传参为空)
     */
    @Override
    public Integer update(Purchaseaddress purchaseaddress) throws Exception {
        if(purchaseaddress == null){
            return 0;
        }
        this.getHibernateTemplate().update(purchaseaddress);
        return 1;
    }

    /**
     * 根据id查询
     */
    @Override
    public Purchaseaddress getById(Integer id) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(Purchaseaddress.class);
        dc.add(Restrictions.eq("id",id));
        List<Purchaseaddress> list = (List<Purchaseaddress>) this.getHibernateTemplate().findByCriteria(dc);
        if(list == null){
            return null;
        }
        return list.get(0);
    }

    /**
     * 删除
     */
    @Override
    public void delete(Purchaseaddress purchaseaddress)throws Exception{
        this.getHibernateTemplate().delete(purchaseaddress);
    }
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}
