package com.comakeit.whms.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.comakeit.whms.RestUrl;
import com.comakeit.whms.bean.Order_Details;

@Controller
public class Manufacturer_Controller {

	@Autowired
	RestUrl restUrl;
	
	RestTemplate restTemplate =new RestTemplate();
	ModelAndView modelAndView=new ModelAndView();
	
	@RequestMapping("viewOrders")
	public ModelAndView viewItems(@SessionAttribute("username") String username)
	{
		modelAndView.addObject("status","viewOrders");
		ResponseEntity<ArrayList<Order_Details>> orderList = restTemplate.exchange(restUrl.getrestURL()+"rest/viewOrders/"+username, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Order_Details>>() {
		});
		modelAndView.addObject("status","ListofOrders");
		modelAndView.addObject("orderList",orderList.getBody());
		modelAndView.setViewName("Manufacturer");
		return modelAndView;
	}
	
	@RequestMapping("orderAccept")
	public ModelAndView orderAccept(HttpServletRequest request,HttpServletResponse response,@SessionAttribute("username") String username)
	{
		String order_Id=(String)request.getParameter("orderId");
		int orderId=Integer.parseInt(order_Id);
		
		Order_Details orderDetails=new Order_Details();
		orderDetails.setOrder_Id(orderId);
		orderDetails.setStatus("Accepted");
		
		Order_Details orderDone=restTemplate.postForObject(restUrl.getrestURL()+"rest/OrderAccept", orderDetails, Order_Details.class);
		
		modelAndView.addObject("orderId",orderDone.getOrder_Id());
		modelAndView.addObject("status","OrderAccept");
		modelAndView.setViewName("Manufacturer");
		return modelAndView;
	}
	
	@RequestMapping("orderReject")
	public ModelAndView orderReject(HttpServletRequest request,HttpServletResponse response,@SessionAttribute("username") String username)
	{
		String order_Id=(String)request.getParameter("orderId");
		int orderId=Integer.parseInt(order_Id);
		
		Order_Details orderDetails=new Order_Details();
		orderDetails.setOrder_Id(orderId);
		orderDetails.setStatus("Rejected");
		
		Order_Details orderDone=restTemplate.postForObject(restUrl.getrestURL()+"rest/OrderReject", orderDetails, Order_Details.class);
		modelAndView.addObject("orderId",orderDone.getOrder_Id());
		modelAndView.addObject("status","OrderReject");
		modelAndView.setViewName("Manufacturer");
		return modelAndView;
	}
}
