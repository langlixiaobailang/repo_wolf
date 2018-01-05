package com.cmos.web.beans.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/12/21.
 */
@Data
public class User implements Serializable{
    private Long userId;
    private String realName;
    private String loginName;
    private String mobile;
    private Date createDate;

}
