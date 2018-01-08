package com.cmos.web.controller.user;

import com.cmos.base.controller.IController;
import com.cmos.base.result.Result;
import com.cmos.web.beans.user.User;
import com.cmos.web.iservice.user.IUserSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@RequestMapping(value = "/get-user")
	public Result<Object> getTest(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			Map<String,Object> paramMap = new HashMap<>();
			User loginUser = this.getLoginUser(request);
			if(loginUser == null){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage(this.LOGIN_OUT_MSG);
				return result;
			}
			paramMap.put("id",2103L);
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
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
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
