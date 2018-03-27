<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../js/uom.js"></script>
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1 style="color: lightblue">Ware House User Type Data Page</h1>
		<hr>
		<hr>
		<!-- For Search -->
		<h3 style="color: lightblue">Search Screen</h3>
		<form:form action="getAllWhUserType" method="get"
			modelAttribute="whUserType">
			<table>
				<tr>
					<td>User Type: <form:radiobuttons path="whUserTyp"
							items="${whUserId}" /></td>
					<td>User Code: <form:input path="whUserCode" /></td>
					<td>User For: <form:input path="whUserFor" /></td>
				</tr>
				<tr>
					<td>User Email : <form:input path="whUserMail" /></td>
					<td>User Contact : <form:input path="whUserContact" /></td>
					<td>User ID Type: <form:select path="whUserIdType">
							<form:option value="">--Select--</form:option>
							<c:forEach items="${whUserTypes}" var="it">
								<form:option value="${it}">${it}</form:option>
							</c:forEach>
						</form:select></td>
				</tr>
				<tr>
					<td>If Other:<form:input path="whUserIdTypeOther" /></td>
					<td>ID Number:<form:input path="whUserIdNumber" /></td>
					<td><input type="submit" value="SEARCH"></td>
				</tr>
			</table>
		</form:form>
		<hr>
		<hr>
		<c:if test="${!empty whList}">
			<table border="1">
				<tr bgcolor="wheat">
					<th>ID</th>
					<th>User Type</th>
					<th>User Code</th>
					<th>User For</th>
					<th>Email</th>
					<th>Contact</th>
					<th>ID Type</th>
					<th>IF Other</th>
					<th>ID NUMBER</th>
					<th>CREATED ON</th>
					<th>MODIFIED ON</th>
				</tr>
				<c:forEach items="${whList}" var="whu">
					<tr>
						<td><c:out value="${whu.whUserTypeId}" /></td>
						<td><c:out value="${whu.whUserTyp}" /></td>
						<td><c:out value="${whu.whUserCode }" /></td>
						<td><c:out value="${whu.whUserFor}" /></td>
						<td><c:out value="${whu.whUserMail}" /></td>
						<td><c:out value="${whu.whUserContact}" /></td>
						<td><c:out value="${whu.whUserIdType}" /></td>
						<td><c:out value="${whu.whUserIdTypeOther}" /></td>
						<td><c:out value="${whu.whUserIdNumber}" /></td>
						<td><c:out value="${whu.createdDate}" /></td>
						<td><c:out value="${whu.lastModifiedDate}" /></td>
						<td><a
							href="deleteWhUserType?whUserTypeId=${whu.whUserTypeId}">DELETE</a></td>
						<td><a href="editWhUserType?whUserTypeId=${whu.whUserTypeId}">EDIT</a></td>
					</tr>
				</c:forEach>
			</table>
			<!--Pagination  -->
			<c:if test="${!whUserPage.isFirst()}">
				<a href="#" onClick="setParam('page','0')">First</a> &nbsp;
		</c:if>

			<c:if test="${whUserPage.hasPrevious()}">
				<a href="#" onClick="setParam('page',${whUserPage.getNumber()-1})">Previous</a>
			</c:if>

			<c:forEach begin="0" end="${whUserPage.getTotalPages()-1}" var="i">
				<c:choose>
					<c:when test="${whUserPage.getNumber() eq i}">
						<span style="color: red"><c:out value="${i+1}" /></span> &nbsp;
				</c:when>
					<c:otherwise>
						<a href="#" onClick="setParam('page',${i})"><c:out
								value="${i+1}" /></a> &nbsp;
				</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${whUserPage.hasNext()}">
				<a href="#" onClick="setParam('page',${whUserPage.getNumber()+1})">Next</a>
			</c:if>

			<c:if test="${!whUserPage.isLast()}">
				<a href="#"
					onClick="setParam('page',${whUserPage.getTotalPages()-1})">Last</a>
			</c:if>

		</c:if>
		<br> <br>
		<c:if test="${empty whList}">
			<h3 style="color: orange">No Any Record Found !!</h3>
		</c:if>
		<br> <a href="/">Click Here to Insert another Order Method</a>
	</center>
</body>
</html>