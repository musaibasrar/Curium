<%--
    Document   : Issuestock
    Created on : Nov 24, 2020, 8:42:28 PM
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
<title>Issue Stock</title>
<link rel="stylesheet" href="/dolphin/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/dolphin/css/datePicker/demos.css">

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

.headerTextLeft {
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
	text-align: left;
	background-image:
		url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
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

.dataTextRight {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
	background-color: #E3EFFF;
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

.dataTextLeft {
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: left;
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

</style>
<script type="text/javascript" src="/dolphin/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/dolphin/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/dolphin/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/dolphin/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/dolphin/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript"
	src="/dolphin/js/datePicker/ui/jquery.ui.accordion.js"></script>
	
	<script src="/dolphin/js/bootstrap.min.js"></script>
<link href="/dolphin/css/select2.min.css" rel="stylesheet" />
<script src="/dolphin/js/select2.min.js"></script>

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
		
		$('#dataTableOne').dataTable({
			"sScrollY" : "380px",
			"bPaginate" : false,
			"bLengthChange" : true,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : true,
			"bAutoWidth" : true,
			/* bJQueryUI: true,
            sPaginationType: "full_numbers" */
		});
		
		// #myInput is a <input type="text"> element
		/* $('#myInput').on( 'keyup', function () {
			$('#dataTableOne').DataTable().search( this.value ).draw();
		} ); */
	});
	
	var table = $('#example').DataTable();
	 
	
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
	});
