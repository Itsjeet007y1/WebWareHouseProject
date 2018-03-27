<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Uom Data insert Page</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-info">
			<div class="panel-heading" align="center">
				<h1 style="color: lightgreen">Welcome to Edit page:</h1>
			</div>
			<div class="panel-body">
				<fm:form action="updateUom" method="post" modelAttribute="uom"
					class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-4">ID :</label>
						<div class="col-sm-4">
							<input type="text" name="uomId" value="${uom.uomId}"
								readonly="readonly" class="form-control" /><br>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-4" for="type">Uom Type
							:</label>
						<div class="col-sm-4">
							<fm:select path="uomType" class="form-control" id="type">
								<fm:option value="">Select</fm:option>
								<c:forEach items="${uomTypes }" var="ob">
									<c:choose>
										<c:when test="${uom.uomType eq ob.key}">
											<fm:option value="${ob.key }" selected="selected">${ob.value}</fm:option>
										</c:when>
										<c:otherwise>
											<fm:option value="${ob.key }">${ob.value}</fm:option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</fm:select>
							<fm:errors path="uomType" cssClass="error" />
						</div>
					</div>


					<div class="form-group">
						<label class="control-label col-sm-4" for="model">Model:</label>
						<div class="col-sm-4">
							<fm:input path="uomModel" value="${uom.uomModel}"
								class="form-control" id="model" />
							<fm:errors path="uomModel" cssClass="error" />
						</div>
					</div>


					<%-- // This line was giving error:"Failed to convert from String to Date".    CRTD:	<fm:input path="createdDate" value="${uom.createdDate}" readonly="readonly"/><br> --%>

					<div class="form-group">
						<label class="control-label col-sm-4" for="desc"> DESC. :</label>
						<div class="col-sm-4">
							<fm:textarea path="description" value="${uom.description}"
								class="form-control" id="desc"></fm:textarea>
							<fm:errors path="description" cssClass="error" />
						</div>
					</div>

					<input type="submit" value="update" class="btn col-sm-offset-4 btn-primary" />&nbsp; &nbsp;
					<a href="/showAllUom" class="btn btn-info">Cancel</a>
				</fm:form>
			</div>
		</div>
	</div>
</body>
</html>