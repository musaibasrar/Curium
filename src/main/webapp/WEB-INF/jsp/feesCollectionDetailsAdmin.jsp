<%--
    Document   : Fees Collecion Details
    Created on : Dec 23, 2011, 5:52:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fees Collecion Details</title>
<link rel="stylesheet" href="/sanmarg/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/sanmarg/css/datePicker/demos.css">
<link rel="stylesheet" href="/sanmarg/css/font-awesome.css">
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

.alignLeft {
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
.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failure {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.successpaymenttype {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failurepaymenttype {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.small-button {
    font-size: 8px;
    padding: 2px 6px;
    height: auto; /* optional */
}
</style>
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/sanmarg/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/sanmarg/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/sanmarg/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/sanmarg/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/sanmarg/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/sanmarg/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/sanmarg/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/sanmarg/js/datePicker/ui/ScrollableGridPlugin.js"></script>
<script type="text/javascript" charset="utf-8">
$(document).ready(function() {
    $('#myTable').dataTable( {
        "sScrollY": "380px",
        "scrollCollapse": true,
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
<script type="text/javascript">
	function select(id, name) {
		var clipEffect = 'blind';
		var options = {};

		$("#effect").show();
		$("#medicineId").val(id)
		$("#medicineName").val(name);

	}
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
<script type="text/javascript" src="/sanmarg/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	function searchByDate() {
		var form1 = document.getElementById("form1");
		form1.action = "/sanmarg/UserProcess/searchByDate";
		form1.method = "POST";
		form1.submit();

	}
	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/sanmarg/FeesDetails/printDataForFees";
		form1.method = "POST";
		form1.submit();
	}
	
	
	$(function() {

		$("#tabs").tabs();
		$("#search").button().click(function() {
			searchByDate();
		});
		$("#effect").hide();

	});
	
	 $(function(){
		 $("#export").button({
				
			});
         $("#delete").button({
             icons:{
                 primary: "ui-icon-trash"
             }
         }).click(function(){
             deleteRecords();
             return false;

         });
         
 		$("#print").button({
            icons:{
                primary: "ui-icon-print"
            }
        }).click(function(){
            printRecords();
            return false;

        });
 		
 		$(".button").button({
            icons:{
                primary: "ui-icon-print"
            }
        }).addClass("small-button");
 		
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
         

     });
	 

	 function checkFieldsButton() {
		 
			
			var oneday = document.getElementById('datepicker').value;
			var fromdate = document.getElementById('datepickerfrom').value;
			var todate = document.getElementById('datepickerto').value;
			
			if(oneday == "" && fromdate == "" && todate == ""){
				alert("Please enter the search criteria");
			}
		
			if(fromdate > todate ){
				alert('"To date" should be greater than "From date"');
			}
			
		}

	 
	 function checkFieldsTo() {
		 
			
				document.getElementById('datepicker').value = "";
				
				
			}

	 
	 function checkFieldsFrom() {
		 
		 document.getElementById('datepicker').value = "";
		 document.getElementById('datepickerto').value = "";
		}
	 
	 
	 function checkFields() {

			document.getElementById('datepickerfrom').value = "";
			document.getElementById('datepickerto').value = "";
			
			
		}
</script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepicker").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerfrom").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepickerfrom").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		$("#datepickerto").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'yy-mm-dd'
		});
		$("#anim").change(function() {
			$("#datepickerto").datepicker("option", "showAnim", $(this).val());
		});
		
		 $("#chequedate").datepicker({
 			changeYear : true,
 			changeMonth : true,
 			dateFormat: 'dd/mm/yy',
 			yearRange: "-50:+0"
 		});
 		$("#anim").change(function() {
 			$("#chequedate").datepicker("option", "showAnim", $(this).val());
 		});
 		
		 $("#transferdate").datepicker({
    			changeYear : true,
    			changeMonth : true,
    			dateFormat: 'dd/mm/yy',
    			yearRange: "-50:+0"
    		});
    		$("#anim").change(function() {
    			$("#transferdate").datepicker("option", "showAnim", $(this).val());
    		});
		
		$("#submitbtn").button().click(function(){
         	 $( "#dialogpaymentmethod" ).dialog( "open" );
              return false;

          });
          
          $(function() {
              $( "#dialogpaymentmethod" ).dialog({
                  autoOpen: false,
                  height: 230,
                  width: 550,
                  modal: true,
                  buttons: {
                      OK: function() {
                      	
                      			submitfees(document.getElementById("cashpayment"),document.getElementById("banktransfer"),
                      					document.getElementById("chequetransfer"), document.getElementById("ackno"), 
                      			document.getElementById("transferdate"), document.getElementById("transferbankname"),
                      			document.getElementById("chequeno"), document.getElementById("chequedate"), document.getElementById("chequebankname"));
                          		$( this ).dialog( "close" );
                   		   }
                  }
              });
          });
	});
</script>


	<script type="text/javascript">
					
					var cancelreceipt = '<c:out default="" value="${cancelreceiptresult}"/>';
		            
		            if(cancelreceipt == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(cancelreceipt == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
		            
				var updatereceiptpaymentmethod = '<c:out default="" value="${updatereceiptpaymentmethod}"/>';
		            
		            if(updatereceiptpaymentmethod == "true"){
		            	 $(function(){
		            		 $( "div.successpaymenttype" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            	 });
		            	 }else if(updatereceiptpaymentmethod == "false"){
		            	  $(function(){
		            		 $( "div.failurepaymenttype" ).fadeIn( 800 ).delay( 2000 ).fadeOut( 1400 );
		            		 });
		            	 }
		            
		            
		            
		            function openPaymentDialog(receiptnumber) {
		                // Set the hidden field with the selected receipt number
		                document.getElementById("selectedReceiptnumber").value = receiptnumber;

		                // Open the dialog (assuming you're using jQuery UI Dialog or similar)
		                $("#dialogpaymentmethod").dialog("open");
		            }
            
		            
		            function selectPayment(id){
		            	
		            	
		            	if(id == 'cashpayment'){
		            		
		            		
		            		document.getElementById('onlinechequeack').style.display = "none";
		            		document.getElementById('onlinechequedate').style.display = "none";
		            		document.getElementById('onlinechequebank').style.display = "none";
		            		
		            		document.getElementById('onlinetransferack').style.display = "none";
		            		document.getElementById('onlinetransferdate').style.display = "none";
		            		document.getElementById('onlinetransferbank').style.display = "none";
		            		
		            		document.getElementById('ackno').style.display = '';
		            		document.getElementById('transferdate').style.display = '';
		            		document.getElementById('transferbankname').style.display = '';
		            		document.getElementById('chequeno').style.display = '';
		            		document.getElementById('chequedate').style.display = '';
		            		document.getElementById('chequebankname').style.display = '';
		            		
		            			
		            	}else if(id == 'banktransfer'){
		            		
		            		
		            		document.getElementById('onlinechequeack').style.display = "none";
		            		document.getElementById('onlinechequedate').style.display = "none";
		            		document.getElementById('onlinechequebank').style.display = "none";
		            		
		            		document.getElementById('onlinetransferack').style.display = '';
		            		document.getElementById('onlinetransferdate').style.display = '';
		            		document.getElementById('onlinetransferbank').style.display = '';
		            		
		            		
		            		document.getElementById('chequeno').style.display = '';
		            		document.getElementById('chequedate').style.display = '';
		            		document.getElementById('chequebankname').style.display = '';
		            		
		            		
		            	}else if(id == 'chequetransfer'){
		            		
		            		document.getElementById('onlinechequeack').style.display = '';
		            		document.getElementById('onlinechequedate').style.display = '';
		            		document.getElementById('onlinechequebank').style.display = '';
		            		
		            		document.getElementById('onlinetransferack').style.display = "none";
		            		document.getElementById('onlinetransferdate').style.display = "none";
		            		document.getElementById('onlinetransferbank').style.display = "none";
		            		
		            		document.getElementById('ackno').style.display = '';
		            		document.getElementById('transferdate').style.display = '';
		            		document.getElementById('transferbankname').style.display = '';
		            	}
		            	
		            }
		            
		            
		            function submitfees(cashpayment,banktransfer,chequetransfer,ackno,transferdate,transferbankname,chequeno,chequedate,chequebankname){
		            	
		            	var paymentmethodvalue = '';
		            	var cashpaymentvalue = '';
		            	var acknovalue = '';
		            	var transferdatevalue = '';
		            	var transferbanknamevalue = '';
		            	var chequenovalue = '';
		            	var chequedatevalue = '';
		            	var chequebanknamevalue = '';
		            	
		            	if(banktransfer.checked == true ){
		            		paymentmethodvalue = 'banktransfer';
		            	}
		            	
		            	if(chequetransfer.checked == true){
		            		paymentmethodvalue = 'chequetransfer';
		            	}
		            	
		            	if(cashpayment.checked == true){
		            		paymentmethodvalue = 'cashpayment';
		            	}
		            	
		            	if(ackno!=null){
		            		acknovalue = ackno.value;
		            	}
		            	
		            	if(transferdate!=null){
		            		transferdatevalue = transferdate.value;
		            	}
		            	
		            	if(transferbankname!=null){
		            		transferbanknamevalue = transferbankname.value;
		            	}
		            	if(chequeno!=null){
		            		chequenovalue = chequeno.value;
		            	}
		            	if(chequedate!=null){
		            		chequedatevalue = chequedate.value;
		            	}
		            	if(chequebankname!=null){
		            		chequebanknamevalue = chequebankname.value;
		            	}
		            	
		            	var receiptnumber = document.getElementById("selectedReceiptnumber").value;
		            	
		            	var form1 = document.getElementById("form1");
		        		form1.action="/sanmarg/FeesCollection/feesPaymentTypeModify?receiptnumber="+receiptnumber+"&paymentmethod="+paymentmethodvalue+"&ackno="+acknovalue+"&transferdate="+transferdatevalue+"&transferbankname="+transferbanknamevalue+"&chequeno="+chequenovalue+"&chequedate="+chequedatevalue+"&chequebankname="+chequebanknamevalue+"";
		        		form1.method = "POST";
		        		form1.submit();
		        		
		            }
        </script>
        
</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/sanmarg/UserProcess/sessionTimeOut");
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
		action="/sanmarg/FeesDetails/exportDataForFees" method="POST">
		
		<div class="alert-box success">Receipt has been cancelled successfully!!!</div>
		<div class="alert-box failure">Receipt cancellation failed, Please try again!!!</div>
		
		<div class="alert-box successpaymenttype">Receipt payment method has been updated successfully!!!</div>
		<div class="alert-box failurepaymenttype">Receipt payment method update failed! Please try again!!!</div>
		
		<div style="height: 28px">
			<button id="add">Search Fees Collection Details</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Dates</a></li>

				</ul>
				<div id="tabs-1">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" id="table1" style="display: block">
						<tr>
							<td width="20%" class="alignRight">Date: &nbsp;</td>
							<td width="28%"><label> <input name="oneday"
									type="text" class="textField" id="datepicker" size="36"
									onfocus="checkFields()" value="${dayone}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
						</tr>

						<tr>
							<td><br /></td>
						</tr>
						<tr>
						<td width="20%" class="alignRight">&nbsp;Between Dates</td>
						</tr>
						
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td width="20%" class="alignRight">From Date:  &nbsp;&nbsp;</td>
							<td ><label> <input name="fromdate"
									type="text" class="textField" id="datepickerfrom" size="36"
									onfocus="checkFieldsFrom()" value="${datefrom}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
							<td class="alignLeft"> &nbsp;&nbsp; &nbsp;&nbsp;To Date:</td>
							<td ><label> <input name="todate"
									type="text" class="textField" id="datepickerto" size="36"
									onfocus="checkFieldsTo()" value="${dateto}" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
						</tr>
						
						<tr>
						<td>&nbsp;</td>
						</tr>
						<tr>
						<td>&nbsp;</td>
						</tr>
						
						<tr>
							<td width="20%" class="alignRight">Mode of Payment &nbsp;&nbsp;</td>
							<td ><label> <select name="modeofpayment"
									id="modeofpayment" style="width: 240px">
										<option selected></option>
										<option value="Bank Transfer">Bank</option>
										<option value="Cash">Cash</option>
								</select>

							</label></td>
							
						</tr>
							
						<tr>
						<td>&nbsp;</td>
						</tr>
						
						<tr>
						<td>&nbsp;</td>
						</tr>
						
						<!-- <tr>
							<td width="20%" class="alignRight">Select Branch  &nbsp;&nbsp;</td>
							<td ><label> <select name="selectedbranchid"
									id="selectedbranchid" style="width: 240px" required>
										<option selected></option>
										<option value="2:Boys High School">Boys High School</option>
										<option value="3:Girls High School">Girls High School</option>
										<option value="4:P.U. College">P.U. College</option>
										<option value="5:Degree College">Degree College</option>
								</select>

							</label></td>
							
						</tr>
							
						<tr>
						<td>&nbsp;</td>
						</tr>
						
						<tr>
						<td>&nbsp;</td>
						</tr> -->
					</table>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button onmouseover="checkFieldsButton();" id="search">Search</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">
					<input type="hidden" id="selectedReceiptnumber" name="receiptnumber" />
					<label style="color: #EB6000;">${branchname} </label>${feesdetailsbranchname}&nbsp;&nbsp;&nbsp; <label style="color: #EB6000;">total fees :</label>Rs. ${sumofonlyfee}
					&nbsp;&nbsp;&nbsp; <label style="color: #EB6000;">total fine :</label>Rs. ${sumoffine}&nbsp;&nbsp;&nbsp; <label style="color: #EB6000;">total Misc. :</label>Rs. ${sumofmisc}
					&nbsp;&nbsp;&nbsp; <label style="color: #EB6000;">Grand Total :</label>Rs. ${sumofdetailsfees}
					
					</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                        <tr>
                            <th class="headerText"><input type="checkbox" id="chckHead" /></th>
                            <th title="click to sort" class="headerText">Date of fees</th>
                            <th title="click to sort" class="headerText">Student Name</th>
                            <th title="click to sort" class="headerText">Class</th>
                            <th title="click to sort" class="headerText">Receipt No.</th>
                            <th title="click to sort" class="headerText">Fee</th>
                            <th title="click to sort" class="headerText">Fine</th>
                            <th title="click to sort" class="headerText">Misc</th>
                            <th title="click to sort" class="headerText">Narration</th>
                            <th title="click to sort" class="headerText">Grand Total</th>
                            <th title="click to sort" class="headerText">Payment Mode</th>
                            <th title="click to sort" class="headerText">View Details</th>
                            <th title="click to sort" class="headerText">Modify</th>
                            <th title="click to sort" class="headerText">Cancel Receipt</th>


                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${searchfeesdetailslist}" var="feesdetails">

                            <tr class="trClass" >
                                <td class="dataText"><input type="checkbox" checked="checked"
								id="<c:out value="${feesdetails.key.receiptnumber}"/>" class="chcktbl"
								name="feesIDs"
								value="<c:out value="${feesdetails.key.receiptnumber}"/>" /></td>
                                <td  class="dataText"><c:out value="${feesdetails.key.date}"/></td>
                                <td  class="dataText"><c:out value="${feesdetails.value.student.name}"/></td>
                                <td  class="dataText"><c:out value="${feesdetails.value.student.classstudying}"/></td>
                                <td  class="dataText"><c:out value="${feesdetails.key.branchreceiptnumber}"/></td>
                                <td class="dataText"><c:out value="${feesdetails.key.totalamount-feesdetails.key.fine-feesdetails.key.misc}"/></td>
                                <td class="dataText"><c:out value="${feesdetails.key.fine}"/></td>
                                <td class="dataText"><c:out value="${feesdetails.key.misc}"/></td>
                                <td  class="dataText"><c:out value="${feesdetails.value.student.remarks}"/></td>
                                <td class="dataText"><c:out value="${feesdetails.key.totalamount}"/></td>
                                <td  class="dataText"><c:out value="${feesdetails.key.paymenttype}"/></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="/sanmarg/FeesCollection/ViewDetails?id=<c:out value='${feesdetails.key.receiptnumber}'/>&sid=<c:out value='${feesdetails.key.sid}'/>">View Details</a></td>
                                <td  class="dataText"> <a href="javascript:void(0);" class="dataTextInActive" onclick="openPaymentDialog('<c:out value="${feesdetails.key.receiptnumber}"/>')"> <i class="fa fa-edit" style="color: #004080; font-size: 16px;"></i></a></td>
                                <td  class="dataTextInActive"><a class="dataTextInActive" href="/sanmarg/FeesCollection/CancelFeesReceipt?id=<c:out value='${feesdetails.key.receiptnumber}'/>&sid=<c:out value='${feesdetails.key.sid}'/>&receiptid=<c:out value='${feesdetails.key.receiptvoucher}'/>&journalid=<c:out value='${feesdetails.key.journalvoucher}'/>"><i class="fa fa-times" style="color:#93051f;font-size: 18px;"></i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                    <tfoot>
					<tr>
													
						<td class="footerTD" colspan="2"> &nbsp;
						<!-- <label style="font-weight: bold;font-size: 14px;font-family: cursive;color: white;">File Name</label>
						<label> <input
									name="fileName" type="text" class="myclass" id="fileName"
									size="20">
							</label> -->
							<input value="Print" style="width: 35px;"
							id="print"/>
						<input value="Export"
							type="submit" id="export"/></td>
							
							

					</tr>
				</tfoot>
			</table>

		</div>
	<div id="dialogpaymentmethod" title="Payment Method">
				
           		 
           		 <table style="width: auto;height: auto;">
						
						<tr>
							<td>Payment method &nbsp;</td>
						
							<td>
							
								<input type="radio" id="cashpayment" name="paymentmethod" value="cashpayment" onclick="selectPayment(this.id)">
								<label for="cashpayment">Cash</label>
									
								<input type="radio" id="banktransfer" name="paymentmethod" value="banktransfer" onclick="selectPayment(this.id)">
								<label for="banktransfer">Bank Transfer</label>
								
								<input type="radio" id="chequetransfer" name="paymentmethod" value="chequetransfer" onclick="selectPayment(this.id)">
								<label for="chequetransfer">Cheque</label>							
							
							</td>
							
							
						</tr>
						
						<tr>
							<td><br></td>
						</tr>
						<tr id="onlinetransferack" style="display: none;">
							<td></td>
						
							<td>
								Acknowledgement # &nbsp;<input type="text" id="ackno" name="ackno" style="width: 175px;">														
							</td>
							
						</tr>
						<tr id="onlinetransferdate" style="display: none;">
							<td></td>
						
							<td>
							Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text"  name="transferdate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transferdate" autocomplete="false" required
									data-validate="validate(required)">
								
							</td>
							
						</tr>
						
						<tr id="onlinetransferbank" style="display: none;">
							<td></td>
						
							<td>Bank&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
									<select name="transferbankname" id="transferbankname" class="dropdownlist" style="font-size: 14px;width: 175px;" required>
											<option value="bank">Bank</option>
								</select>
							
							</label>
							
							</td>
							
						</tr>
						
						
						<tr>
							<td><br></td>
						</tr>
						<tr id="onlinechequeack" style="display: none;">
							<td></td>
						
							<td>
								Cheque # &nbsp;<input type="text" id="chequeno" name="chequeno" style="width: 175px;">														
							</td>
							
						</tr>
						<tr id="onlinechequedate" style="display: none;">
							<td></td>
						
							<td>
							Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text"  name="chequedate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="chequedate" autocomplete="false" required
									data-validate="validate(required)">
								
							</td>
							
						</tr>
						
						<tr id="onlinechequebank" style="display: none;">
							<td></td>
						
							<td>Bank&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label>
									<select name="chequebankname" id="chequebankname" class="dropdownlist" style="font-size: 14px;width: 175px;" required>
											<option value="bank">Bank</option>
								</select>
							
							</label>
							
							</td>
							
						</tr>
						
					</table>
			</div>

	</form>

</body>
</html>