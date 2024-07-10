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
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>Student Logbook</title>
	<link rel="stylesheet" href="/madina/css/bootstrap3.min.css">
	<script type="text/javascript" src="/madina/js/openWindow.js"></script>
	
	<style type="text/css">
	.main-content{
	width: 50%;
	border-radius: 20px;
	box-shadow: 0 5px 5px rgba(0,0,0,.4);
	margin: 5em auto;
	display: flex;
}
.company__info{
	background-color: #1786b4;
	border-top-left-radius: 20px;
	border-bottom-left-radius: 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	color: #fff;
}
.fa-android{
	font-size:3em;
}
@media screen and (max-width: 640px) {
	.main-content{width: 90%;}
	.company__info{
		display: none;
	}
	.login_form{
		border-top-left-radius:20px;
		border-bottom-left-radius:20px;
	}
}
@media screen and (min-width: 642px) and (max-width:800px){
	.main-content{width: 70%;}
}
.row > h2{
	color:#1786b4;
}
.row > h3{
	color:#1786b4;
}
.login_form{
	background-color: #fff;
	border-top-right-radius:20px;
	border-bottom-right-radius:20px;
	border-top:1px solid #ccc;
	border-right:1px solid #ccc;
}
form{
	padding: 0 2em;
}
.form__input{
	width: 70%;
	border:0px solid transparent;
	border-radius: 0;
	border-bottom: 1px solid #aaa;
	padding: 1em .5em .5em;
	padding-left: 2em;
	outline:none;
	margin:1.5em auto;
	transition: all .5s ease;
}
.form__input:focus{
	border-bottom-color: #1786b4;
	box-shadow: 0 0 5px rgba(0,80,80,.4); 
	border-radius: 4px;
}
.btn{
	transition: all .5s ease;
	//width: 70%;
	border-radius: 30px;
	color:#1786b4;
	font-weight: 600;
	background-color: #fff;
	border: 1px solid #1786b4;
	margin-top: 1.5em;
	margin-bottom: 1em;
	
}
.btn:hover, .btn:focus{
	background-color: #1786b4;
	color:#fff;
}
	
	</style>
	
	     <script type="text/javascript">
            

            function redirect(){
                
                var form1=document.getElementById("form1");
                form1.action="/madina/UserProcess/authenticate";
                form1.submit();
            }
        </script>
        
</head>
   
      
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="">
                
        
       <!-- Main Content -->
       	<div class="row" align="center">
						<h3>Logbook</h3>
					</div>
	<div class="container-fluid">
		<div class="row main-content  text-center" style=" border:2px solid black; width:100%;margin:0px;" >
			<div class="container-fluid">
	
					<div class="row">
								<label style="font-size:45px;">Subject</label><input type="text" style="font-size:45px;" class="form__input" value=" ${studentdiary.subject}">
							</div>
							<div class="row">
							<!-- <textarea class="form__input" > -->
							 	<label style="font-size:45px;">Message</label><p style="text-align:justify ;margin:0px 50px 0px 50px;font-size:45px;">${studentdiary.message}</p><br><br><br>
							</div>
					</div>
			</div>
	</div>
	<div class="container-fluid"  align="center">
					
						<form action="/madina/StudentDiaryProcess/viewDiaryStudentParent?id=${username}&urlbranchid=${Parents.student.branchid}" method="post" class="form-group">
							<div class="row">
								<input type="submit" value="Back" class="btn">
							</div>
						</form>
					
				</div>
    </body>
</html>
