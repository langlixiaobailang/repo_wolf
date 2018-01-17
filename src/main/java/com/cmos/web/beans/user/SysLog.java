package com.cmos.web.beans.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
@Data
public class User implements Serializable{
    private Long id;
    private Long deptId;
    private String userName;
    private String loginName;
    private String password;
    private String phone;
    private String email;
    private Long ifAdmin;
    private Long ifLock;
    private Long ifDelete;
    private Date updateDate;
    private Date  createDate;
    private String demo;
}
