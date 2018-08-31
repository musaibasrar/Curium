<%--
    Document   : View Order
    Created on : AUG 16, 2018, 6:00:28 AM
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
<title>View Order</title>
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
/* alert CSS */
.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.update {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.updatefailure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.delete {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.deletefailure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}
</style>
<style>
#button {
	
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
			icons : {
				primary : "ui-icon-arrowthick-1-s"
			}
		}).click(function() {
			runEffect();
			return false;
		});
	});
	$(function() {
		$("#entrydate").datepicker({
			changeYear : true,
			changeMonth : true,
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#entrydate").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function rejectOrders() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=rejectOrders";
		form1.method = "POST";
		form1.submit();
	}
	
	function updateOrders() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=updateOrders";
		form1.method = "POST";
		form1.submit();
	}
	
	function deliverOrders() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=deliverOrders";
		form1.method = "POST";
		form1.submit();
	}
	
	function searchOrders() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=OrderProcess&action=searchOrders";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#apply").button().click(function() {
			searchOrders();
		});
		$("#effect").hide();
		
		 $("#fromdate").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'yy-mm-dd',
				yearRange: "-50:+0"
			});
			$("#anim").change(function() {
				$("#fromdate").datepicker("option", "showAnim", $(this).val());
			});
			
			$("#todate").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'yy-mm-dd',
				yearRange: "-50:+0"
			});
			$("#anim").change(function() {
				$("#todate").datepicker("option", "showAnim", $(this).val());
			});

	});
	
	 $(function(){
         $("#update").button({
             icons:{
                 primary: "ui-icon-note"
             }
         }).click(function(){
        	 if(confirm('Are you sure,you want to update the order(s)?')){
        		 updateOrders();	
         	}
            
             return false;

         });
         $("#reject").button({
             icons:{
                 primary: "ui-icon-circle-close"
             }
         }).click(function(){
        	 if(confirm('Are you sure,you want to reject the order(s)?')){
        		 rejectOrders();
         	}
             return false;

         });
         $("#deliver").button({
             icons:{
                 primary: " ui-icon-person"
             }
         }).click(function(){
        	 if(confirm('Are you sure,you want to confirm the order(s)?')){
        		 deliverOrders();
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
         
         $("#districtcode").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
     });
	 
	
	 
	 
</script>

<script type="text/javascript">
					
					var updateorder='<c:out default="" value="${updateorders}"/>';
		            var deliverorder='<c:out default="" value="${deliverorders}"/>';
		            var rejectorder='<c:out default="" value="${rejectorders}"/>';
		            
		            if(updateorder == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(updateorder == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }else if(deliverorder == "true"){
		                   	 $(function(){
		                   		 $( "div.update" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   	 });
		                   	 }else if(deliverorder == "false"){
		                   	  $(function(){
		                   		 $( "div.updatefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		                   		 });
		                   	 }else if(rejectorder == "true"){
			                   	 $(function(){
			                   		 $( "div.delete" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
			                   	 });
			                   	 }else if(rejectorder == "false"){
			                   	  $(function(){
			                   		 $( "div.deletefailure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
			                   		 });
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
		
		<div class="alert-box success">Order(s) has been updated successfully!!!</div>
		<div class="alert-box failure">Update Failed, Unable to update the order(s)!!!</div>
		
		<div class="alert-box update">Order(s) has been delivered successfully!!!</div>
		<div class="alert-box updatefailure">Deliver Failed, Unable to deliver Order(s)!!!</div>
		
		<div class="alert-box delete">Order(s) has been rejected successfully!!!</div>
		<div class="alert-box deletefailure">Reject Failed, Unable to reject Order(s)!!!</div>
		
		
		<div style="height: 28px">
			<button id="add">Apply Filter</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Filter</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr><td><br></td></tr>
						<tr>
							<td width="10%" class="alignRight">From Date:&nbsp;</td>
							<td width="70%"><label> <input
									name="fromdate" type="text" class="textField" style="width: 200px;" 
									id="fromdate" size="25"  data-validate="validate(required)"/>
							</label>
								<label class="alignRight">&nbsp;&nbsp;To Date: &nbsp;</label>
							<label> <input
									name="todate" type="text" class="textField"
									id="todate" size="25" style="width: 200px;" />
							</label>
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Order Status &nbsp;</td>
							<td width="70%"><label> <select name="orderstatus" id="orderstatus"
									style="width: 200px;" >
										<option selected></option>
										<option value="DELIVERED">Delivered</option>
										<option value="REJECTED" >Rejected</option>
										<option value="PENDING" >Pending</option>
								</select></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="10%" class="alignRight">Center &nbsp;</td>
							<td width="70%"><label> <select name="branchid" id="branchid" 
									style="width: 200px;">
										<option selected></option>
										<c:forEach items="${branchList}" var="branchlist">
											<option value="${branchlist.idbranch}" >
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
							<td><br /></td>
						</tr>
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="apply">Apply</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Orders List</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText" style="width:10%"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText" style="width:10%">Order No.<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Center Name<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Order Date<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" />&nbsp;</th>
							<th title="click to sort" class="headerText">Order Status<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" />&nbsp;</th>
							<th title="click to sort" class="headerText" style="width:15%">Delivered/Rejected Date<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" />&nbsp;</th>
							<th title="click to sort" class="headerText">Payment Status<img
							alt="" style="position: relative; top: 4px;"
							src="images/sort_both.png" />&nbsp;</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${ordersummarylist}" var="ordersummarylist" varStatus="status">
						<tr style="border-color: #000000" border="1" cellpadding="1" cellspacing="1">
                          <td class="dataText" style="width:10%"><input type="checkbox" id = "<c:out value="${ordersummarylist.key.idorders}"/>" class = "chcktbl"  name="orderids"  value="${ordersummarylist.key.idorders}:${status.index}"/></td>
						  <td  class="dataTextInActive" style="width:10%"><a class="dataTextInActive" href="Controller?process=OrderProcess&action=viewOrderDetails&id=<c:out value='${ordersummarylist.key.idorders}'/>&centername=<c:out value="${ordersummarylist.value.centername}" />"><c:out value="${ordersummarylist.key.idorders}"/></a></td>
						  <td class="dataText"><c:out value="${ordersummarylist.value.centername}" /></td>
						  <td class="dataText"><c:out value="${ordersummarylist.key.orderdate}" /></td>
						  <td class="dataText"><c:out value="${ordersummarylist.key.narration}" /></td>
						  <td class="dataText" style="width:15%"><c:out value="${ordersummarylist.key.confirmationdate}" /></td>
						  <td class="dataText"><input type="text" style="background-color: #E3EFFF;border-style: none;color: #4B6A84;" value="<c:out value="${ordersummarylist.key.paymentstatus}" />" id="paymentstatus" name="paymentstatus"></td>
						</tr>
					</c:forEach>

				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="deliver">Deliver</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="reject">Reject</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="update">Update</button>
                            </td> 
                        </tr></tfoot>
			</table>

		</div>


	</form>

</body>
</html>
