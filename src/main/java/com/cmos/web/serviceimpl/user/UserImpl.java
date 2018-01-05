package com.cmos.web.serviceimpl.user;

import com.cmos.base.iservice.IServiceImpl;
import com.cmos.web.iservice.user.IUserSV;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/12/21.
 */
@Service
public class  UserImpl<T> extends IServiceImpl<T> implements  IUserSV<T>  {
}
