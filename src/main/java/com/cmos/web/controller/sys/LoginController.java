package com.cmos.web.controller.sys;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.beans.sys.User;
import com.cmos.web.common.enums.LogType;
import com.cmos.web.common.result.Result;
import com.cmos.web.common.utils.MD5Helper;
import com.cmos.web.iservice.sys.IUserSV;
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
public class LoginController extends IController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private IUserSV userSV;

	/**
	 * 登录校验,验证登录用户
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-check",method = RequestMethod.POST)
	@LoggerManager(type = LogType.LOGIN,module = "登录",description = "用户登录")
	public Result<Object> loginCheck(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try{
			User loginUser = (User)userSV.selectByMap(params);
			if(null == loginUser || !loginUser.getLoginName().equals(params.get("loginName"))
					||!loginUser.getPassword().equals(MD5Helper.getMD5(params.get("password")+"")) ){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("用户名或密码错误！");
				return result;
			}else if(loginUser.getIfLock().equals("1")){
				result.setReturnCode(this.ERROR);
				result.setReturnMessage("当前用户被锁定 请联系管理员！");
				return result;
			}else{
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage("登录成功！");
				this.getSession(request).setAttribute("loginUser",loginUser);
				return result;
			}
		}catch (Exception e){
            logger.info(e.getMessage());
		}
		return result;
	}
	/**
	 * 退出系统
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-out",method = RequestMethod.POST)
	@LoggerManager(type = LogType.LOGINOUT)
	public Result<Object> loginOut(@RequestParam Map<String, Object> params, HttpServletRequest request){
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		this.getSession(request).removeAttribute("loginUser");
		result.setReturnCode(this.SUCCESS);
		result.setReturnMessage("退出成功！");
		return result;
	}
}
