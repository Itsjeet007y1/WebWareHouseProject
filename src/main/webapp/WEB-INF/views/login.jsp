<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading" align="center">
				<h2 style="color: lightBlue">Welcome to Login Page</h2>
			</div>
			<div class="panel-body">
				<form action="login" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-4" for="userId">User
							Name</label>
						<div class="col-sm-4">
							<input type="text" name="username" id="userId"
								class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-4" for="password">Password</label>
						<div class="col-sm-4">
							<input type="password" name="password" id="password"
								class="form-control" />
						</div>
					</div>
					<input type="submit" value="LOGIN"
						class="col-sm-offset-4 btn btn-primary" />&nbsp; &nbsp; &nbsp; <input
						type="reset" value="Reset" class="btn btn-danger" />
					<c:if test="${param.error}">
						<br>
						<br>
						<center>Invalid UserName/Password !!!</center>
					</c:if>
					<br>
					<c:if test="${param.logout}">
						<br>
						<br>
						<center>LogOut Successfully !!!</center>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>