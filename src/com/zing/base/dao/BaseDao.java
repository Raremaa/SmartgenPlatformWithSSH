package com.zing.base.dao;


import java.util.List;

/**
 * 通用Dao接口
 */
public interface BaseDao<T> {
    public void update(T entity) throws Exception;
    public void updateNotNull(T entity) throws Exception;
    public void save(T entity) throws Exception;
    public void delete(Integer id) throws Exception;
    public List<T> getList(T entity) throws Exception;
    public T getModelById(Integer id) throws Exception;
}
