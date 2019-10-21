package com.comakeit.whms.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comakeit.whms.bean.User;
import com.comakeit.whms.exception.UnAuthorizedException;
import com.comakeit.whms.service.LoginService;

@RestController
@RequestMapping("verify")
public class Login_RestController {
	 @Autowired
	 LoginService loginRepository;
	
	@RequestMapping(value ="/login",method = RequestMethod.POST)
	public User verify(@RequestBody User loginCredentials)
	{
		
		User user=loginRepository.getUserType(loginCredentials);
		if(user==null)
		{
//			throw new UnAuthorizedException("Authorization denied");
			return null;
		}
		else
		{
			return user;
		}
		
	}
}
