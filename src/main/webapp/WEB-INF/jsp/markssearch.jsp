<%--
    Document   : Marks Search
    Created on : SEP 23, 2017, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Marks Search</title>
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

.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.alignSearch {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
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

.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
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

<script type="text/javascript"
	src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>

<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchForMarks() {
		
		var examName = document.getElementById("exam").options[document.getElementById("exam").selectedIndex].text;
		var subName = document.getElementById("subject").options[document.getElementById("subject").selectedIndex].text;
		document.getElementById('subjectselected').value = subName;
		document.getElementById('examselected').value = examName;
		var form1 = document.getElementById("form1");
		form1.action = "/abc/MarksDetailsProcess/viewMarks";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			searchForMarks();
		});
		

	});

	$(function() {

		$("#tabs").tabs();

		$("#save").button().click(function() {
			addDepartment();
		});
		/* $("#effect").hide(); */

	});
	
	$(function() {
		$("#updateMarks").button({
			icons : {
				primary : "ui-icon-trash"
			}
		});
		$("#delete").button({
            icons:{
                primary: "ui-icon-trash"
            }
        }).click(function(){
        	if(confirm('Are you sure,you want to delete?')){
        		deleteRecords();	
        	}
            
            return false;

        });
		
		$('#chckHead').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			if (length > 0) {
				$('.chcktbl:checked').attr('checked', false);
				this.checked = false;

			} else {
				if (this.checked == false) {
					$('.chcktbl:checked').attr('checked', false);
				} else {
					$('.chcktbl:not(:checked)').attr('checked', true);
				}

			}

		});
		$('.chcktbl').click(function() {
			var length = $('.chcktbl:checked').length;
			var trLength = $('.trClass').length;
			alert(tdLength);
			if (length > trLength) {

				$('.chcktbl:not(:checked)').attr('disabled', true);
			} else {
				$('.chcktbl:not(:checked)').attr('disabled', false);
			}
		});

		$("#go").button()

	});
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/MarksDetailsProcess/deleteMultiple";
		form1.method = "POST";
		form1.submit();

	}
</script>


<script type="text/javascript">
    
    function checkMandatory(){
    	
    	if(document.getElementById("exam").value == ""){
    		alert('Please enter the exam field');	
    	}else if(document.getElementById("subject").value == ""){
    		alert('Please enter the subject');
    	}
    	
    }
    
function checkMandatoryandSubmit(){
    	
			var checkBox = document.getElementsByName("studentIDs");
			var resultCheckBox=true;			
			for(var i=0; i<checkBox.length; i++){
				if(checkBox[i].checked){
					resultCheckBox=false;
				}
			}
			
    	if(document.getElementById("exam").value == ""){
    		
    		alert('Please enter the exam field');	
    	}else if(document.getElementById("subject").value == ""){
    		alert('Please enter the subject');
    	}else if(resultCheckBox){
    		alert('Select the student(s) to update the marks');
    	}else{
    		var form1 = document.getElementById("form1");
    		form1.action = "/abc/MarksDetailsProcess/addMarks";
    		form1.method = "POST";
    		form1.submit();

    	}
    	
    }
    
   
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
	<form id="form1" action="/abc/MarksDetailsProcess/updateMarks" method="POST">
		<!-- <div style="height: 28px">
			<button id="add">Add Department</button>
			<br />
		</div> -->

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Update Marks</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td class="alignRightFields">Name &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="namesearch" type="text" class="myclass" id="namesearch"
									size="36"">
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>


						<tr>
							<td class="alignRightFields">Class &nbsp;</td>
							<td width="70%"><label> 
												<select name="classsearch"
									id="classsearch" style="width: 120px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
										</select></label>
										
										 <label> 
									<select name="secsearch" id="secsearch"
									style="width: 110px;">
										<option selected></option>

										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label>
						</tr>


				<tr>
							<td><br /></td>
						</tr>

