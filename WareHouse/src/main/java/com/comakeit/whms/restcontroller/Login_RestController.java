package com.comakeit.whms.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comakeit.whms.bean.User;
import com.comakeit.whms.service.LoginService;

@RestController
@RequestMapping("verify")
public class Login_RestController {
	 @Autowired
	 LoginService repo;
	
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	public User verify(@RequestBody User user)
	{
		System.out.println(user.getUser_name());
		return repo.getUserType(user);
		
	}
}
