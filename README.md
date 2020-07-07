
# PROBLEM STATEMENT:

### Create a web application for a warehouse, which maintains the item details, customer details and purchase details.
#### The following are the information which need to be stored
  - Item information such as  item_code, item_name, item_price, stock
  - Customer information such as customer_code, customer_name, phone_number and address.
  - Purchase details such as transaction_id, customer_code, item_code, date_of_purchase,quantity_pur (i.e quantity purchased)
 Additional Requirements:-
  - Duplicate item and customer details should not be there.
  - price, stock, quantity_purchase cannot be 0 or in negative.
  - Write a method that should take customer_code and should display customer_name, phone_number and address. Important: If the customer id is not there , error message should       be displayed.
  - Write a method that should take the item_code as input and will return the price after calculating the new price:
  - If price<10                                    No concession i.e the same price will be returned.
  - If price>=10 and price<=100        10% of price i.e if the price is 100 then the price after   concession is 90.
  - Else                                              20% of price
  - Given a date, list all the purchase transactions by all customers who have purchased items in the shop
  - Once a purchase is made, all necessary updates should be done.

# INTRODUCTION:

A warehouse is a place where raw materials or manufactured goods are stored prior to sale. Warehouse Management System is a web application developed to automate the process of monitoring the warehouse. The web application has two users - a manufacturer and an admin(warehouse manager), the admin is responsible for looking after all the operations in the warehouse. A customer does not have access to the website, he/she has to make the purchase with the warehouse manager who in turn generates an invoice. The warehouse manager also has an inventory of the products in the warehouse and the list of customers the warehouse caters to. The web application allows the warehouse manager to automate the process of placing an order for the customer, maintaining a list of customers, removing a product from the inventory when it is not available anymore or adding a product to the inventory when a new product arrives. The warehouse manager places an order for new products or replenishes stock of the available products accordingly to a manufacturer. For now we have considered that the warehouse is responsible for selling one product and hence has one manufacturer. The manufacturer is allowed to accept or reject the order, the admin is notified of the status of the purchase order. The manufacturer has a view which enables him to see the list of purchase orders made by the warehouse manager. 

### TECHNOLOGIES USED:
	
	Angular,
	SpringBoot,
	MY SQL.

### DATABASE TABLES:
	
	- User
	- Item Details
	- Purchase Details
	- Customer Details
	- Transaction Details
