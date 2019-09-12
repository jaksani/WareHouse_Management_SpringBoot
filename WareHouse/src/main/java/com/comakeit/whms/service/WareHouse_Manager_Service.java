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
import com.comakeit.whms.repository.Customer_Details_Interface;
import com.comakeit.whms.repository.Item_Details_Interface;
import com.comakeit.whms.repository.Order_Details_Interface;
import com.comakeit.whms.repository.Purchase_Details_Interface;

@Component
@EnableAutoConfiguration
@Service
public class WareHouse_Manager_Service {
	
	@Autowired
	WareHouse_Manager_Service WMService;
	
	@Autowired
	Item_Details_Interface item_interface;
	
	@Autowired
	Customer_Details_Interface customer_interface;

	@Autowired
	Order_Details_Interface order_interface;
	
	@Autowired
	Purchase_Details_Interface purchase_interface;
	
	public ArrayList<Item_Details> getItems() {
		// TODO Auto-generated method stub
		ArrayList<Item_Details> itemList= (ArrayList<Item_Details>) item_interface.findAll();
		return itemList;
	}
	
	public Customer_Details createCustomer(Customer_Details customerDetails)
	{
		return customer_interface.save(customerDetails);		
	}

	public int discountItem(int item_code,int item_quantity) {
		
		if(item_interface.existsById(item_code))
		{
			Item_Details item=item_interface.findById(item_code).get();
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
					item_interface.save(item);
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

	public String deleteItem(Item_Details itemDetails) {
		// TODO Auto-generated method stub
		if(item_interface.existsById(itemDetails.getItem_code()))
		{
			Item_Details item=item_interface.findById(itemDetails.getItem_code()).get();
			item_interface.delete(item);
			return "deleted";
		}
		return "null";
	}

	public Item_Details updatePrice(Item_Details itemDetails) {
		// TODO Auto-generated method stub
		if(customer_interface.existsById(itemDetails.getItem_code()))
		{
			Item_Details item=item_interface.findById(itemDetails.getItem_code()).get();
			item.setItem_price(itemDetails.getItem_price());
			item_interface.save(item);
		}
		return null;
	}

	public Order_Details placeOrder(Order_Details orderDetails) {
		// TODO Auto-generated method stub
		if(item_interface.existsById(orderDetails.getItem_code()))
		{
			return order_interface.save(orderDetails);
		}
		return null;
	}

	public Purchase_Details billing(Purchase_Details purchaseDetails) {
		// TODO Auto-generated method stub
		if(customer_interface.existsById(purchaseDetails.getCustomer_code()))
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
				
				Purchase_Details purchaseDone=purchase_interface.save(purchase);
				
				return purchaseDone;
			}
		}
		return null;
	}

	public ArrayList<Purchase_Details> listPurchase(LocalDate date) {
		// TODO Auto-generated method stub
		ArrayList<Purchase_Details> purchaseList=purchase_interface.getPurchaseList(date);
		if(purchaseList!=null)
			return purchaseList;
		return null;
	}

	public ArrayList<Order_Details> getOrders(String username) {
		// TODO Auto-generated method stub
		ArrayList<Order_Details> orderList=order_interface.getListMyOrders(username);
		if(orderList!=null)
			return orderList;
		return null;
	}

	public Customer_Details getcustomerDetails(int customer_code) {
		// TODO Auto-generated method stub
		
		if(customer_interface.existsById(customer_code))
			return customer_interface.findById(customer_code).get();
		return null;
	}

	public Order_Details orderCancel(Order_Details orderDetails) {
		Order_Details orderDone=order_interface.findById(orderDetails.getOrder_Id()).get();
		if(orderDetails.getStatus().equals("Canceled"))
		{
			orderDone.setStatus("Canceled");
			order_interface.save(orderDone);
			return orderDone;
		}
		return null;
	}

}
