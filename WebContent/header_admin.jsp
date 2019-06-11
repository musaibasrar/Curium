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
        <script type="text/javascript">
            var getMember;
            //var getVisit;
            function getdata() {

                if (typeof XMLHttpRequest != "undefined") {
                    getMember = new XMLHttpRequest();
                   // getVisit = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    getMember = new ActiveXObject("Microsoft.XMLHTTP");
                    //getVisit = new ActiveXObject("Microsoft.XMLHTTP");
                }

                getMember.onreadystatechange = processMemberData;
                getMember.open("POST", "StudentController",true);
                getMember.send(null);

                /* getVisit.onreadystatechange = processVisitData;
                getVisit.open("POST", "AppointmentController",true);
                getVisit.send(null); */
            }

            function processMemberData() {
                if (getMember.readyState==4)
                {
                    if (getMember.status==200){

                        var count = getMember.responseXML.getElementsByTagName("count")[0];
                        var childCount=count.childNodes[0].nodeValue;
                        var mdiv = document.getElementById("n1");
                        mdiv.innerHTML=childCount;
                        mdiv.style.visibility='visible';
                        //setTimeout('getdata();', 6000);


                    }
                }

            }
        
        </script>
     
        <style type="text/css">
            
            .style1 {font-family: Arial, Helvetica, sans-serif;
                     font-size: 12px;
                     color: #666666;
            }
            .style50 {color: #FFFFFF}
            .style6 {font-size: 10; font-family: Arial, Helvetica, sans-serif; color: #FFFFFF; }
            a:link {
                color: #FFFFFF;
                text-decoration: none;
                font-family: arial;
                font-size: 10px;
            }
            a:visited {
                color: #FFFFFF;
            }
            a:hover {
                color: yellow;
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
           
        </style>
        <script type="text/javascript">
            function logout(){
                var form1=document.getElementById("form1");
                form1.action="Controller?process=UserProcess&action=logout";
                form1.submit();
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
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <form id="form1" method="post" >
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top" background="images/h_bg.jpg">
                        <table width="100%" border="0" align="center"
						cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
						<tr>

							<td align="left"><img src="images/curiumheader.png"
								style="width: 200px; height: 70px;" /></td>
							<td><a target="leftFrame" href="left_admin.jsp"> <img
									src="images/house_home.png" width="35" height="35"
									alt="Home" style="vertical-align: bottom;" />Home
							</a></td>

							<td><a target="mainFrame"
								href="Controller?process=FeesProcess&action=feesCollect"> <img
									src="images/feescollectw.png" width="35" height="35"
									alt="Fees Collection" style="vertical-align: bottom;" />Fees
									Collect
							</a></td>

							<td><a target="mainFrame"
								href="Controller?process=AccountProcess&action=createVoucher">
									<img alt="Create Voucher" src="images/voucherw.png" width="30"
									height="30" />Create Voucher
							</a></td>

							<!-- <td ><a target="mainFrame" href="feesCollectionDetails.jsp"><img  alt="Fees Details" src="images/feescoll.png" width="30" height="30" /> <div id="" class="noti_bubbleEmpty"></div><div id="" class="noti_bubbleEmpty"></div></a></td>
                                            <td><label style="color:white;font-size: 12px;">Fees <br>Details</label></td> -->

							<td><a target="mainFrame"
								href="Controller?process=StudentProcess&action=addNew"> <img
									src="images/Usersw.png" width="30" height="30"
									alt="Add New Student" style="vertical-align: bottom;" />Add Student
							</a></td>

							<td ><a target="leftFrame" href="leftsettings.jsp">
									<img alt="Settings" src="images/ssettingsw.png" width="30"
									height="30" style="vertical-align: bottom;" />Master Settings
							</a></td>

							<td ><a target="mainFrame"
								href="Controller?process=AdminProcess&action=viewAllExpenses"><img
									alt="Admin Exp" src="images/adexpw.png" width="30" height="30" style="vertical-align: bottom;"/>
									Admin Expense
							</a></td>

							<td ><a target="mainFrame" href="sendsms.jsp"><img
									src="images/sendsmsw.png" width="30" height="30" alt="Send SMS" style="vertical-align: bottom;"/>
									Send Message		
							</a></td>

							<td ><a target="mainFrame"
								href="Controller?process=StudentProcess&action=viewAllStudentsWithParents"><img
									alt="View All Students" src="images/allusersw.png" width="30"
									height="30" style="vertical-align: bottom;"/>
								View Students
							</a></td>
							
							<td ><a target="mainFrame"
								href="Controller?process=UserProcess&action=dashBoard"><img
									alt="Dash Board" src="images/dashboardw.png" width="30"
									height="30" style="vertical-align: bottom;"/>
									Dash Board
									</a></td>
								
							<td ><a target="_parent"
								href="Controller?process=UserProcess&action=logout"><img
									src="images/logoutw.png" width="30" height="30" alt="Log Out" /></a></td>
							<td width="60"></td>
						</tr>

					</table>
				</td>
                </tr>
            </table>
            <script>
                getdata();
            </script>
        </form>
    </body>

</html>
