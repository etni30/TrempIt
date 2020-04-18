<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Controller.Controller, Model.Admin,Model.*, java.util.LinkedList"%>
<%     	
	try{
		Admin admin = (Admin)session.getAttribute("User");
		session.setAttribute("user", admin);
		String type = "Admin";
		
		LinkedList<Edge> edges = admin.getEdges();
		LinkedList<Group> groups = admin.getGroups();
		LinkedList<String> stations = admin.getStations();
		LinkedList<User> users = admin.getUsers();
%>

<!DOCTYPE html>
<html>
<title>TrampIT</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="cssFile/demostyle.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", ariel}
table{overflow: auto; white-space;}
table.groove {overflow: auto; white-space; nowrap; position:ralative;  margin-left: auto; margin-right: auto; }
.submit {position:ralative;  margin-left: auto; margin-right: auto; }
th.groove {border-left-style: double;  padding-left: 50px; padding-right: 50px; }
th.margin {font-family: Comic Sans MS, Comic Sans, cursive;  padding-left: 55px; padding-right: 55px; padding-top: 10px; padding-bottom: 10px;
			background-color: #d4bce1;}
</style>
<body class="w3-light-grey" >

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
        <p>User account</p>
        <div class="w3-light-grey w3-round-xlarge w3-small">
          <div  class="w3-center w3-hight">
			<%
				out.println("First name: " + admin.getFirstName());
			%>
          </div>
		  </br>
          <div  class="w3-center w3-hight">
			<%
				out.println("Last name: " + admin.getLastName());
			%>
          </div>
          </br>
          <div  class="w3-center w3-hight">
			<%
				out.println("admin name: " + admin.getUserName());
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
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>ADMIN MENU</h2>
        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          	<a href="#" onclick="hideEdgeTable()">Edge table</a>
<form action="UpdateEdgeServlet" method="POST">
        <table id="Edge">
        	
			  
			  <tr class="groove">
 				<th class="margin">choose edge</th>
				<th class="margin">City</th>
				<th class="margin">Distance</th>
			    <th class="margin">Station1</th>
			    <th class="margin">Station2</th>
			  </tr>        

		<%		
			  int j = 0;
		  	  for(Edge i: edges){%>
			  <tr class="groove">
			  <th><input type="radio" name="Edge" value="
			  <%out.print(i.getStation1() + "," + i.getStation2());%>" required="required"/></th>
			    <th class="margin"><% out.print(i.getCity());%></th>
				<th class="margin"><% out.print(i.getDistance());%></th>
			    <th class="margin"><% out.print(i.getStation1()); %></th>
			    <th class="margin"><% out.print(i.getStation2()); %></th>  			    
			  </tr>
			  
			  <%}%>

          </b></h5>
          </table>
			<input type="text" name="distance" value="Enter new distance" required="required"/>
           	    
          
          <h6 class="w3-text-teal"><span class="w3-tag w3-teal w3-round">   		
    		    <input type="submit" value="update"/></span></h6>
</form>

        </div>
        
        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          	<a href="#" onclick="hideStationsTable()">stations</a>
         <table id="stations">
		    <th class="margin"><% out.print("station"); %></th>
		    <th class="margin"><% out.print("city"); %></th>           	
          	<%
          	  j = 0;
		  	  for(String i: stations){%>
			  <tr class="groove">
			    <th class="margin"><% out.print(i.split(",")[0]);%></th>
			    <th class="margin"><% out.print(i.split(",")[1]);%></th>  			    
			  </tr>
			  <%}%>
			</table>
			
			
			<form action="addStationServlet"  method="post">
			
			<h5> Add new station</h5>
				<h5>City:
				<input type="text" name="city" required="required" oninput = "validateFields()"/>
				 </h5>
				 
				<h5>Station name:
				<input type="text" name="stationName" required="required"/>
				</h5>
				
          </b>
		  <h6 class="w3-text-teal"><span class="w3-tag w3-teal w3-round">
		  
    		    <input type="submit" name = "submitStation" value="add Station" /></span></h6>
    		    
			</form>
			
        </div>
        
        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          	<a href="#" onclick="hideGroupTable()">Group</a>
          <table id="Group">
			  <tr class="groove">
			  	
				<th class="margin"><% out.print("Amount");%></th>
				<th class="margin"><% out.print("dstCity");%></th>
			    <th class="margin"><% out.print("dstStation"); %></th>
			    <th class="margin"><% out.print("GroupId"); %></th>
			    <th class="margin"><% out.print("IdDriver"); %></th>
			    <th class="margin"><% out.print("SourceCity"); %></th>
			    <th class="margin"><% out.print("SourceStation"); %></th>
			    <th class="margin"><% out.print("DepTime"); %></th>
			    <th class="margin"><% out.print("UsersID1"); %></th>
			    <th class="margin"><% out.print("UsersID2"); %></th>
			    <th class="margin"><% out.print("UsersID3"); %></th>
			    <th class="margin"><% out.print("UsersID4"); %></th>
			  </tr>        
		<%		
			  j = 0;
			  for(Group i: groups){%>
			  <tr class="groove">
				<th class="margin"><% out.print(i.getAmount());%></th>
				<th class="margin"><% out.print(i.getdstCity());%></th>
			    <th class="margin"><% out.print(i.getdstStation()); %></th>
			    <th class="margin"><% out.print(i.getGroupId()); %></th>
			    <th class="margin"><% out.print(i.getIdDriver()); %></th>
			    <th class="margin"><% out.print(i.getSourceCity()); %></th>
			    <th class="margin"><% out.print(i.getSourceStation()); %></th>
			    <th class="margin"><% out.print(i.getDepTime()); %></th>
			    <th class="margin"><% out.print(i.getUsersID()[0]); %></th>
			    <th class="margin"><% out.print(i.getUsersID()[1]); %></th>
			    <th class="margin"><% out.print(i.getUsersID()[2]); %></th>
			    <th class="margin"><% out.print(i.getUsersID()[3]); %></th>			    			    
		</tr>
			  <%}%>
		 </table>
          </b></h5>
          <hr>
        </div>

        <div class="w3-container">
          <h5 class="w3-opacity"><b>
          	<a href="#" onclick="hideUserTable()">Users</a>
          	<table id="Users" onclick="myFunction(event)">
			  <tr class="groove">
				<th class="margin"><% out.print("select user");%></th>
				<th class="margin"><% out.print("Email");%></th>
				<th class="margin"><% out.print("FirstName");%></th>
			    <th class="margin"><% out.print("IdUser"); %></th>
			    <th class="margin"><% out.print("IsInARide"); %></th>
			    <th class="margin"><% out.print("LastName"); %></th>
			    <th class="margin"><% out.print("Password"); %></th>
			    <th class="margin"><% out.print("UserName"); %></th>
			  </tr>        
		<%		
			  j = 0;
			 // admin.u
			  for(User i: users){%>
			  <tr class="groove" id="row">
			  <th class="margin"><% out.print("clicked on the select user");%></th>
			    <th id="chosenUser" class="margin" value="<% out.print(i.getUserName()); %>"><% out.print(i.getUserName()); %></th>
				<th id="chosenUser" class="margin" value="<% out.print(i.getFirstName());%>"><% out.print(i.getFirstName());%></th>
			    <th id="chosenUser" class="margin" value="<% out.print(i.getIdUser());%>" ><% out.print(i.getIdUser()); %></th>
			    <th id="chosenUser" class="margin" value="<% out.print(i.getIsInARide()); %>"><% out.print(i.getIsInARide()); %></th>
			    <th id="chosenUser" class="margin" value="<% out.print(i.getLastName()); %>"><% out.print(i.getLastName()); %></th>
			    <th id="chosenUser" class="margin" value="<% out.print(i.getPassword()); %>"><% out.print(i.getPassword()); %></th>
				<th id="chosenUser" class="margin" value="<% out.print(i.getEmail());%>"><% out.print(i.getEmail());%></th>

		</tr>
			  <%}%>
		<form action="changeUserServlet"  method="post" >
		<tr id="userChange">
				<th><input type="checkbox" name="User" required="required" value="" ></th>
				
				<th><input type="text" name="UserName" value="enter username" required="required"/></th>			
				<th><input type="text" name="FirstName" value="enter FirstName" required="required"/></th>
				<th><input type="text" name="IdUser" value="enter IdUser" required="required" disabled/></th>
				<th><input type="text" name="IsInARide" value="enter IsInARide(1/0)" required="required"/></th>
				<th><input type="text" name="LastName" value="enter LastName" required="required"/></th>
				<th><input type="text" name="Password" value="enter Password" required="required"/></th>
				<th><input type="text" name="Email" value="enter Email" required="required"/></th>

		
		</tr>
		 </table>
          </b></h5>
		  <h6 class="w3-text-teal"><span class="w3-tag w3-teal w3-round">
    		    <input type="submit" value="change user"/></span></h6>
			</form>
        </div>
      </div>
      </div>

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->


<%	}
	catch(java.lang.IllegalStateException e){	// if there were session problems (if time expired)
%>	<script >alert("Session Expired. \n log in again");</script >
	<script >window.location.href = "clear_page.jsp";</script >	
<%}

