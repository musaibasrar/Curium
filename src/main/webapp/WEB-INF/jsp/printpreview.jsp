<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student ID Card</title>


<style>

 *{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, Helvetica, sans-serif;
}
 
body{
    background:#ececec;
    text-align:left;
}

.card{

    width:210px;
    height:322px;

    background:#fff;

    border-radius:12px;

    overflow:hidden;

    position:relative;

    margin:20px 20;


    border:1px solid #999;
}

/*******************************
            HEADER
********************************/

.header{

    height:88px;

    background:#1f212b;

    position:relative;

    overflow:hidden;
}

/* Gold Shape */

.header:after{

    content:"";

    width:150px;
    height:150px;

    background:#d8a040;

    border-radius:50%;

    position:absolute;

    right:-70px;
    top:-20px;
}

/* White Curve */

.header:before{

    content:"";

    width:260px;
    height:120px;

    background:white;

    border-radius:50%;

    position:absolute;

    left:-40px;
    bottom:-95px;

    transform:rotate(8deg);
}

.school{

    position:absolute;

    width:100%;

    top:8px;

    text-align:center;

    color:#fff;

    font-size:16px;

    font-weight:bold;

    z-index:2;
}

.address{

    position:absolute;

    width:100%;

    top:30px;

    text-align:center;

    color:white;

    font-size:9px;

    font-weight:bold;

    z-index:2;
}

.phone{

    position:absolute;

    width:100%;

    top:47px;

    text-align:center;

    color:#fff;

    font-size:8px;

    z-index:2;
}

/*******************************
          SCHOOL LOGO
********************************/

.logo{

    position:absolute;

    left:8px;

    top:52px;

    width:42px;
    height:42px;

    border-radius:50%;

    background:#fff;

    display:flex;
    justify-content:center;
    align-items:center;

    z-index:5;
}

.logo img{

    width:36px;
}

/*******************************
      STUDENT PHOTO
********************************/

.photo{

    width:90px;
    height:90px;

    border-radius:50%;

    border:4px solid #d8a040;

    overflow:hidden;

   // margin:18px auto 0;
    margin-left:60px;
    position:relative;

    z-index:4;
}

.photo img{

    width:100%;
    height:100%;
    object-fit:cover;
}

/*******************************
         STUDENT NAME
********************************/

.name{

    margin-top:10px;

    text-align:center;

    color:#d94343;

    font-size:16px;

    font-weight:bold;

    text-transform:uppercase;
}

/*******************************
          DETAILS
********************************/

.details{

    margin-top:10px;

    padding:0 10px;

    font-size:8px;

    line-height:18px;
}

.details table{

    width:100%;
}

.details td{

    vertical-align:top;
}

.label{

    width:58px;

    font-weight:bold;
}

.value{

    font-weight:bold;
}

/*******************************
            FOOTER
********************************/

.footer{

    position:absolute;

    bottom:0;

    width:100%;

    height:32px;

    background:#1f212b;

    color:#fff;

    text-align:center;

    line-height:32px;

    font-size:10px;

    font-weight:bold;
}

/*.footer:before{

    content:"";

    position:absolute;

    left:50%;

    transform:translateX(-50%);

    top:-12px;

    width:90px;
    height:25px;

    background:#d8a040;

    border-radius:0 0 80px 80px;
}*/

.print{

    text-align:center;

    margin-top:15px;
}

@media print{

.print{

display:none;

}

body{

background:white;
text-align:left;

}

}


</style>

</head>
 <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/hira/UserProcess/sessionTimeOut");
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
<body  class="bodymargin" >
 <c:set var="iInitial" value="${iInitial}"/>
         <c:set var="limit" value="1"/>
                        
          <c:forEach begin="1" end="${iInitial}">
                        <%!                        
                            int i = 1;
                        %>
			<c:if test="${limit < iInitial}">	
<div class="card">

<div class="header">

<div class="school">
HIRA PUBLIC SCHOOL
</div>

<div class="address">
Arehalli, Belur Taluk, Hassan Dist.
</div>

<div class="phone">
Cell : 8277201635
</div>

</div>

<div class="logo">
 <img src="/hira/images/hira.png" />
</div>

<div class="photo">
<img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "") %>" alt="Photo" />
<img src="images/student.jpg">
</div>

<div class="name">
<%= request.getSession().getAttribute("studentname" + i + "") %>
</div>

<div class="details">

<table>

<tr>
<td class="label">Father</td>
<td>:</td>
<td class="value"><%= request.getSession().getAttribute("fathersname" + i + "") %></td>
</tr>

<tr>
<td class="label">DOB</td>
<td>:</td>
<td class="value"><%= request.getSession().getAttribute("dateofbirth" + i + "") %></td>
</tr>

<tr>
<td class="label">Cell</td>
<td>:</td>
<td class="value"><%= request.getSession().getAttribute("contactnumber" + i + "") %></td>
</tr>

</table>

</div>

<div class="footer">

DISE : 29230426413

</div>

</div>
 </c:if>
   <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach>
                    <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>
<div class="print">
<button onclick="window.print()">Print</button>
</div>

</body>
</html>