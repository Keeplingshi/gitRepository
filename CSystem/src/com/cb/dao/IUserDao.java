package com.cb.dao;

import java.util.List;

import com.cb.entity.User;

public interface IUserDao {
	
	public List<User> doGetFilterList();
}
