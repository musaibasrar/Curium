<%--
    Document   : index
    Created on : Jan 11, 2018, 3:49:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Holiday</title>
<link rel="stylesheet" href="/noble/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/noble/css/datePicker/demos.css">
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

.alignRightFields {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.alignSearch {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
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

.footerTD {
	border-radius: 6px;
	background-color: #4b6a84;
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

<link rel="stylesheet" href="/noble/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/noble/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/noble/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/noble/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/noble/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/noble/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/noble/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/noble/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>

<script type="text/javascript" src="/noble/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchForEmployees() {
		
		var form1 = document.getElementById("form1");
		form1.action = "/noble/AttendanceProcess/viewAllEmployees";
		form1.method = "POST";
		form1.submit();

	}
	
function addHolidays() {
		
		var form1 = document.getElementById("form1");
		form1.action = "/noble/AttendanceProcess/addHolidays";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			searchForEmployees();
		});
		

	});
	
	

	$(function() {

		$("#tabs").tabs();

	});
	
	$(function() {
		$("#save").button.click(function(){
			addHolidays();
		});
		$("#delete").button({
            icons:{
                primary: "ui-icon-trash"
            }
        }).click(function(){
        	if(confirm('Are you sure,you want to delete?')){
        		deleteRecords();	
        	}
        });
		
		var addHolidayButtonID="#addHoliday";
        $( addHolidayButtonID )
        .button({
            icons: {
                primary: "ui-icon-plus"
            }
        })
        .click(function() {
            addRow();
            return false;
        });

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
        
	});
	
	 function addRow() {
         var rowCount = document.getElementById('dataTable').rows.length;    
         var col1="<td class='dataTextInActive'><input type='date' name='fromdate' id=from_date_"+rowCount+" /></td>";
         var col2="<td class='dataTextInActive'><input type='date' name='todate' id=to_date_"+rowCount+" /></td>";
         var col3="<td class='dataTextInActive'><input type='text' name='holidayname'  id=holiday_name_"+rowCount+" /></td>";
         var newRow = $("<tr class='trClass'>"+col1+col2+col3+"</tr>");
         $(function() {
             $("#dataTable").find('tbody').append(newRow);
         });
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

<script type="text/javascript">
	
$(function() {
	
	$("#save").button().click(function() {
		addStudent();

	});
		
});
	
</script>


</head>
<body>
	<form id="form1" action="/noble/MarksDetailsProcess/updateMarks" method="POST">
		
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
							<td class="alignRightFields">Weekly Off &nbsp;</td>
							<td width="70%"><label  style="font-weight: bold;color:#325F6D"> <input
									name="sunday" type="checkbox" class="myclass" id="sunday"
									size="36"> Sunday
							</label><label  style="font-weight: bold;color:#325F6D"> <input
									name="monday" type="checkbox" class="myclass" id="monday"
									size="36"> Monday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="tuesday" type="checkbox" class="myclass" id="tuesday"
									size="36"> Tuesday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="wednesday" type="checkbox" class="myclass" id="wednesday"
									size="36"> Wednesday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="thursday" type="checkbox" class="myclass" id="thursday"
									size="36"> Thursday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="friday" type="checkbox" class="myclass" id="friday"
									size="36"> Friday
							</label>
							<label  style="font-weight: bold;color:#325F6D"> <input
									name="saturday" type="checkbox" class="myclass" id="saturday"
									size="36"> Saturday
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

							<td width="12%" align="left" class="alignRightFields">Holidays: &nbsp;</td>
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

		
<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Holidays/Weekly Off </td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Holiday Name/Weekly Off<img
							alt=" " style="position: relative; top: 4px;"
							src="/noble/css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">From Date<img
							alt=" " style="position: relative; top: 4px;"
							src="/noble/css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">To Date<img
							alt=" " style="position: relative; top: 4px;"
							src="/noble/css/dataTable/images/sort_both.png" />&nbsp;&nbsp;
						</th>

					</tr>
				</thead>

				<tbody>

					<c:forEach items="${holidaysList}" var="holidaysList">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${holidaysList.hid}"/>" class="chcktbl"
								name="idfeescategory"
								value="<c:out value="${holidaysList.holidayname}"/>" /></td>
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
