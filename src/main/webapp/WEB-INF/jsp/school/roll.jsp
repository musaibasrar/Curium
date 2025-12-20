<%-- 
    Document   : role
    Created on : Jan 9, 2012, 5:44:56 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	 <title>Curium - School ERP</title>
	<link rel="stylesheet" href="/alirfan/css/bootstrap3.min.css">
	<script type="text/javascript" src="/alirfan/js/openWindow.js"></script>
	
	<style type="text/css">
	.main-content{
	width: 50%;
	border-radius: 20px;
	box-shadow: 0 5px 5px rgba(0,0,0,.4);
	margin: 5em auto;
	display: flex;
}
.company__info{
	background-color: #ffffff;
	border-top-left-radius: 20vmax;
	border-bottom-left-radius: 20px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	color: #7e0707;
}
.fa-android{
	font-size:3em;
}

.bg-success {
    background-color: #243664;
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
	border-bottom-right-radius:20vmax;
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
	width: 40%;
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
	
        
</head>
   
      
    <body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
        <c:choose>
    <c:when test="${userType == 'superadmin'}">
        <c:redirect url="/index_superadmin"/>
    </c:when>

    <c:when test="${userType == 'admin'}">
        <c:redirect url="/index_admin"/>
    </c:when>

    <c:when test="${userType == 'feescollector'}">
        <c:redirect url="/index_feescollector"/>
    </c:when>

    <c:when test="${userType == 'officeadmin'}">
        <c:redirect url="/index_officeadmin"/>
    </c:when>

    <c:when test="${userType == 'teacher'}">
        <c:redirect url="/index_teacher"/>
    </c:when>

    <c:when test="${userType == 'marksentry'}">
        <c:redirect url="/index_marksentry"/>
    </c:when>

    <c:when test="${userType == 'parents'}">
        <c:redirect url="/index_parents"/>
    </c:when>

    <c:otherwise>
        <c:redirect url="/login"/>
    </c:otherwise>
</c:choose>
    </body>
</htm