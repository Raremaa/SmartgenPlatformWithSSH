package com.zing.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 创意项目点赞评论表
 * web项目残留表 后续需优化
 */
public class Creativeremark {
    private int Id;//主键
    private int userId;//用户表主键
    private int creprojectId;//创意项目表主键
    private String creremarkContent;//创意项目评论内容
    private Integer creremarkPraise;//创意项目点赞 0-未点赞 1-已点赞
    private Date creremarkCommentTime;//创意项目评论时间

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCreprojectId() {
        return creprojectId;
    }

    public void setCreprojectId(int creprojectId) {
        this.creprojectId = creprojectId;
    }

    public String getCreremarkContent() {
        return creremarkContent;
    }

    public void setCreremarkContent(String creremarkContent) {
        this.creremarkContent = creremarkContent;
    }

    public Integer getCreremarkPraise() {
        return creremarkPraise;
    }

    public void setCreremarkPraise(Integer creremarkPraise) {
        this.creremarkPraise = creremarkPraise;
    }

    public Date getCreremarkCommentTime() {
        return creremarkCommentTime;
    }

    public void setCreremarkCommentTime(Date creremarkCommentTime) {
        this.creremarkCommentTime = creremarkCommentTime;
    }

    @Override
    public String toString() {
        return "Creativeremark{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", creprojectId=" + creprojectId +
                ", creremarkContent='" + creremarkContent + '\'' +
                ", creremarkPraise=" + creremarkPraise +
                ", creremarkCommentTime=" + creremarkCommentTime +
                '}';
    }
}
