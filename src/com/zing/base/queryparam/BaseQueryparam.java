package com.zing.base.queryparam;

/**
 * 通用查询参数
 */
public class BaseQueryparam {

    private Integer id;//主键id
    private String condition;//条件
    private Integer pageSize; //分页每页最多显示数量
    private Integer page;//分页页数
    private String orderBy;//排序

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
