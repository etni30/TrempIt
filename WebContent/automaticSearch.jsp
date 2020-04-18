<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Controller.Controller, Model.*,java.util.LinkedList"%>
<!-- Passenger searching page -->
<%
try{
	//parameter and initialization
	Controller conn = new Controller();
	User user = (User)session.getAttribute("User");
	LinkedList<String> station = conn.getStations();
	
	//save userName for next page
	session.setAttribute("User", user);
	
%>
<!DOCTYPE html>

<html>
<title>TrampIT</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
table{overflow: auto; white-space;}
table.groove {overflow: auto; white-space; nowrap; position:ralative;  margin-left: auto; margin-right: auto; }
.submit {position:ralative;  margin-left: auto; margin-right: auto; }
th.groove {border-left-style: double;  padding-left: 65px; padding-right: 65px; }
th.margin {font-family: Comic Sans MS, Comic Sans, cursive;  padding-left: 65px; padding-right: 65px; padding-top: 10px; padding-bottom: 10px;
			background-color: #d4bce1;}
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
          <img src="cssFile/images/driver.png" style="width:100%" alt="Avatar" >
           <div class="w3-display-bottomleft w3-container w3-text-black">
            <h2>TrampIt</h2>
          </div>
         </div>
        <div class="w3-container">
          <hr>
        <p>User account</p>
        <div class="w3-light-grey w3-round-xlarge w3-small">
          <div  class="w3-center w3-hight">
			<%
				out.println("First name: " + user.getFirstName());
			%>
          </div>
		  </br>
          <div  class="w3-center w3-hight">
			<%
				out.println("Last name: " + user.getLastName());
			%>
          </div>
          </br>
          <div  class="w3-center w3-hight">
			<%
				out.println("User name: " + user.getUserName());
			%>
          </div>
          </br>
        </div>
          <br>
          <img id="back" src="cssFile/images/button.png" />
        </div>

      </div>
      </br>


    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">

      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>TRAMP SEARCHING (AUTO)</h2>
      </div>
	<!--  names of parameters -->
	<h3 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>INSERT PARAMETERS</h3> 
		<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>arrival and departure time</h4>
	<!-- ---------------------------- searching form -------------------------------------- -->
	<form name="myForm" action="TrempSearchingServlet" onsubmit="return validateForm()" method="Post"> 
	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
		   	  <tr class="groove">
			    <th class="groove">departure time</th>
			    <th class="groove">Arrival time</th>
			   </tr>

			  <tr class="groove">
				<th class="margin"><input type="time"  name="departureT"/ required="required"></th>
				<th class="margin"><input type="time" name="desiredArriveT"/ required="required"></th>
			  </tr>


		  </table>
	  	</div>
	  		<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Location</h4>
	  	<div class="w3-container w3-card w3-white w3-margin-bottom">
	      <table class="groove">
			  <tr>
			    <th class="groove">Origin station</th>
			    <th class="groove">dest. station</th>
			  </tr>
			  <tr>
			    <th class="margin">
			    	<select name="Origin" >
			    	<%for(String x: station) { %>
					    <option name="<% out.print(x);%>"><% out.print(x);%></option>
					<%} %>
					</select >
				</th>
			    <th class="margin">
			    	<select name= "destination">
			    	<%for(String x: station) { %>
					    <option name="<% out.print(x);%>"><% out.print(x);%></option>
					<%} %>
					</select >
				</th>
			  </tr>
		  </table>
	  	</div>
	  	</br>
	  	
	  	
	  	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>CHOOSE PRIORITY</h4>
	  	      <table class="groove">
		  <tr class="groove">
		    <th >your priority</th>
		  </tr>
	  </table>

	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	      <table class="groove">
			  <tr class="groove">
			    <th >
			    	<select name="priority">
					    <option value="0">Minimal walking distance</option>
					    <option value="1">Earliest arrive time</option>
					    <option value="2">Earliest departure time</option>
					</select >
				</th>
			  </tr>
		  </table>
	  	</div>
	  	</br>
	  	
	  	<div class ="submit" ><input type="submit" value="search"></div>
	  	</form>
	 	<!-- ---------------------------- end of form --------------------------------- -->
      

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->
</div>
<%// if time expired or someone tried to get access without permission
	}catch(NullPointerException e){
		%><script >alert("Connection has lost \n log in again");</script >
			<script >window.location.href = "clear_page.jsp";</script >
<%	}catch(Exception e){
%>	<script >alert("Data got lost \n log in again");</script >
	<script >window.location.href = "clear_page.jsp";</script >	
<%}%>
<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find me on social media.</p>

  <p>Powered by Hagashi & co.</a></p>
</footer>

<script >
	var img = document.getElementsByClassName('back');
	document.addEventListener('click', function(e){
		if(e.target && e.target.id== 'back'){
			window.location.href = "mainpage.jsp";
		}
	});
	
	function validateForm() {
		  var Origin = document.forms["myForm"]["Origin"].value;
		  var destination = document.forms["myForm"]["destination"].value;
		  var dstCity = destination.split(",")[1];
		  var srcCity = Origin.split(",")[1];
		  
		  var departureT = document.forms["myForm"]["departureT"].value;
		  var ArriveT = document.forms["myForm"]["desiredArriveT"].value;
		  
		  //form validation
		  
		  //check if the user Arrivel time is grater then departure time
		  if( departureT > ArriveT){
			  alert("your departure time is grater then arival disired time\n\n \t\t\t\t try again");
			  return false;
		  }
		  
		  // check if the user didn't choose two stations from the same city
		  if (Origin == destination || dstCity == srcCity) {
		    alert("you cannot choose two places from the same city \n\n \t\t\t\t try again");
		    return false;
		  }
		}
</script>

</body>
</html>