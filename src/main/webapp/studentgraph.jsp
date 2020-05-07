<%-- 
  Document   : student graph
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
        <title>Student Graph</title>
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
   
    <body>
    <div align="center">
    <label style="font-family: Tahoma;
	font-weight: bolder;
	color: #5E87B0;
        font-size: 20px;"> STUDENT GRAPH &nbsp;&nbsp;&nbsp;<br><br>Student Name: ${studentName}</label><br><br>
    		<!-- <h1 align="center" class="headerTD">Number Of Students Per Center</h1> -->
			<%-- <canvas id="canvas" height="100" width="300"></canvas> --%>
			<c:forEach items="${examDetailsGraph}" var="examDetailsGraph" varStatus="status">
				<canvas id="student-chart${status.index}" width="800" height="200"></canvas>
				<br>
			</c:forEach>
			
		</div>

   <form id="form1" method="post">
   <label id="examDetailsGraphSize" style="display: none;">${examDetailsGraphSize}</label>
	
	
	<c:forEach items="${examDetailsGraph}" var="examDetailsGraph" varStatus="status">
		<label id="subjectname${status.index}" style="display: none;">${examDetailsGraph.subjects}</label>
		<label id="marksobtained${status.index}" style="display: none;">${examDetailsGraph.marks}</label>
		<label id="examname${status.index}" style="display: none;">${examDetailsGraph.examName}</label>
	</c:forEach>
			
	
            </form>
	     <script>
	     var i=0;
	     var ii =document.getElementById("examDetailsGraphSize").innerHTML;
	     
		for(i=0;i<ii;i++){
        	var totalcenters = document.getElementById("subjectname"+i).innerHTML;
        	totalcenters = totalcenters.toUpperCase();
        	var subjectname = JSON.parse(totalcenters);
        	
        	var totalStudents = document.getElementById("marksobtained"+i).innerHTML;
        	var marksobtained = JSON.parse(totalStudents);
        	
        	var examname = document.getElementById("examname"+i).innerHTML;
        	examname = examname.toUpperCase();
        	var exam = JSON.parse(examname);
        	
            new Chart(document.getElementById("student-chart"+i), {
                type: 'bar',
                data: {
                  labels: subjectname,
                  datasets: [
                    {
                      label: "Marks",
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
                      data: marksobtained
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  title: {
                    display: true,
                    text: 'Exam Name: '+exam
                  },
                  scales: {
                      yAxes: [{
                          ticks: {
                              beginAtZero: true,
                              max:100,
                              stepSize: 10,
                              fontColor: "Black"
                             }
                      }],
                    xAxes: [{
                        ticks: {
                            fontColor: "Black"
                        }
                    }]
              }
              }
                   
            });
}   
 
	</script> 
</body>    
</html>