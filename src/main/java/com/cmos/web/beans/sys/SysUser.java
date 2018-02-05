package com.cmos.web.beans.sys;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
@Data
public class SysUser implements Serializable{
    private Long id;
    private Long deptId;
    @Size(max = 10,message = "名字最长为10个字符")
    private String userName;
    @Max(value = 10,message = "denglu账号最大10个")
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
