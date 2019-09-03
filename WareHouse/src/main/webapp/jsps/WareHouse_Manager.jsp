<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 25%;
	background-color: #f1f1f1;
	height: 100%;
	position: fixed;
	overflow: auto;
}

li a {
	display: block;
	color: #000;
	padding: 8px 16px;
	text-decoration: none;
}

/ Change the link color on hover /
li a:hover {
	background-color: #555;
	color: white;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

input {
	margin-bottom: 10px;
	diplay: block;
}
</style>
<title>WareHouse Manager Home Page</title>
</head>
<%@page import="java.util.ArrayList,
				com.WareHouseManagement.spring.Entities.Order_Details,
				com.WareHouseManagement.spring.Entities.Item_Details,
				com.WareHouseManagement.spring.Entities.Purchase_Details,
				com.WareHouseManagement.spring.Entities.Customer_Details" %>
<body>
		<div>
			<h2>MENU</h2>
			<ul>
				<li><a href="billing">BILLING</a></li>
				<li><a href="viewItems">VIEW ITEMS</a></li>
				<li><a href="deleteItem">DELETE ITEM</a></li>
				<li><a href="placeOrder">PLACE ORDER</a></li>
				<li><a href="updatePrice">UPDATE PRICE</a></li>
				<li><a href="listPurchase">LIST PURCHASE ON A DATE</a></li>
				<li><a href="customerRegister">NEW CUSTOMER REGISTRATION</a></li>
				<li><a href="viewCustomerDetails">DISPLAY CUSTOMER</a></li>
				<li><a href="myOrders">MY ORDERS</a></li>
				<li><a href="home">LOGOUT</a></li>
			</ul>
		</div>
		<div style="margin-left: 25%; padding: 1px 16px; height: 100px;font-size:20px;">
			<center>
				Welcome WareHouse Manager
			</center>
		</div>
		
<%
	String status=(String)request.getAttribute("status");
	if(status!=null)
	{    
		if(status.equals("viewItems"))
		{
			ArrayList<Item_Details> itemList=(ArrayList<Item_Details>) request.getAttribute("itemList");
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
			<table style="width: 80%" id="a">
				<thead>
				    <tr>
						<td><%="item_code" %></td>		
						<td><%="item_name" %></td>
						<td><%="item_price" %></td>
						<td><%="stock" %></td>
				    </tr>
				</thead>
<%
			for(int k=0 ; k < itemList.size() ; k++)
			{
%>
				<tbody>
					<tr>
					    <td><%=itemList.get(k).getItem_code() %></td>
					    <td><%=itemList.get(k).getItem_name() %></td>
						<td><%=itemList.get(k).getItem_price() %></td>
						<td><%=itemList.get(k).getStock() %></td>
				    </tr>
				</tbody>	        
<%
			}
%>
			</table>
			</div>
<%
		}
		
		else if(status.equals("deleteItem"))
		{
%>
			<form action="DeleteItem" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						Enter item code:<input type="text" name="item_code" required><br/>
						<input type="submit" value="Delete">
				</div>
			</form>
<%
		}
		else if(status.equals("billing"))
		{
%>
			<form action="Billing" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					Enter Customer code:<input type="text" name="customer_code" required><br/>
					Enter item code:<input type="text" name="item_code" required><br/>
					Enter item quantity:<input type="text" name="item_quantity" required><br/>
			        <input type="submit" value="Bill">
			    </div>
			</form>
			
<%
		}
		else if(status.equals("placeOrder"))
		{
%>
			<form action="PlaceOrder" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					Enter item code:<input type="text" name="item_code" required><br/>
					Enter Manufacturer Name:<input type="text" name="manufacturer_name" required><br/>
					Enter quantity:<input type="text" name="item_quantity" required><br/>
					<input type="submit" value="Place Order">
				</div>
			</form>	
			
<%
		}
		else if(status.equals("updatePrice"))
		{
%>
			<form action="UpdatePrice" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					Enter item code:<input type="text" name="item_code" required><br/>
					Enter price:<input type="text" name="item_price" required><br/>
					<input type="submit" value="Update">	
				</div>
			</form>	
<%
		}
		else if(status.equals("listPurchase"))
		{
%>
			<form action="ListPurchase" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					Enter date:<input type="date" name="date"  required><br/>
					<input type="submit" value="Get List">
				</div>
			</form>
<%
		}
		else if(status.equals("customerRegister"))
		{
%>
			<form action="CustomerRegister" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						Enter Customer name:<input type="text" name="customer_name" required><br/>
						Enter address:<input type="text" name="address" required><br/>
						Enter phone number:<input type="text" name="phone_number" required><br/>
						<input type="submit" value="Register">
				</div>
			</form>
<%
		}
		else if(status.equals("viewCustomerDetails"))
		{
%>
			<form action="CustomerDetails" method="post">
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						Enter Customer code:<input type="text" name="customer_code" required><br/>
						<input type="submit" value="Get Details">
				</div>
			</form>
<%
		}
		else if(status.equals("Item Deleted"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>Item Deleted</center>
				</div>
<%
		}
		else if(status.equals("billingResult"))
		{
			double amount=(double)request.getAttribute("amount");
			if(amount!=0.0)
			{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>Transaction Id is : ${transactionId}<br/>
						Billing Amount is: ${amount}<br/>
						</center>
				</div>
<%
			}
			else
			{
%>
				<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>Customer does not exists<br/>
						Register Customer using below link<br/>
						<a href="customerRegister">NEW CUSTOMER REGISTRATION</a>
						</center>
			    </div>
<%
			}
		}
		else if(status.equals("Price Updated"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Price Updated for the Item with item code : ${Id}
						</center>
			</div>
<%
		}
		
		else if(status.equals("Registration SuccessFull"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Registration SuccessFull<br/>
							The Customer Code is : ${id}<br/>
						</center>
			</div>
<%
		}
		
		else if(status.equals("Order Placed"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Order Placed<br/>
							The Order Id is : ${orderId}<br/>
						</center>
			</div>
<%
		}
		else if(status.equals("OrderCancel"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Order Canceled<br/>
							The Order Id is : ${orderId}<br/>
						</center>
			</div>
<%
		}
		
		else if(status.equals("customerNotFound"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Customer Does Not Exists with given customer Code : ${id}<br/>
							Register Customer using below link<br/>
							<a href="customerRegister">NEW CUSTOMER REGISTRATION</a><br/>
						</center>
			</div>
<%
		}	
		else if(status.equals("purchaseNotFound"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							No Purchase Exists on the given date : ${date}<br/>
						</center>
			</div>
<%
		}
		else if(status.equals("itemNotFound"))
		{
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							Item Does Not Exists with given Item Code : ${id}<br/>
						</center>
			</div>
<%
		}
		else if(status.equals("customerDetails"))
		{
			Customer_Details customer=(Customer_Details)request.getAttribute("Details");
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<center>
							<h3>Customer Details</h3>
							Customer Name:<input type="text" value="<%=customer.getCustomer_name() %>" readOnly><br/>
							Address:<input type="text" value="<%=customer.getAddress() %>" readOnly><br/>
							Phone Number:<input type="text" value="<%=customer.getPhone_number() %>" readOnly><br/>
						</center>
			</div>
<%
		}
		else if(status.equals("ListofMyOrders"))
		{
			ArrayList<Order_Details> orderList=(ArrayList<Order_Details>) request.getAttribute("orderList");
%>
					<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
						<table style="width: 80%" id="a">
							<thead>
							    <tr>
									<td><%="Order_Id" %></td>		
									<td><%="Item_Code" %></td>
									<td><%="Item_Quantity" %></td>
									<td><%="WareManager_Name" %></td>
									<td><%="Order_Status" %></td>
							    </tr>
							</thead>
<%
						for(int k=0 ; k < orderList.size() ; k++)
						{
%>
							<tbody>
								<tr>
								    <td><%=orderList.get(k).getOrder_Id()%></td>
								    <td><%=orderList.get(k).getItem_code() %></td>
									<td><%=orderList.get(k).getItem_quantity() %></td>
									<td><%=orderList.get(k).getManager_name() %></td>
									<td><%=orderList.get(k).getStatus() %></td>
<%
							        if(orderList.get(k).getStatus().equals("pending"))
							        {
%>
								    	<td><a href="orderCancel?orderId=<%=orderList.get(k).getOrder_Id()%>">Cancel</a></td>
<%						
							        }
%>								
							    </tr>
							</tbody>	        
<%
						}
%>
						</table>
					</div>
<%
		}
		else if(status.equals("ListofPurchases"))
		{
			ArrayList<Purchase_Details> purchaseList=(ArrayList<Purchase_Details>) request.getAttribute("purchaseList");
%>
			<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
			<table style="width: 80%" id="a">
				<thead>
				    <tr>
						<td><%="transaction_id" %></td>		
						<td><%="item_code" %></td>
						<td><%="customer_code" %></td>
						<td><%="quantity" %></td>
						<td><%="date_of_purchase" %></td>
						<td><%="purchase_amount" %></td>
				    </tr>
				</thead>
<%
			for(int k=0 ; k < purchaseList.size() ; k++)
			{
%>
				<tbody>
					<tr>
					    <td><%=purchaseList.get(k).getTransaction_id() %></td>
					    <td><%=purchaseList.get(k).getItem_code() %></td>
						<td><%=purchaseList.get(k).getCustomer_code() %></td>
						<td><%=purchaseList.get(k).getQuantity() %></td>
						<td><%=purchaseList.get(k).getDate_of_purchase() %></td>
						<td><%=purchaseList.get(k).getPurchase_amount() %></td>
				    </tr>
				</tbody>	        
<%
			}
%>
			</table>
			</div>
<%
		}
}
%>
</body>
</html>