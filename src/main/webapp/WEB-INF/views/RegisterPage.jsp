<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Uom Register Page</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-heading" align="center">
				<h3 style="color: Green">Welcome to Employee Register Page</h3>
			</div>
			<div class="panel-body">
				<fm:form action="/regUom" method="post" modelAttribute="uom"
					class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-4">Uom Type :</label>
						<div class="col-sm-4">
							<fm:select path="uomType" class="form-control">
								<fm:option value="">--Select--</fm:option>
								<c:forEach items="${uomTypes}" var="ob">
									<fm:option value="${ob.key}">${ob.value}</fm:option>
								</c:forEach>
							</fm:select>
							<fm:errors path="uomType" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4">Uom Model :</label>
						<div class="col-sm-4">
							<fm:input path="uomModel" class="form-control" />
							<fm:errors path="uomModel" cssClass="error" />
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4">Description :</label>
						<div class="col-sm-4">
							<fm:textarea path="description" class="form-control"></fm:textarea>
							<fm:errors path="description" cssClass="error" />
						</div>
					</div>

					<input type="submit" value="Register UOM"
						class="col-sm-offset-4 btn btn-primary" />&nbsp; &nbsp; &nbsp;
					<input type="reset" value="Reset" class="btn btn-danger" />
					<br>
				</fm:form>
			</div>
		</div>
	</div>
</body>
</html>