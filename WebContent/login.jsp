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
        
            <table width="100%" height="605" border="0" cellpadding="0" cellspacing="0">
                
                <tr>
                    <td>
                    	<table width="550" height="400" border="0"  background="images/backtop.png" align="center" cellpadding="0" cellspacing="0">
								<tr><td><br><br><br><br><br><br><br><br><br><br></td></tr>
								<tr><td><br></td></tr>
                            <tr>
                            
                            	<td>
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	</td>
                                <td>
													
                                    <table width="296"  border="0" cellpadding="0" cellspacing="0">
                                    	
                                        <tr>
                                            <td height="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <img src="images/username.png" height="30" name="login" border="0" /></td>
                                            <td>:</td>
                                            <td><span class="style48">
                                                    <input name="loginName" type="text" size="40" placeholder="UserName" style="width:200px;  border:1px solid #02576c; font-family:Arial; font-size:15px; color:#000000">
                                                </span></td>
                                        </tr>
                                        <tr>
                                            <td height="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <img type="image" src="images/lock.png" height="28" name="login" border="0"  /></td>
                                            <td>:</td>
                                            <td><span class="style48">
                                                    <input name="password" type="password" placeholder="Password" size="40"style="width:200px; border:1px solid #02576c; font-family:Arial; font-size:15px; color:#000000">
                                                </span></td>
                                        </tr>
                                        <tr>
                                            <td width="86" height="35"><span class="style6"></span></td>
                                            <td width="10">&nbsp;</td>
                                            <td width="202"><div align="right">
                                                    <input type="image" src="images/loginbutton.png" name="login" border="0" onclick="" />
                                                </div></td>
                                        </tr>
                                    </table>
                                    <br>
                                    
                                    </td>
                            </tr>
                        </table></td>
                </tr>
            </table>
        </form>
    </body>
</html>
