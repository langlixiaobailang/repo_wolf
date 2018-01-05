package com.cmos.base.controller;

import com.cmos.base.common.MD5Helper;
import com.cmos.base.result.Result;
import com.cmos.web.iservice.user.IUserSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginController extends IController{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private IUserSV userSV;

	/**
	 * 登录校验,验证登录用户
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-check",method = RequestMethod.GET)
	public Result<Object> loginCheck(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG);
		try {
			Map<String,Object> loginUser = userSV.selectByMap(params);
			if(loginUser == null){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("该用户不存在");
				return result;
			}else if(!loginUser.get("loginName").equals(params.get("loginName"))){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("请输入正确的账号！");
				return result;
			}else if(loginUser.get("ifLock").equals("1")){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("该用户已经锁定,请联系管理员！");
				return result;
			}else if(!loginUser.get("passWord").equals(MD5Helper.getMD5(params.get("passWord")+""))){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("登录密码不正确！");
				return result;
			}else{
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage("登录成功！");
				this.getSession(request).setAttribute("loginUser",loginUser);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 退出系统
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-out",method = RequestMethod.GET)
	public Result<Object> loginOut(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG);
		this.getSession(request).removeAttribute("loginUser");
		result.setReturnCode(this.SUCCESS);
		result.setReturnMessage("退出成功！");
		return result;
	}
}
