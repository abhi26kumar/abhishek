package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.json.UserLoginJson;
import com.model.Employee;
import com.service.UserLoginService;

@RestController
@RequestMapping(value="/userLogin")
public class UserLoginController {
	UserLoginController()
	{
		System.out.println("ahgasdhagsdh");
	}
	@Autowired
	private UserLoginService userLoginService;
	@RequestMapping(value="/login" ,method=RequestMethod.POST)
	public @ResponseBody Object userLogin(@RequestBody UserLoginJson userLoginJson,HttpServletRequest request)
	{
		 //Object obj=userLoginService.Login(request, userLogin);
		 return "amamm";
	}

	@RequestMapping(value="/login/a1" ,method=RequestMethod.GET)
	public @ResponseBody Employee user(HttpServletRequest request)
	{
		 return new Employee();
	}
	
	@RequestMapping(value="/login/a" ,method=RequestMethod.GET)
	public @ResponseBody Object userLogin1(HttpServletRequest request)
	{
		 Object obj=userLoginService.Login(request, null);
		 return obj;
	}
	
	@RequestMapping(value="/{userId}" ,method=RequestMethod.GET)
	public @ResponseBody Object userDetails(@PathVariable Integer userId, HttpServletRequest request)
	{
		System.out.println("asdasjdhh"+userId);
		 Object obj=userLoginService.getUserDetails(request, userId);
		 UserLoginJson ul=(UserLoginJson)obj;
		 System.out.println(ul.getUserName());
		 return obj;
	}
}
