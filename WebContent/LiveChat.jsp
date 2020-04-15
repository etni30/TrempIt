<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Controller.Controller ,Model.*, java.util.ArrayList"%>
<%
try{
	User user =(User)session.getAttribute("user");
	session.setAttribute("User", user);
	
	String driverName = request.getParameter("trempBox");
	Controller conn = new Controller();
	Group g = conn.getGroupForUser(user.getIdUser());
%>
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <link rel="stylesheet" href="cssFile/demostyle.css">
    <title>Places Search Box</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        bottom: 60%;
        height: 60%;
        width: 50%;
        left: 35%;
        
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #description {
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
      }

      #infowindow-content .title {
        font-weight: bold;
      }

      #infowindow-content {
        display: none;
      }

      #map #infowindow-content {
        display: inline;
      }

      .pac-card {
        margin: 10px 10px 0 0;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
        background-color: #fff;
        font-family: Roboto;
      }

      #pac-container {
        padding-bottom: 12px;
        margin-right: 12px;
      }

      .pac-controls {
        display: inline-block;
        padding: 5px 11px;
      }

      .pac-controls label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }

      #pac-input {
       }

      #pac-input:focus {
       
      }

      #title {
        color: #fff;
        background-color: #4d90fe;
        font-size: 25px;
        font-weight: 500;
        padding: 6px 12px;
      }
      #target {
        width: 70%;
        
        position:relative;
        
      }
    </style>
  </head>
  <body>
  
  <body class="w3-light-grey">

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">

    <!-- Left Column -->
    <div class="w3-third">

      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="cssFile/images/driver.png" style="width:100%" alt="Avatar" >
           <div class="w3-display-bottomleft w3-container w3-text-black">
            <h2>TrampIt</h2>
          </div>
         </div>
        <div class="w3-container">
          <hr>
 
        <div class="w3-light-grey w3-round-xlarge w3-small">
         
         	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>YOUR TREMP DETAILS:</h4> 
      <table class="groove">
		  <tr class="groove"><h4><%out.print("group id:" + g.getGroupId()); %></h4></tr>
		  <tr class="groove"><h4><%out.print("departure time:" + g.getDepTime()); %></h4></tr>
		  <tr class="groove"><h4><%out.print("departure station: " + g.getSourceStation() 
		  								+ ", " + g.getSourceCity()); %></h4></tr>
		  <tr class="groove"><h4><%out.print("destination station: " + g.getdstStation() 
		  								+ ", " + g.getdstCity()); %></h4></tr>
		  <tr class="groove"><h4><%out.print("place left:" + (4 - g.getAmount())); %></h4></tr>
	  </table>
	  <%if(user instanceof Driver){ %>
		<a href="delete_tremp.jsp">click here to delete the tremp</a>
         <%} %>
        </div>
          <img id="back" src="cssFile/images/button.png" />
        </div>
        

      </div>
      </br>

<% }catch(Exception e){
  %>	<script >alert("Data got lost \n log in again");</script >
		<script >window.location.href = "clear_page.jsp";</script >	
<%}%>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">

      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>YOUR TREMPBOX</h2>
      	<!--  searching form  -->
	<form action="#" onsubmit="me.route()"> 
			  <div class="w3-container w3-card w3-white w3-margin-bottom">
			  <!-- search parameters -->
			      <table class="groove">
					  <tr class="groove">
					 <input id="origin-input" class="controls" type="text"
		            placeholder="Enter an origin location">
		
		        <input id="destination-input" class="controls" type="text"
		            placeholder="Enter a destination location">
					 <div id="mode-selector" class="controls">
				 click on one of the buttons to see how to travel:
				 </br>
		          <input type="radio" name="type" id="changemode-walking" checked="checked">
		          <label for="changemode-walking">Walking</label>
		
		          <input type="radio" name="type" id="changemode-transit">
		          <label for="changemode-transit">Transit</label>
		
		          <input type="radio" name="type" id="changemode-driving">
		          <label for="changemode-driving">Driving</label>
		        </div>
		    </div>
					 
			  </tr>
		  </table>
      </div>

	  	</div>
	  	</form>
	
    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->
