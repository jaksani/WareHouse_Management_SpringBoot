package com.comakeit.whms.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.comakeit.whms.bean.Item_Details;
import com.comakeit.whms.bean.Order_Details;
import com.comakeit.whms.repository.Item_Details_Repository;
import com.comakeit.whms.repository.Order_Details_Repository;

@Component
@EnableAutoConfiguration
@Service
public class Manufacturer_Service {
	
	@Autowired
	Order_Details_Repository order_interface;
	
	@Autowired
	Item_Details_Repository item_interface;

	public ArrayList<Order_Details> getOrders(String username) {
		// TODO Auto-generated method stub
		return order_interface.getListofOrders(username);
	}

	public Order_Details getOrderUpdate(Order_Details orderDetails) {
		// TODO Auto-generated method stub
		Order_Details orderDone=order_interface.findById(orderDetails.getOrder_Id()).get();
		
		if(orderDetails.getStatus().equals("Accepted"))
		{
			Item_Details itemDetails=item_interface.findById(orderDone.getItem_code()).get();
			if(itemDetails!=null)
			{
				int itemStock=itemDetails.getStock();
				
			
				itemStock=itemStock+orderDone.getItem_quantity();
				
				itemDetails.setStock(itemStock);
				orderDone.setStatus("Accepted");
				
				item_interface.save(itemDetails);
				order_interface.save(orderDone);
				
				
				return orderDone;
			}
		}
		else if(orderDetails.getStatus().equals("Rejected"))
		{
			orderDone.setStatus("Rejected");
			return orderDone;
		}
		return null;
		
	}
	
}
