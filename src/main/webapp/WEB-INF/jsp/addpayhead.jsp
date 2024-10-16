<%--
    Document   : Add Pay Head
    Created on : APR 22, 2018, 11:01:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Pay Head</title>
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

.amount {
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
.amount {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
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

	function searchForEmployees(staffName, staffDepartment){
		var form1 = document.getElementById("form1");
		form1.action = "/abc/HrProcess/searchEmployeesForPayHead?staffName="+staffName+"&staffDepartment="+staffDepartment+"";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			var staffName = document.getElementById('staffname').value;
			var staffDepartment = document.getElementById('department').value;
			searchForEmployees(staffName, staffDepartment);
		});

		$("#tabs").tabs();

		$("#save").button().click(function() {
			if (confirm('Are you sure,you want to save?')) {
				addPayHeadStaff();
			}
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

	});

	function addPayHeadStaff() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/HrProcess/addPayHeadStaffDetails";
		form1.method = "POST";
		form1.submit();
	}
	

		var xmlHttp;
	    var count;
	    function getPayHead() {

			var selected=document.getElementById('type').value;

			 if (typeof XMLHttpRequest != "undefined") {
				 xmlHttp = new XMLHttpRequest();
	            
	         } else if (window.ActiveXObject) {
	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	             
	         }
			xmlHttp.onreadystatechange = stateChanged;
			xmlHttp.open("GET", "/HrProcess/getPayHead?payHeadType="+selected,true);
			xmlHttp.send(null);
		}
	    
		function stateChanged() {

			if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
				//document.getElementById("mydivmobile").innerHTML = xmlHttp.responseText;
				document.getElementById("payhead").innerHTML = xmlHttp.responseText;
				
				/* count = xmlHttp.responseXML.getElementsByTagName("subgroup")[0];
				var childCount=count.childNodes[0].nodeValue;
				document.getElementById("mydivmobile").innerHTML = childCount;
				document.getElementById("subgroupname").innerHTML = childCount; */
				/* var showdata = xmlHttp.responseText;
				document.getElementById("mydivmobile").innerHTML = showdata; */
			    /* document.getElementById("sgname").style.display = '';
	            document.getElementById("subgroupname").style.display = '';
	            document.getElementById("newsubgr").style.display = "none"; */
			}
		}
		function GetXmlHttpObject() {
			var xmlHttp = null;
			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
			}
			return xmlHttp;
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
	<form id="form1" method="POST">
		
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Add Pay Head</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						</tr>
							<tr>
							<td><br /></td>

						</tr>
						</tr>
							<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td class="alignRightFields">Staff Name &nbsp;</td>
							<td width="12%" align="left"><label  style="font-weight: bold;color:#325F6D"> <input
									name="staffname" type="text" class="myclass" id="staffname"
									size="36">
							</label></td>
							
						</tr>
							<tr>
							<td><br /></td>

						</tr>
						<tr>
						<td class="alignRightFields">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OR </td>
							<td><br /></td>

						</tr>	
						<tr>
							<td><br /></td>

						</tr>					
						<tr>
							<td class="alignRightFields">Department &nbsp;</td>
							<td width="70%"><label> <select name="department" id="department"
									style="width: 240px" ">
										<option selected></option>
										<c:forEach items="${listDepartment}" var="listDepartment">
											<option>
												<c:out value="${listDepartment.departmentname}" />
											</option>
										</c:forEach>
								</select>

							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" >
								<button id="search">Search</button>
							</td>
						</tr>
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>						
						
						<TABLE id="dataTable" width="50%" border="1" >
                <thead>
                    <tr >
                        <td class="headerText">Pay Head Type</td>
                        <td class="headerText">Pay Head Name</td>
                        <td class="headerText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Amount/Percentage&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>                       
                    </tr>
                </thead>
                <tbody>
                		<tr>
                			<td>
                			<label> 
							<select name="type" id="type" style='width: 240px' onchange="getPayHead()">
										<option selected></option>
										<option>Earning</option>
										<option>Deduction</option>
								</select>
							</label>
                			</td>
                			<td id="payhead">
                			<label><select name="payhead"
											id="payhead" style='width: 240px' id="payhead">
												<%-- <option selected></option>
												<c:forEach items='${payheadlist}' var='payheadlist'>
													<option value='${payheadlist.payheadname}'>
														<c:out value='${payheadlist.payheadname}' />
													</option>
												</c:forEach> --%>
							</select></label> 
							</td>
							<td>
								<input type="radio" name="amtper" value="amount" ><label class="amount">Amount</label>
  								<input type="radio" name="amtper" value="percentage"><label class="amount">Percentage</label>
							</td>
									
                		</tr>
                </tbody>
                            </TABLE>
						
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
				</div>
			</div>
		</div>
				<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Staff</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText" style="display:none"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Name<img
							alt=" " style="position: relative; top: 4px;"
							src="/abc/images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Department&nbsp;</th>
							<th title="click to sort" class="headerText">Designation&nbsp;</th>
							<th title="click to sort" class="headerText">Value&nbsp;</th>
						</tr>
				</thead>

				<tbody>
					   <c:forEach items="${employeeList}" var="employee">
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText" style="display:none"><input type="checkbox"  checked id = "<c:out value="${employee.tid}"/>" class = "chcktbl"  name="employeeIDs"  value="<c:out value="${employee.tid}"/>"/></td>
                                <td  class="dataTextInActive" style="text-transform:uppercase"><a class="dataTextInActive" href="/abc/HrProcess/viewLeavesDetails?id=<c:out value='${employee.tid}'/>"><c:out value="${employee.teachername}"/></a></td>
                                <td class="dataText"><c:out value="${employee.department}"/></td>
                                <td class="dataText"><c:out value="${employee.designation}"/></td>
                                <td class="dataText"><input type="text"	id="values" name="values" value="0"
								onkeypress="return event.charCode >= 00 && event.charCode <=57"/></td>
                            </tr>
                        </c:forEach>
				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="save">Save</button> 
                    
                        </tr></tfoot>
			</table>

		</div>
		

	</form>

</body>
</html>
