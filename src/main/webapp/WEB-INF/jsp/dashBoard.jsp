<%-- 
    Document   : notSaved
    Created on : Jan 5, 2012, 1:11:53 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.button.js"></script>
        
        
         <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>
        <script type="text/javascript" src="js/graph/jquery.jqplot.js"></script>        
        <script  type="text/javascript" src="js/graph/plugins/jqplot.dateAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.barRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.categoryAxisRenderer.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.cursor.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.highlighter.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.dragable.min.js"></script>
        <script type="text/javascript" src="js/graph/plugins/jqplot.trendline.min.js"></script>
        <script src="js/jquery.jqplot.min.js" ></script>
        <script src="js/graph/plugins/jqplot.pieRenderer.min.js" ></script> 
        
        
        
        
        <style type="text/css">
<!--
.divCSS {
	height: 40px;
	width: 200px;
	border: 1px solid #305876;
	
}
.tableCSS {
	background-position: center center;
	vertical-align: middle;
	height: 140px;
	width: 100%;
}
.style1 {
	font-family: Tahoma;
	font-weight: bold;
	color: #5E87B0;
        font-size: 12px;
}
.style2 {
	font-size: 12px;
	color: #000000;
}
-->
        </style>

        <script type="text/javascript">
            $(function(){
                $("#view").button()
                
                $("#addnew").button()

                });

            function ViewAll(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=PersonalProcess&action=viewAll";
                form1.submit();
            }
        </script>
        
        <script type="text/javascript">
        function drawChart() {
        	  var slice_1 = ['I', 10];
        	  var slice_2 = ['II', 0];
        	  var slice_3 = ['III', 200];
        	  var slice_4 = ['IV', 50];
        	  var slice_5 = ['V', 15];
        	  var slice_6 = ['VI', 5];
        	  var slice_7 = ['VII', 5];
        	  var slice_8 = ['VIII', 5];
        	  var slice_9 = ['IX', 5];
        	  var slice_0 = ['X', 5];
        	  var series = [slice_1, slice_2, slice_3, slice_4, slice_5, slice_6, slice_7, slice_8, slice_9, slice_0];
        	  var data = [series];
        	 
        	  var options = {
        	    title: 'Students per class',
        	    seriesDefaults: {
        	      renderer: jQuery.jqplot.PieRenderer
        	    },
        	    legend: { show:true, location: 'e' }
        	  };
        	   
        	  $.jqplot('chartDivId', data, options);
        	}
        </script>
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
    <body background="images/bg.jpg" onload='drawChart()'>
    <div id="chartDivId" style="margin-top:20px; margin-left:20px; width:600px; height:300px;"></div>
        <form id="form1" method="post">
            
          <!--  <table height="462" class="tableCSS"  >
    <tr>
        <td height="250" align="center" valign="middle"><p class="style1"> Registration Successful</p>
        <p class="style1">
            <label>${noOfRecordsOfSubscribers}</label>
            <input type="button" id="view" value="View All " onClick="ViewAll()">
            <input type="button" value="Add New" id="addnew" onClick="JavaScript:window.location='addContact.jsp';">
        </p></
            </table> -->
            
      <div align="center">
          <label   style="font-family: Tahoma;
	font-weight: bolder;
	color: #5E87B0;
        font-size: 20px;"> DASH BOARD </label> 
          <br/><br/>
          <table  border="0" cellpadding="5" >
          
          
          <tr>
          <td>
           <table  border="0" cellpadding="5" >
  	<tr>
 
      <td align="right" valign="middle"><p class="style1">Total Number Of Students</p> </td>
    <td align="center" valign="middle"><p class="style1">${totalStudents}</td>
    </tr>
    
    <tr>
    
    <td align="right" valign="middle"><p class="style1">Total Number Of Employees</p></td>
    <td align="right" valign="middle"><p class="style1">${totalNoOfEmployees}</p></td>
    
    </tr>
  
  		</table>
  </td>
  <td></td>
  <td>
   	 <table  border="0" cellpadding="5" >
  
  <tr>
 
      
     <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : Nursery</p></td>
    <td align="right" valign="middle"><p class="style1">${studentNursery}</p></td>
  </tr>
  
  <tr>
 
      
     <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : L.K.G</p></td>
    <td align="right" valign="middle"><p class="style1">${studentLKG}</p></td>
  </tr>
  
  <tr>
 
      
     <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : U.K.G</p></td>
    <td align="right" valign="middle"><p class="style1">${studentUKG}</p></td>
  </tr>
   <tr>
 
      
     <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : I</p></td>
    <td align="right" valign="middle"><p class="style1">${studentOne}</p></td>
  </tr>
  
  <tr>
 	
    
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: II</p></td>
    <td align="right" valign="middle"><p class="style1">${studentTwo}</p></td>
 </tr>
  
   <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: III</p></td>
    <td align="right" valign="middle"><p class="style1">${studentThree}</p></td>
  </tr>
  
    <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: IV</p></td>
    <td align="right" valign="middle"><p class="style1">${studentFour}</p></td>
  </tr>
  
   <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: V</p></td>
    <td align="right" valign="middle"><p class="style1">${studentFive}</p></td>
  </tr>
  
  
 
  <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VI</p></td>
    <td align="right" valign="middle"><p class="style1">${studentSix}</p></td>
  </tr>
  
  
  <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VII</p></td>
    <td align="right" valign="middle"><p class="style1">${studentSeven}</p></td>
  </tr>
  
   <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VIII</p></td>
    <td align="right" valign="middle"><p class="style1">${studentEight}</p></td>
  </tr>
  
   
  <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: IX</p></td>
    <td align="right" valign="middle"><p class="style1">${studentNine}</p></td>
  </tr>
  
    <tr>
    <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: X</p></td>
    <td align="right" valign="middle"><p class="style1">${studentTen}</p></td>
  </tr>
  
 	 </table>
  
      </td>    
          
          
          
          </tr>
 
 
</table>
</div>
            </form>
    </body>
</html>
