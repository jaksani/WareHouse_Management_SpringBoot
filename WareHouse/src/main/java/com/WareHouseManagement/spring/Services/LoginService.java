package com.WareHouseManagement.spring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.WareHouseManagement.spring.WareHouseApplication;
import com.WareHouseManagement.spring.Entities.User;
import com.WareHouseManagement.spring.Repository.UserEntity;
@Component
@EnableAutoConfiguration
@Service
public class LoginService extends WareHouseApplication {

	@Autowired
	UserEntity userEntity;

	public User getUserType(User user) {
		String password = user.getPassword();

		User user1=userEntity.findById(user.getUser_name()).get();
		
		if(user1!=null)
		{
			if(password.equals(user1.getPassword()))
			{
				return user1;
			}
			else
				return null;
		}
		return null;
	}
}
