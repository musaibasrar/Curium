<%--
    Document   : holidays
    Created on : FEB 23, 2018, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Holidays</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
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
.footerTD{
                border-radius:6px;
                background-color:#4b6a84;


                text-align: left;


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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
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
		url("images/ui-bg_diagonals-small_50_466580_40x40.png");
	color: #FFFFFF;
	font-family: Tahoma;
	font-size: 13px;
	text-transform: uppercase;
	text-align: center;
	font-weight: bold;
	height: 22px;
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="css/validation/jquery.ketchup.css">
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/ScrollableGridPlugin.js"></script>
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

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function addHolidays() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addHolidays";
		form1.method = "POST";
		form1.submit();

	}
	
	function addWeekOff() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addWeekOff";
		form1.method = "POST";
		form1.submit();

	}
	
	function deleteRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=deleteMultiple";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#save").button().click(function() {
			if(confirm('Are you sure,you want to save the holidays?')){
				addHolidays();	
        	}
			
		});
		$("#saveweek").button().click(function() {
			if(confirm('Are you sure,you want to save the weekly off days?')){
				addWeekOff();	
        	}
			
		});
		
		$("#addHoliday").button({
	        icons: {
	            primary: "ui-icon-plus"
	        }
	    }).click(function() {
	    	addRow();
	    	 return false;
		});
		
		$("#effect").show();
		
		 var removeHolidayButtonID="#removeHoliday";
	        $(removeHolidayButtonID)
	        .button({
	            icons: {
	                primary: "ui-icon-minus"
	            }
	        })
	        .click(function() {
	            deleteRow('dataTable');
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
	        

	});
	
	 $(function(){
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
        	 if(confirm('Are you sure,you want to delete?')){
        		 deleteRecords();	
         	}
             
             return false;
         });
     });
	 
	
     
	 function addRow() {
         var rowCount = document.getElementById('dataTable').rows.length;    
         var col1="<td class='dataTextInActive'><input type='date' name='fromdate' id=from_date_"+rowCount+" /></td>";
         var col2="<td class='dataTextInActive'><input type='date' name='todate' onblur='checkDates("+rowCount+")' id=to_date_"+rowCount+" /></td>";
         var col3="<td class='dataTextInActive'><input type='text' name='holidayname'  id=holiday_name_"+rowCount+" /></td>";
         var newRow = $("<tr class='trClass'>"+col1+col2+col3+"</tr>");
         $(function() {
             $("#dataTable").find('tbody').append(newRow);
         });
     }
		
	 
     function checkDates(rownum){
		 var fromDate= document.getElementById('from_date_'+rownum).value;
		  var toDate= document.getElementById('to_date_'+rownum).value;
		  var eDate = new Date(fromDate);
		  var sDate = new Date(toDate);
		  if(fromDate!= '' && toDate!= '' && sDate < eDate)
		    {
		    alert("Please ensure that the To Date is greater than or equals to from Date.");
		    document.getElementById('from_date_'+rownum).value = '';
		    document.getElementById('to_date_'+rownum).value = '';
		    return false;
		    }else if(fromDate== '' && toDate== ''){
		    	alert("Enter the valid dates");
		    }
	 }
	
	 
	 function deleteRow(tableID) {
		  try {
             var table = document.getElementById(tableID);
             var rowCount = table.rows.length;
             if(rowCount==1){
                 alert('No records to delete');
                 return;
             }
             table.deleteRow(rowCount-1);
             
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
	<form id="form1">
			

		<div id="effect" class="ui-widget-content ui-corner-all">
			
				
				<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Set Holidays & Weeklyoff</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						<tr>
							<td><br /></td>

						</tr>	
						<tr>
							<td><br /></td>

						</tr>					
						
						<tr>
							<td class="alignRightFields" style="font-weight: bold;color:#325F6D">Weekly Off &nbsp;</td>
							<td width="70%"><label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="sunday" value="Sunday"
									size="36"> Sunday
							</label><label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="monday"
									value="Monday"
									size="36"> Monday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="tuesday"
									value="Tuesday"
									size="36"> Tuesday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="wednesday"
									value="Wednesday"
									size="36"> Wednesday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="thursday"
									value="Thursday"
									size="36"> Thursday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="friday"
									value="Friday"
									size="36"> Friday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="weekoff" type="checkbox" class="myclass" id="saturday"
									value="Saturday"
									size="36"> Saturday
							</label>
							</td>
							
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
												
						
						<tr>
							<td class="alignRightFields" style="font-weight: bold;color:#325F6D"> &nbsp;</td>
							<td width="30%" >
								<button id="saveweek">Save</button>
							</td>
						</tr>
						
												
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td width="12%" align="left" class="alignRightFields" style="font-weight: bold;color:#325F6D">Holidays: &nbsp;</td>
							<td><button id="addHoliday">Add</button>&nbsp;&nbsp;&nbsp;<button id="removeHoliday">Remove</button></td>
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						
						<TABLE id="dataTable" width="50%" border="1" >
                <thead>
                    <tr >
                        <td class="headerText">From Date</td>
                        <td class="headerText">To Date</td>                       
                        <td class="headerText">Holiday Name</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
                            </TABLE>
						<tr>
							<td><br /></td>
						</tr><tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="30%" class="alignRight"></td>
							<td width="30%" class="alignRight">&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="save">Save</button>
							</td>
						</tr>
						
					</table>
				</div>
			</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Holidays List </td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Holidays Name<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">From Date<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">To Date<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>

					</tr>
				</thead>

				<tbody>

					<c:forEach items="${holidaysList}" var="holidaysList">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox" id="<c:out value="${holidaysList.shid}"/>" class="chcktbl" name="holidayid" value="<c:out value="${holidaysList.shid}"/>" /></td>
							<td class="dataText"><c:out value="${holidaysList.holidayname}" /></td>
							<td class="dataText"><c:out value="${holidaysList.fromdate}" /></td>
							<td class="dataText"><c:out value="${holidaysList.todate}" /></td>
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
