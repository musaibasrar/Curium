<%-- 
    Document   : Enquiry Form
    Created on : Apr 12, 2025, 10:25:40 AM
    Author     : Musaib
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
    <title>Enquiry Form</title>
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
</style>
<link rel="stylesheet" href="/sac/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sac/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/sac/js/datePicker/jquery-1.7.1.js"></script>
<script src="/sac/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/sac/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/sac/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/sac/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/sac/js/datePicker/ui/sliderAccess.js"></script>
<script src="/sac/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/sac/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/sac/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/sac/css/datePicker/demos.css">

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
	function saveEnquiryForm() {
		var form1 = document.getElementById("form1");
		form1.action = "/sac/EnquiryProcess/saveEnquiryForm";
		form1.method = "POST";
		form1.submit();
	}
	$(function() {
		$("#tabs").tabs();
		$("#saveenquiryform").button().click(function() {
			saveEnquiryForm();
		});
	});
	function yesCheck(id) {
		if (id == "yes:male") {
			document.getElementById("no:male").checked = false;
		}
	}
	function noCheck(id) {
		if (id == "no:male") {
			document.getElementById("yes:male").checked = false;
		}
	}
</script>

	<script type="text/javascript" src="/sac/js/datetimepicker_css.js"></script>

	<script src="/sac/JavaScript/actb.js"></script>
	<script src="/sac/JavaScript/common.js"></script>




	<script>
		$(function() {
			$("#datepicker").datepicker({
				changeYear : true,
				changeMonth : true,
				dateFormat: 'dd/mm/yy',
				yearRange: "-50:+0"
			});
			$("#anim").change(function() {
				$("#datepicker").datepicker("option", "showAnim", $(this).val());
			});
		});
		
	</script>


	
	<script>
		$(function() {
			$("#tabs").tabs();
			$(".nexttab").click(function() {
			    var selected = $("#tabs").tabs("option", "selected");
			    $("#tabs").tabs("option", "selected", selected + 1);
			});
			$(".prevtab").click(function() {
			    var selected = $("#tabs").tabs("option", "selected");
			    $("#tabs").tabs("option", "selected", selected - 1);
			});
			
			 $("#parentsannualincome").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			               return false;
			    }
			   });
			 
			 $("#contactnumber").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			               return false;
			    }
			   });
			 
			 $("#cocontactnumber").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			               return false;
			    }
			   });
			 
			 $("#sts").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			               return false;
			    }
			   });
			 
			 $("#noofdependents").keypress(function (e) {
			     //if the letter is not digit then display error and don't type anything
			     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			               return false;
			    }
			   });
		});


	</script>

	<script type="text/javascript" charset="utf-8">
	            $(document).ready(function() {
	                $('#myTable').dataTable( {
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
	                    
	                } );
	            } );
	        </script>
	</head>
	<%
		//allow access only if session exists
		String user = null;
		if (session.getAttribute("userAuth") == null) {
			response.sendRedirect("/sac/UserProcess/sessionTimeOut");
		} else
			user = (String) session.getAttribute("userAuth");
		String userName = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user"))
					userName = cookie.getValue();
				if (cookie.getName().equals("JSESSIONID"))
					sessionID = cookie.getValue();
			}
		}
	%>
   <body>
        <form method="post"  id="form1">
        <div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Admission Enquiry Form</a></li>
				</ul>
				
				<div id="fragment-1">
					<table style="width: auto;height: auto;" border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Name of the child</td>
							<td><input type="text" name="name" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Gender</td>
							<td>Male<input type="checkbox" value="Male" name="gender" id="yes:male" onclick="yesCheck(this.id);" />&nbsp; &nbsp;Female<input type="checkbox" value="Female" name="gender" id="no:male" onclick="noCheck(this.id)" /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Date of Birth</td>
							<td><input name="dateofbirth" type="text" class="myclass" id="datepicker" size="20" autocomplete="false" onchange="CalculateAge(this)" data-validate="validate(required)"></td>
							<td class="alignLeft" style="padding-left: 20px;">Caste</td>
							<td><input type="text" name="caste" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Place of Birth</td>
							<td><input type="text" name="placeofbirth" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Surname</td>
							<td><input type="text" name="surname" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Previous Class Passed</td>
							<td><label> <select name="previousclasspass" 
									id="previousclasspass" class="myclass" style="width: 180px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classItem">
											<c:if test="${(classItem.classdetails != '')}">
												<option value="${classItem.classdetails}">
													<c:out value="${classItem.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select></label></td>
							<td class="alignLeft" style="padding-left: 20px;">Previous school Name</td>
							<td><input type="text" name="previousschoolname" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Class to be Admitted</td>
							<td>
								<label> <select name="classadmittedin" 
									id="classadmittedin" class="myclass" style="width: 180px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classItem">
											<c:if test="${(classItem.classdetails != '')}">
												<option value="${classItem.classdetails}">
													<c:out value="${classItem.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select></label>
							</td>
							<td class="alignLeft" style="padding-left: 20px;">Religion</td>
							<td><input type="text" name="religion" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Father's Name</td>
							<td><input type="text" name="fathername" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Father Qualification</td>
							<td><input type="text" name="fatherqualification" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Mother's Name</td>
							<td><input type="text" name="mothername" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Mother Qualification</td>
							<td><input type="text" name="motherqualification" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Elder/Younger<br> brother Education</td>
							<td><input type="text" name="brothereducation" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Elder/Younger<br> Sister Education</td>
							<td><input type="text" name="sistereducation" class="myclass"/></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Nature of <br>Profession/occupation</td>
							<td><input type="text" name="occupation" class="myclass"/></td>
							<td class="alignLeft" style="padding-left: 20px;">Academic Year</td>
							<td><select name="academicyear" id="academicyear" required class="myclass" style="width: 180px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${currentAcademicYear}</option>
										<option>2025/26</option>
										<option>2026/27</option>
										<option>2027/28</option>
										<option>2028/29</option>
										<option>2029/30</option>
										<option>2030/31</option>										
								</select></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Permanent Address</td>
							<td><textarea name="address" class="myclass"></textarea></td>
							<td class="alignLeft" style="padding-left: 20px;">Notes</td>
							<td><textarea name="notes" class="myclass"></textarea></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Contact No</td>
							<td><input type="text" name="contactno" class="myclass"/></td>
							<!-- <td class="alignLeft" style="padding-left: 20px;">Parent Sig</td>
							<td><input type="text" name="parentsign" class="myclass"/></td> -->
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
								<button id="saveenquiryform" class="save">Save</button>
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