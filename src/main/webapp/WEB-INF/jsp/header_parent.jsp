<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CURIUM_PARENT_HEADER</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
        <meta name="Description" content = "School,School Management Software,SchoolCRM,">
        <meta name="Keywords" content = "School,School Management Software,SchoolCRM,">
        <link rel="stylesheet" href="/demov2/css/bootstrap.min.css">
        <script src="/demov2/js/jquery.min.js"></script>
        <script src="/demov2/js/bootstrap.min.js"></script>
        <script src="/demov2/js/popper.min.js"></script>
     
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
                form1.action="/demov2/UserProcess/logout";
                form1.submit();
            }

        </script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/demov2/UserProcess/sessionTimeOut");
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
						cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
						<tr><td></td></tr>
						<tr style="height: 45px;">
							
									<td style="padding-left: 20px;width: 170px;">
								<a target="mainFrame" href="/demov2/welcomeparent" ><img src="/demov2/images/curiumheader.png" width="90" height="30"/></a>
								</td>
								
							<!-- <td align="left"><img src="/demov2/images/demov2/header.png"
								style="width: 200px; height: 20px;" /></td> -->
							<td align="left" style="padding-left:40px;">
 
                                                         <a target="mainFrame" href="/demov2/welcomeparent" style="display: inline-flex; align-items: center;">
     <img src="/demov2/images/home.svg" width="22" height="22" alt="Home" style="margin-right: 4px;" />
     <strong style="font-size: 18px;">Home</strong>
 </a>&nbsp;&nbsp;
 
                                                          <a target="_parent"
                                                                 href="/demov2/UserProcess/logout" style="display: inline-flex; align-items: center;"><img
                                                                         src="/demov2/images/logout.svg" width="22" height="22" alt="Log Out" 
                                                                 /><strong style="font-size: 17px;">Logout</strong></a></td>


						</tr>

					</table>
            </div>
       <!--  <hr style="border-top: 5px solid rgba(1,1,1,1);"> -->
</body>
</html>