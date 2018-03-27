<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>commons</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/uom.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body {
	padding-top: 70px;
}
</style>
<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Sample App</span> <span class="icon-bar"></span>
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand " href="home" >WAREHOUSE</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="home">HOME</a></li>
				
				<!-- Uom Menu -->
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">UOM<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/">Register</a></li>
						<li><a href="showAllUom">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="uomMultipart">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
					<!-- Order Method Menu -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">ORDER METHOD<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="regOrderMethod">Register</a></li>
						<li><a href="getAllOrderMethods">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
					<!-- Shipment Menu -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">SHIPMENT<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="regShipmentType">Register</a></li>
						<li><a href="getAllShipmentTypes">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
					<!-- WH user Menu -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">WH USER<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="whUserTypeReg">Register</a></li>
						<li><a href="getAllWhUserType">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
					<!-- Item Menu -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">ITEM<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="itemsRegPage">Register</a></li>
						<li><a href="getAllItems">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
					<!-- Purchase Menu -->
					<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">PURCHASE<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="regPurchaseOrder">Register</a></li>
						<li><a href="getAllPurchaseOrders">Get All Records</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Export Data</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Import Data</a></li>
					</ul></li>
					
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">My Details</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">User Profile <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logout">Logout <span
								class="glyphicon glyphicon-log-out"></span></a></li>
						<li><a href="#">Change Password</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">About User <span
								class="glyphicon glyphicon-user"></span></a></li>
					</ul></li>
			</ul>
		</div>
	</div>
	</nav>
</body>
</html>