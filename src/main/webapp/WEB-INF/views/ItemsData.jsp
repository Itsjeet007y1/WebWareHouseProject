<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<h1 style="color: lightblue">Items Data Page!!</h1>
		<hr>
		<hr>
		<h2 style="color: lightgreen">
			<u>Available Data</u>
		</h2>
		<hr>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Item Code</th>
				<th>Width</th>
				<th>Length</th>
				<th>Height</th>
				<th>Cost</th>
				<th>Currency</th>
				<th>Uom</th>
				<th>Sale Order</th>
				<th>Purchase Order</th>
				<th>Vendors</th>
				<th>Customers</th>
				<th>CRTD</th>
				<th>MDFD ON</th>
				<th>Description</th>
			</tr>
			<c:forEach items="${itemList}" var="i">
				<tr>
					<td><c:out value="${i.itemId}" /></td>
					<td><c:out value="${i.itemCode}" /></td>
					<td><c:out value="${i.itemWidth}" /></td>
					<td><c:out value="${i.itemLength}" /></td>
					<td><c:out value="${i.itemHeight}" /></td>
					<td><c:out value="${i.itemCost}" /></td>
					<td><c:out value="${i.currency}" /></td>
					<td><c:out value="${i.uom.uomModel}" /></td>
					<td><c:out value="${i.itemSaleOrdMethod.orderCode}" /></td>
					<td><c:out value="${i.itemPurchaseMethod.orderCode}" /></td>
					<td><c:forEach items="${i.itemVendors}" var="ven">
							<c:out value="${ven.whUserCode}" />
							<br>
						</c:forEach></td>
					<td><c:forEach items="${i.itemCustomers}" var="cust">
							<c:out value="${cust.whUserCode}" />
							<br>
						</c:forEach></td>
					<td><c:out value="${i.createdDate}" /></td>
					<td><c:out value="${i.lastModifiedDate}" /></td>
					<td><c:out value="${i.description}" /></td>
					<td><a href="deleteItem?itemId=${i.itemId}">DELETE</a></td>
					<td><a href="editItem?itemId=${i.itemId}">EDIT</a></td>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<!--Pagination  -->
		<c:if test="${!itemPage.isFirst()}">
			<a href="#" onClick="setParam('page','0')">First</a> &nbsp;
		</c:if>

		<c:if test="${itemPage.hasPrevious()}">
			<a href="#" onClick="setParam('page',${itemPage.getNumber()-1})">Previous</a>
		</c:if>

		<c:forEach begin="0" end="${itemPage.getTotalPages()-1}" var="i">
			<c:choose>
				<c:when test="${itemPage.getNumber() eq i}">
					<span style="color: red"><c:out value="${i+1}" /></span> &nbsp;
				</c:when>
				<c:otherwise>
					<a href="#" onClick="setParam('page',${i})"><c:out
							value="${i+1}" /></a> &nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${itemPage.hasNext()}">
			<a href="#" onClick="setParam('page',${itemPage.getNumber()+1})">Next</a>
		</c:if>
		<c:if test="${!itemPage.isLast()}">
			<a href="#" onClick="setParam('page',${itemPage.getTotalPages()-1})">Last</a>
		</c:if>
		<br> <br>
		<c:if test="${empty itemList}">
			<h2 style="color: orange">No any Data Found!!!</h2>
		</c:if>
	</center>
</body>
</html>