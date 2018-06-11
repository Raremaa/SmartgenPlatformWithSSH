package com.zing.json;

import java.util.List;

/**
 * 结果封装类
 * success 默认为false
 * total 默认为0
 * page默认为1
 * pageSize默认为0
 */
public class JsonResult<T> {
    private Integer total = 0;//总记录数
    private Integer page =1;//当前页数
    private Integer pageSize =0;//每页最大记录数
    private Boolean success = false;//操作成功标记 true-成功 false-失败
    private String msg;//消息
    private List<T> datas;//数据

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        if (total == null) {
            this.total = 1;
        } else {
            this.total = total;
        }
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page == null) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            this.pageSize = this.total;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        if(success == null){
            this.success = true;
        }else{
            this.success = success;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
