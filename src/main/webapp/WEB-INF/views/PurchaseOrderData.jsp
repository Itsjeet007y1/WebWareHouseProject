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
</head>
<body>
	<center>
		<h1 style="color: green">Purchase Order Data Page</h1>
		<hr>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>CODE</th>
				<th>SHIPMENT</th>
				<th>VENDOR</th>
				<th>REF NUM</th>
				<th>QUALITY CHECK</th>
				<th>STATUS</th>
				<th>NOTE</th>
			</tr>
			<c:forEach items="${poList}" var="op">
				<tr>
					<td><c:out value="${op.orderId}" /></td>
					<td><c:out value="${op.orderCode}" /></td>
					<td><c:out value="${op.shipmentMode.shipmentCode}" /></td>
					<td><c:out value="${op.vendor.whUserCode}" /></td>
					<td><c:out value="${op.refNumber}" /></td>
					<td><c:out value="${op.qualityCheck}" /></td>
					<td><c:out value="${op.defaultStatus}" /></td>
					<td><c:out value="${op.description}" /></td>
					<%-- <td><a href="deleteOrder?orderId=${op.orderId}">DELETE</a></td>
			<td><a href="editPurchaseOrder?orderId=${op.orderId}">EDIT</a></td> --%>

					<c:if
						test="${'OPEN' eq op.defaultStatus || 'PICKING' eq op.defaultStatus}">
						<td><a href="addPoItem?poId=${op.orderId}">Add Items</a></td>
						<td><a href="editPurchaseOrder?poId=${op.orderId}">Edit
								Order</a></td>
						<td><a href="cancelOrder?poId=${op.orderId}">Cancel Order</a>
						</td>
					</c:if>
					<c:if test="${'ORDERED' eq op.defaultStatus}">
						<td><a href="poConfirm?poId=${op.orderId}">Confirm
								(Invoice)</a></td>
						<td><a href="cancelOrder?poId=${op.orderId}">Cancel Order</a>
						</td>
					</c:if>
					<c:if test="${'INVOICED' eq op.defaultStatus}">
						<td><a href="poInvoceGen?poId=${op.orderId}">Generate
								Invoice</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</center>
</body>
</html>