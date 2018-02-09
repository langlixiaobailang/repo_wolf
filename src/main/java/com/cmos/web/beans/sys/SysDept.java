package com.cmos.web.beans.sys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 * Created by Administrator on 2018/2/5.
 */
@Data
public class SysDept implements Serializable {

    private Long id;
    private Long parentId;//父id
    private String deptName;//部门名称
    private Integer deptOrd;//排序
    private Date createDate;//创建时间
    private String createUser;//创建人姓名
    private Integer deptState;//部门状态
    private Integer ifDelete;
    private String memo;//备注描述
}
