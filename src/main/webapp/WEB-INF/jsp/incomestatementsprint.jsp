<%-- 
    Document   : PrintCurrentStock
    Created on : JAN 8 2021, 2:01 PM
    Author     : Musaib
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html >
<head>
<title>Income statement</title>
<style type="text/css">
<!--
.headerText {
	width: 10px;
	font-family: Tahoma;
	font-size: 12px;
	color: #FFFFFF;
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
	font-size: 12px;
	letter-spacing: normal;
	text-align: left;
}

.dataTextBoldCenter {
	font-weight: bold;
	font-family: Tahoma;
	color: black;
	font-size: 18px;
	letter-spacing: normal;
	text-align: center;
}

.addressLine{
	font-weight: bold;
	font-family: ariel;
	color: black;
	font-size: 22px;
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
.datatable {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

.datatd, .datath {
    border: 1px solid #000000;
    text-align: center;
    padding: 8px;
}

.datatd {
    border: 1px solid #000000;
    text-align: right;
    padding: 8px;
}

.datatdt{
    border: 0px solid #000000;
    text-align: left;
    padding: 8px;
}
.reportheaders{
	font-weight: bold;
	font-size: 12px;

}

</style>

<script>
    window.onload = function(){
        window.print();
    }
    
</script>

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

	

</head>




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
             margin-bottom: 1cm;
             margin-top: 1cm;
             size: auto;
        }

        @media screen {
            .fontsize { font-size: 12px;
                        font-weight: bold;
                        font-family: 'Times New Roman'
            }
            .bodymargin{
                margin-left: 0px ;
                margin-right: 0px;
            }
        }
        table,th,td{
        border:1px solid black;
        border-collapse: collapse;
        }
    </style>




<body style="text-align: center" class="bodymargin">
		<table width="100%" style="border-collapse: collapse;">
			<tr>
				<td align="center">
				<img src="/school/images/school.png" width="80" height="80"/>
				</td>
				<td class="dataTextBoldCenter" style="width: 100%">
				${branchname}<br><br>
				<label class="addressLine">Income Statements</label><br>
				 From Date: ${fromdate}&nbsp;&nbsp;&nbsp;To Date: ${todate}
				</td>
			</tr>
	</table>

				
		<div style="display: flex; gap: 0px;">
            <table width="50%" border="1" >

				<thead>
					<tr>
						<th colspan="3">Expenses</th>
					</tr>
					<tr>
					<th>Account Code</th>
					<th>Name</th>
					<th>Balance</th>
					</tr>
				</thead>

				<tbody>
										
					<c:forEach items="${expensesledgersaccount}" var="expensesledgersaccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1" >
							<td class="dataText" style="text-align: right" width="20%"><c:out value="${expensesledgersaccount.key.accountcode}" /></td>
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
					
					</tbody>
				
			</table>
			
            <table width="50%" border="1">

				<thead>
					<tr>
						<th colspan="3" >Income</th>
					</tr>
					<tr>
					<th>Account Code</th>
					<th>Name</th>
					<th>Balance</th>
					</tr>
				</thead>

				<tbody>
										
					<c:forEach items="${incomeledgersaccount}" var="incomeledgersaccount">

						<tr class="trClass" style="border-color: #000000" border="1"
							cellpadding="1" cellspacing="1">
							<td class="dataText" style="text-align: right" width="20%"><c:out value="${incomeledgersaccount.key.accountcode}" /></td>
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
			</div>
			<br>
			<div style="page-break-inside: avoid;" align="center">
			
				<table>
						<tr>
							<td>
								<p><h4>------------- End of The Report ----------</h4></p>
							</td>
						</tr>				
				</table>
			</div>
</body>
</html>