<tr>
						<td class="alignRightFields">Subject &nbsp;</td>
							<td width="16%" height="30" class="alignLeft"><label>
									<select name="subject" id="subject"
									style="width: 240px" ">
										<option selected></option>

										<c:forEach items="${listSubject}" var="listSubject">

											<option value="${listSubject.subjectid}">
												<c:out value="${listSubject.subjectname}" />
											</option>


										</c:forEach>

								</select></td>
						
						</tr>						
						
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
						<td class="alignRightFields">Exam &nbsp;</td>
							<td width="16%" height="30" class="alignLeft"><label>
									<select name="exam" id="exam"
									style="width: 240px">
										<option selected></option>

										<c:forEach items="${listExam}" var="listExam">

											<option value="${listExam.exid}:${listExam.examname}">
												<c:out value="${listExam.examname}" />
											</option>


										</c:forEach>

								</select></td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						
						
						
						
						
						<tr>
							<!-- <td class="alignRightFields">Subject &nbsp;</td> -->
							<td width="12%" align="left"><label> <input
									name="subjectidselected" type="hidden" class="myclass" id="subjectidselected"
									size="36" value='<c:out value="${subjectid}"></c:out>'>
							</label></td>
							
						</tr>
						
						<tr>
							<!-- <td class="alignRightFields">Exam &nbsp;</td> -->
							<td width="12%" align="left"><label> <input
									name="examidselected" type="hidden" class="myclass" id="examidselected"
									size="36" value='<c:out value="${examid}"></c:out>'>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr><tr>
							<td><br /></td>
						</tr>
						
						<tr>

							<td width="30%" class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="search">Search</button>
							</td>
						</tr>
						
						
						<tr>
							<td><br /></td>

						</tr>
						
					
						<tr>
							<td><br /></td>
						</tr>
						
						
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						
						<tr>
							<td><br /></td>
						</tr>
					</table>
					
					<table>
					<tr>
							<td class="alignRightFields">Subject &nbsp;</td>
							
							
							<td ><label> <input style="border: none;
border-color: transparent;background-color:#E6EEF4;font-size: 18px;font-weight:bold;font-variant: small-caps;color: #EB6000;"
									readonly="readonly"  name="subjectselected" type="text" class="myclass" id="subjectselected"
									size="36" value='<c:out value="${subjectselected}"></c:out>'>
							</label></td>
							
							
							<td class="alignRightFields">Exam &nbsp;&nbsp;</td>
							<td ><label> <input style="border: none;
border-color: transparent;background-color:#E6EEF4;font-size: 15px;font-weight:bold;font-variant: small-caps;color: #EB6000;"
									name="examselected" type="text" class="myclass" id="examselected"
									size="36" value='<c:out value="${examselected}"></c:out>'>
							</label></td>
							
						</tr>
					</table>

				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Search result</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText" style="display: none;"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Class</th>
						<th title="click to sort" class="headerText">Marks</th>



					</tr>
				</thead>

				<tbody>
					<c:forEach items="${newStudentList}" var="Parents" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="display: none;"><input type="checkbox"
								id="<c:out value="${Parents.student.sid}"/>" class="chcktbl" checked
								name="studentIDs"
								value="<c:out value="${Parents.student.sid}"/>" /></td>
								<td class="dataTextInActive"><a class="dataTextInActive"
								><c:out
										value="${Parents.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${Parents.student.name}" /></td>
							<td class="dataText"><c:out value="${Parents.student.classstudying}" /></td>
							<td class="dataText">
									<c:if test="${newMarksDetails[status.index].marksobtained <= 100}">
											<c:set var="marksobtained" value="${newMarksDetails[status.index].marksobtained}" />
									</c:if>
									<c:if test="${newMarksDetails[status.index].marksobtained > 100}">
											<c:set var="marksobtained" value="A" />
									</c:if>
							<input type="text" id="studentMarks" name="studentMarks" value="<c:out value="${marksobtained}" />"
								onkeypress="return (event.charCode >= 00 && event.charCode <=57) || event.charCode == 65"
								maxlength="4"
							><%-- <input type="text"
								id="studentMarks" 
								name="studentMarks"
								onkeyup="checkMandatory();"
								onkeypress="return event.charCode >= 00 && event.charCode <=57"
								maxlength="3"
								 /> --%>
								 <input type="hidden" id="marksid" name="marksid" value="<c:out value="${newMarksDetails[status.index].marksid}" />">
								 
								 </td>


						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
					
						<td class="footerTD" colspan="2"><input value="Update"
							type="submit" id="updateMarks" />
							<!-- &nbsp; &nbsp; &nbsp; &nbsp;<button id="delete">Delete</button> -->
							<!-- <input value="Delete Stamp Fees"
							type="submit" id="deleteStamp" /> --></td>
							

					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
