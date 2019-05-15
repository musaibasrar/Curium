<%--
    Document   : Classes time table
    Created on : MAY 05, 2018, 3:49:45 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Staff's List</title>
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
	font-weight: Bold;
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

.timetable {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

.timetd, .timeth {
    border: 1px solid black;
    text-align: left;
    padding: 8px;
}

.trclass:nth-child(even) {
    background-color: #dddddd;
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

<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	$(function() {

		$("#tabs").tabs();

		$("#save").button().click(function() {
			savePeriods();
			return false;
		});
		
		$("#createNew").button();
		
		$("#delete").button({
	        icons: {
	            primary: "ui-icon-trash"
	        }
	    }).click(function() {
	    	deleteRecord();
	    	 return false;
		});
		
		$("#addSchedule").button({
	        icons: {
	            primary: "ui-icon-plus"
	        }
	    }).click(function() {
	    	addRow();
	    	 return false;
		});
		
		$("#addScheduleNew").button({
	        icons: {
	            primary: "ui-icon-plus"
	        }
	    }).click(function() {
	    	addColumn(document.getElementById("totalperiods").value);
	    	
	    	 return false;
		});
		
		 var removeScheduleButtonID="#removeSchedule";
	        $(removeScheduleButtonID)
	        .button({
	            icons: {
	                primary: "ui-icon-minus"
	            }
	        })
	        .click(function() {
	            deleteRow('dataTableNew');
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

    
	 function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        var col1="<td width='16%' height='30' class='alignLeft'><label><select name='periods' id='periods' style='width: 180px'><option selected value='period1'>Period-1</option><option value='period2'>Period-2</option><option value='period3'>Period-3</option><option value='period4'>Period-4</option><option value='period5'>Period-5</option><option value='period6'>Period-6</option><option value='period7'>Period-7</option><option value='period8'>Period-8</option><option value='period9'>Period-9</option><option value='period10'>Period-10</option><option value='break1'>Break-1</option><option value='break2'>Break-2</option></select></label></td>";
        var col2="<td width='16%' height='30' class='alignLeft'><label><select name='subject' id='subject'	style='width: 240px' > <option selected></option> <c:forEach items='${listSubject}' var='listSubject'><option value='${listSubject.subjectname}'><c:out value='${listSubject.subjectname}' /></option></c:forEach></select></label></td>";
        var col3="<td class='dataTextInActive'><label> <select name='periodstarttimehr' id='periodstarttimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select> <select name='periodstarttimemin' id='periodstarttimemin' style='width: 60px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodstarttimeam' id='periodstarttimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
        var col4="<td class='dataTextInActive'><label> <select name='periodendtimehr' id='periodendtimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select></label><label> <select name='periodendtimemin' id='periodendtimemin' style='width: 80px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodendtimeam' id='periodendtimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+"</tr>");
        $(function() {
            $("#dataTable").find('tbody').append(newRow);
        });
    }
	 
	 function addRowNew() {
	        var rowCount = document.getElementById('dataTable').rows.length;    
	        var col1="<td width='16%' height='30' class='alignLeft'><label><select name='periods' id='periods' style='width: 180px'><option selected value='period1'>Period-1</option><option value='period2'>Period-2</option><option value='period3'>Period-3</option><option value='period4'>Period-4</option><option value='period5'>Period-5</option><option value='period6'>Period-6</option><option value='period7'>Period-7</option><option value='period8'>Period-8</option><option value='period9'>Period-9</option><option value='period10'>Period-10</option><option value='break1'>Break-1</option><option value='break2'>Break-2</option></select></label></td>";
	        var col2="<td width='16%' height='30' class='alignLeft'><label><select name='subject' id='subject'	style='width: 240px' > <option selected></option> <c:forEach items='${listSubject}' var='listSubject'><option value='${listSubject.subjectname}'><c:out value='${listSubject.subjectname}' /></option></c:forEach></select></label></td>";
	        var col3="<td class='dataTextInActive'><label> <select name='periodstarttimehr' id='periodstarttimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select> <select name='periodstarttimemin' id='periodstarttimemin' style='width: 60px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodstarttimeam' id='periodstarttimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
	        var col4="<td class='dataTextInActive'><label> <select name='periodendtimehr' id='periodendtimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select></label><label> <select name='periodendtimemin' id='periodendtimemin' style='width: 80px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodendtimeam' id='periodendtimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
	        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+"</tr>");
	        $(function() {
	            $("#dataTableNew").find('tbody').append(newRow);
	        });
	    }
	 
	 function addColumn(count) {
	        var i;
	        var newCol;
	        for(i=0;i<count;i++){
	        	var col1="<th class='timeth'><label><select name='periods' id='periods' style='width: 80px'><option selected value='period1'>Period-1</option><option value='period2'>Period-2</option><option value='period3'>Period-3</option><option value='period4'>Period-4</option><option value='period5'>Period-5</option><option value='period6'>Period-6</option><option value='period7'>Period-7</option><option value='period8'>Period-8</option><option value='period9'>Period-9</option><option value='period10'>Period-10</option><option value='break1'>Break-1</option><option value='break2'>Break-2</option><option value='leisure'>Leisure</option></select></label><br><br>"
	        	+"<label><select name='subject' id='subject' style='width: 80px'><option selected></option><c:forEach items='${listSubject}' var='listSubject'><option value='${listSubject.subjectname}'><c:out value='${listSubject.subjectname}' /></option></c:forEach></select></label><br><br>"
	        	+"<label><select name='staff' id='staff' style='width: 80px'><option selected></option><c:forEach items='${employeeList}' var='employeeList'><option value='${employeeList.teachername}'><c:out value='${employeeList.teachername}' /></option></c:forEach></select></label><br><br>"
	        	+"<label> <select name='periodstarttimehr' id='periodstarttimehr' style='width: 40px'><option selected value='00'>Hr</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select> <select name='periodstarttimemin' id='periodstarttimemin' style='width: 40px'><option selected value='00'>Min</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodstarttimeam' id='periodstarttimeam' style='width: 40px'><option selected></option><option value='AM'>AM</option><option value='PM'>PM</option></select></label><br>"
	        	+"<label>TO</label><br>"
	        	+"<label> <select name='periodendtimehr' id='periodendtimehr' style='width: 40px'><option selected value='00'>Hr</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select></label><label> <select name='periodendtimemin' id='periodendtimemin' style='width: 40px'><option selected value='00'>Min</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodendtimeam' id='periodendtimeam' style='width: 40px'><option selected></option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></th>";
		        /* var col2="<td width='16%' height='30' class='alignLeft'><label><select name='subject' id='subject'	style='width: 240px' > <option selected></option> <c:forEach items='${listSubject}' var='listSubject'><option value='${listSubject.subjectname}'><c:out value='${listSubject.subjectname}' /></option></c:forEach></select></label></td>";
		        var col3="<td class='dataTextInActive'><label> <select name='periodstarttimehr' id='periodstarttimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select> <select name='periodstarttimemin' id='periodstarttimemin' style='width: 60px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodstarttimeam' id='periodstarttimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
		        var col4="<td class='dataTextInActive'><label> <select name='periodendtimehr' id='periodendtimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select></label><label> <select name='periodendtimemin' id='periodendtimemin' style='width: 80px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodendtimeam' id='periodendtimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>"; */
		        newCol +=col1;
	        }
	        var colA="<th class='timeth'><label><select name='days' id='days' style='width: 60px'><option selected value='monday'>MON</option><option value='tuesday'>TUE</option><option value='wednesday'>WED</option><option value='thursday'>THU</option><option value='friday'>FRI</option><option value='saturday'>SAT</option><option value='sunday'>SUN</option></select></label></th>";
	        var newRow = $("<tr class='trClass'>"+colA+newCol+"</tr>");
	        $(function() {
	            $("#dataTableNew").find('thead').append(newRow);
	        });
	       /*  var col1="<td width='16%' height='30' class='alignLeft'><label><select name='periods' id='periods' style='width: 180px'><option selected value='period1'>Period-1</option><option value='period2'>Period-2</option><option value='period3'>Period-3</option><option value='period4'>Period-4</option><option value='period5'>Period-5</option><option value='period6'>Period-6</option><option value='period7'>Period-7</option><option value='period8'>Period-8</option><option value='period9'>Period-9</option><option value='period10'>Period-10</option><option value='break1'>Break-1</option><option value='break2'>Break-2</option></select></label></td>";
	        var col2="<td width='16%' height='30' class='alignLeft'><label><select name='subject' id='subject'	style='width: 240px' > <option selected></option> <c:forEach items='${listSubject}' var='listSubject'><option value='${listSubject.subjectname}'><c:out value='${listSubject.subjectname}' /></option></c:forEach></select></label></td>";
	        var col3="<td class='dataTextInActive'><label> <select name='periodstarttimehr' id='periodstarttimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select> <select name='periodstarttimemin' id='periodstarttimemin' style='width: 60px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodstarttimeam' id='periodstarttimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
	        var col4="<td class='dataTextInActive'><label> <select name='periodendtimehr' id='periodendtimehr' style='width: 60px'><option selected value='00'>Hour</option><option value='1'>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option><option value='6'>6</option><option value='7'>7</option><option value='8'>8</option><option value='9'>9</option><option value='10'>10</option><option value='11'>11</option><option value='12'>12</option></select></label><label> <select name='periodendtimemin' id='periodendtimemin' style='width: 80px'><option selected value='00'>Minuts</option><option value='00'>00</option><option value='05'>05</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option><option value='25'>25</option><option value='30'>30</option><option value='35'>35</option><option value='40'>40</option><option value='45'>45</option><option value='50'>50</option><option value='55'>55</option></select> <select name='periodendtimeam' id='periodendtimeam' style='width: 60px'><option selected>AM</option><option value='AM'>AM</option><option value='PM'>PM</option></select></label></td>";
	        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+"</tr>");
	        $(function() {
	            $("#dataTable").find('tbody').append(newRow);
	        }); */
	    }
	 
	function savePeriods() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=PeriodProcess&action=savePeriods";
		form1.method = "POST";
		form1.submit();
	}
	
	function getBreaks(){
		
	    var noOfBreaks = document.getElementById("totalbreaks");
        var noOfB = noOfBreaks.options[noOfBreaks.selectedIndex].text;
        
        if(noOfB == 1){
        	document.getElementById("tr1").style.display = '';	
        	document.getElementById("tr2").style.display = "none";
        	document.getElementById("tr3").style.display = "none";
        }else if(noOfB == 2){
        	document.getElementById("tr1").style.display = '';
        	document.getElementById("tr2").style.display = '';
        	document.getElementById("tr3").style.display = "none";
        }else if(noOfB == 3){
        	document.getElementById("tr1").style.display = '';
        	document.getElementById("tr2").style.display = '';
        	document.getElementById("tr3").style.display = '';
        }
	}
	
	function deleteRow(tableID) {
		  try {
           var table = document.getElementById(tableID);
           var rowCount = table.rows.length;
           if(rowCount==0){
               alert('No records to delete');
               return;
           }
           table.deleteRow(rowCount-1);
           
       }catch(e) {
           alert(e);
       }
       }
	
	function getColumns(){
		
		for(var i=0; i<=6; i++){
			addColumn(document.getElementById("totalperiods").value);	
		}
		
	}
	
	function deleteRecord(){
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=PeriodProcess&action=deletePeriods";
		form1.method = "POST";
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
	<form id="form1" method="POST">

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Staff's List</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Staff Name<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
						<th title="click to sort" class="headerText">Department<img
							alt=" " style="position: relative; top: 4px;"
							src="css/dataTable/images/sort_both.png" /></th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${employeeList}" var="employeelist">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox" id="<c:out value="${employeelist.tid}"/>" class="chcktbl" name="staffid" value="<c:out value="${employeelist.tid}"/>" /></td>
							<td class="dataText"><a class="dataTextInActive" href="Controller?process=PeriodProcess&action=viewTeacherTimeTable&teachername=<c:out value="${employeelist.teachername}" />"><c:out value="${employeelist.teachername}" /></a></td>
							<td class="dataText"><c:out value="${employeelist.department}" /></td>
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
