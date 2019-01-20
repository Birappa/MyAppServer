package com.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.myapp.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private MongoTemplate mongoTemplate;// new MongoTemplate(new MongoClient("localhost",27017), "mydb");
	
	@Override
	public User save(User user) {
		
		return mongoTemplate.save(user);
	}

	@Override
	public List<User> findAll() {
		
		return mongoTemplate.findAll(User.class);
	}

	@Override
	public void delete(int id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), User.class);

	}

	@Override
	public User findOne(String username) {
		
		return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), User.class);
	}

	@Override
	public User findById(int id) {
		
		return mongoTemplate.findById(id, User.class);
	}

	@Override
	public User update(User user) {
		
		return mongoTemplate.save(user);
	}

}
