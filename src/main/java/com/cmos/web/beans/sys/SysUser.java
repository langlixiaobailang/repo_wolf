package com.cmos.web.beans.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
@Data
public class SysUser implements Serializable{
    private Long id;
    private Long deptId;
    private String userName;
    private String loginName;
    private String password;
    private String phone;
    private String email;
    private int ifAdmin;
    private int ifLock;
    private int ifDelete;
    private Date updateDate;
    private Date  createDate;
    private String demo;
}