//  if someone tried to get access without permission
	catch(NullPointerException e){
		%><script >alert("Connection has lost \n log in again");</script >
			<script >window.location.href = "clear_page.jsp";</script >
<%}
	catch(Exception e){
	%>
	<script >alert(<%e.toString();%>);</script >
			
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
	const edge = document.getElementById("Edge");
	const stations = document.getElementById("stations");
	const groups = document.getElementById("Group");
	const users = document.getElementById("Users");
	const userChange = document.getElementById("userChange");
	hideAll();
	
	function myFunction(event) {
		if(event.target.getAttribute("id") == "chosenUser")
		for( i= 1; i<8; i++){
		  userChange.children[i].children[0].value = event.target.parentNode.children[i].getAttribute("value");
		}
		userChange.children[0].children[0].value = event.target.parentNode.children[1].getAttribute("value");
		userChange.children[0].children[0].checked = "checked";
	}
	document.addEventListener('click', function(e){
		if(e.target && e.target.id== 'back'){
			alert("goodbye");
			window.location.href = "clear_page.jsp";
		}

	});

	
	function hideEdgeTable(){
		if(edge.style.display == "none"){
			edge.style.display = "block";
		}else{
			edge.style.display = "none";
		}
	}
	function hideStationsTable(){
		if(stations.style.display == "none"){
			stations.style.display = "block";
		}else{
			stations.style.display = "none";
		}
	}
	function hideGroupTable(){
		if(groups.style.display == "none"){
			groups.style.display = "block";
		}else{
			groups.style.display = "none";
		}
	}
	function hideUserTable(){
		if(users.style.display == "none"){
			users.style.display = "block";
		}else{
			users.style.display = "none";
		}
	}
	function hideAll(){
		hideEdgeTable();
		hideStationsTable();
		hideGroupTable();
		hideUserTable();
	}
	function setData(event) { 
		  alert(event.target.parentNode.nodeName);
	}
	

</script>

</body>
</html>