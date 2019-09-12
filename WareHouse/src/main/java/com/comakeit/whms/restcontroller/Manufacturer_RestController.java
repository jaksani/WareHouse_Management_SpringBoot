package com.comakeit.whms.restcontroller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comakeit.whms.bean.Order_Details;
import com.comakeit.whms.service.Manufacturer_Service;

@RestController
public class Manufacturer_RestController {
	
	@Autowired
	Manufacturer_Service manufactuerService;
	
	@RequestMapping(value="rest_viewOrders/{username}",method=RequestMethod.GET)
	public ArrayList<Order_Details> viewOrders(@PathVariable("username") String username)
	{
		return manufactuerService.getOrders(username);
	}
	
	@RequestMapping(value="rest_OrderReject",method=RequestMethod.PUT)
	public Order_Details orderReject(@RequestBody Order_Details orderDetails)
	{
		return manufactuerService.getOrderUpdate(orderDetails);
	}
	
	@RequestMapping(value="rest_OrderAccept",method=RequestMethod.PUT)
	public Order_Details orderAccept(@RequestBody Order_Details orderDetails)
	{
		return manufactuerService.getOrderUpdate(orderDetails);
	}
}
