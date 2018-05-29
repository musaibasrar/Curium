<%@page import="de.tudresden.st.cbse.calendar.appointment.Appointment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>CBSE Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />


<link rel='stylesheet' type='text/css' href='resources/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='resources/jquery/jquery-1.7.1.min.js'></script>
<script type='text/javascript' src='resources/jquery/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='resources/fullcalendar/fullcalendar.js'></script>

<script type="text/javascript">


$(document).ready(function() {
var errors= "${errorMessage}";
if(errors==""){
$('#addAppointment').hide();
$('#screenCover').hide();
}

$('#calendar').fullCalendar({
header: {
left: 'prev,next today',
center: 'title',
right: 'month, agendaWeek, agendaDay'
},
firstDay: 1,
weekMode: 'variable',
editable: false,
allDaySlot: false,
allDayDefault: false,

timeFormat: 'H:mm',

events:{
url: 'calendarAjax' ,
}
});

$('#btnNewAppointment').click(function () {
$('#addAppointment').slideDown();
$('#screenCover').show();
});

$('#btnClose').click(function () {
$('#addAppointment').slideUp();
$('#screenCover').hide();
});



});
</script>

<%!
public String getParam(HttpServletRequest request, String param) {
String value = request.getParameter(param);
if (value == null)
return "";

return value;
}

%>

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
<div id="screenCover"></div>
<div id="container">
<h1>appointment</h1>
<div id=navigation>
<a href="#" class="navi"><div id="naviElement" class="hovered">Calendar</div></a>
<a href="logout" class="navi"><div id="naviElement">Logout</div></a>
</div>



<div id="content">
<h2>
Appointments for
<%=session.getAttribute("currentSessionUser")%>
</h2>
<button id="btnNewAppointment" type="button">New Appointment</button>
<div id="legend">
<div id="free" class="legendElement">free</div>
<div id="blocked" class="legendElement">blocked</div>
<div id="p_blocked" class="legendElement">potentially blocked</div>
<div id="away" class="legendElement">away</div>
</div>
<div id="calendar"></div>




<div id="addAppointment">
<form id="appointment" action="appointment" method="POST">
<h2>new Appointment</h2>

<font color="red">
${errorMessage}
</font>

<table>

<tr>
<td><label for="title">Title:</label></td>
<td><input type="text" id=title name="title" value="<%= getParam(request, "title") %>" /></td>
</tr>
<tr>
<td><label for="start">Start:</label></td>
<td><input id=start name="start" value="<%= getParam(request, "start") %>" /></td>
</tr>
<tr>
<td><label for="end">End:</label></td>
<td><input id=end name="end" value="<%= getParam(request, "end") %>" /></td>
</tr>
<tr>
<td><label for="note">Note:</label></td>
<td><textarea id=note name="note"><%= getParam(request, "note") %></textarea></td>
</tr>
<tr>
<td><label for="share">Share:</label></td>
<td><input type="text" id=share name="share" value="<%= getParam(request, "share") %>" /></td>
</tr>
<tr>
<td><label for="private">Private:</label></td>
<td><input type="checkbox" id=private name="private"/></td>
</tr>
<tr>
<td><label for="type">Type:</label></td>
<td><input type="radio" id=type_free name="type" value="FREE" checked="checked"/>Free<br />
<input type="radio" id=type_away name="type" value="AWAY" />Away<br />
<input type="radio" id=type_blocked name="type" value="BLOCKED" />Blocked<br />
<input type="radio" id=type_potentiallyblocked name="type" value="POTENTIALLY_BLOCKED" />Potentially Blocked<br /></td>
</tr>
</table>
<p>
<input id="appointment" type="submit" value="Create" />
<button id="btnClose" type="button">Close</button>
</p>
</form>
</div>

</div>
<footer> TU Dresden, CBSE, SS2012, Christopher Bellmann und Stanley Förster</footer>

