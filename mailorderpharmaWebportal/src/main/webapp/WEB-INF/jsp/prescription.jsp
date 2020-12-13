<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <!DOCTYPE html>
 <html lang="en">
  <head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Prescription Form</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="images/favicon.png" rel="icon">
  <link href="images/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">
<!--   <link rel="stylesheet" type="text/css" href="summa1.css"> -->

  <!-- Vendor CSS Files -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
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
        <a href="welcome">Mail Order Pharmacy</a>
        <!-- Uncomment below if you prefer to use a text logo -->
        <!--<h1><a href="#hero">Regna</a></h1>-->
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-active"><a href="welcome.html">Home</a></li>
          <li><a href="#subscribe">Subscribe</a></li>
          <li><a href="#services">Supported drugs</a></li>
          <li><a href="#view-subscribed-drugs">Subscribed medicines</a></li>
          <li><a href="#due-for-refill">Due for refill</a></li>
          <li><a href="#ad-hoc-requests">Request now</a></li>
          <li><a href="">Logout</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- End Header -->
  <section>
  <h1>
   Prescription details
  </h1>
  <form name="prescriptionform" model="prescriptionDetails" method="post" action="subscribe">
    
    <div class="form-group">
      <label for="insuranceProvider">Insurance Provider</label>
      <input type="text" class="form-control" name="insuranceProvider" placeholder="">
    </div>
    <div class="form-group">
      <label for="policyNumber">Policy Number</label>
      <input type="text" class="form-control" name="policyNumber" placeholder="">
    </div>
    <div class="form-group">
      <label for="doctorName">Doctor Name</label>
      <input type="text" class="form-control" name="doctorName" placeholder="Enter Doctor name">
    </div>
    <div class="form-group">
      <label for="prescriptionDate">Prescription Date</label>
      <input type="date" class="form-control" name="prescriptionDate" placeholder="Date">
    </div>
    <div class="form-group">
      <label for="Location">Your Location</label>
      <input type="text" class="form-control" name="memberLocation" placeholder="">
    </div>
    <div class="form-group">
      <label for="drugName">Medicine Name</label>
      <input type="text" class="form-control" name="drugName" placeholder="">
    </div>
    <div class="form-group">
      <label for="dosageDefinition">Dosage Definitions</label>
      <input type="text" class="form-control" name="dosageDefinition" placeholder="">
    </div>
    <div class="form-group">
      <label for="quantity">Quantity</label>
      <input type="number" class="form-coquantityntrol" name="quantity" placeholder="">
    </div>
    <div class="form-group">
      <label for="courseDuration">Course Duration</label>
      <input type="number" class="form-coquantityntrol" name="courseDuration" placeholder="">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
<h1>${msg}</h1>
<h1>Footer</h1>
<h1>Footer</h1>
  </section>
    <!-- ======= Footer ======= -->
  <footer id="footer" class="mt-auto fixed-bottom">
    <div class="footer-top">
      <div class="container">

      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong>Regna</strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!--
        All the links in the footer should remain intact.
        You can delete the links only if you purchased the pro version.
        Licensing information: https://bootstrapmade.com/license/
        Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/buy/?theme=Regna
      -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End Footer -->
  
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