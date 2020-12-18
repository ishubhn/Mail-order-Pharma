<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Mail Order Pharmacy</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="images/favicon.png" rel="icon">
<link href="images/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
<link href="vendor/owl.carousel/assets/owl.carousel.min.css"
	rel="stylesheet">
<link href="vendor/venobox/venobox.css" rel="stylesheet">
<link href="vendor/aos/aos.css" rel="stylesheet">
<!-- Template Main CSS File -->
<link href="style/style.css" rel="stylesheet">
</head>
<body>
	<!-- ======= Header ======= -->
	<header id="header">
		<div class="container">

			<div id="logo" class="pull-left">
				<h3>
					<a href="home">Mail Order Pharmacy</a>
				</h3>
			</div>

			<nav id="nav-menu-container">
				<ul class="nav-menu">
					<li class="menu-active"><a href="home">Home</a></li>
					<li><a href="prescriptionform">Subscribe</a></li>
					<li><a href="supportedDrugs">Supported drugs</a></li>
					<li><a href="subscriptions">Subscribed medicines</a></li>
					<li><a href="home#due-for-refill">Due for refill</a></li>
					<li><a href="subscriptions">Request now</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</nav>
			<!-- #nav-menu-container -->
		</div>
	</header>
	<!-- End Header -->
	<br>
	<br>
	<section id="services">
		<div class="container" style="padding: 7%">
			<form method="POST" action="/webportal/postAdhocRefill">
				<table>
					<tr>
						<th>Quantity</th>
						<td><input type="number" name="quantity" /></td>
					</tr>
					<tr>
						<th>Location</th>
						<td><input type="text" name="location" /></td>
					</tr>
					<tr>
						<th>Payment Status</th>
						<td><input type="checkbox" name="paymentStatus" /></td>
					</tr>
					<tr>
						<td><button type="submit" class="btn btn-success">Submit</button></td>
					</tr>
				</table>


			</form>
		</div>
	</section>
	<!-- ======= Footer ======= -->
	<footer id="footer" class="mt-auto fixed-bottom">
		<div class="footer-top">
			<div class="container"></div>
		</div>

		<div class="container">
			<div class="credits">Thank you! We are here for your needs.</div>
		</div>
	</footer>
	<!-- End Footer -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="vendor/jquery.easing/jquery.easing.min.js"></script>
	<script src="vendor/counterup/counterup.min.js"></script>
	<script src="vendor/waypoints/jquery.waypoints.min.js"></script>
	<script src="vendor/aos/aos.js"></script>

	<!-- Template Main JS File -->
	<script src="js/main.js"></script>
</body>
</html>