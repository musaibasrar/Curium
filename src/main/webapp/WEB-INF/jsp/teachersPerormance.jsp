<%--
    Document   : Subject Master
    Created on : MAY 29, 2019, 01:29:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subject Master</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">
<style type="text/css">
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
	width: auto;
	height: auto;
}

.alignRight {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
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
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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

<!--
.header {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	background-color: #4b6a84;
}

.table {
	background-color: #3399CC;
	text-align: center;
	width: auto;
}

.headerText {
	border-radius: 3px;
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
	vertical-align: text-top;
	text-align: center;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.dataTextInActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: none;
}

.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}

.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>

<script type="text/javascript">
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button().click(function() {
			runEffect();
			return false;
		});
	});
	$(function() {
		$("#entrydate").datepicker({
			changeYear : true,
			changeMonth : true
		});
		$("#anim").change(function() {
			$("#entrydate").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function addSubjects() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/SubjectDetailsProcess/addSubjectMaster";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/SubjectDetailsProcess/deleteMultipleSubjects";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			var subname = document.getElementById('subjectname').value;

			if(subname != "" ){
				addSubjects();
			}else{
				alert('Enter the Subject Name');
			}
			
		});
		$("#effect").show();

	});
	
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         $('#chckHead').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             if(length>0){
                 $('.chcktbl:checked').attr('checked', false);
                 this.checked=false;

             }
             else{
                 if (this.checked == false) {
                     $('.chcktbl:checked').attr('checked', false);
                 }
                 else {
                     $('.chcktbl:not(:checked)').attr('checked', true);
                 }

             }

         });
         $('.chcktbl').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             alert(tdLength);
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
         
         $( "#go" )
         .button()
         

     });
</script>
<script type="text/javascript">
	function searchForTeacherDetail() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/TeachersPerformanceProcess/searchForTeacherDetail";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			searchForTeacherDetail();
		});
		

	});
	</script>

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
	<form id="form1" action="/abc/SubjectDetailsProcess/deleteMultipleSubjects" method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		%>
		

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Teacher Performance</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="10" id="table1" style="display: block">
						<tr>
                            
                            


                                <td style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    
                                    Academic Year&nbsp;&nbsp;&nbsp;&nbsp; 

                                
                                    <label>
                                        <label> <select name="academicyear" id="academicyear"
									style="width: 140px">
										<option selected value="${currentAcademicYear}">${currentAcademicYear}(Current Year)</option>
										<option value="2018/19">2018/19</option>
										<option value="2019/20">2019/20</option>
										<option value="2019/20">2020/21</option>
										<option value="2019/20">2021/22</option>
										<option value="2019/20">2022/23</option>
								</select>

							</label> 
                                    </label>

                                </td>


                            </tr>
                            
						<tr>
						
						<td style="font-weight: bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										Classes&nbsp;&nbsp;&nbsp;
										</td>
						</tr>
										
										<c:set var="myList" value="<%= new java.util.ArrayList() %>" />

										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<c:set var="dummy" value="${myList.add(classdetailslist.section)}" />
											<%-- <c:set target="${myList}" property="add" value="${classdetailslist.section}" /> --%>
										</c:if>
										</c:forEach>
										
								<c:forEach items="${classdetailslist}" var="classdetailslist"><tr><td style="font-weight: bold;">
										<c:if test="${(classdetailslist.classdetails != '')}">
										
										<c:if test="${empty myList}">
											   <input type="checkbox"  name="classesselected" value="${classdetailslist.classdetails}--">
										${classdetailslist.classdetails}
											</c:if>
											<c:if test="${not empty myList}">
											<c:forEach var="item" items="${myList}"><label>
										    	<input type="checkbox"  name="classesselected" value="${classdetailslist.classdetails}--${item}">
										${classdetailslist.classdetails} <c:out value="${item}"/></label>
										</c:forEach>
											</c:if>		
										</c:if>	
										<br/></td>
						</tr>
							</c:forEach>
						</td>
						</tr>
						
						<tr><td><br></td></tr>
						<tr>
							<td style="font-weight: bold;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Subject Name &nbsp;&nbsp;&nbsp;
							<label> <select name="subject" id="subject"
									style="width: 240px">
										<option selected></option>

										<c:forEach items="${listSubjectNames}" var="listSubject">

											<option value="${listSubject.subjectid}--${listSubject.subjectname}">
												<c:out value="${listSubject.subjectname}" />
											</option>


										</c:forEach>

								</select>

							</label></td>
						</tr>
<tr>

							
							<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>			
							
							<tr>
							<td><br /></td>
						</tr>
					</table>
				<!-- 	<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="save">Save</button>
							</td>
						</tr>
					</table>-->
				</div>
			</div>
		</div>

			</form>

</body>
</html>
