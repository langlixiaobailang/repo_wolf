package com.cmos.web.controller.sys;

import com.cmos.web.annotation.LoggerManager;
import com.cmos.web.common.enums.LogType;
import com.cmos.web.common.identifyCode.VerifyCode;
import com.cmos.web.common.result.Result;
import com.cmos.web.common.utils.MD5Helper;
import com.cmos.web.iservice.sys.ISysUserSV;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

@RestController
@RequestMapping(value = "/login")
public class LoginController extends IController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ISysUserSV sysUserSV;

	/**
	 * 登录校验,验证登录用户
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-check",method = RequestMethod.POST)
	@LoggerManager(type = LogType.LOGIN,module = "用户",description = "用户登录")
	public Result<Object> loginCheck(@RequestParam Map<String, Object> params, HttpServletRequest request) throws Exception{
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(params.get("loginName")+"",
				MD5Helper.getMD5(params.get("password")+""));
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);//验证
			if (currentUser.isAuthenticated()){
				logger.info(" ********** user identification success **********");
				result.setReturnCode(this.SUCCESS);
				result.setReturnMessage("登录成功");
				return result;
			 }
		  }catch (Exception ex){
			throw ex;
		 }
		return result;
	}
	/**
	 * 退出系统
	 * @return 导航到
	 */
	@RequestMapping(value = "/login-out",method = RequestMethod.POST)
	@LoggerManager(type = LogType.LOGINOUT)
	public Result<Object> loginOut(@RequestParam Map<String, Object> params, HttpServletRequest request) throws Exception{
		Result<Object> result = new Result<>(this.ERROR,this.GETPARAM_ERROR_MSG,this.object);
		request.getSession().removeAttribute("loginUser");
		result.setReturnCode(this.SUCCESS);
		result.setReturnMessage("退出成功！");
		return result;
	}

	public static void main(String[] args) {
		try{
			VerifyCode verifyCode = new VerifyCode();
			BufferedImage bi = verifyCode.getImage();
			System.out.println(verifyCode.getText());
			File aa = ResourceUtils.getFile("classpath:public");
			System.out.println(aa);
			VerifyCode.output(bi, new FileOutputStream(aa+"/b.jpg"));
		}catch (Exception  e){
              logger.error("_____",e);
		}

	}
}
