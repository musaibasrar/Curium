<%-- 
    Document   : student update
    Created on : Jan 10, 2013, 3:25:59 PM
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
<title>Student Update</title>
<link rel="stylesheet" href="/flora/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/flora/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/flora/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/flora/js/datePicker/jquery-1.7.1.js"></script>
<script src="/flora/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/flora/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/flora/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/flora/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/flora/js/datePicker/ui/sliderAccess.js"></script>
<script src="/flora/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/flora/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/flora/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/flora/css/datePicker/demos.css">
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
-->
</style>
<script type="text/javascript" src="/flora/js/datetimepicker_css.js"></script>

<script type="text/javascript">
	
	
	function yesCheck(id) {

		if (document.getElementById(id).checked == true) {
			var splitId = id.split(':');
			document.getElementById('no:'+splitId[1]).checked = false;
		}

	}
	function noCheck(id) {

		if (document.getElementById(id).checked == true) {
			var splitId = id.split(':');
			document.getElementById('yes:'+splitId[1]).checked = false;
		}

	}
	
	 function enterOtherSpecialCategory() {
	        var distlistitem = document.getElementById("specialcategory");
	        var distlistitemtext = distlistitem.options[distlistitem.selectedIndex].text;

	        if (distlistitemtext == "Others (Please Specify)") {
	            document.getElementById("specialcategory").style.display = "none";
	            document.getElementById("categoryname").style.display = "none";
	            document.getElementById("newcateg").style.display = '';
	        }
	    }
</script>


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
	$(function() {
		$("#datepickeradmn").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#datepickeradmn").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateoftc").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateoftc").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateoftcissued").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateoftcissued").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateofadmission").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateofadmission").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#dateofleaving").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(
				function() {
					$("#dateofleaving").datepicker("option", "showAnim",
							$(this).val());
				});
	});
	
	$(function() {
		$("#datepickerCD").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepickerCD").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>


<script type="text/javascript">
	$(function() {

		$("#set").button().click(function() {

			updateStudent();

		});

		$("#cancel").button().click(function() {
			cancel();

		});
	});
</script>
<script>
	
	function CalculateAge(value) {
		var dateOfBirth = document.getElementById('datepicker').value;
		var from = dateOfBirth.split("/");
		var today = new Date();
		var birthDate = new Date(from[2],from[1] - 1,from[0]);
		var month = birthDate.getMonth();
		var age = today.getFullYear() - birthDate.getFullYear();
		var m = today.getMonth() - birthDate.getMonth();
		if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			age--;
		}
		//return age;
		document.getElementById('age').value = age;
	}
	
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
		
		$("#updatetwo").button().click(function() {
			updateStudent();

		});
		$("#updatethree").button().click(function() {
			updateStudent();

		});
		$("#updatefour").button().click(function() {
			updateStudent();

		});

		$("#canceltwo").button().click(function() {
			Cancel();

		});
		$("#cancelthree").button().click(function() {
			Cancel();

		});
		$("#cancelfour").button().click(function() {
			Cancel();

		});
		 $("#sts").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
		 
	});
	
	


