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
<title>Income Statement</title>
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
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/jih/images/jih.png" width="150" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				${branchname}<br><br>
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
                        <td  class="headerTD">Receipts & Payments Statement</td>
                    </tr>
                    
                    <tr>
                        <td  class="headerTD">From ${fromdate}&nbsp;&nbsp;To&nbsp;&nbsp;${todate} </td>
                    </tr>
                </table>
                
                <TABLE  width="100%" border="1" style="border-collapse:collapse;">
	                <tr>
	                    <td colspan="4" ></td>
	                </tr>
            	</TABLE>
                
               
                <table width="50%" border="0"  style="border-color: #4b6a84;float: left;">
                    <tr>
                        <td  class="headerTD">Receipts</td>
                    </tr>
                </table>
                
                 <table width="50%" border="0" style="border-color: #4b6a84;float: left;">
                    <tr>
                        <td  class="headerTD">Payments</td>
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
							<td class="dataText" style="text-align: right" width="20%"><c:out value="${status.index+1}" /></td>
							<td class="dataText" style="text-align: right" width="50%"><c:out value="${incomeledgersaccount.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%"><c:out value="${incomeledgersaccount.value}" /></td>

						</tr>
					</c:forEach>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"></td>

						</tr>
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${incometotallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"><c:out value="${incometotal}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${losslabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"><c:out value="${totalloss}" /></td>

						</tr>
						
						
				</tbody>
			</table>
			
			
			<table width="50%" border="1" class="incomeexpense" style="border-color: #4b6a84;float: left;margin-bottom:50px;">

				<thead>
					
					<tr>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Sl.No.</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Payments</th>
						<th title="click to sort" class="headerText" style="font-weight: bold;">Amount</th>
					</tr>
				</thead>
				<tbody>
										
					<c:forEach items="${expensesledgersaccount}" var="expensesledgersaccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"><c:out value="${status.index+1}" /></td>
							<td class="dataText" style="text-align: right" width="50%"><c:out value="${expensesledgersaccount.key.accountname}" /></td>
							<td class="dataText" style="text-align: right" width="30%"><c:out value="${expensesledgersaccount.value}" /></td>

						</tr>
					</c:forEach>
					
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"></td>

						</tr>
					
					
					<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${expensetotallabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"><c:out value="${expensetotal}" /></td>

						</tr>
						
						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"></td>
							<td class="dataTextInActive" style="text-align: right;height: 20px;" width="50%"><c:out value="${profitlabel}" /></td>
							<td class="dataTextInActive" style="text-align: right" width="30%"><c:out value="${totalprofit}" /></td>

						</tr>
						
						<%-- <tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" width="80%">
							
							<td class="dataTextInActive" style="text-align: left;height: 20px;">TOTAL</td>
							<td class="dataTextInActive" style="text-align: right"><c:out value="${grouponetotal}" /></td>

						</tr> --%>
					
				</tbody>
			</table>
			
			
			<TABLE  width="100%" border="1" style="border-collapse:collapse;">
	                <tr>
	                    <td colspan="4" ></td>
	                </tr>
            	</TABLE>
		</div>
	</form>
</body>
</html>
