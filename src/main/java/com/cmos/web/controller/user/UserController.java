package com.cmos.web.controller.user;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.base.controller.IController;
import com.cmos.web.base.result.Result;
import com.cmos.web.beans.user.User;
import com.cmos.web.common.enums.LogType;
import com.cmos.web.iservice.user.IUserSV;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
	/**
	 * 获取对象
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/get-user",method = RequestMethod.POST)
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
	@RequestMapping(value = "/get-user-list",method = RequestMethod.POST)
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
	 * 添加
	 * @param params
	 * @param request
	 * @return
	 */
	@LoggerManager(type = LogType.INSERT,module = "用户",description = "添加成功！")
	@RequestMapping(value = "/insert",method = RequestMethod.POST)
	public Result<Object> insert(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			User user = new User();
            if(StringUtils.isBlank(params.get("id")+"")){
				userSV.insert(user);
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage(this.INSERT_SUCCESS_MSG);
			}
			return result;
		} catch (Exception e) {
			logger.error(this.INSERT_SUCCESS_MSG,e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改
	 * @param params
	 * @param request
	 * @return
	 */
	@LoggerManager(type = LogType.INSERT,module = "用户",description = "修改成功！")
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public Result<Object> update(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			//判断id为空添加，否则是修改
			if(StringUtils.isBlank(params.get("id")+"")){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage(this.UPDATE_ERROR_MSG);
				return result;
			}
			 //先查询对象在修改
			User user = (User)userSV.selectByMap(params);
			userSV.update(user);
			result.setReturnCode(this.SUCCESS);
			result.setReturnMessage(this.UPDATE_SUCCESS_MSG);
			return result;
		} catch (Exception e) {
			logger.error(this.UPDATE_ERROR_MSG,e);
			e.printStackTrace();
		}
		return null;
	}
}
