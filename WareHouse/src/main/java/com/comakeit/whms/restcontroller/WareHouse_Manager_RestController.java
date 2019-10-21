package com.comakeit.whms.restcontroller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="rest/viewItems",method=RequestMethod.GET)
	public ArrayList<Item_Details> viewItems()
	{
		return wareHouseManagerService.getItems();
	}
	
	@RequestMapping(value="rest/customerRegister",method=RequestMethod.POST)
	public Customer_Details customerRegister(@RequestBody Customer_Details customerDetails)
	{
		System.out.println(customerDetails);
		return wareHouseManagerService.createCustomer(customerDetails);
	}
	
	
	@RequestMapping(value="rest/DeleteItem/{item_code}",method=RequestMethod.DELETE)
	public Item_Details DeleteItem(@PathVariable("item_code") int item_code)
	{
		return wareHouseManagerService.deleteItem(item_code);
	}
	
	@RequestMapping(value="rest/UpdatePrice",method=RequestMethod.PUT)
	public Item_Details UpdatePrice(@RequestBody Item_Details itemDetails)
	{
		return wareHouseManagerService.updatePrice(itemDetails);
	}
	
	
	@RequestMapping(value="rest/PlaceOrder",method=RequestMethod.POST)
	public Order_Details PlaceOrder(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.placeOrder(orderDetails);
	}
	
	@RequestMapping(value="rest/cancelOrder",method=RequestMethod.PUT)
	public Order_Details OrderCancel(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.orderCancel(orderDetails);
	}
	
	@RequestMapping(value="rest/Billing",method=RequestMethod.POST)
	public Purchase_Details Billing(@RequestBody Purchase_Details purchaseDetails)
	{
		return wareHouseManagerService.billing(purchaseDetails);
	}
	
	@RequestMapping(value="rest/ListPurchase/{date}",method=RequestMethod.GET)
	public ArrayList<Purchase_Details> ListPurchase(@PathVariable("date") String date)
	{
		return wareHouseManagerService.listPurchase(LocalDate.parse(date));
	}
	
	@RequestMapping(value="rest/myOrders/{username}",method=RequestMethod.GET)
	public ArrayList<Order_Details> viewOrders(@PathVariable("username") String username)
	{
		return wareHouseManagerService.getOrders(username);
	}
	
	
	@RequestMapping(value="rest/getCustomerDetails/{customer_code}",method=RequestMethod.GET)
	public Customer_Details customerDetails(@PathVariable("customer_code") int customer_code)
	{
		return wareHouseManagerService.getcustomerDetails(customer_code);
	}
}
