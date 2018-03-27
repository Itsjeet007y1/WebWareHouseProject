<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Order Page</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>
	<center>
		<h1 style="color:green">Welcome to Purchase Order Page!!</h1><hr>
		<form:form action="insertPurchaseOrder" method="post" modelAttribute="purchaseOrder">
			Order Code: <form:input path="orderCode"/><br>
			<form:errors path="orderCode" cssClass="error"/><br><br>
			Shipment Mode:<form:select path="shipmentMode">
				<form:option value="">--Select--</form:option>
				<form:options items="${enabled}" itemValue="shipmentId" itemLabel="shipmentCode"/>
			</form:select>
			<br>
			Vendor: <form:select path="vendor">
				<form:option value="">--Select--</form:option>
				<form:options items="${vendorList}" itemValue="whUserTypeId"	itemLabel="whUserCode"/>
			</form:select>			
			<br>
			Reference Number: <form:input path="refNumber"/><br>
			<form:errors path="refNumber" cssClass="error"/><br><br>
			Quality Check: <form:radiobuttons path="qualityCheck" items="${qltList}"/><br>
			<form:errors path="qualityCheck" cssClass="error"/><br><br>
			Default Status: <form:input path="defaultStatus" value="OPEN" readOnly="true"/><br>
			Description: <form:textarea path="description"/><br>
			<form:errors path="description" cssClass="error"/><br>
			<h4 style="color:green">${message}</h4><br><br>
			<input type="submit" value="Place Order"/><br><br>
			<a href="getAllPurchaseOrders">Get All Purchase Order Details</a>
		</form:form>
	</center>
</body>
</html>