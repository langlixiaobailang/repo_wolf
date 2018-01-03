package com.cmos.web.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