</script>
<script type="text/javascript" src="/dolphin/js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function saveissueentry() {
		var form1 = document.getElementById("form1");
		
		if(form1.checkValidity()) {
			form1.action = "/dolphin/MessItemsMoveProcess/saveStockMove";
			form1.method = "POST";
			form1.submit();
		}
		

	}
	
	function cancelRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/dolphin/MessItemsMoveProcess/cancelStockMove";
		form1.method = "POST";
		form1.submit();

	}
	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/dolphin/AdminProcess/printVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function approveRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/dolphin/AdminProcess/approveVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function rejectRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "/dolphin/AdminProcess/rejectVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		
		/* $("#saveissueentry").button().click(function() {
			saveissueentry();
		}); */
		$("#saveissueentry2").button().click(function() {
			saveissueentry();
		});
		$("#effect").hide();

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
	
	var itemlist=[
        <c:forEach varStatus="status" items="${messstockitemdetailslist}" var="itemlist">{
        		availablestock:'<c:out default="0" value="${itemlist.availablequantity}" />',
        		unitprice:'<c:out default="0" value="${itemlist.itemunitprice}" />',
        		sgst:'<c:out default="0" value="${itemlist.sgst}" />',
        		cgst:'<c:out default="0" value="${itemlist.cgst}" />',
                value:'<c:out default="0" value="${itemlist.itemname}" />',
                batchno:'<c:out default="0" value="${itemlist.batchno}" />',
                particularname:'<c:out default="Kilogram" value="${itemlist.unitofmeasure}" />',
                itemid:'<c:out default="0" value="${itemlist.itemid}" />',
                salesprice:'<c:out default="0" value="${itemlist.stockentryexternalid}" />',
                id:'<c:out default="0" value="${itemlist.stockentryid}" />'
                }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ];
	
	function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=ids_"+rowCount+" /><input type='hidden' name='ids' id=stockmove_ids_"+rowCount+" value='' /></td>";
        var col2="<td class='dataTextInActive'><input type='text' name='itemsname' id=items_name_"+rowCount+" class='textfieldvalues' style='font-size: 14px;' required/><input type='hidden' name='itemsids' id=items_ids_"+rowCount+" value='' /></td>";
        /* <input type='hidden' name='batchno' id=batchno_"+rowCount+" value='' /> */
        var col3="<td class='dataTextInActive'><input type='text' name='batchno' id=batchno_"+rowCount+" value=''  class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
        var col4="<td class='dataTextInActive'><input type='text' value='0'   name='itemsquantity'  id=items_quantity_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	   	var col5="<td class='dataTextInActive'><input type='text' value=''   name='itemsunitofmeasure'  id=items_unitofmeasure_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	    var col6="<td class='dataTextInActive'><input type='text' value='0'  name='itemunitprice' id=itemunitprice_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;'/><input type='hidden' name='purchaseprice' id=purchaseprice_"+rowCount+" value='' /></td>";
 	    var col7="<td class='dataTextInActive'><input type='text' value=''   name='sgst'  id=sgst_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	    var col8="<td class='dataTextInActive'><input type='text' value=''   name='cgst'  id=cgst_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	    var col9="<td class='dataTextInActive'><input type='text' name='issuequantity' id=issuequantity_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' onkeyup='calculate("+rowCount+")' onkeydown='calculate("+rowCount+")' required /></td>";
 	    var col10="<td class='dataTextInActive'><input type='text'  value='0'  name='priceonlygst' id=priceonlygst_"+rowCount+" style='font-size: 14px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #5d7e9b;border-right-color: #5d7e9b;border-bottom-color: #5d7e9b;border-left-color: #5d7e9b;border-top-width: 1px;border-right-width: 1px;border-bottom-width: 1px;border-left-width: 1px;width: 80px;height: 25px;border-radius: 5px;background-color: white;' readonly/></td>";
 	    var col11="<td class='dataTextInActive'><input type='text' class='linetotalAmountwithoutgst' value='0'  name='totalpricewithoutgst' id=totalpricewithoutgst_"+rowCount+" style='font-size: 14px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #5d7e9b;border-right-color: #5d7e9b;border-bottom-color: #5d7e9b;border-left-color: #5d7e9b;border-top-width: 1px;border-right-width: 1px;border-bottom-width: 1px;border-left-width: 1px;width: 80px;height: 25px;border-radius: 5px;background-color: white;' readonly/></td>";
 	   	var col12="<td class='dataTextInActive'><input type='text' class='linetotalAmount' value='0'  name='linetotal' id=linetotal_"+rowCount+" style='font-size: 14px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #5d7e9b;border-right-color: #5d7e9b;border-bottom-color: #5d7e9b;border-left-color: #5d7e9b;border-top-width: 1px;border-right-width: 1px;border-bottom-width: 1px;border-left-width: 1px;width: 80px;height: 25px;border-radius: 5px;background-color: white;' readonly/></td>";
 	    var col13="<td class='dataTextInActive'><button onClick='addRow();'>+</button></td>";
        /* var col6="<td class='dataTextInActive'><input type='text' class='linetotalAmount' value='0'  name='linetotal' id=linetotal_"+rowCount+" style='font-size: 14px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #5d7e9b;border-right-color: #5d7e9b;border-bottom-color: #5d7e9b;border-left-color: #5d7e9b;border-top-width: 1px;border-right-width: 1px;border-bottom-width: 1px;border-left-width: 1px;width: 80px;height: 25px;border-radius: 5px;background-color: white;' readonly/></td>"; */
        /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+col6+col7+col8+col9+col10+col11+col12+col13+"</tr>");
        $(function() {
            $("#dataTable").find('tbody').append(newRow);
        });
        $(function() {
            $("#items_name_"+rowCount).autocomplete({
                source: itemlist,
                minLength: 1,
                change:function(event,ui){
                	$("#stockmove_ids_"+rowCount ).val( ui.item.id );
                    $("#items_ids_"+rowCount ).val( ui.item.itemid );
                    $("#batchno_"+rowCount ).val( ui.item.batchno );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.salesprice );
                    $("#purchaseprice_"+rowCount).val( ui.item.unitprice );
                    $("#sgst_"+rowCount).val( ui.item.sgst );
                    $("#cgst_"+rowCount).val( ui.item.cgst );
                },
                focus: function( event, ui ) {
                	$("#stockmove_ids_"+rowCount ).val( ui.item.id );
                    $( "#items_name_"+rowCount).val( ui.item.name );
                    $( "#items_ids_"+rowCount ).val( ui.item.itemid );
                    $("#batchno_"+rowCount ).val( ui.item.batchno );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.salesprice );
                    $("#purchaseprice_"+rowCount).val( ui.item.unitprice );
                    $("#sgst_"+rowCount).val( ui.item.sgst );
                    $("#cgst_"+rowCount).val( ui.item.cgst );
                    return true;
                },
                select: function( event, ui ) {
                	$("#stockmove_ids_"+rowCount ).val( ui.item.id );
                    $( "#items_name_"+rowCount).val( ui.item.value );
                    $( "#items_ids_"+rowCount ).val( ui.item.itemid );
                    $("#batchno_"+rowCount ).val( ui.item.batchno );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.salesprice );
                    $("#purchaseprice_"+rowCount).val( ui.item.unitprice );
                    $("#sgst_"+rowCount).val( ui.item.sgst );
                    $("#cgst_"+rowCount).val( ui.item.cgst );
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +"&nbsp;/&nbsp;</b> <b> "+item.batchno +"</b></a>" )
                .appendTo( ul );
            };

        });
    }
	
	function calculate(value2) {

 	    	var availableQuantity=document.getElementById("items_quantity_"+value2).value;
 	        var issueQuantity=document.getElementById("issuequantity_"+value2).value;
 	        
 	        /* if(parseFloat(issueQuantity,10)>parseFloat(availableQuantity,10)){
 	      		//document.getElementById("errormessage_"+value2).style.display = '';
 	      		$( "#dialog" ).dialog( "open" );
 	      	  document.getElementById("issuequantity_"+value2).value='';
 	      	document.getElementById("ids_"+value2).checked = false;
 	        } */
 	        if(issueQuantity !== ""){
 	        
	 	        if(parseFloat(issueQuantity)>0 && (parseFloat(issueQuantity,10)<=parseFloat(availableQuantity,10))){
	 	        	//document.getElementById("errormessage_"+value2).style.display = 'none';
	 	      	  document.getElementById("ids_"+value2).checked = true;  
	 	      	  
	 	      	  var unitprice = document.getElementById("itemunitprice_"+value2).value;
	 	       	  var sgst=document.getElementById("sgst_"+value2).value;
	 	       	  var cgst=document.getElementById("cgst_"+value2).value;
	 	       	
	 	       	  
	 	       	  var sgstpercentage = (sgst / 100) * unitprice;
	 	       	  var cgstpercentage = (cgst / 100) * unitprice;
	 	       	
	 	           var final1 = document.getElementById("linetotal_"+value2);
	 	           var priceonlygst = document.getElementById("priceonlygst_"+value2);
	 	           var totalpricewithoutgst = document.getElementById("totalpricewithoutgst_"+value2);
	 	           
	 	           var quantity = document.getElementById("issuequantity_"+value2).value;
	 	           var gstprice = parseFloat(unitprice)+parseFloat(sgstpercentage)+parseFloat(cgstpercentage);
	 	           var onlygstprice = parseFloat(sgst)+parseFloat(cgst);
	 	           var totalpricewithoutgstvalue = parseFloat(unitprice)*quantity;
	 	           var totalgstprice = parseFloat(gstprice)*quantity;
	 	           
	 	          priceonlygst.value= parseFloat(onlygstprice).toFixed(1);
     			  totalpricewithoutgst.value = parseFloat(totalpricewithoutgstvalue).toFixed(2);
	 	          final1.value= parseFloat(totalgstprice).toFixed(2);
	 	           
	 	        }else{
	 	        	$( "#dialog" ).dialog( "open" );
	 	        	document.getElementById("issuequantity_"+value2).value='';
	 	      	  document.getElementById("ids_"+value2).checked = false;
	 	        }
 	        
 	        }
 			
    }
	 
	 $(function() {
         $( "#dialog" ).dialog({
             autoOpen: false,
             height: 40,
             width: 180,
             modal: true,
         });
     });
	 
	$(function() {

		$("#tabs").tabs();
		$("#effect").show();
		
		var addItemsButtonID1="#addnewitem1";
        
        $( addItemsButtonID1 )
        .button({
            icons: {
                primary: "ui-icon-plus"
            }
        })
        .click(function() {
            addRow();
            return false;
        });
		
		var addItemsButtonID=".addnewitem";
        var removeItemsButtonID="#removenewitem";
        
        $( addItemsButtonID )
        .button({
            icons: {
                primary: "ui-icon-plus"
            }
        })
        .click(function() {
            addRow();
            return false;
        });
        
       $(removeItemsButtonID)
        .button({
            icons: {
                primary: "ui-icon-minus"
            }
        })
        .click(function() {
            deleteRow('dataTable');
            return false;
        }); 
        
       
       
       $("#saveissueentry").button().click(function(){
        	 $( "#dialogpaymentmethod" ).dialog( "open" );
             return false;

         });
         
         $(function() {
             $( "#dialogpaymentmethod" ).dialog({
                 autoOpen: false,
                 height: 330,
                 width: 570,
                 modal: true,
                 buttons: {
                     OK: function() {
                     	
                    		 generatebill(document.getElementById("cashpayment"),document.getElementById("banktransfer"),
                     					document.getElementById("chequetransfer"), document.getElementById("ackno"), 
                     			document.getElementById("transferdate"), document.getElementById("transferbankname"),
                     			document.getElementById("chequeno"), document.getElementById("chequedate"), document.getElementById("chequebankname"), 
                     			document.getElementById("totalcashamount"), document.getElementById("totalbanktransferamount"),
                     			document.getElementById("totalchequetransferamount"),document.getElementById("itemsGrandTotalAmount"),document.getElementById("itemsGrandTotalAmountWithoutGST"));
                         		$( this ).dialog( "close" );
                  		   }
                 }
             });
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
            
            $("#chequedate").datepicker({
    			changeYear : true,
    			changeMonth : true,
    			dateFormat: 'dd/mm/yy',
    			yearRange: "-50:+0"
    		});
    		$("#anim").change(function() {
    			$("#chequedate").datepicker("option", "showAnim", $(this).val());
    		});
         

	});
   
	
	function generatebill(cashpayment,banktransfer,chequetransfer,ackno,transferdate,transferbankname,chequeno,chequedate,chequebankname,totalcashamount,totalbanktransferamount,
			totalchequetransferamount,itemsgrandtotalamount,itemsGrandTotalAmountWithoutGST){
    	
    	var paymentmethodbanktransfer = '';
    	var paymentmethodchequetransfer = '';
    	var paymentmethodcash = '';
    	var cashpaymentvalue = '';
    	var acknovalue = '';
    	var transferdatevalue = '';
    	var transferbanknamevalue = '';
    	var chequenovalue = '';
    	var chequedatevalue = '';
    	var chequebanknamevalue = '';
    	var totalcashamountvalue = '';
    	var totalbanktransferamountvalue = '';
    	var totalchequetransferamountvalue = '';
    	var itemsgrandtotalamountvalue = '';
    	var itemsTotalAmountWithoutGST='';
    	
    	itemsgrandtotalamountvalue = itemsgrandtotalamount.value;
    	totalcashamountvalue = totalcashamount.value;
    	totalbanktransferamountvalue = totalbanktransferamount.value;
    	totalchequetransferamountvalue = totalchequetransferamount.value;
    	
    	var sum = (parseFloat(totalcashamountvalue)+parseFloat(totalbanktransferamountvalue)+parseFloat(totalchequetransferamountvalue)).toFixed(2);
    	var grandtotal = parseFloat(itemsgrandtotalamountvalue);
    	itemsTotalAmountWithoutGST = parseFloat(itemsGrandTotalAmountWithoutGST.value);
    	
    	if(parseFloat(sum) == parseFloat(grandtotal))
    		
    		{
    	
    	if(banktransfer.checked == true ){
    		paymentmethodbanktransfer = 'banktransfer';
    		
    	}
    	
    	if(chequetransfer.checked == true){
    		paymentmethodchequetransfer = 'chequetransfer';
    	}
    	
    	if(cashpayment.checked == true){
    		paymentmethodcash = 'cashpayment';
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
    	
    	var form1 = document.getElementById("form1");
		form1.action="/dolphin/MessItemsMoveProcess/saveStockMove?paymentmethodbanktransfer="+paymentmethodbanktransfer+"&paymentmethodchequetransfer="+paymentmethodchequetransfer+"&paymentmethodcash="+paymentmethodcash+"&ackno="+acknovalue+"&transferdate="+transferdatevalue+"&transferbankname="+transferbanknamevalue+"&chequeno="+chequenovalue+"&chequedate="+chequedatevalue+"&chequebankname="+chequebanknamevalue+"&totalcashamount="+totalcashamountvalue+"&totalbanktransferamount="+totalbanktransferamountvalue+"&totalchequetransferamount="+totalchequetransferamountvalue+"&itemsGrandTotalAmountWithoutGST="+itemsTotalAmountWithoutGST+"";
		form1.method = "POST";
		form1.submit();
		
    		}else{
    			document.getElementById('totalerror').style.display = '';
    		}
		
    }
	
	 $(function(){
		 
		
         $("#cancel").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             cancelRecords();
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
             approveRecords();
             return false;

         });
         
         $("#reject").button({
             icons:{
                 primary: "ui-icon-closethick"
             }
         }).click(function(){
             rejectRecords();
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
     
     
     function deleteRow(tableID) {
        try {
            var table = document.getElementById(tableID);
            var rowCount = table.rows.length;
            if(rowCount==1){
                alert('No records to delete');
            }
            for(var i=1; i<rowCount; i++) {
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
     
     
     /* var xmlHttp;
 	function getLastPrice(rowIndex){
 		
 		var itemId = document.getElementById('items_ids_'+rowIndex).value;
 		var customerName = document.getElementById('issuedto').value;

 			 if (typeof XMLHttpRequest != "undefined") {
 				 xmlHttp = new XMLHttpRequest();
 	            
 	         } else if (window.ActiveXObject) {
 	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
 	             
 	         }
 			xmlHttp.onreadystatechange = stateChanged;
 			xmlHttp.open("GET", "/dolphin/MessItemsMoveProcess/getCustomerLastPrice?customerName="+customerName+"&itemid="+itemId+"",true);
 			xmlHttp.send(null);
 	}
 	
 	function stateChanged() {

 		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
 			document.getElementById("pricelist").innerHTML = xmlHttp.responseText;
 			
            
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
 	} */
 	
     function writeNumber(number){
    	 var val = number.id;
    	 var res = val.split("_")
    	 document.getElementById("issuequantity_"+res[1]).value += res[0];
    	 return false;
     }
     
     function deleteNumber(number){
    	 
    	 var issueQuantity = document.getElementById("issuequantity_"+number.id).value;
    	 var newQuantity = issueQuantity.slice(0,-1);
    	 document.getElementById("issuequantity_"+number.id).value = newQuantity;
    	 
     }
    
</script>



		<script type="text/javascript">
					
					var itemsissued='<c:out default="" value="${itemsissued}"/>';
		            
		            if(itemsissued == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            	 });
		            	 }else if(itemsissued == "false"){
		            	  $(function(){
		            		 $( "div.failure" ).fadeIn( 800 ).delay( 2000 );
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
		        	
		        	
		        	function selectPayment(id){
		            	
		            	
		            	if(id == 'cashpayment'){
		            		
		            		if(document.getElementById("cashpayment").checked){
		            			
		            			document.getElementById('cashamount').style.display = '';
			            		document.getElementById('banktransferamount').style.display = "none";
			            		document.getElementById('chequetransferamount').style.display = "none";
			            		
			            		document.getElementById('onlinechequeack').style.display = "none";
			            		document.getElementById('onlinechequedate').style.display = "none";
			            		document.getElementById('onlinechequebank').style.display = "none";
			            		
			            		document.getElementById('onlinetransferack').style.display = "none";
			            		document.getElementById('onlinetransferdate').style.display = "none";
			            		document.getElementById('onlinetransferbank').style.display = "none";
		            			
		            		}else{
		            			
		            			document.getElementById('cashamount').style.display = "none";
			            		document.getElementById('banktransferamount').style.display = "none";
			            		document.getElementById('chequetransferamount').style.display = "none";
			            		
			            		document.getElementById('onlinechequeack').style.display = "none";
			            		document.getElementById('onlinechequedate').style.display = "none";
			            		document.getElementById('onlinechequebank').style.display = "none";
			            		
			            		document.getElementById('onlinetransferack').style.display = "none";
			            		document.getElementById('onlinetransferdate').style.display = "none";
			            		document.getElementById('onlinetransferbank').style.display = "none";
		            		}
		            		
		            		
		            		
		            			
		            	}else if(id == 'banktransfer'){
		            		
		            		
		            		if(document.getElementById("banktransfer").checked){
		            		
		            		document.getElementById('cashamount').style.display = "none";
		            		document.getElementById('banktransferamount').style.display = '';
		            		document.getElementById('chequetransferamount').style.display = "none";
		            		
		            		document.getElementById('onlinechequeack').style.display = "none";
		            		document.getElementById('onlinechequedate').style.display = "none";
		            		document.getElementById('onlinechequebank').style.display = "none";
		            		
		            		document.getElementById('onlinetransferack').style.display = '';
		            		document.getElementById('onlinetransferdate').style.display = '';
		            		document.getElementById('onlinetransferbank').style.display = '';
		            		
		            		
		            	}else{
	            			
	            			document.getElementById('cashamount').style.display = "none";
		            		document.getElementById('banktransferamount').style.display = "none";
		            		document.getElementById('chequetransferamount').style.display = "none";
		            		
		            		document.getElementById('onlinechequeack').style.display = "none";
		            		document.getElementById('onlinechequedate').style.display = "none";
		            		document.getElementById('onlinechequebank').style.display = "none";
		            		
		            		document.getElementById('onlinetransferack').style.display = "none";
		            		document.getElementById('onlinetransferdate').style.display = "none";
		            		document.getElementById('onlinetransferbank').style.display = "none";
	            		}
	            		
		            		
		            		
		            	}else if(id == 'chequetransfer'){
		            		
		            		
		            		if(document.getElementById("chequetransfer").checked){
		            			
		            		document.getElementById('cashamount').style.display = "none";
		            		document.getElementById('banktransferamount').style.display = "none";
		            		document.getElementById('chequetransferamount').style.display = '';
		            		
		            		document.getElementById('onlinechequeack').style.display = '';
		            		document.getElementById('onlinechequedate').style.display = '';
		            		document.getElementById('onlinechequebank').style.display = '';
		            		
		            		document.getElementById('onlinetransferack').style.display = "none";
		            		document.getElementById('onlinetransferdate').style.display = "none";
		            		document.getElementById('onlinetransferbank').style.display = "none";
		            		
		            		
		            	}else{
	            			
	            			document.getElementById('cashamount').style.display = "none";
		            		document.getElementById('banktransferamount').style.display = "none";
		            		document.getElementById('chequetransferamount').style.display = "none";
		            		
		            		document.getElementById('onlinechequeack').style.display = "none";
		            		document.getElementById('onlinechequedate').style.display = "none";
		            		document.getElementById('onlinechequebank').style.display = "none";
		            		
		            		document.getElementById('onlinetransferack').style.display = "none";
		            		document.getElementById('onlinetransferdate').style.display = "none";
		            		document.getElementById('onlinetransferbank').style.display = "none";
	            		}
	            		
		            	}
		            	
		            }
		        	
		        	
		        	function calculateGrandTotal() {
		                var sum = 0.0;
		                var sum2 = 0.0;
		                var column2 = $('.linetotalAmount')
		                jQuery.each(column2,function(){
		                    sum += parseFloat($(this).val());
		                });
		                
		                var column1 = $('.linetotalAmountwithoutgst')
		                jQuery.each(column1,function(){
		                sum2 += parseFloat($(this).val());
		                });
		                $('#itemsGrandTotalAmountWithoutGST').val(sum2);
		                
		                $('#itemsTotalAmount').val(sum);
		                $('#itemsGrandTotalAmount').val(sum);
		            }
		        	
		        	$(document).ready(function() {
		                
		                
		                $("#dataTable").keyup(function(){
		                    
		                    var sum = 0.0;
		                    var totalSum=0.0;
		                    var sum2 = 0.0;
		                    var column2 = $('.linetotalAmount')
		                    jQuery.each(column2,function(){
		                        sum += parseFloat($(this).val());
		                    });
		                    
		                    var column1 = $('.linetotalAmountwithoutgst')
		                    jQuery.each(column1,function(){
		                    sum2 += parseFloat($(this).val());
		                    });
		                    $('#itemsGrandTotalAmountWithoutGST').val(sum2);
		                    
		                    $('#itemsTotalAmount').val(sum);
		                    $('#itemsGrandTotalAmount').val(sum);
		                    
		                });
		                $("#dataTable").click(function(){
		                    
		                    var sum = 0.0;
		                    var totalSum=0.0;
		                    var sum2 = 0.0;
		                    var column2 = $('.linetotalAmount')
		                    jQuery.each(column2,function(){
		                        sum += parseFloat($(this).val());
		                    });
		                    
		                    var column1 = $('.linetotalAmountwithoutgst')
		                    jQuery.each(column1,function(){
		                    sum2 += parseFloat($(this).val());
		                    });
		                    $('#itemsGrandTotalAmountWithoutGST').val(sum2);
		                    $('#itemsTotalAmount').val(sum);
		                    $('#itemsGrandTotalAmount').val(sum);
		                });
						$("#dataTable").focus(function(){
		                    
		                    var sum = 0.0;
		                    var sum2 = 0.0;
		                    var totalSum=0.0;
		                    var column2 = $('.linetotalAmount')
		                    jQuery.each(column2,function(){
		                        sum += parseFloat($(this).val());
		                    });
		                    
		                    var column1 = $('.linetotalAmountwithoutgst')
		                    jQuery.each(column1,function(){
		                    sum2 += parseFloat($(this).val());
		                    });
		                    $('#itemsGrandTotalAmountWithoutGST').val(sum2);
		                    $('#itemsTotalAmount').val(sum);
		                    $('#itemsGrandTotalAmount').val(sum);
		                });


		            });
		        	
		        	function deleteRow(tableID) {
		                try {
		                    var table = document.getElementById(tableID);
		                    var rowCount = table.rows.length;
		                    if(rowCount==1){
		                        alert('No records to delete');
		                    }
		                    for(var i=1; i<rowCount-3; i++) {
		                        var row = table.rows[i];
		                        var chkbox = row.cells[0].childNodes[0];
		                        if(null != chkbox && true == chkbox.checked) {
		                            table.deleteRow(i);
		                            rowCount--;
		                            i--;
		                        }
		                    }
		                   
		                    
		                    var sum = 0.0;
		                    var sum2 = 0.0;
		                    var totalSum=0.0;
		                    var column2 = $('.linetotalAmount')
		                    jQuery.each(column2,function(){
		                        sum += parseFloat($(this).val());
		                    });
		                    totalSum=sum;
		                    
		                    var column1 = $('.linetotalAmountwithoutgst')
		                    jQuery.each(column1,function(){
		                    sum2 += parseFloat($(this).val());
		                    });
		                    totalSum2=sum2;
		                    $('#itemsGrandTotalAmountWithoutGST').val(totalSum2);
		                    
		                    $('#itemsTotalAmount').val(totalSum);
		                    $('#itemsGrandTotalAmount').val(totalSum);
		                    	calculateGrandTotal();
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
	response.sendRedirect("/dolphin/UserProcess/sessionTimeOut");
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
		
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<!-- <div style="height: 28px">
			<button id="add">Issue Entry</button>
			<br />
		</div> -->

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Bill Details</a></li>

				</ul>
				<div id="tabs-1">
				
					<table style="margin-left: auto;margin-right: auto;">
						<tr>
							<td><br></td>
						</tr>
						<tr>
						<td class="alignRight">Date&nbsp;</td>
							<td><label> <input type="text"  name="transactiondate"
									class="textField" style="font-size: 14px;"
									value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" 
									id="transactiondate" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
						
							<!-- <td  class="alignRight" style="display: none;">Purpose &nbsp;</td>
							<td ><label> <select name="purpose" style="display: none;"
									id="purpose" class="dropdownlist" style="font-size: 14px;" required>
										<option selected></option>
										<option>Breakfast</option>
										<option>Lunch</option>
										<option>Dinner</option>
										<option>Other</option>
								</select>
							</label></td> -->
							
							<td class="alignRight">Student&nbsp;</td>
							<td>
								<div class="container">
										<div class="row">
										        <select name="issuedto"	id="issuedto" style="font-size: 24px;width: 225px;"  class="form-control select2" required>
										        	<option></option>
										        	<c:forEach items="${studentList}" var="student">
										        	
										        		<option style="color: black;" value="${student.student.name}_${student.student.classstudying}_${student.fathersname}_${student.student.sid}">${student.student.name}&nbsp;&nbsp;/&nbsp;&nbsp;${student.student.classstudying}/&nbsp;&nbsp;${student.fathersname}</option>
										        	</c:forEach>
										        </select><!-- &nbsp;&nbsp;<a target="mainFrame" href="/dolphin/StudentProcess/addNew">New Customer --></a>
									 	</div>
								</div>
							</td>
							
							
						</tr>
						<tr>
							<td><br /></td>
						</tr>
					</table>
					
					
						<div align="center">
						<p>
						<h2 style="text-decoration: underline;color: #eb6000">Items Details</h2>	
						<label><button id="addnewitem1">Add Item</button></label><label><button id="removenewitem">Remove Item</button></label></p>
					</div>
					
					<br>
					<table style="margin-left: auto;margin-right: auto;border: 1px solid black;" id="dataTable">
						<thead>
							<tr>
								<th class="headerText"><input type="checkbox"
									id="selectAll" name="selectAll"
									onclick="selectAllRow('dataTable')" /> </th>
								<th class="headerText">Item Name</th>
								<th class="headerText">Batch</th>
								<th class="headerText">Available Quantity</th>
								<th class="headerText">Unit of Measure</th>
								<th class="headerText">Unit Price</th>
								<th class="headerText">SGST Price</th>
								<th class="headerText">CGST Price</th>
								<th class="headerText">Issue Quantity</th>
								<th class="headerText">Total GST</th>
								<th class="headerText">Total (Excl. GST)</th>
								<th class="headerText">Total (Incl. GST)</th>
								<th class="headerText">Add</th>
							</tr>
						</thead>

						<tbody>						
						</tbody>
						<tfoot>
							<tr>

								<td colspan="10" align="right" style="font-weight: bold;">Total&nbsp;&nbsp;</td>
								<td align="center"><input type="text"
									name="itemsTotalAmount" id="itemsTotalAmount" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" value="0" onkeyup="calculateTransportationCharges();"/></td>
							</tr>
						</tfoot>
					</table>
					<br>
					
					<br>
					<table id="table2" width="100%" border="0" align="center">
						<tr align="center">
							<td>
									<div id="pricelist"> 
								
									</div>
								<br>
								<button id="saveissueentry">Generate Bill</button>
							</td>
						</tr>
					</table>
					</div>
				</div>
			</div>
	</form>
	
	<div id="dialog" title="Quantity not in stock">
	</div>
	
	<div id="dialogpaymentmethod" title="Payment Method">
	
				<table style="width: auto;height: auto;display: none;" id="totalerror">
						<tr>
           		 			<td>
           		 				<p align="center" style="color: red;font-size: 10px;">Grand Total Doesn't Match</p>
           		 			</td>	
           		 		</tr>
				</table>
	
				<table style="width: auto;height: auto;">
					<tr>
           		 			<td>
           		 				Grand Total: &nbsp;<input type="text" name="itemsGrandTotalAmount" id="itemsGrandTotalAmount" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" readonly/>
           		 				<input type="hidden" name="itemsGrandTotalAmountWithoutGST" id="itemsGrandTotalAmountWithoutGST" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" readonly/>
           		 				<br>
           		 			</td>	
           		 			
           		 			
           		 		</tr>
				</table>
				
           		 
           		 <table style="width: auto;height: auto;">
						
						<tr>
							<td>Payment method: &nbsp;</td>
						
							<td>
							
								<input type="checkbox" id="cashpayment" name="paymentmethod" value="cashpayment" onclick="selectPayment(this.id)">
								<label for="cashpayment">Cash</label>
									
								<input type="checkbox" id="banktransfer" name="paymentmethod" value="banktransfer" onclick="selectPayment(this.id)">
								<label for="banktransfer">Bank Transfer</label>
								
								<input type="checkbox" id="chequetransfer" name="paymentmethod" value="chequetransfer" onclick="selectPayment(this.id)">
								<label for="chequetransfer">Cheque</label>							
							
							</td>
						<tr>
							<td><br></td>
						</tr>	
							
						</tr>
						<tr id="cashamount" style="display: none;">
							<td></td>
						
							<td>
								Amount &nbsp;<input type="text" name="totalcashamount" id="totalcashamount" class="textfieldvaluesshorts" value="0" style="font-size: 14px;font-weight: bold;" />														
							</td>
							
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr id="banktransferamount" style="display: none;">
							<td></td>
						
							<td>
								Amount &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" name="totalbanktransferamount" id="totalbanktransferamount" class="textfieldvaluesshorts" value="0" style="font-size: 14px;font-weight: bold;"/>														
							</td>
							
						</tr>
						<tr id="onlinetransferack" style="display: none;">
							<td></td>
						
							<td>
								Acknowledgement # &nbsp;<input type="text" id="ackno" name="ackno" class="textfieldvaluesshorts" style="width: 220px;font-size: 14px;font-weight: bold;">														
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
									<select name="transferbankname" id="transferbankname" class="dropdownlist" style="font-size: 14px;width: 220px;" required>
											<option value="bank">Bank</option>
								</select>
							
							</label>
							
							</td>
							
						</tr>
						
						<tr id="chequetransferamount" style="display: none;">
							<td></td>
						
							<td>
								Amount &nbsp;&nbsp;&nbsp;<input type="text" name="totalchequetransferamount" id="totalchequetransferamount" value="0" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" value="0"/>														
							</td>
							
						</tr>
						<tr id="onlinechequeack" style="display: none;">
							<td></td>
						
							<td>
								Cheque # &nbsp;<input type="text" id="chequeno" name="chequeno" class="textfieldvaluesshorts" style="width: 220px;font-size: 14px;font-weight: bold;">														
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
									<select name="chequebankname" id="chequebankname" class="dropdownlist" style="font-size: 14px;width: 220px;" required>
											<option value="bank">Bank</option>
								</select>
							
							</label>
							
							</td>
							
						</tr>
						
					</table>
			</div>
	
	<script>
    $('.select2').select2();
</script>
</body>
</html>