<%-- 
    Document   : Session Time Out
    Created on : Jan 5, 2012, 1:11:53 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session Time Out</title>
        <style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/iica/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/iica/css/datePicker/demos.css">
        <!--<script type="text/javascript" language="javascript" src="/iica/js/dataTable/jquery.js"></script>-->
        <script type="text/javascript" src="/iica/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" language="javascript" src="/iica/js/dataTable/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/iica/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/iica/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/iica/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/iica/js/datePicker/ui/jquery.ui.button.js"></script>
        
        
        
        
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
                $("#login").button()
            });
            
        </script>
</head>
    <body background="/images/bg.jpg" >
        <form id="form1" method="post">
    <table height="462" class="tableCSS"  >
      <tr>
        <td height="250" align="center" valign="middle"><p class="style1">Session time out, Please login again</p>
        <p class="style1">
          	<!-- <input type="button" value="Login" id="login" > -->
          	<a href="/iica/UserProcess/logout" target="_parent" id="login">Login</a>
        </p></td>
      </tr>
    </table>
            </form>
    </body>
</html>
