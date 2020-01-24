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
	text-align: right;
	vertical-align: middle;
	font-weight: bold;
}

.alignLeft {
	font-family: Tahoma;
	font-size: 13px;
	font-style: normal;
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
.dataText {
                border-radius:3px;
                font-family: Tahoma;
                color: black;
                font-size: 13px;
                letter-spacing: normal;
                text-align: center;
                background-color: #E3EFFF;

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

<script type="text/javascript">
        
        
        $(function() {
            
            var addExamButtonID="#addQualifyingexam";
            var removeExamButtonID="#removeQualifyingexam";
            $( addExamButtonID )
            .button({
                icons: {
                    primary: "ui-icon-plus"
                }
            })
            .click(function() {
                addRow();
                return false;
            });
            $(removeExamButtonID)
            .button({
                icons: {
                    primary: "ui-icon-minus"
                }
            })
            .click(function() {
                deleteRow('dataTable');
                return false;
            });            

        });
        
    function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' name = 'examqualification' value="+rowCount+" id=exam_"+rowCount+" checked required/></td>";
        var col2="<td class='dataTextInActive'><input type='text' name='examname' id=exam_name_"+rowCount+" /></td>";
 	    var col3="<td class='dataTextInActive'><input type='text' name='boardoruni'  id=boardoruni_"+rowCount+"/></td>";
        var col4="<td class='dataTextInActive'><input type='text' name='passingyear' id=passingyear_"+rowCount+" /></td>";
        var col5="<td class='dataTextInActive'><input type='text' name='percentage' id=percentage_"+rowCount+" /></td>";
        var col6="<td class='dataTextInActive'><input type='text' name='remarks'  id=remarks_"+rowCount+" /></td>";
        /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+col6+"</tr>");
        $(function() {
            $("#dataTable").find('tbody').append(newRow);
        });
    }
    
    function selectAllRow(tableID){
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        if(rowCount==1){
            var row = table.rows[0];
            var chkbox = row.cells[0].childNodes[0];
            chkbox.checked=false;
            alert('No records to select');
        }
        for(var i=1; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            chkbox.checked=true;
        }
    }
    
    function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            if(rowCount==1){
                alert('No records to delete');
            }
            for(var i=1; i<=rowCount-1; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
           
            //$('#grandTotalAmount').val(0);
        }catch(e) {
            alert(e);
        }
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
	<form action="Controller?process=PersonalProcess&action=viewAll"
		id="form1" method="POST" enctype="multipart/form-data">
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Student Details</a></li>
					<!-- <li><a href="#tabs-5">Previous School Details</a></li> -->
					<li><a href="#tabs-2">Parent's Details</a></li>
					<li><a href="#tabs-3">Upload Photo</a></li>
					<!-- <li><a href="#tabs-4">Additional Details</a></li> -->
					<li><a href="#tabs-6">Bank Details</a></li>
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
				
					<table border="0" align="center" cellpadding="2" id="table1">


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
							<td><label> <input name="admnno" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" class="textField" required
									value="<c:out default="" value="${student.admissionnumber}" />"
									id="admnno" size="30" data-validate="validate(required)">

							</label></td>
							
							<td  class="alignRight">Student Name &nbsp;</td>
							<td ><input type="hidden" name="id" id="id"
								value="<c:out value="${student.sid}" />" /><input type="hidden" name="studentexternalid" id="studentexternalid"
								value="<c:out value="${student.studentexternalid}" />" /> <input type="hidden" name="degreeid" id="degreeid"
								value="<c:out value="${student.degreedetails.iddegreedetails}" />" />  <label> <input
									name="name" type="text" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required
									value="<c:out value="${student.name}" />" class="textField" 
									id="name" size="30" data-validate="validate(required)">
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						


						<tr>
							<td class="alignRight">Date Of Birth &nbsp;</td>
							<td ><label> <input name="dateofbirth" autocomplete="false" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" value="<fmt:formatDate value="${student.dateofbirth}" pattern="dd/MM/yyyy"/>"
									class="textField" id="datepicker" size="30" autocomplete="false" readonly="readonly"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
									
									
							</label></td>

							<td  class="alignRight">Age&nbsp;</td>

							<td><label> <input name="age"
									type="text" class="textField" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
							

							<td  class="alignRight">Gender &nbsp;</td>

							<td  class="alignLeft">Male<input type="checkbox" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
								value="Male" name="gender" id="yes:male" onclick="yesCheck(this.id);"
								${student.gender == 'Male' ? 'checked' : ''} />&nbsp;
								&nbsp;Female<input type="checkbox" value="Female" name="gender"
								id="no:male" onclick="noCheck(this.id);" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
								${student.gender == 'Female' ? 'checked' : ''} />

							</td>
							
							
							<td  class="alignRight">Date Of Admission &nbsp;</td>

							<td><label> <input
									name="dateofadmission" type="text" class="textField" autocomplete="false" readonly="readonly"
									value="<fmt:formatDate value="${student.admissiondate}" pattern="dd/MM/yyyy"/>"
									id="dateofadmission" size="30" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
							<td  class="alignRight">Course&nbsp;</td>

							<td >
							
							<label> 
									<select name="classsec" id="classsec" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;width: 260px;">
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
							
							<%-- <select name="secstudying" id="secstudying" 
									style="width: 80px;visibility: hidden;">
										<option selected>${secstudying}</option>
										<option></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.section != '')}">
											<option value="${classdetailslist.section}">
												<c:out value="${classdetailslist.section}" />
											</option>
										</c:if>	
										</c:forEach>
								</select> --%>
							</label>
							
							</td>


		
							<td  class="alignRight">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory" 
									id="socialcategory" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;width: 260px;">
										<option>General</option>
										<option>OBC</option>
										<option>SC</option>
										<option>ST</option>
								</select>

							</label></td>
							
							<!-- <td width="20%" class="alignRight">Admitted in class &nbsp;</td> -->

							<td >
							<label> 
								<select name="admclass" id="admclass"
									style="width: 115px;visibility: hidden;">
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
									style="width: 80px;visibility: hidden;">
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

							<td  class="alignRight">Remarks&nbsp;</td>

							<td><label> <input name="remarks"
									type="text" class="textField" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									value="<c:out default="" value="${student.remarks}" />"
									id="remarks" size="30">

							</label></td>
							
							<td class="alignRight">Created Date &nbsp;</td>
							<td ><label> <input name="createddate" readonly="readonly" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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
                        	<td class="alignRight"></td>
							<td class="alignRight">Documents Submitted</td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>
						
                        
                        <tr>
							<!-- <td class="alignLeft">Documents Submitted</td> -->
							<td class="alignRight"></td>
							<td  height="30" class="alignLeft">
								
							<input type="checkbox" value="1" ${student.degreedetails.exampassedyear == '1' ? 'checked' : ''} name="highschoolmarksheet" id="highschoolmarksheet"/>High School Mark Sheet<br><br>	
							<input type="checkbox" value="1" ${student.degreedetails.exampassedregno == '1' ? 'checked' : ''} name="highersecondarymarksheet" id="highersecondarymarksheet"/>Higher Secondary Mark Sheet<br><br>
							<input type="checkbox" value="1" ${student.degreedetails.exampassedresultwithclass == '1' ? 'checked' : ''} name="graduationmarksheet" id="graduationmarksheet" />Graduation Mark Sheet <br><br>
							<input type="checkbox" value="1" ${student.degreedetails.pumediuminstruction == '1' ? 'checked' : ''} name="postgraduationmarksheet" id="postgraduationmarksheet"/>Post Graduation Mark Sheet <br><br>
							<input type="checkbox" value="1" ${student.degreedetails.subjectsqualifingexampartone == '1' ? 'checked' : ''} name="diplomamarksheet" id="diplomamarksheet"/>Diploma Mark Sheet<br><br>
							<input type="checkbox" value="1" ${student.degreedetails.subjectsqualifingexamparttwo == '1' ? 'checked' : ''} name="charactercertificate" id="charactercertificate"/>Character Certificate
							
							</td>
							
							<td class="alignRight"></td>
							
							<td height="30" class="alignLeft">
								<input type="checkbox" value="1" ${student.degreedetails.subjectsdegreecoursepartone == '1' ? 'checked' : ''} name="transfercertificate" id="transfercertificate"/>Transfer Certificate<br><br>	
								<input type="checkbox" value="1" ${student.degreedetails.subjectsdegreecourseparttwo == '1' ? 'checked' : ''} name="migrationprovisionalcertificate" id="migrationprovisionalcertificate"/>Migration/Provisional Certificate<br><br>
								<input type="checkbox" value="1" ${student.degreedetails.proficiencysports == '1' ? 'checked' : ''} name="domicilecertificate" id="domicilecertificate" />Domicile Certificate<br><br>
								<input type="checkbox" value="1" ${student.degreedetails.extracurricular == '1' ? 'checked' : ''} name="castecertificate" id="castecertificate"/>Caste Certificate <br><br>
								<input type="checkbox" value="1" ${student.degreedetails.areyouemployee == '1' ? 'checked' : ''} name="incomecertificate" id="incomecertificate"/>Income Certificate<br><br>
								<input type="checkbox" value="1" ${student.degreedetails.pumarkscard == '1' ? 'checked' : ''} name="othercertificate" id="othercertificate"/>Other Certificate
							</td>
							
						</tr>
						
							<tr>
							<td><br /></td>
						</tr>
						<tr>

							<td><br /></td>
						</tr>

					</table>


