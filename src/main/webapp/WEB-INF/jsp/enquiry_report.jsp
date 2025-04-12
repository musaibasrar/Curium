<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <title>Enquiry Report</title>
<link rel="stylesheet" href="/roshan/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/roshan/css/validation/jquery.ketchup.css">
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/roshan/js/datePicker/jquery-1.7.1.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/roshan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/roshan/js/datePicker/ui/sliderAccess.js"></script>
<script src="/roshan/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/roshan/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/roshan/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/roshan/css/datePicker/demos.css">

<style>
   td{
        font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
    }
    
    @media print {
        button, #filterSection {
            display: none;
        }
    }
</style>

<style type="text/css">
.myclass {
	font-size: 1.3em;
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
	height: 28px;
	color: black;
	text-transform: capitalize;
	border-radius: 4px;
}

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

.alignLeft {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
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
}

.alignRight {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignRightHead {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
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

/* Styling for the tab heading to match addStudent page */
#tabs ul li a {
    font-family: Arial, Helvetica, sans-serif;
    font-size: 14px;
    font-weight: bold;
    color: #325F6D;
    text-decoration: none;
    padding: 8px 12px;
}

#tabs ul li {
    display: inline;
    margin-right: 5px;
}

#tabs ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    border-bottom: 1px solid #5d7e9b;
}

/* Table styles */
.reportTable {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

.reportTable th {
    background-color: #4b6a84;
    color: white;
    padding: 8px;
    text-align: left;
    font-weight: bold;
}

.reportTable td {
    padding: 8px;
    border-bottom: 1px solid #ddd;
}

.reportTable tr:nth-child(even) {
    background-color: #f2f2f2;
}

.reportTable tr:hover {
    background-color: #ddd;
}

/* Button styles */
.save {
    background-color: #4b6a84;
    color: white;
    border: none;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
}

.cancel {
    background-color: #f44336;
    color: white;
    border: none;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
}

.print {
    background-color: #4CAF50;
    color: white;
    border: none;
    padding: 8px 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 14px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 4px;
}
</style>

<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		$("#generate").button().click(function() {
			generateReport();
		});
		$("#print").button().click(function() {
			window.print();
		});
	});
	
	function generateReport(){
		var form1=document.getElementById("form1");
		form1.action="/roshan/EnquiryProcess/generateEnquiryReport";
		form1.submit();
	}
</script>

<script>
	$(function() {
		$("#fromdate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#todate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
	});
</script>
    </head>
   <body>
    <form method="post" id="form1">
        <div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Enquiry Report</a></li>
				</ul>
				
				<div id="fragment-1">
					<div id="filterSection">
						<table style="width: auto;height: auto;" border="0" align="center" id="table1">
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td class="alignLeft">From Date:</td>
								<td><input name="fromdate" type="text" class="myclass" id="fromdate" size="20" autocomplete="false" data-validate="validate(required)" value="${fromdate}"></td>
								<td class="alignLeft" style="padding-left: 20px;">To Date:</td>
								<td><input name="todate" type="text" class="myclass" id="todate" size="20" autocomplete="false" data-validate="validate(required)" value="${todate}"></td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td class="alignLeft">Academic Year:</td>
								<td>
									<select name="academicyear" class="myclass" style="width: 180px;border-radius: 4px;background: white;height: 28px;">
										<option value="">Select Academic Year</option>
										<option value="2025/26" ${academicyear == '2025/26' ? 'selected' : ''}>2025/26</option>
										<option value="2026/27" ${academicyear == '2026/27' ? 'selected' : ''}>2026/27</option>
										<option value="2027/28" ${academicyear == '2027/28' ? 'selected' : ''}>2027/28</option>
										<option value="2028/29" ${academicyear == '2028/29' ? 'selected' : ''}>2028/29</option>
										<option value="2029/30" ${academicyear == '2029/30' ? 'selected' : ''}>2029/30</option>
										<option value="2030/31" ${academicyear == '2030/31' ? 'selected' : ''}>2030/31</option>
									</select>
								</td>
								<td class="alignLeft" style="padding-left: 20px;">Class:</td>
								<td>
									<select name="classadmittedin" class="myclass" style="width: 180px;border-radius: 4px;background: white;height: 28px;">
										<option value="">Select Class</option>
										<c:forEach items="${classdetailslist}" var="classItem">
											<c:if test="${(classItem.classdetails != '')}">
												<option value="${classItem.classdetails}" ${classadmittedin == classItem.classdetails ? 'selected' : ''}>
													<c:out value="${classItem.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr align="center">
								<td class="alignRight">&nbsp;</td>
								<td align="center">
									<button id="generate" class="save">Generate Report</button>
								</td>
								<td align="center">
									<button id="print" class="print">Print Report</button>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
							<tr>
								<td><br /></td>
							</tr>
						</table>
					</div>
					
					<c:if test="${not empty enquiryList}">
						<div class="reportHeader">
							<h2 align="center">Enquiry Report</h2>
							<p align="center">
								<c:if test="${not empty fromdate && not empty todate}">
									From: ${fromdate} To: ${todate}
								</c:if>
								<c:if test="${not empty academicyear}">
									<br>Academic Year: ${academicyear}
								</c:if>
								<c:if test="${not empty classadmittedin}">
									<br>Class: ${classadmittedin}
								</c:if>
							</p>
						</div>
						
						<table class="reportTable">
							<thead>
								<tr>
									<th>Sr. No.</th>
									<th>Name</th>
									<th>Gender</th>
									<th>Date of Birth</th>
									<th>Class</th>
									<th>Academic Year</th>
									<th>Contact No</th>
									<th>Address</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${enquiryList}" var="enquiry" varStatus="status">
									<tr>
										<td>${status.index + 1}</td>
										<td>${enquiry.name}</td>
										<td>${enquiry.gender}</td>
										<td><fmt:formatDate value="${enquiry.dateofbirth}" pattern="dd/MM/yyyy"/></td>
										<td>${enquiry.admissionclass}</td>
										<td>${enquiry.academicYear}</td>
										<td>${enquiry.mobileno}</td>
										<td>${enquiry.address}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					
					<c:if test="${empty enquiryList && not empty fromdate}">
						<div class="reportHeader">
							<h2 align="center">No Enquiries Found</h2>
							<p align="center">
								<c:if test="${not empty fromdate && not empty todate}">
									From: ${fromdate} To: ${todate}
								</c:if>
								<c:if test="${not empty academicyear}">
									<br>Academic Year: ${academicyear}
								</c:if>
								<c:if test="${not empty classadmittedin}">
									<br>Class: ${classadmittedin}
								</c:if>
							</p>
						</div>
					</c:if>
				</div>
			</div>
		</div>
    </form>
    </body>
    </html> 