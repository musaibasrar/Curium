<%-- 
    Document   : header_admin
    Created on : Feb 13, 2013, 11:10:08 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>CURIUM</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="Description" content = "School,School Management Software,SchoolCRM,">
        <meta name="Keywords" content = "School,School Management Software,SchoolCRM,">
        <link rel="stylesheet" href="/alfarooq/css/bootstrap.min.css">
        <script src="/alfarooq/js/jquery.min.js"></script>
        <script src="/alfarooq/js/bootstrap.min.js"></script>
        <script src="/alfarooq/js/popper.min.js"></script>
     
        <style type="text/css">
            
            .style1 {font-family: Arial, Helvetica, sans-serif;
                     font-size: 12px;
                     color: #666666;
            }
            .style50 {color: #FFFFFF}
            .style6 {font-size: 10; font-family: Arial, Helvetica, sans-serif; color: #FFFFFF; }
            a:link {
                color: #000000;
                text-decoration: none;
                font-family: arial;
                font-size: 10px;
            }
            a:visited {
                color: #383838;
            }
            a:hover {
                text-decoration: underline;
            }
            a:active {
                color: #FFFFFF;
            }
            .style5 {font-family: Calibri; font-size: 14px; color: #FFFFFF; }
            .style51 {
                font-size: 14px;
                font-family: Calibri;
            }
            .style52 {
                font-size: 16px;
                font-weight: bold;
                color: #13358F;
            }
            .noti_bubble {
                position:relative;
                font-size: 11px;
                top: -12px;
                left: 20px;
                padding-right:2px;
                background-color:#4B6A84;
                color:white;
                font-weight:normal;
                z-index: 2;
                width: 30px;
                height: 16px;
                border-radius:2px;
                box-shadow:1px 1px 1px gray;

            }
            .noti_bubbleEmpty {
                position:relative;

                top: -16px;
                left: 10px;
                padding-right:2px;


                width: 20px;
                height: 16px;

            }
            a:visited {
    color: #383838;
    
}
a:hover {
text-decoration: underline;
}
           
        </style>
        <script type="text/javascript">
            function logout(){
                var form1=document.getElementById("form1");
                form1.action="/alfarooq/UserProcess/logout";
                form1.submit();
            }

        </script>
    </head>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alfarooq/UserProcess/sessionTimeOut");
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
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
         <div style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
    transition: 0.3s;
        ">
        
                        <table width="100%" border="0" align="center"
						cellpadding="2" cellspacing="0" bordercolor="#FFFFFF">
						<tr><td></td></tr>
						<tr style="height: 45px;">
							
									<td style="padding-left: 20px;">
								<a target="mainFrame" href="/alfarooq/welcome" ><label style="font-family: Tahoma;font-weight: bolder;color: #143160;font-size: 14px;">${branchname}</label></a>
								</td>
								
							<!-- <td align="left"><img src="/alfarooq/images/alfarooqheader.png"
								style="width: 200px; height: 20px;" /></td> -->
							<!-- <td><a target="leftFrame" href="/alfarooq/left_officeadmin"> <img
									src="/alfarooq/images/home.svg" width="18" height="18" 
									alt="Home" style="vertical-align: bottom;font-size: 144px;" />Home
							</a></td>

							<td><a target="mainFrame"
								href="/alfarooq/FeesProcess/feesCollect"> <img
									src="/alfarooq/images/feescollect.svg" width="18" height="18"
									alt="Fees Collection" style="vertical-align: bottom;" />Fees Collection
							</a></td>

							<td>
								<a target="mainFrame"
								href="/alfarooq/AccountProcess/createVoucher"> <img
									src="/alfarooq/images/createvoucher.svg" width="18" height="18"
									alt="Create Voucher" style="vertical-align: bottom;" />
									Create Voucher
								</a>
							</td>

							<td ><a target="mainFrame" href="/alfarooq/feesCollectionDetails"><img  alt="Fees Details" src="/alfarooq/images/feescoll.png" width="30" height="30" /> <div id="" class="noti_bubbleEmpty"></div><div id="" class="noti_bubbleEmpty"></div></a></td>
                                            <td><label style="color:white;font-size: 12px;">Fees <br>Details</label></td>

							<td><a target="mainFrame"
								href="/alfarooq/StudentProcess/addNew"> <img
									src="/alfarooq/images/student_header.svg" width="18" height="18"
									alt="Add New Student" style="vertical-align: bottom;" />Add Student
							</a></td>

							<td ><a target="leftFrame" href="/alfarooq/leftsettings_officeadmin">
									<img alt="Settings" src="/alfarooq/images/settings.svg" width="18"
									height="18" style="vertical-align: bottom;" />Master Settings
							</a></td>

							<td ><a target="mainFrame"
								href="/alfarooq/AdminProcess/viewAllExpenses"><img
									alt="Admin Exp" src="/alfarooq/images/adminexp.svg" width="18" height="18" style="vertical-align: bottom;"/>
									Admin Expense
							</a></td>

							<td ><a target="mainFrame" href="/alfarooq/sendsms"><img
									src="/alfarooq/images/sendmessage.svg" width="18" height="18" alt="Send SMS" style="vertical-align: bottom;"/>
									Send Message		
							</a>
								<a target="leftFrame" href="/alfarooq/reports_officeadmin"><img
									src="/alfarooq/images/reports.svg" width="18" height="18" alt="Reports" style="vertical-align: bottom;"/>
									Reports		
								</a>
							
							</td>

							<td ><a target="mainFrame"
								href="/alfarooq/StudentProcess/viewAllStudentsWithParents"><img
									alt="View All Students" src="/alfarooq/images/students.svg" width="18"
									height="18" style="vertical-align: bottom;"/>
								View Students
							</a></td> -->
							
							<!-- <td ><a target="mainFrame"
								href="/alfarooq/UserProcess/dashBoard"><img
									alt="Dash Board" src="/alfarooq/images/dashboard.svg" width="18"
									height="18" style="vertical-align: bottom;"/>
									Dash Board
									</a></td> -->
								
							<td ><a target="_parent"
								href="/alfarooq/UserProcess/logout"><img
									src="/alfarooq/images/logout.svg" width="18" height="18" alt="Log Out" 
									style="vertical-align: bottom;"/>Logout</a></td>
							<td width="60"></td>
						</tr>

					</table>
            </div>
       <!--  <hr style="border-top: 5px solid rgba(1,1,1,1);"> -->
    </body>

</html>
