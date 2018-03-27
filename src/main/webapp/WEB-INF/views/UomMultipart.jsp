<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<h1 style="color:green">UOM MultiPart Operations Page</h1><hr><hr>
		<h2 style="color:lightblue"><u>Uom Import From Uom Excel Page</u></h2>
		<form action="/uomImport" method="post" enctype="multipart/form-data">
			Select File: <input type="file" name="uomFile"><br><br>
			<input type="submit" value="Upload Uoms"/>
			${importMessage} &nbsp; <a href="showAllUom">Show Data Page</a>
		</form><hr>
		<h2 style="color:lightblue"><u>Uom Exporting to Uom Excel Page</u></h2>
		<a href="uomExport">Download Excel Uom Data</a><hr><hr>
		<!-- Final Message -->
		<c:if test="${!empty importMessage}">
			<c:out value="${importMessage}"/> 
		</c:if>
		<!-- Row by Row Errors -->
		<c:if test="${!empty importErrors}">
			<table border="1">
				<tr>
					<th colspan="2">Errors while importing in Excel Sheet (Row by Row)</th>
				</tr>
				<c:forEach items="${importErrors}" var="error">
					<tr>
						<td><c:out value="${error.key}"/></td>
						<td class="error"><c:out value="${error.value}"/></td>
					</tr>
				</c:forEach>
			</table>	
		</c:if>		
	</center>
</body>
</html>