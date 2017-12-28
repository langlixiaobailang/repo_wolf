package com.base.web.beans;

/**
 * Created by Administrator on 2017/12/21.
 */
public class User {
    private Long userId;
    private String realName;

    public User() {
    }

    public User(Long userId, String realName) {
        this.userId = userId;
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
