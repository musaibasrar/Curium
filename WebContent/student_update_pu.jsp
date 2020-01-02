<%-- 
    Document   : student update pu
    Created on : JUN 21, 2018, 11:45:59 PM
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
<title>student update pu</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="js/datePicker/jquery-1.7.1.js"></script>
<script src="js/datePicker/ui/jquery.ui.core.js"></script>
<script src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="js/datePicker/ui/sliderAccess.js"></script>
<script src="js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
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
-->
</style>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>

<script type="text/javascript">
	document.getElementById("UpdateExecutive").style.display = 'none';
	function maleCheck() {

		if (document.getElementById('male').checked == true) {
			document.getElementById('female').checked = false;

		}

	}

	function femaleCheck() {

		if (document.getElementById('female').checked == true) {
			document.getElementById('male').checked = false;

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
		 
		$(".update").button().click(function() {
			
			var form1 = document.getElementById("form1");
			if(form1.checkValidity()) {
				updateStudent();
			}
			

		});
		
		
		$(".cancel").button().click(function() {
			Cancel();

		});
		
		
	});
	
	
	function cancel() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=viewAll";
		form1.submit();
	}

	function updateStudent() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=StudentProcess&action=updateStudent";
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
	<form id="form1" action="Controller?process=PersonalProcess&action=viewAll" method="POST"  enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student's Details-1</a></li>
					<li><a href="#tabs-3">Upload Documents</a></li>
					<li><a href="#tabs-11">Student's Details-2</a></li>
					<li><a href="#tabs-4">Additional Details</a></li>
					<li><a href="#tabs-5">Bank Details</a></li>
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
                    <input type="hidden" value="<c:out value="${student.archive}"/>" id="studentarchiveupdate" name="studentarchiveupdate">
                    <input type="hidden" value="<c:out value="${student.droppedout}"/>" id="studentdroppedoutupdate" name="studentdroppedoutupdate">
                    <input type="hidden" value="<c:out value="${student.passedout}"/>" id="studentpassedoutupdate" name="studentpassedoutupdate">
                    <input type="hidden" value="<c:out value="${student.leftout}"/>" id="studentleftoutupdate" name="studentleftoutupdate">
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


							<td width="16%" class="alignRight">Admission Number&nbsp;</td>
							<td align="left"><label> <input name="admnno"
									type="text" class="textField"
									value="<c:out default="" value="${student.admissionnumber}" />"
									id="admnno" size="30" data-validate="validate(required)">

							</label></td>
							<td width="16%" class="alignRight">SATS Number&nbsp;</td>

							<td align="left"><label><input name="sts"
									type="text" class="textField"
									value="<c:out default="" value="${student.sts}" />"
									id="sts" size="30" maxlength="9"> 

							</label></td>
						</tr>
						
