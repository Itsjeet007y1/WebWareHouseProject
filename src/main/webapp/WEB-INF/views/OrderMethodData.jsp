<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="../js/uom.js"></script>
</head>
<body>
	<center>
		<h1 style="color:lightgreen">Welcome to OrderMethod Data page!!</h1>
		<hr>
		<h3 style="color:lightblue">Search Screen</h3>
		<form:form action="getAllOrderMethods" method="get" modelAttribute="orderMethod">
			<table>
				<tr>
					<td>Order Mode: <form:radiobuttons path="orderMode" items="${orderModes}"/></td>
					<td>Order Code: <form:input path="orderCode"/></td>
					<td>
						Order Method: <form:select path="orderMetd">
											<form:option value="">--Select--</form:option>
											<form:options items="${orderMethods}"/>
										</form:select>
					</td></tr><tr>
					<td>Order Accept: <form:checkboxes items="${orderAccepts}" path="orderAccept"/></td>
					<td>Description: <form:textarea path="description"/></td>
					<td><input type="submit" value="Search"/></td>
				</tr>
			</table>
		</form:form>
		<hr>
		<hr>
		<c:if test="${!empty orderMethodsList}">
		<table border="1">
			<tr style="color:lightblue">
				<th>ID</th><th>MODE</th><th>CODE</th><th>METHOD</th><th>ACCEPT</th><th>NOTE</th><th>CREATED ON</th><th>MODIFIED ON</th>
			</tr>
			<c:forEach items="${orderMethodsList}" var="om">
				<tr>
					<td><c:out value="${om.orderMethodId}"/></td>
					<td><c:out value="${om.orderMode}"/></td>
					<td><c:out value="${om.orderCode}"/></td>
					<td><c:out value="${om.orderMetd}"/></td>
					<td>
					 <c:forEach items="${om.orderAccept}" var="omac">
					 	<input type="checkBox" checked="checked" disabled="disabled"/><c:out value="${omac}"></c:out>
					 </c:forEach>
					</td>
					<td><c:out value="${om.description}"/></td>
					<td><c:out value="${om.createdDate}"/></td>
					<td><c:out value="${om.lastModifiedDate}"/></td>
					<td><a href="editOrderMethod?orderMethodId=${om.orderMethodId}">Edit</a></td>
					<td><a href="deleteOrderMethod?orderMethodId=${om.orderMethodId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		<!--Pagination  -->
		<c:if test="${!orderMethodsPage.isFirst()}">
			<a href="#" onClick="setParam('page','0')">First</a> &nbsp;
		</c:if>
		
		<c:if test="${orderMethodsPage.hasPrevious()}">
			<a href="#" onClick="setParam('page',${orderMethodsPage.getNumber()-1})">Previous</a>
		</c:if>
		
		<c:forEach begin="0" end="${orderMethodsPage.getTotalPages()-1}" var="i">
			<c:choose>
				<c:when test="${orderMethodsPage.getNumber() eq i}">
					<span style="color:red"><c:out value="${i+1}"/></span> &nbsp;
				</c:when>
				<c:otherwise>
					<a href="#" onClick="setParam('page',${i})"><c:out value="${i+1}"/></a> &nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${orderMethodsPage.hasNext()}">
			<a href="#" onClick="setParam('page',${orderMethodsPage.getNumber()+1})">Next</a>
		</c:if>
		
		<c:if test="${!orderMethodsPage.isLast()}">
			<a href="#" onClick="setParam('page',${orderMethodsPage.getTotalPages()-1})">Last</a>
		</c:if>
		
		</c:if>
		<br><br>
		<c:if test="${empty orderMethodsList}">
			<h3 style="color:orange">No Any Record Found !!</h3>
		</c:if>
		<br>
		<a href="/">Click Here to Insert another Order Method</a>
	</center>
</body>
</html>