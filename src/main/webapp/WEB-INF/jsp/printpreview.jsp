<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>

<meta charset="UTF-8">

<title>School ID Card</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
}

body{

font-family:Arial,Helvetica,sans-serif;
display:flex;
padding:20px;
margin: 0;
flex-direction: column;   /* Vertical arrangement */
align-items: left;      /* Center horizontally */
gap: 10px; 

}

.card{

position:relative;

width:210px;
height:322px;

background:#fff;

border-radius:12px;
overflow:hidden;

border:1px solid #bdbdbd;

box-shadow:0 3px 10px rgba(0,0,0,.25);

margin:5px;

}

/*==============================
SVG
===============================*/

.bgsvg{

position:absolute;

left:0;
top:0;

width:100%;
height:100%;

z-index:1;

}

/*==============================
LOGO PLACE
===============================*/

.logo{

position:absolute;

left:8px;

top:9px;

width:44px;

height:44px;

border-radius:50%;

background:white;

border:2px solid #d8c26b;

display:flex;

justify-content:center;

align-items:center;

overflow:hidden;

z-index:20;

}

.logo img{

width:100%;
height:100%;
object-fit:cover;

}

/*==============================
HEADER
===============================*/

.header{

position:absolute;

left:58px;
top:6px;

width:142px;

text-align:center;

z-index:20;

color:#fff;

}

.trust{

font-size:7px;
font-weight:bold;
line-height:9px;

}

.school{

margin-top:3px;

font-size:12px;
font-weight:900;

line-height:13px;

letter-spacing:.4px;

text-transform:uppercase;

}

.medium{

margin-top:3px;

font-size:5.8px;
font-weight:bold;

line-height:8px;

}

/*==============================
PHOTO PLACE
===============================*/

.photo{

position:absolute;

left:63px;
top:84px;

width:84px;
height:104px;

background:white;

border:2px solid #7a5561;

border-radius:8px;

padding:2px;

overflow:hidden;

z-index:20;

box-shadow:
0 0 3px rgba(0,0,0,.25);

}

.photo img{

width:100%;
height:100%;

object-fit:cover;

border-radius:5px;

}


/*==============================
PLACEHOLDER STYLE
===============================*/

.placeholder{

font-size:10px;
color:#888;
font-weight:bold;

}

/*==============================
DETAILS
==============================*/

.details{

position:absolute;

left:14px;

top:202px;

width:182px;

z-index:20;

}

.infoTable{

width:100%;

border-collapse:collapse;

}

.infoTable td{

vertical-align:top;

padding:2px 0;

}

.label{

width:52px;

font-size:7px;

font-weight:normal;

color:#23236a;

}

.colon{

width:8px;

text-align:center;

font-size:7px;

font-weight:bold;

color:#222;

}

.value{

font-size:7px;

font-weight:normal;

color:#222;

line-height:9px;

}

.nameValue{

font-weight:bold;

font-size:8px;

color:#2c2d86;

}

.addressLabel{

padding-top:2px;

}

.addressValue{

line-height:9px;

}

/*==============================
SIGNATURE
==============================*/

.hmSign{

position:absolute;

right:12px;

bottom:23px;

width:55px;

text-align:center;

z-index:20;

}

.hmSign img{

width:30px;

height:25px;

object-fit:contain;

}

.signPlaceholder{

width:48px;

height:20px;

margin:auto;

border-bottom:1px solid #999;

display:flex;

align-items:center;

justify-content:center;

font-size:7px;

color:#888;

font-style:italic;

}

.hmText{

margin-top:3px;

font-size:6px;

font-weight:bold;

/* color:#2c2d86; */
color:white;

}
.signatureBox{

width:48px;

height:20px;

border-bottom:1px solid #999;

display:flex;

align-items:center;

justify-content:center;

font-size:6px;

font-style:italic;

color:#888;

}
/*==============================
FOOTER
==============================*/

.footerText{

position:absolute;

left:10px;

bottom:6px;

width:190px;

text-align:center;

font-size:5.5px;

font-weight:bold;

line-height:7px;

color:white;

letter-spacing:.2px;

z-index:20;

}
</style>
<style>
 .print-btn{
            text-align: center;
            margin: 10px;
        }

       @media print{

    body{
        display:block;
        padding:0;
        margin:0;
    }

    .card{
        display:block;
        margin:20px;
        page-break-inside: avoid;
        break-inside: avoid;
    }

    .print-btn{
        display:none;
    }
}

</style>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/mfds/UserProcess/sessionTimeOut");
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
 <c:set var="iInitial" value="${iInitial}"/>
         <c:set var="limit" value="1"/>
                        
          <c:forEach begin="1" end="${iInitial}">
                        <%!                        
                            int i = 1;
                        %>
			<c:if test="${limit < iInitial}">	
<div class="card">

<svg class="bgsvg" viewBox="0 0 210 322" preserveAspectRatio="none">

<defs>

<linearGradient id="headBlue" x1="0" y1="0" x2="0" y2="1">

<stop offset="0%" stop-color="#5f66c5"/>

<stop offset="100%" stop-color="#323a8c"/>

</linearGradient>

<linearGradient id="cyan" x1="0" y1="0" x2="1" y2="1">

<stop offset="0%" stop-color="#11d6ff"/>

