<%--
    Document   : Supplier Payments
    Created on : Jan 14, 2021, 8:51:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Supplier Payments</title>
<link rel="stylesheet" href="css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="css/datePicker/demos.css">
<style type="text/css">
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
	width: 220px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
}

.textfieldvaluesshorts{

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
	width: 80px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
}

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: #E3EFFF;
}


.textfieldvalues{

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
	width: 220px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
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


.alignRight {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #325F6D;
	text-align: left;
	vertical-align: middle;
	font-weight: bold;
}

.dropdownlist{
	width: 220px;
	height:27px;
	border-radius: 5px;
	background-color: white;
}


.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

.successsupplierpayment {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failuresupplierpayment {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.successchequedelivered {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failurechequedelivered {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.successchequecleared {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failurechequecleared {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}

.successchequecancelled {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}

.failurechequecancelled {
    color: #a94442;
    background-color: #f2dede;
    border-color: #ebccd1;
    display: none;
}



.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}

.buttonred {
  background-color: red; /* Green */
  border: none;
  color: white;
  padding: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 12px;
}


.textfieldvaluesshort{

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
	width: 80px;
	height: 25px;
	border-radius: 5px;
	background-color: white;
	 
}

</style>
<script type="text/javascript" src="js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript"
	src="js/datePicker/ui/jquery.ui.accordion.js"></script>
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
	$(function() {
		$("#transactiondate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#transactiondate").datepicker("option", "showAnim", $(this).val());
		});
		$("#delivereddate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#delivereddate").datepicker("option", "showAnim", $(this).val());
		});
		$("#cleareddate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange: "-50:+0"
		});
		$("#anim").change(function() {
			$("#cleareddate").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function issuecheque() {
		var form1 = document.getElementById("form1");
		if(form1.checkValidity()) {
			form1.action = "Controller?process=MessSuppliersProcess&action=issueCheque";
			form1.method = "POST";
			form1.submit();
		}
	}
	
	function cancelcheque() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=MessSuppliersProcess&action=cancelCheque";
		form1.method = "POST";
		form1.submit();

	}
	
	function deliveredcheque(delivereddate) {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=MessSuppliersProcess&action=deliveredCheque&deliverydate="+delivereddate;
		form1.method = "POST";
		form1.submit();
	}
	
	function clearedcheque(cleareddate,bankname) {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=MessSuppliersProcess&action=clearedCheque&cleardate="+cleareddate+"&bankname="+bankname+"";
		form1.method = "POST";
		form1.submit();
	}
	
	function printRecords(){
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=MessSuppliersProcess&action=printSupplierPayment";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#issuecheque").button().click(function() {
			issuecheque();
		});
		$("#effect").hide();
		
		 $(function() {
             $( "#dialogdeliverydate" ).dialog({
                 autoOpen: false,
                 height: 130,
                 width: 350,
                 modal: true,
                 buttons: {
                     OK: function() {
                    	 deliveredcheque(document.getElementById("delivereddate").value);
                         $( this ).dialog( "close" );
                     }

                 }
             });
         });
		 
		 $(function() {
             $( "#dialogcleardate" ).dialog({
                 autoOpen: false,
                 height: 130,
                 width: 550,
                 modal: true,
                 buttons: {
                     OK: function() {
                    	 clearedcheque(document.getElementById("cleareddate").value, document.getElementById("bankname").value);
                         $( this ).dialog( "close" );
                     }

                 }
             });
         });


	});
	
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
	
	
	var xmlHttp;
    var count;
    function getBalance() {

		var selected=document.getElementById('supplierid').value;

			 if (typeof XMLHttpRequest != "undefined") {
				 xmlHttp = new XMLHttpRequest();
	            
	         } else if (window.ActiveXObject) {
	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	             
	         }
			xmlHttp.onreadystatechange = stateChanged;
			xmlHttp.open("GET", "AjaxController?process=SupplierBalance&action=getSupplierBalance&supplierid="+selected,true);
			xmlHttp.send(null);
		
	}
    
	function stateChanged() {

		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
			document.getElementById("supplierbalance").innerHTML = xmlHttp.responseText;
			
            document.getElementById("balanceold").style.display = "none";
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
	
	
	 $(function(){
		 
		
         $("#cancel").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             cancelcheque();
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
         
         $("#approve").button({
             icons:{
                 primary: "ui-icon-check"
             }
         }).click(function(){
        	 $( "#dialogcleardate" ).dialog( "open" );
             return false;

         });
         
         $("#deliver").button({
             icons:{
                 primary: "ui-icon-suitcase"
             }
         }).click(function(){
        	 $( "#dialogdeliverydate" ).dialog( "open" );
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
         
         $("#chequeamount").keypress(function (e) {
		     //if the letter is not digit then display error and don't type anything
		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		               return false;
		    }
		   });
         
     });
     
     
     function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            if(rowCount==1){
                alert('No records to delete');
            }
            for(var i=1; i<rowCount-1; i++) {
                var row = table.rows[i];
                var chkbox = row.cells[0].childNodes[0];
                if(null != chkbox && true == chkbox.checked) {
                    table.deleteRow(i);
                    rowCount--;
                    i--;
                }
            }
           
        }catch(e) {
            alert(e);
        }
    }
     
     function numberWithCommas(chequeamount) {
     	var x=chequeamount.value;
     	x = x.replace (/,/g, "");
     	
     	var lastThree = x.substring(x.length-3);
     	var otherNumbers = x.substring(0,x.length-3);
     	if(otherNumbers != '')
     	    lastThree = ',' + lastThree;
     	var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree;
     	chequeamount.value = res;
     }
    
</script>

 <script type="text/javascript">
					

					var supplierpaymentissued = '<c:out default="" value="${supplierpaymentissued}"/>';
					var chequedelivered = '<c:out default="" value="${chequedelivered}"/>';
				 	var chequecleared = '<c:out default="" value="${chequecleared}"/>';
				 	var chequecancelled = '<c:out default="" value="${chequecancelled}"/>';
				 	
 
				 if(supplierpaymentissued == "true"){
				 	 $(function(){
				 		 $( "div.successsupplierpayment" ).fadeIn( 800 ).delay( 2000 );
				 	 });
				 	 }else if(supplierpaymentissued == "false"){
				 	  $(function(){
				 		 $( "div.failuresupplierpayment" ).fadeIn( 800 ).delay( 2000 );
				 		 });
				 	 }else if(chequedelivered == "true"){
				        	 $(function(){
				        		 $( "div.successchequedelivered" ).fadeIn( 800 ).delay( 2000 );
				      	 });
				     }else if(chequedelivered == "false"){
				        	  $(function(){
				        		 $( "div.failurechequedelivered" ).fadeIn( 800 ).delay( 2000 );
				     	 });
				     }else if(chequecleared == "true"){
				           	 $(function(){
				            		 $( "div.successchequecleared" ).fadeIn( 800 ).delay( 2000 );
				       	 });
				     }else if(chequecleared == "false"){
				         	  $(function(){
				            		 $( "div.failurechequecleared" ).fadeIn( 800 ).delay( 2000 );
				       });
				      }else if(chequecancelled == "true"){
					        $(function(){
					            		 $( "div.successchequecancelled" ).fadeIn( 800 ).delay( 2000 );
					     });
					    }else if(chequecancelled == "false"){
					      	  $(function(){
					            		 $( "div.failurechequecancelled" ).fadeIn( 800 ).delay( 2000 );
					        		 });
					    }
		        	
				 
				 function closediv(divid){
						var x = document.getElementById("div"+divid);
						  if (x.style.display === "none") {
						    x.style.display = "block";
						  } else {
						    x.style.display = "none";
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

	<form id="form1" method="POST">
		<%
			java.text.DateFormat df = new java.text.SimpleDateFormat(
					"MM/dd/yyyy");
		
		%>
		
		<div class="alert-box successsupplierpayment" id="div1">Supplier Payment has been issued successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failuresupplierpayment" id="div2">Supplier Payment failed, Please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div class="alert-box successchequedelivered" id="div3">Cheque delivered successfully!!!&nbsp;&nbsp;&nbsp;<button  class="button"  id="3" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failurechequedelivered" id="div4">Cheque delivery failed, Please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="4" onclick="closediv(this.id);">OK</button></div>
		
		<div class="alert-box successchequecleared" id="div5">Cheque cleared successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="5" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failurechequecleared" id="div6">Cheque clearance failed, Please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="6" onclick="closediv(this.id);">OK</button></div>
		
		<div class="alert-box successchequecancelled" id="div7">Cheque cancelled successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="7" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failurechequecancelled" id="div8">Cheque cancellation failed, Please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="8" onclick="closediv(this.id);">OK</button></div>
		
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div style="height: 28px">
			<button id="add">Supplier's Payment</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Details</a></li>

				</ul>
				<div id="tabs-1">
				
					<table style="margin-left: auto;margin-right: auto;">
					
						<tr>
							<td><br><br></td>
						</tr>
						
						<tr>
						<td class="alignRight">Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondate" autocomplete="false" required
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
							<td class="alignRight">Supplier&nbsp;</td>
							<td ><label>
									<select name="supplierid" id="supplierid" class="dropdownlist" style="font-size: 14px;" onchange="getBalance()" required>
											<option></option>
										<c:forEach items="${messsupplierslist}" var="messsupplierslist">
											<option value="${messsupplierslist.id}:${messsupplierslist.linkedledgerid}:${messsupplierslist.name}">${messsupplierslist.name}</option>
										</c:forEach>
								</select>
							
							</label></td>
						
							<td  class="alignRight">Balance &nbsp;</td>
							<td ><label id="supplierbalance"> <input name="balanceold" 
									type="text" class="textfieldvalues" id="balanceold" style="font-size: 14px;">
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td  class="alignRight">Cheque No. &nbsp;</td>
							<td ><label> <input name="chequeno" 
									type="text" class="textfieldvalues" id="chequeno" style="font-size: 14px;">
							</label></td>
						
							<td  class="alignRight">Amount &nbsp;</td>
							<td ><label> <input name="chequeamount" onkeyup="numberWithCommas(this);"
									type="text" class="textfieldvalues" id="chequeamount" style="font-size: 14px;" >
							</label></td>
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					<br>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="issuecheque">Issue</button>
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
					<td class="headerTD">Supplier Payment Details</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Issue Date</th>
						<th title="click to sort" class="headerText">Status</th>
						<th title="click to sort" class="headerText">Supplier Name</th>
						<th title="click to sort" class="headerText">Cheque #</th>
						<th title="click to sort" class="headerText">Amount</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${supplierpaymentlist}" var="supplierpaymentlist">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							
							<c:if test="${supplierpaymentlist.status == 'CLEARED'}">
								<td class="dataText"><input type="checkbox" id = "<c:out value="${supplierpaymentlist.id}"/>" class = "chcktbl"  name="supplierpaymentid"  value="<c:out value="${supplierpaymentlist.id}"/>" disabled="disabled"/></td>
							</c:if>
                          	
                          	<c:if test="${supplierpaymentlist.status == 'DELIVERED'}">
								<td class="dataText"><input type="checkbox" id = "<c:out value="${supplierpaymentlist.id}"/>" class = "chcktbl"  name="supplierpaymentid"  value="<c:out value="${supplierpaymentlist.id}"/>"/></td>
							</c:if>
							
							<c:if test="${supplierpaymentlist.status == 'ISSUED'}">
								<td class="dataText"><input type="checkbox" id = "<c:out value="${supplierpaymentlist.id}"/>" class = "chcktbl"  name="supplierpaymentid"  value="<c:out value="${supplierpaymentlist.id}"/>"/></td>
							</c:if>
							
							<c:if test="${supplierpaymentlist.status == 'CANCELLED'}">
								<td class="dataText"><input type="checkbox" id = "<c:out value="${supplierpaymentlist.id}"/>" class = "chcktbl"  name="supplierpaymentid"  value="<c:out value="${supplierpaymentlist.id}"/>" disabled="disabled"/></td>
							</c:if>
							
						  <td class="dataText" style="width: 10%;"><input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: center;" name="issuedate_${supplierpaymentlist.id}" value="<fmt:formatDate value="${supplierpaymentlist.issuedate}" pattern="dd/MM/yyyy"/>" readonly></td>
						  <td class="dataText">
						  <input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: center;" name="status_${supplierpaymentlist.id}" value="<c:out value="${supplierpaymentlist.status}" />" readonly>
						  </td>
						  <td class="dataText">
						  <c:set var="nameparts" value="${fn:split(supplierpaymentlist.externalid, '_')}" />
						  <input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: center;" name="externalid_${supplierpaymentlist.id}" value="<c:out value="${nameparts[0]}" />" readonly>
						  <input type="hidden"  name="supplierledgerid_${supplierpaymentlist.id}" value="<c:out value="${nameparts[1]}" />">
						  <input type="hidden"  name="issuevoucherid_${supplierpaymentlist.id}" value="<c:out value="${supplierpaymentlist.voucherid}" />">
						  </td>
						  <td class="dataText"><c:out value="${supplierpaymentlist.chequeno}" /></td>
						  
						  <td class="dataTextRight">
						  	<input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: right;width: 70px;" name="chequeamount_${supplierpaymentlist.id}" value="<fmt:formatNumber type="number"  maxFractionDigits = "2"  value="${supplierpaymentlist.amount}" />" readonly>
						 </td>
						</tr>
					</c:forEach>

				</tbody>
					<tfoot>
						<tr>
                            <!-- <td  class="footerTD" colspan="2" ><button id="delete" type="submit">Delete</button>  -->
                    		<td class="footerTD"  colspan="8">
                    		<button id="deliver">Delivered</button> 
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="approve">Cleared</button>
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="cancel">Cancel</button> 
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="print">Print</button>
                    		</td>
                        </tr>
                    </tfoot>
			</table>
			
			<div align="center">
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="Controller?process=MessSuppliersProcess&action=paymentSuppliers&page=${currentPage - 1}">Previous</a></td>
                </c:if>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <table border="0" cellpadding="5" cellspacing="5">
                    <tr>
                        <c:forEach begin="1" end="${noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td style="color: #1D599B;font-weight:bolder;font-size: 20px ">${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="Controller?process=MessSuppliersProcess&action=paymentSuppliers&page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="Controller?process=MessSuppliersProcess&action=paymentSuppliers&page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>

		</div>
			<div id="dialogdeliverydate" title="Delivery Date">
				
           		 
           		 <table style="width: auto;height: auto;">
						
						<tr>
						<td>Delivery Date &nbsp;</td>
						
							<td><label> <input type="text"  name="delivereddate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="delivereddate" autocomplete="false" required
									data-validate="validate(required)">
							</label>
							
							</td>
							
						</tr>
						
						</table>
			</div>
			
			<div id="dialogcleardate" title="Clear Date">
				
           		 
           		 <table style="width: auto;height: auto;">
						
						<tr>
							<td>Clear Date &nbsp;</td>
						
							<td><label> <input type="text"  name="cleareddate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="cleareddate" autocomplete="false" required
									data-validate="validate(required)">
							</label>
							
							</td>
							
							<td>Bank&nbsp;</td>
							<td ><label>
									<select name="bankname" id="bankname" class="dropdownlist" style="font-size: 14px;" required>
											<option value="axisbank">Axis Bank</option>
											<option value="canarabank">Canara Bank</option>
								</select>
							
							</label></td>
							
						</tr>
						
						</table>
			</div>

	</form>
</body>
</html>
