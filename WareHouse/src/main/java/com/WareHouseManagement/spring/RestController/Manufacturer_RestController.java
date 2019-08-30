package com.WareHouseManagement.spring.RestController;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WareHouseManagement.spring.Entities.Order_Details;
import com.WareHouseManagement.spring.Services.Manufacturer_Service;

@RestController
public class Manufacturer_RestController {
	
	@Autowired
	Manufacturer_Service manufactuerService;
	
	@RequestMapping("rest_viewOrders/{username}")
	public ArrayList<Order_Details> viewOrders(@PathVariable("username") String username)
	{
		return manufactuerService.getOrders(username);
	}
	
	@RequestMapping("rest_OrderReject")
	public Order_Details orderReject(@RequestBody Order_Details orderDetails)
	{
		return manufactuerService.getOrderUpdate(orderDetails);
	}
	
	@RequestMapping("rest_OrderAccept")
	public Order_Details orderAccept(@RequestBody Order_Details orderDetails)
	{
		return manufactuerService.getOrderUpdate(orderDetails);
	}
}
