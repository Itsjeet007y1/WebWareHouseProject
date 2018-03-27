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
<center>
	<h1 style="color: lightblue">ShipmentType Data Page!!</h1>
	<hr>
	<hr>
	<h2 style="color: lightgreen">Search Page</h2>
	<hr>
	<form:form action="getAllShipmentTypes" method="get"
		modelAttribute="shipmentType">
		<table>
			<tr>
				<td>Shipment Mode: <form:select path="shipmentMode">
						<form:option value="">--Select--</form:option>
						<c:forEach items="${shipmentModes}" var="sm">
							<form:option value="${sm }">${sm}</form:option>
						</c:forEach>
					</form:select>
				</td>
				<td>Shipment Code: <form:input path="shipmentCode" /></td>
				<td>Enable Shipment: <form:checkbox path="enabled" value="Yes" />YES
				</td>
			</tr>
			<tr>
				<td>Shipment Grade: <form:radiobuttons path="shipmentGrade"
						items="${shipmentGrades}" /></td>
				<td>Description: <form:input path="description" /></td>
				<td><input type="submit" value="SEARCH"></td>
			</tr>
		</table>
	</form:form>
</center>
<body>
	<center>
		<c:if test="${!empty shipList}">
			<h2 style="color: lightgreen">
				<u>Available Data</u>
			</h2>
			<hr>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Mode</th>
					<th>Code</th>
					<th>Enabled</th>
					<th>Grade</th>
					<th>Description</th>
					<th>Created On</th>
					<th>LastModified On</th>
				</tr>
				<c:forEach items="${shipList}" var="s">
					<tr>
						<td><c:out value="${s.shipmentId}" /></td>
						<td><c:out value="${s.shipmentMode}" /></td>
						<td><c:out value="${s.shipmentCode}" /></td>
						<td><c:choose>
								<c:when test="${s.enabled eq 'YES'}">
									<input type="checkbox" checked="checked" disabled="disabled">YES
   	</c:when>
								<c:otherwise>
									<input type="checkbox" disabled="disabled">NO
   	</c:otherwise>
							</c:choose></td>
						<td><c:out value="${s.shipmentGrade}" /></td>
						<td><c:out value="${s.description}" /></td>
						<td><c:out value="${s.createdDate}" /></td>
						<td><c:out value="${s.lastModifiedDate}" /></td>
						<td><a
							href="deleteShipmentType?shipmentTypeId=${s.shipmentId}">DELETE</a></td>
						<td><a href="editShipmentType?shipmentTypeId=${s.shipmentId}">EDIT</a></td>
					</tr>
				</c:forEach>
			</table>
			<br>
			<br>
			<!--Pagination  -->
			<c:if test="${!shipPage.isFirst()}">
				<a href="#" onClick="setParam('page','0')">First</a> &nbsp;
		</c:if>

			<c:if test="${shipPage.hasPrevious()}">
				<a href="#" onClick="setParam('page',${shipPage.getNumber()-1})">Previous</a>
			</c:if>

			<c:forEach begin="0" end="${shipPage.getTotalPages()-1}" var="i">
				<c:choose>
					<c:when test="${shipPage.getNumber() eq i}">
						<span style="color: red"><c:out value="${i+1}" /></span> &nbsp;
				</c:when>
					<c:otherwise>
						<a href="#" onClick="setParam('page',${i})"><c:out
								value="${i+1}" /></a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${shipPage.hasNext()}">
				<a href="#" onClick="setParam('page',${shipPage.getNumber()+1})">Next</a>
			</c:if>

			<c:if test="${!shipPage.isLast()}">
				<a href="#" onClick="setParam('page',${shipPage.getTotalPages()-1})">Last</a>
			</c:if>
			<br>
			<br>

		</c:if>
		<c:if test="${empty shipList}">
			<h2 style="color:orange">No any Data Found!!!</h2>
		</c:if>
	</center>

</body>
</html>