package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.UserLogin;

public interface UserLoginService {
	
	public Object Login(HttpServletRequest request,UserLogin userLogin);
	public Object getUserDetails(HttpServletRequest request,int userId);

}
