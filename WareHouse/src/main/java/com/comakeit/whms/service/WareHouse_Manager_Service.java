package com.comakeit.whms.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comakeit.whms.bean.Customer_Details;
import com.comakeit.whms.bean.Item_Details;
import com.comakeit.whms.bean.Order_Details;
import com.comakeit.whms.bean.Purchase_Details;
import com.comakeit.whms.repository.Customer_Details_Repository;
import com.comakeit.whms.repository.Item_Details_Repository;
import com.comakeit.whms.repository.Order_Details_Repository;
import com.comakeit.whms.repository.Purchase_Details_Repository;

@Component
@EnableAutoConfiguration
@Service
public class WareHouse_Manager_Service {
	
	@Autowired
	WareHouse_Manager_Service WMService;
	
	@Autowired
	Item_Details_Repository itemRepository;
	
	@Autowired
	Customer_Details_Repository customerRepository;

	@Autowired
	Order_Details_Repository orderRepository;
	
	@Autowired
	Purchase_Details_Repository purchaseRepository;
	
	public ArrayList<Item_Details> getItems() {
		// TODO Auto-generated method stub
		ArrayList<Item_Details> itemList= (ArrayList<Item_Details>) itemRepository.findAll();
		return itemList;
	}
	
	public Customer_Details createCustomer(Customer_Details customerDetails)
	{
		return customerRepository.save(customerDetails);		
	}

	public int discountItem(int item_code,int item_quantity) {
		
		if(itemRepository.existsById(item_code))
		{
			Item_Details item=itemRepository.findById(item_code).get();
				int item_price = item.getItem_price();
				int stock=item.getStock();
				if(stock<item_quantity)
				{
					return -1;
				}
				else
				{
					stock=stock-item_quantity;
					item.setStock(stock);
					itemRepository.save(item);
					int discount = 0;
					if(item_quantity>=10 && item_quantity<=100)
						discount=(item_price*10)/100;
					else if(item_quantity>100)
						discount=(item_price*20)/100;
					item_price=(item_quantity*(item_price-discount));
					return item_price;
				}
		}
		return 0;
	}

	public Item_Details deleteItem(Integer item_code) {
		// TODO Auto-generated method stub
		if(itemRepository.existsById(item_code))
		{
			Item_Details item=itemRepository.findById(item_code).get();
			itemRepository.delete(item);
			return item;
		}
		return null;
	}

	public Item_Details updatePrice(Item_Details itemDetails) {
		// TODO Auto-generated method stub
		if(customerRepository.existsById(itemDetails.getItem_code()))
		{
			Item_Details item=itemRepository.findById(itemDetails.getItem_code()).get();
			item.setItem_price(itemDetails.getItem_price());
			itemRepository.save(item);
			return item;
		}
		return null;
	}

	public Order_Details placeOrder(Order_Details orderDetails) {
		// TODO Auto-generated method stub
		if(itemRepository.existsById(orderDetails.getItem_code()))
		{
			orderDetails.setStatus("pending");
			return orderRepository.save(orderDetails);
		}
		return null;
	}

	public Purchase_Details billing(Purchase_Details purchaseDetails) {
		// TODO Auto-generated method stub
		if(customerRepository.existsById(purchaseDetails.getCustomer_code()))
		{
			Purchase_Details purchase=new Purchase_Details();
			int totalPrice=WMService.discountItem(purchaseDetails.getItem_code(),purchaseDetails.getQuantity());	
			if(totalPrice==-1)
			{
				purchase.setQuantity(0);
				purchase.setItem_code(-1);
				return purchase;
			}
			else if(totalPrice==0)
			{
				purchase.setItem_code(0);
				purchase.setQuantity(-1);
				return purchase;
			}
			else
			{
				LocalDate date=LocalDate.now();
				
				purchase.setCustomer_code(purchaseDetails.getCustomer_code());
				purchase.setDate_of_purchase(date);
				purchase.setItem_code(purchaseDetails.getItem_code());
				purchase.setQuantity(purchaseDetails.getQuantity());
				purchase.setPurchase_amount(totalPrice);
				
				Purchase_Details purchaseDone=purchaseRepository.save(purchase);
				
				return purchaseDone;
			}
		}
		return null;
	}

	public ArrayList<Purchase_Details> listPurchase(LocalDate date) {
		// TODO Auto-generated method stub
		ArrayList<Purchase_Details> purchaseList=purchaseRepository.getPurchaseList(date);
		if(purchaseList!=null)
			return purchaseList;
		return null;
	}

	public ArrayList<Order_Details> getOrders(String username) {
		// TODO Auto-generated method stub
		ArrayList<Order_Details> orderList=orderRepository.getListMyOrders(username);
		if(orderList!=null)
			return orderList;
		return null;
	}

	public Customer_Details getcustomerDetails(int customer_code) {
		// TODO Auto-generated method stub
		
		if(customerRepository.existsById(customer_code))
			return customerRepository.findById(customer_code).get();
		return null;
	}

	public Order_Details orderCancel(Order_Details orderDetails) {
		Order_Details orderDone=orderRepository.findById(orderDetails.getOrder_Id()).get();
		if(orderDetails.getStatus().equals("Canceled"))
		{
			orderDone.setStatus("Canceled");
			orderRepository.save(orderDone);
			return orderDone;
		}
		return null;
	}

}
