<%--
    Document   : View Enquiries
    Created on : Apr 12, 2025, 8:20:28 AM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Enquiries</title>
<link rel="stylesheet" href="/hamidullah/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/hamidullah/css/datePicker/demos.css">
<link rel="stylesheet" href="/hamidullah/css/font-awesome.css">
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

.alignLeft {
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
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/hamidullah/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/hamidullah/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/hamidullah/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/hamidullah/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/hamidullah/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/hamidullah/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/hamidullah/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/hamidullah/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
<script type="text/javascript" src="/hamidullah/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	 $(function(){
		 $("#export").button({
				
			});
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
     });
	 

</script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerfrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepickerfrom").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerto").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepickerto").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


	<script type="text/javascript">
					
					var cancelreceipt = '<c:out default="" value="${cancelreceiptresult}"/>';
		            
		            if(cancelreceipt == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(cancelreceipt == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
            
        </script>
        
        <script type="text/javascript">
					
					var deletealert='<c:out default="" value="${deletesuccess}"/>';
		            
		            if(deletealert == "true"){
		            	 $(function(){
		            		 $( "div.success" ).html("Enquiry(ies) Deleted Successfully");
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(deletealert == "false"){
		            	  $(function(){
		            		  $( "div.failure" ).html("Enquiry(ies) Deletion Failed");
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
        </script>
        
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/hamidullah/UserProcess/sessionTimeOut");
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
	<form id="form1" action="/hamidullah/EnquiryProcess/deleteEnquiry" method="post">
	
		<div class="alert-box success"></div>
		<div class="alert-box failure"></div>
		
		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Enquiries</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Name</th>
						<th title="click to sort" class="headerText">Father Name&nbsp;</th>
						<th title="click to sort" class="headerText">Mother Name&nbsp;</th>
						<th title="click to sort" class="headerText">Admission Class</th>
						<th title="click to sort" class="headerText">Previous School Name</th>
						<th title="click to sort" class="headerText">Address</th>
						<th title="click to sort" class="headerText">Academic Year</th>
					</tr>
				</thead>

                   <tbody>

					<c:forEach items="${admissionEnquiryList}" var="admissionEnquiryList">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${admissionEnquiryList.id}"/>" class="chcktbl"
								name="id"
								value="<c:out value="${admissionEnquiryList.id}"/>" /></td>
							<td class="dataText"><a class="dataTextInActive" href="/hamidullah/EnquiryProcess/getStudentEnquiryform?id=<c:out value='${admissionEnquiryList.id}'/>"><c:out value="${admissionEnquiryList.name}" /></a></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.fathername}" /></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.mothername}" /></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.admissionclass}" /></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.previousSchoolName}" /></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.address}" /></td>
							<td class="dataText"><c:out value="${admissionEnquiryList.academicYear}" /></td>
						</tr>
					</c:forEach>




				</tbody>
                    <tfoot>
					<tr>
													
						<td class="footerTD" colspan="2"> &nbsp;
						<!-- <label style="font-weight: bold;font-size: 14px;font-family: cursive;color: white;">File Name</label>
						<label> <input
									name="fileName" type="text" class="myclass" id="fileName"
									size="20">
							</label> -->
						<input value="Delete"
							type="submit" id="export"/> </td>
							
							

					</tr>
				</tfoot>
			</table>

		</div>


	</form>

</body>
</html>
