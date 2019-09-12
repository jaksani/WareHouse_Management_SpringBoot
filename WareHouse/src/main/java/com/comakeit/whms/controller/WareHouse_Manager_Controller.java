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
import com.comakeit.whms.bean.Customer_Details;
import com.comakeit.whms.bean.Item_Details;
import com.comakeit.whms.bean.Order_Details;
import com.comakeit.whms.bean.Purchase_Details;

@Controller
public class WareHouse_Manager_Controller {
	
	@Autowired
	RestUrl restURl;
	
	RestTemplate restTemplate =new RestTemplate();
	ModelAndView modelAndView=new ModelAndView();
	
	@RequestMapping("viewItems")
	public ModelAndView viewItems()
	{
		modelAndView.addObject("status","viewItems");
		ResponseEntity<ArrayList<Item_Details>> itemList = restTemplate.exchange(restURl.getrestURL()+"rest_viewItems", HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Item_Details>>() {
		});
		modelAndView.addObject("itemList",itemList.getBody());
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("myOrders")
	public ModelAndView ListMyOrders(@SessionAttribute("username") String username)
	{
		modelAndView.addObject("status","ListofMyOrders");
		ResponseEntity<ArrayList<Order_Details>> orderList = restTemplate.exchange(restURl.getrestURL()+"rest_myOrders/"+username, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Order_Details>>() {
		});
		System.out.println(orderList.getBody());
		modelAndView.addObject("orderList",orderList.getBody());
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("DeleteItem")
	public ModelAndView DeleteItem(String item_code)
	{
		int itemCode=Integer.parseInt(item_code);
		Item_Details itemDetails=new Item_Details();
		itemDetails.setItem_code(itemCode);
		String result=restTemplate.postForObject(restURl.getrestURL()+"rest_DeleteItem/", itemDetails,String.class);
		if(result.equals("deleted"))
		{
			modelAndView.addObject("status","Item Deleted");
		}
		else
			modelAndView.addObject("status","itemNotFound");
		modelAndView.addObject("id",item_code);
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("Billing")
	public ModelAndView Billing(int customer_code,int item_code,int item_quantity)
	{
		Purchase_Details purchaseDetails=new Purchase_Details();
		purchaseDetails.setCustomer_code(customer_code);
		System.out.println(customer_code);
		purchaseDetails.setItem_code(item_code);
		purchaseDetails.setQuantity(item_quantity);
		Purchase_Details purchaseDone=restTemplate.postForObject(restURl.getrestURL()+"rest_Billing", purchaseDetails, Purchase_Details.class);
		if(purchaseDone!=null)
		{
			if(purchaseDone.getItem_code()==0)
			{
				modelAndView.addObject("status","itemNotFound");
				modelAndView.addObject("id",item_code);
			}
			else if(purchaseDone.getQuantity()==0)
			{	
				modelAndView.addObject("status","Stock Insufficient");
			}
			else
			{
				modelAndView.addObject("amount",purchaseDone.getPurchase_amount());
				modelAndView.addObject("transactionId",purchaseDone.getTransaction_id());
				modelAndView.addObject("status","billingResult");
			}
		}
		else
		{
			modelAndView.addObject("status","customerNotFound");
			modelAndView.addObject("id",customer_code);
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("PlaceOrder")
	public ModelAndView PlaceOrder(@SessionAttribute("username") String manager_name,String manufacturer_name,int item_code,int item_quantity)
	{
		Order_Details orderDetails=new Order_Details();
		orderDetails.setItem_code(item_code);
		orderDetails.setItem_quantity(item_quantity);
		orderDetails.setManufacturer_name(manufacturer_name);
		orderDetails.setManager_name(manager_name);
		orderDetails.setStatus("pending");
		Order_Details orderDone=restTemplate.postForObject(restURl.getrestURL()+"rest_PlaceOrder", orderDetails, Order_Details.class);
		if(orderDone!=null)
		{
			modelAndView.addObject("status","Order Placed");
			modelAndView.addObject("orderId",orderDone.getOrder_Id());	
		}
		else
		{
			modelAndView.addObject("status","itemNotFound");
			modelAndView.addObject("id",item_code);
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("UpdatePrice")
	public ModelAndView UpdatePrice(int item_code,int item_price)
	{
		Item_Details itemDetails=new Item_Details();
		itemDetails.setItem_code(item_code);
		itemDetails.setItem_price(item_price);
		Item_Details itemDone=restTemplate.postForObject(restURl.getrestURL()+"rest_UpdatePrice", itemDetails, Item_Details.class);
		if(itemDone!=null)
		{
			modelAndView.addObject("status","Price Updated");
			modelAndView.addObject("Id",itemDone.getItem_code());
		}
		else
		{
			modelAndView.addObject("status","itemNotFound");
			modelAndView.addObject("id",item_code);
			
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	@RequestMapping("ListPurchase")
	public ModelAndView ListPurchase(String date)
	{
	
		ResponseEntity<ArrayList<Purchase_Details>> purchaseList = restTemplate.exchange(restURl.getrestURL()+"rest_ListPurchase/"+date, HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<Purchase_Details>>() {
		});
		
		if(!purchaseList.getBody().isEmpty())
		{
			modelAndView.addObject("status","ListofPurchases");
			modelAndView.addObject("purchaseList",purchaseList.getBody());
		}
		else
		{
			modelAndView.addObject("status","purchaseNotFound");
			modelAndView.addObject("date",date);
			
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	
	@RequestMapping("CustomerRegister")
	public ModelAndView CustomerRegister(String customer_name,String address,String phone_number)
	{
		Customer_Details customerDetails=new Customer_Details();
		customerDetails.setCustomer_name(customer_name);
		customerDetails.setAddress(address);
		customerDetails.setPhone_number(phone_number);
		Customer_Details customer=restTemplate.postForObject(restURl.getrestURL()+"rest_customerRegister", customerDetails, Customer_Details.class);
		if(customer!=null)
		{
			modelAndView.addObject("status","Registration SuccessFull");
			modelAndView.addObject("id",customer.getCustomer_code());
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	
	
	@RequestMapping("CustomerDetails")
	public ModelAndView CustomerDeatils(int customer_code)
	{
		Customer_Details customer=restTemplate.getForObject(restURl.getrestURL()+"rest_getCustomerDetails/"+customer_code, Customer_Details.class);
		if(customer!=null)
		{
			modelAndView.addObject("status","customerDetails");
			modelAndView.addObject("Details",customer);
		}
		else
		{
			modelAndView.addObject("status","customerNotFound");
			modelAndView.addObject("id",customer_code);
			
		}
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
	@RequestMapping("orderCancel")
	public ModelAndView orderCancel(HttpServletRequest request,HttpServletResponse response,@SessionAttribute("username") String username)
	{
		String order_Id=(String)request.getParameter("orderId");
		int orderId=Integer.parseInt(order_Id);
		
		Order_Details orderDetails=new Order_Details();
		orderDetails.setOrder_Id(orderId);
		orderDetails.setStatus("Canceled");
		
		Order_Details orderDone=restTemplate.postForObject(restURl.getrestURL()+"rest_OrderCancel", orderDetails, Order_Details.class);
		
		modelAndView.addObject("orderId",orderDone.getOrder_Id());
		modelAndView.addObject("status","OrderCancel");
		modelAndView.setViewName("WareHouse_Manager");
		return modelAndView;
	}
}