</div>
</body>
</html><%@page import="de.tudresden.st.cbse.calendar.appointment.Appointment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>CBSE Calendar</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="resources/css/screen.css" />


<link rel='stylesheet' type='text/css' href='resources/fullcalendar/fullcalendar.css' />
<script type='text/javascript' src='resources/jquery/jquery-1.7.1.min.js'></script>
<script type='text/javascript' src='resources/jquery/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='resources/fullcalendar/fullcalendar.js'></script>

<script type="text/javascript">


$(document).ready(function() {
var errors= "${errorMessage}";
if(errors==""){
$('#addAppointment').hide();
$('#screenCover').hide();
}

$('#calendar').fullCalendar({
header: {
left: 'prev,next today',
center: 'title',
right: 'month, agendaWeek, agendaDay'
},
firstDay: 1,
weekMode: 'variable',
editable: false,
allDaySlot: false,
allDayDefault: false,

timeFormat: 'H:mm',

events:{
url: 'calendarAjax' ,
}
});

$('#btnNewAppointment').click(function () {
$('#addAppointment').slideDown();
$('#screenCover').show();
});

$('#btnClose').click(function () {
$('#addAppointment').slideUp();
$('#screenCover').hide();
});



});
</script>

<%!
public String getParam(HttpServletRequest request, String param) {
String value = request.getParameter(param);
if (value == null)
return "";

return value;
}

%>

</head>
<body>
<div id="screenCover"></div>
<div id="container">
<h1>appointment</h1>
<div id=navigation>
<a href="#" class="navi"><div id="naviElement" class="hovered">Calendar</div></a>
<a href="logout" class="navi"><div id="naviElement">Logout</div></a>
</div>



<div id="content">
<h2>
Appointments for
<%=session.getAttribute("currentSessionUser")%>
</h2>
<button id="btnNewAppointment" type="button">New Appointment</button>
<div id="legend">
<div id="free" class="legendElement">free</div>
<div id="blocked" class="legendElement">blocked</div>
<div id="p_blocked" class="legendElement">potentially blocked</div>
<div id="away" class="legendElement">away</div>
</div>
<div id="calendar"></div>




<div id="addAppointment">
<form id="appointment" action="appointment" method="POST">
<h2>new Appointment</h2>

<font color="red">
${errorMessage}
</font>

<table>

<tr>
<td><label for="title">Title:</label></td>
<td><input type="text" id=title name="title" value="<%= getParam(request, "title") %>" /></td>
</tr>
<tr>
<td><label for="start">Start:</label></td>
<td><input id=start name="start" value="<%= getParam(request, "start") %>" /></td>
</tr>
<tr>
<td><label for="end">End:</label></td>
<td><input id=end name="end" value="<%= getParam(request, "end") %>" /></td>
</tr>
<tr>
<td><label for="note">Note:</label></td>
<td><textarea id=note name="note"><%= getParam(request, "note") %></textarea></td>
</tr>
<tr>
<td><label for="share">Share:</label></td>
<td><input type="text" id=share name="share" value="<%= getParam(request, "share") %>" /></td>
</tr>
<tr>
<td><label for="private">Private:</label></td>
<td><input type="checkbox" id=private name="private"/></td>
</tr>
<tr>
<td><label for="type">Type:</label></td>
<td><input type="radio" id=type_free name="type" value="FREE" checked="checked"/>Free<br />
<input type="radio" id=type_away name="type" value="AWAY" />Away<br />
<input type="radio" id=type_blocked name="type" value="BLOCKED" />Blocked<br />
<input type="radio" id=type_potentiallyblocked name="type" value="POTENTIALLY_BLOCKED" />Potentially Blocked<br /></td>
</tr>
</table>
<p>
<input id="appointment" type="submit" value="Create" />
<button id="btnClose" type="button">Close</button>
</p>
</form>
</div>

</div>
<footer> TU Dresden, CBSE, SS2012, Christopher Bellmann und Stanley Förster</footer>

</div>
</body>
</html>