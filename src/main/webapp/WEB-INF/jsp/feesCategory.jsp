<%--
    Document   : fees category
    Created on : Dec 23, 2016, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fees Category</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/abc/css/datePicker/demos.css">
<style type="text/css">
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
}

.dataText {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
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

.dataTextActive {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
	text-decoration: underline;
	cursor: pointer;
}

.dataTextHidden {
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: center;
	background-color: #E3EFFF;
}

.headerTD {
	border-radius: 6px;
	background-color: #4b6a84;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>

<link rel="stylesheet" href="/abc/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/abc/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/abc/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/abc/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		$('#myTable').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>
<script type="text/javascript">
	function getCurrentDate() {
		var today = new Date();
		var day = today.getDate();
		var month = today.getMonth() + 1;
		var year = today.getFullYear();
		if (month < 10) {
			month = "0" + month;

		} else {
			month = month;
		}
		if (day < 10) {
			day = "0" + day;

		} else {
			day = day;
		}
		return month + "/" + day + "/" + year;

	}
</script>
<script type="text/javascript">
	$(function() {
		// run the currently selected effect
		function runEffect() {

			var clipEffect = 'blind';
			var options = {};
			$("#effect").toggle(clipEffect, options, 1000);
		}
		;
		// set effect from select menu value
		$("#add").button().click(function() {
			runEffect();
			return false;
		});
	});
</script>
<script type="text/javascript" src="/abc/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function addFeesCategory() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/FeesProcess/addFeesParticular";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			addFeesCategory();
		});
		$("#effect").hide();

	});
	function calculatestock() {

		var tablet = document.getElementById('tablet').value;
		var strip = document.getElementById('strip').value;

		var stock = Number(tablet) * Number(strip);

		document.getElementById('stockQuantity').value = stock;

	}
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         $('#chckHead').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             if(length>0){
                 $('.chcktbl:checked').attr('checked', false);
                 this.checked=false;

             }
             else{
                 if (this.checked == false) {
                     $('.chcktbl:checked').attr('checked', false);
                 }
                 else {
                     $('.chcktbl:not(:checked)').attr('checked', true);
                 }

             }

         });
         $('.chcktbl').click(function () {
             var length = $('.chcktbl:checked').length;
             var trLength=$('.trClass').length;
             alert(tdLength);
             if (length > trLength) {

                 $('.chcktbl:not(:checked)').attr('disabled', true);
             }
             else {
                 $('.chcktbl:not(:checked)').attr('disabled', false);
             }
         });
         
         $( "#go" )
         .button()
         
         
    	 $("#amount").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
     });
	 function deleteRecords(){
         
         var form1=document.getElementById("form1");
         form1.action="/abc/FeesProcess/deleteMultiple";
        form1.submit();
         
     }
</script>
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
	<form id="form1"
		method="POST">
		<div style="height: 28px">
			<button id="add">Add Fees Category</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Details</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td width="10%" class="alignRight">Fees Category &nbsp;</td>
							<td width="70%"><label> <input id="feescategory" style="width: 210px;border-radius: 4px;background: white;height: 28px;"
									name="feescategory" type="text" class="textField" required size="30">

							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr></tr>
						<tr>
						
						<td width="16%" class="alignRight">Class &nbsp;
							</td>

							<td width="8%">
							 <label> 
							 
							 <c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
										
										<label class="labelClass" style="font-weight: bold;color:#325F6D"><input type="checkbox"  name="fromclass" value="${classdetailslist.classdetails}">
										${classdetailslist.classdetails}</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>	
							</c:forEach>
							
								<%-- <select name="fromclass" id="fromclass"
									style="width: 210px;border-radius: 4px;background: white;height: 28px;">
										<option selected></option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
										<c:if test="${(classdetailslist.classdetails != '')}">
											<option value="${classdetailslist.classdetails}">
												<c:out value="${classdetailslist.classdetails}" />
											</option>
										</c:if>	
										</c:forEach>
								</select> --%>
								
							</label>
							&nbsp;<label style="font-weight: bold;color:#325F85;display: none;">To Class &nbsp;</label>
							
							<label>
							<select name="toclass" id="toclass"
									style="width: 120px;display: none;">
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
							
							</td>
							
						</tr>

						<tr>
							<td><br /></td>
						</tr>

						<tr></tr>


						<tr>
							<td width="10%" class="alignRight">Amount &nbsp;</td>
							<td width="70%"><label> <input name="amount" style="width: 210px;border-radius: 4px;background: white;height: 28px;"
									type="text" class="textField" id="amount" required size="30">
							</label></td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>

						<tr></tr>


						<tr>
							<td width="10%" class="alignRight">Year &nbsp;</td>
							<td width="70%"><label> <select name="categoryyear" id="categoryyear" 
									style="width: 210px;border-radius: 4px;background: white;height: 28px;">
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

							</label> </td>
						</tr>

					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="save">Save</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Fees Category</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Fees Category<img
							alt=" " style="position: relative; top: 4px;"
							src="/abc/css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Class<img
							alt=" " style="position: relative; top: 4px;"
							src="/abc/css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Amount<img
							alt=" " style="position: relative; top: 4px;"
							src="/abc/css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>
						<th title="click to sort" class="headerText">Year<img
							alt=" " style="position: relative; top: 4px;"
							src="/abc/css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>

					</tr>
				</thead>

				<tbody>

					<c:forEach items="${feescategory}" var="fees">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${fees.idfeescategory}"/>" class="chcktbl"
								name="idfeescategory"
								value="<c:out value="${fees.idfeescategory}"/>" /></td>
							<td class="dataText"><c:out value="${fees.feescategoryname}" /></td>
							<td class="dataText"><c:out value="${fees.particularname}" /></td>
							<td class="dataText"><c:out value="${fees.amount}" /></td>
							<td class="dataText"><c:out value="${fees.academicyear}" /></td>
						</tr>
					</c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="delete">Delete</button> 
                    
                        </tr></tfoot>
			</table>

		</div>


	</form>

</body>
</html>
