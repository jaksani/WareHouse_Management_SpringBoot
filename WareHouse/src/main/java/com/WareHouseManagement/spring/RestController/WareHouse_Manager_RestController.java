package com.WareHouseManagement.spring.RestController;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WareHouseManagement.spring.Entities.Customer_Details;
import com.WareHouseManagement.spring.Entities.Item_Details;
import com.WareHouseManagement.spring.Entities.Order_Details;
import com.WareHouseManagement.spring.Entities.Purchase_Details;
import com.WareHouseManagement.spring.Services.WareHouse_Manager_Service;

@RestController
public class WareHouse_Manager_RestController {
	
	@Autowired
	WareHouse_Manager_Service wareHouseManagerService;
	
	@RequestMapping("rest_viewItems")
	@PostMapping
	public ArrayList<Item_Details> viewItems()
	{
		return wareHouseManagerService.getItems();
	}
	
	@RequestMapping("rest_customerRegister")
	@PostMapping
	public Customer_Details customerRegister(@RequestBody Customer_Details customerDetails)
	{
		return wareHouseManagerService.createCustomer(customerDetails);
	}
	
	
	@RequestMapping("rest_DeleteItem/{item_code}")
	@DeleteMapping
	public void DeleteItem(@PathVariable int item_code)
	{
		Item_Details itemDetails =new Item_Details();
		itemDetails.setItem_code(item_code);
		wareHouseManagerService.deleteItem(itemDetails);
	}
	
	@RequestMapping("rest_UpdatePrice")
	@PostMapping
	public Item_Details UpdatePrice(@RequestBody Item_Details itemDetails)
	{
		return wareHouseManagerService.updatePrice(itemDetails);
	}
	
	
	@RequestMapping("rest_PlaceOrder")
	@PostMapping
	public Order_Details PlaceOrder(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.placeOrder(orderDetails);
	}
	
	@RequestMapping("rest_OrderCancel")
	@PostMapping
	public Order_Details OrderCancel(@RequestBody Order_Details orderDetails)
	{
		return wareHouseManagerService.orderCancel(orderDetails);
	}
	
	@RequestMapping("rest_Billing")
	@PostMapping
	public Purchase_Details Billing(@RequestBody Purchase_Details purchaseDetails)
	{
		return wareHouseManagerService.billing(purchaseDetails);
	}
	
	@RequestMapping("rest_ListPurchase/{date}")
	@PostMapping
	public ArrayList<Purchase_Details> ListPurchase(@PathVariable("date") String date)
	{
		return wareHouseManagerService.listPurchase(LocalDate.parse(date));
	}
	
	@RequestMapping("rest_myOrders/{username}")
	@GetMapping
	public ArrayList<Order_Details> viewOrders(@PathVariable("username") String username)
	{
		return wareHouseManagerService.getOrders(username);
	}
	
	
	@RequestMapping("rest_getCustomerDetails/{customer_code}")
	@GetMapping
	public Customer_Details customerDetails(@PathVariable("customer_code") int customer_code)
	{
		return wareHouseManagerService.getcustomerDetails(customer_code);
	}
}
