package com.userapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 3;
	static {
		users.add(new User());
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getUserId() == null) {
			user.setUserId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getUserId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(@PathVariable int id) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getUserId() == id) {
				iterator.remove();
			}
		}
		
		return null; 
		
	}
}
