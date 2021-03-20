<%-- 
    Document   : Print Preview
    Created on : Jan 4, 2013, 4:39:24 PM
    Author     : Musaib
--%>


<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please wait...</title> 

        <script type="text/javascript" language="JavaScript" src="js/motionpack.js"></script>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="css/graph/jquery.jqplot.css">

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
                font-size: 13px;
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
                font-size: 14px;
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
            .tablerows{
                font-size: 12px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;

            }
            -->
        </style>
        <script type="text/javascript">

            function updateContact() {
                var form1 = document.getElementById("form1");
                form1.action = "Controller?process=PersonalProcess&action=updateContactDetails&id=1";
                form1.submit();
            }

            function hideButton() {

            }

        </script>

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

        <script type="text/javascript" charset="utf-8">
            $(document).ready(function() {
                $('#myTable').dataTable({
                    "sScrollY": "380px",
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": true,
                    "bSort": true,
                    "bInfo": true,
                    "bStateSave": false,
                    "bProcessing": false,
                    "bServerSide": false,
                    "bAutoWidth": false,
                    "iDisplayLength": 500,
                    "aoColumnDefs": [
                        {'bSortable': false, 'aTargets': [0]}
                    ]

                });
            });
        </script>
        
        <script type="text/javascript">
                                $(function() {
                                    $("#print")
                                            .button()


                                });
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

    <!-- <style type="text/css">
         
         @media print {
     body { font-size: 25px }
     .hide { visibility: hidden }
       }
       
   @media screen {
     body { font-size: 13px }
   }
   
         
         
     </style> -->

    <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 1cm;
             margin-top: 1cm;
             size: 12.2cm 17.5cm;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
        .card {
    /* box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2); */
    transition: 0.3s;
    width: 12.2cm;
    height: 17.5cm;
}

/* .card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
} */

.container {
    padding: 2px 16px;
}