</script>
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/flora/UserProcess/sessionTimeOut");
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
	<form action="/flora/PersonalProcess/viewAll"
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<li><a href="#tabs-5">Previous School Details</a></li>
					<li><a href="#tabs-2">Parent's Details</a></li>
					<li><a href="#tabs-3">Upload Photo</a></li>
					<li><a href="#tabs-4">Additional Details</a></li>
					<li><a href="#tabs-6">Bank Details</a></li>
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
                    
                    <input type="hidden" value="<c:out value="${student.studentdoc1}"/>" id="studentdoc1update" name="studentdoc1update">
                    <input type="hidden" value="<c:out value="${student.studentdoc2}"/>" id="studentdoc2update" name="studentdoc2update">
                    <input type="hidden" value="<c:out value="${student.studentdoc3}"/>" id="studentdoc3update" name="studentdoc3update">
                    <input type="hidden" value="<c:out value="${student.studentdoc4}"/>" id="studentdoc4update" name="studentdoc4update">
                    <input type="hidden" value="<c:out value="${student.studentdoc5}"/>" id="studentdoc5update" name="studentdoc5update">
                    <input type="hidden" value="<c:out value="${student.archive}"/>" id="studentarchiveupdate" name="studentarchiveupdate">
                    
                    <input type="hidden" value="<c:out value="${student.passedout}"/>" id="passedout" name="passedout">
                    <input type="hidden" value="<c:out value="${student.droppedout}"/>" id="droppedout" name="droppedout">
                    <input type="hidden" value="<c:out value="${student.leftout}"/>" id="leftout" name="leftout">
                    </td>
                    </tr>
				
				</table>
				
					<table width="70%" border="0" align="center" id="table1">


						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>


							<td class="alignLeft">Admission Number&nbsp;</td>
							<td ><label> <input name="admnno"
									type="text" class="myclass" required
									value="<c:out default="" value="${student.admissionnumber}" />"
									id="admnno" size="30" data-validate="validate(required)">

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">STS Number &nbsp;</td>

									<td width="16%"><label> <input
											name="sts" type="text" class="myclass"
											id="sts" size="30" value="${student.sts}">

									</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Name &nbsp;</td>
							<td><input type="hidden" name="id" id="id"
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /> <label> <input
									name="name" type="text" style="text-transform:uppercase" required
									value="<c:out value="${student.name}" />" class="myclass"
									id="name" size="30" data-validate="validate(required)">
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Gender &nbsp;</td>

							<td>Male<input type="checkbox"
								value="Male" name="gender" id="yes:male" onclick="yesCheck(this.id);"
								${student.gender == 'Male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="Female" name="gender"
								id="no:male" onclick="noCheck(this.id);"
								${student.gender == 'Female' ? 'checked' : ''} />

							</td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>



						<tr>
							<td class="alignLeft">Date Of Birth &nbsp;</td>
							<td><label> <input name="dateofbirth" autocomplete="false"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepicker" size="30"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
									
									
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Age&nbsp;</td>

							<td><label> <input name="age"
									type="text" class="myclass"
									value="<c:out default="" value="${student.age}" />" id="age"
									size="30">

							</label></td>
						</tr>


<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Place of birth, Tq, Dist.&nbsp;</td>
							<td><label> <input name="place"
									type="text" class="myclass"
									value="<c:out default="" value="${student.placeofbirth}" />"
									id="place" size="30" data-validate="validate(required)">

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">Date Of Admission &nbsp;</td>

							<td><label> <input
									name="dateofadmission" type="text" class="myclass" autocomplete="false"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									id="dateofadmission" size="30"
									data-validate="validate(required)">

							</label></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>		
						
						
						<tr>
							<td class="alignLeft">Studying in Class&nbsp;</td>

							<td>
							
							<label> 
									<select name="classsec" id="classsec"
									style="width: 130px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${classstudying}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							</label> <label>
							
							<select name="secstudying" id="secstudying" style="width: 75px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${secstudying}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							</label>
							
							</td>



							<td class="alignLeft" style="padding-left: 20px;">Admitted in class &nbsp;</td>

							<td>
							<label> 
								<select name="admclass" id="admclass" style="width: 130px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${classadm}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							</label> <label> 
									<select name="admsec" id="admsec"
									style="width: 75px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${secadm}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>	
										</c:forEach>
							</select>
							</label>
							
							
							
							</td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.bloodgroup}</option>
										<option>A +ve</option>
										<option>A -ve</option>
										<option>AB +ve</option>
										<option>AB -ve</option>
										<option>O +ve</option>
										<option>O -ve</option>
										<option>B +ve</option>
										<option>B -ve</option>
								</select>

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 210px;border-radius: 4px;background: white;height: 28px;" onchange="dropdown()">
										<option selected>${student.nationality }</option>
										<option>Indian</option>
										<option>Other</option>
								</select>

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>



						<tr>


							<td class="alignLeft">Religion &nbsp;</td>

							<td><%-- <label> <input name="religion"
									type="text" class="myclass"
									value="<c:out default="" value="${student.religion}" />"
									id="religion" size="30">

							</label> --%>
							
							<label> <select name="religion" onblur="validateNameContact();"
									id="religion" style="width: 210px;border-radius: 4px;background: white;height: 28px;" onkeypress="return validateContactNum(this);">
										<option selected>${student.religion}</option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>
							
							
							</td>


							<%-- <td width="16%" class="alignRight">Caste &nbsp;</td>

							<td align="left"><label> <input name="caste"
									type="text" class="myclass"
									value="<c:out default="" value="${student.caste}" />"
									id="caste" size="30">

							</label></td>
 --%>
 							<td class="alignLeft" style="padding-left: 20px;">Students Caste</td>
							<td><label> <input
									name="studentscastecertno" type="text" class="myclass" value="${student.studentscastecertno}"
									id="studentscastecertno" size="30">

							</label></td>

						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						<tr>
								<td class="alignLeft">Students Caste &nbsp;</td>
								<td><label> <input name="studentscaste"
									type="text" class="myclass" id="studentscaste" value="${student.studentscaste}" size="30">

							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory"
									id="socialcategory" style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option>General</option>
										<option>OBC</option>
										<option>SC</option>
										<option>ST</option>
								</select>

							</label></td>

							</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						
							<tr>
								<td class="alignLeft">Belong to BPL &nbsp;</td>
								<td height="30">&nbsp;Yes<input
								type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
								onclick="yesCheck(this.id);" ${student.belongtobpl == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="belongtobpl" id="no:bpl"
								onclick="noCheck(this.id);" ${student.belongtobpl == '0' ? 'checked' : ''}/>

							</td>
							<td class="alignLeft" style="padding-left: 20px;">BPL Card No.
								&nbsp;</td>
							<td><label> <input
									name="bplcardno" type="text" class="myclass" value="${student.bplcardno}"
									id="bplcardno" size="30">

							</label></td>
						
						</tr>
						
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Bhagyalakshmi Bond No.
								&nbsp;</td>
								<td>
										<input
									name="bhagyalakshmibondnumber" type="text" class="myclass" value="${student.bhagyalakshmibondnumber}"
									id="bhagyalakshmibondnumber" size="30">

							</td>
							<td class="alignLeft" style="padding-left: 20px;">Student's Aadhar Card No.&nbsp;</td>
							<td><label> <input
									name="disabilitychild" type="text" class="myclass" value="${student.disabilitychild}"
									id="disabilitychild" size="30">

							</label></td>
						</tr>
												
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>

						<tr>
							<td class="alignLeft">Special Category&nbsp;</td>

							<td id="categoryname"><label> <select
									name="specialcategory" onchange="enterOtherSpecialCategory()"
									id="specialcategory" style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.specialcategory}</option>
										<option>None</option>
										<option>Destitute</option>
										<option>HIV Case</option>
										<option>Orphans</option>
										<option>Others (Please Specify)</option>
								</select>

							</label></td>
							<td id="newcateg" style="display: none;"><label> <input
									name="newcategory" id="newcategory" type="text" class="myclass" size="30" 
									<%-- value= "${student.newcategory}" --%>placeholder="Add Other Category" />
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Mother Tongue &nbsp;</td>

							<td align="left"><%-- <label> <input name="motherT"
									type="text" class="myclass"
									value="<c:out default="" value="${student.mothertongue}" />"
									id="motherT" size="30">

							</label> --%>
							
							<label>
							<select name="motherT" onblur="validateNameContact();"
									id="motherT" style="width: 210px;border-radius: 4px;background: white;height: 28px;" onkeypress="return validateContactNum(this);">
										<option selected>${student.mothertongue}</option>
										<option>Urdu</option>
										<option>Hindi</option>
										<option>English</option>
										<option>Kannada</option>
										<option>Marathi</option>
										<option>Telugu</option>
										<option>Tamil</option>
								</select>
							</label>
							
							</td>



						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>

							<td class="alignLeft">RTE &nbsp;</td>
							<td height="30">&nbsp;Yes<input
								type="checkbox" value="1" name="rte" id="yes:rte"
								onclick="yesCheck(this.id);" ${student.rte == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="rte" id="no:rte"
								onclick="noCheck(this.id);" ${student.rte == '0' ? 'checked' : ''}/>
							</td>

							<td class="alignLeft" style="padding-left: 20px;">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="myclass"
									value="<c:out default="" value="${student.remarks}" />"
									id="remarks" size="30">

							</label></td>
							</tr>
							
							
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>



						<tr>
							<td class="alignLeft">Created Date &nbsp;</td>
							<td><label> <input name="createddate"
									type="text" value="<fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>" class="myclass"
									id="datepickerCD" size="30" data-validate="validate(required)">
							</label></td>

							</tr>
							
						<tr>

							<td></td>

						</tr>
						<tr>

							<td></td>

						</tr>

						<tr>

							<td></td>

						</tr>



					</table>




					<table id="table2" width="30%" border="0" align="center">
<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
						<tr>
							<td align="center">

								<button id="set">Update</button>

							</td>
							<td><button type="submit" id="cancel">Cancel</button></td>
						</tr>


					</table>
				</div>
				
				
				<div id="tabs-3">
					<table width="100%" border="0" align="center" >
						<tr>
							<td>
                  		 	 <input type="checkbox" name="studentpicdelete" value="delete">Delete
                    		</td>
							<td>
                  		  	<img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Pic" style="width: 200px;height: 200px;">
                   			 </td>
                    
							<td><br />
							<input type="file" name="fileToUpload" id="fileToUpload" accept="image/*" >
							</td>
						</tr>
						
						<tr>
						<td>
                    <input type="checkbox" name="studentdoc1delete" value="delete">Delete
                    </td>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentdoc1}"/>" alt="Student's Doc1" style="width: 200px;height: 200px;">
                    </td>
                    <td>
                    <input type="file" name="fileToUpload" id="studentdoc1" accept="image/*" >
                    </td>
                    </tr>
                    
                    <tr>
                    <td>
                    <input type="checkbox" name="studentdoc2delete" value="delete">Delete
                    </td>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentdoc2}"/>" alt="Student's Doc2" style="width: 200px;height: 200px;">
                    </td>
                    <td>
                    <input type="file" name="fileToUpload" id="studentdoc2" accept="image/*" >
                    </td>
                    
                    </tr>
                    
                    <tr>
                    <td>
                    <input type="checkbox" name="studentdoc3delete" value="delete">Delete
                    </td>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentdoc3}"/>" alt="Student's Doc3" style="width: 200px;height: 200px;">
                    </td>
                    <td>
                   <input type="file" name="fileToUpload" id="studentdoc3" accept="image/*" >
                    </td>
                    </tr>
                    
                    <tr>
                    <td>
                    <input type="checkbox" name="studentdoc4delete" value="delete">Delete
                    </td>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentdoc4}"/>" alt="Student's Doc4" style="width: 200px;height: 200px;">
                    </td>
                    <td>
                    <input type="file" name="fileToUpload" id="studentdoc4" accept="image/*" >
                    </td>
                    </tr>
                    
                    <tr>
                    <td>
                    <input type="checkbox" name="studentdoc5delete" value="delete">Delete
                    </td>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentdoc5}"/>" alt="Student's Doc5" style="width: 200px;height: 200px;">
                    </td>
                    <td>
                    <input type="file" name="fileToUpload" id="studentdoc5" accept="image/*" >
                    </td>
                    </tr>
				</table>



