<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>Payments & Receipts Statement</title>
<head>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #000000;
	font-weight: normal;
	width: auto;
	height: auto;
	vertical-align: middle;
	text-align: center;
}

.headerTextLeft {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: left;
}

.dataTextBold {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextBoldLeft {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 16px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldRight {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: right;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 32px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: normal;
	font-family: ariel;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.dataText {
	font-family: Tahoma;
	color: black;
	font-size: 12px;
	letter-spacing: normal;
	text-align: center;
}

.dataTextInActive {
	font-family: Tahoma;
	font-size: 12px;
	font-weight: bold;
	letter-spacing: normal;
}
-->
.headerTD {
	width: 10px;
	font-family: Tahoma;
	font-size: 18px;
	color: #000000;
	font-weight: normal;
	width: auto;
	height: 18px;
	vertical-align: middle;
	text-align: center;
}

.incomeexpense {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
}

.incomeexpense td, #incomeexpense th {
  border: 1px solid #000000;
  padding: 8px;
}

.incomeexpense th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: center;
  background-color: #FFFFFF;
  color: black;
  font-size: 14px;
}

</style>


<!-- <style type="text/css">

        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
            	margin-top: 0px;
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              size: auto;   /* auto is the current printer page size */
           	  margin: 0mm;  /* this affects the margin in the printer settings */ 
            
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style> -->
    
    <style type="text/css">

	
        @media print {
            .fontsize { font-size: 15px ;
                        font-weight: bold;
                        font-family: 'Times New Roman';
                        
                        
            }
            .header,.hide { visibility: hidden }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
            
        }
        
        @page {
              
             margin-left:  1cm;
             margin-right: 1cm;
             margin-bottom: 0cm;
             margin-top: 0cm;
        }

        @media screen {
            .fontsize { font-size: 15px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
    </style>


<script>
    window.onload = function(){
        window.print();
    }
    
</script>
</head>


<body style="text-align: center" class="bodymargin">
	<form method="post" class="bodymargin">
	
		<table style="page-break-inside: avoid;border-collapse: collapse;margin-left: auto;margin-right: auto;">
                        		
			<tr>
				<td><img src="/jih/images/jih.png" width="80" height="80"/></td>
				<td>
				<label class="dataTextBoldCenter" style="text-transform: uppercase;">${branchname}</label><br>
				<label class="addressLine">${branchaddress}</label><br>
				<label class="addressLine">Baitulmaal</label><br>
				<label class="addressLine">Receipts & Payments Statement</label>
				</td>
			</tr>
		</table>
		
			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<div>
				<table width="100%">
                    <tr>
                        <td  class="headerTD">From ${fromdate}&nbsp;&nbsp;To&nbsp;&nbsp;${todate} </td>
                    </tr>
                </table>
                
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left"	>

				<thead>
					
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Sl.No.</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Receipts</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Amount</th>
					</tr>
				</thead>

				<tbody>
					
					<c:forEach items="${incomeledgersaccount}" var="incomeledgersaccount" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: center" width="20%"><c:out value="${status.index+1}" /></td>
							<td class="dataText" style="text-align: left" width="50%"><c:out value="${incomeledgersaccount.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${incomeledgersaccount.value}" />
							</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left;">

				<thead>
					
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Sl.No.</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Payments</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Amount</th>
					</tr>
				</thead>
				<tbody>
						<c:set var="sequenceCounter" value="1" scope="page"/>
						<c:forEach items="${expenseledgersaccountclub}" var="expenseledgersaccountclub" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: center" width="20%"><c:out value="${sequenceCounter}" /></td>
							<td class="dataText" style="text-align: left" width="50%"><c:out value="${expenseledgersaccountclub.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${expenseledgersaccountclub.value}" />
							</td>

						</tr>
						<c:set var="sequenceCounter" value="${sequenceCounter + 1}" scope="page"/>
					</c:forEach>
							
					<c:forEach items="${maphalqasharepaidaccount}" var="maphalqasharepaidaccount" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: center" width="20%"><c:out value="${sequenceCounter}" /></td>
							<td class="dataText" style="text-align: left" width="50%"><c:out value="${maphalqasharepaidaccount.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${maphalqasharepaidaccount.value}" />
							</td>

						</tr>
						<c:set var="sequenceCounter" value="${sequenceCounter + 1}" scope="page"/>
					</c:forEach>
					
					<c:forEach items="${expensesledgersaccount}" var="expensesledgersaccount" varStatus="status">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: center" width="20%"><c:out value="${sequenceCounter}" /></td>
							<td class="dataText" style="text-align: left" width="50%"><c:out value="${expensesledgersaccount.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${expensesledgersaccount.value}" />
							</td>

						</tr>
						<c:set var="sequenceCounter" value="${sequenceCounter + 1}" scope="page"/>
					</c:forEach>
				</tbody>
			</table>
			
			
			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
	                <tr>
	                    <td colspan="4" ></td>
	                </tr>
            	</TABLE>
                
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left"	>

				<tbody>
					
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${incometotallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${incometotal}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Opening Balance Cash" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${openingbalance}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Opening Balance Bank" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${openingbalancebank}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Total" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${grandreceipttotal}" />
							</td>

						</tr>
						
						
				</tbody>
			</table>
			
			
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left;">

				<tbody>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${expensetotallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${expensetotal}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Closing Balance Cash" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${closingbalance}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Closing Balance Bank" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${closingbalancebank}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="Total" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${grandpaymenttotal}" />
							</td>

						</tr>
				</tbody>
			</table>
			
			
			<table width="50%" style="border-color: #4b6a84;float: left;">

				<tbody>
						<tr><td><br/></td></tr>
						<tr class="trClass">
							<td class="dataTextInActive" style="text-align: left;height: 20px;width:40%">Sign Ameer-e-Muqami</td>
							<td class="dataTextInActive" style="text-align: center;">:</td>
							<td class="dataTextInActive" style="text-align: left">-----------------------------</td>

						</tr>
						
						<tr class="trClass" >
							<td class="dataTextInActive" style="text-align: left;height: 60px;width:40%" >Sign Accountant</td>
							<td class="dataTextInActive" style="text-align: center;">:</td>
							<td class="dataTextInActive" style="text-align: left">-----------------------------</td>

						</tr>
						
					   <tr class="trClass">
							<td class="dataTextInActive" style="text-align: left;height: 60px;width:40%">Sign Treasurer</td>
							<td class="dataTextInActive" style="text-align: center;">:</td>
							<td class="dataTextInActive" style="text-align: left" >-----------------------------</td>

						</tr>
						
						<tr class="trClass">
							<td class="dataTextInActive" style="text-align: left;height: 0px;width:40%">Date</td>
							<td class="dataTextInActive" style="text-align: center;">:</td>
							<td class="dataTextInActive" style="text-align: left"></td>

						</tr>
						
						
				</tbody>
			</table>
			
                 <table width="50%" border="0" style="border-color: #4b6a84;float: left;">
                    <tr>
                        <td  class="headerTD">Halqa Share Details</td>
                    </tr>
                </table>
			
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left;margin-bottom:0px;">
			
				<tbody>
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataTextInActive" style="text-align: left;width:60%">Halqa Previous Dues</td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${halqasharepreviousdue}" />
							</td>
						</tr>
					
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataTextInActive" style="text-align: left;width:60%">Income Of the Month</td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalpayablehalqaduration}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataTextInActive" style="text-align: left;width:60%">Total Due</td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;">
							<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${halqatotaldue}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataTextInActive" style="text-align: left;width:60%">Paid Halqa Share</td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${totalhalqasharepaid}" />
							</td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataTextInActive" style="text-align: left;width:60%">Payable to Halqa</td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;">
								<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${halqatotalpayable}" />
							</td>

						</tr>
				</tbody>
			</table>
			
		</div>
	</form>
</body>
</html>
