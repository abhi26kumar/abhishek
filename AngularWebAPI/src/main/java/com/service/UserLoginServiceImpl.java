package com.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserLoginDao;
import com.json.UserLoginJson;
import com.model.UserLogin;
@Service
@Transactional
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
	UserLoginDao userLoginDao;
	@Override
	public Object Login(HttpServletRequest request, UserLogin userLogin) {
		// TODO Auto-generated method stub
		UserLogin login=new UserLogin();
		login.setUserName("Abhishek");
		login.setUserPassword("pass@123");
		login.setUserEmailId("aman@gmail.com");
		userLoginDao.create(login);
		return "200";
	}
	@Override
	public Object getUserDetails(HttpServletRequest request, int userId) {
		// TODO Auto-generated method stub
		UserLogin userLogin=userLoginDao.get(userId);
		UserLoginJson userLoginJson=new UserLoginJson();
		userLoginJson.setUserId(userLogin.getUserId());
		userLoginJson.setUserName(userLogin.getUserName());
		userLoginJson.setUserPassword(userLogin.getUserPassword());
		userLoginJson.setUserEmailId(userLogin.getUserEmailId());
		return userLoginJson;
	}

}
