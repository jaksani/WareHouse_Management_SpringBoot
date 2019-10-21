package com.comakeit.whms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comakeit.whms.WareHouseApplication;
import com.comakeit.whms.bean.User;
import com.comakeit.whms.repository.UserRepository;
@Component
@EnableAutoConfiguration
@Service
public class LoginService extends WareHouseApplication {

	@Autowired
	UserRepository userRepository;

	public User getUserType(User loginCredentials) {
		String password = loginCredentials.getPassword();
		if(userRepository.existsById(loginCredentials.getUser_name()))
		{
			User user=userRepository.findById(loginCredentials.getUser_name()).get();
		
			if(password.equals(user.getPassword()))
			{
				return user;
			}
			else
				return null;
		}
		return null;
	}
}
