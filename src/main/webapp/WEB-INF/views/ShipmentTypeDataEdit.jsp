<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>
	<center>
		<h1 style="color: lightblue">ShipmentType Data Edit Page</h1>
		<form:form action="updateShipmentType" method="Post" modelAttribute="shipmentType">
			Shipment ID: <form:input path="shipmentId" readOnly="readOnly" /><br>
			Shipment Mode: <form:select path="shipmentMode">
				<form:option value="">--Select--</form:option>
				<c:forEach items="${shipmentMode}" var="sm">
					<form:option value="${sm}">${sm}</form:option>
				</c:forEach>
			</form:select><br>
			<form:errors path="shipmentMode" cssClass="error"/><br><br>
			Shipment Code: <form:input path="shipmentCode" /><br>
			<form:errors path="shipmentCode" cssClass="error"/><br><br>
			Enable Shipment: <form:checkbox path="enabled" value="YES" />Yes<br>
			<form:errors path="enabled" cssClass="error"/><br><br>
			Shipment Grade: <form:radiobuttons path="shipmentGrade" items="${shipmentGrade}" /><br>
			<form:errors path="shipmentGrade" cssClass="error"/><br><br>
			Description: <form:textarea path="description"/><br>
			<form:errors path="description" cssClass="error"/><br><br>
			<input type="submit" value="Update Shipment Type"><br>
		</form:form>
	</center>
</body>
</html>