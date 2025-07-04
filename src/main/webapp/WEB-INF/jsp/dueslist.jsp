<%--
    Document   : View Diary
    Created on : Mar 09, 2018, 3:05:28 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dues List</title>
<link rel="stylesheet" href="/shadaan/css/datePicker/jquery-ui-1.8.18.custom.css">
<link rel="stylesheet" href="/shadaan/css/datePicker/demos.css">
<style type="text/css">
<!--
.divCSS {
	overflow: scroll;
	height: 100%;
	width: 100%;
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
<style>
#button {
	
}
</style>
<link rel="stylesheet" href="/shadaan/css/validation/jquery.ketchup.css">
<script type="text/javascript" src="/shadaan/js/datePicker/jquery-1.7.1.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" language="javascript"
	src="/shadaan/js/dataTable/jquery.dataTables.js"></script>
<script type="text/javascript" src="/shadaan/js/datePicker/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.ui.widget.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="/shadaan/js/datePicker/ui/jquery.ui.tabs.js"></script>
<script type="text/javascript" src="/shadaan/js/datePicker/ui/sliderAccess.js"></script>

<script type="text/javascript"
	src="/shadaan/js/validation/jquery.ketchup.all.min.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.ui.button.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.core.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.ui.accordion.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.slide.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.bounce.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.clip.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.transfer.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/jquery.effects.blind.js"></script>
<script type="text/javascript"
	src="/shadaan/js/datePicker/ui/ScrollableGridPlugin.js"></script>
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

