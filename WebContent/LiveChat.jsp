<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Model.*, java.util.ArrayList"%>
			<%
				String userName = request.getParameter("userName");
				String type = request.getParameter("type");
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
        <p>User chat:</p>

        <p> <%out.println("User: " + new User().getUserName() +", type: " + new User().getStatus().toString());%></p>
        <div class="w3-light-grey w3-round-xlarge w3-small">
          <div  class="w3-center w3-hight">

          </div>
          </br>
          <div  class="w3-center w3-hight">
          <form action="#">  
			  <div >
	            	<textarea id="userText" rows="4" cols="30">
	            	</textarea>
	          </div>
		  <div id="log_in" class= "w3-container w3-center w3-round-xlarge w3-teal w3-hight" style="width:100%">
            	<input type="submit" value="send mesage"/>
          </div>
          </form>
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

      
		<% Chat chat = new Chat(); 
		   ArrayList<Messages> mess = chat.getChat();
		%>
		<%for(int i = 0; i<chat.getNumOfMessage(); i++){%>
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <div class="w3-container">
          <h5 class="w3-opacity"><b><%out.println( mess.get(i).getName()); %></b></h5>
          <h6 class="w3-text-teal"><span class="w3-tag w3-teal w3-round"><%out.print(mess.get(i).getType());%> </span></h6>		           
          <p><% out.println(mess.get(i).getData());%></p><br>
        </div>
      </div>
        <%} %>
      

    <!-- End Right Column -->
    </div>

  <!-- End Grid -->
  </div>

  <!-- End Page Container -->
</div>

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
	var submit = document.getElementsByClassName('w3-twothird');
	console.log(submit);
	document.addEventListener('click', function(e){
		if(e.target && e.target.id== 'back'){
			window.location.href = "mainpage.jsp";
		}
		if(e.target && e.target.class== 'back'){
	});
	
	function refresh(){
		console.log(<% out.print("hey");%>);
	}
	
</script>

</body>
</html>