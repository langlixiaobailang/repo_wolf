package com.base.web.controller;

import com.base.web.beans.User;
import com.base.web.config.RedisBusiness;
import com.base.web.iservice.IUserSV;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private IUserSV userSV;
	@Autowired
	private RedisBusiness redisBusiness;

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	//@Cacheable(value="usertest")
	public String getTest(){

		Long id = 2102L;
		User user = userSV.getUserById(id);
		try {
			String  uuu = redisBusiness.get("usertime");
			System.out.println(uuu);
			redisBusiness.set("usertime",user.toString(),100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.toString();
	}

	@RequestMapping("/list")
	public String getUserList(){
		PageHelper.startPage(2,100);
		List list = userSV.getUserList();
		PageInfo p =new PageInfo (list);
		return  p.toString();
	}

	@RequestMapping("/listmap")
	public String getUserListMap(){
		PageHelper.startPage(1,100);
		List list = userSV.getUserListMap();
		PageInfo p =new PageInfo (list);
		return  p.toString();
	}

}
