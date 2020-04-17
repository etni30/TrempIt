<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Model.*"%>
<%@ page session="true" %>
<%
	
	try{
		User user= (User)session.getAttribute("User");
		session.setAttribute("user", user);
		
		String type = null;
		if(user instanceof Passenger)
			type = "Passenger";
		else if(user instanceof Driver)
			type = "Driver";
		else
			type = "Admin";
		Model m = new Model();

%>

<!DOCTYPE html>
<html>
<title>W3.CSS Template</title>
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
          <div  class="w3-center w3-hight">
            <%
            	out.println("type: " + type);
            %>
          </div>
        </div>
          <br>
          <img id="back" src="cssFile/images/button.png" />
        </div>

      </div><br>
		
    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>USER MENU</h2>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          <%
          if(type.equals("Passenger") && user.getIsInARide() == false){ 
          %>	
          	<a href="automaticSearch.jsp">Automatic search</a>
          <%}else if(type.equals("Driver") && user.getIsInARide() == false)
          { // TODO add driver tremp %>
          	<a href="creating_new_tremp.jsp">create a new tremp</a>
          <%}else if(user instanceof Admin) {%>
          <a href="show_tables.jsp">Show tables</a>	
          	<%}else{ 
          		out.print("Automatic search - (you are already signed to a tremp)");
          	} %>
          </b></h5>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>details<span class="w3-tag w3-teal w3-round">recommended</span></h6>
          <p>Lorem ipsum dolor sit amet. Praesentium magnam consectetur vel in deserunt aspernatur est reprehenderit sunt hic. Nulla tempora soluta ea et odio, unde doloremque repellendus iure, iste.</p>
          <hr>
        </div>

                    <%
          	if(user instanceof Admin){%>
          	
        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          		<a href="LiveChat.jsp">Edit users</a>
          <%}else{%>
          travel chat (
	
          	
        		 <% if( user.getIsInARide()){
	        		  	%><a href="LiveChat.jsp">trampBox</a>
        		  <%}else{
	        			  out.println("not available - sign to a tramp first " /* + user.isInARide() */);
        		  }
        		%>)</b></h5> <%} %>
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>details</h6>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. </p><br>
        </div>
       <%if(user instanceof Admin){%>
      <div class="w3-container">
          <h5 class="w3-opacity"><b>
          		<a href="LiveChat.jsp">Add stations</a>     
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>details</h6>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. </p><br>
        </div>
        <%}if(user instanceof Admin){%>
      <div class="w3-container">
          <h5 class="w3-opacity"><b>
          		<a href="LiveChat.jsp">Change Edge Distance</a>     
          <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>details</h6>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. </p><br>
        </div>
     
<%}%>
      </div>
      </div>

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->

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
			alert("goodbye");
			window.location.href = "clear_page.jsp";
		}
	});
	
</script>

</body>
</html>