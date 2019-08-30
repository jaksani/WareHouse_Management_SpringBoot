package com.WareHouseManagement.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.WareHouseManagement.spring.RestUrl;

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
	
		System.out.println(restURl.getrestURL());
		String userType=restTemplate.getForObject(restURl.getrestURL()+"verify/"+user_name+"/"+password, String.class);
		if(userType.equals("Manufacturer"))
		{
			modelAndView.addObject("username",user_name);
			modelAndView.setViewName("Manufacturer");
		}
		else if(userType.equals("WareHouse_Manager"))
		{
			modelAndView.addObject("username",user_name);
			modelAndView.setViewName("WareHouse_Manager");
		}
		return modelAndView;
	}
	
	
}
