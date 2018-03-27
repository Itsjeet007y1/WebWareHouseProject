<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Items Registration Page</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>
	<center>
		<h1 style="color: green">Welcome to Item Register Page!!!</h1>
		<hr>
		<form:form action="regItems" method="post" modelAttribute="items">
				Item Code: <form:input path="itemCode" />
			<br>
			<form:errors path="itemCode" cssClass="error"/><br><br>
				Item::		W: <form:input path="itemWidth" />&nbsp; L: <form:input
				path="itemLength" />&nbsp;	H: <form:input path="itemHeight" />
			<br>
			<br>
				Item Cost: <form:input path="itemCost" />
			<br>
			<br>
				Item Currency: <form:select path="currency">
				<form:option value="">--Select--</form:option>
				<form:options items="${currs}" />
			</form:select>
			<br>
			<form:errors path="currency" cssClass="error"/><br><br>
				UOM: <form:select path="uom">
				<form:option value="">--Select--</form:option>
				<form:options items="${uomList}" itemLabel="uomModel" itemValue="uomId" />
			</form:select>
			<br>
			 Order Method (Sale Type): 
				<form:select path="itemSaleOrdMethod">
				<form:option value="">--Select--</form:option>
				<form:options items="${saleMode}" itemLabel="orderCode" itemValue="orderMethodId" />
			</form:select>
			<br>
			<br>
					Order Method (Purchase Type): 
			<form:select path="itemPurchaseMethod">
				<form:option value="">--Select--</form:option>
				<form:options items="${purchaseMode}" itemLabel="orderCode"
					itemValue="orderMethodId" />
			</form:select>
			<br>
			<br>
			Item Vendors: 
				<form:select path="itemVendors">
				<form:option value="">--Select--</form:option>
				<form:options items="${vendors}" itemLabel="whUserCode" itemValue="whUserTypeId"/>
				</form:select>
			<br>
			<br>
			Item Customers:
				<form:select path="itemCustomers">
				<form:option value="">--Select--</form:option>
				<form:options items="${customers}" itemLabel="whUserCode" itemValue="whUserTypeId"/>
				</form:select>
				<br>	
			<br>
				Description: <form:textarea path="description" /><br>
				<form:errors path="description" cssClass="error"/><br><br>
			<input type="submit" value="Create Item" />
		</form:form>
		<h4 style="color: green">${message}</h4>
		<a href="getAllItems">Click Here to Get All Items</a>
	</center>
</body>
</html>