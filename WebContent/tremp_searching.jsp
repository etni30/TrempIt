<%@page import="com.sun.org.apache.xml.internal.security.algorithms.Algorithm"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.omg.CORBA.ExceptionList"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Model.*, Controller.*, java.util.LinkedList"%>
<%
try{
	User user = (User)session.getAttribute("User");
	//save userName for next page
	session.setAttribute("User", user);

		//Searching Algorithm
		ArrayList<Path> pathResult;
		try{
			pathResult= (ArrayList<Path>)session.getAttribute("PathResult");

			%>

<!DOCTYPE html>
<html>
<title>TrampIT</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", ariel}
table.groove {position:ralative;  margin-left: auto; margin-right: auto; }
.submit {position:ralative;  margin-left: auto; margin-right: auto; }
th.groove {border-left-style: double;  padding-left: 50px; padding-right: 50px; }
th.margin {font-family: Comic Sans MS, Comic Sans, cursive;  padding-left: 55px; padding-right: 55px; padding-top: 10px; padding-bottom: 10px;
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
	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>INSERT PARAMETERS</h4> 
      <table class="groove">
		  <tr class="groove">
		    <th class="groove">departure time</th>
		    <th class="groove">Origin </th>
		    <th class="groove">destination</th>
		    <th class="groove">places left</th>
		  </tr>
	  </table>
	  
	<!--  algorithm result form (user choose group) -->
	<form action="AddPassengerServlet" method="Post"> 
	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
	      <% if(pathResult.size()> 0)
				
			  for(Path i: pathResult){%>
			  <tr class="groove">
			  	<th><input type="radio" name="idGroup" value="<%out.print(i.getG().getGroupId());%>" required="required"/></th>
				<th class="margin"><% out.print(i.getG().getDepTime());%></th>
			    <th class="margin"><% out.print(i.getG().getSourceStation()); %></th>
			    <th class="margin"><% out.print(i.getG().getdstStation()); %></th>
			    <th class="margin"><% out.print(i.getG().getAmount()); %></th>
			  </tr>
			  
			  <%}
	      //The algorithm didnt find results
	      else{
			  %>	
			  <tr>
			  <th>
			  <script>alert("the system didnt find result for you \n \t\t\ttry again");</script >
			  <script>window.location.href = "automaticSearch.jsp";</script >
			  <%}// if time expired or someone tried to get access without permission
					}catch(NullPointerException e){
						%><script >alert("Connection has lost \n log in again");</script >
							<script >window.location.href = "clear_page.jsp";</script >
			  <%	}%>
	       </th>
	      </tr>
		  </table>
	  	</div>

	  	<div class ="submit" ><input type="submit" value="search"></div>
	  	</form><!-- end of form -->
 

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->
</div>
<% }catch(Exception e){
  %>	<script >alert("<%out.print(e);%>" + "Data got lost \n log in again");</script >
		<script >window.location.href = "clear_page.jsp";</script >	
<%}%>

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
	
</script>

</body>
</html>