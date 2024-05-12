
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Diary</title>
<link rel="stylesheet" href="/alirfan/css/validation/jquery.ketchup.css">
<script src="/alirfan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/alirfan/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="/alirfan/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/alirfan/css/datePicker/demos.css">

 		<link rel="stylesheet" href="/alirfan/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/alirfan/css/graph/jquery.jqplot.css">

        <script type="text/javascript" src="/alirfan/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/sliderAccess.js"></script>
        <script  type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.ui.resizable.js"></script>

        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.effects.slide.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.effects.bounce.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.effects.clip.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.effects.transfer.js"></script>
        <script type="text/javascript" src="/alirfan/js/datePicker/ui/jquery.effects.blind.js"></script>


<style type="text/css">
.myclass {
				font-size: 1.3em;
				border-top-style: solid;
				border-right-style: solid;
				border-bottom-style: solid;
				border-left-style: solid;
				border-top-color: #5d7e9b;
				border-right-color: #5d7e9b;
				border-bottom-color: #5d7e9b;
				border-left-color: #5d7e9b;
				border-top-width: 1px;
				border-right-width: 1px;
				border-bottom-width: 1px;
				border-left-width: 1px;
				width: auto;
				height: 28px;
				color: black;
				text-transform: capitalize;
				border-radius: 4px;
			}

<!--
.divCSS {
	overflow: scroll;
	height: 100%;
	width: 100%;
}

.fiedlSet {
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-width: 1;
	width: 100%;
	color: #000000;
	font-size: 16px;
	font-weight: bold;
	font-variant: normal;
	font-stretch: wider;
	background-color: #e2ebf3;
	border-top-color: #5d7e9b;
	border-right-color: #5d7e9b;
	border-bottom-color: #5d7e9b;
	border-left-color: #5d7e9b;
}

.legendCSS {
	color: #666666;
}

.tableCSS {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0px;
	top: 0px;
}

.textAreaCSS {
	height: auto;
	width: auto;
}

.textField {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #5d7e9b;
	border-right-color: #5d7e9b;
	border-bottom-color: #5d7e9b;
	border-left-color: #5d7e9b;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
}

.alignRight {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignCenter {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: center;
	//vertical-align: middle;
	font-weight: bold;
}

.alignRightHead {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	font-weight: bold;
}

.alignRightMultiple {
	font-family: Tahoma;
	font-size: 11px;
	font-weight: bolder;
	text-align: right;
	vertical-align: middle;
	font-style: normal;
	color: #325F6D;
}

.alignCentreMultiple {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bolder;
	text-align: center;
	vertical-align: middle;
	font-style: normal;
	color: #000000;
}

.autoAdjust {
	height: auto;
	width: auto;
}

.radioSpanCSS {
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	text-align: left;
	vertical-align: middle;
}

.radioCSS {
	background-position: left center;
}

.spanText {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #000000;
}

.emptyFieldSet {
	border-top-color: #FA7676;
	border-right-color: #FA7676;
	border-bottom-color: #FA7676;
	border-left-color: #FA7676;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-width: thin;
	border-right-width: thin;
	border-bottom-width: thin;
	border-left-width: thin;
	background-image: url(images/close.JPG);
	background-repeat: repeat-y;
	background-attachment: scroll;
	background-position: right;
	width: auto;
	height: auto;
	display: inline;
}

.style1 {
	font-family: Tahoma;
	font-size: 14px;
}

.style2 {
	color: #666666;
	font-family: Tahoma;
	font-size: 14px;
}

.style4 {
	font-size: 12px;
	font-family: Tahoma;
	text-align: left;
	vertical-align: middle;
	color: #325f6d;
}
-->
</style>

<script type="text/javascript" src="/alirfan/js/datetimepicker_css.js"></script>



<script type="text/javascript">
	
	    $(function() {
			$("#datepicker1").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker1").datepicker("option", "showAnim", $(this).val());
			});
		});
	
	    $(function() {
			$("#datepicker2").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker2").datepicker("option", "showAnim", $(this).val());
			});
		});
	    $(function() {
			$("#datepicker3").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-1:+2"
			});
			$("#anim").change(function() {
				$("#datepicker3").datepicker("option", "showAnim", $(this).val());
			});
		});
