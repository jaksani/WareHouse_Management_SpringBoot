package com.WareHouseManagement.spring.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.WareHouseManagement.spring.Entities.User;
import com.WareHouseManagement.spring.Services.LoginService;

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
