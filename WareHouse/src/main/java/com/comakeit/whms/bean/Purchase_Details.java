package com.comakeit.whms.bean;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Purchase_Details 
{
	
	@Id
	@GeneratedValue
	private int transaction_id;
	private int customer_code;
	private int item_code;
	private int quantity;
	private LocalDate date_of_purchase;
	private double purchase_amount;
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public int getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(int customer_code) {
		this.customer_code = customer_code;
	}
	public int getItem_code() {
		return item_code;
	}
	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDate_of_purchase() {
		return date_of_purchase;
	}
	public void setDate_of_purchase(LocalDate date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}
	public double getPurchase_amount() {
		return purchase_amount;
	}
	public void setPurchase_amount(double purchase_amount) {
		this.purchase_amount = purchase_amount;
	}
	@Override
	public String toString() {
		return "Purchase_Details [transaction_id=" + transaction_id + ", customer_code=" + customer_code
				+ ", item_code=" + item_code + ", quantity=" + quantity + ", date_of_purchase=" + date_of_purchase
				+ ", purchase_amount=" + purchase_amount + "]";
	}
	
	
	
	
	
}
