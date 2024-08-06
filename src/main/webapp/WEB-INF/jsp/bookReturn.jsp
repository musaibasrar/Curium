<%-- 
    Document   : feescollection
    Created on : Jul 24, 2012, 4:07:26 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Return</title>
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
                
                
               /*  $("#submitbtn").button().click(function(){
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
                   }); */
                   
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
        		form1.action="/abc/FeesCollection/feesAdd?paymentmethod="+paymentmethodvalue+"&ackno="+acknovalue+"&transferdate="+transferdatevalue+"&transferbankname="+transferbanknamevalue+"&chequeno="+chequenovalue+"&chequedate="+chequedatevalue+"&chequebankname="+chequebanknamevalue+"";
        		form1.method = "POST";
        		form1.submit();
        		
            }
            
            function getstampfees(){
            	var form1 = document.getElementById("form1");
        		form1.action="/abc/LibraryProcess/searchbooks";
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
            
            
		function checkFineAmount(duePayment,finemiscid){
            	
            	var fineAmount = parseInt(document.getElementById("fineamount").value);
            	
            	if(fineAmount<1 || isNaN(fineAmount)){
            		document.getElementById("fineamount").value = 0;
            		document.getElementById(finemiscid).checked = false;
            	 }else{
            		 document.getElementById("fineamount").value = fineAmount;
            		 document.getElementById(finemiscid).checked = true;
            	 }
            	
            }
		
		function checkMiscAmount(duePayment,finemiscid){
        	
        	
        	var miscAmount = parseInt(document.getElementById("miscamount").value);
        	
        	if(miscAmount<1 || isNaN(miscAmount)){
        		document.getElementById("miscamount").value = 0;
        		document.getElementById(finemiscid).checked = false;
        	 }else{
        		 document.getElementById("miscamount").value = miscAmount;
        		 document.getElementById(finemiscid).checked = true;
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
            
        </script>
         <script type="text/javascript">
            var students = [
            <c:forEach varStatus="status" items="${studentListtc}" var="parents">{
                value:'<c:out default="0" value="${parents.student.name}" />',
                admissiondate:'<c:out default="0" value="${parents.student.admissiondate}" />',
                admissionnumber:'<c:out default="0" value="${parents.student.admissionnumber}" />',
                fathername:'<c:out default="0" value="${parents.fathersname}" />',
                mothername:'<c:out default="0" value="${parents.mothersname}" />',
                nationality:'<c:out default="0" value="${parents.student.nationality}" />',
                gender:'<c:out default="0" value="${parents.student.gender}" />',
                religion:'<c:out default="0" value="${parents.student.religion}" />',
                caste:'<c:out default="0" value="${parents.student.caste}" />',
                dateofbirth:'<c:out default="0" value="${parents.student.dateofbirth}" />',
                classandsec:'<c:out default="0" value="${parents.student.classstudying}" />',
                classadmittedin:'<c:out default="0" value="${parents.student.classadmittedin}" />',
                uid:'<c:out default="0" value="${parents.student.studentexternalid}" />',
                id:'<c:out default="0" value="${parents.student.sid}" />',
                
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        $(function() {
            $( "#admno").autocomplete({
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
                    $( "#dateofadmission").val( ui.item.admissiondate );
       			  $( "#studentName").val( ui.item.name );
       			$( "#fathername").val( ui.item.fathername );
       			$( "#mothername").val( ui.item.mothername );
       			$( "#admissionnumber").val( ui.item.admissionnumber );
       			$( "#nationality").val( ui.item.nationality );
       			$( "#gender").val( ui.item.gender );
       			$( "#religion").val( ui.item.religion );
       			$( "#caste").val( ui.item.caste );
       			$( "#dateofbirth").val( ui.item.dateofbirth );
       			$( "#classandsec").val( ui.item.classandsec );
       			$( "#classadmitted").val( ui.item.classandsec );
       			$( "#studentexternalid").val( ui.item.uid );
                    /* $("#classandsec"+rowCount).val( ui.item.classandsec ); */
                    return true;
                }
            }).data( "autocomplete" )._renderItem = function( ul, item ) {
                return $( "<li></li>" )
                .data( "item.autocomplete", item )
                .append( "<a><b> " + item.value +"/" +item.classandsec+"/"+item.fathername+"/"+item.uid+" </b> </a>" )
                .appendTo( ul );
            };
            var addFeesButtonID="#addFees";
            var removeDossageButtonID="#removeDossage";
            $( addFeesButtonID )
            .button({
                icons: {
                    primary: "ui-icon-plus"
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
        </script>
        
        <script type="text/javascript">
	function returnBook() {
		var form1 = document.getElementById("form1");
		form1.action = "/abc/LibraryProcess/bookReturnByStudent";
		form1.method = "POST";
		form1.submit();

	}
	$(function() {

		$("#tabs").tabs();
		$("#submitbtn").button().click(function() {
			returnBook();
		});
		//$("#effect").hide();

	});
	
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
					<li><a href="#fragment-1">Book Return</a></li>
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
                    <td style="width: 45%;" class="alignLeft">Student Name: &nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admno" id="admno" class="myclass" style="font-size: 14px;" onfocusout="datetowords()"/> <input name="studentId" type="hidden" id="studentId" value="" /> </td>
                        
                        <td class="alignLeft">UID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="studentexternalid" id="studentexternalid" class="myclass"  /></td>
                    </tr>
                    
                    <tr>
						<td><br></td>
                    </tr>
                    
                    
                    <tr>
                    
                        <td class="alignLeft" style="width: 45%">Admission No:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input  type="text" name="admissionnumber" id="admissionnumber" class="myclass" readonly/></td>
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
                    	
                        <td><button id="addFees">Search</button>&nbsp;&nbsp;&nbsp;</td>
                        
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
                        
                        <td class="alignLeft">UID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="returnuid" id="dateoffeesDetails" class="myclass" value="${dateoffeesDetails}" /></td>
                        
                    </tr>
                    <tr>
						<td><br></td>
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
                    <tr>
								<th class="headerText"><input type="checkbox" id="chckHead" /></th>
						<th title="click to sort" class="headerText">Book Name</th>
						<th title="click to sort" class="headerText">Book Id</th>
						<th title="click to sort" class="headerText">Date of Issue</th>
						<th title="click to sort" class="headerText">Total Days</th>
							</tr>
                </thead>

				<tbody>

						    <c:forEach items="${bookslist}" var="bookslistdetail" varStatus="status"> 
           							<tr>
           								  <td class="dataTextLeft" style="text-align:center;"><input type="checkbox" id = "<c:out value="${bookslistdetail.id}"/>" class = "chcktbl"  name="bookissueid"  value="<c:out value="${bookslistdetail.id}"/>"/></td>
									      <td class="dataTextLeft"><c:out value="${bookslistdetail.bookName}" /> </td>
									      <td class="dataTextLeft"><c:out value="${bookslistdetail.bookId}" /><input type="hidden" name="bookid"  value="<c:out value="${bookslistdetail.bookId}" />"/> </td>
									      <td class="dataTextLeft"><c:out  value="${bookslistdetail.startDate}" /></td>
									      <td class="dataTextLeft"><c:out  value="${bookslistdetail.noOfDays}" /></td>
                					</tr>
								
							</c:forEach> 
				</tbody>				
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
            
              
            <input type="button" value="Return Book" id="submitbtn"/>
            
            
           <%--  <div id="dialogpaymentmethod" title="Payment Method">
				
           		 
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
											<option value="ICICI">ICICI Bank</option>
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
											<option value="ICICI">ICICI Bank</option>
								</select>
							
							</label>
							
							</td>
							
						</tr>
						
					</table>
			</div> --%>
            </div>
            </div>
        </form>

    </body>
</html>

