<%-- 
    Document   : login
    Created on : Jan 9, 2012, 5:44:56 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Curium - School ERP</title>
        <script type="text/javascript" src="js/openWindow.js"></script>
        <style type="text/css">
            <!--
            .style6 {font-size: 12}
            .style8 {font-family: Tahoma; font-size: 12; color: #333333; }
            .status {font-family: Tahoma; font-size: 12px; color: red;  display: none}
            .style48 {font-family: Arial, Helvetica, sans-serif}
            -->
        </style>


        <script type="text/javascript">
            

            function redirect(){
                
                var form1=document.getElementById("form1");
                form1.action="Controller?process=UserProcess&action=authenticate";
                form1.submit();
            }
        </script>
    </head>
      
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <script type="text/javascript">
            var flag1=<c:out default="" value="${param.login_success}"/>;
            var type='<c:out default="" value="${userType}"/>';
            
            if(flag1){
            	
            	if(type=='superadmin'){
                    window.open('index_superadmin.jsp','_self');
                }else if(type=='admin'){
                    window.open('index_admin.jsp','_self');
                }else if(type=='feescollector'){
                    window.open('index_feescollector.jsp','_self');
                }else if(type=='reception'){                    
                   window.open('','_self');
                    window.close();
                    openIndexPage('index_reception.jsp',screen.width,screen.height);
                }
                else if(type=='staff'){
                	window.open('index.jsp','_self');
                }
            }
            else if(!flag1){
            	window.open('loginFail.jsp','_self');
            }
        </script>
        <form action="Controller?process=UserProcess&action=authenticateUser" method="post" id="form1">
                    	
                                    <table align="center">
                                    	<tr>
                                    	<td>
                                    	<br><br><br>
                                    	</td>
                                    	</tr>
                                    	<tr>
                                    	<td> <img alt="" src="images/curiumlogin.jpg" style="width:300px;height: 300px"></td>
                                    	<td></td><td></td><td></td>
                                    	 <td>
                                    	 <p align="left" style="color: red;">Please enter correct username and password</p>
                                            <img src="images/username.png" height="30" name="login" style="vertical-align: bottom;"/>
                                                    <input name="loginName" type="text"  placeholder="UserName" >
                                                <br><br><br>
                                                <img type="image" src="images/lock.png" height="28" name="login" style="vertical-align: bottom;"/>
                                                <input name="password" type="password" placeholder="Password">
                                                <br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <input type="image" src="images/loginbutton.png" name="login" border="0" />
                                                </td>
                                    	</tr>
                                    </table>
                                    
        </form>
    </body>
</html>
