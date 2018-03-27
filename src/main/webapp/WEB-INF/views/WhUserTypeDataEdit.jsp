<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<h1 style="color:lightgreen">WareHouse Type Data Edit Page!!</h1><hr><hr>
		<form:form action="updateWhUser" method="post" modelAttribute="whUserType">
		<table>
		<tr>
			<td>User ID:</td><td><form:input path="whUserTypeId" readOnly="true"/></td></tr>
			<tr><td>User Type:</td><td><form:radiobuttons path="whUserTyp" items="${whUserId}"/></td></tr>
			<tr><td></td><td><form:errors path="whUserTyp" cssClass="error"/></td></tr>
			<tr><td>User Code:</td><td> <form:input path="whUserCode"/></td></tr>
			<tr><td></td><td><form:errors path="whUserCode" cssClass="error"/></td></tr>
			<tr><td>User For:</td><td> <form:input path="whUserFor"/></td></tr>
			<tr><td></td><td><form:errors path="whUserFor" cssClass="error"/></td></tr>
			<tr><td>User Mail:</td><td> <form:input path="whUserMail"/></td></tr>
			<tr><td></td><td><form:errors path="whUserMail" cssClass="error"/></td></tr>
			<tr><td>User Contact:</td><td> <form:input path="whUserContact"/></td></tr>
			<tr><td></td><td><form:errors path="whUserContact" cssClass="error"/></td></tr>
			<tr><td>User ID Type:</td><td> <form:select path="whUserIdType">
				<form:option value="">--Select--</form:option>
				<c:forEach items="${whUserTypes}" var="it">
					<form:option value="${it}">${it}</form:option>
				</c:forEach>
			</form:select></td></tr>
			<tr><td></td><td><form:errors path="whUserIdType" cssClass="error"/></td></tr>
			<tr><td>If Other:</td><td> <form:input path="whUserIdTypeOther"/></td></tr>
			<tr><td></td><td><form:errors path="whUserIdTypeOther" cssClass="error"/></td></tr>
			<tr><td>ID Number:</td><td> <form:input path="whUserIdNumber"/></td></tr>
			<tr><td></td><td><form:errors path="whUserIdNumber" cssClass="error"/></td></tr>
			<tr><td><input type="submit" value="Insert User Type"/></td></tr>
		</table>
		</form:form>
	</center>
</body>
</html>