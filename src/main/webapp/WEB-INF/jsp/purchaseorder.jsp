<%--
    Document   : Purchase
    Created on : Dec 23, 2011, 5:52:28 PM
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
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Purchase Order</title>
<link rel="stylesheet" href="/childrenandglobal/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/childrenandglobal/css/datePicker/demos.css">
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

.alignRightInvoice {
	font-family: Tahoma;
	font-size: 14px;
	font-style: normal;
	text-transform: capitalize;
	color: #eb6000;
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
	font-family: Tahoma;
	font-size: 14px;
	background-color: #4b6a84;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 27px;
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

.dataTextRight{
	border-radius: 3px;
	font-family: Tahoma;
	color: #4b6a84;
	font-size: 13px;
	letter-spacing: normal;
	text-align: right;
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

.dropdownlist{
	width: 220px;
	height:27px;
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
<script type="text/javascript" src="/childrenandglobal/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/childrenandglobal/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/childrenandglobal/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/childrenandglobal/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/childrenandglobal/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/childrenandglobal/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript"
	src="/childrenandglobal/js/datePicker/ui/jquery.ui.accordion.js"></script>
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
				
		$("#invoicedate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange:   "-0:+1"
		});
		$("#anim").change(function() {
			$("#invoicedate").datepicker("option", "showAnim", $(this).val());
		});
	});
	$(function() {
		
		$("#itementrydate").datepicker({
			changeYear : true,
			changeMonth : true,
			dateFormat: 'dd/mm/yy',
			yearRange:  "-0:+1"
		});
		$("#anim").change(function() {
			$("#itementrydate").datepicker("option", "showAnim", $(this).val());
		});
	});
