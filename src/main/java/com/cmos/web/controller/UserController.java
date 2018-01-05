package com.cmos.web.controller;

import com.cmos.base.common.MD5Helper;
import com.cmos.base.controller.IController;
import com.cmos.base.result.Result;
import com.cmos.web.beans.User;
import com.cmos.web.iservice.IUserSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends IController{
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserSV userSV;

	@RequestMapping(value = "/get-user",method = RequestMethod.GET)
	public Result<Object> getTest(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG);
		User curruser = this.getLoginUser(request);
		if(curruser == null){
			logger.info("请重新登录");
		}
		Long id = 2103L;
		Map<String,Object> paramMap = new HashMap<>();
		try {
			paramMap.put("id",id);
			Map<String,Object> map = userSV.selectByMap(paramMap);
			result.setReturnCode(this.SUCCESS);
			result.setReturnMessage(this.SELECT_SUCCESS_MO_MSG);
			result.setObject(map);
			return result;
		} catch (Exception e) {
			logger.info(this.SELECT_ERROR_MO_MSG);
		}
		return null;
	}
	/**
	 * 根据参数获取列表
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping("/get-user-list")
	public Result<Object> getUserList(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG);
		try {
			PageHelper.startPage(this.pageNum,this.pageSize);
			PageHelper.orderBy("user_id desc");
			List<Map<String,Object>> list = userSV.getListByMap(params);
			PageInfo p = new PageInfo (list);
			result.setReturnCode(this.SUCCESS);
			result.setReturnMessage(this.SELECT_SUCCESS_MSG);
			result.setObject((Object)p);
			return result;
		} catch (Exception e) {
			logger.info(this.SELECT_ERROR_MSG);
		}
		return  null;
	}

}
