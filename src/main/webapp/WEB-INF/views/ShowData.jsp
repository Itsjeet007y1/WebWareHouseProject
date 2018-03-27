<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="fm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="commons.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>UOM Register Page</title>
</head>
<body>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading" align="center">
				<h1 style="color: lightyellow" align="center">Welcome to UOM Data
					Page!!</h1>
			</div>
			<hr />
			<div class="panel-heading">
				<h4 style="color: orange">Search Screen</h4>
			</div>
			<fm:form action="showAllUom" method="get" modelAttribute="uom"
				class="form-inline">
				<div class="table-responsive">
					<table class="table	table-bordered table-hover table-responsive">
						<tr>
							<td><label class="control-label col-xs-4">UOM Type :</label>
								<fm:select path="uomType" class="form-control">
									<fm:option value="">--select--</fm:option>
									<fm:option value="PACK">PACKING</fm:option>
									<fm:option value="NOPACK">NO PACKING</fm:option>
									<fm:option value="NA">-NA-</fm:option>
								</fm:select></td>
							<td><label class="control-label col-xs-4">UOM Model
									:</label> <fm:input type="text" path="uomModel" class="form-control" /></td>
							<td><label class="control-label col-xs-4">Description:</label>
								<fm:textarea path="description" class="form-control"></fm:textarea></td>
							<td><input type="submit" value="Search"
								class="btn btn-primary" /></td>
						</tr>
					</table>
				</div>
			</fm:form>

			<hr>
			<div class="panel-heading">
				<h4 style="color: pink">Unity of Measurements List</h4>
			</div>
			<c:choose>
				<c:when test="${!empty uoms}">
				<center>
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<tr>
								<th>ID</th>
								<th>Types</th>
								<th>Model</th>
								<th>Created Date</th>
								<th>Modified Date</th>
								<th>Description</th>
							</tr>
							<c:forEach items="${uoms}" var="uom">
								<tr>
									<td><c:out value="${uom.getUomId() }" /></td>
									<td><c:out value="${uom.getUomType() }" /></td>
									<td><c:out value="${uom.getUomModel() }" /></td>
									<td><c:out value="${uom.getCreatedDate() }" /></td>
									<td><c:out value="${uom.getLastModifiedDate() }" /></td>
									<td><c:out value="${uom.getDescription() }" /></td>
									<td><a href="deleteUom?uomId=${uom.getUomId()}"
										class="btn btn-danger"><span
											class="glyphicon glyphicon-trash"></span></a></td>
									<td><a href="editUom?uomId=${uom.getUomId()}"
										class="btn btn-primary"><span
											class="glyphicon glyphicon-pencil"></span></a></td>
								</tr>
							</c:forEach>
						</table>
						<hr>
					</div>

					<!--Pagination Design code  -->
					<div class="container small">
						<div class="pagination">
							<ul class="pagination pagination-responsive">
								<c:if test="${!uomPage.isFirst()}">
									<li><a href="#" onclick="setParam('page','0')">First</a></li>&nbsp;
				</c:if>

								<c:if test="${uomPage.hasPrevious()}">
									<li><a href="#"
										onclick="setParam('page',${uomPage.getNumber()-1})">Previous</a></li>&nbsp;
				</c:if>

								<c:forEach begin="0" end="${uomPage.getTotalPages()-1}" var="i">
									<c:choose>
										<c:when test="${uomPage.getNumber() eq i}">
											<li class="active"><a href="#"><c:out value="${i+1}" /></a></li> &nbsp;
				</c:when>
										<c:otherwise>
											<li><a href="#" onclick="setParam('page',${i})"><c:out
														value="${i+1}" /></a></li>&nbsp;
						</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${uomPage.hasNext() }">
									<li><a href="#"
										onclick="setParam('page',${uomPage.getNumber()+1})">Next</a></li>&nbsp;
				</c:if>

								<c:if test="${!uomPage.isLast() }">
									<li><a href="#"
										onclick="setParam('page',${uomPage.getTotalPages()-1})">Last</a></li>&nbsp;
				</c:if>
							</ul>
						</div>
					</div>
					</center>
				</c:when>
				<c:otherwise>
					<h3 style="color: orange">No any Record Found!!</h3>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>