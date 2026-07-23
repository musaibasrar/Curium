<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <style>
       body{
    background:#f2f2f2;
    font-family:Arial, sans-serif;
}

.card{
    width:210px;
    height:322px;

    background:#fff;

    margin:20px auto;

    position:relative;
    overflow:hidden;

    border:1px solid #ccc;
    border-radius:12px;
}

/* Curved Text */
.curveText{
    width:180px;
    height:65px;
    display:block;
    margin:5px auto 0;
}

.curveText text{
    font-size:11px;
    font-weight:bold;
    fill:#000;
}

/* Logo */
.logo{
    width:65px;
    height:65px;

    margin:-5px auto 0;


    display:flex;
    justify-content:center;
    align-items:center;
}

.logo img{
    width:100%;
    height:100%;
    object-fit:contain;
}

/* School Name */
.schoolName{
    margin-top:6px;
    text-align:center;
    font-size:18px;
    font-weight:bold;
}

/* Address */
.address{
    margin-top:4px;
    text-align:center;
    font-size:11px;
    line-height:15px;
}

/* Footer */
.footer{
    position:absolute;
    bottom:0;
    left:0;

    width:100%;
    height:48px;

    background:#222;
    color:#fff;

    text-align:center;

    font-size:10px;
    line-height:14px;

    padding-top:12px;

    clip-path:polygon(0 18%,50% 0,100% 18%,100% 100%,0 100%);
}
.print-btn{
            text-align: center;
            margin: 10px;
        }

        @media print{
            .print-btn{
                display: none;
            }
        }

    </style>
    <link rel="stylesheet" href="style.css">
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/bright/UserProcess/sessionTimeOut");
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
<body>

<div class="card">

    <!-- Curved Text -->
    <svg class="curveText" viewBox="0 0 300 150">
        <defs>
            <path id="curve"
                  d="M40,120 A110,110 0 0,1 260,120"/>
        </defs>

        <text font-size="18" font-weight="bold">
            <textPath href="#curve" startOffset="50%" text-anchor="middle">
                O LORD INCREASE ME IN KNOWLEDGE
            </textPath>
        </text>
    </svg>

    <!-- Logo -->
    <div class="logo">
        <!-- Place your logo image here -->
         <img src="/bright/images/bright.png"> 
    </div>

    <div class="schoolName">
        BRIGHT SCHOOL
    </div>

    <div class="address">
        Vidya Nagar,Virapet<br>
        S.KODAGU-571218
    </div>

    <div class="footer">
        Ph: 819785778 / 94811772288<br>
        Email: brightschoolhm@gmail.com
    </div>

</div>
<div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>
</body>
</html>