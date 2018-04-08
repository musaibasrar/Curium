<%--
    Document   : Period Master
    Created on : APR 11, 2018, 3:49:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Period Master</title>
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

.extraLabels {
	font-family: Tahoma;
	font-size: 11px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
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
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		

	}
	
</script>
  <script type="text/javascript">
    function init() {
      if (arguments.callee.done) return;
      arguments.callee.done = true;
      if (khtmltimer) clearInterval(khtmltimer);
      var s = document.getElementsByTagName('select');
      for (var i = 0; i < s.length; i++) {
        if (s[i].hasAttribute('multiple')) {
          s[i].onclick = updateSelect;
        }
      }
    }
    function updateSelect(e) {
      var opts = this.getElementsByTagName('option'), t, o;
      if (e) {
        e.preventDefault();
        t = e.target;
      }
      else if (window.event) {
        window.event.returnValue = false;
        t = window.event.srcElement;
      }
      else return;
      t = e.target || window.event.srcElement;
      if (t.getAttribute('class') == 'selected') t.removeAttribute('class');
      else t.setAttribute('class', 'selected');
      for (var i = 0, j = opts.length; i < j; i++) {
        if (opts[i].hasAttribute('class')) opts[i].selected = true;
        else opts[i].selected = false;
      }
    }
         
    if (document.addEventListener) document.addEventListener("DOMContentLoaded", init, false);
    /*@cc_on @*/
    /*@if (@_win32)
        document.write("<script id=__ie_onload defer src=javascript:void(0)><\\/script>");
        var script = document.getElementById('__ie_onload');
        script.onreadystatechange = function() {
            if (this.readyState == 'complete') {
                init();
            }
        };
    /*@end @*/
    if (/KHTML/i.test(navigator.userAgent)) {
        var khtmltimer = setInterval(function() {
            if (/loaded|complete/.test(document.readyState)) {
                init();
            }
        }, 10);
    }
    window.onload = init;
  </script>

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function addHolidays() {

		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addHolidays";
		form1.method = "POST";
		form1.submit();

	}

	function searchForEmployees(staffName, staffDepartment){
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=searchEmployees&staffName="+staffName+"&staffDepartment="+staffDepartment+"";
		form1.method = "POST";
		form1.submit();

	}

	$(function() {

		$("#search").button().click(function() {
			var staffName = document.getElementById('staffname').value;
			var staffDepartment = document.getElementById('department').value;
			searchForEmployees(staffName, staffDepartment);
		});

		$("#tabs").tabs();

		$("#save").button().click(function() {
			if (confirm('Are you sure,you want to save these configuration?')) {
				addStaffAttendanceMaster();
			}
			return false;
		});

		$("#savestudentattendance").button().click(function() {
			if (confirm('Are you sure,you want to save these configuration?')) {
				addStudentAttendanceMaster();
			}
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

	function addStudentAttendanceMaster() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addStudentAttendanceMaster";
		form1.method = "POST";
		form1.submit();
	}
	
	function addStaffAttendanceMaster() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AttendanceProcess&action=addStaffAttendanceMaster";
		form1.method = "POST";
		form1.submit();
	}
	
	function getBreaks(){
		
		document.getElementById("tr1").style.display = '';
        /* document.getElementById("subgroupname").style.display = '';
        document.getElementById("newsubgr").style.display = "none"; */
	}
	
</script>


</head>
<body>
	<form id="form1" action="Controller?process=MarksDetailsProcess&action=updateMarks" method="POST">
		
		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Period Master</a></li>
				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">

						</tr>
							<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>					
						<tr>
							<td class="alignRightFields">Academic Year&nbsp;</td>
							<td width="70%"><label>    <label>
                                        <label> <select name="academicyear" id="academicyear"
									style="width: 180px">
										<option selected value="${currentYear}">${currentYear}(Current Year)</option>
										<option value="2015/16">2015/16</option>
										<option value="2016/17">2016/17</option>
										<option value="2017/18">2017/18</option>
										<option value="2018/19">2018/19</option>
										<option value="2019/20">2019/20</option>
										
								</select>

							</label> 
                                    </label>

							</label> 
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>

						<tr>
							<td class="alignRightFields">Total Number Of Periods&nbsp;</td>
							<td width="70%">
                                        <label> <select name="academicyear" id="academicyear"
									style="width: 180px">
										<option selected ></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										
								</select>

							</label> 
							
							
							<label class="extraLabels">&nbsp;&nbsp;&nbsp;&nbsp;Duration Of a Period<label>
							<label> <select name="periodduration" id="periodduration"
									style="width: 80px">
										<option selected>Hour</option>
										<option value="1">1hr</option>
										<option value="2">2hr</option>
								</select>
							</label>
							<label> <select name="perioddurationmin" id="perioddurationmin"
									style="width: 80px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
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
							<td class="alignRightFields">Day Start Time &nbsp;</td>
							<td width="70%">
                                        <label> <select name="daystarttime" id="daystarttime"
									style="width: 60px">
										<option selected >Hour</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										
								</select>
								<select name="daystartminutes" id="daystartminutes"
									style="width: 60px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
										
								</select>
								<select name="daystartam" id="daystartam"
									style="width: 60px">
										<option selected>AM</option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
								</select>

							</label> 
							
							
							<label class="extraLabels">&nbsp;&nbsp;&nbsp;&nbsp;Day End Time<label>
							<label> <select name="dayendtime" id="dayendtime"
									style="width: 60px">
										<option selected >Hour</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										
								</select>
							</label>
							<label> <select name="dayendtimemin" id="dayendtimemin"
									style="width: 80px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
								</select>
								
								<select name="dayendam" id="dayendam"
									style="width: 60px">
										<option selected>AM</option>
										<option value="AM">AM</option>
										<option value="PM">PM</option>
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
							<td class="alignRightFields">Total Number Of Breaks&nbsp;</td>
							<td width="70%">
                                        <label> <select name="totalbreaks" id="totalbreaks" onchange="getBreaks();"
									style="width: 180px">
										<option selected ></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
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
						
						<tr style="display: none;" id="tr1">
							<td class="alignRightFields">Break 1 - After&nbsp;</td>
							<td width="70%">
                                        <label> <select name="breakafter1" id="breakafter1"
									style="width: 180px">
										<option selected ></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										
								</select>

							</label> 
							
							
							<label class="extraLabels">&nbsp;&nbsp;&nbsp;&nbsp;Duration of Break<label>
							<label> <select name="breakduration1" id="breakduration1"
									style="width: 80px">
										<option selected>Hour</option>
										<option value="0">0hr</option>
										<option value="1">1hr</option>
										<option value="2">2hr</option>
								</select>
							</label>
							<label> <select name="breakduration1nmin" id="breakduration1nmin"
									style="width: 80px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
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
						
						<tr style="display: none;" id="tr2">
							<td class="alignRightFields">Break 2 - After&nbsp;</td>
							<td width="70%">
                                        <label> <select name="breakafter2" id="breakafter2"
									style="width: 180px">
										<option selected ></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										
								</select>

							</label> 
							
							
							<label class="extraLabels">&nbsp;&nbsp;&nbsp;&nbsp;Duration of Break<label>
							<label> <select name="breakduration2" id="breakduration2"
									style="width: 80px">
										<option selected>Hour</option>
										<option value="0">0hr</option>
										<option value="1">1hr</option>
										<option value="2">2hr</option>
								</select>
							</label>
							<label> <select name="periodduration2min" id="periodduration2min"
									style="width: 80px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
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
						
						<tr style="display: none;" id="tr3">
							<td class="alignRightFields">Break 3 - After&nbsp;</td>
							<td width="70%">
                                        <label> <select name="breakafter3" id="breakafter3"
									style="width: 180px">
										<option selected ></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										
								</select>

							</label> 
							
							
							<label class="extraLabels">&nbsp;&nbsp;&nbsp;&nbsp;Duration of Break<label>
							<label> <select name="periodduration3" id="periodduration3"
									style="width: 80px">
										<option selected>Hour</option>
										<option value="0">0hr</option>
										<option value="1">1hr</option>
										<option value="2">2hr</option>
								</select>
							</label>
							<label> <select name="periodduration3min" id="periodduration3min"
									style="width: 80px">
										<option selected>Minuts</option>
										<option value="00">00</option>
										<option value="05">05</option>
										<option value="10">10</option>
										<option value="15">15</option>
										<option value="20">20</option>
										<option value="25">25</option>
										<option value="30">30</option>
										<option value="35">35</option>
										<option value="40">40</option>
										<option value="45">45</option>
										<option value="50">50</option>
										<option value="55">55</option>
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

							<td class="alignRight"></td>

							<!-- <td width="30%" class="alignRight">&nbsp;</td> -->
							<td width="30%" >
								<button id="generate">Generate</button>
							</td>
						</tr>
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>						
						
						<tr>
							<td width="12%" align="left" class="alignRightFields">Weekly Off &nbsp;</td>
							<td width="12%" align="left"><label>
									<select name="weekoffstaff" id="weekoffstaff" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${weekOffList}" var="weekOffList">

											<option value="${weekOffList.wid}">
												<c:out value="${weekOffList.weeklyoffday}" />
											</option>


										</c:forEach>

								</select></label>
							</td>
							
						</tr>
												
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>
							<td><br /></td>

						</tr>

						<tr>

							<td width="12%" align="left" class="alignRightFields">Holidays &nbsp;</td>
							<td><label>
									<select name="holidaysstaff" id="holidaysstaff" multiple="multiple"
									style="width: 200px" >
										

										<c:forEach items="${holidaysList}" var="holidaysList">

											<option value="${holidaysList.shid}">
												<c:out value="${holidaysList.holidayname}" />
											</option>


										</c:forEach>

								</select></label></td>
						</tr>
						
						<tr>
							<td><br /></td>

						</tr>
						<tr>
							<td><br /></td>

						</tr>
						
						<tr>

							<td width="12%" align="left" class="alignRightFields">In Time &nbsp;</td>
							<td><label> <input name="intime" id="intime" type="time"
							</input>
							
							 </td>
						</tr>
						
						<!-- <tr>

							<td width="12%" align="left" class="alignRightFields">In Time &nbsp;</td>
							<td><label> <select name="intime" id="intime"
									style="width: 40px">
										<option selected>07</option>
										<option>08</option>
										<option>09</option>
										<option>10</option>
										<option>11</option>
										<option>12</option>
										<option>01</option>
										<option>02</option>
										<option>03</option>
										<option>04</option>
										<option>05</option>
										<option>06</option>
								</select>
								

							</label>
							<label style="font-weight: bold;color:#325F6D">:</label>
							 <label> 
									<select name="mininstaff" id="mininstaff"
									style=" width: 40px">
										<option selected>00</option>
										<option>15</option>
										<option>30</option>
										<option>45</option>
								</select>
							</label>
							<label> 
									<select name="ampminstaff" id="ampminstaff"
									style=" width: 40px">
										<option selected>AM</option>
										<option>PM</option>
								</select>
							</label></td>
						</tr> -->
						
						<tr>
							<td><br /></td>
						</tr>
						
						<tr>

							<td width="12%" align="left" class="alignRightFields">Out Time &nbsp;</td>
							<td><label> <input name="outtime" id="outtime" type="time"/>	</label>
							</td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						
					</table>
					
					
					<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Staff</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Name<img
							alt=" " style="position: relative; top: 4px;"
							src="images/sort_both.png" /></th>
							<th title="click to sort" class="headerText">Contact Number&nbsp;</th>
							<th title="click to sort" class="headerText">Department&nbsp;</th>
						</tr>
				</thead>

				<tbody>

					   <c:forEach items="${employeeList}" var="employee">

                            <tr class="trClass" style="border-color:#000000" border="1"  cellpadding="1"  cellspacing="1" >
                                <td class="dataText"><input type="checkbox" id = "<c:out value="${employee.tid}"/>" class = "chcktbl"  name="employeeIDs"  value="<c:out value="${employee.tid}"/>"/></td>
                                <td  class="dataTextInActive" style="text-transform:uppercase"><a class="dataTextInActive" href="Controller?process=EmployeeProcess&action=ViewDetails&id=<c:out value='${employee.tid}'/>"><c:out value="${employee.teachername}"/></a></td>
                                <td class="dataText"><c:out value="${employee.contactnumber}"/></td>
                                <td class="dataText"><c:out value="${employee.department}"/></td>
                                
                                 

                            </tr>
                        </c:forEach>




				</tbody>
				<tfoot><tr>
                            <td  class="footerTD" colspan="2" ><button id="save">Save</button> 
                    
                        </tr></tfoot>
			</table>

		</div>

				</div>
			</div>
		</div>

		

	</form>

</body>
</html>
