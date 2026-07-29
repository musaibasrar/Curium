<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
}

/* body{
background:#f0f0f0;
display:flex;
justify-content:center;
padding:20px;
font-family:Arial,Helvetica,sans-serif;
} */
 @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }

.card{

width:210px;
height:322px;
background:#fff;
border-radius:10px;
overflow:hidden;
position:relative;
border:2px solid #d7d7d7;
box-shadow:0 0 6px rgba(0,0,0,.25);
margin: 20px;

}

svg{
position:absolute;
left:0;
top:0;
width:100%;
height:100%;
}

.topLogo{

position:absolute;
top:12px;
width:100%;
text-align:center;
z-index:5;

}

.schoolName{

font-size:15px;
font-weight:bold;
line-height:15px;
margin-top:2px;
letter-spacing:.5px;

}

.schoolSub{

font-size:10px;
font-weight:600;
letter-spacing:1px;

}

.photoWrap{

position:absolute;
top:92px;
left:50%;
transform:translateX(-50%);
width:112px;
height:112px;
border-radius:50%;
overflow:hidden;
background:white;
border:1px solid #999;
z-index:10;

}

.photoWrap img{

width:100%;
height:100%;
object-fit:cover;

}

.details{

position:absolute;
top:208px;
left:0;
width:100%;
text-align:center;
z-index:20;

}

.name{

font-size:11px;
font-weight:900;
color:#143a77;
letter-spacing:.4px;
line-height:14px;
text-transform:uppercase;

}

.designation{

margin-top:2px;
font-size:8px;
font-weight:bold;
color:#111;

}

.info{

font-size:6.6px;
font-weight:700;
color:#111;
line-height:8px;
margin-top:3px;

}

.address{

padding:0 14px;

}

.secretary{

position:absolute;
right:8px;
bottom:16px;
text-align:center;
z-index:20;

}

.sign{

margin-bottom:-2px;

}

.secretaryText{

font-size:6px;
font-weight:bold;
color:#555;

}
</style>
<style>
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
<body class="bodymargin">

 <c:set var="iInitial" value="${iInitial}"/>
         <c:set var="limit" value="1"/>
                        
          <c:forEach begin="1" end="${endValue}">
                        <%!                        
                            int i = 1;
                        %>
			<c:if test="${limit < iInitial}">	

<div class="card">

<svg viewBox="0 0 210 322">

<defs>

<linearGradient id="sky" x1="0" y1="0" x2="0" y2="1">

<stop offset="0%" stop-color="#bfefff"/>

<stop offset="100%" stop-color="#d9f7ff"/>

</linearGradient>

</defs>

<!-- top background -->

<path
d="
M0 0
H210
V120
L165 140
H45
L0 120
Z"
fill="url(#sky)"/>

<!-- left white triangle -->

<polygon
points="
0,118
60,142
0,170"
fill="white"/>

<!-- right white triangle -->

<polygon
points="
210,118
150,142
210,170"
fill="white"/>

<!-- left blue stripe -->

<polygon
points="
0,150
56,170
52,176
0,156"
fill="#203d78"/>

<!-- right blue stripe -->

<polygon
points="
210,150
154,170
158,176
210,156"
fill="#203d78"/>

<!-- bottom bar -->

<rect
x="0"
y="292"
width="210"
height="30"
fill="#1d376b"/>

</svg>

<div class="topLogo">

<!-- Logo -->



<div class="schoolName">
<img src="/bright/images/bright.png" width="65" height="72"/>
</div>



</div>

<div class="photoWrap">

<img src="data:image;base64,<%= request.getSession().getAttribute("employeephoto" + i + "")%>" alt="Photo" />

</div>

<!--==========================
    DETAILS SECTION
===========================-->

<div class="details">

    <div class="name">
         <%= request.getSession().getAttribute("teachername" + i + "")%>
    </div>

    <div class="designation">
        <%= request.getSession().getAttribute("designation" + i + "")%>
    </div>

    <div class="info">
        <b>Blood Group:</b>  <%= request.getSession().getAttribute("blodgroup" + i + "")%>
    </div>

    <div class="info">
        <b>DOB:</b> <%= request.getSession().getAttribute("dateofjoining" + i + "") %>
    </div>

    <div class="info address">
        <b>ADDRESS:</b>  <%= request.getSession().getAttribute("Address" + i + "") %>
    </div>

    <div class="info">
        <b>Mob:</b>  <%= request.getSession().getAttribute("contactnumber" + i + "") %>
    </div>

</div>

<!-- Secretary -->

<!-- <div class="secretary">

    <div class="sign">
        <svg width="55" height="20" viewBox="0 0 120 40">

            <path
            d="
            M5 25
            C15 5 20 32 28 16
            C35 5 40 30 48 12
            C60 2 63 32 70 10
            C80 18 88 3 98 18
            C106 25 112 10 118 18"
            fill="none"
            stroke="#000"
            stroke-width="3"
            stroke-linecap="round"/>

        </svg>
    </div>

    <div class="secretaryText">
        Secretary
    </div>
 -->
</div>

</div>
 </c:if>
   <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach>
                    <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>
   <div class="print-btn">
    <button onclick="window.print()">Print</button>
</div>                      
                        
</body>
</html>