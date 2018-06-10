package com.zing.queryparam;

import com.zing.base.queryparam.BaseQueryparam;

/**
 * 收藏表查询参数
 */
public class CollectioninfoQueryparam extends BaseQueryparam{

    private Integer userId;//用户表主键

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
