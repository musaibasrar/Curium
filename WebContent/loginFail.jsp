<%-- 
    Document   : login_Fail
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
		
		#xclose{
			background-color: black; 
			width: 160px;
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
		
		#text{
			  position: absolute;
			  top: 50%;
			  left: 50%;
			  font-size: 30px;
			  color: white;
			  transform: translate(-50%,-50%);
			  -ms-transform: translate(-50%,-50%);
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
			
			#overlay {
  position: fixed; /* Sit on top of the page content */
  display: none; /* Hidden by default */
  width: 100%; /* Full width (cover the whole page) */
  height: 100%; /* Full height (cover the whole page) */
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0,0,0,0.5); /* Black background with opacity */
  z-index: 2; /* Specify a stack order in case you're using a different order for other elements */
  cursor: pointer; /* Add a pointer on hover */
}
			
		</style>

        <script type="text/javascript">
            

            function redirect(){
                
                var form1=document.getElementById("form1");
                form1.action="Controller?process=UserProcess&action=authenticate";
                form1.submit();
            }
            
            function on() {
            	  document.getElementById("overlay").style.display = "block";
            	}

            	function off() {
            	  document.getElementById("overlay").style.display = "none";
            	}

            	
        </script>
    </head>
      
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="on();">
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
                           
              <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        	</div>
        	<div class="col"> 
        			<div id="rcorners0">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td align="center">
        						<img alt="" src="images/welcomelogo.jpg" style="width:200px;height: 200px">
        					</td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        	
        	 </div>
        </div>
        
             <div><br><br></div>
        
        <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Super Admin </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNameSuperAdmin" type="text" placeholder="Username"><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordSuperAdmin" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        	
        	 </div>
        </div>
        
             <div><br><br></div>
             
        	<div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Nursing </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNamescn" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordscn" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise College of Pharmacy </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNamescp" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordscp" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
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
        </div>
        
             <div><br><br></div>
             
             <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sunrise Institute </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Paramedical Sciences </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;" name="loginNamesips" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;" name="passwordsips" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> HPR College</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (B.Sc. Nursing) </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;" name="loginNamehpr" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;" name="passwordhpr" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        		<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Karnataka School</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNamekksn" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordkksn" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	 </div>
        </div>
        
             <div><br><br></div>
             
             <div class="row">
        	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        	<div class="col">
        			<div id="rcorners1">
        			
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Koushthubha School </label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> of Nursing</label>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;"  name="loginNameksn" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" style="width: 60%;"  name="passwordksn" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	<div class="col"> 
        			<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Sri Sai School of Nursing </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNamesssn" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordsssn" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	</div>
        	
        	
        	<div class="col">
        		<div id="rcorners1">
        			<table style="margin-left: auto;margin-right: auto;">
        				<tr>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td></td>
        					<td align="center">
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> Kshatriya School of Nursing </label><br>
        						<label style="font-family: Tahoma;font-weight: bolder;color: #5E87B0;font-size: 18px;"> (GNM) </label><br>
        						<img src="images/user--filled.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="loginNamekssn" type="text"  placeholder="Username" ><br>
        						<img src="images/password.svg" width="22" height="22" alt="Login" style="vertical-align: center;" /><input class="inputfield" name="passwordkssn" type="password" placeholder="Password"><br>
        						<input type="image" width="35" height="45" name="Login" src="images/login.svg" />
        					</td>
        					<td></td>
        					<td></td>
        					<td></td>
        				</tr>
        			</table>
        			</div>
        	
        	 </div>
        </div>
             <div><br><br></div>
             				 <div id="overlay" >
                                    	<div id="text" onclick="off()">Please enter correct username and password<br><br>
                                    	<div align="center" id="xclose" style="margin-left: auto;margin-right: auto;font-size: 20px;">
                                    			X Close
                                    	</div>
                                    	</div>
                                    	
                                    </div>
        </form>
    </body>
</html>
