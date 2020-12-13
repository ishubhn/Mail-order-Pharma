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

  <title>Mail Order Pharmacy</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="images/favicon.png" rel="icon">
  <link href="images/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,700,700i|Poppins:300,400,500,700" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
  <link href="vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="style/style.css" rel="stylesheet">

</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header-transparent">
    <div class="container">

      <div id="logo" class="pull-left">
      <h3><a href="Mail Order Pharmacy">Mail Order Pharmacy</a></h3>
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
          <li class="menu-active"><a href="welcome.html">Home</a></li>
          <li><a href="prescriptionform">Subscribe</a></li>
          <li><a href="#services">Supported drugs</a></li>
          <li><a href="subscriptions">Subscribed medicines</a></li>
          <li><a href="#due-for-refill">Due for refill</a></li>
          <li><a href="#ad-hoc-requests">Request now</a></li>
          <li><a href="">Logout</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- End Header -->

  <!-- ======= Hero Section ======= -->
  <section id="hero">
    <div class="hero-container" data-aos="zoom-in" data-aos-delay="100">
      <h1>Welcome to our Pharmacy</h1>
      <h2>We deliver your pharmaceutical needs to your doorstep</h2>
      <a href="#subscribe" class="btn-get-started">Get Started</a>
    </div>
  </section><!-- End Hero Section -->

  <main id="main">

    <!-- ======= Subscribe Section ======= -->
    <section id="subscribe">
      <div class="container" data-aos="fade-up">
        <div class="row subscribe-container">

          <div class="col-lg-6 content order-lg-1 order-2">
            <h2 class="title">Subscribe with us!</h2>
            <p>
              We provide timely deliveries of your medicines and take care of all your pharmaceutical needs. Our delivary agents brave bad weather and high tempratures to make sure your regular dosage requirements are met.
            </p>

            <div class="icon-box" data-aos="fade-up" data-aos-delay="100">
              <div class="icon"><i class="fa fa-medkit"></i></div>
              <h4 class="title"><a href="prescriptionform">Subscribe</a></h4>
              <p class="description">We provide the medicines you require at your requested dosages and at your frequency. You can unsubscribe at any time.</p>
            </div>

            <div class="icon-box" data-aos="fade-up" data-aos-delay="200">
              <div class="icon"><i class="fa fa-heart"></i></div>
              <h4 class="title"><a href="subscriptions">Unsubscribe</a></h4>
              <p class="description">We hope you feel much better now! We are here for you if you need us.</p>
            </div>

          </div>

          <div class="col-lg-6 background order-lg-2 order-1" data-aos="fade-left" data-aos-delay="100"></div>
        </div>

      </div>
    </section><!-- End Subscribe Section -->

    <!-- ======= Facts Section ======= -->
    <section id="facts">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <h3 class="section-title">Facts</h3>
          <p class="section-description">Our current figures</p>
        </div>
        <div class="row counters">

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">1821</span>
            <p>Customers</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">316</span>
            <p>Pharmacies</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">1,463</span>
            <p>Hours Of Support</p>
          </div>

          <div class="col-lg-3 col-6 text-center">
            <span data-toggle="counter-up">15</span>
            <p>Hard Workers</p>
          </div>

        </div>

      </div>
    </section><!-- End Facts Section -->

    <!-- ======= Supported Drugs Section ======= -->
    <section id="services">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <h3 class="section-title">Supported Drugs</h3>
          <p class="section-description">Please enter your location to search for the drugs delivarable in your area.</p>
        </div>
        <div class="md-form md-bg input-with-pre-icon">
          <form name="supportedDrugs" action="/supportedDrugs" method="post" model="LocationModel">
            <label for="prefixInside4">Enter your location</label>
            <input type="text" id="prefixInside4" class="form-control">
            <br>
            <button type="submit" class="btn btn-outline-success btn-lg ">Submit</button>
          </form>
          </div>
        </div>
    </section><!-- End Services Section -->

    <!-- ======= View Subscribed Drugs Section ======= -->
    <section id="view-subscribed-drugs">
      <div class="container">
        <div class="row" data-aos="zoom-in">
          <div class="col-lg-9 text-center text-lg-left">
            <h3 class="cta-title">Subscribed Drugs</h3>
            <p class="cta-text"> You can view all your subscribed drugs at the click of a button!</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="subscriptions">View</a>
          </div>
        </div>

      </div>
    </section><!-- End View Subscribed Drugs Section -->

    <!-- ======= Due for Refill Section ======= -->
    <section id="due-for-refill">
      <div class="container" data-aos="fade-up">
        <div class="section-header">
          <h3 class="section-title">Due for refill</h3>
          <p class="section-description">Please enter the date to check for dues.</p>
        </div>
        <div class="md-form md-bg input-with-pre-icon">
          <form name="dueForRefill" action="/dueForRefill" method="post" model="DateModel">
            <label for="example-date-input" class="col-2 col-form-label">Date</label>
            <input class="form-control" type="date" value="2011-08-19" id="example-date-input">
            <br>
            <button type="submit" class="btn btn-outline-success btn-lg ">Submit</button>
          </form>
          </div>
        </div>
    </section><!-- End Services Section -->

    <!-- ======= Adhoc Request Section Section ======= -->
    <section id="ad-hoc-request">
      <div class="container">
        <div class="row" data-aos="zoom-in">
          <div class="col-lg-9 text-center text-lg-left">
            <h3 class="cta-title">Immediate Request Delivary</h3>
            <p class="cta-text"> We have got you covered if you require extra dosages for your medicine immediately, seperate from the monthly delivery</p>
          </div>
          <div class="col-lg-3 cta-btn-container text-center">
            <a class="cta-btn align-middle" href="AdHoc">Click to order</a>
          </div>
        </div>

      </div>
    </section><!-- End View Subscribed Drugs Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="footer-top">
      <div class="container">

      </div>
    </div>

    <div class="container">
      <div class="credits">
        Thank you! We are here for your needs.
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top"><i class="fa fa-chevron-up"></i></a>

  <!-- Vendor JS Files -->
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