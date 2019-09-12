package com.comakeit.whms.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comakeit.whms.bean.Customer_Details;
import com.comakeit.whms.bean.Item_Details;
import com.comakeit.whms.bean.Order_Details;
import com.comakeit.whms.bean.Purchase_Details;
import com.comakeit.whms.service.WareHouse_Manager_Service;

@RestController
public class WareHouse_Manager_RestController {
	
	@Autowired
	WareHouse_Manager_Service wareHouseManagerService;
	
	@RequestMapping(value="rest_viewItems",method=RequestMethod.GET)
	public ArrayList<Item_Details> viewItems()
	{
		return wareHouseManagerService.getItems();
	}
	
	@RequestMapping(value="rest_customerRegister",method=RequestMethod.POST)
	public Customer_Details customerRegister(@RequestBody Customer_Details customerDetails)
	{
		return wareHouseManagerService.createCustomer(customerDetails);
	}
	
	
	@RequestMapping(value="rest_DeleteItem",method=RequestMethod.DELETE)
	public String DeleteItem(@RequestBody Item_Details itemDetails)
	{
		String result=wareHouseManagerService.deleteItem(itemDetails);
		return result;
	}
	
	@RequestMapping(value="rest_UpdatePrice",method=RequestMethod.PUT)
	public Item_Details UpdatePrice(@RequestBody Item_Details itemDetails)
	{
		return wareHouseManagerService.updatePrice(itemDetails);
	}
	
	
	@RequestMapping(value="rest_PlaceOrder",method=RequestMethod.POST)
	public Order_Details PlaceOrder(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.placeOrder(orderDetails);
	}
	
	@RequestMapping(value="rest_OrderCancel",method=RequestMethod.PUT)
	public Order_Details OrderCancel(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.orderCancel(orderDetails);
	}
	
	@RequestMapping(value="rest_Billing",method=RequestMethod.POST)
	public Purchase_Details Billing(@RequestBody Purchase_Details purchaseDetails)
	{
		return wareHouseManagerService.billing(purchaseDetails);
	}
	
	@RequestMapping(value="rest_ListPurchase/{date}",method=RequestMethod.GET)
	public ArrayList<Purchase_Details> ListPurchase(@PathVariable("date") String date)
	{
		return wareHouseManagerService.listPurchase(LocalDate.parse(date));
	}
	
	@RequestMapping(value="rest_myOrders/{username}",method=RequestMethod.GET)
	public ArrayList<Order_Details> viewOrders(@PathVariable("username") String username)
	{
		return wareHouseManagerService.getOrders(username);
	}
	
	
	@RequestMapping(value="rest_getCustomerDetails/{customer_code}",method=RequestMethod.GET)
	public Customer_Details customerDetails(@PathVariable("customer_code") int customer_code)
	{
		return wareHouseManagerService.getcustomerDetails(customer_code);
	}
}
