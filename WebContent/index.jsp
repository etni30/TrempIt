<%@page import="API.ControllerInterface"%>
<%--<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%> --%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Controller.Controller"%>
    
<% 
	ControllerInterface con = new Controller();
	try{
		
		con.createDB();	// Create DB if doesn't exist
		
%>

<!DOCTYPE html>
<html>
<title>TrampIT</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<body class="w3-light-grey">


<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">

    <!-- Left Column -->
    <div class="w3-third">

      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="cssFile/images/Logo.jpeg" style="width:100%" alt="Avatar" >
           <div class="w3-display-bottomleft w3-container w3-text-black">
          </div>
         </div>
        <div class="w3-container">
          <hr>
        <p>Account</p>
        <div class="w3-light-grey w3-round-xlarge w3-small">
          <div id="sign_in" class="w3-container w3-center w3-round-xlarge w3-teal w3-hight" style="width:100%">
            sign-up
          </div>
          </br>
          <div id="log_in" class="w3-container w3-center w3-round-xlarge w3-teal w3-hight" style="width:100%">
            log-in
          </div>
          </br>
        </div>
          <br>
        </div>

      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">

      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Welcome to TrampIT!</h2>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>Take a ride</b></h5>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>april 2020 - <span class="w3-tag w3-teal w3-round">Current</span></h6>
          <p>TrampIt: More than a ride app! Use it for private transit navigation, maps, schedules, real-time arrivals and more!</p>
          <hr>
        </div>

        <div class="w3-container">
          <h5 class="w3-opacity"><b>About as</b></h5>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>what do we do?</h6>
          <p>TrampIt is finding you better ways to move, work, and succeed all over the country.
           we offering services that include peer-to-peer ridesharing. sign up to discover a new world of transportation</p><br>
        </div>
      </div>

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->
</div>

<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find me on social media.</p>
  <p><a>Powered by Hagashi & co.</a></p>
</footer>
<%}catch(Exception e){ %>
<script >alert(<%out.print(e.getMessage());%>);</script>
<%} %>
<script type="text/javascript" src="javaScript/sign_in.js"></script>
<script type="text/javascript" src="javaScript/log_in.js"></script>
<script type="text/javascript" src="javaScript/index.js"></script> 
</body>
</html>
