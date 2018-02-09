package com.cmos.web.beans.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典主表
 * Created by Administrator on 2018/2/5.
 */
@Data
public class SysDictMain implements Serializable {


    private Long id;//id
    private String dictTypeCode;//唯一编码
    private String dictValue;//需要设置的值
    private Integer dictOrd;//排序
    private String memo;
}
