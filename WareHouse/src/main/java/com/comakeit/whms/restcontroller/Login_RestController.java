package com.comakeit.whms.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value ="/{username}/{password}",method = RequestMethod.GET)
	public String verify(@PathVariable String username,@PathVariable String password)
	{
		User user=new User();
		user.setUser_name(username);
		user.setPassword(password);
		return repo.getUserType(user);
		
	}
}
