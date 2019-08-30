package com.WareHouseManagement.spring.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order_Details {

		@Id
		@GeneratedValue
		private int order_Id;
		private int item_code;
		private int item_quantity;
		private String manufacturer_name;
		private String manager_name;
		private String status;
		public int getOrder_Id() {
			return order_Id;
		}
		public void setOrder_Id(int order_Id) {
			this.order_Id = order_Id;
		}
		
		public int getItem_code() {
			return item_code;
		}
		public void setItem_code(int item_code) {
			this.item_code = item_code;
		}
		public int getItem_quantity() {
			return item_quantity;
		}
		public void setItem_quantity(int item_quantity) {
			this.item_quantity = item_quantity;
		}
		public String getManager_name() {
			return manager_name;
		}
		public void setManager_name(String manager_name) {
			this.manager_name = manager_name;
		}
		public String getManufacturer_name() {
			return manufacturer_name;
		}
		public void setManufacturer_name(String manufacturer_name) {
			this.manufacturer_name = manufacturer_name;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "Order_Details [order_Id=" + order_Id + ", item_code=" + item_code + ", item_quantity="
					+ item_quantity + ", manufacturer_name=" + manufacturer_name + ", manager_name=" + manager_name
					+ ", status=" + status + "]";
		}
		
		
		
}
