<%-- 
    Document   : Welcome 
    Created on : Apr 08, 2019, 09:38:53 PM
    Author     : Musaib
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dash Board</title>
    <script src="/alfalahschool/js/Chart.min.js"></script>
    <link rel="stylesheet" href="/alfalahschool/css/bootstrap.min.css">
    <script src="/alfalahschool/js/jquery.min.js"></script>
    <script src="/alfalahschool/js/bootstrap.min.js"></script>
    <script src="/alfalahschool/js/popper.min.js"></script>
    <style type="text/css">
        @font-face {
            font-family: "IBMPlexSans";
            src: url("fonts/IBMPlexSans-Regular.ttf");
        }
        #rcorners1 {
            border-radius: 25px;
            padding: 10px;
            text-align: center;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }
        #labelname, #labelnumber {
            font-family: IBMPlexSans;
            font-size: 14px;
        }
        a:link {
            color: black;
            text-decoration: none;
            font-family: arial;
            font-size: 14px;
        }
        a:active, a:hover {
            color: #ef5b00;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
    response.sendRedirect("/alfalahschool/UserProcess/sessionTimeOut");
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
    <div class="container mt-5">
        <div class="text-left mb-4">
            <p style="text-transform: uppercase; color: #143160; font-weight: bolder;">
                Welcome, <label style="color: #93051f;"><c:out default="" value="${username}" /></label>
            </p>
            <p style="color: #93051f; font-weight: bolder;">${branchname}</p>
        </div>
        <div class="d-flex justify-content-center">
            <div class="col-md-5 mb-4">
                <div id="rcorners1">
                    <label style="font-family: Tahoma; font-weight: bolder; color: #5E87B0; font-size: 18px;">Al-Falah Islamic School</label><br>
                    <a target="_parent" href="/alfalahschool/UserProcess/multiUser?branchid=2">
                        <img src="/alfalahschool/images/login.svg" width="25" height="25" alt="Login"/>Login
                    </a>
                </div>
            </div>
            <div class="col-md-5 mb-4">
                <div id="rcorners1">
                    <label style="font-family: Tahoma; font-weight: bolder; color: #5E87B0; font-size: 18px;">Al-Falah Educare Academy</label><br>
                    <a target="_parent" href="/alfalahschool/UserProcess/multiUser?branchid=3">
                        <img src="/alfalahschool/images/login.svg" width="25" height="25" alt="Login"/>Login
                    </a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
