<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/onlinelib/css/bootstrap.min.css"/>
<script src="resources/onlinelib/js/jquery.min.js"></script>
<script src="resources/onlinelib/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/onlinelib/css/font-awesome.min.css"/>
<script src="resources/onlinelib/js/angular.min.js"></script>
<script src="resources/onlinelib/js/angular-route.js"></script>
<script src="resources/js/app.js"></script>
</head>
<body ng-app="app">
<nav class="navbar navbar-inverse" style="margin:0;border-radius:0">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#!home">ChatToGroup</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#!home">Home</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Page 1-1</a></li>
            <li><a href="#">Page 1-2</a></li>
            <li><a href="#">Page 1-3</a></li>
          </ul>
        </li>
        <li><a href="#!aboutUs">About Us</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#!register"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#!login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid" style="padding:0;background-color:#656565" ng-view></div>
<footer>
<style>.a{color: white}</style>
<div class="container-fluid" style="padding-top:20px;padding-bottom:20px;background:#000066;font-size:25px;color:white">
<div class="col-sm-6 col-md-3">
				<h3>Information</h3>
				<ul class="foot-info">
					<li><a class="a" href="#!aboutUs">FAQ's</a></li>
					<li><a class="a" href="#!aboutUs">About Us</a></li>
					<li><a class="a" href="">Find Friends</a></li>
					<li><a class="a" href="">Blogs</a></li>
				</ul></div>
				<div class="col-sm-6 col-md-3">
				<h3>Quick Links</h3>
				<ul class="foot-info">
					<li><a class="a" href="">My Account</a></li>
					<li><a class="a" href="">Login</a></li>
					<li><a class="a" href="">Sign Up</a></li>
					<li><a class="a" href="">Logout</a></li>
				</ul></div>
				<div class="col-sm-6 col-md-3">
				<h3>&nbsp;&nbsp;&nbsp;Contact Us</h3>
				<ul class="foot-info" type="none">
					<li><span class="glyphicon glyphicon-map-marker"></span> New Delhi, India</li>
					<li><span class="glyphicon glyphicon-envelope"></span> test@test.com</li>
					<li><i class="fa fa-phone" style="color:white"></i> +1234 567 890</li>
				</ul></div>
				<div class="col-sm-6 col-md-3 text-center">
				<h3>Follow us on</h3>
				<div class="btn-group">
  <a href="https://www.facebook.com" class="btn btn-success" style="width:5em">
  <i class="fa fa-facebook" style="font-size:40px;color:#fff"></i></a>
  <a href="https://www.twitter.com" class="btn btn-success" style="width:5em">
  <i class="fa fa-twitter" style="font-size:40px;color:#fff"></i></a>
  <a href="https://plus.google.com" class="btn btn-success" style="width:5em">
  <i class="fa fa-google-plus" style="font-size:40px;color:#fff"></i></a>
  <a href="https://www.instagram.com/?hl=en" class="btn btn-success" style="width:5em">
  <i class="fa fa-instagram" style="font-size:40px;color:#fff"></i></a>
</div>
				</div></div>
<nav class="navbar navbar-inverse">
  <div class="container-fluid text-center">
		<span style="color:#fff;font-size:30px">Created By Himanshu</span>
  </div>
</nav>
</footer>
</body>
</html>
</body>
</html>