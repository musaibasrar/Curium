<%-- 
    Document   : bonafide certificate
    Created on : Mar 17 2018, 12:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style>
        .borderset{
        border:1px solid black;
        border-collapse:collapse;
        }
        span{
    display:inline-block;
    border-bottom:2px solid black;
    padding-bottom:1px;
    width: 200px;
    font-weight: normal;
    text-align:center;
}
        </style>
	<script type="text/javascript" src="/littleangel/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/littleangel/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <title>Study Certificate</title>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/littleangel/UserProcess/sessionTimeOut");
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
<body style="font-size:20px;">
	<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form method="post" class="bodymargin">
	<table align="center" style="text-align:center;">
<tr><td>
<h2> STUDY CERTIFICATE</h2></td></tr><tr><td>
<h1 style="font-size:40px;margin:0px;">${branchname}</h1></td></tr><tr><td>
<h2 style="margin:0px;">${branchaddress}</h2></td></tr><tr><td>
<h3 style="text-align:right;">Date <span style= "width:100px;"><input name="dateofcr" type="text" class="textField" style="border: none;border-color: transparent;"
					size="10" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" ></span>    </h3></td></tr>
</table>	
<table align="center"><tr><td><p style="font-size:30px;"><i>This is to certify that Sri / Kum <span style="width:400px"><c:out value="${studentdetailsbonafide.student.name}" /></span>    <br>
S/o/ D/o<span style="width:400px"><c:out value="${studentdetailsbonafide.fathersname}" /></span>     has studied 
<br>from <span><c:out value="${studentdetailsbonafide.student.classstudying}" /></span>      Standard to <span><c:out value="${studentdetailsbonafide.student.classadmittedin}" /></span>     Standard in  our  <br>
institution from <span><c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span>     to <span><c:out value="${studentdetailsbonafide.student.promotedyear}" /></span>     <br>academic  years.<br>
The mother tongue of the candidate is <span><c:out value="${studentdetailsbonafide.student.mothertongue}" /></span>     and his /her <br>
admission number is <span><c:out value="${studentdetailsbonafide.student.admissionnumber}" /></span>     as per the Admission <br>register of the institution.
<br>The above details are true and correct to the best of my knowledge.</i></p></td></tr></table>
<table align="center">
<tr>
<td><h3>Institution Seal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h3></td>
<td><h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Signature of <br>Head of the Institution</h3></td>
</tr>
<tr><td></td><td><h3>Name in Block Letters<span></span>    <br>
Mobile number:</h3></td></tr>
</table>
<table width=100% style="border:2px solid black"></table>
<table align="center" style="text-align:center;">
<tr>
<td><h2>COUNTER SIGNED BY ME</h2></td>
</tr>
<tr>
<td><h2>Address, Seal & Office Telephone number </h2></td>
</tr>
<tr>
<td><h2>of the Block Educational Officer / DDPI.</h2></td>
</tr>
<tr>
<td><h2>Mobile Number: </h2></td>
</tr>
<tr>
              <td align="center"><a id="print" href="/littleangel/DocumentsProcess/printStudyCertificateCounselling">Print</a></td>
            </tr>
</table>
	</form>
</body>
</html>

