package com.WareHouseManagement.spring.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer_Details {
	
	@Id
	@GeneratedValue
	private int customer_code;
	private String customer_name;
	private String address;
	private String phone_number;
	public int getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(int customer_code) {
		this.customer_code = customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	@Override
	public String toString() {
		return "Customer_Details [customer_name=" + customer_name + ", address="
				+ address + ", phone_number=" + phone_number + "]";
	}
	
	
}
