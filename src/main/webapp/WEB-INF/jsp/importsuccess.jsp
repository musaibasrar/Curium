<%-- 
    Document   : import file success
    Created on : Sep 08, 2021, 4:55:53 PM
    Author     : Adeeba
--%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Import File Success</title>
<style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="/js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="/js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/js/datePicker/ui/jquery.ui.button.js"></script>
        
        
        
        
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

       
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=ImportProcess&action=sessionTimeOut");
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
        <form id="form1" method="post">
    <table height="462" class="tableCSS"  >
      <tr>
          <td height="250" align="center" style="font-color:red" valign="middle"><p class="style1">File Imported Successfully</p>
          <td width="26%"  class="alignRightYear">
                            
                             
                            </td>
                            
            
		</td>
      </tr>
    </table>
            </form>
    </body>
</html>
