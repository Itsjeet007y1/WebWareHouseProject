<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Page</title>
<link rel="stylesheet" type="text/css" href="../css/common.css">
</head>
<body>
	<center>
		<h1 style="color:lightgreen">Order Method Edit Page!!</h1><hr>
		<form:form action="updateOrderMethod" method="post" modelAttribute="orderMethod">
		Order Method Id: <form:input path="orderMethodId" readOnly="true"/><br>
		Order Mode: <form:radiobuttons path="orderMode" items="${orderModes}"/><br>
		<form:errors path="orderMode" cssClass="error"/><br><br>
		Order Code: <form:input path="orderCode"/><br>
		<form:errors path="orderCode" cssClass="error"/><br><br>
		Order Method: <form:select path="orderMetd">
											<form:option value="">--Select--</form:option>
											<form:options items="${orderMethods}"/>
										</form:select><br>
		<form:errors path="orderMetd" cssClass="error"/><br><br>
		Order Accept: <form:checkboxes items="${orderAccepts}" path="orderAccept"/><br>
		<form:errors path="orderAccept" cssClass="error"/><br><br>
		Description: <form:textarea path="description"/><br>
		<form:errors path="description" cssClass="error"/><br><br>
		<input type="submit" value="Update Order Method"/>
		</form:form>
	</center>
</body>
</html>