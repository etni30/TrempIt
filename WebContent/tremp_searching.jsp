<%@page import="org.omg.CORBA.ExceptionList"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="Model.*, Controller.*, java.util.LinkedList"%>
<%
	String userName = session.getAttribute("userName").toString();

	String Origin = request.getParameter("Origin");
	String destination = request.getParameter("destination");
	String time = request.getParameter("time");
	String priority = request.getParameter("priority");
	Model m = new Model();  // TODO change to view after finishig tests 
	try{
		//parameter and initialization
		User user = m.getUser(userName);
		//save userName for next page
		session.setAttribute("userName", userName);
		
	%>
<%
// The searching algorithm
	//Controller con = new Controller();
	//LinkedList <Tramp> groupList = con.Find_Tremp(Origin, destination, time, priority);
%>
<%
out.print("tessssssssst");

	LinkedList<Group> groupList = m.getGroups();

%>

<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", ariel}
table.groove {position:ralative;  margin-left: auto; margin-right: auto; }
.submit {position:ralative;  margin-left: auto; margin-right: auto; }
th.groove {border-left-style: double;  padding-left: 50px; padding-right: 50px; }
th.margin {font-family: Comic Sans MS, Comic Sans, cursive;  padding-left: 15px; padding-right: 15px; padding-top: 10px; padding-bottom: 10px;
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
	<!--  searching form  -->
	<form action="search_resault.jsp"> 
	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
	      <% for(Group i: groupList){%>
			  <tr class="groove">
			  	<th><input type="radio" name="trempBox" value="<%out.print(i.getIdDriver());%>" /></th>
				<th class="margin"><% out.print(i.getTime());%></th>
			    <th class="margin"><% out.print(i.getSourceStation()); %></th>
			    <th class="margin"><% out.print(i.getDestStation()); %></th>
			    <th class="margin"><% out.print(i.getAmount()); %></th>
			  </tr>
			  <%} %>
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
<%	}catch(Exception e){
	out.println(e);
} %>

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