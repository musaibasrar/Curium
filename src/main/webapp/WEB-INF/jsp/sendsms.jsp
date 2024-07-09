<%-- 
    Document   : send SMS
    Created on : JUN 22, 2018, 4:17:40 PM
    Author     : Musaib
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEND SMS</title>
	<style type="text/css" title="currentStyle">
            @import "/shatabdi/css/dataTable/css/demo_page.css";
            @import "/shatabdi/css/dataTable/css/jquery.dataTables.css";
        </style>
        <link rel="stylesheet" href="/shatabdi/css/datePicker/jquery-ui-1.8.17.custom.css">
        <link rel="stylesheet" href="/shatabdi/css/datePicker/demos.css">
<link rel="stylesheet" href="/shatabdi/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/shatabdi/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/shatabdi/js/datePicker/jquery-1.7.1.js"></script>
<script src="/shatabdi/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/shatabdi/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/shatabdi/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/shatabdi/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/shatabdi/js/datePicker/ui/sliderAccess.js"></script>
<script src="/shatabdi/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/shatabdi/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/shatabdi/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/shatabdi/css/datePicker/demos.css">

        <script type="text/javascript" language="javascript" src="/shatabdi/js/dataTable/jquery.dataTables.js"></script>
 	
        
<style type="text/css" >
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
                height: 22px;
                vertical-align: middle;
                text-align: center;

            }
            .dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .dataTextInActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration:none;
            }
            .dataTextActive {
                border-radius:3px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;
                text-decoration: underline;
            }
            .dataTextHidden {
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

            }
            .headerTD{
                border-radius:6px;
                background-color:#4b6a84;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
                height: 22px;
            }
            .footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


            }
            -->
        </style>
        
<style type="text/css">
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
-->
</style>


<script type="text/javascript">
	$(function() {
		
		$("#sendsms").button().click(function() {
			sendSMSAll();

		});

		$("#sendsmsnumbers").button().click(function() {
			sendSMSNumbers();

		});
		
		$("#sendsmsstaff").button().click(function() {
			sendSMSStaff();

		});

		
		$("#reset").button().click(function() {
			 
		});
		
		$("#resetnumbers").button().click(function() {
		
		});
		
		$("#resetstaff").button().click(function() {
			
		});
		
		 $("#numbers").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 44) {
		        //display error message
		        $("#errmsg").html("Digits Only").show().fadeOut("slow");
		               return false;
		    }
		   });
	});
</script>
<script>
	$(function() {
		$("#tabs").tabs();

		$("#messagebody").keyup(function(){
			  $("#count").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecount").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecount").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecount").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecount").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecount").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecount").text("5");
			  }
			});
		
		$("#messagebodynumbers").keyup(function(){
			  $("#countnumbers").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecountnumbers").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecountnumbers").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecountnumbers").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecountnumbers").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecountnumbers").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecountnumbers").text("5");
			  }
			});
		
		$("#messagebodystaff").keyup(function(){
			  $("#countstaff").text($(this).val().length);
			  if($(this).val().length==0){
				  $("#messagecountstaff").text("0");
			  }else if($(this).val().length<160){
				  $("#messagecountstaff").text("1");
			  }else if($(this).val().length>159 && $(this).val().length<=306){
				  $("#messagecountstaff").text("2");
			  }else if ($(this).val().length>306 && $(this).val().length<=453){
				  $("#messagecountstaff").text("3");
			  }else if ($(this).val().length>453 && $(this).val().length<=600){
				  $("#messagecountstaff").text("4");
			  }else if ($(this).val().length>600){
				  $("#messagecountstaff").text("5");
			  }
			});
		
		
	});
	
	
</script>

<script type="text/javascript">
	
