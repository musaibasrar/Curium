<%--
    Document   : Books Sales Report
    Created on : AUG 14, 2018, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Books Sales Report</title>
<style type="text/css" title="currentStyle">
            @import "css/dataTable/css/demo_page.css";
            @import "css/dataTable/css/jquery.dataTables.css";
        </style>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<link rel="stylesheet" href="css/font-awesome.css">
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
	font-size: 14px;
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
                border-radius:3px;
                width: 10px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 27px;
                vertical-align: middle;
                text-align: center;
            }
            
.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: left;
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

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: white;
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

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
            "sScrollY": "380px",
            "bPaginate": true,
            "bLengthChange": false,
            "bFilter": true,
            "bSort": true,
            "bInfo": true,
            "bStateSave": false,
            "bProcessing": false,
            "bServerSide": false,
            "bAutoWidth": false,
            "iDisplayLength": 2000,
            "aoColumnDefs":[
                { 'bSortable': false, 'aTargets': [ 0 ] }
            ]
            
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
		$("#add").button({
            icons:{
                primary: " ui-icon-arrowthick-1-s"
            }
        }).click(function() {
			runEffect();
			return false;
		});
		
		 $("#printbooks").button({
				icons : {
					primary : "ui-icon-print"
				}
			}).click(function() {
				printBooks();
			});
			
	});
	
