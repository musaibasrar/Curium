<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="java.lang.String"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admission Abstract</title>
<style>
table,tr,td{
border:1px solid black;
border-collapse:collapse;
text-align:center;
font-weight:bold;
}
</style>
<style>
@media print {
  button {
    display: none !important;   /* hides button during print */
  }
}
</style>
</head>
 <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/bba/UserProcess/sessionTimeOut");
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
 <form action="/bba/" method="post" id="form1" class="bodymargin">
 
 <%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
			
		 <c:set var="iInitial" value="${iInitial}"/>
         <c:set var="limit" value="1"/>
                        
          <c:forEach begin="1" end="${endValue}">
                        <%!                        
                            int i = 1;
                        %>
			<c:if test="${limit < iInitial}">	/
<div style="border:2px solid black;padding:5px;">
<table width="100%" style="border:0px;">
<tr style="border:0px;">
<td style="font-size:30px;font-weight:bold;font-family:cladea;text-align:center;border:0px;">
<u>SCHOOL	ABSTRACT</u>
</td>
<td rowspan="2" style="border:0px;"><table height="100px" width="80px;"><tr><td> <img src="data:image;base64,<%= request.getSession().getAttribute("studentpic" + i + "") %>" style="height:100px;width:80px;border: 1px solid black;border-radius: 10px;" alt="Photo" />
</td></tr></table>
</td>
</tr>
<tr style="border:0px;">
<td style="font-size:20px;font-weight:bold;border:0px;">
School Name:-<span style="border-bottom: 2px dashed #000;"> ${branchname}	MANGALGI, TQ CHITGUPPA,	DIST. BIDAR</span>
</td>
</tr>
</table>
<table width="100%">
<tr>
<td>Sl.
NO</td>
<td>Students	Name	&	
Address</td>
<td>Admission	
Year</td>
<td>Date	of	
Birth</td>
<td>Class</td>
<td>SATS	NO</td>
<td>Religion
Caste</td>
<td>Parents
Occupation</td>
<td>Annual
Income</td>
<td>Remark</td>
</tr>
<tr>
<td>1</td>
<td>2</td>
<td>3</td>
<td>4</td>
<td>5</td>
<td>6</td>
<td>7</td>
<td>8</td>
<td>9</td>
<td>10</td>
</tr>
<tr>
<td>
&nbsp;<br>
&nbsp;<br>
&nbsp;<br>
&nbsp;<br>
&nbsp;<br>
1<br>&nbsp;<br>
&nbsp;<br>
&nbsp;<br>
&nbsp;<br>
&nbsp;<br>

</td>
<td><%= request.getSession().getAttribute("studentname" + i + "") %>,&nbsp;&nbsp;
<%= request.getSession().getAttribute("address" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("admissiondate" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("dateofbirth" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("classsection" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("sts" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("religion" + i + "") %>,
<%= request.getSession().getAttribute("caste" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("profession" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("annualincome" + i + "") %>
</td>
<td><%= request.getSession().getAttribute("remark" + i + "") %>
</td>
</tr>
</table>
<table width="100%" style="border:0px;">
<tr style="border:0px;"><td style="border:0px;"><br></td><td style="border:0px;"></td><td style="border:0px;"></td></tr>
<tr style="border:0px;"><td style="border:0px;"><br></td><td style="border:0px;"></td><td style="border:0px;"></td></tr>
<tr style="border:0px;">
<td style="border:0px;">Date:-&nbsp;&nbsp;<fmt:formatDate type="date" value="${now}" pattern="dd-MM-yyyy"/></td>
<td style="border:0px;"></td>
<td style="border:0px;"></td>
</tr>
<tr style="border:0px;"><td style="border:0px;"><br></td><td style="border:0px;"></td><td style="border:0px;"></td></tr>
<tr style="border:0px;">
<td style="border:0px;">Place:	- Mangalgi</td>
<td style="border:0px;"></td>
<td style="border:0px;"></td>
</tr>
<tr style="border:0px;"><td style="border:0px;"><br></td><td style="border:0px;"></td><td style="border:0px;"></td></tr>
<tr style="border:0px;"><td style="border:0px;"><br></td><td style="border:0px;"></td><td style="border:0px;"></td></tr>
<tr style="border:0px;">
<td style="border:0px;"></td>
<td style="border:0px;">Block	Education	Officer</td>
<td style="border:0px;">HEAD	MASTER</td>
</tr>

</table>
</div>
 </c:if>
   <% i = i + 1;%>
                        <c:set var="limit" value="${limit+1}"/>
                        
                    </c:forEach>
                    <% i = 1;%>
                    <c:set var="iInitial" value="1"/>
                        <c:set var="limit" value="1"/>
                         
                            <button  onclick="window.print()"
                                    >Print</button>     
                       
 </form>                        
</body>
</html>