<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td width="16%" class="alignRight">Name &nbsp;</td>
							<td width="28%"><input type="hidden" name="id" id="id"
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /><input type="hidden" name="pudetailsid" id="pudetailsid"
								value="<c:out value="${student.pudetails.idpudetails}" />" /> <label> <input
									name="name" type="text" style="text-transform:uppercase"
									value="<c:out value="${student.name}" />" class="textField"
									id="name" size="30" required>
							</label></td>

							<td width="16%" class="alignRight">Gender &nbsp;</td>

							<td width="16%" class="alignLeft">Male<input type="checkbox"
								value="Male" name="gender" id="male" onclick="maleCheck();"
								${student.gender == 'Male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="Female" name="gender"
								id="female" onclick="femaleCheck()"
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
							<td width="20%" class="alignRight">Date Of Birth &nbsp;</td>
							<td width="28%"><label> <input name="dateofbirth"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>"
									class="textField" id="datepicker" size="30"
									onchange="CalculateAge(this)" autocomplete="off" readonly='true'>
									
									
							</label></td>

							<td width="16%" class="alignRight">Age&nbsp;</td>

							<td align="left"><label> <input name="age"
									type="text" class="textField"
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
							<td width="16%" class="alignRight">Place of birth, Tq, Dist.&nbsp;</td>
							<td align="left"><label> <input name="place"
									type="text" class="textField"
									value="<c:out default="" value="${student.placeofbirth}" />"
									id="place" size="30" data-validate="validate(required)">

							</label></td>
							<td width="16%" class="alignRight">Transfer Certificate no. & date&nbsp;</td>

							<td align="left"><label><input name="tcno"
									type="text" class="textField"
									value="<c:out default="" value="${student.nooftc}"/>"
									id="tcno" size="30">  <input
									name="dateoftc" type="text" class="textField"
									value="<fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/>"
									id="dateoftc" size="30"  autocomplete="off" readonly='true'>

							</label></td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="16%" class="alignRight">Studying in Class&nbsp;</td>

							<td width="28%">
							<label> 
							<select name="classsec" id="classsec" required
									style="width: 120px;">
										<option selected>${classstudying}</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>
							</label> <label> 
							
							<select name="secstudying" id="secstudying"
									style="width: 80px;">
										<option selected>${secstudying}</option>
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



							<td width="20%" class="alignRight">Admitted in class &nbsp;</td>
							<td width="28%">
							<label> 
							
									<select name="admclass" id="admclass"
									style="width: 115px;">
										<option selected>${classadm}</option>
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
									style="width: 80px;">
										<option selected>${secadm}</option>

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
							<td width="16%" class="alignRight">Date Of Admission &nbsp;</td>

							<td align="left"><label> <input
									name="dateofadmission" type="text" class="textField"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									id="dateofadmission" size="30" autocomplete="off" readonly='true'>

							</label></td>
							
							<td width="16%" class="alignRight">Subsequent progress of the student&nbsp;</td>
							<td align="left"><label> <input name="progress"
									type="text" class="textField"
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
							<td width="16%" class="alignRight">UID (Aadhaar) No.&nbsp;</td>
							<td align="left"><label> <input name="crecord"
									type="text" class="textField"
									value="<c:out default="" value="${student.crecord}" />"
									id="crecord" size="30" data-validate="validate(required)">

							</label></td>
							<td width="16%" class="alignRight">Income Certificate No.&nbsp;</td>

							<td align="left"><label><input name="socialcategory"
									type="text" class="textField"
									value="<c:out default="" value="${student.socialcategory}"/>"
									id="socialcategory" size="30">
							</label></td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td width="16%" class="alignRight">Student's Email ID&nbsp;</td>
							<td align="left"><label> <input name="bhagyalakshmibondnumber"
									type="text" class="textField"
									value="<c:out default="" value="${student.bhagyalakshmibondnumber}" />"
									id="bhagyalakshmibondnumber" size="30" data-validate="validate(required)">

							</label></td>
							<td width="16%" class="alignRight">Physical Condition&nbsp;</td>

							<td align="left"><label><input name="disabilitychild"
									type="text" class="textField"
									value="<c:out default="" value="${student.disabilitychild}"/>"
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
							<td width="16%" class="alignRight">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 200px">
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
							<td width="16%" class="alignRight">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 200px" onchange="dropdown()">
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
							<td width="16%" class="alignRight">Religion &nbsp;</td>

							<td align="left"><%-- <label> <input name="religion"
									type="text" class="textField"
									value="<c:out default="" value="${student.religion}" />"
									id="religion" size="30">

							</label> --%>
							
							<label> <select name="religion" 
									id="religion" style="width: 200px" >
										<option selected>${student.religion}</option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>
							
							
							</td>


							<td width="16%" class="alignRight">Caste &nbsp;</td>

							<td align="left"><label> <input name="caste"
									type="text" class="textField"
									value="<c:out default="" value="${student.caste}" />"
									id="caste" size="30">

							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>
							<td width="16%" class="alignRight">Caste Certificate No. &nbsp;</td>

							<td align="left"><label> <input name="studentscastecertno"
									type="text" class="textField"
									value="<c:out default="" value="${student.studentscastecertno}" />"
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
							<td width="30%" class="alignRight">Belongs to BPL &nbsp;</td>
							<td width="16%" height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
								onclick="yesCheck(this.id);" ${student.belongtobpl == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="belongtobpl" id="no:bpl"
								onclick="noCheck(this.id);" ${student.belongtobpl == '0' ? 'checked' : ''}/>

							</td>
							<td width="20%" class="alignRight">BPL Card No.
								&nbsp;</td>
							<td width="28%"><label> <input
									name="bplcardno" type="text" class="textField" value="${student.bplcardno}"
									id="bplcardno" size="36">

							</label></td>
						
						</tr>
						
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						

						<tr>


							<td width="16%" class="alignRight">Mother Tongue &nbsp;</td>

							<td align="left"><%-- <label> <input name="motherT"
									type="text" class="textField"
									value="<c:out default="" value="${student.mothertongue}" />"
									id="motherT" size="30">

							</label> --%>
							
							<label>
							<select name="motherT" 
									id="motherT" style="width: 200px" >
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


							<td width="20%" class="alignRight">Created Date &nbsp;</td>
							<td width="28%"><label> <input name="createddate"
									type="text" value="<fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>" class="textField"
									id="datepickerCD" size="30" data-validate="validate(required)">
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>



						<tr>


							<td width="16%" class="alignRight">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="textField"
									value="<c:out default="" value="${student.remarks}" />"
									id="remarks" size="30">

							</label></td>
						<tr>

							<td></td>

						</tr>
						<tr>

							<td></td>

						</tr>

						<tr>

							<td></td>

						</tr>
						
						<tr>
									<td align="center"><h3 style="text-decoration: underline;color: #eb6000">Parent's Details:</h3><br /></td>
								</tr>
						
						<tr>
							<td width="30%" class="alignRight">Father's Name* &nbsp;</td>
							<td width="12%" align="left"><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.fathersname}" />" required>
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td width="30%" class="alignRight">Mother's Name &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="mothersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.mothersname}" />" required>
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

							<td width="16%" class="alignRight">Guardian's name & address
								&nbsp;</td>

							<td width="28%"><label> <input name="guardian"
									type="text" class="textField" id="guardian" size="36"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>



							<td width="16%" class="alignRight">Annual Income &nbsp;</td>

							<td width="28%"><label> <input name="annualincome"
									type="text" class="textField" id="annualincome" size="36"
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

							<td width="16%" class="alignRight">Contact Number &nbsp;</td>

							<td width="28%"><label> <input name="contactnumber" maxlength="10"
									type="text" class="textField" id="contactnumber" size="36"
									value="<c:out default="" value="${parents.contactnumber}" />" required>

							</label></td>



							<td width="16%" class="alignRight">CO-Contact Number &nbsp;</td>

							<td width="28%"><label> <input
									name="cocontactnumber" type="text" class="textField"
									id="cocontactnumber" size="36" maxlength="10"
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
							<td width="16%" class="alignRight">Email
								&nbsp;</td>

							<td width="28%"><label> <input name="email"
									type="email" class="textField" id="email" size="36"
									
									value="<c:out default="" value="${parents.email}" />">
									

							<td width="20%" class="alignRight">Number Of Dependents
								&nbsp;</td>

							<td width="28%"><label> <input name="noofdependents"
									type="text" class="textField" id="noofdependents" size="36"
									
									value="<c:out default="" value="${parents.noofdependents}" />">

							</label></td>
							</tr>


						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="16%" class="alignRight">Permanent Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="permanentaddress" type="text" class="textField"
										id="permanentaddress" rows="4" cols="35"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>
							</label></td>

							<td width="20%" class="alignRight">Temporary Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="temporaryaddress"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" class="textField" id="temporaryaddress" rows="4"
										cols="35">${parents.addresstemporary}</textarea>
							</label></td>
						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<tr>
							
							<td width="20%" class="alignRight">Notes &nbsp;</td>
							<td width="28%"><label> <input name="remarks"
									type="text" class="textField" id="remarks" size="36"
									value="<c:out default="" value="${parents.remarks}" />">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td><br /></td>
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

								<button id="set" class="update">Update</button>

							</td>
							<td><button id="cancel" class="cancel">Cancel</button></td>
						</tr>


					</table>
				</div>
				
				<div id="tabs-11">
					<table width="100%" border="0" align="center">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>

							<td width="30%" class="alignRight"><label> <font
									color="red"><div id="mydiv"></div></font>
							</label></td>
							<td width="20%" class="alignRight"></td>
							<td class="alignRight"><font color="red"><div
										id="mydivmobile"></div></font></td>
						</tr>

						<tr>


							<td width="30%" class="alignRight">Particulars of the Examination passed &nbsp;</td>
							<td width="12%" align="left"><label> First appearance &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="checkbox" value="1" name="pep" id="firstappearance" ${student.pudetails.exampassedappearance == '1' ? 'checked' : ''}
								onclick="Check1();" />&nbsp;&nbsp;<br>Subsequent appearance<input
								type="checkbox" value="0" name="pep" id="subsequentappearance" ${student.pudetails.exampassedappearance == '0' ? 'checked' : ''}
								onclick="Check2()" />
							</label></td>
							
							<td width="30%" class="alignRight">Year &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="passedyear" type="text" class="myclass" id="year" size="36" style="text-transform:uppercase"
									value="${student.pudetails.exampassedyear}" 
									> 
							</label></td>

						</tr>
						
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="30%" class="alignRight">SSLC Reg. No. &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="regno" type="text" class="myclass" id="regno" size="36" style="text-transform:uppercase"
									value="${student.pudetails.exampassedregno}" 
									> 
							</label></td>

							<td width="30%" class="alignRight">Result With Class&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="resultclass" type="text" class="myclass" id="resultclass" size="36" style="text-transform:uppercase"
									value="${student.pudetails.exampassedresultwithclass}" 
									> 
							</label></td>

						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
						<tr>
							<td width="30%" class="alignRight">Second Language Offered in SSLC/HSC(X std.) or Equivalent Examination&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="Xsecondlanguage" type="text" class="myclass" id="Xsecondlanguage" size="36"
									value="${student.pudetails.secondlanguage}" 
									>
							</label></td>

							<td width="30%" class="alignRight">Aggregate Marks in SSLC/HSC(X std.) or Equivalent Examination&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="aggmarks" type="text" class="myclass" id="aggmarks" size="36"
									value="${student.pudetails.aggregatemarkssslc}"
									>
							</label></td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
				<tr>
							<td width="30%" class="alignRight">Selected Subjects&nbsp;</td>
							<td width="12%" align="left"> <div id="arts" ><label>Part-I (Choose any Two)<br>
							 <input	name="subjectspart1" type="text" class="myclass" id="subjectspart1" size="36" style="text-transform:uppercase"
									value="${student.pudetails.optionalsubjects}" 
									> 
							</label> <label><br>Part-II (Compulsory) <br>
							<input	name="subjectspart2" type="text" class="myclass" id="subjectspart2" size="36" style="text-transform:uppercase"
									value="${student.pudetails.compulsorysubjects}" 
									>
							</label>
							</div>
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
							<td width="30%" class="alignRight">Medium of instruction at the SSLC/SSC/HSC (X std.) or Equivalent Examination&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="Xmediuminstruction" type="text" class="myclass" id="Xmediuminstruction" size="36"
									 value="${student.pudetails.sslcmediuminstruction}" 
									>
							</label></td>

							<td width="30%" class="alignRight">Medium of instruction selected for Pre-University Course&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="PUmediuminstruction" type="text" class="myclass" id="PUmediuminstruction" size="36"
									value="${student.pudetails.pumediuminstruction}"
									>
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


						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								<tr>
									<td align="center">
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								
								<tr>
									<td align="center">
										
										
										<button id="update2" class="update">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel2" class="cancel">Cancel</button>

									</td>


								</tr>
							</table>

						</div>
						
				<div id="tabs-3">
					<table width="100%" border="0" align="center" >
					
							<tr>
									<td></td>
									<td><label style="font-size: 12px;color: #eb6000;font-weight: bold;">Note: Upload only .jpg images</label></td>
								</tr>
								
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
                    <input type="file" name="studentdoc1" id="studentdoc1" accept="image/*" >
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
                    <input type="file" name="studentdoc2" id="studentdoc2" accept="image/*" >
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
                   <input type="file" name="studentdoc3" id="studentdoc3" accept="image/*" >
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
                    <input type="file" name="studentdoc4" id="studentdoc4" accept="image/*" >
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
                    <input type="file" name="studentdoc5" id="studentdoc5" accept="image/*" >
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
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
								
								<tr>
									<td align="center">
										
										
										<button id="updatethree" class="update">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelthree" class="cancel">Cancel</button>

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
							<table width="100%" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td width="30%" class="alignRight"><label> <font
											color="red"><div id="mydiv"></div></font>
									</label></td>
									<td width="20%" class="alignRight"></td>
									<td class="alignRight"><font color="red"><div
												id="mydivmobile"></div></font></td>
								</tr>



								<tr>
									<td width="30%" class="alignRight">Class on leaving&nbsp;</td>
									<td width="12%" align="left"><label> <input
											name="classonleaving" type="text"
											value="<c:out default="" value="${student.classonleaving}" />" class="myclass" id="classonleaving" style="text-transform:uppercase"
											size="36" > <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td width="30%" class="alignRight">Date of leaving the school&nbsp;</td>
									<td width="12%" align="left"><label> <input
									name="dateofleaving" type="text" class="textField" 
									id="dateofleaving" size="36" value="<fmt:formatDate type="date" value="${student.dateleaving}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)"><!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td width="16%" class="alignRight">Reason for leaving &nbsp;</td>

									<td width="28%"><label> <input name="reasonforleaving"
											type="text" class="textField" id="reasonforleaving" size="36"
											value="<c:out default="" value="${student.reasonleaving}" />"
											>

									</label></td>



									<td width="16%" class="alignRight">No. & date of transfer certificate issued&nbsp;</td>

									<td width="28%"><label> <input name="notcissued"
									type="text" class="textField" id="notcissued" size="36" value="<c:out default="" value="${student.notcissued}" />"
									><input
									name="dateoftcissued" type="text" class="textField" 
									id="dateoftcissued" size="36" value="<fmt:formatDate type="date" value="${student.datetcissued}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)">
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
									<td><br /></td>
								</tr>
								
								<tr align="center">
								
								<td width="20%" class="alignRight"> &nbsp;</td>
								
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
									
										
										<button id="updatefour" class="update">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelfour" class="cancel">Cancel</button>

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
							
						
						<div id="tabs-5">

							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td width="10%" class="alignRight"></td>
							<td width="68%"><label class="alignRight"> Bank Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="bankname"
									value="<c:out default="" value="${student.bankname}" />"
									type="text" class="myclass" id="bankname" size="36"
									onclick="validateNameContact();">
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						<tr>
								<td width="20%" class="alignRight"></td>
							<td width="68%"><label class="alignRight"> Bank IFSC Code&nbsp;&nbsp;<input name="bankifsc"
									value="<c:out default="" value="${student.bankifsc}" />"
									type="text" class="myclass" id="bankifsc" size="36"
									onclick="validateNameContact();">
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						<tr>
								<td width="20%" class="alignRight"></td>
							<td width="68%"><label class="alignRight"> Account No.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="accno"
									value="<c:out default="" value="${student.accno}" />"
									type="text" class="myclass" id="accno" size="36"
									onclick="validateNameContact();">
							</label></td>
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


									<button id="updatethree" class="update">Update</button>

									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="cancelthree" class="cancel">Cancel</button>

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
						
</body>
</html>

