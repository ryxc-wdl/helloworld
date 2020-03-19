package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
     private   IUserDao userDao;

//查询用户
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    //新建保存用户

    @Override
    public void save(UserInfo userInfo) throws Exception {
       userDao.save(userInfo);
    }
    //根据id查询用户
    @Override
    public UserInfo findById(String id) throws Exception {
        return null;
    }
    //根据username查询用户
    @Override
    public UserInfo findByUsername(String username) throws Exception {
        return null;
    }
    //spring-security userdetailservice接口方法 为了其框架能够查询验证用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserInfo userInfo=null;
        try {
            userInfo=  userDao.findByUsername(username);//返回的是userInfo 对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        //要处理userInfo  转化为 Userdetails  因为user实现了Userdetails接口
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),null);
        return user;
    }
}
