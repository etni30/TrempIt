<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Model.*,java.util.LinkedList"%>

<%
try{
	//parameter and initialization
	Model m = new Model();
	User user = (User)session.getAttribute("User");
	LinkedList<String> station = m.getStations();
	//LinkedList<String> city = m.getCities(); TODO
	LinkedList<String> city = new LinkedList<String>();
	city.add("raanana");
	city.add("ramat gan");
	//save userName for next page
	session.setAttribute("User", user);
	
%>
<!DOCTYPE html>

<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
table.groove {position:ralative;  margin-left: auto; margin-right: auto; }
.submit {position:ralative;  margin-left: auto; margin-right: auto; }
th.groove {border-left-style: double;  padding-left: 50px; padding-right: 50px; }
th.margin {  padding-left: 15px; padding-right: 15px; padding-top: 20px; padding-bottom: 20px}
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
	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>INSERT PARAMETERS</h4> 
      <table class="groove">
		  <tr class="groove">
		    <th class="groove">departure time</th>
		    <th class="groove">Origin station</th>
		    <th class="groove">dest. station</th>
		    <th class="groove">Origin station</th>
		    <th class="groove">dest. city</th>
		  </tr>
	  </table>
	<!-- ---------------------------- searching form -------------------------------------- -->
	<form name="myForm" action="tremp_searching.jsp" onsubmit="return validateForm()"> 
	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
			  <tr class="groove">
				<th class="margin"><input type="time" name="time"/ required="required"></th>
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
			    <th class="margin">
			    	<select name= "orgCity">
			    	<%for(String x: city) { %>
					    <option name="<% out.print(x);%>"><% out.print(x);%></option>
					<%} %>
					</select >
				</th>
			    <th class="margin">
			    	<select name= "destCity">
			    	<%for(String x: city) { %>
					    <option name="<% out.print(x);%>"><% out.print(x);%></option>
					<%} %>
					</select >
				</th>
			  </tr>
		  </table>
	  	</div>
	  	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>CHOOSE PRIORITY</h4>
	  	      <table class="groove">
		  <tr class="groove">
		    <th >your priority</th>
		  </tr>
	  </table>

	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
			  <tr class="groove">
			    <th >
			    	<select name="priority">
					    <option value="0">distance from Origin</option>
					    <option value="1">distance from destination</option>
					    <option value="2">departure time</option>
					   <!--  <option value="rank">driver rank</option> -->
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
<%	}catch(Exception e){
	out.println(e);
%>
<script type="text/javascript">window.location.href = "mainpage.jsp"</script>
<%} %>
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
		  var orgCity = document.forms["myForm"]["orgCity"].value;
		  var destCity = document.forms["myForm"]["destCity"].value;
		  if (Origin == destination || orgCity == destCity) {
		    alert("you cannot choose two places from the same city \n\n \t\t\t\t try again");
		    return false;
		  }
		}
</script>

</body>
</html>