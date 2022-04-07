<%--
    Document   : Students Report Center
    Created on : JUL 27, 2018, 12:59:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Students Reports Center</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
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
	text-align: right;
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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

<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>


<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
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

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function searchForStudents() {
		
		var examLevel = document.getElementById("examlevel").value;
		var language = document.getElementById("languageopted").value;
		var qualification = document.getElementById("qualification").value;
		
		if(examLevel!="" || language!="" || qualification!=""){
			var form1 = document.getElementById("form1");
			form1.action = "Controller?process=StudentProcess&action=searchStudentsCenter";
			form1.method = "POST";
			form1.submit();
		}else{
			alert('Enter atleast one filter criteria');
			var form1 = document.getElementById("form1");
			form1.action = "Controller?process=StudentProcess&action=studentsListReportCenter";
			form1.method = "POST";
			form1.submit();
		}
	}

	function printStudentReport() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=printStudentReport";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#search").button().click(function() {
			searchForStudents();
		});
		

	});

	$(function() {

		$("#tabs").tabs();
		
		 $("#effect").hide();
		 
		 $("#printstudentsreport").button({
				icons : {
					primary : "ui-icon-print"
				}
			}).click(function() {
				printStudentReport();
			});
		 
		 $("#export").button({
				icons : {
					primary : "ui-icon-extlink"
				}
			});

	});
	
	$(function() {
		
		
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
	
	
</script>


<script type="text/javascript">
        
      
        $(document).ready(function() {
            
            
            $("#dataTable").keyup(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesFullAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
                $('#feesTotalAmount').val(sum.toPrecision(6));
                
            });
            $("#dataTable").click(function(){
                
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesFullAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                
            });


        });
    
    function selectAllRow(tableID){
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        if(rowCount==1){
            var row = table.rows[0];
            var chkbox = row.cells[0].childNodes[0];
            chkbox.checked=false;
            alert('No records to select');
        }
        for(var i=1; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            chkbox.checked=true;
        }
    }

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
    
    </script>



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
	<form id="form1" action="Controller?process=StudentProcess&action=exportStudentsReport" method="POST">
		<div style="height: 28px">
			<button id="add">Students Details Report</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Apply Filters</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
								<tr>
								<td class="alignRightFields" >Filters<br></td>
								</tr>
								<tr>
								<td class="alignRightFields"><br></td>
								</tr>
						<%-- <tr>
							<td class="alignRightFields" >Center&nbsp;&nbsp;&nbsp;</td>
							<td width="12%" align="left"><label> <select name="centercode" id="centercode"
									style="width: 240px;">
										<option selected></option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode}:${branchlist.centername}" >
												<c:out value="${branchlist.centercode} -- ${branchlist.centername}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>


						<tr>
							<td class="alignRightFields">District &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="districtcode" id="districtcode"
									style="width: 240px;" onchange="getAdmNo()">
										<option selected></option>
										<c:forEach items="${districtsList}" var="districtsList">
											<option value="${districtsList.districtcode}" >
												<c:out value="${districtsList.districtcode} -- ${districtsList.districtname}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr> --%>
						
						<tr>
							<td class="alignRightFields">Exam Level &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="examlevel" id="examlevel"
									style="width: 240px;">
										<option selected></option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Language &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="languageopted" id="languageopted"
									style="width: 240px;">
										<option selected></option>
										<c:forEach items="${languageslist}" var="languageslist">
											<option value="${languageslist.language}" >
												<c:out value="${languageslist.language}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Qualification &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="qualification" id="qualification"
									style="width: 240px;">
										<option selected></option>
										<c:forEach items="${qualificationlist}" var="qualificationlist">
											<option value="${qualificationlist.qualification}" >
												<c:out value="${qualificationlist.qualification}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Religion &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="religion" id="religion"
									style="width: 240px;">
										<option selected></option>
											<option value="ISLAM" >Islam</option>
											<option value="OTHERS" >Others</option>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields">Batch &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="examyear" id="examyear"
									style="width: 240px;">
											<option ></option>
											<option value="${currentAcademicYear}">${currentAcademicYear} {Current Academic Year}</option>
											<option value="2020/21" >2020/21</option>
											<option value="2019/20" >2019/20</option>
											<option value="2018/19" >2018/19</option>
								</select>
							</label> 
						</tr>
						
						<tr>
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

					</table>
					
					

				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Students Details Report</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<!-- <th class="headerText"><input type="checkbox" id="chckHead" /></th> -->
						<th title="click to sort" class="headerText">Registration No.</th>
						<th title="click to sort" class="headerText">Student Name</th>
						<th title="click to sort" class="headerText">Father/Guardian/Husband Name</th>
						<th title="click to sort" class="headerText">Gender</th>
						<th title="click to sort" class="headerText">Exam Level</th>
						<th title="click to sort" class="headerText">Language</th>
						<th title="click to sort" class="headerText">Center Name</th>
			

					</tr>
				</thead>

				<tbody>
					<c:forEach items="${mapstudentreports}" var="Parents">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<%-- <td class="dataText"><input type="checkbox"
								id="<c:out value="${Parents.key.student.sid}"/>" class="chcktbl"
								name="studentIDs"
								value="<c:out value="${Parents.key.student.sid}"/>" /></td> --%>
							<td class="dataTextInActive"><c:out value="${Parents.key.student.admissionnumber}" /></td>
							<td class="dataText"><c:out value="${Parents.key.student.name}" /></td>
							<td class="dataText">
								<c:if test="${(Parents.key.mothersname != '')}">
								<c:out value="${Parents.key.mothersname}" />
								</c:if>
								<c:if test="${(Parents.key.fathersname != '')}">
								<c:out value="${Parents.key.fathersname}" />
								</c:if>
								<c:if test="${(Parents.key.student.guardiandetails != '')}">
								<c:out value="${Parents.key.student.guardiandetails}" />
								</c:if>
							</td>
							<td class="dataText"><c:out	value="${Parents.key.student.gender}" /></td>
							<td class="dataText"><c:out	value="${Parents.key.student.examlevel}" /></td>
							<td class="dataText"><c:out	value="${Parents.key.student.languageopted}" /></td>
							<td class="dataText"><c:out	value="${Parents.value}" /></td>	
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"> 
						<!-- <button id="export" type="submit">Export</button> -->
						  <button id="printstudentsreport">Print</button>
						</td>
					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>