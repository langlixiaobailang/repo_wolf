package com.cmos.web.beans.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典主表
 * Created by Administrator on 2018/2/5.
 */
@Data
public class SysDictDetail implements Serializable {

    private Long id;//id
    private Long dictMainId;//字典主表id
    private String dictCode;//code值
    private String dictValue;//需要设置和展示的值
    private String memo;//备注

}
