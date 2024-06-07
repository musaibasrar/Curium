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
<link rel="stylesheet" href="/noblewisdom/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/noblewisdom/css/validation/jquery.ketchup.css">

<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script src="/noblewisdom/js/datePicker/jquery-1.7.1.js"></script>
<script src="/noblewisdom/js/datePicker/ui/jquery.ui.core.js"></script>
<script src="/noblewisdom/js/datePicker/ui/jquery.ui.widget.js"></script>
<script src="/noblewisdom/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script src="/noblewisdom/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script src="/noblewisdom/js/datePicker/ui/sliderAccess.js"></script>
<script src="/noblewisdom/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
<script src="/noblewisdom/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/noblewisdom/js/datePicker/ui/jquery.ui.button.js"></script>
<link rel="stylesheet" href="/noblewisdom/css/datePicker/demos.css">





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




<script type="text/javascript" src="/noblewisdom/js/datetimepicker_css.js"></script>

<script src="/noblewisdom/JavaScript/actb.js"></script>
<script src="/noblewisdom/JavaScript/common.js"></script>




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
	
	$(function() {
		$("#datepickerDND").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-5:+4"
		});
		$("#anim").change(function() {
			$("#datepickerDND").datepicker("option", "showAnim", $(this).val());
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
		var url = "/check";
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
    
    function Upload() {
        var fileUpload = document.getElementById("fileToUpload");
        if (typeof (fileUpload.files) != "undefined") {
            var size = parseFloat(fileUpload.files[0].size / 1024).toFixed(2);
            if(size>100){
            	alert("File size should not exceed 100KB");
            	document.getElementById("fileToUpload").value='';
            }
        } else {
            alert("Unsupported File");
        }
    }
</script>

<script>
var xmlHttp;
    var count;
    function searchfeecategory() {
    	var addClass=document.getElementById('addclass').value;
    	var yoa=document.getElementById('yearofadmission').value;
			 if (typeof XMLHttpRequest != "undefined") {
				 xmlHttp = new XMLHttpRequest();
	            
	         } else if (window.ActiveXObject) {
	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	             
	         }
			xmlHttp.onreadystatechange = stateChanged;
			xmlHttp.open("GET", "/noblewisdom/FeesProcess/searchfeecategory?classstudying="+addClass+"&yearofadmission="+yoa+"",true);
			xmlHttp.send(null);
		
	}
    
	function stateChanged() {
		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			document.getElementById("feescat").innerHTML = xmlHttp.responseText;
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
    
</script>
<script>
$(function() {
	$('#chckHead').click(function() {
		var length = $('.chcktbl:checked').length;
		var trLength = $('.labelClass').length;
		if (length > 0) {
			$('.chcktbl:checked').attr('checked', false);
			this.checked = false;
		} else {
			if (this.checked == false) {
				$('.chcktbl:checked').attr('checked', false);
			} else {
				$('.chcktbl:not(:checked)').attr('checked', true);
			}
		}
	});
	
	$('.chcktbl').click(function() {
		var length = $('.chcktbl:checked').length;
		var trLength = $('.labelClass').length;
		alert(tdLength);
		if (length > trLength) {
			$('.chcktbl:not(:checked)').attr('disabled', true);
		} else {
			$('.chcktbl:not(:checked)').attr('disabled', false);
		}
	});
});
</script>
<script>
function calculate(value2) {
	var feesCount=document.getElementById("feesCount_"+value2).value;
	//alert("hii", value2);
	 var feesCat=document.getElementById("hiddenfees_amount_"+value2).value;
	 //alert("hii", value2);
     var feesCount=document.getElementById("feesCount_"+value2).value;
     var final1=document.getElementById("hiddenfees_full_amount_"+value2);
     	
     	//var concession = ((feesCat*feesCount)*feesConcession)/100;(% concession)
     	//feesConcession (direct amount)
        //final1.value=feesCat*feesCount;
     	final1.value=feesCat;
         calculateGrandTotal();
   
}
function calculateGrandTotal() {
	
	
    var sum = 0.0;
    var column2 = $('.feesFullAmount')
    jQuery.each(column2,function(){
        sum += parseFloat($(this).val());
    });
    
    $('#feesTotalAmount').val(sum);
}
$(document).ready(function() {
    
    
    $("#dataTable").keyup(function(){
        
        var sum = 0.0;
        var totalSum=0.0;
        var column2 = $('.feesFullAmount')
        jQuery.each(column2,function(){
            sum += parseFloat($(this).val());
        });
        
        $('#feesTotalAmount').val(sum);
        
    });
    $("#dataTable").click(function(){
        
        var sum = 0.0;
        var totalSum=0.0;
        var column2 = $('.feesFullAmount')
        jQuery.each(column2,function(){
            sum += parseFloat($(this).val());
        });
        
        $('#feesTotalAmount').val(sum);
       
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
		response.sendRedirect("/noblewisdom/UserProcess/sessionTimeOut");
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
	<form id="form1" modelAttribute="student"
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
					<li><a href="#fragment-5">Previous School Details</a></li>
					<li><a href="#fragment-4">Additional Details</a></li>
					<li><a href="#fragment-6">Bank Details</a></li>
					<li><a href="#fragment-7">Stamp Fee</a></li>
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
							<td class="alignLeft">Admission Number* &nbsp;</td>
							<td ><label> <input name="admnno" required
									type="text" class="myclass" id="admnno" size="36"
									style=" text-transform: capitalize;">

							</label></td>
							<td class="alignLeft" style="padding-left: 20px;">STS Number &nbsp;</td>
									<td ><label> <input
											name="sts" type="text" class="myclass" size="36"
											style="text-transform:capitalize;"
											id="sts" size="36">

									</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Student Name* &nbsp;</td>
							<td ><label> <input
									name="name" type="text" class="myclass" id="name" size="36" required
									style="text-transform:capitalize;"
									required>
							</label></td>

							<td  class="alignLeft" style="padding-left: 20px;">Gender &nbsp;</td>
							<td  height="30" class="alignLeft">&nbsp;Male<input
								type="checkbox" value="Male" name="gender" id="yes:male"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;Female<input
								type="checkbox" value="Female" name="gender" id="no:male"
								onclick="noCheck(this.id)" />

							</td>


						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
						<tr>
							<td class="alignLeft">Date Of Birth &nbsp;</td>
							<td ><label> <input name="dateofbirth"
									type="text" class="myclass" id="datepicker" size="36" autocomplete="false"
									onchange="CalculateAge(this)"
									data-validate="validate(required)">
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Age &nbsp;</td>
							<td><label> <input
									name="age" type="text" class="myclass" id="age" size="36"
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

							<td  class="alignLeft">Place Of Birth, Tq,
								Dist.&nbsp;</td>
							<td><label> <input
							style="text-transform:capitalize;"
									name="place" type="text" class="myclass" id="place" size="36">
							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Date of admission&nbsp;</td>
							<td ><label><input name="dateofadmission" autocomplete="false"
									type="text" class="myclass" id="dateofadmission" size="36"
									data-validate="validate(required)"> </label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>

						<tr>


							<td class="alignLeft">Studying in Class&nbsp;</td>
							<td ><label> <select name="addclass" required
									id="addclass" style="width: 186px;border-radius: 4px;background: white;height: 28px;" onchange="searchfeecategory()">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

							</label> <label> <select name="addsec" id="addsec" style="width: 70px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>

										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label></td>

							<td  class="alignLeft" style="padding-left: 20px;">Admitted in Class &nbsp;
							</td>

							<td ><label> <select name="admclassE"
									id="admclassE" style="width: 186px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>
							</label> <label> <select name="admsecE" id="admsecE" style="width: 70px;border-radius: 4px;background: white;height: 28px;"
									>
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.section != '')}">
												<option value="${classdetailslist.section}">
													<c:out value="${classdetailslist.section}" />
												</option>
											</c:if>
										</c:forEach>
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
							<td  class="alignLeft">Blood Group &nbsp;</td>

							<td><label> <select name="bloodgroup"
									id="bloodgroup" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<option>A +ve</option>
										<option>B +ve</option>
										<option>AB +ve</option>
										<option>O +ve</option>
										<option>A -ve</option>
										<option>B -ve</option>
										<option>AB -ve</option>
										<option>O -ve</option>


								</select>

							</label></td>


							<td  class="alignLeft" style="padding-left: 20px;">Nationality &nbsp;</td>

							<td><label> <select name="nationality"
									id="nationality" style="width: 258px;border-radius: 4px;background: white;height: 28px;" onchange="dropdown()">
										<option selected>Indian</option>
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

							<td >
								<!-- <label> <input name="religion"
									type="text" class="myclass" id="religion" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> --> <label> <select name="religion"
									id="religion" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<option>Islam</option>
										<option>Hinduism</option>
										<option>Christianity</option>
										<option>jainism</option>
										<option>sikhism</option>
								</select>

							</label>

							</td>



							<!-- <td class="alignRight">Caste &nbsp;</td>
							<td ><label> <input name="caste"
									type="text" class="myclass" id="caste" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label></td> -->

							<td class="alignLeft" style="padding-left: 20px;">Students Caste <br>
								Certificate No.&nbsp;</td>
							<td ><label> <input
									name="studentscastecertno" type="text" class="myclass"
									style="text-transform:capitalize;"
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
							<td class="alignLeft">Students Caste &nbsp;</td>
							<td ><label> <input name="studentscaste"
							style="text-transform:capitalize;"
									type="text" class="myclass" id="studentscaste" size="36">

							</label></td>

							<td  class="alignLeft" style="padding-left: 20px;">Social Category&nbsp;</td>
							<td><label> <select name="socialcategory"
									id="socialcategory" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
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
							<td  class="alignLeft" >Belong to BPL&nbsp;</td>
							<td>&nbsp;Yes<input
								type="checkbox" value="1" name="belongtobpl" id="yes:bpl"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="belongtobpl" id="no:bpl"
								onclick="noCheck(this.id);" />

							</td>
							<td class="alignLeft" style="padding-left: 20px;">BPL Card No.
								&nbsp;</td>
							<td ><label> <input
									name="bplcardno" type="text" class="myclass"
									style="text-transform:capitalize;"
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
							<td class="alignLeft" >Bag No.&nbsp;</td>
								<td><input
									name="bhagyalakshmibondnumber" type="text" class="myclass"
									style="text-transform:capitalize;"
									id="bhagyalakshmibondnumber" size="36">
							</td>
							<td  class="alignLeft" style="padding-left: 20px;">Student's Aadhar Card No.&nbsp;</td>
							<td ><label> <input
									name="disabilitychild" type="text" class="myclass"
									style="text-transform:capitalize;"
									id="disabilitychild" size="36">

							</label></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft" >Special Category&nbsp;</td>

							<td id="categoryname"><label> <select
									name="specialcategory" onchange="enterOtherSpecialCategory()"
									id="specialcategory" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected>None</option>
										<option></option>
										<option>None</option>
										<option>Destitute</option>
										<option>HIV Case</option>
										<option>Orphans</option>
										<option>Others (Please Specify)</option>
								</select>

							</label></td>
							<td  id="newcateg"
								style="display: none;"><label> <input
									name="newcategory" id="newcategory" type="text" class="myclass" size="36"
									style="text-transform:capitalize;"
									placeholder="Add Other Category" />
							</label></td>
							
							<td  class="alignLeft" style="padding-left: 20px;">Mother Tongue &nbsp;</td>
							<td >
								<!-- <label> <input name="motherT"
									type="text" class="textField" id="motherT" size="36"
									onblur="validateNameContact();"
									onkeypress="return validateContactNum(this);">

							</label> --> <label> <select name="motherT"
									 id="motherT"
									style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
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
						
							<td class="alignLeft"  >RTE
										&nbsp;</td>

									<td  >&nbsp;Yes<input
								type="checkbox" value="1" name="rte" id="yes:rte"
								onclick="yesCheck(this.id);" />&nbsp; &nbsp;No<input
								type="checkbox" value="0" name="rte" id="no:rte"
								onclick="noCheck(this.id);" />
										</td>
										
							<td class="alignLeft" style="padding-left: 20px;">Remarks &nbsp;</td>
							<td ><label> <input name="remarks"
									type="text" class="myclass" id="remarks" size="36"
									style="text-transform:capitalize;"
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
										
							<td class="alignLeft">Created Date &nbsp;</td>
							<td ><label> <input name="createddate"
									type="text"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>"
									class="myclass" id="datepickerCD" size="36"
									data-validate="validate(required)">
							</label></td>
							
							<td  class="alignLeft" style="padding-left: 20px;">Admission Year&nbsp;&nbsp;&nbsp;&nbsp;</td>
							
							 <td>
                                        <label> <select name="yearofadmission" id="yearofadmission" 
									style="width: 258px;border-radius: 4px;background: white;height: 28px;" onchange="searchfeecategory()">
										<option selected>${currentAcademicYear}</option>
										<option>2025/26</option>
										<option>2024/25</option>
										<option>2023/24</option>
										<option>2022/23</option>
										<option>2021/22</option>
										<option>2020/21</option>
										<option>2019/20</option>
										<option>2018/19</option>
										<option>2017/18</option>
										<option>2016/17</option>
										<option>2015/16</option>
										<option>2014/15</option>
										<option>2013/14</option>
										<option>2012/13</option>
										<option>2011/12</option>
										<option>2010/11</option>
										<option>2009/10</option>
										<option>2008/09</option>
										<option>2007/08</option>
										<option>2006/07</option>
										<option>2005/06</option>
										<option>2004/05</option>
										<option>2003/04</option>
										<option>2002/03</option>
										<option>2001/02</option>
										<option>2000/01</option>										
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
										
							<td class="alignLeft">DND Date &nbsp;</td>
							<td ><label> <input name="crecorddate"
									type="text"
									class="myclass" id="datepickerDND" size="36"
									data-validate="validate(required)">
							</label></td>
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



						<div id="fragment-3">
							<table width="100%" border="0" align="center">
								<tr>
									<td><label style="font-size: 16px;color: #eb6000;font-weight: bold;">Note: Upload only .jpg files</label><br><br><br><br></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Pic</label><br />  <input type="file" name="fileToUpload"
										id="fileToUpload" accept="image/*" onchange="Upload()"><br><br><br><br></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Doc 1</label><br /> <input type="file" name="fileToUpload"
										id="studentdoc1" accept="image/*" onchange="Upload()"><br><br><br><br></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Doc 2</label><br /> <input type="file" name="fileToUpload"
										id="studentdoc2" accept="image/*" onchange="Upload()"><br><br><br><br></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Doc 3</label><br /> <input type="file" name="fileToUpload"
										id="studentdoc3" accept="image/*" onchange="Upload()"><br><br><br><br></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Doc 4</label><br /> <input type="file" name="fileToUpload"
										id="studentdoc4" accept="image/*" onchange="Upload()"><br><br><br><br></td>
								</tr>
								
								<tr>
								<td></td>
								<td></td>
								</tr>
								
								<tr>
									<td><label style="font-size: 12px;color: #325F6D;font-weight: bold;">Student Doc 5</label><br /> <input type="file" name="fileToUpload"
										id="studentdoc5" accept="image/*" onchange="Upload()"><br><br><br><br></td>
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


						<div id="fragment-4">
							<table style="width: auto;height: auto;" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignLeft">Class on leaving&nbsp;</td>
									<td>
									<label> <select name="classonleaving"
									id="classonleaving" style="width: 256px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
										</select>
									</label>
									<td class="alignLeft" style="padding-left: 20px;">Date of leaving the
										school&nbsp;</td>
									<td><label> <input
											name="dateofleaving" type="text" class="myclass" autocomplete="false"
											id="dateofleaving" size="36"
											data-validate="validate(required)"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignLeft">Reason for leaving
										&nbsp;</td>

									<td><label> <input
											name="reasonforleaving" type="text" class="myclass"
											style="text-transform:capitalize;"
											id="reasonforleaving" size="36"
											>

									</label></td>



									<td class="alignLeft" style="padding-left: 20px;">No. & date of transfer<br>
										certificate issued&nbsp;</td>

									<td ><label> <input name="notcissued" autocomplete="false"
											type="text" class="myclass" id="notcissued" size="36" placeholder="No. of Transfer Certificate">
									</label></td>

								</tr>

								<tr>
								<td></td>
								<td></td>
								<td></td>
									<td><label>
											<input
											name="dateoftcissued" type="text" class="myclass" autocomplete="false"
											id="dateoftcissued" size="36" placeholder="Date of Transfer Certificate"
											data-validate="validate(required)">
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

								<tr>
									<td><br /></td>
								</tr>

								<tr align="center">


									<td class="alignRight">&nbsp;</td>

									<td align="center">


										<button id="savefour" class="save" name="savestudent">Save</button>

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

							</table>

						</div>


						<div id="fragment-2">
							<table style="width: auto;height: auto;" border="0" align="center" id="table1">
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignLeft">Father's Name* &nbsp;</td>
									<td ><label> <input
											name="fathersname" type="text" class="myclass" required
											style="text-transform:capitalize;"
											id="fathersname" size="36"
											required> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Mother's Name* &nbsp;</td>
									<td><label> <input
											name="mothersname" type="text" class="myclass" id="name" required
											style="text-transform:capitalize;"
											size="36"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>



								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>
									<td class="alignLeft">Father's Qualification
										&nbsp;</td>
									<td ><label> <input
											name="fathersqualification" type="text" class="myclass"
											id="fathersqualification" 
											style="text-transform:capitalize;"
											size="36"> <!-- onkeyup="check(this.value);"  -->
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Mother's
										Qualification&nbsp;</td>
									<td><label> <input
											name="mothersqualification" type="text" class="myclass"
											id="mothersqualification"
											style="text-transform:capitalize;"
											size="36"> <!-- onkeyup="check(this.value);"  -->
									</label></td>


								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td class="alignLeft">Father's Aadhar No
										&nbsp;</td>
									<td><label> <input
											name="fatherscastecertno" type="text" class="myclass"
											style="text-transform:capitalize;"
											id="fatherscastecertno" size="36">
									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Mother's Aadhar No
										&nbsp;</td>
									<td ><label> <input
											name="motherscastecertno" type="text" class="myclass"
											style="text-transform:capitalize;"
											id="motherscastecertno" size="36">
									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>

								<tr>

									<td class="alignLeft">Guardian's Name &
										Address &nbsp;</td>
									<td ><label> <input name="guardian"
											type="text" class="myclass" id="guardian" size="36"
											style="text-transform:capitalize;"
											>
									</label></td>



									<td class="alignLeft" style="padding-left: 20px;">Annual Income &nbsp;</td>

									<td ><label> <input
											name="parentsannualincome" type="text" class="myclass"
											style="text-transform:capitalize;"
											id="parentsannualincome" size="36"
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

									<td class="alignLeft">Contact Number* &nbsp;</td>

									<td><label> <input
											name="contactnumber" type="text" class="myclass" required
											style="text-transform:capitalize;"
											id="contactnumber" size="36" maxlength="10" minlength="10">

									</label></td>



									<td class="alignLeft" style="padding-left: 20px;">Co-Contact Number
										&nbsp;</td>

									<td><label> <input
											name="cocontactnumber" type="text" class="myclass"
											style="text-transform:capitalize;"
											id="cocontactnumber" size="36" maxlength="10" minlength="10">

									</label></td>
								</tr>

								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>


								<tr>

									<td class="alignLeft">Email &nbsp;</td>

									<td ><label> <input name="email"
											type="email" class="myclass" id="email" size="36"
											>

									</label></td>

									<td class="alignLeft" style="padding-left: 20px;">Number Of Dependents
										&nbsp;</td>

									<td ><label> <input
											name="noofdependents" type="text" class="myclass"
											id="noofdependents" size="36" >

									</label></td>


								</tr>

								<tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
									<td><br /></td>
								</tr>
								<tr>
								<td class="alignLeft">Permanent Address &nbsp;</td>

								<td ><label> <textarea
											name="permanentaddress" type="text" 
											id="permanentaddress" rows="4" cols="40"
											style="text-transform:capitalize;"
											></textarea>

								</label></td>


								<td class="alignLeft" style="padding-left: 20px;">Temporary Address &nbsp;</td>
								<td ><label> <textarea
											name="temporaryaddress" type="text"  style="text-transform:capitalize;"
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

									<td class="alignLeft">Notes &nbsp;</td>
									<td ><label> <input name="remarksadditional"
											type="text" class="myclass" id="remarksadditional" size="36"
											style="text-transform:capitalize;"
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
						
						<div id="fragment-5">

							<div>
								<table style="width: auto;height: auto;" align="center">
									<tr>

										<td><br /></td>
									</tr>

									<tr>
							<td class="alignLeft">Transfer
								certificate No.&nbsp;</td>
							<td><label> <input style="text-transform:capitalize;"	name="tcno" type="text" class="myclass" id="tcno" size="36">
									
							</label></td>
									<td class="alignLeft" style="padding-left: 20px;">Date of Transfer Certificate&nbsp;</td>
							<td ><label >
							<input name="dateoftc" type="text" class="myclass"
									id="dateoftc" size="36" autocomplete="false"
									style="text-transform:capitalize;"
									data-validate="validate(required)"></label></td>

						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Previous Class Studied &nbsp;</td>

							<td ><label> <select name="lastclass" id="lastclass"
									style="width: 256px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<option value="${classdetailslist.classdetails}">
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:forEach>
								</select>
							</label></td>

							<td class="alignLeft" style="padding-left: 20px;">Previous School Name
								&nbsp;</td>
							<td><label> <input
									name="lastschool" type="text" class="myclass" id="lastschool"
									style="text-transform:capitalize;"
									size="36" >
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr>
							<td class="alignLeft">Languages Studied &nbsp;</td>

							<td><label> <input
									name="languagesstudied" type="text" class="myclass"
									style="text-transform:capitalize;"
									id="languagesstudied" size="36">
							</label></td>



							<td class="alignLeft" style="padding-left: 20px;">Core Subjects Studied &nbsp;</td>
							<td><label> <input
									name="progress" type="text"
									style="text-transform:capitalize;"
									class="myclass" id="progress" size="36">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>
							<td class="alignLeft">Previous School Medium of<br>
								Instruction&nbsp;</td>

							<td><label> <select name="mediumofinstruction"
									id="mediumofinstruction" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<option>Kannada</option>
										<option>Hindi</option>
										<option>Urdu</option>
										<option>English</option>
										<option>Marathi</option>
										<option>Tamil</option>
										<option>Telgu</option>
								</select>

							</label></td>
							
							<td class="alignLeft" style="padding-left: 20px;">Previous School
								Type&nbsp;</td>

							<td><label> <select name="previousschooltype"
									id="previousschooltype" style="width: 258px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
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
						

									<tr>
										<td></td>
										<td align="left">


											<button id="savefive" class="save" name="savestudent">Save</button>

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
						
						<div id="fragment-6">

							<div>
								<table style="width: auto;height: auto;" align="center">
									<tr>

										<td><br /></td>
									</tr>
									
									<tr>
								<td class="alignLeft">Bank Name &nbsp;</td>
							<td><label> <input name="bankname" type="text" class="myclass" id="bankname" size="36"
									style="text-transform:capitalize;"
									>
							</label></td>
						</tr>
						<tr>

										<td><br /></td>
									</tr>
						
						<tr>
							<td class="alignLeft" >Bank IFSC Code&nbsp;</td>
							<td><label> <input name="bankifsc" type="text" class="myclass" id="bankifsc" size="36"
									style="text-transform:capitalize;"
									>
							</label></td>
						</tr>
								<tr>
										<td><br /></td>
								</tr>
						<tr>
							<td class="alignLeft" >Account No. &nbsp;</td>
							<td><label> <input name="accno" type="text" class="myclass" id="accno" size="36"
									style="text-transform:capitalize;"
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
						
						<div id="fragment-7">
						
						<table style="width: auto;height: auto;" align="center">
								
							<tr>
							<td style="font-weight: bold;color:#325F6D">Stamp Fee: &nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>
							<label class="labelClass" style="font-weight: bold;color:#325F6D">  <input  type="checkbox" id = "chckHead" />All
							</label>
							</td>
							
						</tr>
											
						<tr>
							<td class="alignRightFields" style="font-weight: bold;color:#325F6D"></td>
							<td id="feescat">
							
							</td>
							
						</tr>
						 <tr>
							<td><br /></td>
						</tr>
							
						
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
								<tfoot>
					
				</tfoot>
								</tr>
									<tr>
										<td></td>
										<td align="left">
										
											<button id="saveseven" class="save" name="savestudent">Save</button>
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
									form1.action = "/noblewisdom/StudentProcess/AddStudent";
									form1.submit();
								  }
							}

							function Cancel() {
								var form1 = document.getElementById("form1");
								form1.action = "/noblewisdom/StudentProcess/viewAll";
								form1.submit();
							}

							
						</script>
</body>
</html>

