package com.cmos.web.serviceimpl;

import com.cmos.base.iservice.IServiceImpl;
import com.cmos.web.beans.User;
import com.cmos.web.dao.UserDao;
import com.cmos.web.iservice.IUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class  UserImpl<T> extends IServiceImpl<T> implements  IUserSV<T>  {
}