<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
								
								<tr>
									<td align="center">
										
										
										<button id="updatethree" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelthree">Cancel</button>

									</td>


								</tr>
								<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
<tr>
<td><br/></td>
</tr>
							</table>

						</div>
						
</div>


						<div id="tabs-4">
							<table width="70%" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignLeft">Class on leaving&nbsp;</td>
									<td ><label> <input
											name="classonleaving" type="text"
											value="<c:out default="" value="${student.classonleaving}" />" class="myclass" id="classonleaving" style="text-transform: capitalize;"
											size="30" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Date of leaving <br>the school</td>
									<td><label> <input
									name="dateofleaving" type="text" class="myclass" autocomplete="false"
									id="dateofleaving" size="30" value="<fmt:formatDate type="date" value="${student.dateleaving}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)"><!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignLeft">Reason for leaving &nbsp;</td>

									<td><label> <input name="reasonforleaving" style="text-transform: capitalize;"
											type="text" class="myclass" id="reasonforleaving" size="30"
											value="<c:out default="" value="${student.reasonleaving}" />"
											onkeypress="return validateContactNum(this);">

									</label></td>



									<td class="alignLeft" style="padding-left: 20px;">No. & date of <br>transfer certificate issued&nbsp;</td>

									<td><label> <input name="notcissued"
									type="text" class="myclass" id="notcissued" size="30" value="<c:out default="" value="${student.notcissued}" />"
									><input
									name="dateoftcissued" type="text" class="myclass"  autocomplete="false"
									id="dateoftcissued" size="30" value="<fmt:formatDate type="date" value="${student.datetcissued}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)">
							</label></td>
									
								</tr>
								
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
							<tr>
									<td><br /></td>
								</tr>
							<tr>
							<td></td>
								<td align="center">
								<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="prevtab"
									style="font-weight: bold; color: #325F6D; font-size: 13px"
									href="#">Previous</a></td>
							</tr>
							
							<tr>
									<td><br /></td>
								</tr>
									
							<tr align="center">
									
									
									<td width="20%" class="alignRight"> &nbsp;</td>

									<td align="center">
									
										
										<button id="updatefour" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelfour">Cancel</button>

									</td>

								
								
									
								</tr>

								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>
								

						</div>
						</div>
							
							</table>
							
							</div>
							
							
							

				<div id="tabs-2">
					<table width="70%" align="center">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignLeft">Father's Name*</td>
							<td ><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="name"
									size="30" style="text-transform: capitalize;" required
									value="<c:out default="" value="${parents.fathersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Mother's Name*</td>
							<td><label> <input
									name="mothersname" type="text" class="myclass" id="name"
									size="30" style="text-transform: capitalize;" required
									value="<c:out default="" value="${parents.mothersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>


						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Father's Qualification</td>
							<td><label> <input
									name="fathersqualification" type="text" class="myclass" id="fathersqualification"
									size="30" style="text-transform: capitalize;"
									value="<c:out default="" value="${parents.fathersqualification}" />">
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Mother's Qualification;</td>
							<td ><label> <input
									name="mothersqualification" type="text" class="myclass" id="mothersqualification"
									size="30" style="text-transform:capitalize;"
									value="<c:out default="" value="${parents.mothersqualification}" />">
							</label></td>


						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
						<td class="alignLeft">Father's Caste <br> Certificate No</td>
							<td><label> <input
									name="fatherscastecertno" type="text"  style="text-transform:capitalize;" class="myclass" value="${parents.fatherscastecertno}"
									id="fatherscastecertno" size="30">

							</label></td>

					
						<td class="alignLeft" style="padding-left: 20px;">Mother's Caste <br> Certificate No</td>
							<td><label> <input name="motherscastecertno"
									type="text" class="myclass" id="motherscastecertno"  style="text-transform:capitalize;" value="${parents.motherscastecertno}" size="30">

							</label></td>
						</tr>
						
												<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td class="alignLeft">Guardian's <br>name & address</td>

							<td><label> <input name="guardian"
									type="text" class="myclass" id="guardian" size="30" style="text-transform:capitalize;"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>



							<td class="alignLeft" style="padding-left: 20px;">Annual Income</td>

							<td><label> <input name="annualincome"
									type="text" class="myclass" id="annualincome" size="30"
									value="<c:out default="" value="${parents.parentsannualincome}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>

							<td class="alignLeft">Contact Number*</td>

							<td><label> <input name="contactnumber"
									type="text" class="myclass" id="contactnumber" size="30" required
									value="<c:out default="" value="${parents.contactnumber}" />">

							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Co-Contact Number</td>

							<td><label> <input
									name="cocontactnumber" type="text" class="myclass"
									id="cocontactnumber" size="30"
									value="<c:out default="" value="${parents.cocontactnumber}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td class="alignLeft">Email</td>

							<td ><label> <input name="email"
									type="email" class="myclass" id="email" size="30"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.email}" />">
									

							<td class="alignLeft" style="padding-left: 20px;">Number Of Dependents</td>

							<td><label> <input name="noofdependents"
									type="text" class="myclass" id="noofdependents" size="30"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.noofdependents}" />">

							</label></td>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignLeft">Permanent Address</td>
							<td><label> <textarea
										name="permanentaddress" type="text" class="myclass"
										id="permanentaddress" rows="4" cols="30"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Temporary Address</td>
							<td><label> <textarea
										name="temporaryaddress"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" class="myclass" id="temporaryaddress" rows="4"
										cols="30">${parents.addresstemporary}</textarea>
							</label></td>
						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							
							<td class="alignLeft">Notes</td>
							<td><label> <input name="remarksadditional"
									type="text" class="myclass" id="remarksadditional" size="30"
									value="<c:out default="" value="${parents.remarks}" />">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						
	
								<tr align="center">
									<td></td>
									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>
								

						<tr>
									<td><br /></td>
								</tr>	
							<tr align="center">
									
									
									<td width="20%" class="alignRight"> &nbsp;</td>

									<td align="center">
									
										
										<button id="updatetwo" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo">Cancel</button>

									</td>
									</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						</label>
						</td>
						</tr>
						</table>

						</div>

							<div id="tabs-5">
				
						<table style="width:70%;" align="center">
								<tr>

										<td><br /></td>
									</tr>
									
						<tr>
							<td class="alignLeft">Transfer certificate No.</td>

							<td><label><input name="tcno"
									type="text" class="myclass"
									value="<c:out default="" value="${student.nooftc}"/>"
									id="tcno" size="30">  

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">Date of transfer certificate&nbsp;</td>

							<td><label> <input
									name="dateoftc" type="text" class="myclass" autocomplete="false"
									value="<fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/>"
									id="dateoftc" size="30"
									data-validate="validate(required)">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td class="alignLeft">Previous Class Studied</td>

							<td><label> <select name="lastclass" id="lastclass"
									style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.stdlaststudied}</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>

							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Previous School Name
								&nbsp;</td>
							<td><label> <input style="text-transform:capitalize;"
									name="lastschool" type="text" class="myclass"
									value="<c:out default="" value="${student.schoollastattended}" />"
									id="lastschool" size="30" data-validate="validate(required)">
							</label></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignLeft">Languages Studied</td>

							<td><label> <input
									name="languagesstudied" type="text" class="myclass" style="text-transform:capitalize;"
									value="<c:out default="" value="${student.languagesstudied}" />"
									id="languagesstudied" size="30" data-validate="validate(required)">

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Core Subjects Studied&nbsp;</td>
							<td><label> <input name="progress"
									type="text" class="myclass" style="text-transform:capitalize;"
									value="<c:out default="" value="${student.subsequentprogress}" />"
									id="progress" size="30" data-validate="validate(required)">

							</label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
							<tr>
							<td class="alignLeft">Previous School<br> Medium of Instruction</td>

							<td><label> <select name="mediumofinstruction"
									id="mediumofinstruction" style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.mediumofinstruction}</option>
										<option>Kannada</option>
										<option>Hindi</option>
										<option>Urdu</option>
										<option>English</option>
										<option>Marathi</option>
										<option>Tamil</option>
										<option>Telgu</option>
								</select>

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Previous School Type</td>

							<td><label> <select name="previousschooltype"
									id="previousschooltype" style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${student.previousschooltype}</option>
										<option></option>
										<option>Government</option>
										<option>Private Aided</option>
										<option>Local Bodies</option>
										<option>Private Unaided School</option>
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



					<div>
						<table width="100%">
							<tr>

								<td><br /></td>
							</tr>

							<tr>

								<td align="center">
								<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="prevtab"
									style="font-weight: bold; color: #325F6D; font-size: 13px"
									href="#">Previous</a></td>
							</tr>

							<tr>

								<td><br /></td>
							</tr>

							<tr>
								<td align="center">


									<button id="updatefive" class="update"
										onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();"
										onfocus="validateNameContact();validateFatherName();">Update</button>

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="cancelfive" class="cancel">Cancel</button>

								</td>


							</tr>
							<tr>
								<td><br /></td>
							</tr>
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
				
				<div id="tabs-6">

							<div>
								<table width="30%" align="center">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignLeft" ><label> Bank Name</label></td>
									<td ><input name="bankname"
									value="<c:out default="" value="${student.bankname}" />"
									type="text" class="myclass" id="bankname" size="30" style="text-transform: capitalize;"
									onclick="validateNameContact();">
							</td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						<tr>
								<td class="alignLeft"><label> Bank IFSC Code</label></td>
								<td><input name="bankifsc"
									value="<c:out default="" value="${student.bankifsc}" />"
									type="text" class="myclass" id="bankifsc" size="30" style="text-transform: capitalize;"
									onclick="validateNameContact();"></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignLeft"><label> Account No.&nbsp;&nbsp;</label></td>
								<td><input name="accno"
									value="<c:out default="" value="${student.accno}" />" style="text-transform: capitalize;"
									type="text" class="myclass" id="accno" size="30"
									onclick="validateNameContact();"></td>
						</tr>
															
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr align="left">

								<tr>
								<td></td>
								<td align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								 <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>
								<tr><td><br></td></tr>
									<tr>
										<td></td>
								<td>


									<button id="updatesix" class="update"
										onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();"
										onfocus="validateNameContact();validateFatherName();">Update</button>

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="cancelsix" class="cancel">Cancel</button>

								</td>


							</tr>
									<tr>
										<td><br /></td>
									</tr>
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
						</div>
						</form>
						<script>
							$(function() {
								$("#datepicker").datepicker({
									changeYear : true,
									changeMonth : true
								});
								$("#anim").change(
										function() {
											$("#datepicker").datepicker(
													"option", "showAnim",
													$(this).val());
										});
								$("#entryDate").datepicker({
									changeYear : true,
									changeMonth : true
								});
								$("#anim").change(
										function() {
											$("#datepicker").datepicker(
													"option", "showAnim",
													$(this).val());
										});
							});
						</script>

						<script type="text/javascript">
							function cancel() {

								var form1 = document.getElementById("form1");
								form1.action = "/flora/StudentProcess/viewAll";
								form1.submit();
							}

							function updateStudent() {
								
								var form1 = document.getElementById("form1");
								if(form1.checkValidity()) {
									form1.action = "/flora/StudentProcess/updateStudent";
									form1.submit();
								  }
							}
						</script>
</body>
</html>
