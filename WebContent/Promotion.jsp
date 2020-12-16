<%--
    Document   : Promotion
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Promotion</title>
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
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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
		$("#medicineId").val(id)
		$("#medicineName").val(name);

	}
	function getCurrentDate() {
		var today = new Date();
		var day = today.getDate();
		var month = today.getMonth() + 1;
		var year = today.getFullYear();
		if (month < 10) {
			month = "0" + month;

		} else {
			month = month;
		}
		if (day < 10) {
			day = "0" + day;

		} else {
			day = day;
		}
		return month + "/" + day + "/" + year;

	}
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
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function search() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=searchForPromotion";
		form1.method = "POST";
		form1.submit();

	}
	function promoteClass() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=promoteClass";
		form1.method = "POST";
		form1.submit();

	}
	function demoteClass() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=demoteClass";
		form1.method = "POST";
		form1.submit();

	}
	function graduated() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=graduated";
		form1.method = "POST";
		form1.submit();

	}
	function dropped() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=dropped";
		form1.method = "POST";
		form1.submit();

	}
	
	function selectForExamLevel(){
		var selected=document.getElementById('examlevelcode').value;
		document.getElementById('forexamlevel').value = selected;
		
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#search").button().click(function() {
			search();
		});
		$("#effect").hide();

	});
	
	 $(function(){
         $("#promote").button({
             icons:{
                 primary: "ui-icon-arrowreturnthick-1-n"
             }
         }).click(function(){
        			 promoteClass();
         });
         $("#demote").button({
             icons:{
                 primary: "ui-icon-arrowreturnthick-1-s"
             }
         }).click(function(){
        			 demoteClass();
         });
         $("#dropped").button({
             icons:{
                 primary: "ui-icon-triangle-1-s"
             }
         }).click(function(){
        	 dropped();
         });
         
         $("#graduated").button({
             icons:{
                 primary: "ui-icon-star"
             }
         }).click(function(){
        	 graduated();
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
	<form id="form1" method="POST">
		<div style="height: 28px">
			<button id="add">Search</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Criteria</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						
						<tr>
							<td class="alignRight" >Center&nbsp;&nbsp;&nbsp;</td>
							<td width="12%" align="left"><label> <select name="centercode" id="centercode" required
									style="width: 240px;">
										<option selected>${resultservicecentersearch}</option>
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
							<td width="10%" class="alignRight">Examination Level&nbsp;</td>
							<td width="70%"><label> <select name="examlevelcode" id="examlevelcode" onchange="selectForExamLevel()"
									style="width: 240px;" required>
										<option selected>${resultserviceexamlevelsearch}</option>
										<c:forEach items="${examleveldetails}" var="examleveldetails">
											<option value="${examleveldetails.levelcode}:${examleveldetails.idexamlevel}" >
												<c:out value="${examleveldetails.levelcode} -- ${examleveldetails.levelname}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Language &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="languageopted" id="languageopted"
									style="width: 240px;">
										<option selected>${resultservicelanguagesearch}</option>
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
						
						<tr style="display: none;">
							<td width="10%" class="alignRight">Examination Level&nbsp;</td>
							<td width="70%"><label> <input type="text" name="forexamlevel" id="forexamlevel" value="${resultserviceexamlevelsearch}">
							</label></td>
						</tr>
						
						<tr>
							<td class="alignRight">Exam Year &nbsp;&nbsp;&nbsp;</td>
							<td width="70%"><label> 
										<select name="examyear" id="examyear"
									style="width: 240px;" required>
											<option selected value="${resultexamyear}">${resultexamyear}</option>
											<option ></option>
											<option value="${currentAcademicYear}">${currentAcademicYear} {Current Academic Year}</option>
											<option value="2013/14" >2013/14</option>
											<option value="2014/15" >2014/15</option>
											<option value="2015/16" >2015/16</option>
											<option value="2016/17" >2016/17</option>
											<option value="2017/18" >2017/18</option>
											<option value="2018/19" >2018/19</option>
											<option value="2019/20" >2019/20</option>
											<option value="2020/21" >2020/21</option>
											<option value="2020/21" >2021/22</option>
											<option value="2020/21" >2022/23</option>
								</select>
							</label> 
						</tr>

						<tr>
							<td><br /></td>

						</tr>
						
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="search">Search</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">View All Students</td>
					
				</tr>
			</table>
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr  >
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">Admission Number</th>
                            <th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText">Exam Level&nbsp;</th>
                            <th title="click to sort" class="headerText">Qualification&nbsp;</th>
                            <th title="click to sort" class="headerText">Center Code&nbsp;</th>
                            <th title="click to sort" class="headerText">%&nbsp;</th>
                            <th title="click to sort" class="headerText">Result&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${resultlist}" var="resultlist">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${resultlist.student.sid}"/>" class = "chcktbl"  name="studentIDs"  value="<c:out value="${resultlist.student.sid}~${resultlist.student.admissionnumber}"/>"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="Controller?process=StudentProcess&action=ViewDetails&id=<c:out value='${resultlist.student.sid}'/>"><c:out value="${resultlist.student.admissionnumber}"/></a></td>
                                <td class="dataText"><c:out value="${resultlist.student.name}"/></td>
                                <td class="dataText"><c:out value="${resultlist.student.examlevel}"/></td>
                                <td class="dataText"><c:out value="${resultlist.student.qualification}"/></td>
                                <td class="dataText"><c:out value="${resultlist.student.centercode}"/></td>
                                <td class="dataText">
                                	<fmt:formatNumber type = "number" 
         								pattern = "0.#" value = "${resultlist.percentage}" />%
                                </td>
                                <td class="dataText"><c:out value="${resultlist.resultclass}" /></td>
                                 <input type="hidden" id="classstudying" name="classstudying" value="${resultlist.student.examlevel}"/>

                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button value="Promote" id="promote">Promote</button>
                             &nbsp;&nbsp;&nbsp;&nbsp;<button id="demote">Demote</button> 
                             &nbsp;&nbsp;&nbsp;&nbsp;<button id="graduated">Graduated</button> 
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="dropped">Dropped Out</button>
                            </td>
                    
                        </tr></tfoot>
                </table>

		</div>


	</form>

</body>
</html>
