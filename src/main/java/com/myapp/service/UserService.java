package com.myapp.service;

import java.util.List;

import com.myapp.model.User;


public interface UserService {

	 	User save(User user);
	    List<User> findAll();
	    void delete(int id);

	    User findOne(String username);

	    User findById(int id);

	    User update(User user);
}