<stop offset="100%" stop-color="#00a8ff"/>

</linearGradient>

<linearGradient id="footerBlue" x1="0" y1="0" x2="0" y2="1">

<stop offset="0%" stop-color="#4f4da8"/>

<stop offset="100%" stop-color="#2a2c73"/>

</linearGradient>

</defs>

<!-- Header -->

<path
d="
M0 0
H210
V90
L170 124
H40
L0 90
Z"
fill="url(#headBlue)"/>

<!-- Decorative polygons -->

<polygon
points="0,45 65,110 50,122 0,60"
fill="#4347a5"/>

<polygon
points="210,45 145,110 160,122 210,60"
fill="#4347a5"/>

<!-- Cyan strips -->

<polygon
points="0,86 46,126 36,138 0,100"
fill="url(#cyan)"/>

<polygon
points="210,86 164,126 174,138 210,100"
fill="url(#cyan)"/>

<!-- White body -->

<rect
x="0"
y="136"
width="210"
height="156"
fill="#ffffff"/>

<!-- Footer -->

<path
d="
M0 292
H210
V322
H0
Z"
fill="url(#footerBlue)"/>

<!-- Footer highlight -->

<path
d="
M0 292
H210"
stroke="#9ea4ff"
stroke-width="1"
fill="none"/>
</svg>

<!-- LOGO -->

<div class="logo">

<!-- Replace with your logo -->
<!-- <img src="logo.png"> -->

<div class="placeholder">

<img src="/mfds/images/mfds.png"/>

</div>

</div>

<!-- HEADER -->

<div class="header">

<div class="trust">

Universal Educational and Charitable Trust

</div>

<div class="school">

MAAZ FOUNDATION<br>

DANISH SCHOOL

</div>

<div class="medium">

Pre-Primary &amp; Higher Primary<br>

English Medium

</div>

</div>

<!-- PHOTO -->

<div class="photo">

<!-- Replace with student's image -->
<!-- <img src="student.jpg"> -->

<div class="placeholder">

<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "") %>"  alt="Photo" />

</div>

</div>

<!-- ============================
     STUDENT DETAILS
================================ -->

<div class="details">

    <table class="infoTable">

        <tr>
            <td class="label">Name</td>
            <td class="colon">:</td>
            <td class="value nameValue"><%= request.getSession().getAttribute("studentname" + i + "") %></td>
        </tr>

        <tr>
            <td class="label">F.Name</td>
            <td class="colon">:</td>
            <td class="value"><%= request.getSession().getAttribute("fathersname" + i + "") %></td>
        </tr>

        <tr>
            <td class="label">Cell</td>
            <td class="colon">:</td>
            <td class="value"><%= request.getSession().getAttribute("contactnumber" + i + "") %></td>
        </tr>

        <tr>
            <td class="label addressLabel">Address</td>
            <td class="colon">:</td>
            <td class="value addressValue">
                <%= request.getSession().getAttribute("address" + i + "") %>
            </td>
        </tr>

    </table>

</div>

<!-- ============================
     H.M. SIGNATURE PLACEHOLDER
================================ -->

<div class="hmSign">

    <div class="signatureBox">

        <%
        String className = (String) request.getSession().getAttribute("classsection" + i + "");

        if (className != null) {
            className = className.trim().toUpperCase();

            if (className.equals("MONTESSORY--")
                    || className.equals("L.K.G--")
                    || className.equals("U.K.G--")
                    || className.equals("1ST STD--")
                    || className.equals("2ND STD--")
                    || className.equals("3RD STD--")
                    || className.equals("4TH STD")
                    || className.equals("5TH STD--")) {
        %>

                <img src="/mfds/images/hmsignonetofive.png" alt="HM Signature"/>

        <%
            } else if (className.equals("6TH STD--")
                    || className.equals("7TH STD--")
                    || className.equals("8TH STD--")
                    || className.equals("9TH STD--")
                    || className.equals("10TH STD--")) {
        %>

                <img src="/mfds/images/hmsignature.png" alt="HM Signature"/>

        <%
            }
        }
        %>

    </div>

    <div class="hmText">
        H.M. Sign.
    </div>

</div>
<!-- ============================
      FOOTER TEXT
================================ -->

<div class="footerText">

    Near Masjid-e-Rahman Vakkalgera,
    Kalaburagi - 585104

    <br>

    Office Contact :
     <%
        String classecName = (String) request.getSession().getAttribute("classsection" + i + "");

        if (classecName != null) {
        	classecName = classecName.trim().toUpperCase();

            if (classecName.equals("MONTESSORY--")
                    || classecName.equals("L.K.G--")
                    || classecName.equals("U.K.G--")
                    || classecName.equals("1ST STD--")
                    || classecName.equals("2ND STD--")
                    || classecName.equals("3RD STD--")
                    || classecName.equals("4TH STD")
                    || classecName.equals("5TH STD--")) {
        %>

                9535823748

        <%
            } else if (classecName.equals("6TH STD--")
                    || classecName.equals("7TH STD--")
                    || classecName.equals("8TH STD--")
                    || classecName.equals("9TH STD--")
                    || classecName.equals("10TH STD--")) {
        %>

                9972732188

        <%
            }
        }
        %>

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