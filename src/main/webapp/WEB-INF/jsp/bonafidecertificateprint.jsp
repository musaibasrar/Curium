<%-- 
    Document   : bonafide certificate
    Created on : Mar 17 2018, 12:32 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bonafide Certificate</title>
<style>
@page{size:A4;margin:8mm;}
body{
    font-family:"Times New Roman",serif;
    background:#f5f5f5;
}
.page{
    width:190mm;
    min-height:277mm;
    margin:auto;
    background:#fff;
    border:1px solid #777;
    padding:10px;
    box-sizing:border-box;
    position:relative;
}
.inner{
    border:1px solid #777;
    min-height:255mm;
    padding:12px;
    position:relative;
}
.header table,.content table{
    width:100%;
    border-collapse:collapse;
}
td{
    vertical-align:top;
    font-size:22px;
}
.center{
    text-align:center;
}
.title{
    font-size:28px;
    font-weight:700;
    font-style:italic;
    text-decoration:underline;
    margin:10px 0 15px;
}
.line{
    display:inline-block;
    border-bottom:1px solid #000;
    min-width:220px;
    height:auto;
    vertical-align:middle;
    text-align:center;
}
.small{
    font-size:16px;
}
.stamp{
    position:absolute;
    right:18px;
    top:18px;
    width:110px;
    height:110px;
    border:1px solid #bbb;
    border-radius:50%;
    display:flex;
    align-items:center;
    justify-content:center;
    color:#999;
    font-style:italic;
    font-size:18px;
}
.boxes{
    border-collapse:collapse;
    display:inline-table;
    vertical-align:middle;
}
.boxes td{
    width:24px;
    height:24px;
    border:1px solid #555;
}
.footer{
    position:absolute;
    bottom:14px;
    left:12px;
    right:12px;
}
.signature{
    float:right;
    text-align:center;
    font-size:18px;
    }
.adharbox{
text-align:center;
}    
</style>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/iqra/UserProcess/sessionTimeOut");
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
</head>
<body>
<div class='page'>
<div class='inner'>
<div class='stamp'>School Stamp</div>
<div class='header'>
<table>
<tr><td><b><i>School Name</i></b></td><td><b>AWAMI HIGH SCHOOL</b></td></tr>
<tr><td><b><i>UDISE Code</i></b></td><td>36160902283</td></tr>
<tr><td><b><i>Mandal & District</i></b></td><td>Zaheerabad, Dist. Sangareddy</td></tr>
<tr><td class='small' colspan='2'>School Recognition Proc. No. : 3452/B3/2019, Dated : 22/11/2019</td></tr>
</table>
</div>
<div class='title center'>BONAFIDE CERTIFICATE</div>
<div class='content'>
<table>
<tr><td colspan='2'>This is to certify that Mr./Ms.
     <span class='line' style='min-width:400px;float: right;'>
      <c:out value="${studentdetailsbonafide.student.name}" /></span></td></tr>
<tr><td colspan='2'>S/o or D/o Sri. 
    <span class='line' style='min-width:230px'><c:out value="${studentdetailsbonafide.fathersname}" />&nbsp;</span> &amp; Smt. 
    <span class='line' style='min-width:230px;float: right;'>&nbsp;<c:out value="${studentdetailsbonafide.mothersname}" />
    </span></td></tr>
<tr><td colspan='2'>bearing admission No. <span class='line' style='min-width:160px'>&nbsp;<c:out value="${studentdetailsbonafide.student.admissionnumber}" /></span> 
is a bonafide student of this institution for the</td></tr>
<tr><td colspan='2'>classes from <span class='line' style='min-width:130px'>&nbsp;
<c:out value="${studentdetailsbonafide.student.classadmittedin}" /></span> to 
<span class='line' style='min-width:130px'><c:out value="${studentdetailsbonafide.student.classstudying}" />
</span> for the academic years from <span class='line' style='min-width:100px;'>&nbsp;
<c:out value="${studentdetailsbonafide.student.yearofadmission}" /></span> to
 <span class='line' style='min-width:120px'>&nbsp;
 <c:out value="${studentdetailsbonafide.student.promotedyear}" /></span>.</td></tr>
<tr><td colspan='2'>His/Her Date of Birth is <span class='line' style='min-width:220px'>
<c:out value="${studentdetailsbonafide.student.dateofbirth}" /></span> (In words:
 <span class='line' style='min-width:120px;float: right;'>&nbsp; </span></td></tr>
<tr><td colspan='2'><span class='line' style='min-width:620px'> ${dobInWords}</span>) as per relevant records of this institution.</td></tr>
<tr><td colspan='2'>His / Her conduct is Satisfactory.</td></tr>
<tr><td colspan='2' style='padding-top:25px;'>His/Her Aadhaar No.: 
<table class='boxes'><tr>
                <c:forEach var="i" begin="0" end="11">
                    <td class="adharbox">
                        ${fn:substring(studentdetailsbonafide.student.disabilitychild, i, i+1)}
                    </td>
                </c:forEach>
            </tr></table></td></tr>
<tr><td colspan='2' style='padding-top:12px;'>PEN No.: <span style='display:inline-block;width:95px'></span>
<table class='boxes'><tr><c:forEach var="i" begin="0" end="11">
                    <td class="adharbox">
                        ${fn:substring(studentdetailsbonafide.student.pen, i, i+1)}
                    </td>
                </c:forEach></tr></table></td></tr>
</table>
</div>
<div class='footer'>
<div style='float:left;'>Date of Issue : <span class='line' style='min-width:300px'></span></div>
<div class='signature'>Headmaster / Principal<br>Signature with Stamp</div>
</div>
</div>
</div>
<div style='text-align:center;margin:15px;' class='no-print'><button onclick='window.print()' style='padding:10px 20px;font-size:16px;cursor:pointer;'>Print</button></div>
<style>@media print{.no-print{display:none;}body{background:#fff;}}</style>
</body>
</html>