.carddetails{
            	font-size: 23px;
                font-family: Tahoma;
                text-align: left;
                font-weight: bold;
            }
    </style>


    <body class="bodymargin" onload="window.print();">
       <%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		
        <form action="" method="post" id="form1" class="bodymargin">

           
            <div style="page-break-before: always;border-collapse: collapse;">



                <table cellpadding="2"  border="0"   >
                
                
                	<c:forEach items="${messcardstudentlist}" var="studentlist" varStatus="status">
                			<tr>
                            <td class="fontsize" >
		                         <div class="card">
		                         		<div>
		                         			<table>
		                         				<tr>
		                         					<td style="padding-left: 30px;">
		                         						<a><img src="images/shaheenlogo.png" style="width:180px;height:90px;vertical-align: baseline;" alt="shaheenlogo">
		                         							</a>
		                         					</td>
		                         					<td style="padding-left: 20px;">
		                         						<label style="font-size: 28px;color: #466580;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zaiqa <br> Mess Services</label>
		                         						<br>
		                         					</td>
		                         				</tr>
		                         			</table>
		                         			</div>
		                         			<div align="center">
		                         			<hr style="margin-top: 1px;margin-bottom: 1px;color: black;">
		                         						<br>
		                         					<label style="font-size: 18px;">Valid From : <fmt:formatDate type="date" value="${studentlist.value.validfrom}" pattern="dd/MM/yyyy"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Valid Till : <fmt:formatDate type="date" value="${studentlist.value.validto}" pattern="dd/MM/yyyy"/></label> <br>
		                         					<br>
		                         					<c:if test="${studentlist.key.student.nationality == 'nri'}">
														<label style="font-size: 25px;">NRI</label>
		                         					
		                         					</c:if>
		                         					<br>
		                         					<c:if test="${studentlist.key.student.breakfast == 'breakfast'}">
		                         						<a> <img
															src="images/breakfast.png" width="30" height="30" 
															alt="Home" style="vertical-align: baseline;font-size: 180px;" />
														</a>
														<label style="font-size: 25px;">${studentlist.key.student.breakfast}</label>
		                         					
		                         					</c:if>
		                         					&nbsp;&nbsp;&nbsp;
		                         					<c:if test="${studentlist.key.student.lunch == 'lunch'}">
														<a> <img
															src="images/lunch.png" width="30" height="30" 
															alt="Home" style="vertical-align: baseline;font-size: 144px;" />
															
														</a>
														<label style="font-size: 25px;">${studentlist.key.student.lunch}</label>
		                         					</c:if>
		                         					
		                         					&nbsp;&nbsp;&nbsp;
		                         					<c:if test="${studentlist.key.student.dinner == 'dinner'}">
		                         						<a > <img
															src="images/dinner.png" width="30" height="30" 
															alt="Home" style="vertical-align: baseline;font-size: 144px;" />
														</a>
		                         					<label style="font-size: 25px;">${studentlist.key.student.dinner}</label>
		                         					</c:if>
							
		                         		<hr style="margin-top: 1px;margin-bottom: 1px;color: black;">
		                         						 
		                         		</div>
		  								<div class="container" align="center">
		  								
		  								<table style="width: auto;height: auto;">
  											<tr>
  												<td>
						    							<img src="data:image;base64,${studentlist.key.student.studentpic}" style="height:250px;width:220px;" alt="Student's Photo" />
  												</td>
  											</tr>
  										</table>
		  								
		  									<table>	
  											<tr>
  												<td class="carddetails" style="width: 50%;padding-left: 10px;">
  														Name
  													</td><td class="carddetails">:&nbsp;${studentlist.key.student.name}
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 50%;padding-left: 10px;">
  														Adm. No
  														</td><td class="carddetails">:&nbsp;${studentlist.key.student.admissionnumber}
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 50%;padding-left: 10px;">
						  								UID
  														</td><td class="carddetails">:&nbsp;${studentlist.key.student.studentexternalid}
  												</td>
  											</tr>
  											
  										</table>
  										
		  								</div>
								</div>
								<TABLE width="100%" border="0"
			style="page-break-after: always; border-collapse: collapse;">

			<tr>
			<td>
			<br>
			<br><br><br></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
			<td></td>
			</tr>
		<tr>
		<td></td>
			<td align="left"></td>	
			<td align="center"></td>
			<td align="center"></td>
		</tr>
		
			<tr>
              <td align="center"></td>
            </tr>
		</TABLE>
                            </td>
                            <td></td>
                            <td></td>
                            
                            
                        </tr>
                	
                	</c:forEach>

                        <%-- <c:set var="iInitial" value="${iInitial}"/>
                        <c:set var="limit" value="1"/>
                        
                        
                        <c:forEach begin="1" end="${endValue}">
                        <%!                        
                            int i = 1;
                        %>

                        <tr style="page-break-before: always;">
                            <c:if test="${limit < iInitial}">
                            <td class="fontsize" >
		                         <div class="card">
		                         		<div>
		                         			<table>
		                         				<tr>
		                         					<td style="padding-left: 30px;">
		                         						<a><img src="images/shaheenlogo.png" style="width:180px;height:90px;vertical-align: baseline;" alt="shaheenlogo">
		                         							</a>
		                         					</td>
		                         					<td style="padding-left: 20px;">
		                         						<label style="font-size: 28px;color: #466580;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zaiqa <br> Mess Services</label>
		                         					</td>
		                         				</tr>
		                         			</table>
		                         			</div>
		                         			<div align="center">
		                         			<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         			<%= request.getSession().getAttribute("meals" + i + "")%>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         		</div>
		  								<div class="container" align="center">
		  								
		  								<table style="width: auto;height: auto;">
  											<tr>
  												<td>
						    							<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "")%>" style="height:250px;width:220px;" alt="Student's Photo" />
  												</td>
  											</tr>
  										</table>
		  								
		  									<table>	
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Name
  													</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("studentname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Adm. No
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("admissionnumber" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
						  								UID
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("uid" + i + "")%>
  												</td>
  											</tr>
  											
  										</table>
  										
		  								</div>
								</div>
                            </td>
                            <td></td>
                            <td></td>
                            </c:if>
                            
                        </tr>
                        <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach> --%>
                    <%-- <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>  --%>
                        
                        
                    <%-- <c:forEach begin="1" end="${endValue}">
                        <%!                        
                            int i = 1;
                        %>

                        <tr>
                            <c:if test="${limit < iInitial}">
                            <td class="fontsize" >
		                         <div class="card">
		                         		<div>
		                         			<table>
		                         				<tr>
		                         					<td style="padding-left: 30px;">
		                         						<a><img src="images/shaheenlogo.png" style="width:180px;height:90px;vertical-align: baseline;" alt="shaheenlogo">
		                         							</a>
		                         					</td>
		                         					<td style="padding-left: 20px;">
		                         						<label style="font-size: 28px;color: #466580;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zaiqa <br> Mess Services</label>
		                         					</td>
		                         				</tr>
		                         			</table>
		                         			</div>
		                         			<div align="center">
		                         			<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         			<%= request.getSession().getAttribute("meals" + i + "")%>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         		</div>
		  								<div class="container" align="center">
		  								
		  								<table style="width: auto;height: auto;">
  											<tr>
  												<td>
						    							<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "")%>" style="height:250px;width:220px;" alt="Student's Photo" />
  												</td>
  											</tr>
  										</table>
		  								
		  									<table>	
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Name
  													</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("studentname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Adm. No
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("admissionnumber" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
						  								UID
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("uid" + i + "")%>
  												</td>
  											</tr>
  											
  										</table>
  										
		  								</div>
								</div>
                            </td>
                            <td></td>
                            <td></td>
                            </c:if>
                            <c:set var="limit" value="${limit+1}"/>
                            <% i = i + 1;%>
                            <c:if test="${limit < iInitial}">
                            <td  class="fontsize"> 
                            	<div class="card">
		                         		<div align="center">
		                         			<img src="images/shaheenlogo.png" style="width:100px;height:68px;" alt="shaheenlogo"><br>
		                         			<label>Zaiqa Mess Services</label>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         			<%= request.getSession().getAttribute("meals" + i + "")%>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         		</div>
		  								<div class="container" align="center">
		  								
		  								<table style="width: auto;height: auto;">
  											<tr>
  												<td>
						    							<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "")%>" style="height:150px;width:120px;" alt="Student's Photo" />
  												</td>
  											</tr>
  										</table>
		  								
		  									<table>	
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Name
  													</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("studentname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Adm. No
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("fathersname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
						  								UID
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("UID" + i + "")%>
  												</td>
  											</tr>
  											
  										</table>
  										
		  								</div>
								</div>
                            </td>
						 <td></td>
                            <td></td>
                            </c:if>
                            <c:set var="limit" value="${limit+1}"/>
                            <% i = i + 1;%>
                            <c:if test="${limit < iInitial}">
                            <td  class="fontsize">
                            		<div class="card">
		                         		<div align="center">
		                         			<img src="images/shaheenlogo.png" style="width:100px;height:68px;" alt="shaheenlogo"><br>
		                         			<label>Zaiqa Mess Services</label>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         			<%= request.getSession().getAttribute("meals" + i + "")%>
		                         		<hr style="margin-top: 0px;margin-bottom: 0px;">
		                         		</div>
		  								<div class="container" align="center">
		  								
		  								<table style="width: auto;height: auto;">
  											<tr>
  												<td>
						    							<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "")%>" style="height:150px;width:120px;" alt="Student's Photo" />
  												</td>
  											</tr>
  										</table>
		  								
		  									<table>	
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Name
  													</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("studentname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
  														Adm. No
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("fathersname" + i + "")%>
  												</td>
  											</tr>
  											<tr>
  												<td class="carddetails" style="width: 40px;padding-left: 10px;">
						  								UID
  														</td><td class="carddetails">:&nbsp;<%= request.getSession().getAttribute("UID" + i + "")%>
  												</td>
  											</tr>
  											
  										</table>
  										
		  								</div>
								</div>
                            </td>
                            </c:if>
                        </tr>
                        <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach>
                    <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>  --%>
                </table>
                
                
                

            </div>
	
		<!-- <table  width="70%"  id="table11" align="left">
                    <tr>
                        <td width="30%"> 

                        </td>
                        <td>
                            <button id="print" type="button" style="background-image: url(images/print.jpg);width: 63px;height: 60px" onclick="window.print();
                                    this.style.visibility = 'hidden', loading.style.visibility = 'visible'" class="hide"></button>     
                        </td>

                    </tr>

                </table>
 -->
 
 
        </form>
    </body>
</html>
