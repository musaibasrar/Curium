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
<script src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="css/datePicker/demos.css">

<style type="text/css">

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
	color: black;
	text-transform: capitalize;
	text-transform:uppercase;
	height: 30px;
	border-radius: 5px;
}

.alignRight {
	font-family: Tahoma;
	font-size: 14px;
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

.myclass {
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
	color: black;
	text-transform: capitalize;
}

.educationtable {
font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
	
}

.educationtableheaderdescription {
  border: 1px solid #5d7e9b;
  text-align: center;
  padding: 8px;
}

.certificatetable {
font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 50%;
  float: left;	
}

.photocopy {
  text-align: center;
  font-size: 16px; 
  font-weight: bold;
}
</style>

<script type="text/javascript" src="js/datetimepicker_css.js"></script>

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
		
		$("#annualincome").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
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
	
    function numberWithCommas(annualincome) {
    	var x=annualincome.value;
    	x = x.replace (/,/g, "");
    	
    	var lastThree = x.substring(x.length-3);
    	var otherNumbers = x.substring(0,x.length-3);
    	if(otherNumbers != '')
    	    lastThree = ',' + lastThree;
    	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
    	annualincome.value = res;
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
		$("#updatefive").button().click(function() {
			updateStudent();

		});
		$("#updatesix").button().click(function() {
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
		$("#cancelfive").button().click(function() {
			Cancel();

		});
		$("#cancelsix").button().click(function() {
			Cancel();

		});
		/*  $("#sts").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   }); */
		 
	});
	
	


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
	<form action="Controller?process=PersonalProcess&action=viewAll"
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<li><a href="#tabs-6">Educational Qualification</a></li>
					<li><a href="#tabs-3">Upload Documents</a></li>
					<li><a href="#tabs-2">Parent's Details</a></li>
					<li><a href="#tabs-5">Previous School Details</a></li>
					<li><a href="#tabs-4">Additional Details</a></li>
					<!-- <li><a href="#tabs-6">Bank Details</a></li> -->
				</ul>



				<div id="tabs-1">
				
				<table align="center">
				
				<tr>
                    <td>
                    <img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 200px;height: 200px;">
                    <input type="hidden" value="<c:out value="${student.studentpic}"/>" id="studentpicupdate" name="studentpicupdate">
                    <input type="hidden" value="<c:out value="${student.passedout}"/>" id="passedout" name="passedout">
                    <input type="hidden" value="<c:out value="${student.droppedout}"/>" id="droppedout" name="droppedout">
                    <input type="hidden" value="<c:out value="${student.leftout}"/>" id="leftout" name="leftout">
                    </td>
                    </tr>
				
				</table>
				
					<table width="80%" border="0" align="center" id="table1">


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


							<td  class="alignRight">Admission Number&nbsp;</td>
							<td align="left"><label> <input name="admnno" 
									type="text" class="textField"  style="font-size: 16px;" required 
									value="<c:out default="" value="${student.admissionnumber}" />"
									id="admnno" size="26" data-validate="validate(required)">

							</label></td>
							<td  class="alignRight">BRF Number &nbsp;</td>

									<td ><label> <input
											name="studentexternalid" type="text" class="textField" style="font-size: 16px;"
											id="studentexternalid" size="26" value="${student.studentexternalid}">

									</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td  class="alignRight">Name &nbsp;</td>
							<td width="28%"><input type="hidden" name="id" id="id"
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /> 
								
								<label> <input	name="name" type="text"  style="font-size: 16px;"
									value="<c:out value="${student.name}"/>" class="textField"
									id="name" size="26" required>
							</label></td>

							<td  class="alignRight">Gender &nbsp;</td>

							<td  class="alignLeft" style="font-size: 16px;">Male<input type="checkbox"
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
							<td width="20%" class="alignRight">Date Of Birth &nbsp;</td>
							<td width="28%"><label> <input name="dateofbirth" autocomplete="false" style="font-size: 16px;"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>"
									class="textField" id="datepicker" size="26"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
									
									
							</label></td>

							<td  class="alignRight">Age&nbsp;</td>

							<td align="left"><label> <input name="age"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${student.age}" />" id="age"
									size="26">

							</label></td>
						</tr>


<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
											
						
						<tr>
							<td  class="alignRight">Studying in Class&nbsp;</td>

							<td width="28%">
							
							<label> 
									<select name="classsec" id="classsec"
									style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
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
							</label>
							
							<%--  <label>
							<select name="secstudying" id="secstudying"
									style="width: 80px;">
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
							</label> --%>
							</td>



							<td width="20%" class="alignRight">Admitted in class &nbsp;</td>

							<td width="28%">
							<label> 
								<select name="admclass" id="admclass"
									style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
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
							</label><%--  <label> 
									<select name="admsec" id="admsec"
									style="width: 80px;">
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
							</label> --%>
							</td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td  class="alignRight">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
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
							<td  class="alignRight">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white" onchange="dropdown()">
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


							<td  class="alignRight">Religion &nbsp;</td>

							<td align="left"><%-- <label> <input name="religion"
									type="text" class="textField"
									value="<c:out default="" value="${student.religion}" />"
									id="religion" size="26">

							</label> --%>
							
							<label> <select name="religion" onblur="validateNameContact();"
									id="religion" style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white" onkeypress="return validateContactNum(this);">
										<option selected>${student.religion}</option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>
							
							
							</td>


							<%-- <td  class="alignRight">Caste &nbsp;</td>

							<td align="left"><label> <input name="caste"
									type="text" class="textField"
									value="<c:out default="" value="${student.caste}" />"
									id="caste" size="26">

							</label></td>
 --%>
 							<td  class="alignRight">Caste Certificate No.</td>
							<td width="28%"><label> <input 
										style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white"
									name="studentscastecertno" type="text" class="textField" value="${student.studentscastecertno}"
									id="studentscastecertno" size="36">

							</label></td>

						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						<tr>
							<td width="20%" class="alignRight">Caste &nbsp;</td>
							<td width="28%"><label> <input name="caste" style="font-size: 16px;"
									type="text" class="textField" id="caste" value="${student.caste}" size="26">

							</label></td>
							
							
							<td  class="alignRight">Mother Tongue &nbsp;</td>

							<td align="left">
							<label>
							<select name="motherT" onblur="validateNameContact();"
									id="motherT" 
									style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white"
									 onkeypress="return validateContactNum(this);">
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

							<!-- <td  class="alignRight">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory"
									id="socialcategory" style="width: 240px;font-size: 16px;">
										<option>General</option>
										<option>OBC</option>
										<option>SC</option>
										<option>ST</option>
								</select>

							</label></td> -->

	</tr>
	<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						
												<%-- <tr>
						<td width="30%" class="alignRight">Was in receipt of any scholarship &nbsp;</td>
							<td  height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
								onclick="yesCheck(this.id);" ${student.belongtobpl == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="belongtobpl" id="no:bpl"
								onclick="noCheck(this.id);" ${student.belongtobpl == '0' ? 'checked' : ''}/>

							</td>
							<td width="20%" class="alignRight">Adhar Card No.
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
							<td width="20%" class="alignRight">Whether Vaccinated
								&nbsp;</td>
								<td  height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="bhagyalakshmibondnumber" id="yes:bpl"
								onclick="yesCheck(this.id);" ${student.bhagyalakshmibondnumber == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="bhagyalakshmibondnumber" id="no:bpl"
								onclick="noCheck(this.id);" ${student.bhagyalakshmibondnumber == '0' ? 'checked' : ''}/>

							</td>
							<td  class="alignRight">Marks of Identification on Pupil's body&nbsp;</td>
							<td><label> <input
									name="disabilitychild" type="text" class="textField" value="${student.disabilitychild}"
									id="disabilitychild" size="36">

							</label></td>
						</tr>
												
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
 --%>
						<tr>
							<%-- <td width="20%" class="alignRight">Special Category&nbsp;</td>

							<td id="categoryname"><label> <select
									name="specialcategory" onchange="enterOtherSpecialCategory()"
									id="specialcategory" style="width: 240px">
										<option selected>${student.specialcategory}</option>
										<option>None</option>
										<option>Destitute</option>
										<option>HIV Case</option>
										<option>Orphans</option>
										<option>Others (Please Specify)</option>
								</select>

							</label></td>
							<td width="28%" id="newcateg"
								style="display: none;"><label> <input
									name="newcategory" id="newcategory" type="text" class="textField" size="36" 
									value= "${student.newcategory}"placeholder="Add Other Category" />
							</label></td> --%>

							<td  class="alignRight">Date Of Admission &nbsp;</td>

							<td align="left"><label> <input
									name="dateofadmission" type="text" class="textField" autocomplete="false" style="font-size: 16px;"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									id="dateofadmission" size="26"
									data-validate="validate(required)">

							</label></td>
							
							
							<td width="20%" class="alignRight">Created Date &nbsp;</td>
							<td width="28%"><label> <input name="createddate" style="font-size: 16px;"
									type="text" value="<fmt:formatDate value="${student.createddate}" pattern="dd/MM/yyyy"/>" class="textField"
									id="datepickerCD" size="26" data-validate="validate(required)">
							</label></td>



						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>

							<%-- <td width="30%" class="alignRight">RTE &nbsp;</td>
							<td  height="30" class="alignLeft">&nbsp;Yes<input
								type="checkbox" value="1" name="rte" id="yes:rte"
								onclick="yesCheck(this.id);" ${student.rte == '1' ? 'checked' : ''}/>&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="rte" id="no:rte"
								onclick="noCheck(this.id);" ${student.rte == '0' ? 'checked' : ''}/>
							</td> --%>

							<td  class="alignRight">Remarks&nbsp;</td>

							<td align="left"><label> <input name="remarks"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${student.remarks}" />"
									id="remarks" size="26">

							</label></td>
							</tr>
							
							
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>

						<tr>

							<td></td>

						</tr>



					</table>




					<table id="table2" align="center">
<tr>

									<td><br /></td>
								</tr>
								
								<tr>
									<td></td>
									<td>
										<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									</td>
								</tr>

							<tr>

									<td><br /></td>
								</tr>
						<tr>
						
							<td>
								<button id="set">Update</button>
							</td>
							<td></td>
							<td><button type="submit" id="cancel">Cancel</button></td>
						</tr>


					</table>
				</div>
				
				
				<div id="tabs-3">
				
							<table width="20%" style="float: left" align="center">
									<tr>
									<td></td>
								</tr>
							</table>
							
							<table width="20%" style="float: left" align="center">
								<tr>
									<td><label style="font-size: 12px;color: #eb6000;font-weight: bold;">Note: Upload only .jpg images</label></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Pic</label><br /> 
									<img src="data:image;base64,<c:out value="${student.studentpic}"/>" alt="Student's Photo" style="width: 150px;height: 150px;">
									<input type="file" name="fileToUpload" id="fileToUpload" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 1</label><br />
									<img src="data:image;base64,<c:out value="${student.studentdoc1}"/>" alt="student doc1" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${student.studentdoc1}"/>" id="studentdoc1update" name="studentdoc1update">
									 <input type="file" name="studentdoc1" id="studentdoc1" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 2</label><br /> 
									<img src="data:image;base64,<c:out value="${student.studentdoc2}"/>" alt="student doc2" style="width: 150px;height: 150px;">
									<input type="hidden" value="<c:out value="${student.studentdoc2}"/>" id="studentdoc2update" name="studentdoc2update">
									<input type="file" name="studentdoc2" id="studentdoc2" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 3</label><br />
									 <img src="data:image;base64,<c:out value="${student.studentdoc3}"/>" alt="student doc3" style="width: 150px;height: 150px;">
									 <input type="hidden" value="<c:out value="${student.studentdoc3}"/>" id="studentdoc3update" name="studentdoc3update">
									 <input type="file" name="studentdoc3" id="studentdoc3" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 4</label><br /> 
										<img src="data:image;base64,<c:out value="${student.studentdoc4}"/>" alt="student doc4" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${student.studentdoc4}"/>" id="studentdoc4update" name="studentdoc4update">
									<input type="file" name="studentdoc4" id="studentdoc4" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								</table>
								
								<table width="30%" align="center">
								
								<tr>
									<td></td>
								</tr>
								
								<tr>
								<td><br><br></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 5</label><br />
										<img src="data:image;base64,<c:out value="${student.studentdoc5}"/>" alt="student doc5" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${student.studentdoc5}"/>" id="studentdoc5update" name="studentdoc5update">
									 <input type="file" name="studentdoc5" id="studentdoc5" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 6</label><br /> 
									<img src="data:image;base64,<c:out value="${student.studentdoc6}"/>" alt="student doc6" style="width: 150px;height: 150px;">
									<input type="hidden" value="<c:out value="${student.studentdoc6}"/>" id="studentdoc6update" name="studentdoc6update">
									<input type="file" name="studentdoc6" id="studentdoc6" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 7</label><br />
										<img src="data:image;base64,<c:out value="${student.studentdoc7}"/>" alt="student doc7" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${student.studentdoc7}"/>" id="studentdoc7update" name="studentdoc7update">
									 <input type="file" name="studentdoc7" id="studentdoc7" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 14px;color: #325F6D;font-weight: bold;">Student Doc 8</label><br /> 
										<img src="data:image;base64,<c:out value="${student.studentdoc8}"/>" alt="student doc8" style="width: 150px;height: 150px;">
										<input type="hidden" value="<c:out value="${student.studentdoc8}"/>" id="studentdoc8update" name="studentdoc8update">
									<input type="file" name="studentdoc8" id="studentdoc8" accept="image/*"></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								
								<tr>
								<td><br><br><br></td>
								<td><br><br><br></td>
								</tr>
								
								
								
							</table>

					<table width="50%" align="center">
									<tr>

										<td></td>
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
										<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
								&nbsp;&nbsp;&nbsp;
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
									
									</tr>

									<tr>
										<td><br /></td>
									</tr>
									
									<tr>
										<td >


										<button id="updatethree" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancelthree">Cancel</button>

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
							
												<!-- <table width="100%" border="0" align="center" >
						<tr>
							<td><br />
							<input type="file" name="fileToUpload" id="fileToUpload" accept="image/*" >
							</td>
						</tr>
						

</table> -->



<div>
							<!-- <table width="100%">
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
							</table> -->

						</div>
						
</div>


						<div id="tabs-4">
							<table width="100%"  align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignRight">Class on leaving&nbsp;</td>
									<td width="28%" align="left">
									<label> 	
										<select name="classonleaving" id="classonleaving"
											style="width: 260px;height: 30px;border-radius: 5px;font-size: 16px;background-color: white">
										<option selected>${student.classonleaving}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
									</select>
									</label></td>

									<td class="alignRight">Date of leaving the school&nbsp;</td>
									<td width="28%" align="left"><label> <input
									name="dateofleaving" type="text" class="textField" autocomplete="false" style="font-size: 16px;"
									id="dateofleaving" size="26" value="<fmt:formatDate type="date" value="${student.dateleaving}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)"><!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td  class="alignRight">Reason for leaving &nbsp;</td>

									<td width="28%"><label> <input name="reasonforleaving" style="text-transform:uppercase;font-size: 16px;"
											type="text" class="textField" id="reasonforleaving" size="26"
											value="<c:out default="" value="${student.reasonleaving}" />"
											onkeypress="return validateContactNum(this);">

									</label></td>



									<td  class="alignRight">No. & date of transfer certificate issued&nbsp;</td>

									<td width="28%"><label> <input name="notcissued" style="text-transform:uppercase;font-size: 16px;"
									type="text" class="textField" id="notcissued" size="26" value="<c:out default="" value="${student.notcissued}" />"
									><input
									name="dateoftcissued" type="text" class="textField"  autocomplete="false" style="font-size: 16px;"
									id="dateoftcissued" size="26" value="<fmt:formatDate type="date" value="${student.datetcissued}" pattern="dd/MM/yyyy"/>" data-validate="validate(required)">
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
					<table width="80%"  id="table1">
						<tr>
							<td><br><br><br></td>
						</tr>
					
						<tr>
						
							<td class="alignRight">Father's Name* &nbsp;</td>
							<td width="28%"><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="textField" id="name" 
									size="26" style="text-transform:uppercase;font-size: 16px;" required
									value="<c:out default="" value="${parents.fathersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td class="alignRight">Mother's Name* &nbsp;</td>
							<td width="28%"><label> <input
									name="mothersname" type="text" class="textField" id="name"
									size="26" style="text-transform:uppercase;font-size: 16px" required
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

							<td  class="alignRight">Contact Number* &nbsp;</td>

							<td width="28%"><label> <input name="contactnumber" style="font-size: 16px;"
									type="text" class="textField" id="contactnumber" size="26" required
									value="<c:out default="" value="${parents.contactnumber}" />">

							</label></td>



							<td  class="alignRight">CO-Contact Number &nbsp;</td>

							<td width="28%"><label> <input
									name="cocontactnumber" type="text" class="textField"
									id="cocontactnumber" size="26" style="font-size: 16px;"
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
							<td  class="alignRight">Permanent Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="permanentaddress" type="text" style="text-transform:uppercase;font-size: 16px; border-radius: 5px"
										id="permanentaddress" rows="4" cols="27" 
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>
							</label></td>

							<td width="20%" class="alignRight">Temporary Address &nbsp;</td>
							<td width="28%"><label> <textarea
										name="temporaryaddress" style="text-transform:uppercase;font-size: 16px; border-radius: 5px"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" id="temporaryaddress" rows="4"
										cols="27">${parents.addresstemporary}</textarea>
							</label></td>
						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>

							<td  class="alignRight">Guardian's name & address
								&nbsp;</td>

							<td width="28%"><label> <input name="guardian" style="font-size: 16px;"
									type="text" class="textField" id="guardian" size="26"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>



							<td  class="alignRight">Annual Income &nbsp;</td>

							<td width="28%"><label> <input name="annualincome" style="font-size: 16px;"
									type="text" class="textField" id="annualincome" size="26"
									value="<c:out default="" value="${parents.parentsannualincome}" />" onkeyup="numberWithCommas(this);">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
						<td class="alignRight">Fathers Occupation&nbsp;</td>
							<td width="28%"><label> <input style="font-size: 16px;"
									name="fatherscastecertno" type="text" class="textField" value="${parents.fatherscastecertno}"
									id="fatherscastecertno" size="26">

							</label></td>

					
						<td class="alignRight">Mothers Occupation&nbsp;</td>
							<td width="28%"><label> <input name="motherscastecertno" style="font-size: 16px;"
									type="text" class="textField" id="motherscastecertno" value="${parents.motherscastecertno}" size="26">

							</label></td>
						</tr>
						
												<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRight">Father's Qualification &nbsp;</td>
							<td width="28%" align="left"><label> <input
									name="fathersqualification" type="text" class="textField" id="fathersqualification"
									size="26" style="text-transform:uppercase;font-size: 16px;" 
									value="<c:out default="" value="${parents.fathersqualification}" />">
							</label></td>

							<td class="alignRight">Mother's Qualification &nbsp;</td>
							<td width="28%" align="left"><label> <input
									name="mothersqualification" type="text" class="textField" id="mothersqualification"
									size="26" style="text-transform:uppercase;font-size: 16px;"
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
							<td  class="alignRight">Email
								&nbsp;</td>

							<td width="28%"><label> <input name="email" style="font-size: 16px;"
									type="email" class="textField" id="email" size="26"
									onblur="validateNameContact();" class="textField"
									value="<c:out default="" value="${parents.email}" />">
									

							<td class="alignRight">Notes &nbsp;</td>
							<td width="28%"><label> <input name="remarksadditional" style="font-size: 16px;"
									type="text" class="textField" id="remarksadditional" size="26"
									value="<c:out default="" value="${parents.remarks}" />">
							</label></td>

						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						
	
								<tr>
									<td></td>
									<td></td>
									<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="nexttab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Next</a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
									<a class="prevtab" style="font-weight: bold;color: #325F6D;font-size: 13px" href="#">Previous</a>
									</td>
								</tr>
								

								<tr>
									<td><br /></td>
								</tr>	
							<tr>
									
									<td></td>
									<td></td>
									<td>
										
										<button id="updatetwo" onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();" onfocus="validateNameContact();validateFatherName();">Update</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo">Cancel</button>

									</td>


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
				
						<table   width="80%" border="0" align="center" id="table1">
								<tr>
										<td><br><br><br><br></td>
										
									</tr>
									
						<tr>
							
							<td class="alignRight">Transfer certificate No.&nbsp;</td>

							<td width="28%"><label><input name="tcno"
									type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${student.nooftc}"/>"
									id="tcno" size="26">  

							</label></td>
							<td class="alignRight">Date of transfer certificate&nbsp;</td>

							<td width="28%"><label> <input
									name="dateoftc" type="text" class="textField" autocomplete="false" style="font-size: 16px;"
									value="<fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/>"
									id="dateoftc" size="26"
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

							<td class="alignRight">Previous School Name
								&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="lastschool" type="text" class="textField" style="font-size: 16px;"
									value="<c:out default="" value="${student.schoollastattended}" />"
									id="lastschool" size="26" data-validate="validate(required)">
							</label></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<%-- <tr>
							<td  class="alignRight">Languages Studied &nbsp;</td>

							<td><label> <input
									name="languagesstudied" type="text" class="textField"
									value="<c:out default="" value="${student.languagesstudied}" />"
									id="languagesstudied" size="26" data-validate="validate(required)">

							</label></td>
							
							<td  class="alignRight">Core Subjects Studied&nbsp;</td>
							<td align="left"><label> <input name="progress"
									type="text" class="textField"
									value="<c:out default="" value="${student.subsequentprogress}" />"
									id="progress" size="26" data-validate="validate(required)">

							</label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
						<tr>
							<td  class="alignRight">Previous School Medium of
								Instruction&nbsp;</td>

							<td><label> <select name="mediumofinstruction"
									id="mediumofinstruction" style="width: 240px">
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
							
							<td class="alignRight">Previous School Type&nbsp;</td>

							<td><label> <select name="previousschooltype"
									id="previousschooltype" style="width: 230px">
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
 --%>

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
				
				<%-- <div id="tabs-6">

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


									<button id="updatethree" class="update"
										onmouseover="validateNameContact();validateFatherName();validateAdmissionNumber();"
										onfocus="validateNameContact();validateFatherName();">Update</button>

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

						</div> --%>
	
	
					 <div id="tabs-6">

							<div>
								<br><br><br>
								<table class="educationtable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Educational Qualification</th>
											<th class="educationtableheaderdescription">Name of the Board</th>
											<th class="educationtableheaderdescription">Year of Passing</th>
											<th class="educationtableheaderdescription">No. of Attempts</th>
											<th class="educationtableheaderdescription">Total Marks Scored</th>
											<th class="educationtableheaderdescription">% Secured</th>
										
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												SSLC / 10<sup>th</sup> 
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="tenthboard" type="text" class="myclass"
									value="<c:out default="" value="${student.tenthboard}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="tenthboard" size="26"> 
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
											<input
									name="tenthyearofpassing" type="text" class="myclass"
									value="<c:out default="" value="${student.tenthyearofpassing}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="tenthyearofpassing" size="5">
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="tenthnoofattempts" type="text" class="myclass"
									value="<c:out default="" value="${student.tenthnoofattempts}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="tenthnoofattempts" size="5">
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="tenthtotalmarkssecured" type="text" class="myclass"
									value="<c:out default="" value="${student.tenthtotalmarkssecured}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="tenthtotalmarkssecured" size="5">
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="tenthpercentage" type="text" class="myclass"
									value="<c:out default="" value="${student.tenthpercentage}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="tenthpercentage" size="5"> 
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												 P.U.C / 12<sup>th</sup>  / Inter 
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="twelthboard" type="text" class="myclass"
									value="<c:out default="" value="${student.twelthboard}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="twelthboard" size="26"> 
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="twelthyearofpassing" type="text" class="myclass"
									value="<c:out default="" value="${student.twelthyearofpassing}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="twelthyearofpassing" size="5">  
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="twelthnoofattempts" type="text" class="myclass"
									value="<c:out default="" value="${student.twelthnoofattempts}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="twelthnoofattempts" size="5">  
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="twelthtotalmarkssecured" type="text" class="myclass"
									value="<c:out default="" value="${student.twelthtotalmarkssecured}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="twelthtotalmarkssecured" size="5"> 
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input
									name="twelthpercentage" type="text" class="myclass"
									value="<c:out default="" value="${student.twelthpercentage}" />"
									style="text-transform:uppercase;height: 30px;font-size: 13px;font-weight: bold;border-radius: 5px;font-size: 16px"
									id="twelthpercentage" size="5">  
											</td>
										</tr>
									</tbody>
								
								</table>
								
								
								<div><p class="photocopy">Photocopies of the Certificate Attached</p></div>
								<table class="certificatetable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Sr.No.</th>
											<th class="educationtableheaderdescription">Certificate</th>
											<th class="educationtableheaderdescription">Yes/No</th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>1.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>10<sup>th</sup> Marks Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="markscard10" id="markscard10"
												 ${student.markscard10 == 'yes' ? 'checked' : ''} />
											</td>
											
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>2.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>PUC / 12<sup>th</sup> Marks Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="markscard12" id="markscard12"
												 ${student.markscard12 == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>3.</p>
											</td>
												<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>Migration Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="certificatemigration" id="certificatemigration"
												 ${student.certificatemigration == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>4.</p>
											</td>	
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>Eligibility Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="eligibilitycertificate" id="eligibilitycertificate"
												 ${student.eligibilitycertificate == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
									
									</tbody>
									
									</table>
									
									<table class="certificatetable">
									<thead class="educationtableheaderdescription">
										<tr class="educationtableheaderdescription">
											<th class="educationtableheaderdescription">Sr.No.</th>
											<th class="educationtableheaderdescription">Certificate</th>
											<th class="educationtableheaderdescription">Yes/No</th>
										</tr>
									</thead>
									
									<tbody>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>5.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;padding-top: 10px;padding-bottom: 9px;">
												<p>Transfer Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="transfercertificate" id="transfercertificate"
												 ${student.transfercertificate == 'yes' ? 'checked' : ''} />
											</td>
											
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>6.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;padding-top: 10px;padding-bottom: 9px;">
												<p>Conduct Certificate</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="conductcertificate" id="conductcertificate"
												 ${student.conductcertificate == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>7.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>Aadhaar Card</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="adharcard" id="adharcard"
												 ${student.adharcard == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
										<tr>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>8.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<p>Passport Size Photos-6 nos.</p>
											</td>
											<td  class="educationtableheaderdescription" style="font-weight: bold;">
												<input type="checkbox" value="yes" name="passportsizephotos" id="passportsizephotos"
												 ${student.passportsizephotos == 'yes' ? 'checked' : ''} />
											</td>
										</tr>
									
									</tbody>
									
									</table>
									
									<table align="center">
									<tr>
										<td>
											<br>
										</td>
									</tr>
									
									<tr>
									<!-- <td class="alignRight">&nbsp;</td> -->
										<td></td>
										
									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										class="prevtab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Previous</a></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>
						

									<tr>
										<td></td>
										<td align="center">
											<button id="updatesix" class="update" name="savestudent">Update</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
											<button id="cancelsix" class="cancel">Cancel</button>

										</td>


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
								form1.action = "Controller?process=StudentProcess&action=viewAll";
								form1.submit();
							}

							function updateStudent() {
								
								var form1 = document.getElementById("form1");
								if(form1.checkValidity()) {
									form1.action = "Controller?process=StudentProcess&action=updateStudent";
									form1.submit();
								  }
							}
						</script>
</body>
</html>

