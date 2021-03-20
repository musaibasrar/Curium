<%-- 
Document   : left
Created on : Jan 4, 2012, 3:41:11 PM
Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Left</title>
        <link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
        <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <style>

            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .noti_bubble {
                position:absolute;
                font-size: 12px;
                top: 450px;
                left: 0px;
                padding-right:2px;
                background-color:transparent;
                color:black;
                font-weight:bold;

                z-index: 2;
                width: 100%;
                height: 16px;
            }
        </style>
        <style>
            body{
                margin:0;
                padding:0;
                background:#FFFFFF;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }
            /*body{
                margin:0;
                padding:0;
                background:#E6EEF4;
                font:80% Tahoma;
                color:#555;
                line-height:150%;
                text-align:left;

            }*/
            a{
                text-decoration:none;

                color:#cfe0ea;;
            }
            a:hover{
                text-decoration:underline;
                color:#EB6000;
            }
            h1{
                font-size:140%;
                margin:0 20px;
                line-height:80px;	
            }

            #content{margin:0 0px;}
            p{	
                margin:0 auto;
                width:700px;
                padding:1em 0;
            }


            .noti_Container {
                position:relative;
                border:1px solid blue; /* This is just to show you where the container ends */
                width:16px;
                height:16px;
                cursor: pointer;
            }
            .headerTD{
                border-radius:1px;
                background-color:#4b6a84;
                background-image: url("images/ui-bg_diagonals-small_50_466580_40x40.png");
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
                height: 22px;
            }


.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default { border: 1px solid #cccccc; background: #ffffff url(images/ui-bg_glass_100_f6f6f6_1x400.png) 50% 50% repeat-x; font-weight: bold; color: #1c94c4; }
.ui-widget-content {
    border: 1px solid #b6cfe2;
    background: #ffffff;
    color: #222222;
}

.sideaccordian{
		
		font-size: 12px;
		/* border: 0px; */
		border-radius: 5px;
		/* border-bottom:  1px solid #010d1c !important; */
}

        </style>


        <script>
            $(function() {


                $("#container").accordion({
                    collapsible: true,
                    active: false,
                    autoHeight: false,
                    navigation: true
                });


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
    <body onload="StartClock()" onunload="KillClock()" >
        <%--  <div class="headerTD">Welcome <c:out default="" value="${userAuth}"/> </div> --%>

        <div id="container" style="width: 95%;">
        			<!-- border:none;border-bottom: 1px solid #010d1c !important; border-right: 1px solid #010d1c !important;-->
            
            <h5 class="sideaccordian"><a href="#">Students</a></h5>
            
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="Controller?process=StudentProcess&action=viewAll" style="font-size: 12px;">View All</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;">
            				 <a target="mainFrame" href="Controller?process=StudentProcess&action=addNew" style="font-size: 12px;">Add New</a>
            			</td>
            		</tr>
            	</table>
            </div>

            <h5 class="sideaccordian"><a href="#">Fees</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesCollect" style="font-size: 12px;">Fees Collection</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;">
            				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesStructure" style="font-size: 12px;">Fees Structure</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="feesCollectionDetails.jsp" style="font-size: 12px;">Fees Collection Details</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;">
            				 <a target="mainFrame" href="feescancelledreceipts.jsp" style="font-size: 12px;">Cancelled Fees Receipts</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="Controller?process=FeesProcess&action=feesReport" style="font-size: 12px;">Fees Report</a>
            			</td>
            		</tr>
            	</table>
            </div>
            
            
            <h5 class="sideaccordian"><a href="#" >Mess Cards</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="Controller?process=printids&action=generateIds" style="font-size: 12px;">Generate IDs</a>
            			</td>
            		</tr>
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;">
            				 <a target="mainFrame" href="Controller?process=printids&action=cardValidity" style="font-size: 12px;">Card Validity</a>
            			</td>
            		</tr>
            	</table>
            </div>
            
            <h5 class="sideaccordian"><a href="#" >Documents</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				<a target="mainFrame" href="Controller?process=DocumentsProcess&action=studentsDetailsReports" style="font-size: 12px;">Student Details</a>
            			</td>
            		</tr>
            	</table>
            </div>
            
            <h5 class="sideaccordian"><a href="#" >Expenses</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="Controller?process=AdminProcess&action=viewAllExpenses" style="font-size: 12px;">Expenses</a>
            			</td>
            		</tr>
            	</table>
            </div>
            
            <h5 class="sideaccordian"><a href="#" >Send Notifications</a></h5>
            <div style="padding-left: 0px;padding-right: 0px;">
            	<table style=" border-collapse: collapse;width: 100%">
            		<tr>
            			<td style="text-align: left;  padding: 4px;padding-left:40px ;background-color: #f5f8f9;">
            				 <a target="mainFrame" href="Controller?process=SMSProcess&action=sendSMS" style="font-size: 12px;">SMS</a>
            			</td>
            		</tr>
            	</table>
            </div>
            <!-- <div >
               <br/>
                <a target="mainFrame" href="sendemail.jsp" style="font-size: 12px;">Email</a><br/>
            </div> -->
            
            
            
            </div>
                   
            
            <!-- END -->
           
       
        


        </body>

</html>