</script>
<script type="text/javascript" src="/childrenandglobal/js/datetimepicker_css.js"></script>
<script type="text/javascript">
	
	function saveInventory() {
		
		var form1 = document.getElementById("form1");
		
		if(form1.checkValidity()) {
			form1.action = "/childrenandglobal/MessItemsProcess/savePurchaseOrder";
			form1.method = "POST";
			form1.submit();
		}
		
	}
	
	function cancelPurchase() {
			
				var form1 = document.getElementById("form1");
				form1.action = "/childrenandglobal/MessItemsProcess/cancelPurchaseOrder";
				form1.method = "POST";
				form1.submit();
			
		}
	
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
	
	function calculate(value2) {

      	  var purchaseprice = document.getElementById("purchaseprice_"+value2).value;
      	  var sgst=document.getElementById("sgst_"+value2).value;
      	  var cgst=document.getElementById("cgst_"+value2).value;
      	
      	  
      	  var sgstpercentage = (sgst / 100) * purchaseprice;
      	  var cgstpercentage = (cgst / 100) * purchaseprice;
      	
          var final1 = document.getElementById("linetotal_"+value2);
          var totalpricewithoutgst = document.getElementById("totalpricewithoutgst_"+value2);
          
          var quantity = document.getElementById("items_quantity_"+value2).value;
          var gstprice = parseFloat(purchaseprice)+parseFloat(sgstpercentage)+parseFloat(cgstpercentage);
          var totalgstprice = parseFloat(gstprice)*quantity;
          var totalpricewithoutgstvalue = parseFloat(purchaseprice)*quantity;
          
          final1.value=parseFloat(totalgstprice).toFixed(2);
         
          totalpricewithoutgst.value = parseFloat(totalpricewithoutgstvalue).toFixed(2);
      }
	 
	 function calculateTransportationCharges() {

	      	var grandtotal=document.getElementById("itemsTotalAmount").value;
	      	
	      	        	
	          var final1=document.getElementById("itemsGrandTotalAmount");
	          var transportationcharges=document.getElementById("transportationcharges").value;
	          var totalprice = parseFloat(grandtotal) + parseFloat(transportationcharges);
	          final1.value = parseFloat(totalprice).toFixed(2);
	         
	      }
	
	var itemlist=[
        <c:forEach varStatus="status" items="${messstockavailabilitylist}" var="itemlist">{
                value:'<c:out default="0" value="${itemlist.messitems.name}" />',
                particularname:'<c:out default="0" value="${itemlist.messitems.unitofmeasure}" />',
                id:'<c:out default="0" value="${itemlist.messitems.id}" />'
                }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ];
	
	function addRow() {
        var rowCount = document.getElementById('dataTable').rows.length;    
        
        var col1="<td class='dataTextInActive'><input type='checkbox' class = 'chcktbl' id=items_"+rowCount+" /><input type='hidden' name='itemids' id=items_id_"+rowCount+" value='' /></td>";
        var col2="<td class='dataTextInActive'><input type='text' name='itemsname' id=items_name_"+rowCount+" class='textfieldvalues' style='font-size: 14px;'/></td>";
        var col4="<td class='dataTextInActive'><input  value='0'   name='itemsunitofmeasure'  id=items_unitofmeasure_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' readonly/></td>";
        var col5="<td class='dataTextInActive'><input type='text' value='0'   name='itemsquantity'  id=items_quantity_"+rowCount+" class='textfieldvaluesshorts' style='font-size: 14px;' onkeyup='calculate("+rowCount+")'/></td>";
        var newRow = $("<tr class='trClass'>"+col1+col2+col4+col5+"</tr>");
        $(function() {
            $("#dataTable").find('tbody').append(newRow);
        });
        $(function() {
            $("#items_name_"+rowCount).autocomplete({
                source: itemlist,
                minLength: 1,
                change:function(event,ui){
                	
                    $("#items_id_"+rowCount ).val( ui.item.id );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                },
                focus: function( event, ui ) {
                    $( "#items_name_"+rowCount).val( ui.item.name );
                    $( "#items_id_"+rowCount ).val( ui.item.id );
                    $("#items_unitofmeasure"+rowCount).val( ui.item.particularname );

                    return true;
                },
                select: function( event, ui ) {
                    $( "#items_name_"+rowCount).val( ui.item.value );
                    $( "#items_id_"+rowCount ).val( ui.item.id );
                    $("#items_unitofmeasure_"+rowCount).val( ui.item.particularname );
                   
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +":&nbsp;</b> <b> "+item.particularname +"</b></a>" )
                .appendTo( ul );
            };

        });
    }
	
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
    }
	
	$(document).ready(function() {
        
        
        $("#dataTable").keyup(function(){
            
            var sum = 0.0;
            var totalSum=0.0;
            var column2 = $('.linetotalAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            
            var sum2 = 0.0;
            var column1 = $('.linetotalAmountwithoutgst')
            jQuery.each(column1,function(){
            sum2 += parseFloat($(this).val());
            });
            $('#itemsGrandTotalAmountWithoutGST').val(sum2);
            
            $('#itemsTotalAmount').val(sum);
            
        });
        $("#dataTable").click(function(){
            
            var sum = 0.0;
            var totalSum=0.0;
            var column2 = $('.linetotalAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            
            var sum2 = 0.0;
            var column1 = $('.linetotalAmountwithoutgst')
            jQuery.each(column1,function(){
            sum2 += parseFloat($(this).val());
            });
            $('#itemsGrandTotalAmountWithoutGST').val(sum2);
            
            $('#itemsTotalAmount').val(sum);
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
            var totalSum=0.0;
            var totalSum2=0.0;
            var column2 = $('.linetotalAmount')
            jQuery.each(column2,function(){
                sum += parseFloat($(this).val());
            });
            totalSum=sum;
            
            var sum2 = 0.0;
            var column1 = $('.linetotalAmountwithoutgst')
            jQuery.each(column1,function(){
            sum2 += parseFloat($(this).val());
            });
            totalSum2=sum2;
            $('#itemsGrandTotalAmountWithoutGST').val(totalSum2);
            
            $('#itemsTotalAmount').val(totalSum);
            	calculateGrandTotal();
            //$('#grandTotalAmount').val(0);
        }catch(e) {
            alert(e);
        }
    }

    
	 $(function(){
         
         $("#saveitemsinventory").button({
             icons:{
                 primary: "ui-icon-check"
             }
         }).click(function(){
             saveInventory();
             return false;

         });
         
         $('#chckHeadReceived').click(function () {
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
         
         $("#cancel").button({
             icons:{
                 primary: "ui-icon-cancel"
             }
         }).click(function(){
             cancelPurchase();
             return false;

         });
     });
</script>

<script type="text/javascript">

            function openPopup(externalId,date, supplierrefno, name, invoicetotal){
            	
        			 if (typeof XMLHttpRequest != "undefined") {
        				 xmlHttp = new XMLHttpRequest();
        	            
        	         } else if (window.ActiveXObject) {
        	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        	             
        	         }
        			xmlHttp.onreadystatechange = stateChangedSSGroup;
        			xmlHttp.open("GET", "/childrenandglobal/stockentry/mrvDetails?invoicedetailsid="+externalId+"&entrydate="+date+"&supplierreferenceno="+supplierrefno+"&suppliername="+name+"&invoicetotal="+invoicetotal+"",true);
        			xmlHttp.send(null);

        		
                $( "#dialog" ).dialog( "open" );
            }
            
            function stateChangedSSGroup() {

        		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        			document.getElementById("mrvdetails").innerHTML = xmlHttp.responseText;
        		}
        	}
            
            function selectVendor(name,id){
                var vendorName=document.getElementById('vendorName');
                var vendorID=document.getElementById("vendorID");
                vendorName.value=name;
                vendorID.value=id;
                $( "#dialog" ).dialog( "close" );

            }
        </script>


        <script type="text/javascript">

            $(function() {
                $( "#dialog" ).dialog({
                    autoOpen: false,
                    height: 400,
                    width: 600,
                    modal: true,
                    buttons: {
                        Cancel: function() {
                            $( this ).dialog( "close" );
                        }

                    }
                });
            });



        </script>
        
        <script type="text/javascript">
					
					var itemsreceived = '<c:out default="" value="${itemsreceived}"/>';
		            
		            if(itemsreceived == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            	 });
		            	 }else if(itemsreceived == "false"){
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
        
        <script type="text/javascript">

            function openPopup(invoicedetailsid,date,name){
            	
        			 if (typeof XMLHttpRequest != "undefined") {
        				 xmlHttp = new XMLHttpRequest();
        	            
        	         } else if (window.ActiveXObject) {
        	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        	             
        	         }
        			xmlHttp.onreadystatechange = stateChangedSSGroup;
        			xmlHttp.open("GET", "/childrenandglobal/stockentry/purchaseMrvDetails?invoicedetailsid="+invoicedetailsid+"&entrydate="+date+"&suppliername="+name+"",true);
        			xmlHttp.send(null);

        		
                $( "#dialog" ).dialog( "open" );
            }
        </script>
        
        <script type="text/javascript">
					
					var itemsreceived = '<c:out default="" value="${itemsreceived}"/>';
		            
		            if(itemsreceived == "true"){
		            	 $(function(){
		            		 $( "div.success" ).fadeIn( 800 ).delay( 2000 );
		            	 });
		            	 }else if(itemsreceived == "false"){
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
		        	
		        	
		        	 function printlayer(layer){
		             	var generator = window.open(",");
		             	var layetext = document.getElementById(layer);
		             	generator.document.write(layetext.innerHTML.replace("Print Me"));
		             	
		             	generator.document.close();
		             	generator.print();
		             	generator.close();
		             }
		        	
        </script>
        

</head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/childrenandglobal/UserProcess/sessionTimeOut");
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
		<div class="alert-box success" id="div1">Items has been received successfully!!!&nbsp;&nbsp;&nbsp;<button class="button" id="1" onclick="closediv(this.id);">OK</button></div>
		<div class="alert-box failure" id="div2">Items received failed, please try again!!!&nbsp;&nbsp;&nbsp;<button class="buttonred" id="2" onclick="closediv(this.id);">OK</button></div>
		
		<div style="height: 28px">
			<button id="add">Purchase Order</button>
			<br />
		</div>

		<div id="effect" class="ui-widget-content ui-corner-all">
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">Order Details</a></li>

				</ul>
				<div id="tabs-1">
				<br>
				<br>
				<table  style="margin-left: auto;margin-right: auto;">
						
						<tr>
							<td class="alignRight">Supplier &nbsp;</td>
							<td>
								<select name="supplierid" id="supplierid" class="dropdownlist" style="font-size: 14px;" required>
										<c:forEach items="${messsupplierslist}" var="messsupplierslist">
											<option value="${messsupplierslist.id}:${messsupplierslist.linkedledgerid}:${messsupplierslist.name}">${messsupplierslist.name}</option>
										</c:forEach>
								</select>
							</td>
							
							<!-- <td class="alignRight">&nbsp;&nbsp;&nbsp;Ref./Invoice No.&nbsp;</td>
							<td ><input type="text" id="supplierreferenceno" name="supplierreferenceno" class="textfieldvalues" style="font-size: 14px;">
							</td> -->
							
						</tr>
						<tr>
							<td><br /></td>
	
							</tr>
						<tr>
						<td class="alignRight">Purchase Date&nbsp;</td>
							<td><label> <input	name="invoicedate"	type="text" value="<fmt:formatDate type="date" value="${now}" pattern="dd/MM/yyyy"/>" class="textfieldvalues" id="invoicedate" style="font-size: 14px;" autocomplete="false" required>
							</label></td>
							
							
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
								<th class="headerText">
								<input type="checkbox" id="chckHeadReceived" />
							</th>
								<th class="headerText">Item Name</th>
								<th class="headerText">UOM</th>
								<th class="headerText">Quantity</th>
								
								
							</tr>
						</thead>

						<tbody>	
						<c:forEach items="${purchasedetail}" var="purchasedetail">
						<tr>
						<td class='dataTextInActive'><input type="checkbox" id = "<c:out value="${purchasedetail.itemId}"/>" class = "chcktbl"  name="itemids"  value="<c:out value="${purchasedetail.itemId}"/>"/></td>
						<td class='dataTextInActive'><input type='text' value="${purchasedetail.supplierName}"  class='textfieldvalues' style='font-size: 14px;'/></td>
						<td class='dataTextInActive'><input type='text' value="${purchasedetail.quantity}"  class='textfieldvaluesshorts' style='font-size: 14px;'/></td>
						</tr>
						</c:forEach>
						</tbody>
							<tfoot>
							
														

						</tfoot>
					</table>
					<div align="center">
						<p>
						<label><button id="saveitemsinventory">Save</button></label></p>
						
									
					</div>
				</div> 
			</div>
		</div>

		<div style="overflow: scroll; height: 600px">
			<table width="100%">
				<tr>
					<td class="headerTD">Items Received List</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Serial No.</th>
						<th title="click to sort" class="headerText">P.O. No.</th>
						<th title="click to sort" class="headerText">Supplier Id</th>
						<th title="click to sort" class="headerText">Supplier Name</th>
						<th title="click to sort" class="headerText">Status</th>
						<th title="click to sort" class="headerText">Received Status</th>
						
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${polist}" var="polist" varStatus="status">
						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							
							
                          <td class="dataText"><input type="checkbox" id = "<c:out value="${polist.key.id}"/>" class = "chcktbl"  name="invoiceid"  value="<c:out value="${polist.key.id}"/>"/></td>
						  <td class="dataText">${status.index+1}</td>
						  <td class="dataText"><c:out value="${polist.key.externalId}" /></td>
						  <td class="dataText"><c:out value="${polist.key.id}" /></td>
						  <td class="dataText"><c:out value="${polist.value.name}" /></td>
						  <td class="dataText"><a href="#" onclick="openPopup('<c:out value="${polist.key.externalId}"/>','<c:out value="${polist.key.entryDate}"/>','<c:out value="${polist.value.name}"/>')">View Detail</a></td>
						  <td class="dataText"><a href="/childrenandglobal/MessItemsProcess/receivePurchaseOrder?id=<c:out value='${polist.key.externalId}'/>">Receive</a></td>
						</tr>
					</c:forEach>

				</tbody>
					<tfoot>
						<tr>
                            <!-- <td  class="footerTD" colspan="2" ><button id="delete" type="submit">Delete</button>  -->
                    		<td class="footerTD"  colspan="8">
                    		<!-- <button id="print">Print</button> --> 
                    		<!-- &nbsp;&nbsp;&nbsp;
                    		<button id="approve">Approve</button>
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
                    <td><a style="color: #4B6A84;font-size: 12px" href="/childrenandglobal/MessItemsProcess/purchaseItems?page=${currentPage - 1}">Previous</a></td>
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
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/childrenandglobal/MessItemsProcess/purchaseItems?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="/childrenandglobal/MessItemsProcess/purchaseItems?page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>

		</div>


		<div id="dialog" title="Items Receive Details">
				
                
             	 <div id="mrvdetails">
              			
              			
           		 </div>
           		 <div align="center">
           		 <br><br>
           		 <a href="#" id="print" onclick="javascript: printlayer('mrvdetails')">Print</a>
           		 </div>
           		 
           		 <!-- <table width="100%" border="0">
						
						
						
						<tr>
						<td width="30%" class="alignRight">Sub-Group Name &nbsp;</td>
						
							<td width="12%" align="left" id="mrvdetails"><label> <select name="subgroupname" id="sgname"  onchange="dropdowndist()"
									style="width: 240px" ">
									<option >UI</option>
								</select>
							</label>
							
							</td>
							
						</tr>
						
						</table> -->
			</div>



	</form>

</body>
</html>