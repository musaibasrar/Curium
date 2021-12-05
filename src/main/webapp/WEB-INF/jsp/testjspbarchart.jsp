<%-- 
  Document   : jspbarchart
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
        <title>JSP Page</title>

        <script type="text/javascript" language="JavaScript" src="js/motionpack.js"></script>
        <script src="js/Chart.js"></script>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.min.css">

        <link rel="stylesheet" href="css/datePicker/demos.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>

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
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: left;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
                height: 100%;
                width: 100%;
            }

            .fiedlSet {
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-width: 1;
                width: 100%;
                color: #000000;
                font-size: 16px;
                font-weight: bold;
                font-variant: normal;
                font-stretch: wider;
                background-color: #e2ebf3;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
            }
            .legendCSS {
                color: #666666;
            }
            .tableCSS {
                width: 100%;
                height: 100%;
                position: absolute;
                left: 0px;
                top: 0px;
            }
            .textAreaCSS {
                height: auto;
                width: auto;
            }
            .textField {
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-color: #5d7e9b;
                border-right-color: #5d7e9b;
                border-bottom-color: #5d7e9b;
                border-left-color: #5d7e9b;
                border-top-width: 1px;
                border-right-width: 1px;
                border-bottom-width: 1px;
                border-left-width: 1px;
                width: auto;
                height: auto;
            }
            .alignRight {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: right;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignLeft {
                font-family: Tahoma;
                font-size: 11px;
                font-style: normal;
                text-transform: capitalize;
                color: #325F6D;
                text-align: left;
                vertical-align: middle;
                font-weight: bold;
            }
            .alignRightMultiple {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bolder;
                text-align: right;
                vertical-align: middle;
                font-style: normal;
                color: #325F6D;
            }
            .alignCentreMultiple {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bolder;
                text-align: center;
                vertical-align: middle;
                font-style: normal;
                color: #000000;
            }
            .autoAdjust {
                height: auto;
                width: auto;
            }
            .radioSpanCSS {
                font-size: 12px;
                font-family: Arial, Helvetica, sans-serif;
                text-align: left;
                vertical-align: middle;
            }
            .radioCSS {
                background-position: left center;
            }
            .spanText {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                font-weight: bold;
                color: #000000;
            }
            .emptyFieldSet {
                border-top-color: #FA7676;
                border-right-color: #FA7676;
                border-bottom-color: #FA7676;
                border-left-color: #FA7676;
                border-top-style: solid;
                border-right-style: solid;
                border-bottom-style: solid;
                border-left-style: solid;
                border-top-width: thin;
                border-right-width: thin;
                border-bottom-width: thin;
                border-left-width: thin;
                background-image: url(images/close.JPG);
                background-repeat: repeat-y;
                background-attachment: scroll;
                background-position: right;
                width: auto;
                height: auto;
                display: inline;
            }
            .style1 {
                font-family: Tahoma;
	font-weight: bold;
	color: #5E87B0;
        font-size: 12px;
            }
            .style2 {
                color: #666666;
                font-family: Tahoma;
                font-size: 14px;
            }
            .style4 {
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                vertical-align: middle;
                color: #325f6d;
            }
            -->
        </style>
        

        <script type="text/javascript">
        $(function() {
                
            $("#accordion").accordion({
                collapsible: true,
                    
                autoHeight: false});
            /*$("#set")
                .button()
                .click(function() {
                    updateVisit();                    
                });  */
        });
        
       
        </script>

        



    </head>
   
    <body background="images/bg.jpg" >
    <div  align="center">
    <label   style="font-family: Tahoma;
	font-weight: bolder;
	color: #5E87B0;
        font-size: 20px;"> DASH BOARD </label>
    		<h1 align="center" class="headerTD">Number Of Students Per Class</h1>
			<canvas id="canvas" height="100" width="300"></canvas>
		</div>
   <!-- <div id="chartDivId" style="margin-top:20px; margin-left:20px; width:600px; height:300px;"></div> -->
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
 
      
     <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : Nursery</p></td> -->
    <td><input type="hidden" id="nursery" name="nursery" value="[1,2,3,4,5,6,7,8]"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentNursery}</p></td> --%>
  </tr>
  
  <tr>
 
      
     <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : L.K.G</p></td> -->
     <td><input type="hidden" id="lkg" name="lkg" value="${studentLKG}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentLKG}</p></td> --%>
  </tr>
  
  <tr>
 
      
     <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : U.K.G</p></td> -->
     <td><input type="hidden" id="ukg" name="ukg" value="${studentUKG}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentUKG}</p></td> --%>
  </tr>
   <tr>
 
      
     <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class : I</p></td> -->
     <td><input type="hidden" id="I" name="I" value="${studentOne}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentOne}</p></td> --%>
  </tr>
  
  <tr>
 	
    
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: II</p></td> -->
    <td><input type="hidden" id="II" name="II" value="${studentTwo}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentTwo}</p></td> --%>
 </tr>
  
   <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: III</p></td> -->
    <td><input type="hidden" id="III" name="III" value="${studentThree}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentThree}</p></td> --%>
  </tr>
  
    <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: IV</p></td> -->
    <td><input type="hidden" id="IV" name="IV" value="${studentFour}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentFour}</p></td> --%>
  </tr>
  
   <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: V</p></td> -->
    <td><input type="hidden" id="V" name="V" value="${studentFive}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentFive}</p></td> --%>
  </tr>
  
  
 
  <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VI</p></td> -->
    <td><input type="hidden" id="VI" name="VI" value="${studentSix}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentSix}</p></td> --%>
  </tr>
  
  
  <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VII</p></td> -->
    <td><input type="hidden" id="VII" name="VII" value="${studentSeven}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentSeven}</p></td> --%>
  </tr>
  
   <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: VIII</p></td> -->
    <td><input type="hidden" id="VIII" name="VIII" value="${studentEight}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentEight}</p></td> --%>
  </tr>
  
   
  <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: IX</p></td> -->
    <td><input type="hidden" id="IX" name="IX" value="${studentNine}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentNine}</p></td> --%>
  </tr>
  
    <tr>
    <!-- <td align="right" valign="middle"><p class="style1">Total Number Of Students Class: X</p></td> -->
    <td><input type="hidden" id="X" name="X" value="${studentTen}"/></td>
    <%-- <td align="right" valign="middle"><p class="style1">${studentTen}</p></td> --%>
  </tr>
  
  <tr>
  <td>
  
  
  </td>
  
  </tr>
  
 	 </table>
  
      </td>    
          
          
          
          </tr>
 <table>
 <tr>
 
 <td></td>
 </tr>
 
 </table>
 
