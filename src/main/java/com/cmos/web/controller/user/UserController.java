package com.cmos.web.controller.user;

import com.cmos.base.controller.IController;
import com.cmos.base.result.Result;
import com.cmos.web.beans.user.User;
import com.cmos.web.iservice.user.IUserSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
	/**
	 * 获取对象
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get-user")
	public Result<Object> getUser(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("id",34L);
			User user = (User) userSV.selectByMap(paramMap);
			result.setReturnCode(this.SUCCESS);
			result.setReturnMessage(this.SELECT_SUCCESS_MO_MSG);
			result.setObject(user);
			return result;
		} catch (Exception e) {
			logger.error(this.SELECT_ERROR_MO_MSG,e);
			e.printStackTrace();
		}
	    return null;
	}
	/**
	 * 根据参数获取列表分页
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping("/get-user-list")
	public Result<Object> getUserList(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			PageHelper.startPage(this.pageNum,this.pageSize);
			PageHelper.orderBy("create_date desc");
			List<User> list = (List<User>)userSV.getListByMap(params);
			PageInfo p = new PageInfo (list);
			result.setReturnCode(this.SUCCESS);
			result.setReturnMessage(this.SELECT_SUCCESS_MSG);
			result.setObject((Object)p);
			return result;
		} catch (Exception e) {
			logger.error(this.SELECT_ERROR_MO_MSG,e);
			e.printStackTrace();
		}
		return  null;
	}
	/**
	 * 添加与修改
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save")
	public Result<Object> save(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			//判断id为空添加，否则是修改
			User user = new User();
            if(StringUtils.isBlank(params.get("id")+"")){
				userSV.insert(user);
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage(this.SAVE_SUCCESS_MSG);
			}else{ //先查询对象在修改
				user = (User)userSV.selectByMap(params);
				userSV.update(user);
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage(this.UPDATE_SUCCESS_MSG);
			}
			return result;
		} catch (Exception e) {
			logger.error(this.SAVE_ERROR_MSG,e);
			e.printStackTrace();
		}
		return null;
	}
}
