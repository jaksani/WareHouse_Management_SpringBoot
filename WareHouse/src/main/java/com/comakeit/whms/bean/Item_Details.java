package com.comakeit.whms.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item_Details 
{
	
	@Id
	@GeneratedValue
	private int item_code;
	private String item_name;
	private int item_price;
	private int stock;
	
	public int getItem_code() {
		return item_code;
	}
	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock){
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Item_Details [item_code=" + item_code + ", item_name=" + item_name + ", item_price=" + item_price
				+ ", stock=" + stock + "]";
	}
	
	
	
}
