package com.comakeit.whms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.comakeit.whms.RestUrl;
import com.comakeit.whms.bean.User;

@Controller
@SessionAttributes("username")
public class Login_Controller {

	@Autowired
	RestUrl restURl;
	
	RestTemplate restTemplate =new RestTemplate();
	ModelAndView modelAndView=new ModelAndView();
	@RequestMapping("home")
	public String home() {
		return "index";
	}

	@RequestMapping("login")
	public ModelAndView Login1(String user_name,String password)
	{
		User loginCredentials=new User();
		loginCredentials.setUser_name(user_name);
		loginCredentials.setPassword(password);
		User user=restTemplate.postForObject(restURl.getrestURL()+"verify/login",loginCredentials, User.class);
		if(!user.getUser_type().equals("Wrong"))
		{
			if(user.getUser_type().equals("Manufacturer"))
			{
				modelAndView.addObject("username",user_name);
				modelAndView.setViewName("Manufacturer");
			}
			else if(user.getUser_type().equals("WareHouse_Manager"))
			{
				modelAndView.addObject("username",user_name);
				modelAndView.setViewName("WareHouse_Manager");
			}
		}
		else 
		{
			ModelAndView mv=new ModelAndView();
			mv.addObject("status","Wrong Credentials");
			mv.setViewName("index");
			return mv;
		}
		return modelAndView;
	}
	
	
}
