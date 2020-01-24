<%-- 
    Document   : add Student
    Created on : Jun 17, 2013, 4:17:40 PM
    Author     : Musaib
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<%@page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML5>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Student</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">

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

.alignRightHead {
	font-family: Tahoma;
	font-size: 12px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	font-weight: bold;
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
-->
</style>

<script type="text/javascript">
	function dropdown() {
		var listitem = document.getElementById("city");
		var listitemtext = listitem.options[listitem.selectedIndex].text;

		if (listitemtext == "Add New") {
			document.getElementById("city").style.display = "none";
			document.getElementById("addcity").style.display = '';
		}
	}

	

	function dropdownadmclass() {

		var classlistitem = document.getElementById("admclass");
		var classlistitemtext = classlistitem.options[classlistitem.selectedIndex].text;

		if (classlistitemtext == "Add New") {
			document.getElementById("admclass").style.display = "none";
			document.getElementById("admclassE").style.display = '';
			document.getElementById("addsecE").style.display = '';
		}

	}

</script>




<script type="text/javascript" src="js/datetimepicker_css.js"></script>

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
		$("#datepicker1").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#datepicker1").datepicker("option", "showAnim", $(this).val());
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

		
		$(".save").button().click(function() {
			addStudent();

		});

		$(".cancel").button().click(function() {
			Cancel();

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


<script type="text/javascript">
	function check(value) {

		xmlHttp = GetXmlHttpObject()
		var url = "check.jsp";
		url = url + "?name=" + value;
		xmlHttp.onreadystatechange = stateChanged
		xmlHttp.open("GET", url, true)
		xmlHttp.send(null)
	}
	function stateChanged() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			var showdata = xmlHttp.responseText;
			document.getElementById("mydiv").innerHTML = showdata;
		}
	}
	function GetXmlHttpObject() {
		var xmlHttp = null;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return xmlHttp;
	}


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

<script type="text/javascript">
	
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
        var col6="<td class='dataTextInActive'><input type='text' name='examremarks'  id=remarks_"+rowCount+" /></td>";
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
	if (session.getAttribute("userAuth") == null) {
		response.sendRedirect("Controller?process=UserProcess&action=sessionTimeOut");
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
	<form id="form1" 
		method="post" enctype="multipart/form-data">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		%>
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div>
			<div id="tabs">
				<ul>
					<li><a href="#fragment-1">Student's Details</a></li>
					<li><a href="#fragment-2">Parent's Details</a></li>
					<li><a href="#fragment-3">Upload Photo</a></li>
					<li><a href="#fragment-6">Bank Details</a></li>
				</ul>



				<div id="fragment-1">
					<table border="0" align="center" id="table1">
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignRight">Admission Number* &nbsp;</td>
							<td ><label> <input name="admnno" required
									type="text" class="myclass" id="admnno" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;">

							</label></td>
							<td class="alignRight">Student Name* &nbsp;</td>
							<td align="left"><label> <input
									name="name" type="text" class="myclass" id="name" size="30" required
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									required>
							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						

						<tr>
							<td class="alignRight">Date Of Birth &nbsp;</td>
							<td ><label> <input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="30" autocomplete="false" readonly="readonly"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
							</label></td>

							<td class="alignRight">Age &nbsp;</td>
							<td align="left"><label> <input
									name="age" type="text" class="myclass" id="age" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
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

							<td class="alignRight">Gender &nbsp;</td>
							<td height="30" class="alignLeft">&nbsp;Male<input
								type="checkbox" value="Male" name="gender" id="yes:male"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="no:male"
								onclick="noCheck(this.id)" />

							</td>

						<td class="alignRight">Date of admission&nbsp;</td>
							<td ><label><input name="dateofadmission" autocomplete="false" readonly="readonly"
									type="text" class="myclass" id="dateofadmission" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									data-validate="validate(required)"> </label></td>
									
									

						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>


						<tr>


							<td class="alignRight">Course&nbsp;</td>
							<td ><label> <select name="addclass"
									id="addclass" style="width: 256px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label></td>
								
								<td  class="alignRight">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory"
									id="socialcategory" style="width: 256px">
										<option selected></option>
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
						
							<td class="alignRight">Remarks &nbsp;</td>
							<td ><label> <input name="remarks"
									type="text" class="myclass" id="remarks" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>
							
							<td class="alignRight">Created Date &nbsp;</td>
							<td ><label> <input name="createddate" readonly="readonly"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepickerCD" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"s
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
							<td class="alignRight">
								
							</td>
							
							<td class="alignRight">
								Documents Submitted
							</td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignRight"></td>
							
							<td  height="30" class="alignLeft">
								
							<input type="checkbox" value="1" name="highschoolmarksheet" id="highschoolmarksheet"/>High School Mark Sheet<br><br>	
							<input type="checkbox" value="1" name="highersecondarymarksheet" id="highersecondarymarksheet"/>Higher Secondary Mark Sheet<br><br>
							<input type="checkbox" value="1" name="graduationmarksheet" id="graduationmarksheet" />Graduation Mark Sheet <br><br>
							<input type="checkbox" value="1" name="postgraduationmarksheet" id="postgraduationmarksheet"/>Post Graduation Mark Sheet <br><br>
							<input type="checkbox" value="1" name="diplomamarksheet" id="diplomamarksheet"/>Diploma Mark Sheet<br><br>
							<input type="checkbox" value="1" name="charactercertificate" id="charactercertificate"/>Character Certificate
							
							</td>
							
							<td  class="alignRight"></td>
							
							<td height="30" class="alignLeft">
								<input type="checkbox" value="1" name="transfercertificate" id="transfercertificate"/>Transfer Certificate<br><br>	
								<input type="checkbox" value="1" name="migrationprovisionalcertificate" id="migrationprovisionalcertificate"/>Migration/Provisional Certificate<br><br>
								<input type="checkbox" value="1" name="domicilecertificate" id="domicilecertificate" />Domicile Certificate<br><br>
								<input type="checkbox" value="1" name="castecertificate" id="castecertificate"/>Caste Certificate <br><br>
								<input type="checkbox" value="1" name="incomecertificate" id="incomecertificate"/>Income Certificate<br><br>
								<input type="checkbox" value="1" name="othercertificate" id="othercertificate"/>Other Certificate
							</td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
				</table>
				
				<div >

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

						</tbody>
					</TABLE>
					
					</div>
				
				
						<div>
							<table width="100%">
								<tr>

									<td><br /></td>
								</tr>

								<tr>

									<td align="center"><a class="nexttab"
										style="font-weight: bold; color: #325F6D; font-size: 13px"
										href="#">Next</a></td>
								</tr>

								<tr>

									<td><br /></td>
								</tr>

								<tr>
									<td align="center">


										<button id="savestudents" class="save" name="savestudent">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="cancel" class="cancel">Cancel</button>

									</td>


								</tr>
							</table>

						</div>
						
						
				</div>


						<div id="fragment-3">
							<table width="100%" border="0" align="center">
								<tr>
									<td><br /> <input type="file" name="fileToUpload"
										id="fileToUpload" accept="image/*"></td>
								</tr>


							</table>



							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>

									<tr>

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
										<td align="center">


											<button id="savethree" class="save" name="savestudent">Save</button>

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


						


						<div id="fragment-2">
							<table border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignRight">Father's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="fathersname" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fathersname" size="30"
											required> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignRight">Mother's Name* &nbsp;</td>
									<td align="left"><label> <input
											name="mothersname" type="text" class="myclass" id="name" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											size="30"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								
								<tr>
									<td class="alignRight">Fathers Occupation
										&nbsp;</td>
									<td><label> <input
											name="fatherscastecertno" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="fatherscastecertno" size="30">
									</label></td>
									
									<td class="alignRight">Annual Income &nbsp;</td>

									<td ><label> <input
											name="parentsannualincome" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="parentsannualincome" size="30"
											onkeyup="numberWithCommas(this);">

									</label></td>

								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="contactnumber" size="30" maxlength="10" minlength="10">

									</label></td>



									<td class="alignRight">Co-Contact Number
										&nbsp;</td>

									<td><label> <input
											name="cocontactnumber" type="text" class="myclass"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											id="cocontactnumber" size="30" maxlength="10" minlength="10">

									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

								<td class="alignRight">Permanent Address &nbsp;</td>

								<td ><label> <textarea
											name="permanentaddress" type="text" 
											id="permanentaddress" rows="4" cols="40"
											></textarea>

								</label></td>


								<td class="alignRight">Temporary Address &nbsp;</td>
								<td ><label> <textarea
											name="temporaryaddress" type="text" 
											id="temporaryaddress" rows="4" cols="40"></textarea>
								</label></td>
								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignRight">Notes &nbsp;</td>
									<td ><label> <input name="remarksadditional"
											type="text" class="myclass" id="remarksadditional" size="30"
											style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
											>
									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>


								<tr>
									<td><br /></td>
								</tr>



								<tr align="center">
									<td class="alignRight">&nbsp;</td>
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
								<tr align="center">
									<td class="alignRight">&nbsp;</td>
									<td align="center">
										<button id="savetwo"  class="save" name="savestudent">Save</button>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="canceltwo" class="cancel">Cancel</button>
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
						
						
						
						<div id="fragment-6">

							<div>
								<table width="100%">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignRight">Bank Name &nbsp;</td>
							<td><label> <input name="bankname" type="text" class="myclass" id="bankname" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						
						<tr>
							<td class="alignRight">Bank IFSC Code&nbsp;</td>
							<td><label> <input name="bankifsc" type="text" class="myclass" id="bankifsc" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
							</label></td>
						</tr>
								<tr>
										<td><br /></td>
								</tr>
						<tr>
							<td class="alignRight">Account No. &nbsp;</td>
							<td><label> <input name="accno" type="text" class="myclass" id="accno" size="30"
									style="text-transform:uppercase;height: 18px;font-size: 13px;font-weight: bold;"
									>
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
										<td align="left">


											<button id="savesix" class="save" name="savestudent">Save</button>

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
					</table>
				</div>


			</div>
		</div>





	</form>
	<script type="text/javascript">
							function addStudent() {
								var form1 = document.getElementById("form1");
								if(form1.checkValidity()) {
									form1.savestudent.disabled = true;
									form1.action = "Controller?process=StudentProcess&action=AddStudent";
									form1.submit();
								  }
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "Controller?process=StudentProcess&action=viewAll";
								form1.submit();
							}

							
						</script>
</body>
</html>