</div>

    <div id="map"></div>
    
    <footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find me on social media.</p>
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
</footer>

    <script>

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    mapTypeControl: false,
    center: {lat: -33.8688, lng: 151.2195},
    zoom: 13
  });

  new AutocompleteDirectionsHandler(map);
}

/**
 * @constructor
 */
function AutocompleteDirectionsHandler(map) {
  this.map = map;
  this.originPlaceId = null;
  this.destinationPlaceId = null;
  this.travelMode = 'WALKING';
  this.directionsService = new google.maps.DirectionsService;
  this.directionsRenderer = new google.maps.DirectionsRenderer;
  this.directionsRenderer.setMap(map);

  var originInput = document.getElementById('origin-input');
  var destinationInput = document.getElementById('destination-input');
  var modeSelector = document.getElementById('mode-selector');

  var originAutocomplete = new google.maps.places.Autocomplete(originInput);
  // Specify just the place data fields that you need.
  originAutocomplete.setFields(['place_id']);

  var destinationAutocomplete =
      new google.maps.places.Autocomplete(destinationInput);
  // Specify just the place data fields that you need.
  destinationAutocomplete.setFields(['place_id']);

  this.setupClickListener('changemode-walking', 'WALKING');
  this.setupClickListener('changemode-transit', 'TRANSIT');
  this.setupClickListener('changemode-driving', 'DRIVING');

  this.setupPlaceChangedListener(originAutocomplete, 'ORIG');
  this.setupPlaceChangedListener(destinationAutocomplete, 'DEST');


}

// Sets a listener on a radio button to change the filter type on Places
// Autocomplete.
AutocompleteDirectionsHandler.prototype.setupClickListener = function(
    id, mode) {
  var radioButton = document.getElementById(id);
  var me = this;

  radioButton.addEventListener('click', function() {
    me.travelMode = mode;
    me.route();
  });
};

AutocompleteDirectionsHandler.prototype.setupPlaceChangedListener = function(
    autocomplete, mode) {
  var me = this;
  autocomplete.bindTo('bounds', this.map);

  autocomplete.addListener('place_changed', function() {
    var place = autocomplete.getPlace();

    if (!place.place_id) {
      window.alert('Please select an option from the dropdown list.');
      return;
    }
    if (mode === 'ORIG') {
      me.originPlaceId = place.place_id;
    } else {
      me.destinationPlaceId = place.place_id;
    }
    if(me.originPlaceId != null && me.destinationPlaceId != null){
    	  me.distance = google.maps.geometry.spherical.computeDistanceBetween(this.originPlaceId, this.destinationPlaceId);
    	  console.log(me.distance);
    }
    me.route();
  });
};

AutocompleteDirectionsHandler.prototype.route = function() {
	//var distance = google.maps.geometry.spherical.computeDistanceBetween(this.originPlaceId, this.destinationPlaceId);
	//console.log(distance);
  if (!this.originPlaceId || !this.destinationPlaceId) {
    return;
  }
  var me = this;
  this.directionsService.route(
      {
        origin: {'placeId': this.originPlaceId},
        destination: {'placeId': this.destinationPlaceId},
        travelMode: this.travelMode
      },
      function(response, status) {
        if (status === 'OK') {
          me.directionsRenderer.setDirections(response);
        } else {
          window.alert('Directions request failed due to ' + status);
        }
      });

};

var img = document.getElementsByClassName('back');
document.addEventListener('click', function(e){
	if(e.target && e.target.id== 'back'){
		window.location.href = "mainpage.jsp";
	}
});


    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDopI8aARx_Cu5Eo6A3VHvTMKOeqdX9KZE&libraries=places&callback=initMap"
        async defer></script>

  </body>
</html>