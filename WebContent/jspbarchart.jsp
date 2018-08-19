<%-- 
  Document   : Dash Board
  Created on : Jan 13, 2012, 12:21:03 PM
  Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <script src="js/Chart.min.js"></script>
    </head>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
}else user = (String) session.getAttribute("userAuth");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
   
    <body >
    <div align="center">
    <label style="font-family: Tahoma;
	font-weight: bolder;
	color: #5E87B0;
        font-size: 20px;"> DASH BOARD </label><br><br>
    		<!-- <h1 align="center" class="headerTD">Number Of Students Per Center</h1> -->
			<%-- <canvas id="canvas" height="100" width="300"></canvas> --%>
			<canvas id="student-chart" width="800" height="200"></canvas>
		</div>
		<div align="center">
		<div style="float: left;">
		<canvas id="book-chart"></canvas>
		</div>
		<div style="float: left;">
			<canvas id="qualification-chart"></canvas>
		</div>
		</div>
   <form id="form1" method="post">
	<label id="centers" style="display: none;">${studentxaxis}</label>
	<label id="students" style="display: none;">${studentyaxis}</label>
	<label id="booksname" style="display: none;">${booksxaxis}</label>
	<label id="booksquantity" style="display: none;">${booksyaxis}</label>
	<label id="qualificationname" style="display: none;">${qualificationxaxis}</label>
	<label id="qualificationquantity" style="display: none;">${qualificationyaxis}</label>
            </form>
            
            <script>
        	var totalcenters = document.getElementById("centers").innerHTML;
        	var centerslabel = JSON.parse(totalcenters);
        	
        	var totalStudents = document.getElementById("students").innerHTML;
        	var studentslabel = JSON.parse(totalStudents);
        	
            new Chart(document.getElementById("student-chart"), {
                type: 'bar',
                data: {
                  labels: centerslabel,
                  datasets: [
                    {
                      label: "Total Students",
                      backgroundColor: [
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)'
      	            ],
      	            borderColor: [
      	                'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)',
      	                'rgba(290, 159, 64, 1)'
      	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 3,
                      data: studentslabel
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  title: {
                    display: true,
                    text: 'Number of Students Per Center'
                  }
                }
            });
            
            var booksnamex = document.getElementById("booksname").innerHTML;
        	var booksnamelabel = JSON.parse(booksnamex);
        	
        	var booksquantityy = document.getElementById("booksquantity").innerHTML;
        	var booksquantitylabel = JSON.parse(booksquantityy);
        	
            new Chart(document.getElementById("book-chart"), {
                type: 'pie',
                data: {
                  labels: booksnamelabel,
                  datasets: [
                    {
                      label: "Total",
                      backgroundColor: [
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)'
      	            ],
      	            borderColor: [
      	                'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)',
      	                'rgba(290, 159, 64, 1)'
      	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 3,
                      data: booksquantitylabel
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  title: {
                    display: true,
                    text: 'Books Info'
                  }
                }
            });   
	
            var totalqualification = document.getElementById("qualificationname").innerHTML;
        	var qualificationlabel = JSON.parse(totalqualification);
        	
        	var totalqualificationquantity = document.getElementById("qualificationquantity").innerHTML;
        	var qualificationquantitylabel = JSON.parse(totalqualificationquantity);
        	
            new Chart(document.getElementById("qualification-chart"), {
                type: 'pie',
                data: {
                  labels: qualificationlabel,
                  datasets: [
                    {
                      label: "Total Students",
                      backgroundColor: [
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)'
      	            ],
      	            borderColor: [
      	                'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)',
      	                'rgba(290, 159, 64, 1)'
      	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 3,
                      data: qualificationquantitylabel
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  title: {
                    display: true,
                    text: 'Qualification'
                  }
                }
            });
		
	</script>
</body>

    
</html>
