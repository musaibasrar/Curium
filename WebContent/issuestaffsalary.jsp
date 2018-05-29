<%--
    Document   : Issue Staff Salary
    Created on : APR 30, 2018, 09:36:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Issue Staff Salary</title>
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

.labels {
					font-family: Tahoma;
					font-size: 12px;
					font-style: normal;
					text-transform: capitalize;
					color: #325F6D;
					text-align: left;
					vertical-align: middle;
					font-weight: bold;
				}

</style>

<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
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
	src="js/validation/jquery.ketchup.all.min.js"></script>
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
<script src="js/print/jquery.printPage.js" type="text/javascript"></script>
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
  

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	$(function() {

		$("#tabs").tabs();

		 $("#issue").button({
            icons:{
                primary: "ui-icon-check"
            }
        }).click(function(){
        	if (confirm('Are you sure,you want to issue?')) {
				issueStaffSalary();
			}
            return false;

        });
		 
		 $("#cancel").button({
	            icons:{
	                primary: "ui-icon-circle-close"
	            }
	        }).click(function(){
	        	if (confirm('Are you sure,you want to cancel?')) {
					cancelStaffSalary();
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

function issueStaffSalary(){
	var form1 = document.getElementById("form1");
	form1.action = "Controller?process=HrProcess&action=issueProcessedSalary";
	form1.method = "POST";
	form1.submit();

}

function cancelStaffSalary(){
	var form1 = document.getElementById("form1");
	form1.action = "Controller?process=HrProcess&action=cancelStaffSalary";
	form1.method = "POST";
	form1.submit();

}

</script>
        <script type="text/javascript">
             /* $(function() {

                 $("#print").printPage();
             }); */
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
<jsp:useBean id="now" class="java.util.Date" scope="page" />
	<form id="form1" method="POST">
		
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Issue Staff Salary</a></li>
				</ul>
				
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
						<th class="headerText" ><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Name<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Department&nbsp;</th>
							<th title="click to sort" class="headerText">Designation&nbsp;</th>
							<th title="click to sort" class="headerText">Processed Date&nbsp;</th>
							<th title="click to sort" class="headerText">Isssed Date&nbsp;</th>
							<th title="click to sort" class="headerText">Month&nbsp;</th>
							<th title="click to sort" class="headerText">Year&nbsp;</th>
							<th title="click to sort" class="headerText">Net Payment&nbsp;</th>
							<th title="click to sort" class="headerText">Payment Type&nbsp;</th>
							<th title="click to sort" class="headerText">Status&nbsp;</th>
							<th title="click to sort" class="headerText">Print&nbsp;</th>
						</tr>
				</thead>

				<tbody>
					   <c:forEach items="${processsalarydetailslist}" var="processsalarydetailslist">
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                            		
                            		 <c:if test="${processsalarydetailslist.status == 'ISSUED'}">
                    					<td class="dataText" ><input type="checkbox" disabled="disabled" id = "<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>" class = "chcktbl"  name="idprocesssalarydetails"  value="<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>"/></td>
                    					
                					</c:if>
                					<c:if test="${processsalarydetailslist.status == 'PROCESSED'}">
                    					<td class="dataText" ><input type="checkbox" id = "<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>" class = "chcktbl"  name="idprocesssalarydetails"  value="<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>"/></td>
                					</c:if>
                					<c:if test="${processsalarydetailslist.status  == 'CANCELLED'}">
                    					<td class="dataText" ><input type="checkbox" disabled="disabled" id = "<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>" class = "chcktbl"  name="idprocesssalarydetails"  value="<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>"/></td>
                    					
                					</c:if> 
                					
                                <%-- <td class="dataText" ><input type="checkbox" id = "<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>" class = "chcktbl"  name="idprocesssalarydetails"  value="<c:out value="${processsalarydetailslist.idprocesssalarydetails}"/>"/></td> --%>
                                <td  class="dataTextInActive" style="text-transform:uppercase"><c:out value="${processsalarydetailslist.teacher.teachername}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.teacher.department}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.teacher.designation}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.processeddate}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.issueddate}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.month}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.year}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.netpayment}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.paymenttype}"/></td>
                                <td class="dataText"><c:out value="${processsalarydetailslist.status}"/></td>
                                <td class="dataText"><a id="print" href="Controller?process=HrProcess&action=printSalarySlip&salaryid=<c:out value="${processsalarydetailslist.idprocesssalarydetails}" />"><div align="center" align="center"><img  alt="Printsalaryslip" src="images/printer.png" width="30" height="30" /> <div id="" class="noti_bubbleEmpty"></div></div></a></td>
                            </tr>
                        </c:forEach>
				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="issue">Issue</button> 
                    	
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button id="cancel">Cancel</button>
                        </tr></tfoot>
			</table>

		</div>
		

	</form>

</body>
</html>
