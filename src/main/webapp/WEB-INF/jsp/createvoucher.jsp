<%-- 
    Document   : create voucher
    Created on : Feb 20, 2018, 4:07:26 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Voucher</title>

        <link rel="stylesheet" href="/ruyaa/css/datePicker/jquery-ui-1.8.18.custom.css">

        <link rel="stylesheet" href="/ruyaa/css/datePicker/demos.css">
        <script type="text/javascript" src="/ruyaa/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="/ruyaa/js/datePicker/ui/jquery-ui-1.8.17.custom.js"></script>
        <script type="text/javascript" src="/ruyaa/js/datePicker/ui/jquery.ui.datepicker.js"></script>
  
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
            .style1 { <link rel="stylesheet" href="/ruyaa/https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
		<script src="/ruyaa/https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
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
            -->
        </style>
        
        <script type="text/javascript">
    $(function() {
    
        // run the currently selected effect
        function runEffect() {
            var clipEffect='blind';
            var options = {};
            $( "#effect" ).toggle( clipEffect, options, 1000 );
        };
        
       
        $( "#saveReceipt" ).button().click(function() {
            saveReceipt();
            return false;
        });
        $( "#savePayment" ).button().click(function() {
            savePayment();
            return false;
        });
        
        $( "#saveContra" ).button().click(function() {
        	saveContra();
            return false;
        });
        
        $( "#saveJournal" ).button().click(function() {
        	saveJournal();
            return false;
        });
    });
    
    
    function saveReceipt(){
    	
    	if(document.getElementById("dramount").vlaue == 0 || document.getElementById("cramountsecond").value == 0){
      	  alert('Voucher with zero amount cannot be saved!');
        }else if(document.getElementById("dramount").value != document.getElementById("cramountsecond").value){
        	alert('Credits must be equal to Debits.');
        }else{
        	
        	if(confirm('Are you sure,you want to save the receipt voucher?')){
        		
        		var form1 = document.getElementById("form1");
                form1.action = "/ruyaa/AccountProcess/saveReceipt";
                form1.submit();	
        	}
        	
        }
    	  
    	
    }
    
    function savePayment(){
    	
    	if(document.getElementById("dramountpayment").vlaue == 0 || document.getElementById("cramountpaymentsecond").value == 0){
        	  alert('Voucher with zero amount cannot be saved!');
          }else if(document.getElementById("dramountpayment").value != document.getElementById("cramountpaymentsecond").value){
          	alert('Credits must be equal to Debits.'); 	
          }else{
        	  if(confirm('Are you sure,you want to save the payment voucher?')){
        	  
        		  var form1 = document.getElementById("form1");
              	  form1.action = "/ruyaa/AccountProcess/savePayment";
              	  form1.submit();
        	  
        	  }
          }
  }
    
    function saveContra(){
      var accountOne = 	document.getElementById("accountbalancecontra").value;
      var accountTwo = document.getElementById("accountbalancecontrasecond").value;
      if(accountOne == accountTwo){
    	  alert('An account can be selected only once!');
      }else if(document.getElementById("dramountcontra").value == 0 || document.getElementById("cramountcontrasecond").value == 0){
    	  alert('Voucher with zero amount cannot be saved!');
      }else if(document.getElementById("dramountcontra").value != document.getElementById("cramountcontrasecond").value){
        	alert('Credits must be equal to Debits.'); 	
      }else{
    	  if(confirm('Are you sure,you want to save the contra voucher?')){
    	  var form1 = document.getElementById("form1");
          form1.action = "/ruyaa/AccountProcess/saveContra";
          form1.submit();
    	  }
      }
  	  
  	
  }
    
    function saveJournal(){
    	
    	if(document.getElementById("dramountjournal").value == 0 || document.getElementById("cramountjournalsecond").value == 0){
      	  alert('Voucher with zero amount cannot be saved!');
        }else if(document.getElementById("dramountjournal").value != document.getElementById("cramountjournalsecond").value){
        	alert('Credits must be equal to Debits.'); 	
        }else{
        	if(confirm('Are you sure,you want to save the journal voucher?')){
        	 var form1 = document.getElementById("form1");
             form1.action = "/ruyaa/AccountProcess/saveJournal";
             form1.submit();
        	}
        }
    }
        </script> 
        <script type="text/javascript">
    
            
        </script>
                <script type="text/javascript">
            $(function() {
                $( "#tabs" ).tabs();
                
                $("#dateofreceipt").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'yy-mm-dd',
        			yearRange: "-50:+0"
        		});
                
                $("#dateofpayment").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'yy-mm-dd',
        			yearRange: "-50:+0"
        		});
                
                $("#dateofcontra").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'yy-mm-dd',
        			yearRange: "-50:+0"
        		});
                
                $("#dateofjournal").datepicker({
        			changeYear : true,
        			changeMonth : true,
        			dateFormat: 'yy-mm-dd',
        			yearRange: "-50:+0"
        		});
                
                $("#dramount").keypress(function (e) {
         		     //if the letter is not digit then display error and don't type anything
         		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
         		               return false;
         		    }
         		    $('#grandTotalAmountDr').val(document.getElementById("dramount").value);
         		   });
                
                $("#dramount").keyup(function (e) {
        		    $('#grandTotalAmountDr').val(document.getElementById("dramount").value);
        		   });
                
                $("#cramountsecond").keypress(function (e) {
        		     //if the letter is not digit then display error and don't type anything
        		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
        		               return false;
        		    }
        		   });
                
                $("#cramountsecond").keyup(function (e) {
        		    $('#grandTotalAmountCr').val(document.getElementById("cramountsecond").value);
        		   });

                //payment
                
                $("#dramountpayment").keypress(function (e) {
        		     //if the letter is not digit then display error and don't type anything
        		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
        		               return false;
        		    }
        		    $('#grandTotalAmountDrPayment').val(document.getElementById("dramountpayment").value);
        		   });
               
               $("#dramountpayment").keyup(function (e) {
       		    $('#grandTotalAmountDrPayment').val(document.getElementById("dramountpayment").value);
       		   });
               
               $("#cramountpaymentsecond").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
       		               return false;
       		    }
       		   });
               
               $("#cramountpaymentsecond").keyup(function (e) {
       		    $('#grandTotalAmountCrPayment').val(document.getElementById("cramountpaymentsecond").value);
       		   });
               
               //contra
                
               $("#dramountcontra").keypress(function (e) {
       		     //if the letter is not digit then display error and don't type anything
       		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
       		               return false;
       		    }
       		    $('#grandTotalAmountDrcontra').val(document.getElementById("dramountcontra").value);
       		   });
              
              $("#dramountcontra").keyup(function (e) {
      		    $('#grandTotalAmountDrcontra').val(document.getElementById("dramountcontra").value);
      		   });
              
              $("#cramountcontrasecond").keypress(function (e) {
      		     //if the letter is not digit then display error and don't type anything
      		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
      		               return false;
      		    }
      		   });
              
              $("#cramountcontrasecond").keyup(function (e) {
      		    $('#grandTotalAmountCrcontra').val(document.getElementById("cramountcontrasecond").value);
      		   });
              
              
            //journal
              
              $("#dramountjournal").keypress(function (e) {
      		     //if the letter is not digit then display error and don't type anything
      		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
      		               return false;
      		    }
      		    $('#grandTotalAmountDrjournal').val(document.getElementById("dramountjournal").value);
      		   });
             
             $("#dramountjournal").keyup(function (e) {
     		    $('#grandTotalAmountDrjournal').val(document.getElementById("dramountjournal").value);
     		   });
             
             $("#cramountjournalsecond").keypress(function (e) {
     		     //if the letter is not digit then display error and don't type anything
     		     if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which !=46) {
     		               return false;
     		    }
     		   });
             
             $("#cramountjournalsecond").keyup(function (e) {
     		    $('#grandTotalAmountCrjournal').val(document.getElementById("cramountjournalsecond").value);
     		   });
              
            });

            
            
            window.onload=function() {
            	//receipt
            	  document.getElementById("accountname").onchange=function() {
            	    document.getElementById("accountbalance").value=this.options[this.selectedIndex].getAttribute("data-sync"); 
            	  }
            	  document.getElementById("accountname").onchange(); // trigger when loading
            	  
            	  document.getElementById("accountnamesecond").onchange=function() {
              	    document.getElementById("accountbalancesecond").value=this.options[this.selectedIndex].getAttribute("data-sync-second"); 
              	  }
              	  document.getElementById("accountnamesecond").onchange(); // trigger when loading
              	  
              	  
              	  //payment
              	document.getElementById("accountnamepayment").onchange=function() {
              	    document.getElementById("accountbalancepayment").value=this.options[this.selectedIndex].getAttribute("data-sync-payment"); 
              	  }
              	  document.getElementById("accountnamepayment").onchange(); // trigger when loading
              	  
              	  document.getElementById("accountnamepaymentsecond").onchange=function() {
                	    document.getElementById("accountbalancepaymentsecond").value=this.options[this.selectedIndex].getAttribute("data-sync-payment-second"); 
                	  }
                	  document.getElementById("accountnamepaymentsecond").onchange(); // trigger when loading
                	 
                	  // contra
                	  
                	  document.getElementById("accountnamecontra").onchange=function() {
                    	    document.getElementById("accountbalancecontra").value=this.options[this.selectedIndex].getAttribute("data-sync-contra"); 
                    	  }
                    	  document.getElementById("accountnamecontra").onchange(); // trigger when loading
                    	  
                    	  document.getElementById("accountnamecontrasecond").onchange=function() {
                      	    document.getElementById("accountbalancecontrasecond").value=this.options[this.selectedIndex].getAttribute("data-sync-contra-second"); 
                      	  }
                      	  document.getElementById("accountnamecontrasecond").onchange(); // trigger when loading
                      	  
                      	  
                      	 // journal
                    	  
                    	  document.getElementById("accountnamejournal").onchange=function() {
                        	    document.getElementById("accountbalancejournal").value=this.options[this.selectedIndex].getAttribute("data-sync-journal"); 
                        	  }
                        	  document.getElementById("accountnamejournal").onchange(); // trigger when loading
                        	  
                        	  document.getElementById("accountnamejournalsecond").onchange=function() {
                          	    document.getElementById("accountbalancejournalsecond").value=this.options[this.selectedIndex].getAttribute("data-sync-journal-second"); 
                          	  }
                          	  document.getElementById("accountnamejournalsecond").onchange(); // trigger when loading
                          	  
              	  
            	}
            
            	
            
        </script>
    </head>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("userAuth") == null){
	response.sendRedirect("/ruyaa/UserProcess/sessionTimeOut");
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
    <jsp:useBean id="now" class="java.util.Date" scope="page" />
        <form id="form1" method="post" ">

            <div id="tabs">
				<ul>
					<li><a href="#tabs-1">Receipt</a></li>
					<li><a href="#tabs-2">Payment</a></li>
					<li><a href="#tabs-3">Contra</a></li>
					<li><a href="#tabs-4">Journal</a></li>
				</ul>
				<div id="tabs-1">
					<table  width="100%">
               
                <tbody>
                
	                <tr>
                    	<td align="center">
                    	<label style="font-weight: bold;font-size: 20px;color: #EB6000">Receipt&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    	</td>
                    </tr>
                    
                    <tr>
                        <td><label style="font-weight: bold;">Receipt Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                        <input
									name="dateofreceipt" type="text" class="textField"
									id="dateofreceipt" size="25" 
									value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" 
									required/> </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    	&nbsp;&nbsp;&nbsp;
                    	</td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                    <tr >
                        <td class="headerText">Cr/Dr</td>
                        <td class="headerText">Account Name</td>                       
                        <td class="headerText">Balance</td>
                        <td class="headerText">Dr Amount</td>
						<td class="headerText">Cr Amount</td>
                    </tr>
                </thead>

				<tbody>
				<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Dr" readonly /></td>
					<td align="center"><label>
									<select name="accountname" id="accountname" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancereceipt}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalance" id="accountbalance" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancereceipt}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />) 
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramount" id="dramount" value="0"/></b></td>
					<td align="center"><b><input type="text" name="cramount" id="cramount" style="background-color: #E3E3E3;" readonly/></b></td>
					</tr>
					
					<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Cr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamesecond" id="accountnamesecond" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalanceexbc}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync-second="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalancesecond" id="accountbalancesecond" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalanceexbc}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountsecond" id="dramountsecond" style="background-color: #E3E3E3;" readonly/></b></td>
					<td align="center"><b><input type="text" name="cramountsecond" id="cramountsecond" value="0"/></b></td>
					</tr>
				</tbody>
				 <tr>
						<td><br></td>
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
				<tfoot>
                    
                    <tr>

                        <td colspan="3" align="right"><b>Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountDr" id="grandTotalAmountDr" value="0" readonly /></b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountCr" id="grandTotalAmountCr" value="0" readonly /></b></td>
                    </tr>
					<tr>
						<td align="right"><b>Narration&nbsp;&nbsp;</b></td>
                        <td align="left"><label><textarea  name="receiptnarration"
											type="text" class="textField" id="receiptnarration" rows="2" cols="40"
											
											onkeypress="return validateContactNum(this);"></textarea></label></td>
                    </tr>
                    <tr>
                    <td>
                    <input type="hidden" value="1" name="receiptvoucher" id="receiptvoucher" >
                    </td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td colspan="2" align="right">
								<button id="saveReceipt">Save</button>
							</td>
							</tr>						
                </tfoot>
			</table>
				</div>

				<div id="tabs-2">
					<table  width="100%">
               
                <tbody>
                
                <tr>
                    	<td align="center">
                    	<label style="font-weight: bold;font-size: 20px;color: #EB6000">Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    	</td>
                    </tr>
	                
                    <tr>
                        <td><label style="font-weight: bold;">Payment Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                        <input
									name="dateofpayment" type="text" class="textField"
									id="dateofpayment" size="25" 
									value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" 
									required/> </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    	&nbsp;&nbsp;&nbsp;
                    	</td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                    <tr >
                        <td class="headerText">Cr/Dr</td>
                        <td class="headerText">Account Name</td>                       
                        <td class="headerText">Balance</td>
                        <td class="headerText">Dr Amount</td>
						<td class="headerText">Cr Amount</td>
                    </tr>
                </thead>

				<tbody>
				<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Dr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamepayment" id="accountnamepayment" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalanceexpacc}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync-payment="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label><select name="accountbalancepayment" id="accountbalancepayment" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalanceexpacc}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountpayment" id="dramountpayment" value="0"/></b></td>
					<td align="center"><b><input type="text" name="cramountpayment" id="cramountpayment" style="background-color: #E3E3E3;" readonly/></b></td>
					</tr>
					
					<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Cr" readonly /></td>
					<td align="center"><label>
										<select name="accountnamepaymentsecond" id="accountnamepaymentsecond" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancepayment}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync-payment-second="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select>
									</label></td>
					<td align="center"><label>
					<select name="accountbalancepaymentsecond" id="accountbalancepaymentsecond" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancepayment}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />)
											</option>


										</c:forEach>

								</select>
								
									</label></td>
					<td align="center"><b><input type="text" name="dramountpaymentsecond" id="dramountpaymentsecond" style="background-color: #E3E3E3;" readonly/></b></td>
					<td align="center"><b><input type="text" name="cramountpaymentsecond" id="cramountpaymentsecond" value="0"/></b></td>
					</tr>
				</tbody>
				 <tr>
						<td><br></td>
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
				<tfoot>
                    
                    <tr>

                        <td colspan="3" align="right"><b>Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountDrPayment" id="grandTotalAmountDrPayment" value="0" readonly /></b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountCrPayment" id="grandTotalAmountCrPayment" value="0" readonly /></b></td>
                    </tr>
					<tr>
						<td align="right"><b>Narration&nbsp;&nbsp;</b></td>
                        <td align="left"><label><textarea  name="paymentnarration"
											type="text" class="textField" id="paymentnarration" rows="2" cols="40"
											
											onkeypress="return validateContactNum(this);"></textarea></label></td>
                    </tr>
                    <tr>
                    <td>
                    <input type="hidden" value="2" name="paymentvoucher" id="paymentvoucher" >
                    </td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td colspan="2" align="right">
								<button id="savePayment">Save</button>
							</td>
							</tr>						
                </tfoot>
			</table>
				</div>
				
				
				<div id="tabs-3">
					<table  width="100%">
               
                <tbody>
	                
	                <tr>
                    	<td align="center">
                    	<label style="font-weight: bold;font-size: 20px;color: #EB6000">Contra&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    	</td>
                    </tr>
                    
                    <tr>
                        <td><label style="font-weight: bold;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                        <input
									name="dateofcontra" type="text" class="textField"
									id="dateofcontra" size="25" 
									value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" 
									required/> </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    	&nbsp;&nbsp;&nbsp;
                    	</td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                    <tr >
                        <td class="headerText">Cr/Dr</td>
                        <td class="headerText">Account Name</td>                       
                        <td class="headerText">Balance</td>
                        <td class="headerText">Dr Amount</td>
						<td class="headerText">Cr Amount</td>
                    </tr>
                </thead>

				<tbody>
				<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Dr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamecontra" id="accountnamecontra" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancecontra}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync-contra="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalancecontra" id="accountbalancecontra" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancecontra}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountcontra" id="dramountcontra" value="0"/></b></td>
					<td align="center"><b><input type="text" name="cramountcontra" id="cramountcontra" style="background-color: #E3E3E3;" readonly/></b></td>
					</tr>
					
					<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Cr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamecontrasecond" id="accountnamecontrasecond" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancecontra}" var="accountdetailsbalance">

											<option value="${accountdetailsbalance.accountDetails.accountdetailsid}" data-sync-contra-second="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalancecontrasecond" id="accountbalancecontrasecond" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancecontra}" var="accountdetailsbalance">

											<option  value="${accountdetailsbalance.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalance.currentbalance}" /> (<c:out value="${accountdetailsbalance.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountcontrasecond" id="dramountcontrasecond" style="background-color: #E3E3E3;" readonly/></b></td>
					<td align="center"><b><input type="text" name="cramountcontrasecond" id="cramountcontrasecond" value="0"/></b></td>
					</tr>
				</tbody>
				 <tr>
						<td><br></td>
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
				<tfoot>
                    
                    <tr>

                        <td colspan="3" align="right"><b>Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountDrcontra" id="grandTotalAmountDrcontra" value="0" readonly /></b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountCrcontra" id="grandTotalAmountCrcontra" value="0" readonly /></b></td>
                    </tr>
					<tr>
						<td align="right"><b>Narration&nbsp;&nbsp;</b></td>
                        <td align="left"><label><textarea  name="contranarration"
											type="text" class="textField" id="contranarration" rows="2" cols="40"
											
											onkeypress="return validateContactNum(this);"></textarea></label></td>
                    </tr>
                    <tr>
                    <td>
                    <input type="hidden" value="3" name="contravoucher" id="contravoucher" >
                    </td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td colspan="2" align="right">
								<button id="saveContra">Save</button>
							</td>
							</tr>						
                </tfoot>
			</table>
				</div>
				
				<div id="tabs-4">
					<table  width="100%">
               
                <tbody>
	                
	                <tr>
                    	<td align="center">
                    	<label style="font-weight: bold;font-size: 20px;color: #EB6000">Journal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                    	</td>
                    </tr>
                    
                    <tr>
                        <td><label style="font-weight: bold;">Date:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label> 
                        <input
									name="dateofjournal" type="text" class="textField"
									id="dateofjournal" size="25" 
									value="<fmt:formatDate type="date" value="${now}" pattern="yyyy-MM-dd"/>" 
									required/> </td>
                        
                    </tr>
                    <tr>
                    	<td>
                    	&nbsp;&nbsp;&nbsp;
                    	</td>
                    </tr>
                    <tr>
						<td><br></td>
                    </tr>
                </tbody>
            </table>
            
            <table width="100%" border="0" style="border-color: #4b6a84;"
				id="myTable">

				<thead>
                    <tr >
                        <td class="headerText">Cr/Dr</td>
                        <td class="headerText">Account Name</td>                       
                        <td class="headerText">Balance</td>
                        <td class="headerText">Dr Amount</td>
						<td class="headerText">Cr Amount</td>
                    </tr>
                </thead>

				<tbody>
				<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Dr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamejournal" id="accountnamejournal" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancejournal}" var="accountdetailsbalancejournal">

											<option value="${accountdetailsbalancejournal.accountDetails.accountdetailsid}" data-sync-journal="${accountdetailsbalancejournal.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalancejournal.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalancejournal" id="accountbalancejournal" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancejournal}" var="accountdetailsbalancejournal">

											<option  value="${accountdetailsbalancejournal.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalancejournal.currentbalance}" /> (<c:out value="${accountdetailsbalancejournal.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountjournal" id="dramountjournal" value="0"/></b></td>
					<td align="center"><b><input type="text" name="cramountjournal" id="cramountjournal" style="background-color: #E3E3E3;" readonly/></b></td>
					</tr>
					
					<tr>
					<td align="center" ><input size="2" style="font-weight: bold;background-color: #EEEEEE;font-size: 12px;" type="text" value="Cr" readonly /></td>
					<td align="center"><label>
									<select name="accountnamejournalsecond" id="accountnamejournalsecond" 
									style="width: 240px;"">
										

										<c:forEach items="${accountdetailsbalancejournal}" var="accountdetailsbalancejournal">

											<option value="${accountdetailsbalancejournal.accountDetails.accountdetailsid}" data-sync-journal-second="${accountdetailsbalancejournal.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalancejournal.accountDetails.accountname}" />
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><label>
									<select name="accountbalancejournalsecond" id="accountbalancejournalsecond" disabled="true"
									style="width: 240px;-webkit-appearance: none;
    -moz-appearance: none;background-color: #E3E3E3;
    text-indent: 1px;
    text-overflow: '';font-weight: bold;color: black;"">

										<c:forEach items="${accountdetailsbalancejournal}" var="accountdetailsbalancejournal">

											<option  value="${accountdetailsbalancejournal.accountDetails.accountdetailsid}">
												<c:out value="${accountdetailsbalancejournal.currentbalance}" /> (<c:out value="${accountdetailsbalancejournal.crdr}" />)
											</option>


										</c:forEach>

								</select></label></td>
					<td align="center"><b><input type="text" name="dramountjournalsecond" id="dramountjournalsecond" style="background-color: #E3E3E3;" readonly/></b></td>
					<td align="center"><b><input type="text" name="cramountjournalsecond" id="cramountjournalsecond" value="0"/></b></td>
					</tr>
				</tbody>
				 <tr>
						<td><br></td>
                    </tr>
                     <tr>
						<td><br></td>
                    </tr>
				<tfoot>
                    
                    <tr>

                        <td colspan="3" align="right"><b>Total&nbsp;&nbsp;</b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountDrjournal" id="grandTotalAmountDrjournal" value="0" readonly /></b></td>
                        <td align="center"><b><input type="text" name="grandTotalAmountCrjournal" id="grandTotalAmountCrjournal" value="0" readonly /></b></td>
                    </tr>
					<tr>
						<td align="right"><b>Narration&nbsp;&nbsp;</b></td>
                        <td align="left"><label><textarea  name="journalnarration"
											type="text" class="textField" id="journalnarration" rows="2" cols="40"
											
											onkeypress="return validateContactNum(this);"></textarea></label></td>
                    </tr>
                    <tr>
                    <td>
                    <input type="hidden" value="4" name="journalvoucher" id="journalvoucher" >
                    </td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td></td>
                    </tr>
                    <tr>
                    <td colspan="2" align="right">
								<button id="saveJournal">Save</button>
							</td>
							</tr>						
                </tfoot>
			</table>
				</div>
				
			</div>
        </form>

    </body>
</html>