$(document).ready(function() {
    // Select change event
    $("select").change(function() {
        var selectedOption = $(this).find("option:selected").attr("value");
        if (selectedOption) {
            $(".box").hide();
            $("." + selectedOption).show();
        } else {
            $(".box").hide();
        }
    }).change();

    // Initialize DataTable
    $('#myTable').dataTable({
        scrollY: "380px",
        paging: true,
        lengthChange: false,
        searching: true,
        ordering: true,
        info: true,
        stateSave: false,
        processing: false,
        serverSide: false,
        autoWidth: false,
        pageLength: 20000,
        columnDefs: [
            { orderable: false, targets: [0] }
        ]
    });
});

	
</script>
</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/shatabdi/UserProcess/sessionTimeOut");
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
	<form method="post"  id="form1">
		
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Students</a></li>
					<!-- <li><a href="#tabs-2">Staff</a></li> -->
					<li><a href="#tabs-3">Selected Numbers</a></li>
				</ul>



				<div id="tabs-1">
					<table  border="0" style="width: auto;height: auto;"  id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						
						<tr>
							<td class="alignRight"><label>Class </label>
							</td><td>
							<label>
							<select name="addclass" id="addclass"
									style="width: 120px">
										<option selected></option>
										<option>ALL</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}" >
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select>

							</label> <label>&nbsp;Sec
									<select name="addsec" id="addsec"
									style=" width: 120px">
										<option selected></option>
										<option>ALL</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>	
										</c:forEach>

								</select></label>
								</td>
						</tr>
							<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td class="alignRight">SMS Template &nbsp;</td>
							<td><label>
								<select name="messagebody" id="messagebody"
									style="width: 120px">
											<option selected></option>
            								<option value="holiday">Holiday</option>
            								<option value="exams">Exams</option> -->
            								<option value="festival">Festival</option>
            								<option value="feesreminder">Fees Reminder</option>
								</select>
							
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
											
						<tr>
							<td class="alignRight">Message&nbsp;</td>
							<td >
								<div class="holiday box">
								 								
								<span style="font-size: 16px;">Dear parents, school will be closed on </span> <span style="font-weight: bold;color: red">Date</span>  <span style="font-size: 16px;">due to</span> <span style="font-weight: bold;color: red">Reason</span>
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="holidayvar1" name="holidayvar1" maxlength="30">
								<br><br>
								<label style="color: red;">Reason:&nbsp;&nbsp;</label><input type="text" id="holidayvar2" name="holidayvar2" maxlength="30">
								<input type="hidden" id="holidayvar3" name="holidayvar3" maxlength="30">
								<input type="hidden" id="holidayvar4" name="holidayvar4" maxlength="30">
								</div>
									
    							<div class="exams box">
    								<span style="font-size: 16px;">Dear Parents,We would like to inform you that the </span> <span style="font-weight: bold;color: red">Exam</span>  <span style="font-size: 16px;">will commence from</span> <span style="font-weight: bold;color: red">Date</span>
								<br><br>
								<label style="color: red;">Exams:&nbsp;</label>&nbsp;<input type="text" id="examsvar1" name="examsvar1" maxlength="30" value="Exams">
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" id="examsvar2" name="examsvar2" maxlength="30">
								<input type="hidden" id="examsvar3" name="examsvar3" maxlength="30">
								<input type="hidden" id="examsvar4" name="examsvar4" maxlength="30">
    							</div>
    							
    							<div class="festival box">
    							<span style="font-size: 16px;">Dear Parents,We would like to wish you, a </span> <span style="font-weight: bold;color: red">Festival Name</span>
								<br><br>
								<label style="color: red;">Festival Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="festivalvar1" name="festivalvar1" maxlength="30">
								<br><br>
								<input type="hidden" id="festivalvar2" name="festivalvar2" maxlength="30">
								<input type="hidden" id="festivalvar3" name="festivalvar3" maxlength="30">
								<input type="hidden" id="festivalvar4" name="festivalvar4" maxlength="30">
								</div>
								
    							<div class="feesreminder box">
    								<span style="font-size: 16px;">Dear parents,kindly note there is a pending fee payment.kindly make the payment before <span style="font-weight: bold;color: red">Date</span>.Please ignore if already paid.</span>
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="feesremindervar1" name="feesremindervar1" maxlength="30">
								<br><br>
								<input type="hidden" id="feesremindervar2" name="feesremindervar2" maxlength="30">
								<input type="hidden" id="feesremindervar3" name="feesremindervar3" maxlength="30">
								<input type="hidden" id="feesremindervar4" name="feesremindervar4" maxlength="30">
    							</div>
							
							</td>
							
						</tr>
						
						<!-- <tr>
						
						<td width="30%" class="alignRight">Count: &nbsp;</td>
							<td width="12%" align="left"><label name="count" id="count" style="color: #325F6D;font-weight: bold;">
							</label></td>
							
						</tr>

						<tr>
							<td width="30%" class="alignRight">No. Of Messages : &nbsp;</td>
							<td width="12%" align="left"><label name="messagecount" id="messagecount" style="color: #325F6D;font-weight: bold;">
							</label></td>
						</tr> -->

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
									<td>

										<button id="sendsms">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="reset">Reset</button>

									</td>


								</tr>
							</table>

						</div>
						</div>
						
						<%-- <div id="tabs-2">
					<table width="100%" border="0" align="center" id="table1">
					
					<tr>
							<td width="20%" class="alignRight"></td>
							<td class="alignRight"></td>
							<td width="30%" class="alignRight">
							<a href="SMSProcess/SMSbalanceCheck" title="Click to check SMS balance">SMS Balance</a>
											: ${smsbalance}
							</td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignRight">Select&nbsp;</td>
							<td width="28%"> <label>Department: <select name="department" id="department"
									style="width: 120px">
										<option selected></option>
										<option>ALL</option>
										<c:forEach items="${listDepartment}" var="listDepartment">
											<option value="${listDepartment.departmentname}" >
												<c:out value="${listDepartment.departmentname}" />
											</option>
										</c:forEach>
								</select>

							</label> </td>
							<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td width="40%" class="alignRight">Message* &nbsp;</td>
							<td width="12%" align="center"><label> <textarea  name="messagebodystaff"
											type="text" class="textField" id="messagebodystaff" rows="6" cols="55"
											></textarea>
							</label></td>
							
						</tr>
						<tr>
						
						<td width="30%" class="alignRight">Count: &nbsp;</td>
							<td width="12%" align="left"><label name="countstaff" id="countstaff" style="color: #325F6D;font-weight: bold;">
							</label></td>
							
						</tr>

						<tr>
							<td width="30%" class="alignRight">No. Of Messages : &nbsp;</td>
							<td width="12%" align="left"><label name="messagecountstaff" id="messagecountstaff" style="color: #325F6D;font-weight: bold;">
							</label></td>
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

										<button id="sendsmsstaff">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="resetstaff">Reset</button>

									</td>


								</tr>
							</table>

						</div> --%>
						
					<div id="tabs-3">
					
						<table width="100%">
                    <tr>
                        <td  class="headerTD">View All Students</td>
                    </tr>

                    

                </table>
                <table   width="100%"  border="0" style="border-color:#4b6a84;"  id="myTable">

                    <thead>
                        <tr>
                            <th class="headerText"><input  type="checkbox" id = "chckHead" /></th>
                            <th title="click to sort" class="headerText">UID</th>
                            <th title="click to sort" class="headerText">Admission Number</th>
                            <th title="click to sort" class="headerText">Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            <th title="click to sort" class="headerText">Class</th>
                            <th title="click to sort" class="headerText">Father's Name&nbsp;</th>
                            <th title="click to sort" class="headerText">Contact Number&nbsp;</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${studentList}" var="Parents">
											
                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${Parents.student.sid}"/>" class = "chcktbl"  name="numbers"  value="<c:out value="${Parents.contactnumber}"/>"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" style="cursor: pointer;" onclick="viewStudentDetails(${Parents.student.sid},${Parents.student.branchid})"><c:out value="${Parents.student.studentexternalid}"/></a></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="/shatabdi/StudentProcess/ViewDetails?id=<c:out value='${Parents.student.sid}'/>&urlbranchid=<c:out value='${Parents.student.branchid}'/>"><c:out value="${Parents.student.admissionnumber}"/></a></td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.student.name}"/></td>
                                <td class="dataText" style="text-transform:uppercase">
                                 <c:forEach var="splt" items="${fn:split(Parents.student.classstudying,'--')}">
						    		${splt} 
								</c:forEach>
                                </td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.fathersname}"/></td>
                                <td class="dataText" style="text-transform:uppercase"><c:out value="${Parents.contactnumber}"/></td>
                                <!-- <fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/>  -->
                                <!-- <td class="dataText"><fmt:formatDate value="${Parents.student.admissiondate}" pattern="yyyy-MM-dd"/></td> -->
                                 

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                						
					<table width="100%" border="0" align="center" id="table1">
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
							<td class="alignRight">SMS Template &nbsp;</td>
							<td><label>
								<select name="messagebodynumber" id="messagebodynumber"
									style="width: 120px">
											<option selected></option>
            								<option value="holidaynumber">Holiday</option>
            								<option value="examsnumber">Exams</option> -->
            								<option value="festivalnumber">Festival</option>
            								<option value="feesremindernumber">Fees Reminder</option>
								</select>
							
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
											
						<tr>
							<td class="alignRight">Message&nbsp;</td>
							<td >
								<div class="holidaynumber box">
								 								
								<span style="font-size: 16px;">Dear parents, school will be closed on </span> <span style="font-weight: bold;color: red">Date</span>  <span style="font-size: 16px;">due to</span> <span style="font-weight: bold;color: red">Reason</span>
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="holidaynumbervar1" name="holidaynumbervar1" maxlength="30">
								<br><br>
								<label style="color: red;">Reason:&nbsp;&nbsp;</label><input type="text" id="holidaynumbervar2" name="holidaynumbervar2" maxlength="30">
								<input type="hidden" id="holidaynumbervar3" name="holidaynumbervar3" maxlength="30">
								<input type="hidden" id="holidaynumbervar4" name="holidaynumbervar4" maxlength="30">
								</div>
									
    							<div class="examsnumber box">
    								<span style="font-size: 16px;">Dear Parents,We would like to inform you that the </span> <span style="font-weight: bold;color: red">Exam</span>  <span style="font-size: 16px;">will commence from</span> <span style="font-weight: bold;color: red">Date</span>
								<br><br>
								<label style="color: red;">Exams:&nbsp;</label>&nbsp;<input type="text" id="examsnumbervar1" name="examsnumbervar1" maxlength="30" value="Exams">
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type="text" id="examsnumbervar2" name="examsnumbervar2" maxlength="30">
								<input type="hidden" id="examsnumbervar3" name="examsnumbervar3" maxlength="30">
								<input type="hidden" id="examsnumbervar4" name="examsnumbervar4" maxlength="30">
    							</div>
    							
    							<div class="festivalnumber box">
    							<span style="font-size: 16px;">Dear Parents,We would like to wish you, a </span> <span style="font-weight: bold;color: red">Festival Name</span>
								<br><br>
								<label style="color: red;">Festival Name:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="festivalnumbervar1" name="festivalnumbervar1" maxlength="30">
								<br><br>
								<input type="hidden" id="festivalnumbervar2" name="festivalnumbervar2" maxlength="30">
								<input type="hidden" id="festivalnumbervar3" name="festivalnumbervar3" maxlength="30">
								<input type="hidden" id="festivalnumbervar4" name="festivalnumbervar4" maxlength="30">
								</div>
								
    							<div class="feesremindernumber box">
    								<span style="font-size: 16px;">Dear parents,kindly note there is a pending fee payment.kindly make the payment before <span style="font-weight: bold;color: red">Date</span>.Please ignore if already paid.</span>
								<br><br>
								<label style="color: red;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>&nbsp;<input type="text" id="feesremindernumbervar1" name="feesremindernumbervar1" maxlength="30">
								<br><br>
								<input type="hidden" id="feesremindernumbervar2" name="feesremindernumbervar2" maxlength="30">
								<input type="hidden" id="feesremindernumbervar3" name="feesremindernumbervar3" maxlength="30">
								<input type="hidden" id="feesremindernumbervar4" name="feesremindernumbervar4" maxlength="30">
    							</div>
							
							</td>
							
						</tr>

						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>
								<tr>
									<td align="center">

										<button id="sendsmsnumbers">Send</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="resetnumbers">Reset</button>

									</td>


								</tr>
							</table>

						</div>

						</div>
						</div>


						</form>
						<script type="text/javascript">
							function sendSMSAll() {
								var form1 = document.getElementById("form1");
								form1.action = "/shatabdi/SMSProcess/sendAllSMS";
								form1.submit();
							}
							
							function sendSMSNumbers() {
								var form1 = document.getElementById("form1");
								form1.action = "/shatabdi/SMSProcess/sendNumbersSMS";
								form1.submit();
							}
							
							function sendSMSStaff() {
								var form1 = document.getElementById("form1");
								form1.action = "/shatabdi/SMSProcess/sendStaffSMS";
								form1.submit();
							}
							
						</script>
</body>
</html>