<script type="text/javascript" src="/shadaan/js/datetimepicker_css.js"></script>
<script type="text/javascript">
$(function(){
    $("#delete").button({
        icons:{
            primary: "ui-icon-trash"
        }
    }).click(function(){
        deleteRecords();
        return false;

    });
    function deleteRecords(){
        
        var form1=document.getElementById("form1");
        form1.action="/shadaan/DiaryProcess/deleteRecord";
        form1.method = "POST";
       form1.submit();
        
    }

	
	//chechbox
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
         

</script>


 <script type="text/javascript">

            function openPopup(id,date,studentName,branchreceiptnumber,due){
            	
        			   if (typeof XMLHttpRequest != "undefined") {
        				 xmlHttp = new XMLHttpRequest();
        	            
        	         } else if (window.ActiveXObject) {
        	        	 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        	             
        	         }
        			xmlHttp.onreadystatechange = stateChangedSSGroup;
        			xmlHttp.open("GET", "/shadaan/stockentry/dueMrvDetails?date="+date+"&studentName="+studentName+"&branchreceiptnumber="+branchreceiptnumber+"&due="+due+"",true);
        			xmlHttp.send(null);  
        			 document.getElementById("itemsGrandTotalAmount").value = due;
        			 document.getElementById("dueid").value = id;
        		
                $( "#dialog" ).dialog( "open" );
                
                //from here
            }
        </script>
                <script type="text/javascript">

          /*   $(function() {
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
            }); */


//fom here
        </script>
     <script type="text/javascript">

          
            
            function stateChangedSSGroup() {

        		if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete") {
        			document.getElementById("dueMrvDetails").innerHTML = xmlHttp.responseText;
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
                        Pay: function() {
                         	
                   		 generatebill(document.getElementById("itemsGrandNetDueAmount"),document.getElementById("dueid"));
                        		$( this ).dialog( "close" );
                 		   }
                }
            });
        });

              function generatebill(itemsGrandNetDueAmount,dueid){
            	  var dueAmountValue = '';
            	  var dueidValue = '';
            	  dueAmountValue = itemsGrandNetDueAmount.value;
            	  dueidValue = dueid.value;
            	var form1 = document.getElementById("form1");
        		form1.action="/shadaan/MessItemsMoveProcess/paydue?itemsGrandNetDueAmount="+dueAmountValue+"&dueid="+dueidValue+"";
        		form1.method = "POST";
        		form1.submit();
        		
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
        
        <script>
    function getAmount() {
        // Get input values and parse them as numbers
        var gTotal = parseFloat(document.getElementById("itemsGrandTotalAmount").value) || 0;
        var cashAmount = parseFloat(document.getElementById("totalcashamount").value) || 0;
        var bankAmount = parseFloat(document.getElementById("totalbanktransferamount").value) || 0;
        var chequeAmount = parseFloat(document.getElementById("totalchequetransferamount").value) || 0;

        // Calculate total paid
        var totalPaid = cashAmount + bankAmount + chequeAmount;

        // Calculate due amount
        var dueAmount = gTotal - totalPaid;
        var dueAmountString = dueAmount.toFixed(2).toString();

        // Update the output fields
        document.getElementById('itemsGrandNetTotalAmount').value = totalPaid.toFixed(2);
        document.getElementById('itemsGrandNetDueAmount').value = dueAmount.toFixed(2);
       // document.getElementById('itemsGrandNetDueAmount').value = dueAmountString;
    }
</script>
 
<script type="text/javascript">
$(function(){
    $("#save").button({
        icons:{
            primary: "ui-icon-trash"
        }
    }).click(function(){
        payDue();
        return false;

    });
});
    function payDue(){
        
        var form1=document.getElementById("form1");
        form1.action="/shadaan/MessItemsMoveProcess/paydue";
        form1.method = "POST";
       form1.submit();
        
    }

    </script>

</head>
  <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/shadaan/UserProcess/sessionTimeOut");
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

	

		<div style="overflow: hidden">
			<table width="100%">
				<tr>
					<td class="headerTD">Unpaid Report</td>
				</tr>
			</table>
			<table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
					<tr>
						<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Date</th>
						<th title="click to sort" class="headerText">Student Name</th>
						<th title="click to sort" class="headerText">Branch Receipt Number&nbsp;</th>
						<th title="click to sort" class="headerText">Academic Year</th>
						<th title="click to sort" class="headerText">Due Amount&nbsp;</th>
						<th title="click to sort" class="headerText">Pay Due</th>
						
					</tr>
				</thead>

				<tbody>

					<c:forEach items="${dueslist}" var="dueslist">

						<tr style="border-color: #000000" border="1" cellpadding="1"
							cellspacing="1">
							<td class="dataText"><input type="checkbox"
								id="<c:out value="${diary.id}"/>" class="chcktbl"
								name="id"
								value="<c:out value="${diary.id}"/>" /></td>
							<td class="dataText"><c:out value="${dueslist.date}" /></td>
							<td class="dataText"><c:out value="${dueslist.studentName}" /></td>
							<td class="dataText"><c:out value="${dueslist.branchreceiptnumber}" /></td>
							<td class="dataText"><c:out value="${dueslist.academicyear}" /></td>
							<td class="dataText"><c:out value="${dueslist.due}" /></td>
							<td class="dataText"><a href="#" onclick="openPopup('<c:out value="${dueslist.receiptnumber}"/>','<c:out value="${dueslist.date}"/>','<c:out value="${dueslist.studentName}"/>','<c:out value="${dueslist.branchreceiptnumber}"/>','<c:out value="${dueslist.due}"/>')">Pay Due</a></td>
						</tr>
					</c:forEach>




				</tbody>
				<tfoot>
					<tr>
						<td class="footerTD" colspan="2"><button id="delete">Delete record</button>
							</td>
							

					</tr>
				</tfoot>
			</table>

		</div>

 <div align="center">
             <%--For displaying Previous link except for the 1st page --%>
                <c:if test="${currentPage != 1}">
                    <td><a style="color: #4B6A84;font-size: 12px" href="/shadaan/DiaryProcess/viewdiarystudent?page=${currentPage - 1}">Previous</a></td>
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
                                    <td style="color: black;font-weight:bold;font-size: 15px "><a style="color: #4B6A84" href="/shadaan/DiaryProcess/viewdiarystudent?page=${i}">${i}</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>

                <%--For displaying Next link --%>
                <c:if test="${currentPage lt noOfPages}">
                    <td ><a style="color: #4B6A84;font-size: 12px" href="/shadaan/DiaryProcess/viewdiarystudent?page=${currentPage + 1}">Next</a></td>
                </c:if>
                    </div>
                    
                    <div id="dialog" title="Items Receive Details">
				
                
             	 <div id="dueMrvDetails">
              			
           		 </div>
           		 <div align="center">
           		 <table style="width: auto;height: auto;">
					<tr>
					        <td>
           		 				Net Amount:</td><td> &nbsp;<input type="text" name="itemsGrandNetTotalAmount" id="itemsGrandNetTotalAmount" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" />
           		 				<input type="hidden" name="itemsGrandTotalAmountWithoutGST" id="itemsGrandTotalAmountWithoutGST" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" />
           		 				<br>
           		 			</td>
           		 				
           		 			<td>
           		 				Due Amount:</td><td> &nbsp;<input type="text" name="itemsGrandNetDueAmount" id="itemsGrandNetDueAmount" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" />
           		 				<br>
           		 			</td>
           		 			  
           		 			<td>
           		 				Current Total Due:</td><td> &nbsp;<input type="text" name="itemsGrandTotalAmount" id="itemsGrandTotalAmount" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" readonly/>
           		 				<input type="hidden" name="dueid" id="dueid" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" />
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
								Amount &nbsp;<input type="text" name="totalcashamount" id="totalcashamount" onkeyup="getAmount()" class="textfieldvaluesshorts" value="0" style="font-size: 14px;font-weight: bold;" />														
							</td>
							
						</tr>
						<tr>
							<td><br></td>
						</tr>
						<tr id="banktransferamount" style="display: none;">
							<td></td>
						
							<td>
								Amount &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" name="totalbanktransferamount" id="totalbanktransferamount" onkeyup="getAmount()" class="textfieldvaluesshorts" value="0" style="font-size: 14px;font-weight: bold;"/>														
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
								Amount &nbsp;&nbsp;&nbsp;<input type="text" name="totalchequetransferamount" id="totalchequetransferamount" onkeyup="getAmount()" value="0" class="textfieldvaluesshorts" style="font-size: 14px;font-weight: bold;" value="0"/>														
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
           		 <br><br>
           		<!--  <a href="#" id="print" onclick="javascript: printlayer('dueMrvDetails')">Print</a> -->
           		 <!-- <button id="save" >Pay Now</button> -->
           		 </div>
           		 
           				</div>
                    
                    
	</form>

</body>
</html>
