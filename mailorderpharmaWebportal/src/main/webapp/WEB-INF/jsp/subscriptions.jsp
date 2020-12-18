<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<!-- cards -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700"
	rel="stylesheet">
<!--   <link rel="stylesheet" type="text/css" href="summa1.css"> -->

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

<!-- =======================================================
  * Template Name: Regna - v2.2.0
  * Template URL: https://bootstrapmade.com/regna-bootstrap-onepage-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>

	<!-- ======= Header ======= -->
	<header id="header">
		<div class="container">

			<div id="logo" class="pull-left">
				<a href="home">Mail Order Pharmacy</a>
				<!-- Uncomment below if you prefer to use a text logo -->
				<!--<h1><a href="#hero">Regna</a></h1>-->
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
	<section id="services">
		<div class="container">
			<br>
			<br>
			<h1>Your Subscriptions</h1>

			<div class="container bcontent">
				<hr />
				<c:choose>
					<c:when test="${not empty subscriptionList}">
						<c:forEach items="${subscriptionList}" var="subscription">
							<div class="card" style="width: 500px;">
								<div class="row no-gutters">
									<div class="col-sm-7">
										<div class="card-body">
											<h5 class="card-title">${subscription.drugName }</h5>
											<p class="card-text">Quantity: ${subscription.quantity }</p>
											<p class="card-text">Refill Cycle Period:
												${subscription.refillCycle }</p>
											<p class="card-text">Subscription Date:
												${subscription.subscriptionDate }</p>
											<p class="card-text">Subscription Status:
												${subscription.subscriptionStatus }</p>
											<p class="card-text">Delivery Location:
												${subscription.memberLocation }</p>
											<form method=post
												action="unsubscribe/${subscription.subscriptionId }">
												<input class="btn btn-primary" type=submit
													value="Unsubscribe">
													<a class="btn btn-primary" href="adhocRefill/${subscription.subscriptionId}"> Request refill</a>
											</form>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<h1>${msg}</h1>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<!-- ======= Footer ======= -->
	<div class="footer-margin">
		<footer id="footer">
			<div class="footer-top">
				<div class="container"></div>
			</div>

			<div class="container">
				<div class="credits">Thank you! We are here for your needs.</div>
			</div>
		</footer>
		<!-- End Footer -->
	</div>
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