<div>
						<label class="alignRight">Qualifying Exam:</label>
						
						<button id="addQualifyingexam">Add</button>
						<button id="removeQualifyingexam">Remove</button>
						
						<TABLE id="dataTable" width="100%" border="0">
						<thead>
							<tr>
								<td class="headerText">Sl.No.</td>
								<td class="headerText">Name of Exam</td>
								<td class="headerText">Board/University</td>
								<td class="headerText">Passing Year</td>
								<td class="headerText">Percentage</td>
								<td class="headerText">Remarks</td>



							</tr>
						</thead>
						<tbody>
							
							<c:forEach items="${qualifyingexamdetails}" var="examdetails" varStatus="status">
									<tr class="dataText">
									<td>${status.index+1}</td>
									<td>${examdetails.exampassedresultwithclass}</td>
									<td>${examdetails.optionalsubjects}</td>
									<td>${examdetails.exampassedyear}</td>
									<td>${examdetails.exampassedregno}</td>
									<td>${examdetails.compulsorysubjects}</td>
									</tr>							
							
							</c:forEach>
						</tbody>
					</TABLE>                    
                    
                    </div>



					<table id="table2" width="30%" border="0" align="center">
<tr>

									<td><br /></td>
								</tr>
								
								<tr>

									<td align="center">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
							<td><br />
							<input type="file" name="fileToUpload" id="fileToUpload" accept="image/*" >
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


						<%-- <div id="tabs-4">
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
											size="36" onblur="validateName();"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td width="30%" class="alignRight">Date of leaving the school&nbsp;</td>
									<td width="12%" align="left"><label> <input
									name="dateofleaving" type="text" class="textField" autocomplete="false"
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

									<td  class="alignRight">Reason for leaving &nbsp;</td>

									<td ><label> <input name="reasonforleaving"
											type="text" class="textField" id="reasonforleaving" size="36"
											value="<c:out default="" value="${student.reasonleaving}" />"
											onkeypress="return validateContactNum(this);">

									</label></td>



									<td  class="alignRight">No. & date of transfer certificate issued&nbsp;</td>

									<td ><label> <input name="notcissued"
									type="text" class="textField" id="notcissued" size="36" value="<c:out default="" value="${student.notcissued}" />"
									><input
									name="dateoftcissued" type="text" class="textField"  autocomplete="false"
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
							
							</div> --%>
							
							
							

				<div id="tabs-2">
					<table  id="table1" align="center">
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
							<td  class="alignRight">Father's Name* &nbsp;</td>
							<td><input type="hidden"
								name="idparents" id="idparents"
								value="<c:out value="${parents.pid}" />" /> <label> <input
									name="fathersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required
									value="<c:out default="" value="${parents.fathersname}" />"">
									<!-- onkeyup="check(this.value);"  -->
							</label></td>

							<td class="alignRight">Mother's Name* &nbsp;</td>
							<td><label> <input
									name="mothersname" type="text" class="myclass" id="name"
									size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;" required
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
						
						<%-- <tr>
							<td width="30%" class="alignRight">Father's Qualification &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="fathersqualification" type="text" class="myclass" id="fathersqualification"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.fathersqualification}" />">
							</label></td>

							<td width="30%" class="alignRight">Mother's Qualification &nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="mothersqualification" type="text" class="myclass" id="mothersqualification"
									size="36" style="text-transform:uppercase"
									value="<c:out default="" value="${parents.mothersqualification}" />">
							</label></td>


						</tr>



						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr> --%>
						<tr>
						<td class="alignRight">Fathers Occupation&nbsp;</td>
							<td ><label> <input style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									name="fatherscastecertno" type="text" class="textField" value="${parents.fatherscastecertno}"
									id="fatherscastecertno" size="36">

							</label></td>


						<td  class="alignRight">Annual Income &nbsp;</td>

							<td ><label> <input name="annualincome"
									type="text" class="textField" id="annualincome" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									value="<c:out default="" value="${parents.parentsannualincome}" />">

							</label></td>
					
						<%-- <td width="20%" class="alignRight">Mothers Occupation&nbsp;</td>
							<td ><label> <input name="motherscastecertno"
									type="text" class="textField" id="motherscastecertno" value="${parents.motherscastecertno}" size="36">

							</label></td> --%>
						</tr>
						
												<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<%-- <tr>

							<td  class="alignRight">Guardian's name & address
								&nbsp;</td>

							<td ><label> <input name="guardian"
									type="text" class="textField" id="guardian" size="36"
									value="<c:out default="" value="${student.guardiandetails}" />">

							</label></td>



							<td  class="alignRight">Annual Income &nbsp;</td>

							<td ><label> <input name="annualincome"
									type="text" class="textField" id="annualincome" size="36"
									value="<c:out default="" value="${parents.parentsannualincome}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr> --%>

						<tr>

							<td  class="alignRight">Contact Number* &nbsp;</td>

							<td ><label> <input name="contactnumber" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" class="textField" id="contactnumber" size="36" required maxlength="10" minlength="10"
									value="<c:out default="" value="${parents.contactnumber}" />">

							</label></td>



							<td  class="alignRight">CO-Contact Number &nbsp;</td>

							<td ><label> <input
									name="cocontactnumber" type="text" class="textField" maxlength="10" minlength="10"
									id="cocontactnumber" size="36" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									value="<c:out default="" value="${parents.cocontactnumber}" />">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>


						<%-- <tr>
							<td  class="alignRight">Email
								&nbsp;</td>

							<td ><label> <input name="email"
									type="email" class="textField" id="email" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.email}" />">
									

							<td width="20%" class="alignRight">Number Of Dependents
								&nbsp;</td>

							<td ><label> <input name="noofdependents"
									type="text" class="textField" id="noofdependents" size="36"
									onblur="validateNameContact();"
									value="<c:out default="" value="${parents.noofdependents}" />">

							</label></td>

							</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr> --%>
						
						<tr>
							<td  class="alignRight">Permanent Address &nbsp;</td>
							<td ><label> <textarea
										name="permanentaddress" type="text" class="textField"
										id="permanentaddress" rows="4" cols="35"
										value="<c:out default="" value="${parents.addresspermanent}"/>">${parents.addresspermanent}</textarea>
							</label></td>

							<td class="alignRight">Temporary Address &nbsp;</td>
							<td ><label> <textarea
										name="temporaryaddress"
										value="<c:out default="" value="${parents.addresstemporary}" />"
										type="text" class="textField" id="temporaryaddress" rows="4"
										cols="35">${parents.addresstemporary}</textarea>
							</label></td>
						</tr>



						<tr>
							<td><br></td>
						</tr>
						<tr>
							<td><br></td>
						</tr>


						<tr>
							
							<td class="alignRight">Notes &nbsp;</td>
							<td > <input name="remarksadditional" style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									type="text" class="textField" id="remarksadditional" size="36"
									value="<c:out default="" value="${parents.remarks}" />">
							</td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						
	
								<tr align="center">
									<td class="alignRight"> &nbsp;</td>
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
									
									
									<td class="alignRight"> &nbsp;</td>

									<td align="center">
									
										
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
						</table>

						</div>

							<%-- <div id="tabs-5">
				
						<table style="width:70%;" align="center">
								<tr>

										<td><br /></td>
									</tr>
									
						<tr>
							<td class="alignRight">Transfer certificate No.&nbsp;</td>

							<td><label><input name="tcno"
									type="text" class="textField"
									value="<c:out default="" value="${student.nooftc}"/>"
									id="tcno" size="36">  

							</label></td>
							<td class="alignRight">Date of transfer certificate&nbsp;</td>

							<td><label> <input
									name="dateoftc" type="text" class="textField" autocomplete="false"
									value="<fmt:formatDate value="${student.dateoftc}" pattern="dd/MM/yyyy"/>"
									id="dateoftc" size="36"
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
							<td class="alignRight">Previous Class Studied &nbsp;</td>

							<td><label> <select name="lastclass" id="lastclass"
									style="width: 230px;">
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

							<td class="alignRight">Previous School Name
								&nbsp;</td>
							<td width="12%" align="left"><label> <input
									name="lastschool" type="text" class="textField"
									value="<c:out default="" value="${student.schoollastattended}" />"
									id="lastschool" size="36" data-validate="validate(required)">
							</label></td>
						</tr>


						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td  class="alignRight">Languages Studied &nbsp;</td>

							<td><label> <input
									name="languagesstudied" type="text" class="textField"
									value="<c:out default="" value="${student.languagesstudied}" />"
									id="languagesstudied" size="30" data-validate="validate(required)">

							</label></td>
							
							<td  class="alignRight">Core Subjects Studied&nbsp;</td>
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
						
						<tr>

								<tr>
								<td></td>
								<td>
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

