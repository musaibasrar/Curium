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
         <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/popper.min.js"></script>
        <style type="text/css">
            <!--
            .style6 {font-size: 12}
            .style8 {font-family: Tahoma; font-size: 12; color: #333333; }
            .status {font-family: Tahoma; font-size: 12px; color: red;  display: none}
            .style48 {font-family: Arial, Helvetica, sans-serif}
            -->
        </style>
        
        <style type="text/css">
        
        #rcorners1 {
		  border-radius: 25px;
		  border: 0px solid;
		  padding: 10px; 
		  width: 340px;
		  height: 220px; 
		  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		}
		
		#rcorners0 {
		  width: 340px;
		}
		
		.inputfield {
		    width: 80%;
  			/* padding: 12px 20px; */
  			margin: 8px 0;
  			display: inline-block;
  			border: 1px solid #ccc;
  			border-radius: 4px;
  			box-sizing: border-box;
		}
		
		a:link {
                color: black;
                text-decoration: none;
                font-family: arial;
                font-size: 14px;
            }
            a:active {
                color: #ef5b00;
                text-decoration: underline;
            }
            
			a:hover {
				text-decoration: underline;
			}
			
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
                }else if(type=='staff'){
                	window.open('index.jsp','_self');
                }
            }
            else if(!flag1){
            	window.open('loginFail.jsp','_self');
            }
        </script>
        <form action="Controller?process=UserProcess&action=authenticateUser" method="post" id="form1">
                           
              <div class="row" style="padding-left:20%;padding-top: 10%">	
        			<div id="rcorners0">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td align="center">
        						<img alt="" src="images/welcomelogo.jpg" style="width:250px;height: 250px">
        					</td>
        				</tr>
        			</table>
        			</div>
        			
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Physiotherapy </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 70%;" name="loginNamescbpt" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 70%;" name="passwordscbpt" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        </div>
        
        </form>
    </body>
</html>
