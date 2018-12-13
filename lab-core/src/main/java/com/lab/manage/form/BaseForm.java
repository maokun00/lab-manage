package com.lab.manage.form;

/**
 * Created by Chengcheng on 2018/12/12.
 */
public class BaseForm {

    protected static final long serialVersionUID = 1L;

    private Integer offset;

    private Integer limit = 10;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