</script>
<script type="text/javascript">
	function addDiary() {
		var form1 = document.getElementById("form1");
		form1.action = "/alirfan/StudentDiaryProcess/addDiary";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#savediary").button().click(function() {
			addDiary();
		});
		//$("#effect").hide();

	});
	
	 var students = [
         <c:forEach varStatus="status" items="${studentList}" var="parent">{
         	value:'<c:out default="0" value="${parent.student.name}" />',
             admissionno:'<c:out default="0" value="${parent.student.admissionnumber}" />',
             regno:'<c:out default="0" value="${parent.student.studentexternalid}" />',
             name:'<c:out default="0" value="${parent.student.name}" />',
             classandsec:'<c:out default="0" value="${parent.student.classstudying}" />',
             id:'<c:out default="0" value="${parent.student.sid}" />',
             fathername:'<c:out default="0" value="${parent.fathersname}" />',
             
         }<c:if test="${!status.last}">,</c:if>
         </c:forEach>
     ];
	 $(function() {
         $( "#studentname").autocomplete({
             source: students,
             minLength: 1,
             change:function(event,ui){
                 $( "#studentId").val( ui.item.id );
                 
                 
             },
             focus: function( event, ui ) {
                 $( "#studentId").val( ui.item.id );
                 return true;
             },
             select: function( event, ui ) {
                 $( "#studentId").val( ui.item.id );
    			  $( "#studentName").val( ui.item.name );
    			$( "#classandsec").val( ui.item.classandsec );
    			$( "#admissionno").val( ui.item.admissionno );
                 /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                 return true;
             }
         }).data( "autocomplete" )._renderItem = function( ul, item ) {
             return $( "<li></li>" )
             .data( "item.autocomplete", item )
             .append( "<a><b> " + item.value +" / "+item.classandsec+" / "+item.regno+" / "+item.fathername+" </b> </a>" )
             .appendTo( ul );
         };
         var addFeesButtonID="#addFees";
         var removeDossageButtonID="#removeDossage";
         $( addFeesButtonID )
         .button({
             icons: {
                 primary: "ui-icon-search"
             }
         })
         .click(function() {
         	 getstampfees();
              //addRow();
             return false;
         });
         $(removeDossageButtonID)
         .button({
             icons: {
                 primary: "ui-icon-minus"
             }
         })
         .click(function() {
             deleteRow('dataTable');
             return false;
         });            

     });
	</script>
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/alirfan/UserProcess/sessionTimeOut");
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
	<form method="post"  id="form1">
	<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Diary</a></li>
				<!-- 	<li><a href="#tabs-2">Staff</a></li>
					<li><a href="#tabs-3">Selected Numbers</a></li>-->
				</ul>



				<div id="tabs-1">
					<table  border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
                    		<td style="font-weight: bold">Student Name: &nbsp;&nbsp;&nbsp;&nbsp; </td>
                    		<td style="font-weight: bold"><input  type="text" name="studentname" id="studentname" class="myclass" /> <input name="studentId" type="hidden" id="studentId" value="" /> 
                    		<input name="classandsec" type="hidden" id="classandsec" value="" /></td>
                    	</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
						
						<td style="font-weight: bold">Subject &nbsp;</td>
							<td style="font-weight: bold"><label> <input  name="subject" class="myclass"
											type="text" id="subject" 
											/>
							</label></td>
							
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td style="font-weight: bold">Message* &nbsp;</td>
							<td style="font-weight: bold"><label> <textarea  name="messagebody"
											type="text" class="myclass" id="messagebody" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>	<td style="font-weight: bold">Create Date &nbsp;</td>
							<td style="font-weight: bold"><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker3" size="36"
									data-validate="validate(required)">
							</label></td>	
						</tr>

						<tr>
							<td><br /></td>
						</tr>

					</table>
						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								<tr>
									<td align="center">

										<button id="savediary">Save</button>

										
									</td>


								</tr>
							</table>

						</div>
						</div>
						</div>
						</div>
						</form>
						
</body>
</html>


