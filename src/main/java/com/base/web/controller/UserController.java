package com.base.web.controller;

import com.base.web.beans.User;
import com.base.web.iservice.IUserSV;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String getTest(){
		List list = new ArrayList<>();
		PageHelper.startPage(1,10);
		PageInfo p =new PageInfo (list);
		Long id = 2102L;
		User user = userSV.getUserById(id);
		return user.toString();
	}

	@RequestMapping("/list")
	public String getUserList(){
		PageHelper.startPage(2,100);
		List list = userSV.getUserList();
		PageInfo p =new PageInfo (list);
		return  p.toString();
	}

}
