<%-- 
    Document   : Saved
    Created on : Jan 5, 2012, 1:11:53 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>sms success</title>
        <style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/alfarooq/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/alfarooq/css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="/alfarooq/js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="/alfarooq/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="/alfarooq/js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/alfarooq/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/alfarooq/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/alfarooq/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/alfarooq/js/datePicker/ui/jquery.ui.button.js"></script>
        
        
        
        
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
                $("#sendsmspage").button()

                });

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
    <body background="/images/bg.jpg" >
        <form id="form1" action="/alfarooq/SMSProcess/sendSMS"  method="post">
    <table height="462" class="tableCSS"  >
      <tr>
        <td height="250" align="center" valign="middle"><p class="style1" style="color:red;font-size: 20px;">SMS sending failed</p>
        <p class="style1">
            <input type="submit" value="Resend SMS" id="sendsmspage">
        </p></td>
      </tr>
    </table>
            </form>
    </body>
</html>
