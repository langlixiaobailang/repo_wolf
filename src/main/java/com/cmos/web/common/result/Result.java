package com.cmos.web.common.result;

/**
 * Created by Administrator on 2018/1/3.
 */

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Result<T> implements Serializable {

    public static final String OK = "0";

    public static final Result<Object> SUCCESS = new Result<Object>("0", "");

    public static class Bean implements Serializable {
        int total = 0;

        public Bean(int total) {
            this.total = total;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    private Collection<T> beans;

    private Bean bean;

    private Object object = new Object();

    private int totalCount = 0;

    private String returnMessage = "";

    private String returnCode = "";

    public Result() {
        this(null);
    }

    /**
     * 错误消息对象构造
     * @param returnCode 状态码
     * @param returnMessage 状态描述
     * */
    public Result(String returnCode, String returnMessage) {
        this(null);
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
    }

    /**
     * 错误消息对象构造
     * @param returnCode 状态码
     * @param returnMessage 状态描述
     * */
    public Result(String returnCode, String returnMessage, Object object) {
        this(null);
        this.returnCode = returnCode;
        this.returnMessage = returnMessage;
        this.object = object;
    }

    public Result(Collection<T> dataList) {
        this(dataList, dataList != null ? dataList.size() : 0);
    }

    public Result(int totalCount) {
        this((Collection<T>)null, totalCount);
    }

    /**
     * 分页结果构造
     * @param dataList 结果集
     * @param totalCount 当前查询的总记录数
     * */
    public Result(Collection<T> dataList, int totalCount) {
        this.totalCount = totalCount;
        this.returnCode = Result.OK;
        this.returnMessage = "";
        this.beans = dataList;
        this.getBean().setTotal(totalCount);
    }

    /***
     * 分页结果构造
     * @param pageResult 分页结果对象
     * */
    public Result(Page<T> pageResult) {
        this(pageResult != null ? pageResult.getResult() : null);
        this.setTotalCount(pageResult != null ? (int)pageResult.getTotal() : 0);
    }

    public Collection<T> getBeans() {
        if (this.beans == null)
            this.beans = new ArrayList<>(0);
        return beans;
    }

    public void setBeans(Collection<T> beans) {
        this.beans = beans;
    }

    public Bean getBean() {
        if (this.bean == null)
            this.bean = new Bean(0);
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
