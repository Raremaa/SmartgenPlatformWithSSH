package com.zing.queryparam;

/**
 * 购物车通用查询接口数据
 */
public class ShoppingcartQueryParam {
    private String condition;//条件
    private String orderBy;//排序 按什么排序
    private String orderByInTurn;//升序或降序 接受desc/DESC 或 asc/ASC
    private Integer page;//当前页数(1-n)
    private Integer pageSize;//每页显示多少数据(0-n)

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderByInTurn() {
        return orderByInTurn;
    }

    public void setOrderByInTurn(String orderByInTurn) {
        this.orderByInTurn = orderByInTurn;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
