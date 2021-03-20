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
	});
</script>
<script type="text/javascript" src="js/datetimepicker_css.js"></script>
<script type="text/javascript">

	function saveissueentry() {
		var form1 = document.getElementById("form1");
		
		if(form1.checkValidity()) {
			form1.action = "Controller?process=MessItemsMoveProcess&action=saveStockMove";
			form1.method = "POST";
			form1.submit();
		}
		

	}
	
	function cancelRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=MessItemsMoveProcess&action=cancelStockMove";
		form1.method = "POST";
		form1.submit();

	}
	
	function printRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=printVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function approveRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=approveVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	function rejectRecords() {
		var form1 = document.getElementById("form1");
		form1.action = "Controller?process=AdminProcess&action=rejectVoucher";
		form1.method = "POST";
		form1.submit();
	}
	
	$(function() {

		$("#tabs").tabs();
		$("#saveissueentry").button().click(function() {
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
                value:'<c:out default="0" value="${itemlist.itemname}" />',
                batchno:'<c:out default="0" value="${itemlist.batchno}" />',
                particularname:'<c:out default="Kilogram" value="${itemlist.unitofmeasure}" />',
                itemid:'<c:out default="0" value="${itemlist.itemid}" />',
                id:'<c:out default="0" value="${itemlist.stockentryid}" />'
                }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ];
	
	function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=ids_"+rowCount+" /><input type='hidden' name='ids' id=stockmove_ids_"+rowCount+" value='' /></td>";
        var col2="<td class='dataTextInActive'><input type='text' name='itemsname' id=items_name_"+rowCount+" class='textfieldvalues' style='font-size: 14px;'/><input type='hidden' name='itemsids' id=items_ids_"+rowCount+" value='' /></td>";
 	    var col3="<td class='dataTextInActive'><input type='text' value='0'   name='itemsquantity'  id=items_quantity_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	   	var col4="<td class='dataTextInActive'><input type='text' value=''   name='itemsunitofmeasure'  id=items_unitofmeasure_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	    var col5="<td class='dataTextInActive'><input type='text' value='0'   name='itemunitprice' id=itemunitprice_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
 	   	var col6="<td class='dataTextInActive'><input type='text' name='issuequantity' id=issuequantity_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' onkeyup='calculate("+rowCount+")' onkeypress='calculate("+rowCount+")' onkeydown='calculate("+rowCount+")' required /></td>";
        /* var col6="<td class='dataTextInActive'><input type='text' class='linetotalAmount' value='0'  name='linetotal' id=linetotal_"+rowCount+" style='font-size: 14px;border-top-style: solid;border-right-style: solid;border-bottom-style: solid;border-left-style: solid;border-top-color: #5d7e9b;border-right-color: #5d7e9b;border-bottom-color: #5d7e9b;border-left-color: #5d7e9b;border-top-width: 1px;border-right-width: 1px;border-bottom-width: 1px;border-left-width: 1px;width: 80px;height: 25px;border-radius: 5px;background-color: white;' readonly/></td>"; */
        /* var col4="<td class='dataTextInActive'><input type='text' value='1' onclick='calculate("+rowCount+")'  onkeyup='calculate("+rowCount+")' name='feesQuantities' id=fees_quantity_"+rowCount+" /><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><select  onchange='calculate("+rowCount+")'  name='feesQuantities' id=fees_quantity_"+rowCount+"><option></option><option>JAN</option><option>Feb</option><option>MAR</option><option>APR</option><option>MAY</option><option>JUN</option><option>JUL</option><option>AUG</option><option>SEP</option><option>OCT</option><option>NOV</option><option>DEC</option></select><input type='hidden'   id=hiddenfees_quantity_"+rowCount+" value='' /></td>"; */
        /* var col4="<td class='dataTextInActive'><input class='feesAmount' type='text' value='0'      name='feesAmounts' id=fees_amount_"+rowCount+" /></td>"; */
        var newRow = $("<tr class='trClass'>"+col1+col2+col3+col4+col5+col6+"</tr>");
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
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.unitprice );
                },
                focus: function( event, ui ) {
                	$("#stockmove_ids_"+rowCount ).val( ui.item.id );
                    $( "#items_name_"+rowCount).val( ui.item.name );
                    $( "#items_ids_"+rowCount ).val( ui.item.itemid );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.unitprice );
                    return true;
                },
                select: function( event, ui ) {
                	$("#stockmove_ids_"+rowCount ).val( ui.item.id );
                    $( "#items_name_"+rowCount).val( ui.item.value );
                    $( "#items_ids_"+rowCount ).val( ui.item.itemid );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                    $("#items_quantity_"+rowCount).val( ui.item.availablestock );
                    $("#itemunitprice_"+rowCount).val( ui.item.unitprice );
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
	          
	          if(parseFloat(issueQuantity,10)>parseFloat(availableQuantity,10)){
	        	  $( "#dialog" ).dialog( "open" );
	        	  document.getElementById("issuequantity_"+value2).value='';
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
		$("#effect").hide();
		
		var addItemsButtonID="#addnewitem";
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
        

	});
   
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
		
		<div class="alert-box success" id="div1">Items has been issued successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Items issuance failed, please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<jsp:useBean id="now" class="java.util.Date" scope="page" />
		<div style="height: 28px">
			<button id="add">Issue Entry</button>
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
									id="transactiondate" autocomplete="false"
									data-validate="validate(required)">
							</label></td>
							
							</tr>
							<tr>
							<td><br /></td>
	
							</tr>
						
						<tr>
						
						<tr>
							<td class="alignRight">Issue To&nbsp;</td>
							<td ><label>
									<select name="issuedto"
									id="issuedto" class="dropdownlist" style="font-size: 14px;" >
										<option selected="selected">Shaheen Nagar- Mess</option>
										<option>Gole Khana- Mess</option>
										<option>Mailoor- Mess</option>
								</select>
							
							</label></td>
						
							<td  class="alignRight">Purpose &nbsp;</td>
							<td ><label> <select name="purpose"
									id="purpose" class="dropdownlist" style="font-size: 14px;" required>
										<option selected></option>
										<option>Breakfast</option>
										<option>Lunch</option>
										<option>Dinner</option>
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
					</table>
					
					<div align="center">
						<p>
						<h2 style="text-decoration: underline;color: #eb6000">Item Details</h2>	
						<label><button id="addnewitem">Add Item</button></label><label><button id="removenewitem">Remove Item</button></label></p>
					</div>
					
					<br>
					<table style="margin-left: auto;margin-right: auto;border: 1px solid black;" id="dataTable">
						<thead>
							<tr>
								<th class="headerText"><input type="checkbox"
									id="selectAll" name="selectAll"
									onclick="selectAllRow('dataTable')" /> </th>
								<th class="headerText">Item Name</th>
								<th class="headerText">Available Quantity</th>
								<th class="headerText">Unit of Measure</th>
								<th class="headerText">Unit Price</th>
								<th class="headerText">Issue Quantity</th>
							</tr>
						</thead>

						<tbody>						
						</tbody>
					</table>
					<br>
					<table id="table2" width="100%" border="0" align="center">
						<tr>
							<td align="center">
								<button id="saveissueentry">Issue</button>
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
					<td class="headerTD">Items Issue List</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Issue Date</th>
						<th title="click to sort" class="headerText">Purpose</th>
						<th title="click to sort" class="headerText">Item Name</th>
						<th title="click to sort" class="headerText">Quantity</th>
						<th title="click to sort" class="headerText">Issued To</th>
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${messstockmovelist}" var="stockmovelist">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
                          <td class="dataText"><input type="checkbox" id = "<c:out value="${stockmovelist.id}"/>" class = "chcktbl"  name="stockmoveid"  value="<c:out value="${stockmovelist.id}"/>"/></td>
						  <td class="dataText" style="width: 10%;"><input type="text"  style="background-color: #E3EFFF;border-style: none;color: #4B6A84;text-align: center;" name="transactiondate_${stockmovelist.id}" value="<fmt:formatDate value="${stockmovelist.transactiondate}" pattern="dd/MM/yyyy"/>" readonly></td>
						  <td class="dataText"><c:out value="${stockmovelist.purpose}" /></td>
						     <c:set var="itemparts" value="${fn:split(stockmovelist.externalid, '_')}" />
						  <td class="dataText"><c:out value="${itemparts[0]}" /></td>
						  <td class="dataText"><c:out value="${stockmovelist.quantity}" /></td>
						  <td class="dataText"><c:out value="${stockmovelist.issuedto}" /></td>
						</tr>
					</c:forEach>

				</tbody>
					<tfoot>
						<tr>
                            <!-- <td  class="footerTD" colspan="2" ><button id="delete" type="submit">Delete</button>  -->
                    		<td class="footerTD"  colspan="8">
                    		<!-- <button id="print">Print</button> --> 
                    		<!-- <button id="approve">Approve</button>
                    		&nbsp;&nbsp;&nbsp;
                    		<button id="reject">Reject</button> 
                    		&nbsp;&nbsp;&nbsp; -->
                    		<button id="cancel">Cancel</button>
                    		</td>
                        </tr>
                    </tfoot>
			</table>
			
			<div align="center">
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="Controller?process=MessItemsMoveProcess&action=issueItems&page=${currentPage - 1}">Previous</a></td>
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
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="Controller?process=MessItemsMoveProcess&action=issueItems&page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="Controller?process=MessItemsMoveProcess&action=issueItems&page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>
                    
		</div>

	</form>
	
	<div id="dialog" title="Quantity not in stock">
	</div>

</body>
</html>
