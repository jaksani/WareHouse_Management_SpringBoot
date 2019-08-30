package com.WareHouseManagement.spring.Services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.WareHouseManagement.spring.Entities.Customer_Details;
import com.WareHouseManagement.spring.Entities.Item_Details;
import com.WareHouseManagement.spring.Entities.Order_Details;
import com.WareHouseManagement.spring.Entities.Purchase_Details;
import com.WareHouseManagement.spring.Repository.Customer_Details_Interface;
import com.WareHouseManagement.spring.Repository.Item_Details_Interface;
import com.WareHouseManagement.spring.Repository.Order_Details_Interface;
import com.WareHouseManagement.spring.Repository.Purchase_Details_Interface;

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
				stock=stock-item_quantity;
				item.setStock(stock);
				item_interface.save(item);
				int discount = 0;
				if(item_quantity>=10 && item_quantity<=100)
					discount=(item_price*10)/100;
				else if(item_quantity>100)
					discount=(item_price*20)/100;
				item_price=(item_price*item_quantity)-discount;
				return item_price;
		}
		return 0;
	}

	public void deleteItem(Item_Details itemDetails) {
		// TODO Auto-generated method stub
		Item_Details item=item_interface.findById(itemDetails.getItem_code()).get();
		item_interface.delete(item);
	}

	public String updatePrice(Item_Details itemDetails) {
		// TODO Auto-generated method stub
		Item_Details item=item_interface.findById(itemDetails.getItem_code()).get();
		item.setItem_price(itemDetails.getItem_price());
		item_interface.save(item);
		return "Price Updated";
	}

	public Order_Details placeOrder(Order_Details orderDetails) {
		// TODO Auto-generated method stub
		return order_interface.save(orderDetails);
		
	}

	public Purchase_Details billing(Purchase_Details purchaseDetails) {
		// TODO Auto-generated method stub
		if(customer_interface.existsById(purchaseDetails.getCustomer_code()))
		{
			
			int totalPrice=WMService.discountItem(purchaseDetails.getItem_code(),purchaseDetails.getQuantity());	
			
			LocalDate date=LocalDate.now();
			Purchase_Details purchase=new Purchase_Details();
			purchase.setCustomer_code(purchaseDetails.getCustomer_code());
			purchase.setDate_of_purchase(date);
			purchase.setItem_code(purchaseDetails.getItem_code());
			purchase.setQuantity(purchaseDetails.getQuantity());
			purchase.setPurchase_amount(totalPrice);
			
			Purchase_Details purchaseDone=purchase_interface.save(purchase);
			
			
			return purchaseDone;
		}
		return null;
	}

	public ArrayList<Purchase_Details> listPurchase(LocalDate date) {
		// TODO Auto-generated method stub
		return purchase_interface.getPurchaseList(date);
	}

	public ArrayList<Order_Details> getOrders(String username) {
		// TODO Auto-generated method stub
		return order_interface.getListMyOrders(username);
	}

	public Customer_Details getcustomerDetails(int customer_code) {
		// TODO Auto-generated method stub
		return customer_interface.findById(customer_code).get();
	}

	public Order_Details orderCancel(Order_Details orderDetails) {
		Order_Details orderDone=order_interface.findById(orderDetails.getOrder_Id()).get();
		if(orderDetails.getStatus().equals("Canceled"))
		{
			orderDone.setStatus("Canceled");
			return orderDone;
		}
		return null;
	}

}
