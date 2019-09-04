<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WareHouse Management Home Page</title>
</head>
<body>
	<center>
		<h2> Welcome to the WareHouseManagement</h2>
		<form action="login" method="post">
		UserName:<input type="text" name="user_name" required><br/><br/>
		Password:<input type="password" name="password" required><br/>
		<input type="submit" value="Login">
		</form>
	<%
		String status=(String)request.getAttribute("status");
		if(status!=null){    
			if(status.equals("Wrong Credentials"))
			{
	%>
				<div >
							<center>
								Wrong Credentials<br/>
								Either User_Name or Password is Wrong<br/> 
							</center>
				</div>
	<%
			}
		}
	%>
	</center>
</body>
</html>