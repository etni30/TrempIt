<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@page import="View.*, Model.*"%>

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
th.margin {  padding-left: 50px; padding-right: 50px; padding-top: 20px; padding-bottom: 20px}
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
				String userName = request.getParameter("userName");
				String type = request.getParameter("type");
				out.println("name: " + userName);
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
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>TRAMP SEARCHING (AUTO)</h2>
      </div>
	<!--  names of parameters -->
	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>INSERT PARAMETERS</h4> 
      <table class="groove">
		  <tr class="groove">
		    <th class="groove">departure time</th>
		    <th class="groove">Origin </th>
		    <th class="groove">destination</th>
		  </tr>
	  </table>
	<!--  searching form  -->
	<form action="mainpage.jsp"> 
	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
			  <tr class="groove">
				<th class="margin"><input type="time" name="time"/></th>
			    <th class="margin">
			    	<select name="Origin">
					    <option value="1">אחד</option>
					    <option value="2">שנים</option>
					    <option value="3">שלוש</option>
					</select >
				</th>
			    <th class="margin">
			    	<select name= "destination">
					    <option value="1">אחד</option>
					    <option value="2">שנים</option>
					    <option value="3">שלוש</option>
					</select >
				</th>
			  </tr>
		  </table>
	  	</div>
	  	<h4 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>CHOOSE PRIORITY</h4>
	  	      <table class="groove">
		  <tr class="groove">
		    <th class="groove">first priority</th>
		    <th class="groove">second priority</th>
		    <th class="groove">third priority</th>
		  </tr>
	  </table>
	<!--  searching form  -->

	  <div class="w3-container w3-card w3-white w3-margin-bottom">
	  <!-- search parameters -->
	      <table class="groove">
			  <tr class="groove">
			    <th >
			    	<select name="first_priority">
					    <option value="Origin">distance from Origin</option>
					    <option value="destination">distance from destination</option>
					    <option value="departure ">departure time</option>
					    <option value="rank">driver rank</option>
					</select >
				</th>
			    <th >
			    	<select name="second_priority" required="required" >
					    <option value="Origin">distance from Origin</option>
					    <option value="destination">distance from destination</option>
					    <option value="departure ">departure time</option>
					    <option value="rank">driver rank</option>
					</select >
				</th>
			    <th >
			    	<select name="third_priority">
					    <option value="Origin">distance from Origin</option>
					    <option value="destination">distance from destination</option>
					    <option value="departure ">departure time</option>
					    <option value="rank">driver rank</option>
					</select >
				</th>
			  </tr>
		  </table>
	  	</div>
	  	</br>
	  	
	  	<div class ="submit" ><input type="submit" value="search"></div>
	  	</form><!-- end of form -->
      

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
	document.addEventListener('click', function(e){
		if(e.target && e.target.id== 'back'){
			alert("goodbye");
			window.location.href = "demo.jsp";
		}
	});
	
</script>

</body>
</html>