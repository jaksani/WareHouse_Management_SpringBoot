package com.WareHouseManagement.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WareHouse_Manager_Redirect {
	
	ModelAndView modelAndView=new ModelAndView();
	
	@RequestMapping("billing")
	public ModelAndView billing()
	{
		modelAndView.addObject("status","billing");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("deleteItem")
	public ModelAndView deleteItem()
	{
		modelAndView.addObject("status","deleteItem");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	@RequestMapping("placeOrder")
	public ModelAndView placeOrder()
	{
		modelAndView.addObject("status","placeOrder");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("updatePrice")
	public ModelAndView updatePrice()
	{
		modelAndView.addObject("status","updatePrice");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("listPurchase")
	public ModelAndView listPurchase()
	{
		modelAndView.addObject("status","listPurchase");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("customerRegister")
	public ModelAndView customerRegister()
	{
		modelAndView.addObject("status","customerRegister");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("viewCustomerDetails")
	public ModelAndView customerDetails()
	{
		modelAndView.addObject("status","viewCustomerDetails");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
}
