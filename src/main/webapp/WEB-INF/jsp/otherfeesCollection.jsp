<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Other Fees Collection</title>
<link rel="stylesheet" href="/abc/css/datePicker/jquery-ui-1.8.18.custom.css">
        <link rel="stylesheet" href="/abc/css/graph/jquery.jqplot.css">

        <link rel="stylesheet" href="/abc/css/datePicker/demos.css">
        <script type="text/javascript" src="/abc/js/datePicker/jquery-1.7.1.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.dialog.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.autocomplete.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.core.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.datepicker.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.accordion.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/sliderAccess.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery-ui-timepicker-addon.js"></script>
        <script  type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.position.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.mouse.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.draggable.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.ui.resizable.js"></script>

        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.effects.slide.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.effects.bounce.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.effects.clip.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.effects.transfer.js"></script>
        <script type="text/javascript" src="/abc/js/datePicker/ui/jquery.effects.blind.js"></script>
        <style type="text/css">
            <!--
            .labelCss {
                font-family: Tahoma;
                font-size: 11px;
                font-weight: bold;
            }
            .dataTextInActive {
                border-radius:1px;
                font-family: Tahoma;
                color: #4b6a84;
                font-size: 12px;
                font-weight: bold;
                letter-spacing: normal;
                text-align: center;
                vertical-align: top;
                text-decoration:none;
            }
            .headerText {
                border-radius:3px;
                font-family: Tahoma;
                font-size: 12px;
                background-color: #4b6a84;
                color: #FFFFFF;
                font-weight: normal;
                width: auto ;
                height: 22px;
                vertical-align: middle;
                text-align: center;
                font-size: 16px;
                background-image: url("/images/ui-bg_diagonals-small_50_466580_40x40.png");
            }
            .headerTD{
                background-color:#4b6a84;
                color: #FFFFFF;
                font-family: Tahoma;
                font-size: 13px;
                text-transform: uppercase;
                text-align: center;
                font-weight: bold;
            }
            .smallheaderTD{
                color: #4b6a84;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .textFieldFixedWidth{
                width: 57px;
            }
            .subHeaderTD{
                color: #325F6D;
                font-family: Tahoma;
                font-size: 11px;
                text-transform: uppercase;
                text-align: left;
                font-weight: bold;
            }
            .divCSS{
                overflow:  scroll;
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
				font-size: 14px;
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
            .myclass {
				font-size: 1.3em;
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
				height: 28px;
				color: black;
				text-transform: capitalize;
				border-radius: 4px;
			}
        </style>
        <script type="text/javascript">
            /**
             * Comment
             */
            function calculateGrandTotal() {
                var sum = 0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                $('#grandTotalAmount').val(sum);
            }
            
            
            $(document).ready(function() {
                $("#dateoffees").val(getCurrentDate());
                //$("#dateoffees").datepicker();
                
                
                $("#balanceamount").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum);
                });
                $("#miscellanousamount").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum);
                });
                $("#dataTable").keyup(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#feesTotalAmount').val(sum.toPrecision(6));
                    $('#grandTotalAmount').val();
                });
                $("#myTable").keyup(function(){
                   
                    var sum = 0.0;
                    var totalSum=0.0;
                    var amountp = $('.amountpaying');
                    jQuery.each(amountp,function(){
                        sum += parseFloat($(this).val());
                    });
                    var finep = $('.fine');
                    jQuery.each(finep,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum;
                    $('#grandTotalAmount').val(totalSum);
                });
               
                 $(document).ready(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum);
                });
                $(document).ready(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#grandTotalAmount').val(totalSum);
                });
                $(document).ready(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#feesTotalAmount').val(sum.toPrecision(6));
                    $('#grandTotalAmount').val();
                });
                $(document).ready(function(){
                   
                    var sum = 0.0;
                    var totalSum=0.0;
                    var amountp = $('.amountpaying');
                    jQuery.each(amountp,function(){
                        sum += parseFloat($(this).val());
                    });
                    var finep = $('.fine');
                    jQuery.each(finep,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum;
                    $('#grandTotalAmount').val(totalSum);
                });
                
                $("#dataTable").click(function(){
                    var consultation=parseFloat($("#balanceamount").val());
                    var miscellanous=parseFloat($("#miscellanousamount").val());
                    var sum = 0.0;
                    var totalSum=0.0;
                    var column2 = $('.feesAmount')
                    jQuery.each(column2,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum+miscellanous+consultation;
                    $('#feesTotalAmount').val(sum.toPrecision(6));
                    $('#grandTotalAmount').val(totalSum);
                });
            });
            /**
             * Comment
             */
            /**
             * Comment
             */
            /**
             * Comment
             */
            function validate() {
                if(validatePatient() & validatefees()){
                    return true;
                }
                else{
                    return false;
                }
            }
            function validatePatient() {
                if($("#studentId").val()==""){
                    alert("Select Student");
                    return false;
                }
                else{
                    return true;
                }
            }
            function validatefees() {
                var count = 0;
                var idColumn = $('.feesStatus')
               
                if(idColumn.length==0){
                    alert("Add fees");
                    return false;
                }else{
                    jQuery.each(idColumn,function(){
                    
                        if($(this).val()=="not set"){
                        
                            count++;
                        }
                    });
                    if(count >0){
                        alert("Add fees");
                        return false;
                    }
                    else{
                        return true;
                    }
                
                }
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
           
            function getCurrentDate(){
                var today = new Date();
                var day=today.getDate();
                var month=today.getMonth()+1;
                var year=today.getFullYear();
                if(month <10){
                    month="0"+month;
                }
                else {
                    month=month;
                }
                if(day <10){
                    day="0"+day;
                }
                else {
                    day=day;
                }
                return day+"/"+month+"/"+year;
            }
           
            var students = [
            <c:forEach varStatus="status" items="${studentListFeesCollection}" var="parent">{
            	value:'<c:out default="0" value="${parent.student.name}" />',
                admissionno:'<c:out default="0" value="${parent.student.admissionnumber}" />',
                regno:'<c:out default="0" value="${parent.student.studentexternalid}" />',
                name:'<c:out default="0" value="${parent.student.name}" />',
                classandsec:'<c:out default="0" value="${parent.student.classstudying}" />',
                id:'<c:out default="0" value="${parent.student.sid}" />',
                fathername:'<c:out default="0" value="${parent.fathersname}" />',
                
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        $(function() {
            $( "#studentname").autocomplete({
                source: students,
                minLength: 1,
                change:function(event,ui){
                    $( "#studentId").val( ui.item.id );
                    
                    
                },
                focus: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
                    return true;
                },
                select: function( event, ui ) {
                    $( "#studentId").val( ui.item.id );
       			  $( "#studentName").val( ui.item.name );
       			$( "#classandsec").val( ui.item.classandsec );
       			$( "#admissionno").val( ui.item.admissionno );
                    /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +" / "+item.classandsec+" / "+item.regno+" / "+item.fathername+" </b> </a>" )
                .appendTo( ul );
            };
            var addFeesButtonID="#addFees";
            var removeDossageButtonID="#removeDossage";
            $( addFeesButtonID )
            .button({
                icons: {
                    primary: "ui-icon-search"
                }
            })
            .click(function() {
            	 getstampfees();
                 //addRow();
                return false;
            });
            $(removeDossageButtonID)
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
        $('#selectAll').click(function () {
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
        /**
         *
         */
         
         $(function(){
            
             $('#chckHead').click(function () {
                 var length = $('.chcktb2:checked').length;
                 var trLength=$('.trClass').length;
                 if(length>0){
                     $('.chcktb2:checked').attr('checked', false);
                     this.checked=false;
                 }
                 else{
                     if (this.checked == false) {
                         $('.chcktb2:checked').attr('checked', false);
                     }
                     else {
                         $('.chcktb2:not(:checked)').attr('checked', true);
                     }
                 }
             });
             $('.chcktb2').click(function () {
                 var length = $('.chcktb2:checked').length;
                 var trLength=$('.trClass').length;
                 alert(tdLength);
                 if (length > trLength) {
                     $('.chcktb2:not(:checked)').attr('disabled', true);
                 }
                 else {
                     $('.chcktb2:not(:checked)').attr('disabled', false);
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
               
                var consultation=parseFloat($("#balanceamount").val());
                var miscellanous=parseFloat($("#miscellanousamount").val());
                var sum = 0.0;
                var totalSum=0.0;
                var column2 = $('.feesAmount')
                jQuery.each(column2,function(){
                    sum += parseFloat($(this).val());
                });
                totalSum=sum+miscellanous+consultation;
                $('#feesTotalAmount').val(sum);
                $('#grandTotalAmount').val(totalSum);
                
                calculateGrandTotal();
                //$('#grandTotalAmount').val(0);
            }catch(e) {
                alert(e);
            }
        }
        function calculate(value2) {
            //var val1=value1.value;
            var actualQuantity=document.getElementById("hiddenfees_quantity_"+value2).value;
            var val1=document.getElementById("fees_quantity_"+value2).value;
            var val2=document.getElementById("hiddenfees_amount_"+value2).value;
            var final1=document.getElementById("fees_amount_"+value2);
            if(parseInt(val1) > parseInt(actualQuantity) ){
                final1.value=0;
                alert("Enter the valid fees amount");
            }
            
            else{
                final1.value=(val2);
            }    
        }
       
        </script>
        <script type="text/javascript">
    $(function() {
        // run the currently selected effect
        function runEffect() {
            var clipEffect='blind';
            var options = {};
            $( "#effect" ).toggle( clipEffect, options, 1000 );
        };
        // set effect from select menu value
        $( "#add" ).button().click(function() {
            runEffect();
            return false;
        });
    });
        </script> 
        <script type="text/javascript">
    $(function() {
        $( "#add" )
        .button()
        .click(function() {
            runEffect();
        });
        $( "#submit" ).button();
        $( "#effect" ).hide();
    });
            
        </script>
                <script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
                
                $("#amountpaying").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
       		   });
                
                
                $("#fine").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
       		               return false;
       		    }
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
            	
            	var form1 = document.getElementById("form1");
        		form1.action="/abc/FeesCollection/feesAddother?paymentmethod="+paymentmethodvalue+"&ackno="+acknovalue+"&transferdate="+transferdatevalue+"&transferbankname="+transferbanknamevalue+"&chequeno="+chequenovalue+"&chequedate="+chequedatevalue+"&chequebankname="+chequebanknamevalue+"";
        		form1.method = "POST";
        		form1.submit();
        		
            }
            
            function getstampfees(){
            	var form1 = document.getElementById("form1");
        		form1.action="/abc/FeesCollection/otherStampFees";
        		form1.method = "POST";
        		form1.submit();
            }
            
            function checkWithDueAmount(duePayment,sfsid){
            	
            	var str = duePayment.id;
            	var res = str.split("_");
            	
            	var dueAmount = parseInt(document.getElementById("dueamount_"+res[1]).value);
            	var payment = parseInt(duePayment.value,10);
            	document.getElementById(sfsid).checked = true; 
            	
            	if(payment<=9 && payment>=1){
            		duePayment.value = payment;
            	}
            	
            	if(payment>dueAmount){
            		duePayment.value = 0;
            		document.getElementById(sfsid).checked = false; 
            		alert('Amount Due to be paid must be lesser than or equals to Due Amount');
            	}
            	
            	if(payment<1 || isNaN(payment)){
            		duePayment.value = 0;
            		document.getElementById(sfsid).checked = false; 
            	}
            	
            	
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
 
		 function toggleAmount(checkbox, index) {
			 	var ind = index;
			    const amountPayingInput = document.getElementById("amountpaying_"+ind);
			    const dueAmountHiddenInput = document.getElementById("dueamount_"+ind);
			    
			    if (checkbox.checked) {
			        amountPayingInput.value = dueAmountHiddenInput.value;
			        var sum = 0.0;
                    var totalSum=0.0;
                    var amountp = $('.amountpaying');
                    jQuery.each(amountp,function(){
                        sum += parseFloat($(this).val());
                    });
                    var finep = $('.fine');
                    jQuery.each(finep,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum;
                    $('#grandTotalAmount').val(totalSum);
			    } else {
			        amountPayingInput.value = 0;
			        var sum = 0.0;
                    var totalSum=0.0;
                    var amountp = $('.amountpaying');
                    jQuery.each(amountp,function(){
                        sum += parseFloat($(this).val());
                    });
                    var finep = $('.fine');
                    jQuery.each(finep,function(){
                        sum += parseFloat($(this).val());
                    });
                    totalSum=sum;
                    $('#grandTotalAmount').val(totalSum);
			    }
			}
            
        </script>

</head>
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/abc/UserProcess/sessionTimeOut");
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
 <form id="form1" method="post">

            <div id="tabs">
				<ul>
					<li><a href="#fragment-1">Other Fees Collection</a></li>
				</ul>

			<div id="fragment-1">
            	<table  width="100%">
                 <tbody>
	                <tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #eb6000;">Search Student:&nbsp;&nbsp;&nbsp;&nbsp;</td> 
                    </tr>

                   	<tr>
                    	<td><br></td> 
                    </tr>

                    <tr>
                    <td style="width: 45%;" class="alignLeft">Student Name: &nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentname" id="studentname" class="myclass" /> <input name="studentId" type="hidden" id="studentId" value="" /> </td>

                        <td class="alignLeft">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" class="myclass" name="dateoffees" id="dateoffees"  readonly="readonly"/></td>
                    </tr>

                    <tr>
						<td><br></td>
                    </tr>


                    <tr>

                        <td class="alignLeft" style="width: 45%">Admission No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admissionno" id="admissionno" class="myclass" readonly/></td>
                        <td class="alignLeft">Class & SEC : &nbsp;&nbsp;&nbsp;<input type="text" name="classandsec" id="classandsec" class="myclass" /></td>

                    </tr>

                    <tr>
						<td><br></td>
                    </tr>

                    <tr>

                        <td class="alignLeft" style="width: 45%">Academic Year:&nbsp;&nbsp;&nbsp;&nbsp; 
                        	   <label>
                                        <label> <select name="academicyear" id="academicyear" required
									 style="width: 184px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${currentAcademicYear}</option>
										<option>2025/26</option>
										<option>2024/25</option>
										<option>2023/24</option>
										<option>2022/23</option>
										<option>2021/22</option>
										<option>2020/21</option>
										<option>2019/20</option>
										<option>2018/19</option>
										<option>2017/18</option>

								</select>

							</label> 
                                    </label>

                        </td>

                    </tr>

                    <tr>
						<td><br></td>
                    </tr>

                    <tr>

                        <td><button id="addFees">Search Fees</button>&nbsp;&nbsp;&nbsp;</td>

                    </tr>

                    <tr>
						<td><br></td>
                    </tr>

					<tr>
                    <td style="width: 45%;font-weight: bold;font-size: 15px;color: #eb6000;">Student Details:&nbsp;&nbsp;&nbsp;&nbsp; 
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
                    <tr>
                    <td class="alignLeft" style="width: 45%">Admission No: &nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admnoDetails" required id="admnoDetails" readonly value="${admnoDetails}" class="myclass" /> <input name="studentIdDetails" type="hidden" id="studentIdDetails" value="${studentIdDetails}" /> </td>

                        <td class="alignLeft">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="dateoffeesDetails" id="dateoffeesDetails" class="myclass" value="${dateoffeesDetails}" /></td>

                    </tr>

                    <tr>

                        <td class="alignLeft" style="width: 45%">Student Name:&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentNameDetails" id="studentNameDetails" value="${studentNameDetails}" class="myclass" readonly/></td>
                        <td class="alignLeft">Class & SEC : &nbsp;&nbsp;&nbsp;

                        		<select name="classandsecDetails"
									id="classandsecDetails" style="width: 184px;border-radius: 4px;background: white;height: 28px;">
										<option selected>${classandsecDetails}</option>
										<c:forEach items="${classdetailslist}" var="classdetailslist">
											<c:if test="${(classdetailslist.classdetails != '')}">
												<option value="${classdetailslist.classdetails}">
													<c:out value="${classdetailslist.classdetails}" />
												</option>
											</c:if>
										</c:forEach>
								</select>

                        </td>

                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>

            <table width="100%" border="1" style="border-color: #4b6a84;font-size: 18px;"
				id="myTable">

				<thead>
                    <tr >
                    	<th class="headerText"><input type="checkbox" id="chckHead" /></th>
                        <td class="headerText">Other Fees Category</td>
                        <td class="headerText">Qty.</td>
                        <td class="headerText">Total Amount/Due Amount</td>                       
                        <td class="headerText">Amount Due to be paid</td>
                        <!-- <td class="headerText">Fine</td> -->

                    </tr>
                </thead>
			
				<tbody>
					<c:forEach items="${studentotherfeesdetails}" var="studentfeesdetails" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" align="center"><input type="checkbox"  class = "chcktb2" checked="checked" 
								onchange="toggleAmount(this, ${status.index})"
								id="<c:out value="${studentfeesdetails.key.sfsid}"/>" 
								name="studentsfsids" 
								value="<c:out value="${studentfeesdetails.key.sfsid}"/>_${status.index}" /></td>
							<td class="dataText" align="center" style="font-weight: bold;font-size: 13px;"><c:out	value="${studentfeesdetails.key.otherfeescategory.feescategoryname}" /></a><input name="idfeescategory" type="hidden" id="idfeescategory" value="${studentfeesdetails.key.idfeescategory}" /></td>
							<td class="dataText" align="center" style="font-weight: bold;font-size: 13px;"><c:out	value="${studentfeesdetails.key.totalinstallment}" /></a></td>
							<td class="dataText" align="center" style="font-weight: bold;font-size: 13px;">
							<c:out value="${studentfeesdetails.key.feesamount}/${studentfeesdetails.value}" />
							<input type="hidden" id="dueamount_${status.index}" value="${studentfeesdetails.value}"/>
							</td>
							<td class="dataText" align="center">
							<%-- onkeyup="checkWithDueAmount(this,${studentfeesdetails.key.sfsid})" --%>
							<input type="text" class="amountpaying" value="${studentfeesdetails.value}" id="amountpaying_${status.index}" name="amountpaying" >
							<input type="hidden" id="fine" value="0" class="fine" name="fine" >
							</td>
							<!-- <td class="dataText" align="center">
							<input type="text" id="fine" value="0" class="fine" name="fine" >
							</td> -->
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>

                    <tr>

                        <td colspan="4" align="right"><b>Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmount" id="grandTotalAmount" value="0" readonly /></b></td>
                    </tr>
                </tfoot>
			</table>
			<table>
					<tr>
						<td align="right"><b>Balance Books&nbsp;&nbsp;</b></td>
                        <td align="left"><label><textarea  name="balancebooks"
											type="text" class="textField" id="balancebooks" rows="2" cols="40"
											
											onkeypress="return validateContactNum(this);"></textarea></label></td>
                    </tr>
                    
                    </table>
            <!-- <TABLE id="dataTable" width="100%" border="1" >
                <thead>
                    <tr >
                        <td class="headerText"><INPUT type="checkbox" id="selectAll"  name="selectAll" onclick="selectAllRow('dataTable')" /></td>
                        <td class="headerText">Fees Category</td>
                        <td class="headerText">Fees Amount</td>                       
                        <td class="headerText">For the month</td>
                        <td class="headerText">Total Amount</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" align="right">Total&nbsp;&nbsp;</td>
                        <td align="center"><input type="text" name="feesTotalAmount" id="feesTotalAmount" value="0" readonly /></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="right"><b>Grand Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmount" id="grandTotalAmount" value="0" readonly /></b></td>
                    </tr>
                </tfoot>
            </TABLE>
 -->

            <!-- <input type="submit" value="submit" id="submit"/> -->


            <input type="button" value="submit" id="submitbtn"/>


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
            </div>
            </div>
        </form>


</body>
</html>