</script>
<script type="text/javascript">

	function printBooks() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=printSalesReport";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#save").button({
            icons:{
                primary: "ui-icon-contact"
            }
        }).click(function() {
			addBooks();
		});
		$("#effect").hide();

	});
	
	 $(function(){
         $("#deletebooks").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         $("#update").button({
             icons:{
                 primary: "ui-icon-note"
             }
         }).click(function(){
             updateRecords();
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
	 
	 $(function() {
			$("#transactiondatefrom").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-50:+0"
			});
			$("#anim").change(function() {
				$("#transactiondatefrom").datepicker("option", "showAnim", $(this).val());
			});
			$("#transactiondateto").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-50:+0"
			});
			$("#anim").change(function() {
				$("#transactiondateto").datepicker("option", "showAnim", $(this).val());
			});
			
			 $("#generatereport").button({
	             icons:{
	                 primary: "ui-icon-document"
	             }
	         }).click(function(){
	             generateBooksSalesReport();
	             return false;

	         });
			 
			 $("#print").button({
	             icons:{
	                 primary: "ui-icon-print"
	             }
	         }).click(function(){
	             printRecords();
	             return false;

	         });
			 
			 
		});
	 
	 function generateBooksSalesReport() {
			var form1 = document.getElementById("form1");
			form1.action = "Controller?process=OrderProcess&action=generateBooksSalesReport";
			form1.method = "POST";
			form1.submit();
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
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		%>
		<c:set var="itemTotal" value="${0}" />
		<c:set var="totalQuantity" value="${0}" />
		
		
		
		<div style="height: 28px">
			<button id="add">Search</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Parameters</a></li>

				</ul>
				<div id="tabs-1">
				
					<table style="margin-left: auto;margin-right: auto;">
					
						<tr>
							<td><br><br></td>
						</tr>
						
						<tr>
						<td class="alignRight">From Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondatefrom"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondatefrom" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							<td class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondateto"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondateto" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
							<td class="alignRight">Title&nbsp;</td>
							<td ><label>
									<select name="title" id="title" autocomplete="false"
									style="width: 180px;font-size: 14px;" >
										<option selected></option>
										<c:forEach items="${booksinfolist}" var="booksinfo">
											<option value="${booksinfo.title}">
												<c:out value="${booksinfo.title}" />
											</option>
										</c:forEach>
								</select>
							
							</label></td>
						
							<td  class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Author &nbsp;</td>
							<td ><label> <select name="author" id="author" 
									style="width: 180px;font-size: 14px;" >
										<option selected></option>
										<c:forEach items="${booksinfolist}" var="booksinfo">
											<option value="${booksinfo.author}">
												<c:out value="${booksinfo.author}" />
											</option>
										</c:forEach>
								</select>
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">Language&nbsp;</td>
							<td ><label>
									<select name="language" id="language" 
									style="width: 180px;font-size: 14px;" required>
										<option value="" ></option>
										<option value="Urdu" >Urdu</option>
										<option value="English">English</option>
										<option value="Kannada">Kannada</option>
										<option value="Hindi">Hindi</option>
										<option value="Tamil">Tamil</option>
										<option value="Telegu">Telegu</option>
										<option value="Marathi">Marathi</option>
								</select>
							
							</label></td>
							
							<td  class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Center Code &nbsp;</td>
							<td ><label> <select name="centercode" id="centercode" 
									style="width: 180px;font-size: 14px;" >
										<option selected></option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.centercode} -- ${branchlist.centername}" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">
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
							<td class="alignRight">Status&nbsp;</td>
							<td ><label>
									<select name="status" id="status" 
									style="width: 180px;font-size: 14px;" required>
										<option value="DELIVERED" selected="selected">Delivered</option>
										<option value="" ></option>
										<option value="PENDING">Pending</option>
										<option value="REJECTED">Rejected</option>
								</select>
							
							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					
						<div align="center">
						<p>
						<label><button id="generatereport">Generate Report</button></label></p>
						
									
					</div>
					
					</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Books Sales Report<br>
					${fromdateselected}&nbsp;&nbsp;${todateselected}&nbsp;&nbsp;${titleselected}&nbsp;&nbsp;${authorselected}&nbsp;&nbsp;${languageselected}&nbsp;&nbsp;</td>
				</tr>
			</table>
			
			<table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th title="click to sort" class="headerText">Order Date</th>
                            <th title="click to sort" class="headerText">Center</th>
                            <th title="click to sort" class="headerText">Status</th>
                            <th title="click to sort" class="headerText">Title</th>
                            <th title="click to sort" class="headerText">Author</th>
                            <th title="click to sort" class="headerText">Language</th>
                            <th title="click to sort" class="headerText">Qty</th>
                            <th title="click to sort" class="headerText">Price&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                    
                        <c:forEach items="${bookslistreport}" var="books" varStatus="status">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><c:out value="${books.orderdate}"/></td>
                                <td class="dataText"><c:out value="${books.centercode}"/></td>
                                <td class="dataText"><c:out value="${books.narration}"/></td>
                                <td class="dataText"><c:out value="${books.title}"/></td>
                                <td class="dataText"><c:out value="${books.author}"/></td>
                                <td class="dataText"><c:out value="${books.language}"/></td>
								<td class="dataText">
								<c:out value="${books.quantity}"/>
								 <c:set var="totalQuantity" value="${totalQuantity + books.quantity}" />
								</td>
                                <td class="dataText"><c:out value="${books.price}"/></td>
                                <%-- <td class="dataText" style="text-align: right">
                                <c:set var="totalQuantity" value="${totalQuantity + books.quantity}" />
                                	<c:set var="itemTotal" value="${itemTotal + books.quantity * books.price}" />
                                	<fmt:setLocale value="en_IN" scope="request"/>
									<fmt:formatNumber type="currency"  value="${books.quantity * books.price}" /> 
                               </td> --%>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                    	<tr>
							<td class="dataTextRight" colspan="5">
								<label style="color: #eb6000"><b>Total Quantity:
									<c:out value="${totalQuantity}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
								</label> 
								<%-- <label style="color: #eb6000"><b>Grand Total:
							<fmt:setLocale value="en_IN" scope="request"/>
							<fmt:formatNumber type="currency"  value="${itemTotal}" /></b>
							</label>  --%>
							</td>
						</tr>
                    
                    <tr>
                     		<td  class="footerTD" >
                           <button id="printbooks">Print</button> 
                           </td>
                    
                        </tr></tfoot>
                </table>
		</div>


	</form>

</body>
</html>
