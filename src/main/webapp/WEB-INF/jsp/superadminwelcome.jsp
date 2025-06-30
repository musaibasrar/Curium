<%-- 
  Document   : Dash Board
  Created on : Jan 13, 2012, 12:21:03 PM
  Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dash Board</title>
        <script src="/ruyaa/js/Chart.min.js"></script>
         <link rel="stylesheet" href="/ruyaa/css/bootstrap.min.css">
        <script src="/ruyaa/js/jquery.min.js"></script>
        <script src="/ruyaa/js/bootstrap.min.js"></script>
        <script src="/ruyaa/js/popper.min.js"></script>
    </head>
    
	<style type="text/css">

		@font-face {
		  font-family: "IBMPlexSans";
  		  src: url("fonts/IBMPlexSans-Regular.ttf");
		}

		#rcorners1 {
		  border-radius: 25px;
		  border: 0px solid;
		  padding: 10px; 
		  width: 340px;
		  height: 120px; 
		  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		}
		
		#labelname {
		 	font-family: IBMPlexSans;
		 	font-size: 14px;
		}
		
		#labelnumber {
		 	font-family: IBMPlexSans;
		 	font-size: 14px;
		}
		
			a:link {
                color: black;
                text-decoration: none;
                font-family: arial;
                font-size: 14px;
            }
            a:active {
                color: #ef5b00;
                text-decoration: underline;
            }
            
			a:hover {
				text-decoration: underline;
			}
		
	</style>    
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/ruyaa/UserProcess/sessionTimeOut");
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
    	<div class="container-sm" align="center">
    		<br>
   			 
        </div>
        
               
        <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Nursing </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=2"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Pharmacy </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=3"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        		<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Physiotherapy </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=4"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	 </div>
        </div>
        
             <div><br><br></div>
             
             <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise Institute </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Paramedical Sciences </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=5"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> HPR College</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (B.Sc. Nursing) </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=6"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        		<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Karnataka School</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=7"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	 </div>
        </div>
        
             <div><br><br></div>
             
             <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Koushthubha School </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=8"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        						<label id="labelnumber">${totalstudents}</label><br>
        						<label id="labelnumber">${totalteachers}</label>	
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sri Sai School of Nursing </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=9"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        		<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Kshatriya School of Nursing </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<a onclick="window.open(this.href,'_blank'); return false;"
        						 href="/ruyaa/UserProcess/authenticateSuperUser?branchid=10"> <img
									src="/ruyaa/images/login.svg" width="25" height="25" alt="Login" style="vertical-align: bottom;" />Login
							</a>
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td>
        					</td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div>
        </div>
        
             <div><br><br></div>
             
             <div class="row">
             	
             	<div class="col">
             		<canvas id="student-monthlyfeeschart" height="200"></canvas>
             	</div>
             	
            	<div class="col">
            		<canvas id="student-expensechart" height="200"></canvas>
            	</div>	
             </div>
             
             <div class="row">
             	
             	<div class="col">
             		<canvas id="student-chart" height="200"></canvas>
             	</div>
             	
            	<div class="col" align="center">
            		<canvas id="student-male-female" height="300"></canvas>
            	</div>	
             </div>
			
			
			
		

   		<form id="form1" method="post">
				<label id="classesstudying" style="display: none;">${studentxaxis}</label>
				<label id="students" style="display: none;">${studentyaxis}</label>
				<label id = "studentsfees" style="display: none;">${studenttotalfees}</label>
				<label id = "currentdate" style="display: none;">${currentdate}</label>
				<label id = "monthlystudentsfees" style="display: none;">${monthlystudentsfees}</label>
				<label id = "monthlist" style="display: none;">${monthlist}</label>
				<label id = "monthlytotalexpenses" style="display: none;">${monthlyexpenses}</label>
				<label id = "monthlisttotalexpenses" style="display: none;">${monthlistexpenses}</label>
				<label id = "totalboysgirls" style="display: none;">${totalboysgirls}</label>
        </form>
	     
	     
	     
	     
	     <script>
        	var totalclasses = document.getElementById("classesstudying").innerHTML;
        	var classlabel = JSON.parse(totalclasses);
        	
        	var totalStudents = document.getElementById("students").innerHTML;
        	var studentslabel = JSON.parse(totalStudents);
        	
        	 var newarr=GetMax(studentslabel);
        		var roundedno = newarr % 100;
        		var finalstep = (100 -roundedno) + parseInt(newarr);
   
        		function GetMax(arr)
        		{   var MaxX=arr[0];

        		    for (var X=0;X<arr.length;X++)
        		        if (MaxX<arr[X])
        		            MaxX=arr[X];

        		    return MaxX;
        		}
        	
            new Chart(document.getElementById("student-chart"), {
                type: 'bar',
                data: {
                  labels: classlabel,
                  datasets: [
                    {
                      label: "Total Students",
                      backgroundColor: [
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(30, 26, 86, 0.2)',
        	                'rgba(75, 202, 48, 0.2)',
        	                'rgba(153, 102, 255, 0.2)',
        	                'rgba(86, 59, 64, 0.2)',
        	                'rgba(60, 39, 132, 0.2)',
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(30, 26, 86, 0.2)',
        	                'rgba(75, 202, 48, 0.2)',
        	                'rgba(153, 102, 255, 0.2)',
        	                'rgba(86, 59, 64, 0.2)',
        	                'rgba(60, 39, 132, 0.2)',
        	                'rgba(54, 162, 235, 0.2)',
        	                'rgba(30, 26, 86, 0.2)',
        	                'rgba(75, 202, 48, 0.2)',
        	                'rgba(153, 102, 255, 0.2)',
        	                'rgba(86, 59, 64, 0.2)',
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
          	                'rgba(290, 159, 64, 1)',
          	              	'rgba(200,99,132,1)',
        	                'rgba(54, 162, 235, 1)',
        	                'rgba(220, 206, 86, 1)',
        	                'rgba(75, 192, 192, 1)',
        	                'rgba(153, 102, 255, 1)',
        	                'rgba(290, 159, 64, 1)',
        	                'rgba(200,99,132,1)',
          	                'rgba(54, 162, 235, 1)',
          	                'rgba(220, 206, 86, 1)',
          	                'rgba(75, 192, 192, 1)',
          	                'rgba(153, 102, 255, 1)',
          	                'rgba(290, 159, 64, 1)',
          	              	'rgba(200,99,132,1)',
        	                'rgba(54, 162, 235, 1)',
        	                'rgba(220, 206, 86, 1)',
        	                'rgba(75, 192, 192, 1)',
        	                'rgba(153, 102, 255, 1)',
        	                'rgba(290, 159, 64, 1)'
          	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 1,
                      data: studentslabel
                    }
                  ]
                },
                options: {
                  responsive: true,
                  legend: { display: false },
                  
                  "hover": {
                      "animationDuration": 0
                    },
                     "animation": {
                        "duration": 1,
                      "onComplete": function() {
                        var chartInstance = this.chart,
                          ctx = chartInstance.ctx;
         
                        ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                        ctx.textAlign = 'center';
                        ctx.textBaseline = 'bottom';
         
                        this.data.datasets.forEach(function(dataset, i) {
                          var meta = chartInstance.controller.getDatasetMeta(i);
                          meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                          });
                        });
                      }
                    },
                    
                  title: {
                    display: true,
                    text: 'Number of Students Per Class'
                  },
                   scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                max: finalstep+20,
                                stepSize: 20,
                                fontColor: "Black"
                               }
                        
                            }], xAxes: [{
                                ticks: {
                                    fontColor: "Black"
                                }
                            }]
                }
                } 
            });
            
          		//Bar Chart for Monthly Expense
        	var totalExpenses = document.getElementById("monthlytotalexpenses").innerHTML;
        	var expenselabel = JSON.parse(totalExpenses);
        	
      	 	var currdate = document.getElementById("monthlisttotalexpenses").innerHTML;
      		var dateslabel = JSON.parse(currdate);
        	
            new Chart(document.getElementById("student-expensechart"), {
                type: 'bar',
                data: {
                  labels: dateslabel,
                  datasets: [
                    {
                      backgroundColor: [
                    	'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)',
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)',
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)'
      	            ],
      	            borderColor: [
      	            	'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)',
      	                'rgba(290, 159, 64, 1)',
      	              	'rgba(200,99,132,1)',
    	                'rgba(54, 162, 235, 1)',
    	                'rgba(220, 206, 86, 1)',
    	                'rgba(75, 192, 192, 1)',
    	                'rgba(153, 102, 255, 1)',
    	                'rgba(290, 159, 64, 1)',
    	                'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)'
      	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 1,
                      data: expenselabel
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  
                  
                  "hover": {
                      "animationDuration": 0
                    },
                     "animation": {
                        "duration": 1,
                      "onComplete": function() {
                        var chartInstance = this.chart,
                          ctx = chartInstance.ctx;
         
                        ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                        ctx.textAlign = 'center';
                        ctx.textBaseline = 'bottom';
         
                        this.data.datasets.forEach(function(dataset, i) {
                          var meta = chartInstance.controller.getDatasetMeta(i);
                          meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                          });
                        });
                      }
                    },
                    
                  title: {
                    display: true,
                    text: 'Monthly Expenses'
                  } ,
                   scales: {
                	   yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                max: 20000,
                                stepSize: 2000
                               }
                        
                            }]
                } 
                } 
            });
            
            
          //Bar Chart for Monthly fees
        	var monthlyfees = document.getElementById("monthlystudentsfees").innerHTML;
        	var monthlyfeeslabel = JSON.parse(monthlyfees);
        	
      	 	var monthlist = document.getElementById("monthlist").innerHTML;
      		var monthlistlabel = JSON.parse(monthlist);
        	
            new Chart(document.getElementById("student-monthlyfeeschart"), {
                type: 'bar',
                data: {
                  labels: monthlistlabel,
                  datasets: [
                    {
                      backgroundColor: [
                    	  'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)',
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)',
      	                'rgba(86, 59, 64, 0.2)',
      	                'rgba(60, 39, 132, 0.2)',
      	                'rgba(54, 162, 235, 0.2)',
      	                'rgba(30, 26, 86, 0.2)',
      	                'rgba(75, 202, 48, 0.2)',
      	                'rgba(153, 102, 255, 0.2)'
      	            ],
      	            borderColor: [
      	            	'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)',
      	                'rgba(290, 159, 64, 1)',
      	              	'rgba(200,99,132,1)',
    	                'rgba(54, 162, 235, 1)',
    	                'rgba(220, 206, 86, 1)',
    	                'rgba(75, 192, 192, 1)',
    	                'rgba(153, 102, 255, 1)',
    	                'rgba(290, 159, 64, 1)',
    	                'rgba(200,99,132,1)',
      	                'rgba(54, 162, 235, 1)',
      	                'rgba(220, 206, 86, 1)',
      	                'rgba(75, 192, 192, 1)',
      	                'rgba(153, 102, 255, 1)'
      	            ],
      	            maintainAspectRatio: false,
      	            borderWidth: 1,
                      data: monthlyfeeslabel
                    }
                  ]
                },
                options: {
                  legend: { display: false },
                  
                  
                  "hover": {
                      "animationDuration": 0
                    },
                     "animation": {
                        "duration": 1,
                      "onComplete": function() {
                        var chartInstance = this.chart,
                          ctx = chartInstance.ctx;
         
                        ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
                        ctx.textAlign = 'center';
                        ctx.textBaseline = 'bottom';
         
                        this.data.datasets.forEach(function(dataset, i) {
                          var meta = chartInstance.controller.getDatasetMeta(i);
                          meta.data.forEach(function(bar, index) {
                            var data = dataset.data[index];
                            ctx.fillText(data, bar._model.x, bar._model.y - 5);
                          });
                        });
                      }
                    },
                    
                  title: {
                    display: true,
                    text: 'Monthly Fees Collection'
                  } ,
                   scales: {
                	   yAxes: [{
                            ticks: {
                                beginAtZero: true,
                                max: 25000,
                                stepSize: 5000
                               }
                        
                            }]
                } 
                } 
            });
            
            //Boys & Girls
            
            var boysgirls = document.getElementById("totalboysgirls").innerHTML;
        	var boysGirlsGraph = JSON.parse(boysgirls);
            
            var ctx = document.getElementById("student-male-female");
            var myChart = new Chart(ctx, {
              type: 'pie',
              data: {
                labels: ['Boys', 'Girls'],
                datasets: [{
                  data: boysGirlsGraph,
                  backgroundColor: [
                	  'rgba(54, 162, 235, 0.2)',
                	  'rgba(255, 99, 132, 0.5)'
                    
                  ],
                  borderColor: [
                	  'rgba(54, 162, 235, 1)',
                	  'rgba(255,99,132,1)'
                    
                  ],
                  borderWidth: 1
                }]
              },
              options: {
               	//cutoutPercentage: 80,
                responsive: false,
                
                title: {
                    display: true,
                    text: 'Total Boys & Girls',
                    position: 'top'
                  },
                  rotation: -0.7 * Math.PI,
                  legend: {
                	    display: true
                	  }

              }
            });
     
            
	</script> 
</body>    
</html>