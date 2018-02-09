package com.cmos.web.controller.sys;

import com.cmos.web.beans.sys.SysDept;
import com.cmos.web.common.result.Result;
import com.cmos.web.iservice.sys.ISysDeptSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/2/7.
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends  IController{

    @Autowired
    private ISysDeptSV sysDeptSV;

    @RequestMapping("/save")
    public Result<Object> saveDept( SysDept sysDept) throws Exception{
        Result<Object> r = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
        try {
            System.out.println(sysDept);
            sysDeptSV.insert(sysDept);
            r.setReturnCode(this.SUCCESS);
            r.setReturnMessage(this.INSERT_SUCCESS_MSG);
        } catch (Exception e) {
            r.setReturnCode(this.ERROR);
            r.setReturnMessage(this.INSERT_ERROR_MSG);
           throw e;
        }
        return  r;
    }



}
