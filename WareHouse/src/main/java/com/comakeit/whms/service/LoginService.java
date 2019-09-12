package com.comakeit.whms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comakeit.whms.WareHouseApplication;
import com.comakeit.whms.bean.User;
import com.comakeit.whms.repository.UserEntity;
@Component
@EnableAutoConfiguration
@Service
public class LoginService extends WareHouseApplication {

	@Autowired
	UserEntity userEntity;

	public String getUserType(User user) {
		String password = user.getPassword();
		if(userEntity.existsById(user.getUser_name()))
		{
			User user1=userEntity.findById(user.getUser_name()).get();
		
			if(password.equals(user1.getPassword()))
			{
				return user1.getUser_type();
			}
			else
				return "Wrong";
		}
		return "Wrong";
	}
}
