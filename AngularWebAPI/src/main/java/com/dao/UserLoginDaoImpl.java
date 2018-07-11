package com.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model.UserLogin;
@Repository
public class UserLoginDaoImpl extends GenericDaoImpl<UserLogin, Integer> implements UserLoginDao {
@Autowired
	public UserLoginDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory, UserLogin.class);
		// TODO Auto-generated constructor stub
	}

}
