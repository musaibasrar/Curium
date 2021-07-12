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
<title>Print Income Statement</title>
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
	height: 22px;
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
-->
.headerTD {
	width: 10px;
	font-family: Tahoma;
	font-size: 18px;
	color: #000000;
	font-weight: normal;
	width: auto;
	height: 22px;
	vertical-align: middle;
	text-align: center;
}

.trialbalance {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
}

.trialbalance td, .trialbalance th {
  border: 1px solid #000000;
  padding: 8px;
}

.trialbalance th {
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
              
             margin-left:  0cm;
             margin-right: 0cm;
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
		<br>
		<br>
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="left" style="padding-left: 130px;">
				<img src="images/logo.jpg" width="150" height="50"/>
				</td> 
				<td style="width: 100%;" align="left">
				<label class="dataTextBoldCenter">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Masji-e-Ali</label><br>
				<label class="addressLine">Arkat Galli, Noor Khan Taleem, Bidar - 585401<br>
				 </label>
				</td>
			</tr>
</table>

			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
                <tr>
                    <td colspan="4" ></td>
                </tr>
            </TABLE>

		<div>
		<table width="100%" class="trialbalance">
                    <tr>
                        <td  class="headerTD">Trial Balance<br>
                        From Date: ${fromdatetb}&nbsp;&nbsp;&nbsp;To Date: ${todatetb}</td>
                    </tr>

                    

                </table>
                
			<table width="100%" border="0" style="border-color: #4b6a84;" id="myTable" class="trialbalance">

				<thead>
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Particulars</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Debit</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Credit</th>
					</tr>
				</thead>

				<tbody>
					
					<c:forEach items="${accountdetailsbalanceMap}" var="accountdetails">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: left"><c:out value="${accountdetails.key.accountname}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							
							<c:if test="${(accountdetails.key.accountGroupMaster.accountgroupid == 1) || (accountdetails.key.accountGroupMaster.accountgroupid == 5)}">
							
									<c:if test="${accountdetails.value >= 1}">
										<td class="dataText" style="text-align: right;">
										<fmt:formatNumber type="currency" pattern="#,##0.00;" value="${accountdetails.value}" />
										<%-- <c:out value="${accountdetails.value}" /> --%>
										</td>
										<td class="dataText"></td>			
									</c:if>
									
									<c:if test="${accountdetails.value < 1}">
										<td class="dataText" ></td>
										<td class="dataText" style="text-align: right;">
										<fmt:formatNumber type="currency" pattern="#,##0.00;" value="${accountdetails.value*-1}" />
										</td>			
									</c:if>
							
							</c:if>
							
							<c:if test="${(accountdetails.key.accountGroupMaster.accountgroupid == 2) || (accountdetails.key.accountGroupMaster.accountgroupid == 3) || (accountdetails.key.accountGroupMaster.accountgroupid == 4)}">
										
									<c:if test="${accountdetails.value >= 1}">
										<td class="dataText"></td>
										<td class="dataText" style="text-align: right;"><c:out value="${accountdetails.value}" /></td>			
									</c:if>

									<c:if test="${accountdetails.value < 1}">
										<td class="dataText" style="text-align: right;"><c:out value="${accountdetails.value*-1}" /></td>
										<td class="dataText"></td>			
									</c:if>
										
							</c:if>
							
						</tr>
					</c:forEach>
					
				</tbody>
				<tfoot>
				
				<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataTextInActive" style="text-align: right;height: 20px;"><c:out value="${differencetotal}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${debitdifference}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${creditdifference}" /></td>
							
				</tr>			
				
				<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							
							<td class="dataTextInActive" style="text-align: right;height: 20px;">TOTAL</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${debittotal}" /></td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${credittotal}" /></td>
						</tr>
					
				</tfoot>
			</table>
			

		</div>
	</form>
</body>
</html>
