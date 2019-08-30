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
<title>Manufacturer Home Page</title>
</head>
<%@page import="java.util.ArrayList,com.WareHouseManagement.spring.Entities.Order_Details" %>
<body>
<div>
			<h2>MENU</h2>
			<ul>
				<li><a href="viewOrders">ORDERS</a></li>
				<li><a href="home">LOGOUT</a></li>
			</ul>
		</div>

<%
	String status=(String)request.getAttribute("status");
	if(status!=null){    
	if(status.equals("ListofOrders"))
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
						<td><a href="orderAccept?orderId=<%=orderList.get(k).getOrder_Id()%>">Accept</a></td>
						<td><a href="orderReject?orderId=<%=orderList.get(k).getOrder_Id()%>">Reject</a></td>
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
	else if(status.equals("OrderReject"))
	{
%>
		<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					<center>You have Rejected the order with Order Id : ${orderId}<br/>
					</center>
			</div>
<%
	}
	else if(status.equals("OrderAccept"))
	{
%>
		<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
					<center>You have Accepted the order with Order Id : ${orderId}<br/>
					</center>
			</div>
<%
	}
	}
%>
</body>
</html>