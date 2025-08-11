<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <title>Print Enquiry Form</title>
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
    
    .title {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 24px;
        font-weight: bold;
        text-align: center;
        color: #325F6D;
        margin-bottom: 10px;
    }
    
    .subtitle {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        color: #325F6D;
        margin-bottom: 20px;
    }
    
    @media print {
        button, #print, #modify {
            display: none !important;
        }
        
        #tabs ul {
            display: none !important;
        }
        
        #fragment-1 {
            display: block !important;
        }
        
        .title, .subtitle {
            display: block !important;
        }
    }
</style>
<link rel="stylesheet" href="/jdh/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/jdh/css/validation/jquery.ketchup.css">
<script type="text/javascript"
	src="/jdh/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/jdh/js/datePicker/jquery-1.7.1.js"></script>
<script src="/jdh/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/jdh/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/jdh/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/jdh/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/jdh/js/datePicker/ui/sliderAccess.js"></script>
<script src="/jdh/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/jdh/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/jdh/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/jdh/css/datePicker/demos.css">

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
</style>

<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		$("#modify").button().click(function() {
			updateEnquiryForm();
		});
		$("#print").button();
	});
	
	function updateEnquiryForm(){
		var form1=document.getElementById("form1");
		form1.action="/jdh/EnquiryProcess/updateEnquiryDetails?id=<c:out value='${admissionEnquiry.id}'/>";
		form1.submit();
	}
	
</script>
    </head>
   <body>
    <form method="post" id="form1">
        <div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Admission Enquiry Form</a></li>
				</ul>
				
				<div id="fragment-1">
					<div class="title">vision Group of Institutions</div>
					<div class="subtitle">Admission Enquiry Form - ${admissionEnquiry.academicYear}</div>
					<table style="width: auto;height: auto;" border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Name of the child</td>
							<td>${admissionEnquiry.name}</td>
							<td class="alignLeft" style="padding-left: 20px;">Gender</td>
							<td>${admissionEnquiry.gender}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Date of Birth</td>
							<td>${admissionEnquiry.dateofbirth}</td>
							<td class="alignLeft" style="padding-left: 20px;">Caste</td>
							<td>${admissionEnquiry.caste}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Place of Birth</td>
							<td>${admissionEnquiry.placeOfBirth}</td>
							<td class="alignLeft" style="padding-left: 20px;">Surname</td>
							<td>${admissionEnquiry.surName}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Previous class pass</td>
							<td>${admissionEnquiry.previousClassPassed}</td>
							<td class="alignLeft" style="padding-left: 20px;">Previous school Name</td>
							<td>${admissionEnquiry.previousSchoolName}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Class to be admitted</td>
							<td>${admissionEnquiry.admissionclass}</td>
							<td class="alignLeft" style="padding-left: 20px;">Religion</td>
							<td>${admissionEnquiry.religion}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Father's Name</td>
							<td>${admissionEnquiry.fathername}</td>
							<td class="alignLeft" style="padding-left: 20px;">Father Qualification</td>
							<td>${admissionEnquiry.fatherQualification}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Mother's Name</td>
							<td>${admissionEnquiry.mothername}</td>
							<td class="alignLeft" style="padding-left: 20px;">Mother Qualification</td>
							<td>${admissionEnquiry.motherQualification}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Elder/Younger brother Education</td>
							<td>${admissionEnquiry.brothereducation}</td>
							<td class="alignLeft" style="padding-left: 20px;">Elder/Younger Sister Education</td>
							<td>${admissionEnquiry.sistereducation}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Nature of Profession/occupation</td>
							<td>${admissionEnquiry.occupation}</td>
							<td class="alignLeft" style="padding-left: 20px;">Academic Year</td>
							<td>${admissionEnquiry.academicYear}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Permanent Address</td>
							<td>${admissionEnquiry.address}</td>
							<td class="alignLeft" style="padding-left: 20px;">Notes</td>
							<td>${admissionEnquiry.notes}</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Contact No</td>
							<td>${mobileno}</td>
							<td class="alignLeft" style="padding-left: 20px;">Parent Sig</td>
							<td>${placeOfBirth}</td>
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
								<button onclick="window.print()" id="print">Print</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="modify">Modify</button>
							</td>
							<td colspan="2">&nbsp;</td>
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
    </form>
    </body>
    </html>