<%--
    Document   : Rank List Report
    Created on : AUG 09, 2018, 11:14:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rank List Report</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">

  <script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        
        
         <script  type="text/javascript" src="js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="js/datePicker/ui/jquery.ui.resizable.js"></script>
        
        
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

	$(function() {

		$("#apply").button().click(function() {
			searchRankListReport();
		});
		
	});
	
	function searchRankListReport() {
		var form1 = document.getElementById("form1");
		if(form1.checkValidity()) {
			form1.action = "Controller?process=StudentProcess&action=searchRankListReport";
			form1.method = "POST";
			form1.submit();
		  }
	}
	
	$(function() {

		$("#tabs").tabs();

		$("#effect").hide();
		
		 $("#printranklistreport").button({
				icons : {
					primary : "ui-icon-print"
				}
			})
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
	

    $(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button({
			icons : {
				primary : "ui-icon-arrowthick-1-s"
			}
		}).click(function() {
			runEffect();
			return false;
		});
	});
	
    function emptycenter(){
    	
    	document.getElementById("centercode").value='';
    }
    
    function emptystate(){
    	document.getElementById("state").value='';
    }

	
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
	<form id="form1" method="POST">
		
		<div style="height: 28px">
		
			<button id="add">Apply Filters</button>
			<br />
		</div>
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Filters</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
								
								<tr>
								<td class="alignRightFields"><br></td>
								</tr>
								
								<tr>
							<td class="alignRightFields" >State&nbsp;&nbsp;&nbsp;</td>
							<td width="12%" align="left">
							
											<label> 
												<select name="state" id="state" style="width: 240px;" onchange="emptycenter()">
                                            <option selected>${rankliststatesearch}</option>
                                            <option></option>
                                            <option>Andhra Pradesh</option>
                                            <option>Goa</option>
                                            <option>Karnataka</option>
                                            <option>Maharashtra</option>
                                            <option>Tamil Nadu</option>
                                            <option>Telangana</option>
                                        </select>
									</label>
								</td>
							
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td class="alignRightFields" >Center&nbsp;&nbsp;&nbsp;</td>
							<td width="12%" align="left"><label> <select name="centercode" id="centercode" onchange="emptystate()"
									style="width: 240px;">
										<option selected>${ranklistcentersearch}</option>
										<option></option>
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
							<td class="alignRightFields">Exam Level &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="examlevelcode" id="examlevelcode" required
									style="width: 240px;">
										<option selected>${ranklistexamlevelsearch}</option>
										<option></option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}:${examleveldetails.idexamlevel}" >
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
										<option selected>${ranklistlanguagesearch}</option>
										<option></option>
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
										<option selected>${ranklistqualificationsearch}</option>
										<option></option>
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
						
						<%-- <tr>
							<td class="alignRightFields">Exam Level &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="forexamlevel" id="forexamlevel" required
									style="width: 240px;">
										<option selected>${forranklistexamlevelsearch}</option>
										<option></option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}:${examleveldetails.idexamlevel}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
											</option>
										</c:forEach>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr> --%>
						
						
						<tr>
							<td class="alignRightFields">Exam Year &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="examyear" id="examyear"
									style="width: 240px;" required>
											<option selected value="${ranklistexamyear}">${ranklistexamyear}</option>
											<option ></option>
											<option value="${currentAcademicYear}">${currentAcademicYear} {Current Academic Year}</option>
											<option value="2021/22" >2021/22</option>
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
								<button id="apply">Apply</button>
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
					<td class="headerTD" >Rank List</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th title="click to sort" class="headerText">Admission Number</th>
						<th title="click to sort" class="headerText">Student Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
						<th title="click to sort" class="headerText">Language</th>
						<th title="click to sort" class="headerText">%&nbsp;</th>
						<th title="click to sort" class="headerText">Result</th>
						<th title="click to sort" class="headerText">Rank</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach items="${ranklistreport}" var="resultlist" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataTextInActive"><a class="dataTextInActive"><c:out	value="${resultlist.student.admissionnumber}" /></a></td>
							<td class="dataText"><c:out value="${resultlist.student.name}" /></td>
							<td class="dataText"><c:out value="${resultlist.student.languageopted}" /></td>
							<td class="dataText">
							<fmt:formatNumber type = "number" 
         								pattern = "0.#" value = "${resultlist.percentage}" />%
							</td>
							<td class="dataText"><c:out value="${resultlist.resultclass}" /></td>
							<td class="dataText"><c:out value="${resultlist.rank}"/></td>								
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
					
						<td class="footerTD" colspan="2">
						  <a id="printranklistreport" href="Controller?process=StudentProcess&action=printRankListReport">Print</a>
						</td>
						<%-- <td class="footerTD" colspan="2">
						  <a style="font-weight: bold;color: white;font-size: 14px;">&nbsp;&nbsp;Total: ${totalstudentresult}&nbsp;&nbsp;Distinction: ${distinctioncount}&nbsp;&nbsp;First Class: ${firstcount}
						  &nbsp;&nbsp;Second Class: ${secondcount}&nbsp;&nbsp;Pass: ${passcount}&nbsp;&nbsp;Fail: ${failcount}</a>
						</td> --%>
					</tr>
				</tfoot>
			</table>

		</div>

	</form>
</body>
</html>