</table>
</div>
            </form>
            
            <script>
	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
	
	var nursery = document.getElementById("nursery").value;
	var students = [
	                <c:forEach varStatus="status" items="${studentList}" var="student">{
	                    value:'<c:out default="0" value="${student.admissionnumber}" />',
	                    name:'<c:out default="0" value="${student.name}" />',
	                    classandsec:'<c:out default="0" value="${student.classstudying}" />',
	                    id:'<c:out default="0" value="${student.sid}" />',
	                    
	                }<c:if test="${!status.last}">,</c:if>
	                </c:forEach>
	            ];
	var barChartData = {
		labels : ["Nursery - ","L.K.G - ","U.K.G - ","I - ","II - ","III - ","IV - ","V - ","VI - ","VII - ","VIII - ","IX - ","X - "],
		datasets : [
			{
				fillColor : "rgba(0,0,0,0.8)",
				strokeColor : "rgba(220,220,220,0.8)",
				highlightFill: "rgba(0,0,0,0.75)",
				highlightStroke: "rgba(0,0,0,1)",
				data : nursery
			}
		]
	}
	window.onload = function(){
		var ctx = document.getElementById("canvas").getContext("2d");
		window.myBar = new Chart(ctx).Bar(barChartData, {
			responsive : true
		});
	}
	</script>
</body>

    
</html>
