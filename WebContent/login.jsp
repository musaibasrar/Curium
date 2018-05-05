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
        <title>Welcome</title>
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
    <body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <script type="text/javascript">
            var flag1=<c:out default="" value="${param.login_success}"/>;
            var type='<c:out default="" value="${userType}"/>';
            
            if(flag1){
                if(type=='admin'){
                    window.open('index.jsp','_self');
                }
                else if(type=='reception'){                    
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
                    <td><table width="425" height="290" border="0" align="center" cellpadding="0" cellspacing="0">


                            <tr>
                                <td width="425" valign="bottom" background="images/login_page.jpg">

                                    <table width="296" height="105" border="0" align="center" cellpadding="0" cellspacing="0">
                                        <tr id="status">
                                            <td  height="23"  colspan="2" class="status">&nbsp;</td>
                                        </tr>
                                        <tr id="status">
                                            <td  height="23"  colspan="2" class="status"><br/></td>
                                        </tr>
                                        <tr>
                                            <td height="23"><span class="style8">User Name</span></td>
                                            <td>:</td>
                                            <td><span class="style48">
                                                    <input name="loginName" type="text" size="40"style="width:200px; border:1px solid #3993DB; font-family:Arial; font-size:12px; color:#000000">
                                                </span></td>
                                        </tr>
                                        <tr>
                                            <td height="23"><span class="style8">Password</span></td>
                                            <td>:</td>
                                            <td><span class="style48">
                                                    <input name="password" type="password" size="40"style="width:200px; border:1px solid #3993DB; font-family:Arial; font-size:12px; color:#000000">
                                                </span></td>
                                        </tr>
                                        <tr>
                                            <td width="86" height="35"><span class="style6"></span></td>
                                            <td width="10">&nbsp;</td>
                                            <td width="202"><div align="right">
                                                    <input type="image" src="images/button.png" name="login" border="0" onclick="" />
                                                </div></td>
                                        </tr>
                                        <tr  >
                                            <td  colspan="2" ><span id="label1" class="status">Login Failed</span></td>
                                        </tr>


                                    </table>
                                    <p>&nbsp;</p>
                                    <p>&nbsp;</p></td>
                            </tr>
                        </table></td>
                </tr>
            </table>
        </form>
    </body>
</html>
